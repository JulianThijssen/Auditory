package com.auditory.systems;

import static org.lwjgl.opengl.GL11.glCallList;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;

import com.auditory.Camera;
import com.auditory.Entity;
import com.auditory.System;
import com.auditory.components.Mesh;
import com.auditory.components.Transform;

public class RenderSystem extends System {
	private Camera camera = new Camera();

	@Override
	public void update(Entity e) {
		camera.applyPerspectiveMatrix();
		camera.applyTransformMatrix();
		
		Transform t = world.transforms.get(e.id);
		Mesh m = world.meshes.get(e.id);
		
		if(t == null || m == null) {
			return;
		}
		
		glPushMatrix();
		glTranslatef(t.position.x, t.position.y, t.position.z);
		glCallList(m.mesh);
		glPopMatrix();
	}
}
