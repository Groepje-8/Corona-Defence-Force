package nl.han.ica.oopg.game;

import java.util.ArrayList;

import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.ica.oopg.sound.Sound;
import nl.han.ica.oopg.tile.GrasTile;
import nl.han.ica.oopg.tile.SupermarktTile;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.tile.WegTile;
import nl.han.ica.oopg.view.View;
import nl.han.ica.oopg.vijand.Relschopper;
import nl.han.ica.oopg.vijand.Vijand;
import nl.han.ica.oopg.game.InstellingenScherm;

@SuppressWarnings("serial")
public class Spel extends GameEngine {

	public static String MEDIA_URL = "src/main/java/media/";

	private final int MENUSCHERM = 0;
	private final int INSTELLINGENSCHERM = 1;
	private final int SCORESCHERM = 2;
	private final int SPELSCHERM = 3;
	private int state = MENUSCHERM;
	private InstellingenScherm instellingenScherm;
	private MenuScherm menuScherm;
	private Knoppen dashboard;
	private TextObject dashboardText;
	public Sound backgroundSound;
	
	int worldWidth = 1280;
	int worldHeight = 720;
	

	private int levens, tijd, geld;
//	private ArrayList<Verdediger> verdedigers;
	private ArrayList<Vijand> vijanden = new ArrayList<>();
//	private ArrayList<Projectiel> projectielen;
//	private Verdediger geselecteerdeVerdediger;
	
	int vijandSpawnX = 1024;
	int vijandSpawnY = 208;
	
	public static void main(String[] args) {
		Spel spel = new Spel();
		spel.runSketch();

	}

	@Override
	public void setupGame() {
		instellingenScherm = new InstellingenScherm();
		menuScherm = new MenuScherm();
		backgroundSound = new Sound(this, "C:\\Users\\levig\\OneDrive\\Documents\\GitHub\\Corona-Defence-Force\\Code\\src\\main\\java\\media\\8bitmusic.mp3");
		View view = new View(worldWidth, worldHeight);
		setView(view);
		size(worldWidth, worldHeight);
		view.setBackground(30, 30, 36);

		bepaalScherm();
		
		vijanden.add(new Relschopper());
		addGameObject(vijanden.get(0), vijandSpawnX, vijandSpawnY);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		switch (state) {
		case MENUSCHERM:
			
			dashboard.draw(g);
			
			break;
			
		case INSTELLINGENSCHERM:
			dashboard.draw(g);
			
			break;
		case SPELSCHERM:
			
			break;
		}
		

	}

	public void bepaalScherm() {

		switch (state) {
		case MENUSCHERM:

			dashboard = menuScherm.getDashboard();
			dashboard.draw(g);

			break;

		case INSTELLINGENSCHERM:

			dashboard = instellingenScherm.getDashboard();
			dashboard.draw(g);
			

			break;

		case SCORESCHERM:
			

			break;

		case SPELSCHERM:
			//state = SCORESCHERM;
			initializeTileMap();
			break;

		default:
			break;
		}
	}
	

