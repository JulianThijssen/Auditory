package com.auditory.geom;

import java.util.ArrayList;
import java.util.List;

import com.auditory.util.Vector3;

public class Model {
	public List<Vector3> vertices = new ArrayList<Vector3>();
	public List<Vector3> normals = new ArrayList<Vector3>();
	public List<Face> faces = new ArrayList<Face>();
	
	public void printModel() {
		for(int i = 0; i < vertices.size(); i++) {
			System.out.println("Vertex: " + vertices.get(i).toString());
		}
		for(int i = 0; i < normals.size(); i++) {
			System.out.println("Normal: " + normals.get(i));
		}
	}
}
