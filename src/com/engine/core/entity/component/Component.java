package com.engine.core.entity.component;

public abstract class Component {
	protected int type;
	
	public Component(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}
}