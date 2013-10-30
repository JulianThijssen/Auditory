package com.auditory;

import static org.lwjgl.opengl.GL11.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.openal.AL;
import org.lwjgl.LWJGLException;

import com.auditory.audio.AudioListener;
import com.auditory.audio.AudioSource;
import com.auditory.behaviours.PlayerMovement;
import com.auditory.components.Mesh;
import com.auditory.entities.Player;
import com.auditory.gameobjects.Block;
import com.auditory.geom.Face;
import com.auditory.geom.Model;
import com.auditory.geom.Vector3;
import com.auditory.util.Log;
import com.auditory.util.OBJLoader;

public class Game {
	
	public Camera camera;
	public Model model;
	public int cubeList;
	public Player player = new Player(0, 0, 0);
	public Block block = new Block(0, 0, 0);
	public int shaderProgram;
	
	public Game() {
		init();
	}
	
	public void init() {
		try {
			Display.setDisplayMode(new DisplayMode(512, 512));
			Display.create();
			AL.create();
		} catch(LWJGLException e) {
			e.printStackTrace();
			System.out.println("Failed to create window");
			return;
		}
		
		//Audio
		//player.addComponent(new AudioListener(player));
		//player.addComponent(new AudioSource(player, "footstep_wood1.wav"));
		
		//Shaders
		//shaderProgram = S
		
		//Graphics
        player.addChild(new Camera(0, 0, 0));
        player.addComponent(new PlayerMovement(player));
		
        
        cubeList = loadModel("res/Fountain.obj");
		
		block.addComponent(new Mesh(block, cubeList));
		
		//Lock the cursor to the screen
		Mouse.setGrabbed(true);
		
		update();
	}
	
	public void update() {
		while(!Display.isCloseRequested()) {
	        if (Mouse.isButtonDown(0)) {
	            Mouse.setGrabbed(true);
	        } else if (Mouse.isButtonDown(1)) {
	            Mouse.setGrabbed(false);
	        }
			Display.sync(60);
			Display.update();
			
			//Update entities
			player.update();
			
			//Render
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
			glColor3f(0.5f, 0.5f, 0.5f);
			
			glLoadIdentity();
	        player.camera.applyTransformMatrix();
	        
			block.render();
		}
		close();
	}
	
	public int loadModel(String filepath) {
		try {
			model = OBJLoader.load("res/Fountain.obj");
		} catch(FileNotFoundException fe) {
			Log.debug("The file was not found");
		} catch(IOException ie) {
			Log.debug("There was a problem while reading from file");
		}
		
		int list = glGenLists(1);
		glNewList(cubeList, GL_COMPILE);
		glBegin(GL_TRIANGLES);
		for(Face face: model.faces) {
			Vector3 v1 = model.vertices.get(face.vertices[0] - 1);
			glVertex3f(v1.x, v1.y, v1.z);
			Vector3 n1 = model.normals.get(face.normals[0] - 1);
			glNormal3f(n1.x, n1.y, n1.z);
			Vector3 v2 = model.vertices.get(face.vertices[1] - 1);
			glVertex3f(v2.x, v2.y, v2.z);
			Vector3 n2 = model.normals.get(face.normals[1] - 1);
			glNormal3f(n2.x, n2.y, n2.z);
			Vector3 v3 = model.vertices.get(face.vertices[2] - 1);
			glVertex3f(v3.x, v3.y, v3.z);
			Vector3 n3 = model.normals.get(face.normals[2] - 1);
			glNormal3f(n3.x, n3.y, n3.z);
		}
		glEnd();
		glEndList();
		
		return list;
	}
	
	public void close() {
		glDeleteLists(cubeList, 1);
		Display.destroy();
		AL.destroy();
	}
	
	public static void main(String[] args) {
		new Game();
	}
}
