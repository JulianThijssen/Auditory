package com.auditory;

import java.util.ArrayList;
import java.util.List;

public class Entity {
	//The ID of this object
	public String id = null;
	//A list of components added to the game object
	protected List<Component> components = new ArrayList<Component>();
	
	public void addComponent(Component c) {
		components.add(c);
	}
}
