package com.GupiGames.Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.GupiGames.FSM.Player.PlayerState;
import com.GupiGames.FSM.Player.Player_Climb;
import com.GupiGames.FSM.Player.Player_Dead;
import com.GupiGames.FSM.Player.Player_Idle;
import com.GupiGames.FSM.Player.Player_Plant;
import com.GupiGames.Graphics.PlayerAni;

public class Player extends Entity{

	private static final long serialVersionUID = -3809241077021378000L;

	public PlayerState playerState;
	
	public static boolean up, down, left, right, enter, enterMenu, plant;
	public static boolean jumpCooldown = true;
	public static boolean attackCooldown = true;
	public BufferedImage[] currentAni;
	
	public int jumpTick = 0;
	public int attackTick = 0;

	public boolean bigSoul = false;
	
	public Player(BufferedImage sprite, int x, int y, int Width, int Height) {
		super(sprite, x, y, Width, Height);
		
		maskWidth = 8;  maskHeight = 15;
		maskX = 4 ; maskY = 1;
		
		playerState = new Player_Idle();
		currentAni = PlayerAni.idle_Right;
		side = "Right";
	}
	
	public void playerController() {
		playerState.feedback();
		playerState.dead();
		playerState.plant();
		playerState.interact();
		playerState.climb();
		playerState.idle();
		playerState.walk();
		playerState.fall();
		playerState.jump();
	}
	
	public void cooldownController() {
		
		if(!jumpCooldown) {
			jumpTick++;
			if(jumpTick >= 30) {
				jumpCooldown = true;
				jumpTick = 0;
			}
		}

		
		if(!attackCooldown) {
			attackTick++;
			if(attackTick >= 60 && soul > 0) {
				attackCooldown = true;
				attackTick = 0;
			}
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
	
	public void update() {
		if(!(playerState instanceof Player_Climb) && !(playerState instanceof Player_Plant) && !(playerState instanceof Player_Dead)) {
			animation();
		}
		playerController();
		cooldownController();
	}
	
	public void render(Graphics g) {
		g.drawImage(currentAni[curAnimation], x, y, null);
	}	
	
}
