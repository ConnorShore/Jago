package com.engine.core.entity.component;

public class Entity {
	private World world = new World();
	
	protected int id;
	
	public Entity() {
		id = world.createEntity();
		System.out.println("Entity id: " + id);
	}
	
	public void addComponent(Component comp) {
		world.addComponent(id, comp);
	}
	
	public int getID() {
		return id;
	}
	
	public World getWorld() {
		return world;
	}
}