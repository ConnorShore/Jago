package com.engine.core.entity;

import org.lwjgl.util.vector.Vector3f;

import com.engine.core.models.TexturedModel;

public class Entity {
	private TexturedModel model;
	private Vector3f position, rotation;
	private float scale;
	
	public Entity(TexturedModel model, Vector3f position, Vector3f rotation, float scale) {
		this.model = model;
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
	}
	
	public void moveEntity(float dx, float dy, float dz) {
		this.position.x += dx;
		this.position.y += dy;
		this.position.z += dz;
	}
	
	public void rotateEntity(float dx, float dy, float dz) {
		this.rotation.x += dx;
		this.rotation.y += dy;
		this.rotation.z += dz;
	}

	public TexturedModel getModel() {
		return model;
	}

	public void setModel(TexturedModel model) {
		this.model = model;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public Vector3f getRotation() {
		return rotation;
	}

	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
}