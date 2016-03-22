package com.game.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import com.engine.core.Loader;
import com.engine.core.Window;
import com.engine.core.entity.Entity;
import com.engine.core.entity.Light;
import com.engine.core.entity.Tree;
import com.engine.core.render.MasterRenderer;
import com.engine.core.terrain.Terrain;
import com.engine.core.textures.TerrainTexture;
import com.engine.core.textures.TerrainTexturePack;

public class MainGame {
	private Random random;
	
	private Loader loader;
	private MasterRenderer renderer;
	
	private Light light;
	private Player player;;
	private TerrainTexture backgroundTexture, rTexture, gTexture, bTexture, blendMap;
	private TerrainTexturePack texturePack;
	
	private Terrain terrainTR, terrainTL;
	
	private List<Entity> entities = new ArrayList<Entity>();
	private List<Terrain> terrains = new ArrayList<Terrain>();
	
	public MainGame() {
		Window.create();
	}
	
	private void init() {
		loader = new Loader();
		renderer = new MasterRenderer(loader);
		random = new Random(1538265);
		
		//*********TERRAIN***********//
		
		backgroundTexture = new TerrainTexture(loader.loadTexture("grass.png"));
		rTexture = new TerrainTexture(loader.loadTexture("dirt.png"));
		gTexture = new TerrainTexture(loader.loadTexture("stone.png"));
		bTexture = new TerrainTexture(loader.loadTexture("flowers.png"));
		blendMap = new TerrainTexture(loader.loadTexture("blendMap.png"));
		
		texturePack = new TerrainTexturePack(backgroundTexture, rTexture, bTexture, gTexture);
		
		terrainTR = new Terrain(0,-1,loader, texturePack, blendMap, "heightMap.png");
		terrainTL = new Terrain(-1,-1,loader, texturePack, blendMap, "heightMap.png");
		terrains.add(terrainTR);
		terrains.add(terrainTL);
		
		//****************************//

		player = new Player();
		
		for (int i = 0; i < 150; i++) {
			float x = random.nextFloat() * 1600 - 800;
			float z = random.nextFloat() * -800;
			float y = getActiveTerrain(new Vector3f(x, 0, z)).getHeightOfTerrain(x, z);
			entities.add(new Tree(new Vector3f(x, y, z), new Vector3f(0, random.nextInt(180), 0), random.nextFloat() * 1.5f));
		}

		light = new Light(new Vector3f(0,50,-150), new Vector3f(0.65f, 0.65f, 0.65f));
	}
	
	private Terrain getActiveTerrain(Vector3f position) {
		//Terrain size : 800
		if(((int)position.x / 800) == 0 && position.x >= 0) {	//X: 0
			if(((int) position.z / 800) == 0 && position.z < 0) {	//Z: -1
				return terrainTR;
			}
		}
		
		else if(((int)position.x / 800) == 0 && position.x < 0) {	//X: -1
			if(((int)position.z / 800) == 0 && position.z < 0) {	//Z: -1
				return terrainTL;
			}
		}
		
		return terrainTR;
	}
	
	private void update(float delta) {
		player.update(delta, getActiveTerrain(player.getPosition()));
	}
	
	private void render() {
		renderer.render(light, player.getCamera());
		
		for(Entity e : entities) {
			renderer.processEntity(e);
		}
		for(Terrain t: terrains)
			renderer.processTerrain(t);
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