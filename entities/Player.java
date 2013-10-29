package com.auditory.entities;

import com.auditory.Entity;
import com.auditory.geom.Vector3;

public class Player extends Entity {
	public static final float HEIGHT = 0.1f;

	public Player(float x, float y, float z) {
		super(new Vector3(x, y, z));
	}
}
