package com.auditory.components;

import org.lwjgl.util.vector.Vector3f;

import com.auditory.Component;

public class Transform extends Component {
	//The position of the game object in global coordinates
	public Vector3f position;
	//The rotation of the game object in euler coordinates
	public Vector3f rotation;
	
	public Transform(int id, Vector3f position) {
		this(id, position, new Vector3f(0, 0, 0));
	}
	
	public Transform(int id, Vector3f position, Vector3f rotation) {
		super(id);
		this.position = position;
		this.rotation = rotation;
	}
}
