package com.engine.core.entity.Component;


public class InputComponent extends Component {
	
	private int key;
	private Component actor;
	
	public InputComponent(int key, Component actor) {
		this.key = key;
		this.actor = actor;
	}

	public int getKey() {
		return key;
	}

	public Component getActor() {
		return actor;
	}
}