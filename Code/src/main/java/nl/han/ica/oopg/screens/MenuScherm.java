package nl.han.ica.oopg.screens;

import java.util.ArrayList;

import nl.han.ica.oopg.dashboard.*;
import nl.han.ica.oopg.game.Spel;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.TextObject;

public class MenuScherm {
	private TextObject dashboardText;
	public Knop instellingenKnop;
	public Knop startKnop;
	public ArrayList<Knop> knoppen = new ArrayList<Knop>();
	private Spel spel;
	
	private final int xStartKnop = 325;
	private final int yStartKnop = 430;
	private final int xInstellingenKnop = 280;
	private final int yInstellingenKnop = 350;
	private final int buttonWidth = 300;
	private final int buttonHeight = 100;

	private int fontSize, fontSizeInstellingen;

	public MenuScherm(Spel spel) {
		this.spel = spel;
		
		fontSize = 20;
		fontSizeInstellingen = 15;
		
		createInstellingenKnop();
		createStartKnop();
		
		knoppen.add(instellingenKnop);
		knoppen.add(startKnop);
		
		
		
	}


	public Knop getInstellingenKnop() {
		return instellingenKnop;
	}
	public Knop getStartKnop() {
		return startKnop;
	}
	public ArrayList<Knop> getKnoppen(){
		return knoppen;
	}

	public void createInstellingenKnop() {
		instellingenKnop = new Knop(xInstellingenKnop, yInstellingenKnop, buttonWidth, buttonHeight, "Instellingen", spel);
	}

	public void createStartKnop() {
		startKnop = new Knop(xStartKnop, yStartKnop, buttonWidth, buttonHeight, "Start", spel);
	}
}
