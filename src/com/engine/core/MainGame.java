package com.engine.core;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import com.engine.core.entity.Entity;
import com.engine.core.entity.Light;
import com.engine.core.models.RawModel;
import com.engine.core.models.TexturedModel;
import com.engine.core.render.MasterRenderer;
import com.engine.core.terrain.Terrain;
import com.engine.core.textures.ModelTexture;
import com.engine.core.textures.TerrainTexture;
import com.engine.core.textures.TerrainTexturePack;
import com.game.core.Player;

public class MainGame {
	private Loader loader;
	private MasterRenderer renderer;
	
	private RawModel model;
	private ModelTexture texture;
	private TexturedModel texturedModel;
	
	private Entity entity;
	private Light light;
	private Player player;;
	
	private TerrainTexture backgroundTexture, rTexture, gTexture, bTexture, blendMap;
	private TerrainTexturePack texturePack;
	
	private Terrain terrain, terrain2;
	
	private float delta, timeCount;
	private double lastLoopTime;
	
	public MainGame() {
		Window.create();
	}
	
	private void init() {
		loader = new Loader();
		renderer = new MasterRenderer();
		model = loader.loadObjModel("stall.obj");
		texture = new ModelTexture(loader.loadTexture("stallTexture.png"));
		texturedModel = new TexturedModel(model, texture);
		entity = new Entity(texturedModel, new Vector3f(0,0,-25), new Vector3f(0,180,0), 1);
		light = new Light(new Vector3f(0,25,-20), new Vector3f(1,1,1));
		player = new Player();
		
		backgroundTexture = new TerrainTexture(loader.loadTexture("grass.png"));
		rTexture = new TerrainTexture(loader.loadTexture("dirt.png"));
		gTexture = new TerrainTexture(loader.loadTexture("stone.png"));
		bTexture = new TerrainTexture(loader.loadTexture("flowers.png"));
		blendMap = new TerrainTexture(loader.loadTexture("blendMap.png"));
		
		texturePack = new TerrainTexturePack(backgroundTexture, rTexture, bTexture, gTexture);
		
		terrain = new Terrain(0,-1,loader, texturePack, blendMap, "heightMap.png");
		terrain2 = new Terrain(-1,-1,loader, texturePack, blendMap, "heightMap.png");
	
		lastLoopTime = System.currentTimeMillis();
	}
	
	private void input(float delta) {
		player.input(delta);
	}
	
	private void update(float delta) {
		player.update(delta);
	}
	
	private void render() {
		renderer.render(light, player.getCamera());
		renderer.processEntity(entity);
		renderer.processTerrain(terrain);
		renderer.processTerrain(terrain2);
		Window.update();
	}
	
	private void gameLoop() {
		long lastTime = System.currentTimeMillis();
		int frames = 0;
		
		while(!Display.isCloseRequested()) {
			long currentTime = System.currentTimeMillis();
			delta = (float) (currentTime - lastLoopTime);
			lastLoopTime = currentTime;
			timeCount += delta;
			frames++;

			input(delta);
			update(delta);
			render();
			
			if(currentTime - lastTime >= 1000) {
				System.out.println("FPS: " + frames);
				frames = 0;
				lastTime = System.currentTimeMillis();
			}
		}
	}
	
	private void cleanUp() {
		loader.cleanUp();
		Window.close();
	}
	
	public void run() {
		init();
		gameLoop();
		cleanUp();
	}
}