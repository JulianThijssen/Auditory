package com.auditory.systems;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import com.auditory.Entity;
import com.auditory.System;
import com.auditory.components.Mesh;
import com.auditory.components.Transform;
import com.auditory.util.Log;

public class RenderSystem extends System {
	Vector3f axisX = new Vector3f(1, 0, 0);
	Vector3f axisY = new Vector3f(0, 1, 0);
	Vector3f axisZ = new Vector3f(0, 0, 1);
	
	Vector3f light = new Vector3f(0, 10, 0);
	
	@Override
	public void update(Entity e) {
		Transform t = world.transforms.get(e.id);
		Mesh m = world.meshes.get(e.id);
		
		if(t == null || m == null) {
			return;
		}
		
		Matrix4f projectionMatrix = world.projectionMatrix;
		Matrix4f viewMatrix = world.viewMatrix;
		Matrix4f modelMatrix = world.modelMatrix;
		
		modelMatrix.setIdentity();
		
		//FIXME rotation should be in radians
		modelMatrix.translate(new Vector3f(t.position.x, t.position.y, t.position.z));
		modelMatrix.rotate(t.rotation.x, axisX);
		modelMatrix.rotate(t.rotation.y, axisY);
		modelMatrix.rotate(t.rotation.z, axisZ);
		
		FloatBuffer projBuffer = BufferUtils.createFloatBuffer(16);
		projectionMatrix.store(projBuffer);
		projBuffer.flip();
		
		FloatBuffer viewBuffer = BufferUtils.createFloatBuffer(16);
		viewMatrix.store(viewBuffer);
		viewBuffer.flip();
		
		FloatBuffer modelBuffer = BufferUtils.createFloatBuffer(16);
		modelMatrix.store(modelBuffer);
		modelBuffer.flip();
		
		FloatBuffer lightBuffer = BufferUtils.createFloatBuffer(4);
		light.store(lightBuffer);
		lightBuffer.put(1.0f);
		lightBuffer.flip();
		
		GL20.glUniformMatrix4(world.projLoc, false, projBuffer);
		GL20.glUniformMatrix4(world.viewLoc, false, viewBuffer);
		GL20.glUniformMatrix4(world.modelLoc, false, modelBuffer);
		GL20.glUniform4(world.lightLoc, lightBuffer);
		GL30.glBindVertexArray(m.mesh);
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		
		//Draw the vertices
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, m.vertexCount);
		
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);
	}
}
