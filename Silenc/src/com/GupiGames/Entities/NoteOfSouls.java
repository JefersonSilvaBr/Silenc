package com.GupiGames.Entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.GupiGames.World.World;
import com.GupiGames.main.Game;

public class NoteOfSouls extends Entity{
	
	public BufferedImage[] currentAni;
	public BufferedImage[] note1;
	public BufferedImage[] note2;

	public int directionX;
	
	public NoteOfSouls(BufferedImage sprite, int x, int y, int Width, int Height, int dic) {
		super(sprite, x, y, Width, Height);
		directionX = dic;
		initSprite();
	}

	public void moves() {
		x+= 1*directionX;
	}
	public void initSprite() {
		
		note1 = new BufferedImage[2];
		note1[0] = Game.playerSheet.getSprite(0, 16*4, 16, 16);
		note1[1] = Game.playerSheet.getSprite(16, 16*4, 16, 16);
		
		note2 = new BufferedImage[2];
		note2[0] = Game.playerSheet.getSprite(32, 16*4, 16, 16);
		note2[1] = Game.playerSheet.getSprite(48, 16*4, 16, 16);
		
		currentAni = note1;
		if(Game.rand.nextInt(6) > 2) {
			currentAni = note2;
		}
		
	}
	
	public void animation() {
		frame++;
		if(frame >= maxFrame) {
			curAnimation++;
			frame = 0;
		}
		if(curAnimation >= 2) {
			curAnimation = 0;
		}
	}
	
	public void collider() {
		maskWidth = 8; maskHeight = 8;
		maskX = 3; maskY = 3;
		if(!(World.isFree(this, 0, 0))) {
			directionX*=-1;
		}
	}
	
	public void wasConsumed() {
		
		Rectangle soulCollider = new Rectangle((int)this.getX(), (int)this.getY(),
				(int)this.getMaskWidth(), (int)this.getMaskHeight());
		
		Rectangle playerCollider = new Rectangle((int)Game.player.getX(), (int)Game.player.getY(),
				(int)Game.player.getMaskWidth(), (int)Game.player.getMaskHeight());
		
		for(int index = 0; index < Game.entities.size(); index++) {
			Entity entity = Game.entities.get(index);
			Rectangle entityCollider = new Rectangle((int)entity.getX(), (int)entity.getY(),
					(int)entity.getWidth(), (int)entity.getHeight());
			
			if(soulCollider.intersects(entityCollider)) {
				
			}
			
		}
		
		if(soulCollider.intersects(playerCollider)) {
			System.out.println("soul");
			Game.player.soul++;
			selfDestroy();
		}
		
	}
	
	public void selfDestroy() {
		Game.souls.remove(this);
		return;
	}
	
	public void update() {
		animation();
		moves();
		collider();
		wasConsumed();
	}
	
	public void render(Graphics g) {
		g.drawImage(currentAni[curAnimation], x, y, null);
	}
	
}
