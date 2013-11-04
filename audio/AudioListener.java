package com.auditory.audio;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL10;

import com.auditory.Component;
import com.auditory.Entity;

public class AudioListener extends Component {
	private FloatBuffer position = (FloatBuffer) BufferUtils.createFloatBuffer(3).clear();
	private FloatBuffer velocity = (FloatBuffer) BufferUtils.createFloatBuffer(3).clear();
	private FloatBuffer orientation = (FloatBuffer) BufferUtils.createFloatBuffer(6).clear();
	
	public AudioListener(Entity parent) {
		super(parent);
		setPosition(parent.position.x, parent.position.y, parent.position.z);
		orientation.put(new float[] {0, 0, -1, 0, 1, 0});
		orientation.position(0);
		
		AL10.alListener(AL10.AL_POSITION, position);
		AL10.alListener(AL10.AL_VELOCITY, velocity);
		AL10.alListener(AL10.AL_ORIENTATION, orientation);
	}
	
	public void setPosition(float x, float y, float z) {
		position.put(0, x);
		position.put(1, y);
		position.put(2, z);
		position.position(0);
		AL10.alListener(AL10.AL_POSITION, position);
	}
	
	public void setVelocity(float x, float y, float z) {
		velocity.put(0, x);
		velocity.put(1, y);
		velocity.put(2, z);
		velocity.position(0);
		AL10.alListener(AL10.AL_VELOCITY, velocity);
	}
	
	public void setAtOrientation(float x, float y, float z) {
		orientation.put(0, x);
		orientation.put(1, y);
		orientation.put(2, z);
		orientation.position(0);
		AL10.alListener(AL10.AL_ORIENTATION, orientation);
	}
	
	public void update() {
		
	}
	
	public void rotate(float radians) {
		float newx = (float) (orientation.get(0) * Math.cos(radians) - orientation.get(2) * Math.sin(radians));
		float newz = (float) (orientation.get(0) * Math.sin(radians) + orientation.get(2) * Math.cos(radians));
		setAtOrientation(newx, orientation.get(1), newz);
	}
}
