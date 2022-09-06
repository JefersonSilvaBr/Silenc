package com.GupiGames.Sounds;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	Clip clip;
	URL soundURL[] = new URL[30];
	
	public Sound() {
		
		soundURL[0] = getClass().getResource("/Sound/BackgroundSound.wav");
		soundURL[1] = getClass().getResource("/Sound/BackgrounSound.wav");
		
	}
	
	public void setFile(int file) {
		try {
			
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[file]);
			clip  = AudioSystem.getClip();
			clip.open(ais);
			
		}catch(Exception e) {}
	}
	public void play(int file) {
		setFile(file);
		clip.start();
	}
	public void loop(int file) {
		setFile(file);
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void stop() {
		clip.stop();
	}
}
