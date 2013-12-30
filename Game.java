package com.auditory;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.openal.AL;
import org.lwjgl.LWJGLException;

import com.auditory.systems.RenderSystem;
import com.auditory.util.Log;

public class Game {
	public World world;
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
			Log.debug("Failed to create window");
			return;
		}
		
		//Shaders
		//shaderProgram = S
		
		world = new World();
		
		world.addSystem(new RenderSystem());
		
		Entity player = EntityFactory.createPlayer(world, 0, 0, 0);
		Entity cube = EntityFactory.createCube(world, 0, 0, -2);
		world.addEntity(player);
		world.addEntity(cube);
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
			
			//Render
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
			glColor3f(0.5f, 0.5f, 0.5f);
			glLoadIdentity();
			
			//Update the world
			world.update();
	        
	        Display.update();
		}
		close();
	}
	
	public void close() {
		world.destroy();
		Display.destroy();
		AL.destroy();
	}
	
	public static void main(String[] args) {
		new Game();
	}
}
