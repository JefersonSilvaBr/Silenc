package com.GupiGames.FSM.Game;

import java.awt.Graphics;

import com.GupiGames.Entities.Player;
import com.GupiGames.main.Game;
import com.GupiGames.main.UI;

public class Game_Credits implements GameState {

	@Override
	public void update() {
		if(Player.left) {
			Game.gameState = new Game_Menu();
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(UI.credits, 0, 0, null);

	}

}
