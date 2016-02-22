package com.game.core;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import com.engine.core.Camera;

public class Player {
	
	private Camera camera;
	
	private Vector3f position;
	private Vector3f rotation;
	
	private float walkSpeed;
	
	private final float HEIGHT;
	
	public Player() {
		camera = new Camera();
		position = camera.getPosition();
		rotation = new Vector3f(camera.getYaw(), camera.getPitch(), camera.getRoll());
		HEIGHT = 2.0f;
		walkSpeed = 0.2f;
		
		position.y = camera.getPosition().y + HEIGHT;
	}
	
	public void input(float delta) {
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			camera.moveForward(delta * walkSpeed);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			camera.moveForward(-delta * walkSpeed);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			camera.moveSide(-delta * walkSpeed);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			camera.moveSide(delta * walkSpeed);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			camera.moveUp(delta * walkSpeed);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
			camera.moveUp(delta * walkSpeed);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			Mouse.setGrabbed(false);
		}
		
		if(Mouse.isButtonDown(0)) {
			Mouse.setGrabbed(true);
		}
	}

	public void update(float delta) {
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
		return HEIGHT;
	}

	public float getWalkSpeed() {
		return walkSpeed;
	}
}