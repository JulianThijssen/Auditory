package com.auditory.entities;

import org.lwjgl.input.Keyboard;

import com.auditory.Entity;

public class Player extends Entity {
	public static final float HEIGHT = 0.1f;
	public static final float SPEED = 0.1f;
	
	private boolean forward, backward, left, right;
	
	public Player(float x, float y, float z) {
		super(x, y, z);
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
		
		if(forward)  {velocity.z = -SPEED;}
		if(backward) {velocity.z = SPEED;}
		if(left)     {velocity.x = -SPEED;}
		if(right)    {velocity.x = SPEED;}
		if(!forward && !backward) {velocity.z = 0;}
		if(!left && !right) {velocity.x = 0;}
		
		if(!velocity.isZero()) {
			if(!sources.get(0).isPlaying()) {
				sources.get(0).setPitch((float) (Math.random() + 0.5f));
				sources.get(0).playAudio(false);
			}
		}
		
		position.add(velocity);
		
		sources.get(0).setPosition(position.x, position.y - HEIGHT, position.z);
		listener.setPosition(position.x, position.y, position.z);
	}
}
