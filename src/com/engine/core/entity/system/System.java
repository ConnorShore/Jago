package com.engine.core.entity.system;

import java.util.ArrayList;
import java.util.List;

import com.engine.core.entity.component.ComponentType;
import com.engine.core.entity.component.Entity;
import com.engine.core.entity.component.World;

public abstract class System {
	
	protected World world = new World();
	
	protected List<Entity> movementSystem = new ArrayList<Entity>();
	
	public void sortEntites(List<Entity> entities) {
		for(int i = 0; i < entities.size(); i++) {
			if(entities.get(i).getComponents().get(i) == ComponentType.POSITION.getValue()
					&& entities.get(i).getComponents().get(i) == ComponentType.VELOCITY.getValue()) {
				movementSystem.add(entities.get(i));
			}
		}
	}
	
	public abstract void init();
	public abstract void update();
	
	public World getWorld() {
		return world;
	}
}