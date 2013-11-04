package com.auditory.managers;

import java.util.ArrayList;
import java.util.List;

import com.auditory.Entity;

public class EntityManager {
	public List<Entity> entities = new ArrayList<Entity>();
	
	public void addEntity(Entity e) {
		entities.add(e);
	}
}
