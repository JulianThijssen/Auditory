package com.auditory.systems;

import static org.lwjgl.opengl.GL11.*;

import com.auditory.Camera;
import com.auditory.Entity;
import com.auditory.System;
import com.auditory.components.Mesh;
import com.auditory.components.Transform;
import com.auditory.geom.Vector3;

public class RenderSystem extends System {
	private Camera camera = new Camera();

	@Override
	public void update(Entity e) {
		camera.applyPerspectiveMatrix();
		camera.update();
		
		Transform t = world.transforms.get(e.id);
		Mesh m = world.meshes.get(e.id);
		
		if(t == null || m == null) {
			return;
		}
		
		Vector3 axis = eulerToAxis(t.rotation);
		
		glPushMatrix();
			glTranslatef(t.position.x, t.position.y, t.position.z);
			glRotatef(0, axis.x, axis.y, axis.z);
	
			glCallList(m.mesh);
		glPopMatrix();
	}
	
	public Vector3 eulerToAxis(Vector3 euler) {
		float pitch = (float)(Math.toRadians(euler.x));
		float yaw = (float)(Math.toRadians(euler.y));
		
		Vector3 axis = new Vector3(0, 0, 0);
		axis.x = (float) (Math.sin(yaw) * Math.cos(pitch));
		axis.y = (float) Math.sin(pitch);
		axis.z = (float) (Math.cos(yaw) * Math.cos(pitch));
		
		return axis;
	}
}
