package com.auditory.systems;

import org.lwjgl.input.Keyboard;
import com.auditory.System;
import com.auditory.Entity;
import com.auditory.components.Transform;
import com.auditory.components.Velocity;

public class PlayerInputSystem extends System {
	public static final float MAX_SPEED = 0.3f;
	public static final float ACCELERATION = 0.05f;

	
	//Movement
	private boolean forward, backward, left, right;

	@Override
	public void update(Entity e) {
		Transform t = world.transforms.get(e.id);
		Velocity v = world.velocities.get(e.id);
		
		if(t == null || v == null) {
			return;
		}
		
		forward = Keyboard.isKeyDown(Keyboard.KEY_W);
		backward = Keyboard.isKeyDown(Keyboard.KEY_S);
		left = Keyboard.isKeyDown(Keyboard.KEY_A);
		right = Keyboard.isKeyDown(Keyboard.KEY_D);

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
	}
}
