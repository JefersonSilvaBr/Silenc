package com.GupiGames.FSM;

import com.GupiGames.Entities.Player;
import com.GupiGames.Entities.Tree;
import com.GupiGames.Graphics.PlayerAni;
import com.GupiGames.main.Game;

public class Player_Feedback implements PlayerState{
	public int tick = 0;
	
	public void interact() {}
	
	public void climb() {
		if(Tree.isClimb()) {
			PlayerState.setState(new Player_Climb());
		}
	}

	public void idle() {
		tick++;
		if(tick >= 10) {
			PlayerState.setState(new Player_Idle());
		}
	}

	public void walk() {}

	public void jump() {}

	public void fall() {}

	public void feedback() {

		if(Player.side == "Right") {
			Game.player.currentAni = PlayerAni.feedback_Right;
		}
		else if(Player.side == "Left") {
			Game.player.currentAni = PlayerAni.feedback_Left;
		}		
		
	}

	public void plant() {	
	}

	public void dead() {
		
		if(Game.player.soul <= 0  && Game.player.bigSoul == false) {
			Game.gameState = new Game_GameOver();
		}
		else {
			System.out.println("getSoul");
			PlayerState.setState(new Player_Dead());
		}
	}

}
