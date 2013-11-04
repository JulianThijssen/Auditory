package com.auditory;

public abstract class Behaviour extends Component {
	
	public Behaviour(GameObject parent) {
		super(parent);
	}
	
	public abstract void update();
}
