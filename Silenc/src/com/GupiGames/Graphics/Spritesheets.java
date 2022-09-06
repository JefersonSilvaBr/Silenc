package com.GupiGames.Graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.GupiGames.main.Game;

public class Spritesheets {

	private BufferedImage spritesheets;
	
	public Spritesheets(String path) {
		try {
			spritesheets = ImageIO.read(Game.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getSprite(int x, int y, int Width, int Height) {
		return spritesheets.getSubimage(x, y, Width, Height);
	}
	
}
