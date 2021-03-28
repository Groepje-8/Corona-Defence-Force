package nl.han.ica.oopg.screens;
import java.util.ArrayList;


import nl.han.ica.oopg.dashboard.Dashboard;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import nl.han.ica.oopg.verdediger.PolitieAgent;
import processing.core.PGraphics;

public class BuildScreen {
	public ArrayList<SpriteObject> Verdedigers = new ArrayList<SpriteObject>();
	Dashboard screen;
	private int x = 1024;
	private int y = 0;
	private int width = 256;
	private int height = 720;
	
	public BuildScreen(){
		screen = new Dashboard(x, y, width, height);
		//screen.setBackground(255, 255, 255);
		fillVerdedigers();
		
	}
	private void fillVerdedigers() {
		PolitieAgent politieAgent = new PolitieAgent(10, 0, 5);
		Verdedigers.add(politieAgent);
	}
	
	public void drawBuildScreen(PGraphics g) {
//		   PGraphics canvas = drawCanvas();
//	        g.image(canvas, this.getX(), this.getY());
	}
	
	public Dashboard getBuildScreen() {
		return screen;
	}

	public int getX() {
		return x;
	}
	public ArrayList<SpriteObject> getVerdedigers(){
		return Verdedigers;
	}

}

