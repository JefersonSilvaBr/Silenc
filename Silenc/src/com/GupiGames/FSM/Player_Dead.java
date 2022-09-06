package com.GupiGames.FSM;

import com.GupiGames.Entities.body;
import com.GupiGames.Graphics.PlayerAni;
import com.GupiGames.World.World;
import com.GupiGames.main.Game;

public class Player_Dead implements PlayerState {

	private boolean start = true;
	private int tick = 0;
	private int Stage = 0;
	
	public Player_Dead() {
		Game.player.curAnimation = 0;
	}

	public void interact() {}
	
	public void climb() {}

	public void walk() {}

	public void jump() {}

	public void fall() {
		if((World.isFall(Game.player, 1))) {
			Game.player.setY((int)Game.player.getY() + 1);
		}
	}

	public void feedback() {}

	public void plant() {}
	
	public void dead() {
		
		Game.player.currentAni = PlayerAni.dead;
		if(start) {
			tick++;
			if(Stage == 3 && tick >= 29 && tick != 30) {
				if(Game.player.soul <= 0  && Game.player.bigSoul == false) {
					System.out.println("Game_Over");
					start = false;
				}
				Game.entities.add(new body(PlayerAni.dead[3], (int) (Game.player.getX()),  (int) (Game.player.getY()), 16, 16));
				Game.player.setX(World.spawnX);
				Game.player.setY(World.spawnY);
				PlayerState.setState(new Player_Idle());
				start = false;
			}
			if(tick >= 30) { 
				Stage++;
				Game.player.curAnimation++;
				tick = 0;
			}
		}
		
	}

	@Override
	public void idle() {
		// TODO Auto-generated method stub
		
	}

}
