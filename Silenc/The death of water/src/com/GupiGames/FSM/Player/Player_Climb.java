package com.GupiGames.FSM.Player;

import com.GupiGames.Entities.Player;
import com.GupiGames.Entities.Tree;
import com.GupiGames.Graphics.PlayerAni;
import com.GupiGames.World.World;
import com.GupiGames.main.Game;

public class Player_Climb implements PlayerState {

	public void interact() {
	}

	public void climb() {
		Game.player.currentAni = PlayerAni.climb;
		if(Player.right && World.isFree(Game.player, 1, 0)) {
			Game.player.animation();
			Game.player.setX((int)Game.player.getX() + 1);
		}
		else if(Player.left && World.isFree(Game.player, -1, 0)) {
			Game.player.animation();
			Game.player.setX((int)Game.player.getX() - 1);
		}
		if(Player.down && World.isFree(Game.player, 0, 1)) {
			Game.player.animation();
			Game.player.setY((int)Game.player.getY() + 1);
		}
		else if(Player.up && World.isFree(Game.player, 0, -1)) {
			Game.player.animation();
			Game.player.setY((int)Game.player.getY() -1);
		}
		
	}

	public void idle() {
		if(!(Tree.isClimb())) {
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
