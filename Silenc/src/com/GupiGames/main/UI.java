package com.GupiGames.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class UI {
	
	public static BufferedImage bigSoul_void = Game.entitiesSheet.getSprite(48, 0, 16, 16);
	public static BufferedImage bigSoul_full = Game.entitiesSheet.getSprite(32, 0, 16, 16);
	
	public static BufferedImage soul_void = Game.entitiesSheet.getSprite(48, 16, 16, 16);
	public static BufferedImage soul_full = Game.entitiesSheet.getSprite(32, 16, 16, 16);
	
	public static BufferedImage ButtonPlay = Game.uiSheet.getSprite(0, 0, 48, 16);
	public static BufferedImage ButtonStory = Game.uiSheet.getSprite(0, 16, 48, 16);
	public static BufferedImage ButtonCredits = Game.uiSheet.getSprite(0, 32, 48, 16);
	public static BufferedImage ButtonMenu = Game.uiSheet.getSprite(0, 48, 48, 16);
	public static BufferedImage ButtonExit = Game.uiSheet.getSprite(0, 64, 48, 16);
	public static BufferedImage ButtonResume = Game.uiSheet.getSprite(48, 48, 48, 16);
	public static BufferedImage ButtonTutorial_of = Game.uiSheet.getSprite(48, 64, 48, 16);
	public static BufferedImage ButtonTutorial_on = Game.uiSheet.getSprite(96, 64, 48, 16);
	public static BufferedImage ButtonMask_on = Game.uiSheet.getSprite(48, 0, 50, 18);
	public static BufferedImage ButtonMask_off = Game.uiSheet.getSprite(48, 18, 50, 18);
	
	public static BufferedImage logo = Game.uiSheet.getSprite(0, 80, 120, 48);
	public static BufferedImage gameOver = Game.uiSheet.getSprite(0, 128, 224, 48);
	public static BufferedImage credits = Game.uiSheet.getSprite(368, 0, 232, 192);
	
	public static BufferedImage tutorial_1 = Game.keysSheet.getSprite(0, 0, 240, 80);
	public static BufferedImage tutorial_2 = Game.keysSheet.getSprite(0, 128, 48, 32);
	public static BufferedImage tutorial_3 = Game.keysSheet.getSprite(48, 128, 48, 32);
	public static BufferedImage tutorial_03 = Game.keysSheet.getSprite(48, 112, 128, 16);
	
	
	public static void soulsUI(Graphics g) {
		
		g.drawImage(bigSoul_void, 16, 0, null);
		g.drawImage(soul_void, 27, 0, null);
		g.drawImage(soul_void, 38, 0, null);
		
		switch(Game.player.soul) 
		{
		
		case 1:
			g.drawImage(soul_full, 27, 0, null);
		break;
		
		case 2:
			g.drawImage(soul_full, 27, 0, null);
			g.drawImage(soul_full, 38, 0, null);
		break;
		
		}
		if(Game.player.bigSoul) {
			g.drawImage(bigSoul_full, 16, 0, null);
		}
	}
	
}
