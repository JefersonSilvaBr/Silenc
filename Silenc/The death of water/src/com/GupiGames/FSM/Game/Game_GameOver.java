package com.GupiGames.FSM.Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.GupiGames.Entities.Player;
import com.GupiGames.World.World;
import com.GupiGames.main.Game;
import com.GupiGames.main.UI;

public class Game_GameOver implements GameState {

	public String[] options = {"Play", "Menu"};
	private int curOption = 0;
	
	public Game_GameOver() {
		Player.up = false;
		Player.down = false;
	}

	@Override
	public void update() {
		if(Player.up) {
			if((curOption - 1) < 0) {
				curOption = options.length - 1;
			}
			else {
				curOption--;
			}
			Player.up = false;
		}
		else if(Player.down) {
			if((curOption + 1) >= options.length) {
				curOption = 0;
			}
			else {
				curOption++;
			}
			Player.down = false;
		}
		
		if(curOption == 0 && Player.enterMenu) {
			World.reset();
			Game.map = new World("/Map/mapJam-0" + (Game.currentMap) + ".png");
			Player.enterMenu = false;
			Game.gameState = new Game_Play();
		}
		else if(curOption == 1 && Player.enterMenu) {
			Player.enterMenu = false;
			Game.gameState = new Game_Menu();
		}
	}

	@Override
	public void render(Graphics g) {
		Game.map.render(g);
		for(int index = 0; index < Game.souls.size(); index++) {
			Game.souls.get(index).render(g);
		}
		for(int index = 0; index < Game.entities.size(); index++) {
			Game.entities.get(index).render(g);
		}
		Game.player.render(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(new Color(0,0,0, 110));
		g2.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		
		g.drawImage(UI.gameOver, 15, 40, null);
		
		int i = 40;
		
		g.drawImage(UI.ButtonMask_off, 95, 49 + i, null);
		g.drawImage(UI.ButtonMask_off, 95, 69 + i, null);
		
		if(curOption == 0) {
			g.drawImage(UI.ButtonMask_on, 95, 49 + i, null);
		}
		else if(curOption == 1) {
			g.drawImage(UI.ButtonMask_on, 95, 69 + i, null);
		}
		
		g.drawImage(UI.ButtonPlay, 96, 50 + i, null);
		g.drawImage(UI.ButtonMenu, 96, 70 + i, null);
		
	}

}
