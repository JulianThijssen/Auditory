package com.auditory.geom;

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
	
	public static Vector3 add(Vector3 v1, Vector3 v2) {
		Vector3 v = new Vector3(0, 0, 0);
		v.x = v1.x + v2.x;
		v.y = v1.y + v2.y;
		v.z = v1.z + v2.z;
		return v;
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
	
	@Override
	public String toString() {
		return String.format("(%s, %s, %s)", x, y, z);
	}
}