package com.GupiGames.FSM.Player;

import com.GupiGames.main.Game;

public interface PlayerState {

	public void plant();
	
	public void interact();
	
	public void climb();
	
	public void idle();
	
	public void walk();
	
	public void jump();
	
	public void fall();
	
	public void feedback();
	
	public void dead();
	
	public static void setState(PlayerState newState) {
		Game.player.curAnimation = 0;
		Game.player.playerState = newState;
	}
}
