package com.auditory;

import com.auditory.components.Mesh;
import com.auditory.components.Transform;
import com.auditory.geom.Vector3;

public class EntityFactory {
	public static Entity createPlayer(World world, float x, float y, float z) {
		Entity e = world.createEntity();
		e.addComponent(new Transform(new Vector3(x, y, z)));
		return e;
	}
	
	public static Entity createBlock(World world, float x, float y, float z) {
		Entity e = world.createEntity();
		e.addComponent(new Mesh("res/Fountain.obj"));
		return e;
	}
}
