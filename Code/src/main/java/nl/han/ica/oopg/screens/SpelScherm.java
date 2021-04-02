package nl.han.ica.oopg.screens;

import java.util.ArrayList;

import nl.han.ica.oopg.game.Spel;

public class SpelScherm {
	private ArrayList<Knop> knoppen = new ArrayList<>();
	
	private final int xPauzeKnop = 10;
	private final int yPauzeKnop = 640;
	
	private final int buttonWidth = 80;
	private final int buttonHeight = 80;
	
	public SpelScherm(Spel spel) {
		knoppen.add(new Knop(xPauzeKnop, yPauzeKnop, buttonWidth, buttonHeight, "", spel));
	}
	
	public Knop getPauseKnop() {
		return knoppen.get(0);
	}
}
