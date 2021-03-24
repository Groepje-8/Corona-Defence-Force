package nl.han.ica.oopg.game;

import java.util.ArrayList;

import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.ica.oopg.tile.GrasTile;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.tile.WegTile;
import nl.han.ica.oopg.view.View;

@SuppressWarnings("serial")
public class Spel extends GameEngine {

	public static String MEDIA_URL = "src/main/java/media/";

	private final int MENUSCHERM = 0;
	private final int INSTELLINGENSCHERM = 1;
	private final int SCORESCHERM = 2;
	private final int SPELSCHERM = 3;
	private int state = MENUSCHERM;
	private instellingenScherm instellingenScherm;
	private MenuScherm menuScherm;
	private Dashboard dashboard;
	private TextObject dashboardText;
	int worldWidth = 1280;
	int worldHeight = 720;
	TextObject to;

	private int levens, tijd, geld;
	/*
	 * private ArrayList<Verdediger> verdedigers; private ArrayList<Vijand> vijand;
	 * private ArrayList<Projectiel> projectielen; private Map map; private
	 * Verdediger geselecteerdeVerdediger;
	 */

	public static void main(String[] args) {
		Spel spel = new Spel();
		spel.runSketch();
	}

	@Override
	public void setupGame() {
		instellingenScherm = new instellingenScherm(this);
		menuScherm = new MenuScherm();
		View view = new View(worldWidth, worldHeight);
		setView(view);
		size(worldWidth, worldHeight);
		view.setBackground(30, 30, 36);
		bepaalScherm();

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

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
			instellingenScherm.initializeSound(this);

			break;

		case SCORESCHERM:

			break;

		case SPELSCHERM:

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

		Sprite wegSprite = new Sprite(Spel.MEDIA_URL.concat("wegSprite.png"));
		Sprite grasSprite = new Sprite(Spel.MEDIA_URL.concat("grasSprite.png"));

		TileType<WegTile> wegTileType = new TileType<>(WegTile.class, wegSprite);
		TileType<GrasTile> grasTileType = new TileType<>(GrasTile.class, grasSprite);

		TileType[] tileTypes = { wegTileType, grasTileType };
		int tileSize = 64;
		int tilesMap[][] = { { 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1 }, { 0, 0, 0, 0, 0, 0, 0, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1 }, };
		tileMap = new TileMap(tileSize, tileTypes, tilesMap);

		((WegTile) tileMap.getTileOnIndex(0, 4)).setVolgendeWeg(((WegTile) tileMap.getTileOnIndex(1, 4)));

	}

	public void nieuweGolf() {

	}

	public void gameOver() {

	}

	public void plaatsNieuweVerdediger() {

	}

	public void setTileMap() {

	}

	public void setGameSpeed() {

	}

	public void mousePressed() {
//		if (state == MENUSCHERM) {
//			if (mouseX > to.getX() && mouseX < (to.getX() + to.getWidth()) && mouseY > to.getY()
//					&& mouseX < (to.getY() + to.getHeight())) {
//				state = INSTELLINGENSCHERM;
//				bepaalScherm();
//			}
//		}
		if (state == MENUSCHERM) {
			state = INSTELLINGENSCHERM;

			bepaalScherm();

		} else {
			state = MENUSCHERM;

			bepaalScherm();
		}

	}

}
