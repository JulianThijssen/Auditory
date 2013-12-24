package com.auditory;

import java.util.ArrayList;
import java.util.List;

public class Entity {
	//The ID of this object
	public int id;
	//The world this entity is in
	private World world;
	//A list of components added to the game object
	private List<Component> components = new ArrayList<Component>();
	
	public Entity(World world, int id) {
		this.world = world;
		this.id = id;
	}
}
