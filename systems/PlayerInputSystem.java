package com.auditory.systems;

import org.lwjgl.input.Keyboard;

import com.auditory.System;
import com.auditory.Entity;
import com.auditory.components.Mesh;
import com.auditory.components.Transform;
import com.auditory.components.Velocity;
import com.auditory.geom.Vector3;
import com.auditory.util.Log;

public class PlayerInputSystem extends System {
	public static final float MAX_SPEED = 0.3f;
	public static final float ACCELERATION = 0.05f;
	
	//Movement
	private boolean forward, backward, left, right;

	@Override
	public void update(Entity e) {
		forward = Keyboard.isKeyDown(Keyboard.KEY_W);
		backward = Keyboard.isKeyDown(Keyboard.KEY_S);
		left = Keyboard.isKeyDown(Keyboard.KEY_A);
		right = Keyboard.isKeyDown(Keyboard.KEY_D);

		Transform t = world.transforms.get(e.id);
		Velocity v = world.velocities.get(e.id);
		
		if(t == null || v == null) {
			return;
		}

		if(forward)  {v.velocity.z -= ACCELERATION;}
		if(backward) {v.velocity.z += ACCELERATION;}
		if(left)     {v.velocity.x -= ACCELERATION;}
		if(right)    {v.velocity.x += ACCELERATION;}
		if(!forward && !backward) {v.velocity.z = 0;}
		if(!left && !right) {v.velocity.x = 0;}
		
		if(v.velocity.length() > MAX_SPEED) {
			v.velocity.normalise();
			v.velocity.scale(MAX_SPEED);
		}
		
		t.position.add(v.velocity);
		
		float pitch = (float)(Math.toRadians(t.rotation.x));
		float yaw = (float)(Math.toRadians(t.rotation.y));
		float x, y, z;
		y = (float)((Math.cos(pitch) * 0) + (Math.sin(pitch) * -1));
		z = (float)((-Math.sin(pitch) * 0) + (Math.cos(pitch) * -1));
		Vector3 pitchdir = new Vector3(0, y, z);
		x = (float)((Math.cos(yaw) * 0) - (Math.sin(yaw) * -1));
		z = (float)((Math.sin(yaw) * 0) + (Math.cos(yaw) * -1));
		Vector3 yawdir = new Vector3(x, 0, z);
		Vector3 direction = Vector3.add(pitchdir, yawdir);
		direction.normalise();
	}
}
