package com.auditory;

public abstract class Component {
	protected GameObject parent;
	
	public Component(GameObject parent) {
		this.parent = parent;
	}
}
