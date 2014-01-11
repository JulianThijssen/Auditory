package com.auditory.components;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector3f;

import com.auditory.Component;
import com.auditory.geom.Face;
import com.auditory.geom.Model;
import com.auditory.util.Log;
import com.auditory.util.OBJLoader;

public class Mesh extends Component {
	public int mesh;
	public int vertexCount;
	
	public Mesh(int id, String filepath) {
		super(id);
		mesh = loadModel(filepath);
	}
	
	private int loadModel(String filepath) {
		Model model = null;
		try {
			model = OBJLoader.load(filepath);
		} catch(FileNotFoundException fe) {
			Log.debug("The file was not found: " + filepath);
			return -1;
		} catch(IOException ie) {
			Log.debug("There was a problem while reading from file");
			return -1;
		}
		
		vertexCount = model.faces.size() * 3;
		FloatBuffer vertices = BufferUtils.createFloatBuffer(vertexCount * 3);		
		
		for(Face face: model.faces) {
			Vector3f v1 = model.vertices.get(face.vertices[0] - 1);
			vertices.put(v1.x);
			vertices.put(v1.y);
			vertices.put(v1.z);
			
			Vector3f v2 = model.vertices.get(face.vertices[1] - 1);
			vertices.put(v2.x);
			vertices.put(v2.y);
			vertices.put(v2.z);
			
			Vector3f v3 = model.vertices.get(face.vertices[2] - 1);
			vertices.put(v3.x);
			vertices.put(v3.y);
			vertices.put(v3.z);
			/*
			Vector3f n1 = model.normals.get(face.normals[0] - 1);
			glNormal3f(n1.x, n1.y, n1.z);
			
			Vector3f n2 = model.normals.get(face.normals[1] - 1);
			glNormal3f(n2.x, n2.y, n2.z);
			
			Vector3f n3 = model.normals.get(face.normals[2] - 1);
			glNormal3f(n3.x, n3.y, n3.z);*/
		}
		
		vertices.flip();
		
		int vao = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vao);
		
		int vbo = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertices, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL30.glBindVertexArray(0);
		
		return vao;
	}
}
