package com.auditory;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.util.glu.GLU;

import com.auditory.geom.Vector3;

public class Camera extends Entity {
	public static final float   DEFAULT_FOV = 90;
	public static final float   DEFAULT_ASPECTRATIO = 1;
	public static final float   DEFAULT_ZNEAR = 0.3f;
	public static final float   DEFAULT_ZFAR = 100f;
	public static final boolean DEFAULT_PERSPECTIVE = true;
	

	private float   fieldOfView = DEFAULT_FOV;
	private float   aspectRatio = DEFAULT_ASPECTRATIO;
	private float   zNear       = DEFAULT_ZNEAR;
	private float   zFar        = DEFAULT_ZFAR;
	private boolean perspective = DEFAULT_PERSPECTIVE;
	
	public Camera(float x, float y, float z) {
		super(new Vector3(x, y, z));
		enablePerspective(DEFAULT_PERSPECTIVE);
	}
	
	public void enablePerspective(boolean b) {
		perspective = b;
		if(perspective) {
			applyPerspectiveMatrix();
		} else {
			//TODO applyOrthographicMatrix();
		}
	}
	
	private void applyPerspectiveMatrix() {
        glPushAttrib(GL_TRANSFORM_BIT);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        GLU.gluPerspective(fieldOfView, aspectRatio, zNear, zFar);
        glPopAttrib();
    }
	
	public void applyTransformMatrix() {
		glPushAttrib(GL_TRANSFORM_BIT);
		glMatrixMode(GL_MODELVIEW);
		glRotatef(parent.rotation.x, 1, 0, 0);
		glRotatef(parent.rotation.y, 0, 1, 0);
		glRotatef(parent.rotation.z, 0, 0, 1);
		glTranslatef(-parent.position.x, -parent.position.y, -parent.position.z);
		glPopAttrib();
	}
}
