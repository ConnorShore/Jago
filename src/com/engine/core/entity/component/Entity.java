package com.engine.core.entity.component;

import java.util.ArrayList;
import java.util.List;

public class Entity {
	
	private World world = new World();
	private List<Integer> components = new ArrayList<Integer>();
	
	protected int id;
	
	public Entity() {
		id = world.createEntity();
	}
	
	public void addComponent(Component comp) {
		world.addComponent(id, comp);
		components.add(comp.getType());
	}
	
	public int getID() {
		return id;
	}
	
	public World getWorld() {
		return world;
	}
	
	public List<Integer> getComponents() {
		return components;
	}
}