package nl.han.ica.oopg.screens;

import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.sound.Sound;

public class SoundHandler {
	
	public boolean isGeluidAan = false;
	public boolean isMuziekAan = false;
	public String muziekTekst;
	public String geluidTekst;
	
	public SoundHandler() {
		muziekTekst = ("Zet Muziek " + !isMuziekAan);
		geluidTekst = ("Zet Geluid " + !isGeluidAan);
	}
	
	
	public void playMusic(GameEngine engine, Sound sound) {
		if (!isMuziekAan) {
			if (!sound.isPlaying()) {
				sound.play(); // maak looping als hij na 1x stopt (nog niet getest)
				setMuziek();
			}
		} else {
			sound.pause();
			setMuziek();

		}
		muziekTekst = ("Zet Muziek " + !isMuziekAan);

	}
	public void setGeluid() {
		isGeluidAan = !isGeluidAan;
	}

	public void setMuziek() {
		isMuziekAan = !isMuziekAan;
	}

	public boolean getGeluid() {
		return isGeluidAan;
	}

	public boolean getMuziek() {
		return isMuziekAan;
	}

}
