package nl.han.ica.oopg.game;
import nl.han.ica.oopg.dashboard.*;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.TextObject;


public class MenuScherm {
	private TextObject dashboardText;
	private final int xSpelKnop = 0;
	private final int ySpelKnop = 0;
	private final int xInstellingenKnop = 200;
	private final int yInstellingenKnop = 200;
	private final int buttonWidth = 100;
	private final int buttonHeight = 100;
	
	private int fontSize, fontSizeInstellingen;
	
	
	public MenuScherm(){
		fontSize = 20;
		fontSizeInstellingen = 15;
	}
	
	public Dashboard createSpelStartKnop() {
		Dashboard dashboard = new Dashboard(xSpelKnop, ySpelKnop, buttonWidth, buttonHeight);
        dashboardText = new TextObject(" Start Spel ", fontSize);
        dashboard.addGameObject(dashboardText);
		return dashboard;
	}
	
	public Dashboard createInstellingenKnop() {
		Dashboard dashboard = new Dashboard(xInstellingenKnop, yInstellingenKnop, buttonWidth, buttonHeight);
        dashboardText = new TextObject(" Start Spel ", fontSize);
        dashboard.addGameObject(dashboardText);
		return dashboard;
	}
	
	public Sprite createInstructieSheetBG() {
		Sprite instructieSheetBG = new Sprite(Spel.MEDIA_URL.concat("instructieSheet.jpg"));
		return instructieSheetBG;
	}
	
	public TextObject[] createInstructieSheet() {
		TextObject instructieSheet[] = {
			new TextObject("Instructies!", fontSize),
			new TextObject("Instructie1", fontSizeInstellingen),
			new TextObject("Instructie2", fontSizeInstellingen),
			new TextObject("Instructie3", fontSizeInstellingen),
			new TextObject("Instructie4", fontSizeInstellingen),
		};
		return instructieSheet;
	
	}
	
}
