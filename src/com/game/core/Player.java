package com.game.core;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import com.engine.core.Camera;

public class Player {
	
	private Camera camera;
	
	private Vector3f position;
	private Vector3f rotation;
	
	private float height;
	private float walkSpeed;
	
	public Player() {
		camera = new Camera();
		position = camera.getPosition();
		rotation = new Vector3f(camera.getYaw(), camera.getPitch(), camera.getRoll());
		height = 2.0f;
		walkSpeed = 0.2f;
		
		position.y = camera.getPosition().y + height;
	}
	
	public void input() {
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			camera.moveForward(walkSpeed);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			camera.moveForward(-walkSpeed);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			camera.moveSide(-walkSpeed);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			camera.moveSide(walkSpeed);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			camera.moveUp(walkSpeed);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
			camera.moveUp(-walkSpeed);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			Mouse.setGrabbed(false);
		}
		
		if(Mouse.isButtonDown(0)) {
			Mouse.setGrabbed(true);
		}
	}

	public void update() {
		camera.update();
	}
	
	public Camera getCamera() {
		return camera;
	}

	public Vector3f getPosition() {
		return position;
	}

	public Vector3f getRotation() {
		return rotation;
	}

	public float getHeight() {
		return height;
	}

	public float getWalkSpeed() {
		return walkSpeed;
	}
}