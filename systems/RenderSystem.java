package com.auditory.systems;

import static org.lwjgl.opengl.GL11.glCallList;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;

import com.auditory.components.Transform;
import com.auditory.geom.Vector3;

public class RenderSystem {
	public void update() {
		for(Transform t: transforms) {
			Vector3 pos = t.position;
			glPushMatrix();
			glTranslatef(pos.x, pos.y, pos.z);
			glCallList(mesh);
			glPopMatrix();
		}
	}
}
