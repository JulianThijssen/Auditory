package com.auditory;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.openal.AL;
import org.lwjgl.LWJGLException;

import com.auditory.audio.AudioListener;
import com.auditory.audio.AudioSource;
import com.auditory.entities.Player;
import com.auditory.entities.TestBlock;
import com.auditory.geom.Face;
import com.auditory.geom.Model;
import com.auditory.util.Camera;
import com.auditory.util.Log;
import com.auditory.util.OBJLoader;
import com.auditory.util.Vector3;

public class Game {
	public Camera camera;
	public Model model;
	public int modelDisplayList;
	public Player player = new Player(0, 0, 0);
	public TestBlock block = new TestBlock(0, 0, 0);
	
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
		
		//Graphics
		camera = new Camera();
        camera.applyPerspectiveMatrix();
		
		//block.addAudioSource(new AudioSource(block, "civilian_mono.wav"));
		//block.getAudioSource(0).playAudio(false);
		player.addAudioListener(new AudioListener(player));
		player.addAudioSource(new AudioSource(player, "footstep_wood1.wav"));
		try {
			model = OBJLoader.load("res/Cube.obj");
			model.printModel();
		} catch(FileNotFoundException fe) {
			Log.debug("The file was not found");
		} catch(IOException ie) {
			Log.debug("There was a problem while reading from file");
		}
		
		modelDisplayList = GL11.glGenLists(1);
		GL11.glNewList(modelDisplayList, GL11.GL_COMPILE);
		GL11.glBegin(GL11.GL_TRIANGLES);
		for(Face face: model.faces) {
			Vector3 v1 = model.vertices.get(face.vertices[0] - 1);
			GL11.glVertex3f(v1.x, v1.y, v1.z);
			Vector3 n1 = model.normals.get(face.normals[0] - 1);
			GL11.glNormal3f(n1.x, n1.y, n1.z);
			Vector3 v2 = model.vertices.get(face.vertices[1] - 1);
			GL11.glVertex3f(v2.x, v2.y, v2.z);
			Vector3 n2 = model.normals.get(face.normals[1] - 1);
			GL11.glNormal3f(n2.x, n2.y, n2.z);
			Vector3 v3 = model.vertices.get(face.vertices[2] - 1);
			GL11.glVertex3f(v3.x, v3.y, v3.z);
			Vector3 n3 = model.normals.get(face.normals[2] - 1);
			GL11.glNormal3f(n3.x, n3.y, n3.z);
		}
		GL11.glEnd();
		GL11.glEndList();
		
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
			GL11.glLoadIdentity();
	        camera.applyTransformMatrix();
	        camera.applyPerspectiveMatrix();
			//Graphics
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
			GL11.glColor3f(0.5f, 0.5f, 0.5f);
			GL11.glCallList(modelDisplayList);
			
			
		}
		close();
	}
	
	public void close() {
		GL11.glDeleteLists(modelDisplayList, 1);
		Display.destroy();
		AL.destroy();
	}
	
	public static void main(String[] args) {
		new Game();
	}
}
