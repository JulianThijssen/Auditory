package com.auditory.components;

import com.auditory.Component;
import com.auditory.geom.Vector3;

public class Velocity extends Component {
	//The velocity of the game object in global coordinates
	public Vector3 velocity;
	
	public Velocity(int id, Vector3 velocity) {
		super(id);
		this.velocity = velocity;
	}
}
