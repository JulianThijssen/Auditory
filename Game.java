package com.auditory;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.openal.AL;
import org.lwjgl.LWJGLException;

import com.auditory.audio.AudioListener;
import com.auditory.audio.AudioSource;
import com.auditory.entities.Player;
import com.auditory.entities.TestBlock;

public class Game {
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
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 512, 0, 512, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		block.addAudioSource(new AudioSource(block, "civilian_mono.wav"));
		block.getAudioSource(0).playAudio(false);
		player.addAudioListener(new AudioListener(player));
		player.addAudioSource(new AudioSource(player, "footstep_wood1.wav"));
		
		update();
	}
	
	public void update() {
		while(!Display.isCloseRequested()) {
			Display.sync(60);
			Display.update();
			
			player.update();
			
			//Graphics
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			GL11.glColor3f(0.5f, 0.5f, 0.5f);
		}
		close();
	}
	
	public void close() {
		Display.destroy();
		AL.destroy();
	}
	
	public static void main(String[] args) {
		new Game();
	}
}
