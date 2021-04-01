package nl.han.ica.oopg.screens;

import java.util.ArrayList;
import nl.han.ica.oopg.sound.Sound;
import nl.han.ica.oopg.engine.*;
import nl.han.ica.oopg.game.Spel;

public class InstellingenScherm {
	public boolean isGeluidAan;
	public boolean isMuziekAan;
	public ArrayList<Knop> knoppen = new ArrayList<Knop>();
	
	private final int xGeluidKnop = 260;
	private final int yGeluidKnop = 350;
	private final int xMuziekKnop = 260;
	private final int yMuziekKnop = 430;
	private final int xBackKnop = 290;
	private final int yBackKnop = 508;
	
	private final int buttonWidth = 300;
	private final int buttonHeight = 120;

	public InstellingenScherm(Spel spel) {
		
		knoppen.add(new Knop(xGeluidKnop, yGeluidKnop, buttonWidth, buttonHeight, "Zet Geluid " + !isGeluidAan, spel));
		knoppen.add(new Knop(xMuziekKnop, yMuziekKnop, buttonWidth, buttonHeight, "Zet Muziek " + !isMuziekAan, spel));
		knoppen.add(new Knop(xBackKnop, yBackKnop, buttonWidth, buttonHeight, "Ga Terug", spel));
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

	public Knop getGeluidKnop() {
		return knoppen.get(0);
	}

	public Knop getMuziekKnop() {
		return knoppen.get(1);
	}

	public Knop getBackKnop() {
		return knoppen.get(2);
	}
	
	public void soundHandler(GameEngine engine, Sound sound) {
		if (!isMuziekAan) {
			if (!sound.isPlaying()) {
				sound.play(); // maak looping als hij na 1x stopt (nog niet getest)
				setMuziek();
			}
		} else {
			sound.pause();
			setMuziek();

		}
		getMuziekKnop().setText("Zet Muziek " + !isMuziekAan);

	}
}
