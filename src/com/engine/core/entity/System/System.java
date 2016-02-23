package com.engine.core.entity.System;

import com.engine.core.entity.Component.Component;

public abstract class System {
	public abstract void add(Component comp);
	public abstract void remove(Component comp);
	public abstract void update();
	public abstract void render();
}