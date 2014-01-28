package com.auditory;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import com.auditory.components.Transform;
import com.auditory.components.Velocity;
import com.auditory.util.Rotation;

public class Input {
	public static final float MAX_SPEED = 0.3f;
	public static final float ACCELERATION = 0.005f;

	private World world;
	
	//Movement
	private boolean forward, backward, left, right;

	public Input(World world) {
		this.world = world;
	}
	
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
		
		Vector3f cameraDir = Rotation.eulerToAxis(world.mainCamera.getRotation());
		Vector3f direction = (Vector3f) cameraDir.scale(ACCELERATION);
		
		if(forward)  {Vector3f.add(v.velocity, direction, v.velocity);}
		if(backward) {Vector3f.sub(v.velocity, direction, v.velocity);}
		if(left)     {v.velocity.x -= ACCELERATION;}
		if(right)    {v.velocity.x += ACCELERATION;}
		if(!forward && !backward) {v.velocity.scale(0);}
		
		if(v.velocity.length() > MAX_SPEED) {
			v.velocity.normalise();
			v.velocity.scale(MAX_SPEED);
		}
		
		Vector3f.add(t.position, v.velocity, t.position);
	}
	
	public void update(Camera c) {
		forward = Keyboard.isKeyDown(Keyboard.KEY_W);
		backward = Keyboard.isKeyDown(Keyboard.KEY_S);
		left = Keyboard.isKeyDown(Keyboard.KEY_A);
		right = Keyboard.isKeyDown(Keyboard.KEY_D);
		
		Vector3f cameraDir = Rotation.eulerToAxis(world.mainCamera.getRotation());
		Vector3f direction = (Vector3f) cameraDir.scale(ACCELERATION);
		
		if(forward)  {Vector3f.add(c.velocity, direction, c.velocity);}
		if(backward) {Vector3f.sub(c.velocity, direction, c.velocity);}
		if(left)     {c.velocity.x -= ACCELERATION;}
		if(right)    {c.velocity.x += ACCELERATION;}
		if(!forward && !backward) {c.velocity.scale(0);}
		
		if(c.velocity.length() > MAX_SPEED) {
			c.velocity.normalise();
			c.velocity.scale(MAX_SPEED);
		}
		
		Vector3f.add(c.position, c.velocity, c.position);
	}
}
