package com.auditory.components;

import com.auditory.Component;
import com.auditory.geom.Vector3;

public class Transform extends Component {
	//The position of the game object in global coordinates
	public Vector3 position;
	//The rotation of the game object in euler coordinates
	public Vector3 rotation;
	
	public Transform(Vector3 position) {
		this(position, new Vector3(0, 0, 0));
	}
	
	public Transform(Vector3 position, Vector3 rotation) {
		this.position = position;
		this.rotation = rotation;
	}
}
