package com.GupiGames.World;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.GupiGames.main.Game;

public class pressurePlates extends Tile{
	
	public String State = "Normal";
	public int ID;
	
	public static BufferedImage normal = Game.tilesSheet.getSprite(0, 64, 16, 16);; 
	public static BufferedImage pressed = Game.tilesSheet.getSprite(16, 64, 16, 16);;
	
	public pressurePlates(BufferedImage sprite, int x, int y, int Width, int Height) {
		super(sprite, x, y, Width, Height);
		im();
	}
	
	public void im() {
		
		for(int index = 0; index < Game.pressurePlates.size(); index++) {
			if(Game.pressurePlates.get(index) == this) {
				ID = index;
			}
		}
	}
	
	public void openDoor() {
		
		for(int index = 0; index < World.tiles.length; index++) {
			if(Game.doors.get(ID) == World.tiles[index]) {
				
				if(State == "Pressed") {
					World.tiles[index].State = "isOpen";
				}
				else {
					World.tiles[index].State = "Close";
				}
				
			}
		}
		
		
	}
	
	public void isPressed() {
		
		Rectangle entitCollider = new Rectangle(
				(int)(Game.player.getX() + Game.player.getMaskX()),
				(int)(Game.player.getY() + Game.player.getMaskY()),
				(int)Game.player.getMaskWidth(),
				(int)Game.player.getMaskHeight());
		Rectangle tilCollider = new Rectangle(
				(int)(this.getX() + this.getMaskX()),
				(int)(this.getY() + this.getMaskY()),
				(int)this.getMaskWidth(),
				(int)this.getMaskHeight());
		
		if(tilCollider.intersects(entitCollider)) {
			State = "Pressed";
		}
		else {
			State = "Normal";
		}

		for(int index = 0; index < Game.entities.size(); index++) {
			
			Rectangle entityCollider = new Rectangle(
					(int)(Game.entities.get(index).getX() + Game.entities.get(index).getMaskX()),
					(int)(Game.entities.get(index).getY() + Game.entities.get(index).getMaskY()),
					(int)Game.entities.get(index).getMaskWidth(),
					(int)Game.entities.get(index).getMaskHeight());
			
			Rectangle tileCollider = new Rectangle(
					(int)(this.getX() + this.getMaskX()),
					(int)(this.getY() + this.getMaskY()),
					(int)this.getMaskWidth(),
					(int)this.getMaskHeight());
			
			if(tileCollider.intersects(entityCollider)) {
				State = "Pressed";
			}
		}
	}
	
	public void update() {
		isPressed();
		openDoor();
	}
	
	public void render(Graphics g) {
		if(State == "Normal") {
			g.drawImage(normal, x, y, null);
		}
		else {
			g.drawImage(pressed, x, y, null);
		}
	}

}
