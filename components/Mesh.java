package com.auditory.components;

import static org.lwjgl.opengl.GL11.GL_COMPILE;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glEndList;
import static org.lwjgl.opengl.GL11.glGenLists;
import static org.lwjgl.opengl.GL11.glNewList;
import static org.lwjgl.opengl.GL11.glNormal3f;
import static org.lwjgl.opengl.GL11.glVertex3f;
import static org.lwjgl.opengl.GL30.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.auditory.Component;
import com.auditory.geom.Face;
import com.auditory.geom.Model;
import com.auditory.geom.Vector3;
import com.auditory.util.Log;
import com.auditory.util.OBJLoader;

public class Mesh extends Component {
	public int mesh;
	
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
		
		int list = glGenLists(1);
		glNewList(list, GL_COMPILE);
		glBegin(GL_TRIANGLES);
		for(Face face: model.faces) {
			Vector3 v1 = model.vertices.get(face.vertices[0] - 1);
			glVertex3f(v1.x, v1.y, v1.z);
			Vector3 n1 = model.normals.get(face.normals[0] - 1);
			glNormal3f(n1.x, n1.y, n1.z);
			Vector3 v2 = model.vertices.get(face.vertices[1] - 1);
			glVertex3f(v2.x, v2.y, v2.z);
			Vector3 n2 = model.normals.get(face.normals[1] - 1);
			glNormal3f(n2.x, n2.y, n2.z);
			Vector3 v3 = model.vertices.get(face.vertices[2] - 1);
			glVertex3f(v3.x, v3.y, v3.z);
			Vector3 n3 = model.normals.get(face.normals[2] - 1);
			glNormal3f(n3.x, n3.y, n3.z);
		}
		glEnd();
		glEndList();
		
		return list;
	}
}
