package com.GupiGames.FSM;

import com.GupiGames.Entities.Player;
import com.GupiGames.Entities.Tree;
import com.GupiGames.Graphics.PlayerAni;
import com.GupiGames.World.World;
import com.GupiGames.main.Game;

public class Player_Walk implements PlayerState {

	public void interact() {
		if(Player.enter && Player.attackCooldown == true && Game.player.soul > 0) {
			if(Player.side == "Right" && World.isFree(Game.player, 10, 0)) {
				PlayerState.setState(new Player_Interact());
			}
			else if(Player.side == "Left" && World.isFree(Game.player, -10, 0)) {
				PlayerState.setState(new Player_Interact());
			}	
		}
	}
	
	public void climb() {
		if(Tree.isClimb()) {
			PlayerState.setState(new Player_Climb());
		}
	}

	public void idle() {
		if(!(Player.right) && !(Player.left)) {
			PlayerState.setState(new Player_Idle());
		}
	}

	public void walk() {
		if(Player.right && World.isFree(Game.player, 1, 0)) {
			Game.player.currentAni = PlayerAni.run_Right;
			Game.player.setX((int)Game.player.getX() + 1);
		}
		else if(Player.left && World.isFree(Game.player, -1, 0)) {
			Game.player.currentAni = PlayerAni.run_Left;
			Game.player.setX((int)Game.player.getX() - 1);
		}
	}

	public void jump() {
		if(Player.up && World.isFree(Game.player, 0, -3) && Player.jumpCooldown == true) {
			PlayerState.setState(new Player_Jump());
		}

	}

	public void fall() {
		if(World.isFall(Game.player, 1)) {
			PlayerState.setState(new Player_Fall());
		}
	}

	public void feedback() {}

	@Override
	public void plant() {}

	@Override
	public void dead() {
		// TODO Auto-generated method stub
		
	}

}
