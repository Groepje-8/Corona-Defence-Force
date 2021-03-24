package nl.han.ica.oopg.game;
import nl.han.ica.oopg.dashboard.*;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.TextObject;


public class MenuScherm {
	private TextObject dashboardText;
	private Knop instellingenKnop;
	private Knoppen menuDashboard;
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
		createInstellingenKnop();
		createDashboard();
	}
	
	public void gaNaarSpelScherm() {
		
	}
	
	public void gaNaarInstellingenScherm() {
		
	}
	public Knoppen getDashboard() {
		return menuDashboard;
	}
	
	public void createDashboard() {
		menuDashboard = new Knoppen(0, 0, 1280, 600);
		menuDashboard.addKnop(instellingenKnop,100);
	

	}
	public void createInstellingenKnop() {

		instellingenKnop = new Knop(xInstellingenKnop, yInstellingenKnop, buttonWidth, buttonHeight,
				"Instellingen");
		instellingenKnop.setForeColor(146, 20, 12, 255);

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
