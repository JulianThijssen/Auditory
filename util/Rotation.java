package com.auditory.util;

import com.auditory.geom.Vector3;

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
	public static Vector3 eulerToAxis(Vector3 euler) {
		float pitch = (float)(Math.toRadians(euler.x));
		float yaw = (float)(Math.toRadians(euler.y));
		
		Vector3 v = new Vector3(0, 0, -1);
		
//		Vector3 pitchv = new Vector3(v.x, (float) ((Math.cos(pitch) * v.y) + (Math.sin(pitch) * v.z)), (float) ((-Math.sin(pitch) * v.y) + (Math.cos(pitch) * v.z)));
//		Vector3 yawv   = new Vector3((float) ((Math.cos(yaw) * v.x) + (-Math.sin(yaw) * v.z)), v.y, (float) ((Math.sin(yaw) * v.x) + (Math.cos(yaw) * v.z)));
//		Vector3 c1     = new Vector3((float) ((Math.cos(yaw) * v.x) + (-Math.sin(yaw) * v.z)),
//									 (float) ((Math.sin(pitch) * Math.sin(yaw) * v.x) + (Math.cos(pitch) * v.y) + (Math.sin(pitch) * Math.cos(yaw) * v.z)),
//									 (float) ((Math.cos(pitch) * Math.sin(yaw) * v.x) + (-Math.sin(pitch) * v.y) + (Math.cos(pitch) * Math.cos(yaw) * v.z)));
		Vector3 c2     = new Vector3((float) ((Math.cos(yaw) * v.x) + (Math.sin(yaw) * Math.sin(pitch) * v.y) + (-Math.sin(yaw) * Math.cos(pitch) * v.z)),
									 (float) ((Math.cos(pitch) * v.y) + (Math.sin(pitch) * v.z)),
									 (float) ((Math.sin(yaw) * v.x) + (-Math.sin(pitch) * Math.cos(yaw) * v.y) + (Math.cos(yaw) * Math.cos(pitch) * v.z)));
		return c2;
	}
}
