package com.auditory.entities;

import org.lwjgl.input.Keyboard;

import com.auditory.Entity;
import com.auditory.util.Vector3;

public class Player extends Entity {
	public static final float SPEED = 0.1f;
	public Vector3 position;
	public Vector3 velocity;
	public float rotation;
	
	private boolean forward, backward, left, right;
	
	public Player(float x, float y, float z) {
		position = new Vector3(x, y, z);
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
		
		position.add(velocity);
	}
}
