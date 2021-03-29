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
import nl.han.ica.oopg.verdediger.PolitieAgent;
import nl.han.ica.oopg.verdediger.Verdediger;
import nl.han.ica.oopg.verdediger.VerdedigersLijst;
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
	private final int TILESIZE = 64;
	private int state = MENUSCHERM;
	private InstellingenScherm instellingenScherm;
	private MenuScherm menuScherm;
	private Dashboard dashboard;
	private TextObject dashboardText;
	private VerdedigersLijst verdedigersLijst;

	private BuildScreen buildScreen;
	private Verdediger selectedVerdediger;
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
		verdedigersLijst = new VerdedigersLijst(this, TILESIZE);
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

			
			break;

		case INSTELLINGENSCHERM:
			
		

			break;

		case SPELSCHERM:

			
			if (vijandSpawner != null) {
				vijandSpawner.checkVijandStatus();
				vijandSpawner.verwijderDodeVijanden();
			}
			verdedigersLoop();

			break;

		}

	}

	public void bepaalScherm() {

		switch (state) {
		case MENUSCHERM:

			dashboard = menuScherm.getDashboard();
			addDashboard(dashboard);

			break;

		case INSTELLINGENSCHERM:

			dashboard = instellingenScherm.getDashboard();
			addDashboard(dashboard);

			break;

		case SPELSCHERM:
			geld = 50;
			deleteAllDashboards();
			initializeTileMap();
			initializeVijandMap();

			vijandSpawner.beginAlarmGolf();
			
			showDashboard();
			showMoney();
		    

			break;

		case SCORESCHERM:
			state = MENUSCHERM;

			break;

		default:
			break;
		}
	}

	private void initializeTileMap() {

		Sprite wegSprite = new Sprite(MEDIA_URL.concat("wegSprite.png"));
		Sprite grasSprite = new Sprite(MEDIA_URL.concat("grasSprite.png"));
		Sprite supermarktSprite = new Sprite(MEDIA_URL.concat("jumbo.png"));

		TileType<WegTile> wegTileType = new TileType<>(WegTile.class, wegSprite);
		TileType<GrasTile> grasTileType = new TileType<>(GrasTile.class, grasSprite);
		TileType<SupermarktTile> supermarktTileType = new TileType<>(SupermarktTile.class, supermarktSprite);

		TileType[] tileTypes = { wegTileType, grasTileType, supermarktTileType };

		int tilesMap[][] = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1 }, { 1, 2, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0 },
				{ 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1 }, { 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 1 },
				{ 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1 }, { 1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };
		tileMap = new TileMap(TILESIZE, tileTypes, tilesMap);
	}

	public void initializeVijandMap() {
		int vijandMap[][] = { { 0, 1, 2, 3, 4 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 } };
		int vijandSpawnX = 15;
		int vijandSpawnY = 2;
		int tijdTussenVijanden = 1;
		int tijdTussenGolven = 15;
		
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

	public void verdedigersLoop() {
		for (int i = 0; i < verdedigersLijst.getVerdedigers().size(); i++) {

			verdedigersLijst.getVerdedigers().get(i).laatsteAanvaltijd++;
			if (verdedigersLijst.getVerdedigers().get(i).laatsteAanvaltijd > verdedigersLijst.getVerdedigers()
					.get(i).herlaadTijd) {

				for (int j = 0; j < vijandSpawner.getVijanden().size(); j++) {
			
					if (vijandSpawner.getVijanden().get(j).getCenterX() < verdedigersLijst.getVerdedigers().get(i).getCenterX()	+ verdedigersLijst.getVerdedigers().get(i).radius
							&& vijandSpawner.getVijanden().get(j).getCenterX() > verdedigersLijst.getVerdedigers().get(i).getCenterX() - verdedigersLijst.getVerdedigers().get(i).radius
							&& vijandSpawner.getVijanden().get(j).getCenterY() < verdedigersLijst.getVerdedigers().get(i).getCenterY() + verdedigersLijst.getVerdedigers().get(i).radius
							&& vijandSpawner.getVijanden().get(j).getCenterY() > verdedigersLijst.getVerdedigers().get(i).getCenterY() - verdedigersLijst.getVerdedigers().get(i).radius) {
						vijandSpawner.getVijanden().get(j).krijgSchade(verdedigersLijst.getVerdedigers().get(i).aanvalsKracht);
						verdedigersLijst.getVerdedigers().get(i).laatsteAanvaltijd = 0;
						System.out.println(vijandSpawner.getVijanden().get(j).getLevens());
						System.out.println(vijandSpawner.getVijanden().get(j).isLevend());
					}

				}
			}
		}

	}
	private void showMoney() {
		TextObject money = new TextObject (Integer.toString(geld),22);
		money.setForeColor(35,35,255,255);
		addGameObject(money,(worldWidth / 2) - 75, 20);
	}
	
	private void showDashboard() {
		dashboard = buildScreen.getBuildScreen();
		for (Verdediger i : buildScreen.Verdedigers) {
			addGameObject(i, buildScreen.getX(), 0);
			TextObject naam = new TextObject (i.getNaam(), 16);
			naam.setForeColor(255,255,255,255);
			addGameObject(naam,buildScreen.getX(), 0 + TILESIZE*2);
			TextObject prijs = new TextObject (Integer.toString(i.getPrijs()),16);
			prijs.setForeColor(255,255,255,255);
			addGameObject(prijs,buildScreen.getX()+ TILESIZE * 2, 0 + TILESIZE * 2);
		}
	}

	public void mouseClicked() {
		switch (state) {
		case MENUSCHERM:

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
			break;

		case INSTELLINGENSCHERM:

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

			break;
		case SPELSCHERM:

			for (Verdediger i : buildScreen.Verdedigers) {
				if (mouseX > i.getX() && mouseX < (i.getX() + i.getWidth()) && mouseY > i.getY()
						&& mouseY < (i.getY() + i.getHeight())) {
					this.selectedVerdediger = i;
					System.out.println(i.naam + " geselecteerd");

				}

			}

			if (selectedVerdediger != null && mouseX < (16 * tileMap.getTileSize())
					&& mouseY < (10 * tileMap.getTileSize())) {
				if (tileMap.getTileOnPosition(mouseX, mouseY) != null) {
					if (tileMap.getTileOnPosition(mouseX, mouseY) instanceof GrasTile) {
						System.out.println(selectedVerdediger.naam + "geplaatst");
						verdedigersLijst.addVerdediger(new PolitieAgent(25, 5),
								tileMap.getTilePixelLocation(tileMap.getTileOnPosition(mouseX, mouseY)));

						this.selectedVerdediger = null;
					}
					if (verdedigersLijst.isVerdediger(mouseX, mouseY)) {
						System.out.println("Hier staat al een verdediger");

					}

				}

			}
			break;
		}

	}
}
