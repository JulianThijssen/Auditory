package com.auditory;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	public static final float   DEFAULT_FOV = 90;
	public static final float   DEFAULT_ASPECTRATIO = 1;
	public static final float   DEFAULT_ZNEAR = 0.3f;
	public static final float   DEFAULT_ZFAR = 100f;
	public static final boolean DEFAULT_PERSPECTIVE = true;
	public static final float   MIN_PITCH = -90;
	public static final float   MAX_PITCH = 90;
	public static final float   DEFAULT_SENSITIVITY = 0.1f;
	
	public float   fieldOfView = DEFAULT_FOV;
	public float   aspectRatio = DEFAULT_ASPECTRATIO;
	public float   zNear       = DEFAULT_ZNEAR;
	public float   zFar        = DEFAULT_ZFAR;
	public boolean perspective = DEFAULT_PERSPECTIVE;
	private float  sensitivity = DEFAULT_SENSITIVITY;
	
	Vector3f axisX = new Vector3f(1, 0, 0);
	Vector3f axisY = new Vector3f(0, 1, 0);
	Vector3f axisZ = new Vector3f(0, 0, 1);
	
	private Matrix4f projectionMatrix = new Matrix4f();
	private Matrix4f viewMatrix = new Matrix4f();
	
	//FIXME Taking (0,0,0) as temporary camera point
	Vector3f position = new Vector3f(0, 0, 0);
	Vector3f rotation = new Vector3f(0, 0, 0);
	
	public Camera() {
		projectionMatrix.m00 = (float) (1 / Math.tan(Math.toRadians(fieldOfView / 2f)));
		projectionMatrix.m11 = (float) (1 / Math.tan(Math.toRadians(fieldOfView / 2f)));
		projectionMatrix.m22 = -((zFar + zNear) / (zFar - zNear));
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * zNear * zFar) / (zFar - zNear));
		projectionMatrix.m33 = 0;
	}
	
	public Vector3f getRotation() {
		return rotation;
	}
	
	public void setPosition(Vector3f position) {
		this.position = position;
	}
	
	public void update() {
		viewMatrix.setIdentity();
		
		float dx = Mouse.getDX() * sensitivity;
		float dy = -Mouse.getDY() * sensitivity;
		
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

		/*viewMatrix.rotate(rotation.x, axisX);
		viewMatrix.rotate(rotation.y, axisY);
		viewMatrix.rotate(rotation.z, axisZ);
		viewMatrix.translate(new Vector3f(-position.x, -position.y, -position.z));*/
	}
	
	public Matrix4f getViewMatrix() {
		return viewMatrix;
	}
	
	public Matrix4f getPerspectiveMatrix() {
		return projectionMatrix;
    }
}
