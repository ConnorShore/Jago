package com.game.core;

import org.lwjgl.opengl.Display;

import com.engine.core.Loader;
import com.engine.core.RawModel;
import com.engine.core.Renderer;
import com.engine.core.Window;

public class Application {
	public static void main(String[] args) {
		Window.create();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		StaticShader shader = new StaticShader();
		
		float[] vertices = {
				  -0.5f, 0.5f, 0,
				  -0.5f, -0.5f, 0,
				  0.5f, -0.5f, 0,
				  0.5f, 0.5f, 0f
				};
				  
		int[] indices = {
		  0,1,3,
		  3,1,2
		};
		
		RawModel model = loader.loadToVao(vertices, indices);
		
		while(!Display.isCloseRequested()) {
			renderer.prepare();
			
			shader.start();
			renderer.render(model);
			shader.stop();
			
			Window.update();
		}
		
		shader.cleanUp();
		loader.cleanUp();
		Window.close();
	}
}