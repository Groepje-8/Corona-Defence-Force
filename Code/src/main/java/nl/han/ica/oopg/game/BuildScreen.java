package nl.han.ica.oopg.game;
import java.util.ArrayList;

import nl.han.ica.oopg.dashboard.Dashboard;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import processing.core.PGraphics;

public class BuildScreen {
	ArrayList<SpriteObject> Verdedigers = new ArrayList<SpriteObject>();
	public static String MEDIA_URL = "code/src/main/java/media/";
	Dashboard screen;
	private int x = 1024;
	private int y = 0;
	private int width = 256;
	private int height = 720;
	
	BuildScreen(){
		screen = new Dashboard(x,y,width,height);
		screen.setBackground(255, 255, 255);
		fillVerdedigers();
		
	}
	private void fillVerdedigers() {
		Sprite sprite = new Sprite("C:\\Users\\Joria\\Documents\\GitHub\\Corona-Defence-Force\\Code\\src\\main\\java\\media\\cop.png");
		sprite.resize(width/2, width/2);
		PolitieAgent politieAgent = new PolitieAgent(10,sprite,"PolitieAgent",10,0,5,20);
		Verdedigers.add(politieAgent);
	}
	
	public void drawBuildScreen(PGraphics P) {
//		for(SpriteObject i : Verdedigers) {
//			i.draw(null);
//		}
	}
	
	public Dashboard getBuildScreen() {
		return screen;
	}

	public int getX() {
		return x;
	}

}

