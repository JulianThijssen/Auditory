package com.auditory.util;

import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_TRANSFORM_BIT;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glPopAttrib;
import static org.lwjgl.opengl.GL11.glPushAttrib;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class Camera {
	public static final float MIN_PITCH = -90;
	public static final float MAX_PITCH = 90;
	
	public Vector3 position = new Vector3(0, 0, 0);
	public Vector3 rotation = new Vector3(0, 0, 0); //Pitch Yaw Roll
	private int     fieldOfView = 90;
	private float   aspectRatio = 1;
	private float   zNear = 0.1f;
	private float   zFar = 100f;
	
	public void processMouse(float mouseSpeed) {
		float dx = Mouse.getDX();
		float dy = -Mouse.getDY();
		
		//Pitch
		if(rotation.x + dy > MAX_PITCH) {
			rotation.x = MAX_PITCH;
		} else if(rotation.x + dy < MIN_PITCH) {
			rotation.x = MIN_PITCH;
		} else {
			rotation.x += dy;
		}
		//Yaw
		if(rotation.y + dx > 360) {
			rotation.y = rotation.y + dx - 360;
		} else if(rotation.y + dx < 0) {
			rotation.y = 360 - rotation.y + dx;
		} else {
			rotation.y += dx;
		}
	}
	
	public void processKeyboard() {
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			position.z -= 1;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			position.z += 1;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			position.x -= 1;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			position.x += 1;
		}
	}
	
	public void 
	
	public void applyPerspectiveMatrix() {
        glPushAttrib(GL_TRANSFORM_BIT);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        GLU.gluPerspective(fieldOfView, aspectRatio, zNear, zFar);
        glPopAttrib();
    }
	
	public void applyTransformMatrix() {
		GL11.glPushAttrib(GL11.GL_TRANSFORM_BIT);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glRotatef(rotation.x, 1, 0, 0);
		GL11.glRotatef(rotation.y, 0, 1, 0);
		GL11.glRotatef(rotation.z, 0, 0, 1);
		GL11.glTranslatef(-position.x, -position.y, -position.z);
		GL11.glPopAttrib();
	}
}
