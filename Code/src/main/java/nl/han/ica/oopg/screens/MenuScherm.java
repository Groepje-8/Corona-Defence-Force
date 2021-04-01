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
	//private Knoppen menuDashboard;
	private final int xStartKnop = 325;
	private final int yStartKnop = 430;
	private final int xInstellingenKnop = 280;
	private final int yInstellingenKnop = 350;
	private final int buttonWidth = 300;
	private final int buttonHeight = 100;

	private int fontSize, fontSizeInstellingen;

	public MenuScherm() {
		fontSize = 20;
		fontSizeInstellingen = 15;
		createInstellingenKnop();
		createStartKnop();
		knoppen.add(instellingenKnop);
		knoppen.add(startKnop);
		//createDashboard();
	}

//	public void gaNaarSpelScherm() {
//
//	}
//
//	public void gaNaarInstellingenScherm() {
//
//	}

	//public Knoppen getDashboard() {
	//	return menuDashboard;
	//}
//
//	public void createDashboard() {
//		menuDashboard = new Knoppen(0, 0, 1280, 600);
//		menuDashboard.addKnop(instellingenKnop, 100);
//		menuDashboard.addKnop(startKnop, 100);
//
//	}
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

		instellingenKnop = new Knop(xInstellingenKnop, yInstellingenKnop, buttonWidth, buttonHeight, "Instellingen");

	}

	public void createStartKnop() {
		startKnop = new Knop(xStartKnop, yStartKnop, buttonWidth, buttonHeight, "Start");


	}

	public Sprite createInstructieSheetBG() {
		Sprite instructieSheetBG = new Sprite(Spel.MEDIA_URL.concat("instructieSheet.jpg"));
		return instructieSheetBG;
	}

	public TextObject[] createInstructieSheet() {
		TextObject instructieSheet[] = { new TextObject("Instructies!", fontSize),
				new TextObject("Instructie1", fontSizeInstellingen),
				new TextObject("Instructie2", fontSizeInstellingen),
				new TextObject("Instructie3", fontSizeInstellingen),
				new TextObject("Instructie4", fontSizeInstellingen), };
		return instructieSheet;

	}

}
