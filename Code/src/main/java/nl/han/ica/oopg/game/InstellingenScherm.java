package nl.han.ica.oopg.game;

import java.util.Objects;

import nl.han.ica.oopg.dashboard.*;
import nl.han.ica.oopg.sound.Sound;
import nl.han.ica.oopg.engine.*;
import nl.han.ica.oopg.objects.*;

public class InstellingenScherm {
	public boolean isGeluidAan;
	public boolean isMuziekAan;
	public Knoppen instellingenDashboard;
	public Knop muziekKnop;
	public Knop geluidsKnop;
	public Knop backKnop;
	private final int xGeluidKnop = 570;
	private final int yGeluidKnop = 250;
	private final int xMuziekKnop = 570;
	private final int yMuziekKnop = 350;
	private final int xBackKnop = 570;
	private final int yBackKnop = 450;
	private final int buttonWidth = 100;
	private final int buttonHeight = 100;
	private int fontSize;

	public InstellingenScherm() {
		fontSize = 20;
		
		createMuziekKnop();
		createGeluidKnop();
		createBackKnop();
		createDashboard();

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

	public void createDashboard() {
		instellingenDashboard = new Knoppen(0, 0, 1280, 600);
		instellingenDashboard.addKnop(muziekKnop, 100);
		instellingenDashboard.addKnop(geluidsKnop, 100);
		instellingenDashboard.addKnop(backKnop, 100);

	}

	public Knoppen getDashboard() {
		return instellingenDashboard;
	}

	public void soundHandler(GameEngine engine,Sound sound) {
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
