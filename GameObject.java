package com.auditory;

import static org.lwjgl.opengl.GL11.*;

import com.auditory.geom.Vector3;

public abstract class GameObject {
	//A reference to the model buffer of the game object
	protected int model;
	//The position of the game object in global coordinates
	public Vector3 position;
	//The rotation of the game object in euler coordinates
	public Vector3 rotation;
	
	public GameObject(Vector3 position) {
		this.position = position;
	}
	
	public void setModel(int model) {
		this.model = model;
	}
	
	public void render() {
		glPushMatrix();
		glTranslatef(position.x, position.y, position.z);
		glCallList(model);
		glPopMatrix();
	}
}
