package com.GupiGames.Entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.GupiGames.World.World;
import com.GupiGames.main.Game;

public class Tree extends Entity{
	
	 public boolean start = true;
	 public int Stage = 0;
	 public int tick = 0;
	 
	 public static BufferedImage treeTop = Game.entitiesSheet.getSprite(0, 0, 16, 16);
	 public static BufferedImage treeMid = Game.entitiesSheet.getSprite(0, 16, 16, 16);

	public Tree(BufferedImage sprite, int x, int y, int Width, int Height, String Stat) {
		super(sprite, x, y, Width, Height);
		maskX = 6;
		maskWidth = 4; maskHeight = 16;
		this.State = Stat;
	}
	
	public void growing() {
		
		if(start) {
			if(!(World.isFree(this, 0, -16))) {
				start = false;
			}
			tick++;
			if(Stage == 1) {
				Game.entities.add(new Tree(Game.playerSheet.getSprite(0, 0, 16, 16), x, y-16, 16, 16, "visible"));
				start = false;
			}
			if(tick >= 30) {
				Stage++;
				curAnimation++;
				tick = 0;
			}
		}
		
	}
	
	public static boolean isClimb() {
		
		Rectangle playerCollider = new Rectangle(
				(int)(Game.player.getX() + Game.player.getMaskX()),
				(int)(Game.player.getY() + Game.player.getMaskY()),
				(int)Game.player.getMaskWidth(),
				(int)Game.player.getMaskHeight());
			
		for(int index = 0; index < Game.entities.size(); index++) {
				
			if(Game.entities.get(index) instanceof Tree) {
				
				Rectangle entityCollider = new Rectangle(
						(int)(Game.entities.get(index).getX() + Game.entities.get(index).getMaskX()),
						(int)(Game.entities.get(index).getY() + Game.entities.get(index).getMaskY()),
						(int)Game.entities.get(index).getMaskWidth(),
						(int)Game.entities.get(index).getMaskHeight());
					
				if(playerCollider.intersects(entityCollider)) {
					return true;	
				}
			}		
		}
		return false;
	}
	
	public void update() {
		growing();	
	}
	
	public void render(Graphics g) {
		
		if(Stage == 0) {
			g.drawImage(treeTop, x, y, null);
		}
		else if(Stage == 1) {
			g.drawImage(treeMid, x, y, null);
		}
		
	}

}
