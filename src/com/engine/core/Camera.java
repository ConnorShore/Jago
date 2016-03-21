package com.engine.core;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	private Vector3f position;
	private float pitch;
	private float yaw;
	private float roll;
	private float sensitivity;
	private float dx, dy;
	
	public Camera() {
		position = new Vector3f(0,0,0);
		//position = new Vector3f(450, 15, -150);
		sensitivity = 0.05f;

		Mouse.setGrabbed(true);
		Mouse.setCursorPosition(Window.WIDTH/2, Window.HEIGHT/2);
	}
	
	public void update() {
		mouseLook();
	}
	
	private void mouseLook() {
		if(Mouse.isGrabbed()) {
			dx = Mouse.getDX();
			dy = Mouse.getDY();
			
			yaw += dx * sensitivity;
			pitch -= dy * sensitivity;
		}
	}
	
	public void moveForward(float distance) {
		position.x += distance * (float)Math.sin(Math.toRadians(yaw));
	    position.z -= distance * (float)Math.cos(Math.toRadians(yaw));
	}
	
	public void moveSide(float distance) {
	    position.x -= distance * (float)Math.sin(Math.toRadians(yaw-90));
	    position.z += distance * (float)Math.cos(Math.toRadians(yaw-90));
	}
	
	public void moveUp(float dist) {
		position.y += dist;
	}
	
	public Vector3f getPosition() {
		return position;
	}
	
	public float getSensitivity() {
		return sensitivity;
	}
	
	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	public void setYaw(float yaw) {
		this.yaw = yaw;
	}

	public void setRoll(float roll) {
		this.roll = roll;
	}

	public void setSensitivity(float sensitivity) {
		this.sensitivity = sensitivity;
	}

	public float getPitch() {
		return pitch;
	}
	
	public float getYaw() {
		return yaw;
	}
	
	public float getRoll() {
		return roll;
	}
}