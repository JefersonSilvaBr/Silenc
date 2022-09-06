package com.GupiGames.FSM;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.GupiGames.Entities.Player;
import com.GupiGames.main.Game;

public class Game_Transit implements GameState {
	
	public int stage = 0;
	public boolean state;
	
	public void update() {
		if(Player.enterMenu && Game.currentMap != 6) {
			Game.State = "nextLevel";
		}
		else if(state) {
			Game.State = "nextLevel";
		}
		else if(Player.enterMenu) {
			Player.enterMenu = false;
			stage++;
			if(stage >= 3) {
				state = true;
			}
		}
	}

	public void render(Graphics g) {
		
		int i = 30;
		
		if(Game.currentMap == 0) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("arial", Font.BOLD, 10));
			g.drawString(" Today the story is of George,", 10+i, 40);
			g.drawString("a jazz musician very renowned", 10+i, 60);
			g.drawString("in the community... going to", 10+i, 80);
			
			g.drawString("the show of his dreams,", 10+i, 100);
			g.drawString("believing he would make the  ", 10+i, 120);
			g.drawString("best presentation of his story.", 10+i, 140);
		}
		else if(Game.currentMap == 1) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("arial", Font.BOLD, 10));
			g.drawString("But life is a surprise box, and", 10+i, 60);
			g.drawString("George didn't expect the worst…", 10+i, 80);
			
			g.drawString("and his last memory was the", 10+i, 100);
			g.drawString("front of a silver car.", 10+i, 120);
		}
		else if(Game.currentMap == 2) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("arial", Font.BOLD, 10));
			g.drawString("Act 1: Holding her to be reborn;", 10+i, 90);
		}
		else if(Game.currentMap == 4) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("arial", Font.BOLD, 10));
			g.drawString("Act 2: I do not even hear my heart;", 10+i, 90);
		}
		else if(Game.currentMap == 5) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("arial", Font.BOLD, 10));
			g.drawString("Act 3: I see a way out;", 10+i, 90);
		}
		
		if(Game.currentMap == 6) {
			if(stage == 0) {
				g.setColor(Color.WHITE);
				g.setFont(new Font("arial", Font.BOLD, 10));
				g.drawString("George wakes up in a hospital bed,", 10+i, 40);
				g.drawString("slowly opening his eyes to the", 10+i, 60);
				g.drawString("cheerful reaction of his family", 10+i, 80);
				
				g.drawString("members, and looking at his", 10+i, 100);
				g.drawString("girlfriend, sees her lips move,", 10+i, 120);
				g.drawString("but hears nothing. I fell into", 10+i, 140);
			}
			else if(stage == 1) {
				g.setColor(Color.WHITE);
				g.setFont(new Font("arial", Font.BOLD, 10));
				g.drawString("tears realizing that he lost", 10+i, 40);
				g.drawString("his hearing;", 10+i, 60);
			}
			else if(stage == 2) {
				g.setColor(Color.WHITE);
				g.setFont(new Font("arial", Font.BOLD, 10));
				g.drawString("4 months later he returns home", 10+i, 40);
				g.drawString("with his girlfriend, and in his mind", 10+i, 60);
				g.drawString("wondered \"what a strange feeling that was\",", 10+i, 80);
				
				g.drawString("but soon changed his thoughts and", 10+i, 100);
				g.drawString("continued traveling with his girlfriend", 10+i, 120);
				g.drawString("and his silver car;", 10+i, 140);
			}
		}
		
	}

}
