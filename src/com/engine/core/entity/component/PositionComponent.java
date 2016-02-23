package com.engine.core.entity.component;

public class PositionComponent extends Component{
	private float x, y;

	public PositionComponent(float x, float y) {
		super(ComponentType.POSITION.getValue());
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}