package com.engine.core.entity.component;


public enum ComponentType {
	NONE(0), POSITION(1), VELOCITY(2), INPUT(4);

	private int value;
	
	ComponentType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}