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
	
	public boolean isZero() {
		if(x == 0 && y == 0 && z == 0) {
			return true;
		}
		return false;
	}
	
	public float length() {
		return (float) Math.sqrt(x * x + y * y + z * z);
	}
	
	public void normalise() {
		float length = length();
		x = x / length;
		y = y / length;
		z = z / length;
	}
	
	public void scale(float factor) {
		x *= factor;
		y *= factor;
		z *= factor;
	}
}
