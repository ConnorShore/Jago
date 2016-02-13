package com.game.core;

import org.lwjgl.util.vector.Matrix4f;

import com.engine.core.Camera;
import com.engine.core.ShaderProgram;
import com.engine.core.tools.Tools;

public class StaticShader extends ShaderProgram {
	
	private static final String VERTEX_FILE = "res/shaders/colorShader.vert";
	private static final String FRAGMENT_FILE = "res/shaders/colorShader.frag";
	
	private int location_transformationMatrix;
	private int location_projectionMatrix;
	private int location_viewMatrix;
	
	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
		
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "vertexPosition");
		super.bindAttribute(1, "vertexUV");
	}

	@Override
	protected void getAllUniformLocations() {
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
	}
	
	public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(location_transformationMatrix, matrix);
	}
	
	public void loadProjectionMatrix(Matrix4f matrix) {
		super.loadMatrix(location_projectionMatrix, matrix);
	}
	
	public void loadViewMatrix(Camera camera) {
		Matrix4f viewMatrix = Tools.createViewMatrix(camera);
		super.loadMatrix(location_viewMatrix, viewMatrix);
	}
}