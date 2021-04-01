package nl.han.ica.oopg.screens;

import java.util.ArrayList;

import nl.han.ica.oopg.game.Spel;

public class SpelScherm {
	private ArrayList<Knop> knoppen = new ArrayList<>();
	
	private final int xPauzeKnop = 10;
	private final int xFastForwardKnop = 90;
	private final int yKnoppen = 640;
	
	private final int buttonWidth = 80;
	private final int buttonHeight = 80;
	
	public SpelScherm(Spel spel) {
		knoppen.add(new Knop(xPauzeKnop, yKnoppen, buttonWidth, buttonHeight, "", spel));
		knoppen.add(new Knop(xFastForwardKnop, yKnoppen, buttonWidth, buttonHeight, "", spel));
	}
	
	public Knop getPauseKnop() {
		return knoppen.get(0);
	}
	
	public Knop getFastForwardKnop() {
		return knoppen.get(1);
	}
}
