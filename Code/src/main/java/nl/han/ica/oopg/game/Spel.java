package nl.han.ica.oopg.game;

import java.util.ArrayList;

import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.ica.oopg.screens.BuildScreen;
import nl.han.ica.oopg.screens.InstellingenScherm;
import nl.han.ica.oopg.screens.Knop;
import nl.han.ica.oopg.screens.MenuScherm;
import nl.han.ica.oopg.sound.Sound;
import nl.han.ica.oopg.tile.GrasTile;
import nl.han.ica.oopg.tile.Logo;
import nl.han.ica.oopg.tile.SupermarktTile;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.tile.WegTile;
import nl.han.ica.oopg.verdediger.Verdediger;
import nl.han.ica.oopg.verdediger.VerdedigersLijst;
import nl.han.ica.oopg.view.View;
import nl.han.ica.oopg.vijand.VijandSpawner;

@SuppressWarnings("serial")
public class Spel extends GameEngine {

	// public static String MEDIA_URL = "src/main/java/media/";
	public static String MEDIA_URL = "C:\\\\Users\\\\Joria\\\\Documents\\\\GitHub\\\\Corona-Defence-Force\\\\Code\\\\src\\\\main\\\\java\\\\media\\";
	private final int MENUSCHERM = 0;
	private final int INSTELLINGENSCHERM = 1;
	private final int SCORESCHERM = 2;
	private final int SPELSCHERM = 3;
	private final int TILESIZE = 64;
	private final int BUILDSCREENXOFFSET = 135;
	private final int BUILDSCREENTEXTOFFSET = 90;
	private final int BUILDSCREENFONTSIZE = 16;
	private int state = MENUSCHERM;
	private InstellingenScherm instellingenScherm;
	private MenuScherm menuScherm;
	private VerdedigersLijst verdedigersLijst;
	private BuildScreen buildScreen;
	private Verdediger selectedVerdediger;
	public Sound backgroundSound;

	TextObject levensTO, geldTO, aantalVijandenTO, timerTO, messageTO;
	View view;

	int worldWidth = 1280;
	int worldHeight = 720;

	private int levens, geld;
//	private ArrayList<Verdediger> verdedigers;
//	private ArrayList<Projectiel> projectielen;
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

		view = new View(worldWidth, worldHeight);
		setView(view);
		size(worldWidth, worldHeight);

