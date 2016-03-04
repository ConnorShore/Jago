package com.engine.core.entity.component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.engine.core.entity.system.Mask;

public class World {
	private int currentEntity = 0;
	
	private List<Integer> entities = new ArrayList<Integer>();
	
	//Components//
	private Map<Integer, PositionComponent> position = new HashMap<Integer, PositionComponent>();
	private Map<Integer, VelocityComponent> velocity = new HashMap<Integer, VelocityComponent>();
	////
	
	public void addComponent(int id, Component comp) {
		if(comp.getType() == ComponentType.POSITION.getValue()) {
			System.out.println("Position id: "+ id);
			position.put(id, (PositionComponent) comp);
		}
		
		if(comp.getType() == ComponentType.VELOCITY.getValue()) {
			System.out.println("Velocity id: "+ id);
			velocity.put(id, (VelocityComponent) comp);
		}
	}
	
	public int createEntity() {
		entities.add(currentEntity);
		position.put(currentEntity, null);
		velocity.put(currentEntity, null);
		
		return currentEntity++;
	}
	
	public void print() {
		for(int i = 0; i < currentEntity; i++) {
			System.out.println("Entity ID: " + entities.get(i));
			System.out.println("Position ID: " + position.get(i));
			System.out.println("Velocity ID: " + velocity.get(i));
		}
	}
	
	public void destroyEntity(int entity) {
		entities.set(entity, ComponentType.NONE.getValue());
	}

	public List<Integer> getEntities() {
		return entities;
	}

	public Map<Integer, PositionComponent> getPosition() {
		return position;
	}

	public Map<Integer, VelocityComponent> getVelocity() {
		return velocity;
	}

	public int getCurrentEntity() {
		return currentEntity;
	}
	
//	public static void main(String[] args) {
//		Entity e1 = new Entity();
//		e1.addComponent(new PositionComponent(10, 2));
//		e1.addComponent(new VelocityComponent(0, 2));
//		
//		Entity e2 = new Entity();
//		e2.addComponent(new PositionComponent(10, 2));
//		e1.addComponent(new VelocityComponent(0, 2));
//		
//		Entity e3 = new Entity();
//		e3.addComponent(new VelocityComponent(0, 2));
//		
//		Entity e4 = new Entity();
//		e4.addComponent(new VelocityComponent(0, 2));
//		
//		print();
//	}
}
