package com.auditory;

public abstract class System {
	protected World world;
	
	public abstract void update(Entity e);
	
	public void setWorld(World world) {
		this.world = world;
	}
}
