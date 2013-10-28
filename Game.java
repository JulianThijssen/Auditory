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
	public int modelDisplayList;
	public Player player = new Player(0, 0, 0);
	public Block block = new Block(0, 0, 0);
	public Block block2 = new Block(2, 0, 0);
	
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
		//block.addAudioSource(new AudioSource(block, "civilian_mono.wav"));
		//block.getAudioSource(0).playAudio(false);
		player.addAudioListener(new AudioListener(player));
		player.addAudioSource(new AudioSource(player, "footstep_wood1.wav"));
		
		//Graphics
		camera = new Camera();
        camera.applyPerspectiveMatrix();
		
		try {
			model = OBJLoader.load("res/Cube.obj");
		} catch(FileNotFoundException fe) {
			Log.debug("The file was not found");
		} catch(IOException ie) {
			Log.debug("There was a problem while reading from file");
		}
		
		modelDisplayList = glGenLists(1);
		glNewList(modelDisplayList, GL_COMPILE);
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
		
		block.setModel(modelDisplayList);
		block2.setModel(modelDisplayList);
		
		//Lock the cursor to the screen
		Mouse.setGrabbed(true);
		
		update();
	}
	
	public void update() {
		while(!Display.isCloseRequested()) {
			camera.processMouse(1);
	        camera.processKeyboard();
	        System.out.println(camera.position);
	        if (Mouse.isButtonDown(0)) {
	            Mouse.setGrabbed(true);
	        } else if (Mouse.isButtonDown(1)) {
	            Mouse.setGrabbed(false);
	        }
			Display.sync(60);
			Display.update();
			
			player.update();
			glLoadIdentity();
	        camera.applyTransformMatrix();
	        camera.applyPerspectiveMatrix();
	        
			//Render
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
			glColor3f(0.5f, 0.5f, 0.5f);
			block.render();
			block2.render();
		}
		close();
	}
	
	public void close() {
		glDeleteLists(modelDisplayList, 1);
		Display.destroy();
		AL.destroy();
	}
	
	public static void main(String[] args) {
		new Game();
	}
}
