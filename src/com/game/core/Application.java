package com.game.core;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import com.engine.core.Camera;
import com.engine.core.Loader;
import com.engine.core.Renderer;
import com.engine.core.Window;
import com.engine.core.entity.Entity;
import com.engine.core.entity.Light;
import com.engine.core.models.RawModel;
import com.engine.core.models.TexturedModel;
import com.engine.core.textures.ModelTexture;

public class Application {
	public static void main(String[] args) {
		Window.create();
		
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);

		
		RawModel model = loader.loadObjModel("stall.obj");
		ModelTexture texture = new ModelTexture(loader.loadTexture("stallTexture.png"));
		TexturedModel texturedModel = new TexturedModel(model, texture);
		
		Entity entity = new Entity(texturedModel, new Vector3f(0,0,-25), new Vector3f(0,0,0), 1);
		Light light = new Light(new Vector3f(0,0,-20), new Vector3f(1,1,1));
		Camera camera = new Camera();
		
		while(!Display.isCloseRequested()) {
			entity.rotateEntity(0, 1, 0);
			camera.input();
			renderer.prepare();
			shader.start();
			shader.loadLight(light);
			shader.loadViewMatrix(camera);
			renderer.render(entity, shader);
			shader.stop();
			
			Window.update();
		}
		
		shader.cleanUp();
		loader.cleanUp();
		Window.close();
	}
}