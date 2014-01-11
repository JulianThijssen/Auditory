package com.auditory.components;

import org.lwjgl.util.vector.Vector3f;

import com.auditory.Component;


public class Velocity extends Component {
	//The velocity of the game object in global coordinates
	public Vector3f velocity;
	
	public Velocity(int id, Vector3f velocity) {
		super(id);
		this.velocity = velocity;
	}
}
