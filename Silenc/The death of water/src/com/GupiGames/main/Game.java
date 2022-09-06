package com.GupiGames.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import com.GupiGames.Entities.Entity;
import com.GupiGames.Entities.Player;
import com.GupiGames.FSM.Game.GameState;
import com.GupiGames.FSM.Game.Game_Credits;
import com.GupiGames.FSM.Game.Game_Menu;
import com.GupiGames.FSM.Game.Game_Play;
import com.GupiGames.Graphics.InitAni;
import com.GupiGames.Graphics.Spritesheets;
import com.GupiGames.Sounds.Sound;
import com.GupiGames.World.Tile;
import com.GupiGames.World.World;

public class Game extends Canvas implements Runnable, KeyListener{

	private static final long serialVersionUID = 1L;
	public static GameState gameState = new Game_Menu();
	public static Spritesheets icon = new Spritesheets("/Image/Silenc.png");
	
	public static String State = "";
	public static Random rand = new Random();
	public static int currentMap = 0;
	public static boolean tutorial = true;
	
	public boolean isRunning;
	public Thread thread;
	
	public static JFrame frame;
	public static final int WIDTH = 240;
	public static final int HEIGHT = 160;
	public static final int SCALE = 4;
	public static final int pixels = 32;
	
	public static BufferedImage background;	
	public static Spritesheets uiSheet;	
	public static Spritesheets keysSheet;	
	public static Spritesheets tilesSheet;	
	public static Spritesheets entitiesSheet;	
	public static World map;
	public static Sound sound= new Sound();
	
	public static InitAni initAni;
	
	public static Spritesheets playerSheet;	
	public static Player player;
	
	public static List<Entity> entities;
	public static List<Entity> souls;
	
	public static List<Tile> pressurePlates;
	public static List<Tile> doors;
	public static boolean escape;
	public static Game_Play save;
	
	public static void main(String[] args) {
		
		Game game = new Game();
		game.start();
		
	}
	
	
	public Game() {
		sound = new Sound();
		sound.loop(0);
		setPreferredSize(new Dimension( WIDTH*SCALE, HEIGHT*SCALE));
		addKeyListener(this);
		initFrame();
		initSpritesheets();
		initObjects();
		initMaps();
		
	}
	
	public void initFrame() {
		
		frame = new JFrame();
		frame.add(this);
		frame.setIconImage(icon.getSprite(0, 0, 608, 608));
		frame.setResizable(false);
		frame.setTitle("Silenc");
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	
	public void initSpritesheets() {
		background = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		tilesSheet = new Spritesheets("/Image/tilesAgostJam.png");
		entitiesSheet = new Spritesheets("/Image/itensJamAgost.png");
		playerSheet = new Spritesheets("/Image/waterPlayer-Sheet.png");
		uiSheet = new Spritesheets("/Image/UI.png");
		keysSheet = new Spritesheets("/Image/tutorial.png");
		
		initAni = new InitAni();
	}
	
	
	public void initObjects() {	
		
		pressurePlates = new ArrayList<Tile>();
		doors = new ArrayList<Tile>();
		player = new Player(playerSheet.getSprite(0, 0, 16, 16), 16, 16, 16, 16);
		entities = new ArrayList<Entity>();
		souls = new ArrayList<Entity>();
	}
	
	
	public static void initMaps() {
		if(State == "nextLevel" && currentMap < 6) {
			World.reset();
			currentMap++;
			map = new World("/Map/mapJam-0" + (currentMap) + ".png");
			gameState = new Game_Play();
			State = "run";
		}
		else if(State == "nextLevel" && currentMap == 6) {
			gameState = new  Game_Credits();
			State = "run";
		}
	}
	
	
	public synchronized void start() {
		
		Thread thread = new Thread(this);
		thread.start();
		
		isRunning = true;
		
	}

	public synchronized void stop() {
		
		isRunning = false;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void applySave(String str) {
		String[] spl = str.split("/");
		for(int i = 0; i < spl.length; i++) {
			String[] spl2 = spl[i].split(":");
			switch(spl2[0])
			{
			
			case "level":
				if(Integer.parseInt(spl2[1]) != 0) {
					currentMap =  Integer.parseInt(spl2[1]) - 1;
				}
				else {
					currentMap =  Integer.parseInt(spl2[1]);
				}
				initMaps();
			break;	
			
			}
		}
	}
	
	public static String loadGame(int encode) {
		String line = "";
		File file = new File("save.txt");
		if(file.exists()) {
			try {
				String singleLine = null;
				BufferedReader reader = new BufferedReader(new FileReader("save.txt"));
				try {
					while((singleLine = reader.readLine()) != null) {
						String[] trans = singleLine.split(":");
						char[] val = trans[1].toCharArray();
						trans[1] = "";
						for(int i = 0; i < val.length; i++) {
							val[i]-=encode;
							trans[1]+=val[i];
						}
						line+=trans[0];
						line+=":";
						line+=trans[1];
						line+="/";
					}	
				}catch(IOException e) {}
			}catch(FileNotFoundException e) {}
		}
		
		return line;
	}
	
	public static void saveGame(String[] val1, int[] val2, int encode) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter("save.txt"));
		}catch(IOException e) {}
		
