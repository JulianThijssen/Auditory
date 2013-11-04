package com.auditory;

import java.util.ArrayList;
import java.util.List;

import com.auditory.managers.EntityManager;
import com.auditory.util.IdentifierPool;

public class World {
	public EntityManager entityManager = new EntityManager();
	public IdentifierPool idpool = new IdentifierPool();
	public List<Entity> entities = new ArrayList<Entity>();
	
	public List<System> systems = new ArrayList<System>();
	
	public Entity createEntity() {
		Entity e = new Entity(this, idpool.getId());
		return e;
	}
	
	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	public void update() {
		for(System s: systems) {
			s.update();
		}
	}
	
	public void render() {
		
	}
	
	public void destroy() {
		for(Entity e: entities) {
			//TODO Destroy mesh lists
		}
	}
}
