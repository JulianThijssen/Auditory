package com.auditory.entities;

import com.auditory.GameObject;
import com.auditory.geom.Vector3;

public class Player extends GameObject {
	public static final float HEIGHT = 0.1f;

	public Player(float x, float y, float z) {
		super(new Vector3(x, y, z));
	}
}
