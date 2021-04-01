package nl.han.ica.oopg.screens;

import java.util.ArrayList;

import nl.han.ica.oopg.game.Spel;

public class MenuScherm {
	public ArrayList<Knop> knoppen = new ArrayList<Knop>();

	private final int xStartKnop = 325;
	private final int yStartKnop = 430;
	private final int xInstellingenKnop = 280;
	private final int yInstellingenKnop = 350;
	private final int buttonWidth = 300;
	private final int buttonHeight = 100;

	public MenuScherm(Spel spel) {
		knoppen.add(new Knop(xInstellingenKnop, yInstellingenKnop, buttonWidth, buttonHeight, "Instellingen", spel));
		knoppen.add(new Knop(xStartKnop, yStartKnop, buttonWidth, buttonHeight, "Start", spel));
	}

	public Knop getInstellingenKnop() {
		return knoppen.get(0);
	}

	public Knop getStartKnop() {
		return knoppen.get(1);
	}
}
