package com.game.core;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import com.engine.core.Camera;
import com.engine.core.terrain.Terrain;

public class Player {
	
	private Camera camera;
	
	private Vector3f position, rotation;
	
	private float walkSpeed, upwardsSpeed, height;
	
	private final float GRAVITY, JUMP_POWER;
	
	private boolean inAir, crouched;
	
	public Player() {
		camera = new Camera();
		position = camera.getPosition();
		rotation = new Vector3f(camera.getYaw(), camera.getPitch(), camera.getRoll());
		height = 2.0f;
		walkSpeed = 20.0f;
		GRAVITY = -3f;
		JUMP_POWER = 1.15f;
		inAir = true;
		crouched = false;
		
		position.y = camera.getPosition().y + height;
	}
	
	private void input(float delta) {
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			walkSpeed = 30.0f;
		} else {
			walkSpeed = 20.0f;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
			crouched = true;
			walkSpeed = 10.0f;
			height = 1.25f;
		} else {
			crouched = false;
			height = 2.0f;
		}
		
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
			jump();
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			Mouse.setGrabbed(false);
		}
		
		if(Mouse.isButtonDown(0)) {
			Mouse.setGrabbed(true);
		}
	}

	public void update(float delta, Terrain terrain) {
		input(delta);
		camera.update();
		
		upwardsSpeed += GRAVITY * delta;
		camera.moveUp(upwardsSpeed);
		
		float terrainHeight = terrain.getHeightOfTerrain(camera.getPosition().x, camera.getPosition().z);
		if((camera.getPosition().y - height) < terrainHeight) {
			upwardsSpeed = 0.0f;
			inAir = false;
			camera.setPosition(new Vector3f(camera.getPosition().x, terrainHeight + height, camera.getPosition().z));
		}
	}
	
	private void jump() {
		if(!inAir) {
			upwardsSpeed = JUMP_POWER;
			inAir = true;
		}
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