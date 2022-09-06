package com.GupiGames.World;

import java.awt.image.BufferedImage;

import com.GupiGames.main.Game;

public class WallTile extends Tile{
	
	private static final long serialVersionUID = -3514463356773827532L;
	
	public static BufferedImage Blank = Game.tilesSheet.getSprite(48, 80, 16, 16);
	public static BufferedImage WallTile_left = Game.tilesSheet.getSprite(0, 16, 16, 16);
	public static BufferedImage WallTile_right = Game.tilesSheet.getSprite(48, 48, 16, 16);
	public static BufferedImage WallTile_up = Game.tilesSheet.getSprite(32, 48, 16, 16);
	public static BufferedImage WallTile_Down = Game.tilesSheet.getSprite(16, 16, 16, 16);
	public static BufferedImage WallTile_Right = Game.tilesSheet.getSprite(16, 0, 16, 16);
	public static BufferedImage WallTile_Left = Game.tilesSheet.getSprite(0, 0, 16, 16);

	public WallTile(BufferedImage sprite, int x, int y, int Width, int Height) {
		super(sprite, x, y, Width, Height);
	}
	
}