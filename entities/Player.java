package com.auditory.entities;

import org.lwjgl.input.Keyboard;

import com.auditory.Entity;
import com.auditory.geom.Vector3;

public class Player extends Entity {
	public static final float HEIGHT = 0.1f;
	public static final float MAX_SPEED = 0.3f;
	public static final float ACCELERATION = 0.05f;
	
	private boolean forward, backward, left, right;
	
	public Player(float x, float y, float z) {
		super(new Vector3(x, y, z));
	}
	
	public void update() {
		while(Keyboard.next()) {
			if(Keyboard.getEventKeyState()) {
				switch(Keyboard.getEventKey()) {
					case Keyboard.KEY_W: forward = true; break;
					case Keyboard.KEY_S: backward = true; break;
					case Keyboard.KEY_A: left = true; break;
					case Keyboard.KEY_D: right = true; break;
				}
				if(Keyboard.getEventKey() == Keyboard.KEY_R) {
					listener.rotate((float) Math.toRadians(15));
				}
			}
			else {
				switch(Keyboard.getEventKey()) {
					case Keyboard.KEY_W: forward = false; break;
					case Keyboard.KEY_S: backward = false; break;
					case Keyboard.KEY_A: left = false; break;
					case Keyboard.KEY_D: right = false; break;
				}
			}
		}
		
		if(forward)  {velocity.z -= ACCELERATION;}
		if(backward) {velocity.z += ACCELERATION;}
		if(left)     {velocity.x -= ACCELERATION;}
		if(right)    {velocity.x += ACCELERATION;}
		if(!forward && !backward) {velocity.z = 0;}
		if(!left && !right) {velocity.x = 0;}
		
		if(velocity.length() > MAX_SPEED) {
			velocity.normalise();
			velocity.scale(MAX_SPEED);
		}
		
		if(!velocity.isZero()) {
			if(!sources.get(0).isPlaying()) {
				sources.get(0).setPitch(velocity.length() + 0.8f);
				sources.get(0).setGain(velocity.length() + 0.5f);
				sources.get(0).playAudio(false);
			}
		}
		
		position.add(velocity);
		
		sources.get(0).setPosition(position.x, position.y - HEIGHT, position.z);
		listener.setPosition(position.x, position.y, position.z);
	}
}
