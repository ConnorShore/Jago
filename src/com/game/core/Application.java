package com.game.core;

import com.engine.core.MainGame;

public class Application {
	
	private static MainGame game = new MainGame();
	
	public static void main(String[] args) {
		game.run();
	}
}