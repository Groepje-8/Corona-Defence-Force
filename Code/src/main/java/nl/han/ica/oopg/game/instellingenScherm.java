package nl.han.ica.oopg.game;
import nl.han.ica.oopg.dashboard.*;
import nl.han.ica.oopg.sound.Sound;
import nl.han.ica.oopg.engine.*;
import nl.han.ica.oopg.objects.*;


public class instellingenScherm {
	public boolean isGeluidAan;
	public boolean isMuziekAan;
	private TextObject dashboardText;
	private Sound backgroundSound;
	private final int xGeluidKnop = 0;
	private final int yGeluidKnop = 0;
	private final int xMuziekKnop = 200;
	private final int yMuziekKnop = 200;
	private final int buttonWidth = 100;
	private final int buttonHeight = 100;
	private int fontSize;
	
	
	instellingenScherm(){	
		fontSize = 20;
		createGeluidKnop();
		createMuziekKnop();
		
	}
	
	public void setGeluid() {
		isGeluidAan = !isGeluidAan;
		dashboardText.setText(null);
	}
	public void setMuziek() {
		isMuziekAan = !isMuziekAan;
	}
	
	public boolean getGeluid() {
		return isGeluidAan;
	}
	public boolean getMuziek() {
		return isMuziekAan;
	}
	
	
	public Dashboard createGeluidKnop() {
		Dashboard dashboard = new Dashboard(xGeluidKnop, yGeluidKnop, buttonWidth, buttonHeight);
        dashboardText = new TextObject("Geluid is:" +Boolean.toString(isGeluidAan),fontSize);        
        dashboard.addGameObject(dashboardText);
        // dit moet naar de spel klasse.
        //addDashboard(dashboard);
        return dashboard;
		
	}
	public Dashboard createMuziekKnop() {
		Dashboard dashboard = new Dashboard(xMuziekKnop, yMuziekKnop, buttonWidth, buttonHeight);
        dashboardText = new TextObject("Geluid is:" +Boolean.toString(isMuziekAan),fontSize);        
        dashboard.addGameObject(dashboardText);
        // dit moet naar de spel klasse.
        //addDashboard(dashboard);
        return dashboard;
		
	}
	 
	public void initializeSound(GameEngine engine) {
		if (isGeluidAan) {
        backgroundSound = new Sound(engine, "src/main/java/nl/han/ica/oopd/waterworld/media/8bitmusic.mp3");
        backgroundSound.loop(-1);
		}        
    }

}
