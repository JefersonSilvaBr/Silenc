package com.GupiGames.World;

import java.awt.image.BufferedImage;

import com.GupiGames.main.Game;

public class Plataform extends FloorTile{
	
	public static BufferedImage midPlataform = Game.tilesSheet.getSprite(16, 80, 16, 16);
	public static BufferedImage LeftPlataform = Game.tilesSheet.getSprite(0, 80, 16, 16);
	public static BufferedImage RightPlataform = Game.tilesSheet.getSprite(32, 80, 16, 16);
	
	public Plataform(BufferedImage sprite, int x, int y, int Width, int Height) {
		super(sprite, x, y, Width, Height);
	}

}
