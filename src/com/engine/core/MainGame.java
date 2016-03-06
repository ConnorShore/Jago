package com.engine.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import com.engine.core.entity.Entity;
import com.engine.core.entity.Light;
import com.engine.core.entity.Tree;
import com.engine.core.render.MasterRenderer;
import com.engine.core.terrain.Terrain;
import com.engine.core.textures.TerrainTexture;
import com.engine.core.textures.TerrainTexturePack;
import com.game.core.Player;

public class MainGame {
	private Random random;
	
	private Loader loader;
	private MasterRenderer renderer;
	
	private Light light;
	private Player player;;
	private TerrainTexture backgroundTexture, rTexture, gTexture, bTexture, blendMap;
	private TerrainTexturePack texturePack;
	
	private Terrain terrain, terrain2;
	
	private List<Entity> entities = new ArrayList<Entity>();
	
	public MainGame() {
		Window.create();
	}
	
	private void init() {
		loader = new Loader();
		renderer = new MasterRenderer();
		random = new Random(1538265);
		
		for (int i = 0; i < 2; i++) {
			float x = random.nextFloat() * 800 - 400;
			float z = random.nextFloat() * -600;
			float y = 0;
			entities.add(new Tree(new Vector3f(x, y, z), new Vector3f(0, random.nextInt(180), 0), 0.9f));

			x = random.nextFloat() * 800 - 400;
			z = random.nextFloat() * -600;
			y = 0;
			entities.add(new Tree(new Vector3f(x, y, z), new Vector3f(0, random.nextInt(180), 0), 1.9f));

			x = random.nextFloat() * 800 - 400;
			z = random.nextFloat() * -600;
			y = 0;
			entities.add(new Tree(new Vector3f(x, y, z), new Vector3f(0, random.nextInt(180), 0), 2.3f));
		}

		light = new Light(new Vector3f(0,50,-150), new Vector3f(0.65f, 0.65f, 0.65f));
		player = new Player();
		
		backgroundTexture = new TerrainTexture(loader.loadTexture("grass.png"));
		rTexture = new TerrainTexture(loader.loadTexture("dirt.png"));
		gTexture = new TerrainTexture(loader.loadTexture("stone.png"));
		bTexture = new TerrainTexture(loader.loadTexture("flowers.png"));
		blendMap = new TerrainTexture(loader.loadTexture("blendMap.png"));
		
		texturePack = new TerrainTexturePack(backgroundTexture, rTexture, bTexture, gTexture);
		
		terrain = new Terrain(0,-1,loader, texturePack, blendMap, "heightMap.png");
		terrain2 = new Terrain(-1,-1,loader, texturePack, blendMap, "heightMap.png");
	}
	
	private void update(float delta) {
		player.update(delta, terrain);
	}
	
	private void render() {
		renderer.render(light, player.getCamera());
		for(Entity e : entities) {
			renderer.processEntity(e);
		}
		renderer.processTerrain(terrain);
		//renderer.processTerrain(terrain2);
	}
	
	private void gameLoop() {
		while(!Display.isCloseRequested()) {
			update(Window.getDelta());
			render();
			Window.update();
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