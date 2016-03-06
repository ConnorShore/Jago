package com.engine.core.entity;

import org.lwjgl.util.vector.Vector3f;

import com.engine.core.Loader;
import com.engine.core.models.TexturedModel;

public abstract class Entity {
	protected Loader loader = new Loader();
	
	protected TexturedModel model;
	protected Vector3f position, rotation;
	protected float scale;
	
	public Entity(TexturedModel model, Vector3f position, Vector3f rotation, float scale) {
		this.model = model;
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
	}
	
	public Entity(Vector3f position, Vector3f rotation, float scale) {
		this.model = null;
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
	}
	
	public Entity() {
		this.model = null;
		this.position = new Vector3f(0,0,0);
		this.rotation = new Vector3f(0,0,0);
		this.scale = 1.0f;
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
	
	public abstract void update(float delta);

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