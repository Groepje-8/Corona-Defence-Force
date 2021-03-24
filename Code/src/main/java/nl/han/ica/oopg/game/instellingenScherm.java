package nl.han.ica.oopg.game;

import java.util.Objects;

import nl.han.ica.oopg.dashboard.*;
import nl.han.ica.oopg.sound.Sound;
import nl.han.ica.oopg.engine.*;
import nl.han.ica.oopg.objects.*;

public class instellingenScherm {
	public boolean isGeluidAan;
	public boolean isMuziekAan = true;
	public Knoppen instellingenDashboard;
	public Knop muziekKnop;
	public Knop geluidsKnop;
	private Sound backgroundSound;
	private final int xGeluidKnop = 590;
	private final int yGeluidKnop = 150;
	private final int xMuziekKnop = 590;
	private final int yMuziekKnop = 200;
	private final int buttonWidth = 100;
	private final int buttonHeight = 100;
	private int fontSize;

	public instellingenScherm(GameEngine engine) {
		fontSize = 20;
		backgroundSound = new Sound(engine, "/media/8bitmusic.mp3");
		createMuziekKnop();
		createGeluidKnop();
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
				"Geluid is " + Boolean.toString(isGeluidAan));
		muziekKnop.setForeColor(146, 20, 12, 255);
	}

	public void createMuziekKnop() {

		muziekKnop = new Knop(xMuziekKnop, yMuziekKnop, buttonWidth, buttonHeight,
				"Muziek is " + Boolean.toString(isMuziekAan));
		muziekKnop.setForeColor(146, 20, 12, 255);

	}

	public GameObject getMuziekKnop() {
		return muziekKnop;
	}

	public void createDashboard() {
		instellingenDashboard = new Knoppen(0, 0, 1280, 600);
		instellingenDashboard.addKnop(muziekKnop,100);
		instellingenDashboard.addKnop(geluidsKnop,100);

	}

	public Knoppen getDashboard() {
		return instellingenDashboard;
	}

	public void initializeSound(GameEngine engine) {
		if (isMuziekAan) {
			if (!backgroundSound.isPlaying()) {
				backgroundSound.loop(-1);
				setMuziek();
			}
		} else {
			backgroundSound.pause();
			backgroundSound.rewind();
			setMuziek();

		}

	}
}
