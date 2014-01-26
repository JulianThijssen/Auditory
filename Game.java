package com.auditory;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;
import org.lwjgl.openal.AL;
import org.lwjgl.LWJGLException;

import com.auditory.systems.RenderSystem;
import com.auditory.util.Log;

public class Game {
	public World world;
	
	public Game() {
		init();
	}
	
	public void init() {
		PixelFormat pixelFormat = new PixelFormat();
		ContextAttribs contextAttributes = new ContextAttribs(3, 2).withForwardCompatible(true).withProfileCore(true);

		try {
			Display.setDisplayMode(new DisplayMode(512, 512));
			Display.setTitle("Auditory");
			//Display.create();
			Display.create(pixelFormat, contextAttributes);
			AL.create();
		} catch(LWJGLException e) {
			e.printStackTrace();
			Log.debug("Failed to create window");
			return;
		}
		Log.debug("OpenGL version: " + GL11.glGetString(GL11.GL_VERSION));
		
		world = new World();

		world.addSystem(new RenderSystem());
		
		Entity player = EntityFactory.createPlayer(world, 0, 0, 5);
		Entity cube = EntityFactory.createCube(world, 0, 0, -10);
		Entity plane = EntityFactory.createPlane(world, 0, 0, 0, 30, 30);
		world.addEntity(player);
		world.addEntity(cube);
		world.addEntity(plane);
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
