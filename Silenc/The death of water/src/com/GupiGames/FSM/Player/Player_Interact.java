package com.GupiGames.FSM.Player;

import com.GupiGames.Entities.NoteOfSouls;
import com.GupiGames.Entities.Player;
import com.GupiGames.Entities.Tree;
import com.GupiGames.Graphics.PlayerAni;
import com.GupiGames.main.Game;

public class Player_Interact implements PlayerState {

	public int tick = 0;

	public void interact() {
		
		if(Player.side == "Right" && Player.attackCooldown) {
			Game.player.soul--;
			Game.player.currentAni = PlayerAni.interact_Right;
			Game.souls.add(new NoteOfSouls(Game.playerSheet.getSprite(0, 0, 16, 16), (int)(Game.player.getX()) + 8, (int)(Game.player.getY()), 16, 16, 1));
			Player.attackCooldown = false;
		}
		else if(Player.side == "Left" && Player.attackCooldown) {
			Game.player.soul--;
			Game.player.currentAni = PlayerAni.interact_Left;
			Player.attackCooldown = false;
			Game.souls.add(new NoteOfSouls(Game.playerSheet.getSprite(0, 0, 16, 16), (int)(Game.player.getX()) - 8, (int)(Game.player.getY()), 16, 16, -1));
		}
		
		tick++;
	}
	
	public void climb() {
		if(Tree.isClimb()) {
			PlayerState.setState(new Player_Climb());
		}
	}
	
	public void idle() {
		if(tick >= 15) {
			PlayerState.setState(new Player_Idle());
		}
	}

	public void walk() {}

	public void jump() {}

	public void fall() {}

	public void feedback() {}

	public void plant() {}

	public void dead() {}

}
