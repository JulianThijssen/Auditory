package com.auditory.systems;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import com.auditory.Entity;
import com.auditory.System;
import com.auditory.components.Mesh;
import com.auditory.components.Transform;

public class RenderSystem extends System {
	Vector3f axisX = new Vector3f(1, 0, 0);
	Vector3f axisY = new Vector3f(0, 1, 0);
	Vector3f axisZ = new Vector3f(0, 0, 1);
	
	@Override
	public void update(Entity e) {		
		Transform t = world.transforms.get(e.id);
		Mesh m = world.meshes.get(e.id);
		
		if(t == null || m == null) {
			return;
		}
		
		Matrix4f modelMatrix = world.modelMatrix;
		modelMatrix.translate(new Vector3f(t.position.x, t.position.y, t.position.z));
		modelMatrix.rotate(t.rotation.x, axisX);
		modelMatrix.rotate(t.rotation.y, axisY);
		modelMatrix.rotate(t.rotation.z, axisZ);
			
		GL30.glBindVertexArray(m.mesh);
		GL20.glEnableVertexAttribArray(0);
		
		//Draw the vertices
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, m.vertexCount);
		
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}
}
