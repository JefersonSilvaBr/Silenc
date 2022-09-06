package com.GupiGames.FSM;

import java.awt.Graphics;

public class Game_Exit implements GameState {

	public void update() {
		System.exit(1);
	}

	public void render(Graphics g) {}

}