		bepaalScherm();
	}

	@Override
	public void update() {
		switch (state) {
		case MENUSCHERM:

			break;

		case INSTELLINGENSCHERM:

			break;

		case SPELSCHERM:

			if (vijandSpawner != null) {
				vijandSpawner.checkVijandStatus();
				vijandSpawner.verwijderDodeVijanden();
				updateGameDetailDashboard();
			}

			verdedigersLoop();
			isGameOver();

			break;
		case SCORESCHERM:
			state = MENUSCHERM;

			break;

		}
	}

	public void bepaalScherm() {

		switch (state) {
		case MENUSCHERM:
			this.deleteAllGameObjectsOfType(Knop.class);
			view.setBackground(loadImage(MEDIA_URL.concat("MenuBackground.png")));

			addGameObject(menuScherm.getInstellingenKnop());
			addGameObject(menuScherm.getStartKnop());
			
			break;

		case INSTELLINGENSCHERM:
			
            this.deleteAllGameObjectsOfType(Knop.class);
            for(Knop i : instellingenScherm.getKnoppen()) {
            	addGameObject(i);
            }
            
			view.setBackground(loadImage(MEDIA_URL.concat("InstellingenBackground.png")));


			break;

		case SPELSCHERM:
			this.deleteAllGameObjectsOfType(Knop.class);
			geld = 100;
			levens = 20;
			view.setBackground(loadImage(MEDIA_URL.concat("SpelBackground.png")));


			initializeTileMap();
			initializeVijandSpawner();
			initializeMessage();

			vijandSpawner.beginAlarmGolf();
			vijandSpawner.beginAlarmTimer();

			showBuildScreenDashboard();
			showGameDetailDashboard();

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
		Sprite supermarktSprite = new Sprite(MEDIA_URL.concat("supermarktSprite.png"));

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

		addGameObject(new Logo(new Sprite(MEDIA_URL.concat("jumbo1.png"))), 6, 80);
	}

	public void initializeVijandSpawner() {
		int vijandMap[][] = { { 0, 0, 1, 1, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 } };
		int vijandSpawnX = 15;
		int vijandSpawnY = 2;
		int tijdTussenVijanden = 1;
		int tijdTussenGolven = 15;

		vijandSpawner = new VijandSpawner(this, vijandMap, vijandSpawnX, vijandSpawnY, tijdTussenVijanden,
				tijdTussenGolven);
	}

	public void ontvangSchade(int schade) {
		levens -= schade;
	}

	public void gameOver() {

	}

	public void plaatsNieuweVerdediger() {

	}

	public void setGeld(int geld) {
		this.geld = this.geld + geld;
	}

	public void setGameSpeed() {

	}

	public void verdedigersLoop() {
		for (int i = 0; i < verdedigersLijst.getVerdedigers().size(); i++) {

			verdedigersLijst.getVerdedigers().get(i).laatsteAanvaltijd++;
			if (verdedigersLijst.getVerdedigers().get(i).laatsteAanvaltijd > verdedigersLijst.getVerdedigers()
					.get(i).herlaadTijd) {

				for (int j = 0; j < vijandSpawner.getVijanden().size(); j++) {

					if (vijandSpawner.getVijanden().get(j).getCenterX() < verdedigersLijst.getVerdedigers().get(i)
							.getCenterX() + verdedigersLijst.getVerdedigers().get(i).radius
							&& vijandSpawner.getVijanden().get(j).getCenterX() > verdedigersLijst.getVerdedigers()
									.get(i).getCenterX() - verdedigersLijst.getVerdedigers().get(i).radius
							&& vijandSpawner.getVijanden().get(j).getCenterY() < verdedigersLijst.getVerdedigers()
									.get(i).getCenterY() + verdedigersLijst.getVerdedigers().get(i).radius
							&& vijandSpawner.getVijanden().get(j).getCenterY() > verdedigersLijst.getVerdedigers()
									.get(i).getCenterY() - verdedigersLijst.getVerdedigers().get(i).radius) {
						vijandSpawner.getVijanden().get(j)
								.krijgSchade(verdedigersLijst.getVerdedigers().get(i).aanvalsKracht);
						verdedigersLijst.getVerdedigers().get(i).laatsteAanvaltijd = 0;
						System.out.println(vijandSpawner.getVijanden().get(j).getLevens());
						System.out.println(vijandSpawner.getVijanden().get(j).isLevend());
						break;

					}

				}
			}
		}
	}

	private void showGameDetailDashboard() {
		levensTO = new TextObject("Levens = " + levens, 22);
		levensTO.setForeColor(23, 13, 29, 255);
		addGameObject(levensTO, 200, 668);

		geldTO = new TextObject("$ " + geld, 22);
		geldTO.setForeColor(23, 13, 29, 255);
		addGameObject(geldTO, 400, 668);

		aantalVijandenTO = new TextObject("Vijanden verslagen = " + vijandSpawner.getAantalVerslagen(), 22);
		aantalVijandenTO.setForeColor(23, 13, 29, 255);
		addGameObject(aantalVijandenTO, 520, 668);

		timerTO = new TextObject("Volgende golf: " + vijandSpawner.getGolfTimer(), 22);
		timerTO.setForeColor(23, 13, 29, 255);
		addGameObject(timerTO, 800, 668);
	}

	private void updateGameDetailDashboard() {
		levensTO.setText("Levens = " + levens);
		geldTO.setText("$" + geld);
		aantalVijandenTO.setText("Vijanden verslagen = " + vijandSpawner.getAantalVerslagen());
		timerTO.setText("Volgende golf: " + vijandSpawner.getGolfTimer());
	}

	private void showBuildScreenDashboard() {
		for (int i = 0; i < buildScreen.Verdedigers.size(); i++) {

			int x = buildScreen.getX() + (BUILDSCREENXOFFSET * (i % 2)) + 12;
			int y = ((i / 2) | 0) * BUILDSCREENXOFFSET + 15;

			addGameObject(buildScreen.Verdedigers.get(i), x, y, 101);

			TextObject naam = new TextObject(buildScreen.Verdedigers.get(i).getNaam(), BUILDSCREENFONTSIZE);
			naam.setForeColor(255, 255, 255, 255);
			x = buildScreen.getX() + (BUILDSCREENXOFFSET * (i % 2)) + 12;
			y = BUILDSCREENTEXTOFFSET + ((i / 2) | 0) * BUILDSCREENXOFFSET + 15;
			addGameObject(naam, x, y, 101);

			TextObject prijs = new TextObject("Cost: " + Integer.toString(buildScreen.Verdedigers.get(i).getPrijs()),
					BUILDSCREENFONTSIZE);
			prijs.setForeColor(255, 255, 255, 255);
			x = buildScreen.getX() + (BUILDSCREENXOFFSET * (i % 2)) + 12;
			y = 20 + BUILDSCREENTEXTOFFSET + ((i / 2) | 0) * BUILDSCREENXOFFSET + 15;
			addGameObject(prijs, x, y, 101);
		}
	}

	private void initializeMessage() {
		messageTO = new TextObject("", 22);
		messageTO.setForeColor(35, 35, 255, 255);
		addGameObject(messageTO, (worldWidth / 2) - 75, 20);
	}

	private void showMessage(String x) {
		messageTO.setText(x);
	}

	private void clearMessage() {
		messageTO.setText("");
	}

	private void isGameOver() {
		if (levens < 1) {
			this.pauseGame();
			state = SCORESCHERM;
		}
	}

	public void mouseClicked() {
		switch (state) {
		case MENUSCHERM:

			for (Knop i : menuScherm.getKnoppen()) {
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

			for (GameObject i : instellingenScherm.getKnoppen()) {

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
				System.out.println(i.getX());
				if (mouseX > i.getX() && mouseX < (i.getX() + i.getWidth()) && mouseY > i.getY()
						&& mouseY < (i.getY() + i.getHeight())) {
				//	this.selectedVerdediger = clone(i);
					System.out.println(i.naam + " geselecteerd");
					clearMessage();

				}

			}
		

			if (selectedVerdediger != null && mouseX < (16 * tileMap.getTileSize())
					&& mouseY < (10 * tileMap.getTileSize())) {
				if (tileMap.getTileOnPosition(mouseX, mouseY) != null) {
					if (tileMap.getTileOnPosition(mouseX, mouseY) instanceof GrasTile) {
						if (geld - selectedVerdediger.prijs >= 0) {
							System.out.println(selectedVerdediger.naam + "geplaatst");
							verdedigersLijst.addVerdediger(new Verdediger(selectedVerdediger),
									tileMap.getTilePixelLocation(tileMap.getTileOnPosition(mouseX, mouseY)));
							geld -= selectedVerdediger.prijs;
//							selectedVerdediger.resize(90);
//							this.selectedVerdediger = null;
//							((Verdediger) this.getGameObjectItems().get(this.getGameObjectItems().size()-1)).resize(TILESIZE);
							clearMessage();
						} else {
							showMessage("Niet genoeg geld");
						}
					}
					if (verdedigersLijst.isVerdediger(mouseX, mouseY)) {
						showMessage("Hier staat al een verdediger");

					}

				}

			}
			break;
		default:
			state = MENUSCHERM;
			break;
		}

	}
}
