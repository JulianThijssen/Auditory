package com.auditory;

import org.lwjgl.input.Keyboard;

import com.auditory.components.Transform;
import com.auditory.components.Velocity;
import com.auditory.geom.Vector3;
import com.auditory.util.Log;
import com.auditory.util.Rotation;

public class Input {
	public static final float MAX_SPEED = 0.3f;
	public static final float ACCELERATION = 0.05f;

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
		Log.debug(world.mainCamera.getRotation().toString());
		Vector3 camRot = Rotation.eulerToAxis(world.mainCamera.getRotation());
		Log.debug(camRot.toString());
		if(forward)  {v.velocity.add(camRot.scale(ACCELERATION));}
		if(backward) {v.velocity.subtract(camRot.scale(ACCELERATION));}
		if(left)     {v.velocity.x -= ACCELERATION;}
		if(right)    {v.velocity.x += ACCELERATION;}
		if(!forward && !backward) {v.velocity.scale(0);}
		
		if(v.velocity.length() > MAX_SPEED) {
			v.velocity.normalise();
			v.velocity.scale(MAX_SPEED);
		}
		
		t.position.add(v.velocity);
	}
}
