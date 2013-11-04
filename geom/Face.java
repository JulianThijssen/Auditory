package com.auditory.geom;

public class Face {
	public int[] vertices = new int[3];
	public int[] normals = new int[3];
	
	public Face(int[] vertices, int[] normals) {
		this.vertices = vertices;
		this.normals = normals;
	}
}
