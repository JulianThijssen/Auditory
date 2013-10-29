package com.auditory.components;

import static org.lwjgl.opengl.GL11.glCallList;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;

import com.auditory.Component;
import com.auditory.GameObject;
import com.auditory.Renderable;
import com.auditory.geom.Vector3;

public class Mesh extends Component implements Renderable {
	private int mesh;
	
	public Mesh(GameObject parent, int mesh) {
		super(parent);
		this.mesh = mesh;
	}
	
	public void render() {
		Vector3 pos = parent.position;
		glPushMatrix();
		glTranslatef(pos.x, pos.y, pos.z);
		glCallList(mesh);
		glPopMatrix();
	}
}
