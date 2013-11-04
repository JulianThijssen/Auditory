package com.auditory.systems;

import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_TRANSFORM_BIT;
import static org.lwjgl.opengl.GL11.glCallList;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glPopAttrib;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushAttrib;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.glu.GLU;

import com.auditory.System;
import com.auditory.Component;
import com.auditory.components.Camera;
import com.auditory.components.Mesh;
import com.auditory.components.Transform;
import com.auditory.geom.Vector3;

public class RenderSystem extends System {
	private List<Transform> transforms = new ArrayList<Transform>();
	private List<Mesh>      meshes     = new ArrayList<Mesh>();
	private List<Camera>    cameras    = new ArrayList<Camera>();
	
	private void applyPerspectiveMatrix(Camera camera) {
        glPushAttrib(GL_TRANSFORM_BIT);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        GLU.gluPerspective(camera.fieldOfView, camera.aspectRatio, camera.zNear, camera.zFar);
        glPopAttrib();
    }
	
	public void applyTransformMatrix(Transform t) {
		glPushAttrib(GL_TRANSFORM_BIT);
		glMatrixMode(GL_MODELVIEW);
		glRotatef(t.rotation.x, 1, 0, 0);
		glRotatef(t.rotation.y, 0, 1, 0);
		glRotatef(t.rotation.z, 0, 0, 1);
		glTranslatef(-t.position.x, -t.position.y, -t.position.z);
		glPopAttrib();
	}
	
	public void update() {
		for(Camera c: cameras) {
			int id = c.id;
			Vector3 pos = t.position;
			glPushMatrix();
			glTranslatef(pos.x, pos.y, pos.z);
			glCallList(mesh);
			glPopMatrix();
		}
	}
}
