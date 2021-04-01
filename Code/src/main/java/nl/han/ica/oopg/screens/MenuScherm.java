package nl.han.ica.oopg.screens;

import java.util.ArrayList;

public class MenuScherm {

	public Knop instellingenKnop;
	public Knop startKnop;
	public ArrayList<Knop> knoppen = new ArrayList<Knop>();
	// private Knoppen menuDashboard;
	private final int xStartKnop = 325;
	private final int yStartKnop = 430;
	private final int xInstellingenKnop = 280;
	private final int yInstellingenKnop = 350;
	private final int buttonWidth = 300;
	private final int buttonHeight = 100;

	public MenuScherm() {

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

	public ArrayList<Knop> getKnoppen() {
		return knoppen;
	}

	public void createInstellingenKnop() {

		instellingenKnop = new Knop(xInstellingenKnop, yInstellingenKnop, buttonWidth, buttonHeight, "Instellingen");

	}

	public void createStartKnop() {
		startKnop = new Knop(xStartKnop, yStartKnop, buttonWidth, buttonHeight, "Start");

	}

}
