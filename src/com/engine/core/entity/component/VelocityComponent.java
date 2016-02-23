package com.engine.core.entity.component;

public class VelocityComponent extends Component {
	private float velX, velY;

	public VelocityComponent(float velX, float velY) {
		super(ComponentType.VELOCITY.getValue());
		this.velX = velX;
		this.velY = velY;
	}

	public float getvelX() {
		return velX;
	}

	public void setvelX(float velX) {
		this.velX = velX;
	}

	public float getvelY() {
		return velY;
	}

	public void setvelY(float velY) {
		this.velY = velY;
	}
}