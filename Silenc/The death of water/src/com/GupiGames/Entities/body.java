package com.GupiGames.Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class body extends Player{

	private static final long serialVersionUID = -6544084330310573693L;

	public body(BufferedImage sprite, int x, int y, int Width, int Height) {
		super(sprite, x, y, Width, Height);
	}
	
	public void update() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, x, y, null);
	}

}
