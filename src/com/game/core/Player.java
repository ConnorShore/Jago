package com.game.core;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import com.engine.core.Camera;
import com.engine.core.terrain.Terrain;

public class Player {
	
	private Camera camera;
	
	private Vector3f position, rotation;
	
	private float walkSpeed, upwardsSpeed;
	
	private final float HEIGHT, GRAVITY, JUMP_POWER;
	
	private boolean inAir;
	
	public Player() {
		camera = new Camera();
		position = camera.getPosition();
		rotation = new Vector3f(camera.getYaw(), camera.getPitch(), camera.getRoll());
		HEIGHT = 2.0f;
		walkSpeed = 20.0f;
		GRAVITY = -3f;
		JUMP_POWER = 1.15f;
		inAir = true;
		
		position.y = camera.getPosition().y + HEIGHT;
	}
	
	private void input(float delta) {
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			walkSpeed = 30.0f;
		} else {
			walkSpeed = 20.0f;
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
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
			camera.moveUp(-delta * walkSpeed);
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
		System.out.println("Round 1: " + upwardsSpeed);
		
		float terrainHeight = terrain.getHeightOfTerrain(camera.getPosition().x, camera.getPosition().z);
		if((camera.getPosition().y - HEIGHT) < terrainHeight) {
			upwardsSpeed = 0.0f;
			inAir = false;
			camera.setPosition(new Vector3f(camera.getPosition().x, terrainHeight + HEIGHT, camera.getPosition().z));
		}

		System.out.println("Round 2: " + upwardsSpeed);
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
		return HEIGHT;
	}

	public float getWalkSpeed() {
		return walkSpeed;
	}
}