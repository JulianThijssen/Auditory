package com.auditory.systems;

import static org.lwjgl.opengl.GL11.*;

import com.auditory.Entity;
import com.auditory.System;
import com.auditory.components.Mesh;
import com.auditory.components.Transform;
import com.auditory.geom.Vector3;
import com.auditory.util.Rotation;

public class RenderSystem extends System {
	
	@Override
	public void update(Entity e) {		
		Transform t = world.transforms.get(e.id);
		Mesh m = world.meshes.get(e.id);
		
		if(t == null || m == null) {
			return;
		}
		
		Vector3 axis = Rotation.eulerToAxis(t.rotation);
		
		glPushMatrix();
			glTranslatef(t.position.x, t.position.y, t.position.z);
			glRotatef(t.rotation.x, 1, 0, 0);
			glRotatef(t.rotation.y, 0, 1, 0);
			glRotatef(t.rotation.z, 0, 0, 1);
			glCallList(m.mesh);
		glPopMatrix();
	}
}
