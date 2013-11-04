package com.auditory.systems;

import org.lwjgl.input.Mouse;

import com.auditory.Behaviour;
import com.auditory.Entity;

public class MouseLook extends Behaviour {
	public static final float MIN_PITCH = -90;
	public static final float MAX_PITCH = 90;
	public static final float DEFAULT_SENSITIVITY = 0.1f;
	
	private float sensitivity = DEFAULT_SENSITIVITY;
	
	public MouseLook(Entity parent) {
		super(parent);
	}

	public void processMouse() {
		float dx = Mouse.getDX() * sensitivity;
		float dy = -Mouse.getDY() * sensitivity;
		
		//Pitch
		if(parent.rotation.x + dy > MAX_PITCH) {
			parent.rotation.x = MAX_PITCH;
		} else if(parent.rotation.x + dy < MIN_PITCH) {
			parent.rotation.x = MIN_PITCH;
		} else {
			parent.rotation.x += dy;
		}
		//Yaw
		if(parent.rotation.y + dx > 360) {
			parent.rotation.y = parent.rotation.y + dx - 360;
		} else if(parent.rotation.y + dx < 0) {
			parent.rotation.y = 360 - parent.rotation.y + dx;
		} else {
			parent.rotation.y += dx;
		}
	}

	@Override
	public void update() {
		processMouse();
	}
}