		for(int index = 0; index < val1.length; index++) {
			String current = val1[index];
			current+=":";
			char[] value  = Integer.toString(val2[index]).toCharArray();
			for(int i = 0; i < value.length; i++) {
				value[i]+=encode;
				current+=value[i];
			}
			try {
				writer.write(current);
				if(index < val1.length - 1) {
					writer.newLine();
				}
			}catch(IOException e) {}
		}
		try {
			writer.flush();
			writer.close();
		}catch(IOException e) {}
		
	}
	
	public void update() {
		initMaps();
		gameState.update();
	}
	
	public void render() {
		
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = background.getGraphics();
		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
		//
		gameState.render(g);
		//
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(background, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		bs.show();
		
	}

	
	public void run() {
		
		long lastTime = System.nanoTime();
		double amountOfUpdates = 60.0;
		double nanoSeconds = 1000000000 / amountOfUpdates;
		double timeToUpdate = 0;
		
		int frame = 0;
		double timer = System.currentTimeMillis();
		
		while(isRunning) {
			
			long now = System.nanoTime();
			timeToUpdate += (now - lastTime) / nanoSeconds;
			lastTime = now;
			
			if(timeToUpdate >= 1) {
				
				update();
				render();
				timeToUpdate = 0;
				frame++;
				
			}
			
			if(System.currentTimeMillis() - timer >= 10000) {
				
				System.out.println("FPS: " +  (frame/10));
				frame = 0;
				timer = System.currentTimeMillis();
				
				
			}

		}
		
		stop();

	}


	@Override
	public void keyTyped(KeyEvent e) {
	}


	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT 
				|| e.getKeyCode() == KeyEvent.VK_D) {
			Player.side = "Right";
			Player.right = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT 
				|| e.getKeyCode() == KeyEvent.VK_A) {
			Player.side = "Left";
			Player.left = true;
			Player.right = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP
				|| e.getKeyCode() == KeyEvent.VK_L 
				|| e.getKeyCode() == KeyEvent.VK_W ) {
			Player.up = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN 
				|| e.getKeyCode() == KeyEvent.VK_S) {
			Player.down = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_K) {
			Player.enter = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_E) {
			Player.plant = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			Game.escape = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			Player.enterMenu = true;
		}
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT 
				|| e.getKeyCode() == KeyEvent.VK_D) {
			Player.right = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT 
				|| e.getKeyCode() == KeyEvent.VK_A) {
			Player.left = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP
				|| e.getKeyCode() == KeyEvent.VK_L 
				|| e.getKeyCode() == KeyEvent.VK_W ) {
			Player.up = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN 
				|| e.getKeyCode() == KeyEvent.VK_S) {
			Player.down = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_K) {
			Player.enter = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_E) {
			Player.plant = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			Game.escape = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			Player.enterMenu = false;
		}
		
	}
	
}