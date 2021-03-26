package nl.han.ica.oopg;

import nl.han.ica.oopg.screens.Knop;

public class MenuScherm {
	private final int xInstellingenKnop = 0;
	private final int yInstellingenKnop = 0;
	private final int buttonWidth = 100;
	private final int buttonHeight = 100;
	private Knop instellingenKnop;
	private Knop spelKnop;
	
	public MenuScherm(){
		
		
	}
	
	public void tekenScherm() {
		
	}
	
	public void gaNaarSpelScherm() {
		
	}
	
	public void gaNaarInstellingenScherm() {
		
	}
	public void createInstellingenKnop() {
		instellingenKnop = new Knop(xInstellingenKnop, yInstellingenKnop, buttonWidth, buttonHeight,
				"Instellingen");
		instellingenKnop.setForeColor(0, 0, 255, 255);
//		instellingenKnop.
	}
	
}
