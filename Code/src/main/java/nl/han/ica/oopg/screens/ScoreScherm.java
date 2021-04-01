package nl.han.ica.oopg.screens;

import java.util.ArrayList;

import nl.han.ica.oopg.game.Spel;
import nl.han.ica.oopg.objects.*;

public class ScoreScherm {
	private ArrayList<Knop> knoppen = new ArrayList<>();
	
	private final int xTerugKnop = 300;
	private final int yTerugKnop = 590;
	private final int buttonWidth = 300;
	private final int buttonHeight = 120;
	
	public ScoreScherm(Spel spel){
		knoppen.add(new Knop(xTerugKnop, yTerugKnop, buttonWidth, buttonHeight, "Ga Terug", spel));
	}
	
	public Knop getTerugKnop() {
		return knoppen.get(0);
	}

}
