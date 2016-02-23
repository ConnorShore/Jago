package com.engine.core.entity.Component;

public class VelocityComponent extends Component {
	private float velX;
	private float velY;
	private float velZ;
	
	public VelocityComponent(float velX, float velY, float velZ) {
		super();
		this.velX = velX;
		this.velY = velY;
		this.velZ = velZ;
	}
	
	public float getVelX() {
		return velX;
	}
	
	public float getVelY() {
		return velY;
	}
	
	public float getVelZ() {
		return velZ;
	}
}