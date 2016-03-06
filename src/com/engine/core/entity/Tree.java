package com.engine.core.entity;

import org.lwjgl.util.vector.Vector3f;

import com.engine.core.models.TexturedModel;
import com.engine.core.textures.ModelTexture;

public class Tree extends Entity {
	
	public Tree(Vector3f position, Vector3f rotation, float scale) {
		super(position, rotation, scale);
		model = new TexturedModel(loader.loadObjModel("tree.obj"), new ModelTexture(loader.loadTexture("tree.png")));
	}
	
	public void update(float delta) {
		
	}
}
