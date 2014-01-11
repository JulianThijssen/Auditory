package com.auditory.util;

import org.lwjgl.util.vector.Vector3f;


/*
 * The following are the CCW rotation matrices for pitch and yaw where A is the angle in degrees.
 * 
 * Pitch rotation matrix (P):
 * [1		0		0  ]
 * [0	   cosA	   sinA]
 * [0     -sinA    cosA]
 * 
 * Yaw rotation matrix (Y):
 * [cosA    0     -sinA]
 * [0       1       0  ]
 * [sinA    0      cosA]
 * 
 * Combined matrix (PY):
 * [  cosY		0		-sinY  ]
 * [sinPsinY   cosP    sinPcosY]
 * [cosPsinY  -sinP    cosPcosY]
 * 
 * Combined matrix (YP):
 * [  cosY  sinYsinP  -sinYcosP]
 * [   0       cosP      sinP  ]
 * [  sinY -sinPcosY   cosYcosP]
 */
public class Rotation {
	public static Vector3f eulerToAxis(Vector3f euler) {
		float pitch = (float)(Math.toRadians(euler.x));
		float yaw = (float)(Math.toRadians(euler.y));
		
		Vector3f v = new Vector3f(0, 0, -1);
		
//		Vector3f pitchv = new Vector3f(v.x, (float) ((Math.cos(pitch) * v.y) + (Math.sin(pitch) * v.z)), (float) ((-Math.sin(pitch) * v.y) + (Math.cos(pitch) * v.z)));
//		Vector3f yawv   = new Vector3f((float) ((Math.cos(yaw) * v.x) + (-Math.sin(yaw) * v.z)), v.y, (float) ((Math.sin(yaw) * v.x) + (Math.cos(yaw) * v.z)));
//		Vector3f c1     = new Vector3f((float) ((Math.cos(yaw) * v.x) + (-Math.sin(yaw) * v.z)),
//									 (float) ((Math.sin(pitch) * Math.sin(yaw) * v.x) + (Math.cos(pitch) * v.y) + (Math.sin(pitch) * Math.cos(yaw) * v.z)),
//									 (float) ((Math.cos(pitch) * Math.sin(yaw) * v.x) + (-Math.sin(pitch) * v.y) + (Math.cos(pitch) * Math.cos(yaw) * v.z)));
		Vector3f c2     = new Vector3f((float) ((Math.cos(yaw) * v.x) + (Math.sin(yaw) * Math.sin(pitch) * v.y) + (-Math.sin(yaw) * Math.cos(pitch) * v.z)),
									 (float) ((Math.cos(pitch) * v.y) + (Math.sin(pitch) * v.z)),
									 (float) ((Math.sin(yaw) * v.x) + (-Math.sin(pitch) * Math.cos(yaw) * v.y) + (Math.cos(yaw) * Math.cos(pitch) * v.z)));
		return c2;
	}
}
