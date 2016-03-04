package com.game.core;

import com.engine.core.MainGame;
import com.engine.core.entity.component.MainTest;

public class Application {
	
	//private static MainGame game = new MainGame();
	private static MainTest test = new MainTest();
	
	public static void main(String[] args) {
		//game.run();
		test.run();
	}
}