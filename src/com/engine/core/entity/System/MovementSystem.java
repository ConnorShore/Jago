package com.engine.core.entity.System;

import java.util.ArrayList;
import java.util.List;

import com.engine.core.entity.Component.Component;
import com.engine.core.entity.Component.InputComponent;
import com.engine.core.entity.Component.PositionComponent;
import com.engine.core.entity.Component.VelocityComponent;

public class MovementSystem extends System {
	
	private List<InputComponent> inputs = new ArrayList<InputComponent>();
	private List<VelocityComponent> velocities = new ArrayList<VelocityComponent>();
	private List<PositionComponent> positions = new ArrayList<PositionComponent>();
	
	public MovementSystem(List<PositionComponent> position, List<VelocityComponent> velocity, List<InputComponent> input) {
		this.positions = position;
		this.velocities = velocity;
		this.inputs = input;
	}

	@Override
	public void add(Component comp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Component comp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}
	
}
