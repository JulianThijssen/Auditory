package com.auditory.systems;

import org.lwjgl.input.Keyboard;

import com.auditory.Behaviour;
import com.auditory.Entity;
import com.auditory.geom.Vector3;

public class PlayerMovement extends Behaviour {
	public static final float MAX_SPEED = 0.3f;
	public static final float ACCELERATION = 0.05f;
	
	//Movement
	private boolean forward, backward, left, right;
	//The velocity of the entity
	public Vector3 velocity = new Vector3(0, 0, 0);
	
	public PlayerMovement(Entity parent) {
		super(parent);
	}
	
	public void update() {
		processKeyboard();
		move();
	}
	
	public void processKeyboard() {
		forward = Keyboard.isKeyDown(Keyboard.KEY_W);
		backward = Keyboard.isKeyDown(Keyboard.KEY_S);
		left = Keyboard.isKeyDown(Keyboard.KEY_A);
		right = Keyboard.isKeyDown(Keyboard.KEY_D);

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
		
		parent.position.add(velocity);
	}
	
	public void move() {
		float pitch = (float)(Math.toRadians(parent.rotation.x));
		float yaw = (float)(Math.toRadians(parent.rotation.y));
		float x, y, z;
		y = (float)((Math.cos(pitch) * 0) + (Math.sin(pitch) * -1));
		z = (float)((-Math.sin(pitch) * 0) + (Math.cos(pitch) * -1));
		Vector3 pitchdir = new Vector3(0, y, z);
		x = (float)((Math.cos(yaw) * 0) - (Math.sin(yaw) * -1));
		z = (float)((Math.sin(yaw) * 0) + (Math.cos(yaw) * -1));
		Vector3 yawdir = new Vector3(x, 0, z);
		Vector3 direction = Vector3.add(pitchdir, yawdir);
		direction.normalise();
		//System.out.println(direction);
	}
}
