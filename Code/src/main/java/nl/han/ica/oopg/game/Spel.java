package nl.han.ica.oopg.game;

import java.util.ArrayList;

import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.ica.oopg.view.View;

@SuppressWarnings("serial")
public class Spel extends GameEngine {
	
	public static String MEDIA_URL = "src/main/java/party/game/tutorial/media/";
	
	/*
	private final int MENUSCHERM = 0;
	private final int INSTELLINGENSCHERM = 1;
	private final int SCORESCHERM = 2;
	private final int SPELSCHERM = 3;
	private int state = MENUSCHERM; 
	
	
	private int levens, tijd, geld;
	private ArrayList<Verdediger> verdedigers;
	private ArrayList<Vijand> vijand;
	private ArrayList<Projectiel> projectielen;
	private Map map;
	private Verdediger geselecteerdeVerdediger;
	*/
	
	public static void main(String[] args) {
		Spel spel = new Spel();
		spel.runSketch();
	}
	
	@Override
	public void setupGame() {
		int worldWidth = 1280;
		int worldHeight = 720;
		
		TextObject to = new TextObject("Hello, World!", 40);
		to.setForeColor(255, 255, 255, 255);
		addGameObject(to, 100, 100);
		
		View view = new View(worldWidth, worldHeight);

		setView(view);
		size(worldWidth, worldHeight);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public void bepaalScherm() {
		
		switch (state) {
		case MENUSCHERM:
			
			break;
			
		case INSTELLINGENSCHERM:
			
			break;
			
		case SCORESCHERM:
			
			break;
			
		case SPELSCHERM: 
			
			break;
			
		default:
			break;
		}
	}
	
	
	public void nieuweGolf() {
		
	}
	
	public void gameOver() {
		
	}
	
	public void plaatsNieuweVerdediger() {
		
	}
	
	public void addDashboard() {
		
	}
	
	public void setTileMap() {
		
	}
	
	public void setGameSpeed() {
		
	}
	
}
