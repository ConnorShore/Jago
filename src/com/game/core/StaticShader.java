package com.game.core;

import com.engine.core.ShaderProgram;

public class StaticShader extends ShaderProgram {
	
	private static final String VERTEX_FILE = "res/shaders/colorShader.vert";
	private static final String FRAGMENT_FILE = "res/shaders/colorShader.frag";
	
	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
		
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "vertexPosition");
		super.bindAttribute(1, "vertexUV");
	}

}
