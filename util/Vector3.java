package com.auditory.util;

public class Vector3 {
	public float x, y ,z;
	
	public Vector3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void add(Vector3 v) {
		x += v.x;
		y += v.y;
		z += v.z;
	}
}
