package com.engine.core.entity.component;


public class World {
	private int ENTITY_COUNT = 50;
	
	private int[] entities = new int[ENTITY_COUNT];
	
	//Components//
	private PositionComponent[] position = new PositionComponent[ENTITY_COUNT];
	private VelocityComponent[] velocity = new VelocityComponent[ENTITY_COUNT];
	////
	
	public int createEntity() {
		for(int i = 0; i < entities.length; i++) {
			if(entities[i] == ComponentType.NONE.getValue()) {
				return i;
			}
		}
		
		resize();
		return createEntity();
	}
	
	public void destroyEntity(int entity) {
		entities[entity] = ComponentType.NONE.getValue();
	}
	
	private void resize() {
		ENTITY_COUNT = ENTITY_COUNT * 2;
	}
}
