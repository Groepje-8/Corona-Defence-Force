package nl.han.ica.oopg.game;

import java.util.ArrayList;

import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.ica.oopg.screens.BuildScreen;
import nl.han.ica.oopg.screens.InstellingenScherm;
import nl.han.ica.oopg.screens.MenuScherm;
import nl.han.ica.oopg.sound.Sound;
import nl.han.ica.oopg.tile.GrasTile;
import nl.han.ica.oopg.tile.SupermarktTile;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.tile.WegTile;
import nl.han.ica.oopg.view.View;
import nl.han.ica.oopg.vijand.Relschopper;
import nl.han.ica.oopg.vijand.Vijand;
import nl.han.ica.oopg.vijand.VijandSpawner;
import nl.han.ica.oopg.screens.InstellingenScherm;

@SuppressWarnings("serial")
public class Spel extends GameEngine {

	public static String MEDIA_URL = "src/main/java/media/";
	//public static String MEDIA_URL = "C:\\\\Users\\\\Joria\\\\Documents\\\\GitHub\\\\Corona-Defence-Force\\\\Code\\\\src\\\\main\\\\java\\\\media\\";
	private final int MENUSCHERM = 0;
	private final int INSTELLINGENSCHERM = 1;
	private final int SCORESCHERM = 2;
	private final int SPELSCHERM = 3;
	private int state = MENUSCHERM;
	private InstellingenScherm instellingenScherm;
	private MenuScherm menuScherm;
	private Dashboard dashboard;
	private TextObject dashboardText;

	private BuildScreen buildScreen;
	private SpriteObject selectedVerdediger;
	public Sound backgroundSound;

	int worldWidth = 1280;
	int worldHeight = 720;

	private int levens, tijd, geld;
//	private ArrayList<Verdediger> verdedigers;
//	private ArrayList<Projectiel> projectielen;
//	private Verdediger geselecteerdeVerdediger;
	VijandSpawner vijandSpawner;

	public static void main(String[] args) {
		Spel spel = new Spel();
		spel.runSketch();
	}

	@Override
	public void setupGame() {
		instellingenScherm = new InstellingenScherm();
		menuScherm = new MenuScherm();
		buildScreen = new BuildScreen();
		backgroundSound = new Sound(this, MEDIA_URL.concat("8bitmusic.mp3"));

		View view = new View(worldWidth, worldHeight);
		setView(view);
		size(worldWidth, worldHeight);
		view.setBackground(30, 30, 36);

		bepaalScherm();
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
			dashboard.draw(g);
			if (vijandSpawner != null) {
				vijandSpawner.checkVijandStatus();
			}
			
			break;

		}

	}

	public void bepaalScherm() {

		switch (state) {
		case MENUSCHERM:

			dashboard = menuScherm.getDashboard();

			break;

		case INSTELLINGENSCHERM:

			dashboard = instellingenScherm.getDashboard();

			break;

		case SPELSCHERM:
			initializeTileMap();
			initializeVijandMap();
			vijandSpawner.beginAlarmGolf();
			
//			buildScreen.getBuildScreen().draw(g);
//			vervang dashboard met het bouw scherm loop door alle te kopen verdedigers en teken die.
			dashboard = buildScreen.getBuildScreen();
			for (GameObject i : buildScreen.Verdedigers) {
				addGameObject(i, buildScreen.getX(), 0);
			}

			break;

		case SCORESCHERM:
			state = MENUSCHERM;

			break;

		default:
			break;
		}
	}
	

	public void addDashboard() {
		Dashboard dashboard = new Dashboard(0, 0, worldWidth, 300);
		dashboardText = new TextObject("hi", 12);
		dashboard.addGameObject(dashboardText);
		addDashboard(dashboard);
	}

	private void initializeTileMap() {

		Sprite wegSprite = new Sprite(MEDIA_URL.concat("wegSprite.png"));
		Sprite grasSprite = new Sprite(MEDIA_URL.concat("grasSprite.png"));
		Sprite supermarktSprite = new Sprite(MEDIA_URL.concat("jumbo.png"));

		TileType<WegTile> wegTileType = new TileType<>(WegTile.class, wegSprite);
		TileType<GrasTile> grasTileType = new TileType<>(GrasTile.class, grasSprite);
		TileType<SupermarktTile> supermarktTileType = new TileType<>(SupermarktTile.class, supermarktSprite);
		int tileSize = 64;
		TileType[] tileTypes = { wegTileType, grasTileType, supermarktTileType };

		int tilesMap[][] = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1 }, { 1, 2, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0 },
				{ 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1 }, { 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1 },
				{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1 }, { 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };
		tileMap = new TileMap(tileSize, tileTypes, tilesMap);
	}
	
	public void initializeVijandMap() {
		int vijandMap[][] = {
			{0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0}
		};
		int vijandSpawnX = 1028 + 16;
		int vijandSpawnY = 128 + 16;
		int tijdTussenVijanden = 1;
		int tijdTussenGolven = 13;
		
		vijandSpawner = new VijandSpawner(this, vijandMap, vijandSpawnX, vijandSpawnY, tijdTussenVijanden, tijdTussenGolven);
	}
	
	public void ontvangSchade(int schade) {
		levens -= schade;
	}
	
	public void gameOver() {

	}

	public void plaatsNieuweVerdediger() {

	}

	public void setGameSpeed() {

	}

	public void mousePressed() {
		// kijk naar dashboard in menuscherm.
		if (state == MENUSCHERM) {
			for (GameObject i : dashboard.getGameObjects()) {
				if (mouseX > i.getX() && mouseX < (i.getX() + i.getWidth()) && mouseY > i.getY()
						&& mouseY < (i.getY() + i.getHeight())) {
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
			for (GameObject i : dashboard.getGameObjects()) {

				if (mouseX > i.getX() && mouseX < (i.getX() + i.getWidth()) && mouseY > i.getY()
						&& mouseY < (i.getY() + i.getHeight())) {

					if (i.equals(instellingenScherm.backKnop)) {
						state = MENUSCHERM;
						bepaalScherm();
					}

					if (i.equals(instellingenScherm.geluidsKnop)) {

					}

					if (i.equals(instellingenScherm.muziekKnop)) {
						instellingenScherm.soundHandler(this, backgroundSound);

					}

				}

			}
		}

		if (state == SPELSCHERM) {
			for (SpriteObject i : buildScreen.Verdedigers) {
				if (mouseX > i.getX() && mouseX < (i.getX() + i.getWidth()) && mouseY > i.getY()
						&& mouseY < (i.getY() + i.getHeight())) {
					this.selectedVerdediger = i;
					System.out.println("Verdediger geselecteerd");
				}

			}

			if (selectedVerdediger != null && mouseX < (16 * tileMap.getTileSize())
					&& mouseY < (10 * tileMap.getTileSize())) {
				if (tileMap.getTileOnPosition(mouseX, mouseY) != null) {
					if (tileMap.getTileOnPosition(mouseX, mouseY) instanceof GrasTile) {
						System.out.println("plaats toren");
						tileMap.getTileOnPosition(mouseX, mouseY).setSprite(new Sprite(MEDIA_URL.concat("cop.png")));
						tileMap.getTileOnPosition(mouseX, mouseY).setSpriteSize(tileMap.getTileSize());
						this.selectedVerdediger = null;
					}

				}

			}

		}

	}
}
