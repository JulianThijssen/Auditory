package com.auditory.gameobjects;

import com.auditory.GameObject;
import com.auditory.geom.Vector3;

public class Block extends GameObject {
	public Block(float x, float y, float z) {
		super(new Vector3(x, y, z));
	}
}
