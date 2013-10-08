package com.auditory;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.LWJGLException;
import org.lwjgl.util.WaveData;
import com.auditory.util.Vector3;

import java.nio.IntBuffer;
import java.nio.FloatBuffer;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Game {
	public static final float SPEED = 0.1f;
	public Vector3 position = new Vector3(0, 0, 0);
	public Vector3 velocity = new Vector3(0, 0, 0);
	public float rotation = 0;
	
	//Audio
	IntBuffer buffer = BufferUtils.createIntBuffer(1);
	IntBuffer source = BufferUtils.createIntBuffer(1);
	
	FloatBuffer sourcePos = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] {0.0f, 0.0f, 0.0f}).rewind();
	FloatBuffer sourceVel = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] {0.0f, 0.0f, 0.0f}).rewind();
	FloatBuffer listenerPos = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] {position.x, position.y, position.z}).flip();
	FloatBuffer listenerVel = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] {velocity.x, velocity.y, velocity.z}).flip();
	FloatBuffer listenerOri = (FloatBuffer)BufferUtils.createFloatBuffer(6).put(new float[] {0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f}).rewind();
	
	public Game() {
		init();
	}
	
	public void init() {
		try {
			Display.setDisplayMode(new DisplayMode(512, 512));
			Display.create();
			AL.create();
		} catch(LWJGLException e) {
			System.out.println("Failed to create window");
			return;
		}
		
		//Graphics
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 512, 0, 512, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		//Audio
		AL10.alGenBuffers(buffer);
		
		if(AL10.alGetError() != AL10.AL_NO_ERROR) {
			System.out.println("Error AL");
			close();
			return;
		}
		
		try {
			BufferedInputStream in = new BufferedInputStream(new FileInputStream("res/civilian_mono.wav"));
			WaveData waveFile = WaveData.create(in);
			System.out.println(waveFile);
			AL10.alBufferData(buffer.get(0), waveFile.format, waveFile.data, waveFile.samplerate);
			waveFile.dispose();
		} catch(FileNotFoundException e) {
			System.out.println("Could not find .wav file");
			close();
			return;
		}
		
		AL10.alGenSources(source);
		
		if(AL10.alGetError() != AL10.AL_NO_ERROR) {
			System.out.println("Error AL2");
			close();
			return;
		}
		
		AL10.alSourcei(source.get(0), AL10.AL_BUFFER, buffer.get(0));
		AL10.alSourcef(source.get(0), AL10.AL_PITCH, 1.0f);
		AL10.alSourcef(source.get(0), AL10.AL_GAIN, 0.3f);
		AL10.alSource(source.get(0), AL10.AL_POSITION, sourcePos);
		AL10.alSource(source.get(0), AL10.AL_VELOCITY, sourceVel);
		
		int error = AL10.alGetError();
		if(error != AL10.AL_NO_ERROR) {
			System.out.println("Error AL3: " + error);
			close();
			return;
		}
		
		AL10.alListener(AL10.AL_POSITION, listenerPos);
		AL10.alListener(AL10.AL_VELOCITY, listenerVel);
		AL10.alListener(AL10.AL_ORIENTATION, listenerOri);
		
		AL10.alSourcePlay(source.get(0));

		update();
	}
	
	public void update() {
		while(!Display.isCloseRequested()) {
			Display.sync(60);
			Display.update();
			
			//Graphics
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			GL11.glColor3f(0.5f, 0.5f, 0.5f);
			
			//Input
			while(Keyboard.next()) {
				if(Keyboard.getEventKeyState()) {
					if(Keyboard.getEventKey() == Keyboard.KEY_W) {
						velocity.z = -SPEED;
					}
					if(Keyboard.getEventKey() == Keyboard.KEY_S) {
						velocity.z = SPEED;
					}
					if(Keyboard.getEventKey() == Keyboard.KEY_D) {
						velocity.x = SPEED;
					}
					if(Keyboard.getEventKey() == Keyboard.KEY_A) {
						velocity.x = -SPEED;
					}
					if(Keyboard.getEventKey() == Keyboard.KEY_R) {
						rotation = 15;
						float x = listenerOri.get(0);
						float z = listenerOri.get(2);
						float newx = (float) (x * Math.cos(rotation) - z * Math.sin(rotation));
						float newz = (float) (x * Math.sin(rotation) + z * Math.cos(rotation));
						listenerOri.put(0, newx);
						listenerOri.put(2, newz);
					}
				}
			}
			
			position.add(velocity);
			
			listenerPos.put(0, position.x);
			listenerPos.put(1, position.y);
			listenerPos.put(2, position.z);
			
			AL10.alListener(AL10.AL_POSITION, listenerPos);
			AL10.alListener(AL10.AL_ORIENTATION, listenerOri);
		}
		close();
	}
	
	public void close() {
		AL10.alDeleteSources(source);
		AL10.alDeleteBuffers(buffer);
		Display.destroy();
		AL.destroy();
	}
	
	public static void main(String[] args) {
		new Game();
	}
}