	private void initializeTileMap() {

		Sprite wegSprite = new Sprite(Spel.MEDIA_URL.concat("wegSprite.png"));
		Sprite grasSprite = new Sprite(Spel.MEDIA_URL.concat("grasSprite.png"));
		Sprite supermarktSprite = new Sprite(Spel.MEDIA_URL.concat("jumbo.png"));

		TileType<WegTile> wegTileType = new TileType<>(WegTile.class, wegSprite);
		TileType<GrasTile> grasTileType = new TileType<>(GrasTile.class, grasSprite);
		TileType<SupermarktTile> supermarktTileType = new TileType<>(SupermarktTile.class, supermarktSprite);

		TileType[] tileTypes = { wegTileType, grasTileType, supermarktTileType };
		int tileSize = 64;
		int tilesMap[][] = { 
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, 
				{ 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1 }, 
				{ 1, 2, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0 },
				{ 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1 }, 
				{ 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1 }, 
				{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1 },
				{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1 }, 
				{ 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, 
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
				};
		tileMap = new TileMap(tileSize, tileTypes, tilesMap);
		initializeTileRoute();
	}
	
	
	public void initializeTileRoute() {
		setNextTile(14, 2);
		setNextTile(13, 2);
		setNextTile(13, 3);
		setNextTile(13, 4);
		setNextTile(13, 5);
		setNextTile(13, 6);
		setNextTile(13, 7);
		setNextTile(12, 7);
		setNextTile(11, 7);
		setNextTile(10, 7);
		setNextTile(9, 7);
		setNextTile(9, 6);
		setNextTile(9, 5);
		setNextTile(9, 4);
		setNextTile(10, 4);
		setNextTile(11, 4);
		setNextTile(11, 3);
		setNextTile(11, 2);
		setNextTile(11, 1);
		setNextTile(10, 1);
		setNextTile(9, 1);
		setNextTile(8, 1);
		setNextTile(7, 1);
		setNextTile(6, 1);
		setNextTile(5, 1);
		setNextTile(4, 1);
		setNextTile(4, 2);
		setNextTile(4, 3);
		setNextTile(4, 4);
		setNextTile(5, 4);
		setNextTile(6, 4);
		setNextTile(6, 5);
		setNextTile(6, 6);
		setNextTile(6, 7);
		setNextTile(5, 7);
		setNextTile(4, 7);
		setNextTile(3, 7);
		setNextTile(2, 7);
		setNextTile(1, 7);
		setNextTile(1, 6);
		setNextTile(1, 5);
		setNextTile(1, 4);
		setNextTile(1, 3);
		((WegTile) tileMap.getTileOnIndex(vorigeX, vorigeY)).setLaatsteWegTrue();
	}
	int vorigeX = 15;
	int vorigeY = 2;
	public void setNextTile(int nieuweX, int nieuweY) {
		((WegTile) tileMap.getTileOnIndex(vorigeX, vorigeY)).setVolgendeWeg(((WegTile) tileMap.getTileOnIndex(nieuweX, nieuweY)));
		
		vorigeX = nieuweX;
	    vorigeY = nieuweY;
	}
	
	public void nieuweGolf() {

	}

	public void gameOver() {

	}

	public void plaatsNieuweVerdediger() {

	}

	public void addDashboard() {
		Dashboard dashboard = new Dashboard(0, 0, worldWidth, 300);
		dashboardText = new TextObject("hi", 12);
		dashboard.addGameObject(dashboardText);
		addDashboard(dashboard);

	}

	public void setTileMap() {

	}

	public void setGameSpeed() {

	}

	public void mousePressed() {
		// kijk naar dashboard in menuscherm.
		if (state == MENUSCHERM) {		
			for(GameObject i:dashboard.getGameObjects()) {
				if (mouseX > i.getX() && mouseX < (i.getX()+i.getWidth()) && mouseY > i.getY() && mouseY < (i.getY()+i.getHeight())){
					if (i.equals(menuScherm.instellingenKnop)) {
						state = INSTELLINGENSCHERM;
						bepaalScherm();
					}
					if (i.equals(menuScherm.startKnop)) {
						state = SPELSCHERM;
						bepaalScherm();
					}
					
				}
			}
		}
		// kijk naar dashboard in instellingenScherm.
		if (state == INSTELLINGENSCHERM) {		
			for(GameObject i:dashboard.getGameObjects()) {
				
				if (mouseX > i.getX() && mouseX < (i.getX()+i.getWidth()) && mouseY > i.getY() && mouseY < (i.getY()+i.getHeight())){
					
					
					if (i.equals(instellingenScherm.muziekKnop)) {
					instellingenScherm.soundHandler(this,backgroundSound);
					
					dashboard.refresh(instellingenScherm.muziekKnop);
					dashboard.update();
					
					} else {
					if (i.equals(instellingenScherm.geluidsKnop)) {
						
					}
					}
					if (i.equals(instellingenScherm.backKnop)) {
						state = MENUSCHERM;
						bepaalScherm();
					}
					
				}
				
			}
		}
		
//		if (state == MENUSCHERM) {
//			state = INSTELLINGENSCHERM;
//
//			bepaalScherm();
//
//		} else {
//			state = MENUSCHERM;
//
//			bepaalScherm();
//		}

	}

}