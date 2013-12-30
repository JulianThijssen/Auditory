package com.auditory;

import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_TRANSFORM_BIT;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glPopAttrib;
import static org.lwjgl.opengl.GL11.glPushAttrib;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.glu.GLU;

import com.auditory.geom.Vector3;
import com.auditory.util.Rotation;

public class Camera {
	public static final float   DEFAULT_FOV = 90;
	public static final float   DEFAULT_ASPECTRATIO = 1;
	public static final float   DEFAULT_ZNEAR = 0.3f;
	public static final float   DEFAULT_ZFAR = 100f;
	public static final boolean DEFAULT_PERSPECTIVE = true;
	public static final float MIN_PITCH = -90;
	public static final float MAX_PITCH = 90;
	public static final float DEFAULT_SENSITIVITY = 0.1f;
	
	public float   fieldOfView = DEFAULT_FOV;
	public float   aspectRatio = DEFAULT_ASPECTRATIO;
	public float   zNear       = DEFAULT_ZNEAR;
	public float   zFar        = DEFAULT_ZFAR;
	public boolean perspective = DEFAULT_PERSPECTIVE;
	private float  sensitivity = DEFAULT_SENSITIVITY;
	
	//FIXME Taking (0,0,0) as temporary camera point
	Vector3 position = new Vector3(0, 0, 0);
	Vector3 rotation = new Vector3(0, 0, 0);
	
	public Vector3 getRotation() {
		return rotation;
	}
	
	public void setPosition(Vector3 position) {
		this.position = position;
	}
	
	public void update() {
		applyPerspectiveMatrix();
		
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
		
		glPushAttrib(GL_TRANSFORM_BIT);
			glMatrixMode(GL_MODELVIEW);
			glTranslatef(-position.x, -position.y, -position.z);
			
			//FIXME
			//Vector3 axis = Rotation.eulerToAxis(rotation);
			//glRotatef(0, -axis.x, -axis.y, -axis.z);
			glRotatef(rotation.x, 1, 0, 0);
			glRotatef(rotation.y, 0, 1, 0);
			glRotatef(rotation.z, 0, 0, 1);
		glPopAttrib();
	}
	
	public void applyPerspectiveMatrix() {
        glPushAttrib(GL_TRANSFORM_BIT);
	        glMatrixMode(GL_PROJECTION);
	        glLoadIdentity();
	        GLU.gluPerspective(fieldOfView, aspectRatio, zNear, zFar);
        glPopAttrib();
    }
}
