package com.auditory.util;

import com.auditory.geom.Vector3;

public class Rotation {
	public static Vector3 eulerToAxis(Vector3 euler) {
		float pitch = (float)(Math.toRadians(euler.x));
		float yaw = (float)(Math.toRadians(euler.y));
		
		Vector3 axis = new Vector3(0, 0, 0);
		axis.x = (float) (Math.sin(yaw) * Math.cos(pitch));
		axis.y = (float) Math.sin(pitch);
		axis.z = (float) (Math.cos(yaw) * Math.cos(pitch));
		
		return axis;
	}
}
