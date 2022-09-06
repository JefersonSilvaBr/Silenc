package com.GupiGames.FSM.Game;

import java.awt.Graphics;

public class Game_Exit implements GameState {

	public void update() {
		System.exit(1);
	}

	public void render(Graphics g) {}

}
