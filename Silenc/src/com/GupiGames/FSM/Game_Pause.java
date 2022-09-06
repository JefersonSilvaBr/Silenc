package com.GupiGames.FSM;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.GupiGames.Entities.Player;
import com.GupiGames.main.Game;
import com.GupiGames.main.UI;

public class Game_Pause implements GameState {
	public String[] options = {"Resume", "Tutorial", "Menu"};
	public int curOption = 0;
	public int opacity = 0;
	
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
			Game.gameState = Game.save;
		}
		else if(curOption == 1 && Player.enterMenu) {
			Game.tutorial = false;
		}
		else if(curOption == 2 && Player.enterMenu) {
			Player.enterMenu = false;
			Game.gameState = new Game_Menu();
		}

		
	}

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
		
		int i = 20;
		
		g.drawImage(UI.logo, 57, i - 5, null);
		
		g.drawImage(UI.ButtonMask_off, 95, 49 + i, null);
		g.drawImage(UI.ButtonMask_off, 95, 69 + i, null);
		g.drawImage(UI.ButtonMask_off, 95, 89 + i, null);
		
		if(curOption == 0) {
			g.drawImage(UI.ButtonMask_on, 95, 49 + i, null);
		}
		else if(curOption == 1) {
			g.drawImage(UI.ButtonMask_on, 95, 69 + i, null);
		}
		else if(curOption == 2) {
			g.drawImage(UI.ButtonMask_on, 95, 89 + i, null);
		}

		if(Game.tutorial) {
			g.drawImage(UI.ButtonTutorial_on, 95, 69 + i, null);
		}
		else {
			g.drawImage(UI.ButtonTutorial_of, 96, 70 + i, null);
		}
		
		
		g.drawImage(UI.ButtonResume, 96, 50 + i, null);
		g.drawImage(UI.ButtonMenu, 96, 90 + i, null);
		
	}
}
