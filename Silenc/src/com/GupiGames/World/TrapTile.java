package com.GupiGames.World;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.GupiGames.main.Game;

public class TrapTile extends Tile{
		
	public static BufferedImage spike_Inverse_down = Game.tilesSheet.getSprite(32, 0, 16, 16);
	public static BufferedImage spike_Inverse_up = Game.tilesSheet.getSprite(48, 0, 16, 16);
	public static BufferedImage spike_Inverse_left = Game.tilesSheet.getSprite(32, 16, 16, 16);
	public static BufferedImage spike_Inverse_right = Game.tilesSheet.getSprite(48, 16, 16, 16);
	
	public static BufferedImage spike = Game.tilesSheet.getSprite(0, 48, 16, 16);
	
	public boolean trapCooldown = true;;
	public int trapTick;
	
	public TrapTile(BufferedImage sprite, int x, int y, int Width, int Height) {
		super(sprite, x, y, Width, Height);
	}
	
	public void damage() {
		
		Rectangle entityCollider = new Rectangle(
				(int)(Game.player.getX() + Game.player.getMaskX()),
				(int)(Game.player.getY() + Game.player.getMaskY()),
				(int)Game.player.getMaskWidth(),
				(int)Game.player.getMaskHeight());
		Rectangle tileCollider = new Rectangle(
				(int)(this.getX() + this.getMaskX()),
				(int)(this.getY() + this.getMaskY()),
				(int)this.getMaskWidth(),
				(int)this.getMaskHeight() + 1);
		
		if(tileCollider.intersects(entityCollider)) {
			Game.player.setY((int)Game.player.getY() + 1);
			Game.player.getSoul();
		}
		
	}
	
	public void cooldownController() {
	}
	
	public void update() {

		damage();

	}
		
}
