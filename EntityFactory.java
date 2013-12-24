package com.auditory;

import com.auditory.components.Mesh;
import com.auditory.components.Transform;
import com.auditory.components.Velocity;
import com.auditory.geom.Vector3;

public class EntityFactory {
	public static Entity createPlayer(World world, float x, float y, float z) {
		Entity e = world.createEntity();
		world.addComponent(new Transform(e.id, new Vector3(x, y, z)));
		world.addComponent(new Velocity(e.id, new Vector3(0, 0, 0)));
		world.addComponent(new Mesh(e.id, "res/armadillo.obj"));
		return e;
	}
	
	public static Entity createBlock(World world, float x, float y, float z) {
		Entity e = world.createEntity();
		world.addComponent(new Transform(e.id, new Vector3(x, y, z)));
		world.addComponent(new Mesh(e.id, "res/armadillo.obj"));
		return e;
	}
}
