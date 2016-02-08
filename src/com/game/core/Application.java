package com.game.core;

import org.lwjgl.opengl.Display;

import com.engine.core.Loader;
import com.engine.core.Renderer;
import com.engine.core.Window;
import com.engine.core.models.RawModel;
import com.engine.core.models.TexturedModel;
import com.engine.core.textures.ModelTexture;

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
		
		float[] uvs = {
			0,0,
			0,1,
			1,1,
			1,0
		};
		
		RawModel model = loader.loadToVao(vertices, uvs, indices);
		ModelTexture texture = new ModelTexture(loader.loadTexture("picture.png"));
		TexturedModel texturedModel = new TexturedModel(model, texture);
		
		while(!Display.isCloseRequested()) {
			renderer.prepare();
			
			shader.start();
			renderer.render(texturedModel);
			shader.stop();
			
			Window.update();
		}
		
		shader.cleanUp();
		loader.cleanUp();
		Window.close();
	}
}