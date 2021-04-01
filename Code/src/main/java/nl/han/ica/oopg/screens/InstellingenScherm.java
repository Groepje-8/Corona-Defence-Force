package nl.han.ica.oopg.screens;

import java.util.ArrayList;
import java.util.Objects;

import nl.han.ica.oopg.dashboard.*;
import nl.han.ica.oopg.sound.Sound;
import nl.han.ica.oopg.engine.*;
import nl.han.ica.oopg.objects.*;

public class InstellingenScherm {
	public boolean isGeluidAan;
	public boolean isMuziekAan;
	public Knop muziekKnop;
	public Knop geluidsKnop;
	public Knop backKnop;
	public ArrayList<Knop> knoppen = new ArrayList<Knop>();
	private final int xGeluidKnop = 260;
	private final int yGeluidKnop = 350;
	private final int xMuziekKnop = 260;
	private final int yMuziekKnop = 430;
	private final int xBackKnop = 290;
	private final int yBackKnop = 508;
	private final int buttonWidth = 300;
	private final int buttonHeight = 120;
	private int fontSize;

	public InstellingenScherm() {
		fontSize = 20;

		createMuziekKnop();
		createGeluidKnop();
		createBackKnop();
		knoppen.add(geluidsKnop);
		knoppen.add(muziekKnop);
		knoppen.add(backKnop);
		// createDashboard();

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

	public void createGeluidKnop() {
		geluidsKnop = new Knop(xGeluidKnop, yGeluidKnop, buttonWidth, buttonHeight,
				"Zet Geluid " + Boolean.toString(!isGeluidAan));
		muziekKnop.setForeColor(146, 20, 12, 255);
	}

	public void createMuziekKnop() {

		muziekKnop = new Knop(xMuziekKnop, yMuziekKnop, buttonWidth, buttonHeight,
				"Zet Muziek " + Boolean.toString(!isMuziekAan));
		muziekKnop.setForeColor(146, 20, 12, 255);

	}

	public void createBackKnop() {
		backKnop = new Knop(xBackKnop, yBackKnop, buttonWidth, buttonHeight, "Ga Terug");
		backKnop.setForeColor(146, 20, 12, 255);
	}

	public GameObject getMuziekKnop() {
		return muziekKnop;
	}

	public ArrayList<Knop> getKnoppen() {
		return knoppen;
	}

	public void soundHandler(GameEngine engine, Sound sound) {
		if (!isMuziekAan) {
			if (!sound.isPlaying()) {
				sound.play();// maak looping als hij na 1x stopt (nog niet getest)
				setMuziek();
			}
		} else {
			sound.pause();
			setMuziek();

		}
		muziekKnop.setText("Zet Muziek " + Boolean.toString(!isMuziekAan));

	}
}
