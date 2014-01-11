package com.auditory;

import org.lwjgl.util.vector.Vector3f;

import com.auditory.components.MainPlayer;
import com.auditory.components.Mesh;
import com.auditory.components.Transform;
import com.auditory.components.Velocity;


public class EntityFactory {
	public static Entity createPlayer(World world, float x, float y, float z) {
		Entity e = world.createEntity();
		world.addComponent(new Transform(e.id, new Vector3f(x, y, z)));
		world.addComponent(new Velocity(e.id, new Vector3f(0, 0, 0)));
		world.addComponent(new Mesh(e.id, "res/armadillo.obj"));
		world.addComponent(new MainPlayer(e.id));
		return e;
	}
	
	public static Entity createCube(World world, float x, float y, float z) {
		Entity e = world.createEntity();
		world.addComponent(new Transform(e.id, new Vector3f(x, y, z)));
		world.addComponent(new Velocity(e.id, new Vector3f(0, 0, 0)));
		world.addComponent(new Mesh(e.id, "res/armadillo.obj"));
		return e;
	}
}
