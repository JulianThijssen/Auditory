package com.auditory;

import java.util.ArrayList;
import java.util.List;

import com.auditory.geom.Vector3;

public abstract class Entity extends GameObject {	
	protected List<GameObject> children = new ArrayList<GameObject>();
	
	public Entity(Vector3 position) {
		super(position);
	}

	public void update() {
		for(Component c: components) {
			if(c instanceof Behaviour) {
				((Behaviour) c).update();
			}
		}
		if(camera != null) {
			camera.update();
		}
	}
	
	public void addChild(GameObject o) {
		children.add(o);
	}
}
