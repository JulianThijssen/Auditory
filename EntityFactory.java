package com.auditory;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector3f;

import com.auditory.components.MainPlayer;
import com.auditory.components.Mesh;
import com.auditory.components.Transform;
import com.auditory.components.Velocity;


public class EntityFactory {
	public static Entity createPlayer(World world, float x, float y, float z) {
		Entity e = world.createEntity();
		world.addComponent(new Transform(e.id, new Vector3f(x, y, z)));
		world.addComponent(new Velocity(e.id, new Vector3f(0, 0, 0)));
		world.addComponent(new Mesh(e.id, "res/armadillo.obj"));
		world.addComponent(new MainPlayer(e.id));
		return e;
	}
	
	public static Entity createCube(World world, float x, float y, float z) {
		Entity e = world.createEntity();
		world.addComponent(new Transform(e.id, new Vector3f(x, y, z)));
		world.addComponent(new Velocity(e.id, new Vector3f(0, 0, 0)));
		world.addComponent(new Mesh(e.id, "res/Cube.obj"));
		return e;
	}
	
	public static Entity createPlane(World world, float x, float y, float z, float w, float h) {
		Entity e = world.createEntity();
		world.addComponent(new Transform(e.id, new Vector3f(x, y, z)));
		FloatBuffer vertices = BufferUtils.createFloatBuffer(6 * 3);
		vertices.clear();
		w = w/2;
		h = h/2;
		float[] v = {
			-w, 0, -h,
			-w, 0, h,
			w, 0, -h,
			w, 0, h,
			w, 0, -h,
			-w, 0, h
		};
		
		vertices.put(v);
		vertices.flip();
		int vao = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vao);
		
		int vbo = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertices, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL30.glBindVertexArray(0);
		Mesh mesh = new Mesh(e.id);
		mesh.mesh = vao;
		mesh.vertexCount = 6;
		world.addComponent(mesh);
		return e;
	}
}
