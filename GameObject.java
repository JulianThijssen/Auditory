package com.auditory;

import java.util.ArrayList;
import java.util.List;

import com.auditory.geom.Vector3;

public abstract class GameObject {
	//Every gameobject can potentially have a parent
	public GameObject parent = null;
	//A list of children added to the game object
	protected List<GameObject> children = new ArrayList<GameObject>();
	//A list of components added to the game object
	protected List<Component> components = new ArrayList<Component>();
	//The position of the game object in global coordinates
	public Vector3 position;
	//The rotation of the game object in euler coordinates
	public Vector3 rotation;
	
	public GameObject(Vector3 position) {
		this(position, new Vector3(0, 0, 0));
	}
	
	public GameObject(Vector3 position, Vector3 rotation) {
		this.position = position;
		this.rotation = rotation;
	}
	
	public void addChild(GameObject o) {
		o.setParent(this);
		children.add(o);
	}
	
	public void addComponent(Component c) {
		components.add(c);
	}
	
	public void setParent(GameObject parent) {
		this.parent = parent;
	}
	
	public void update() {
		for(Component c: components) {
			if(c instanceof Behaviour) {
				((Behaviour) c).update();
			}
		}
	}

	public void render() {
		for(Component c: components) {
			if(c instanceof Renderable) {
				((Renderable) c).render();
			}
		}
	}
}
