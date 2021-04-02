package nl.han.ica.oopg.game;

import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.ica.oopg.screens.BuildScreen;
import nl.han.ica.oopg.screens.Buildable;
import nl.han.ica.oopg.screens.InstellingenScherm;
import nl.han.ica.oopg.screens.Knop;
import nl.han.ica.oopg.screens.MenuScherm;
import nl.han.ica.oopg.screens.ScoreScherm;
import nl.han.ica.oopg.screens.SpelScherm;
import nl.han.ica.oopg.screens.SoundHandler;
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
import nl.han.ica.oopg.vijand.Vijand;

@SuppressWarnings("serial")
public class Spel extends GameEngine {

	// public static String MEDIA_URL = "src/main/java/media/";
	public static String MEDIA_URL = "C:\\\\Users\\\\Joria\\\\Documents\\\\GitHub\\\\Corona-Defence-Force\\\\Code\\\\src\\\\main\\\\java\\\\media\\";

	private final int MENUSCHERM = 0;
	private final int INSTELLINGENSCHERM = 1;
	private final int SCORESCHERM = 2;
	private final int SPELSCHERM = 3;
	private int state = MENUSCHERM;

	private final int BOUWSCHERMXOFFSET = 135;
	private final int BOUWSCHERMTEXTOFFSET = 90;
	private final int BOUWSCHERMFONTSIZE = 16;
	private final int TILESIZE = 64;

	private InstellingenScherm instellingenScherm;
	private MenuScherm menuScherm;
	private SpelScherm spelScherm;
	private ScoreScherm scoreScherm;
	
	private VerdedigersLijst verdedigersLijst;
	private BuildScreen buildScreen;
	private Verdediger selectedVerdediger;
	public Sound backgroundSound;
	public SoundHandler soundHandler = new SoundHandler();

	TextObject levensTO, geldTO, aantalVijandenTO, timerTO, messageTO;
	View view;

	int worldWidth = 1280;
	int worldHeight = 720;

	private int levens, geld;

	VijandSpawner vijandSpawner;
	Logo pause, fastForward;
	Knop pauseKnop, fastForwardKnop;

	public static void main(String[] args) {
		Spel spel = new Spel();
		spel.runSketch();
	}

	@Override
	public void setupGame() {

		

		instellingenScherm = new InstellingenScherm(this, soundHandler);
		menuScherm = new MenuScherm(this);
		spelScherm = new SpelScherm(this);
		scoreScherm = new ScoreScherm(this);
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
				isGameOver();
			}

			verdedigersLoop();

			break;
			
		case SCORESCHERM:

			break;

		}
	}

	public void bepaalScherm() {

		switch (state) {
		case MENUSCHERM:
			deleteAllGameOBjects();
			
			view.setBackground(loadImage(MEDIA_URL.concat("MenuBackground.png")));

			addGameObject(menuScherm.getInstellingenKnop());
			addGameObject(menuScherm.getStartKnop());

			break;

		case INSTELLINGENSCHERM:
			deleteAllGameOBjects();
			
			view.setBackground(loadImage(MEDIA_URL.concat("InstellingenBackground.png")));
			


			this.deleteAllGameObjectsOfType(Knop.class);
			for (Knop i : instellingenScherm.getKnoppen()) {
				addGameObject(i);
			}

			view.setBackground(loadImage(MEDIA_URL.concat("InstellingenBackground.png")));

			break;

		case SPELSCHERM:
			deleteAllGameOBjects();
			
			view.setBackground(loadImage(MEDIA_URL.concat("SpelBackground.png")));
			geld = 100;
			levens = 10;
			
			initializeTileMap();
			initializeVijandSpawner();
			initializeMessage();
			
			showBuildScreenDashboard();
			showGameDetailDashboard();

			vijandSpawner.beginAlarmGolf();
			vijandSpawner.beginAlarmTimer();

			break;

		case SCORESCHERM:
			deleteAllGameOBjects();
			clearTileMap();
			
			if (vijandSpawner.isSpelKlaar()) {
				view.setBackground(loadImage(MEDIA_URL.concat("ScoreBackground2.png")));
			} else {
				view.setBackground(loadImage(MEDIA_URL.concat("ScoreBackground1.png")));
			}
			
			addGameObject(scoreScherm.getTerugKnop());
			addGameObject(aantalVijandenTO, 240, 470);
			
			break;

		default:
			break;
		}
	}

	// Initialiseer functies
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

	public void clearTileMap(){
		int tilesMap[][] = { 
			{ -1 }
		};
		tileMap.setTileMap(tilesMap);
	}

	public void initializeVijandSpawner() {
		// 0 = Relschopper, 1 = Antivaxmoeder, 2 = Urker, 3 = FrangeLans, 4 = Berry Thiaudet.
		int vijandMap[][] = { 
				{ 0, 0, 0, 0, 1 }, { 0, 1, 0, 1, 0 }, { 2, 1, 0, 0, 1 }, { 0, 0, 0, 1, 0, 0, 0}, { 2, 0, 2, 0, 2 },
				{ 1, 3, 1}, { 0, 2, 1, 2, 0 }, { 1, 3, 1, 3, 1 }, { 0, 1, 2, 3}, { 2, 2, 2, 0, 1},
				{ 4, 0, 2, 0, 2}, { 0, 2, 0, 2, 0}, { 1, 3, 2, 2, 1}, { 0, 1, 1, 2, 1}, { 2, 2, 0, 0, 1},
				{ 4, 3, 0, 0, 0}, { 2, 1, 2, 0, 0}, { 0, 1, 2, 4, 0}, { 1, 0, 0, 0, 1}, { 2, 3, 4, 0, 0},
				{ 4, 3, 2, 2, 2}, { 1, 1, 3, 2, 2, 1, 2}, { 4, 3, 2, 1, 2, 1, 2}, { 3, 2, 3, 2, 1, 0, 1}, { 4, 3, 2, 1, 0, 1, 2, 3, 4}
			};
		int vijandSpawnX = 15;
		int vijandSpawnY = 2;
		int tijdTussenVijanden = 1;
		int tijdTussenGolven = 15;

		vijandSpawner = new VijandSpawner(this, vijandMap, vijandSpawnX, vijandSpawnY, tijdTussenVijanden,
				tijdTussenGolven);
	}

	private void showGameDetailDashboard() {
		pause = new Logo(new Sprite(MEDIA_URL.concat("pause.png")));
		addGameObject(pause, 10, 640);
		
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

	private void showBuildScreenDashboard() {
		for (int i = 0; i < buildScreen.Buildables.size(); i++) {

			int x = buildScreen.getX() + (BOUWSCHERMXOFFSET * (i % 2)) + 12;
			int y = ((i / 2) | 0) * BOUWSCHERMXOFFSET + 15;

			addGameObject(buildScreen.Buildables.get(i), x, y, 101);

			TextObject naam = new TextObject(buildScreen.Buildables.get(i).getVerdediger().getNaam(),
					BOUWSCHERMFONTSIZE);
			naam.setForeColor(255, 255, 255, 255);
			x = buildScreen.getX() + (BOUWSCHERMXOFFSET * (i % 2)) + 12;
			y = BOUWSCHERMTEXTOFFSET + ((i / 2) | 0) * BOUWSCHERMXOFFSET + 15;
			addGameObject(naam, x, y, 101);

			TextObject prijs = new TextObject(
					"Cost: " + Integer.toString(buildScreen.Buildables.get(i).getVerdediger().getPrijs()),
					BOUWSCHERMFONTSIZE);
			prijs.setForeColor(255, 255, 255, 255);
			x = buildScreen.getX() + (BOUWSCHERMXOFFSET * (i % 2)) + 12;
			y = 20 + BOUWSCHERMTEXTOFFSET + ((i / 2) | 0) * BOUWSCHERMXOFFSET + 15;
			addGameObject(prijs, x, y, 101);
		}
	}

	private void initializeMessage() {
		messageTO = new TextObject("", 22);
		messageTO.setForeColor(35, 35, 255, 255);
		addGameObject(messageTO, (worldWidth / 2) - 175, 20);
	}

	private void showMessage(String x) {
		messageTO.setText(x);
	}

	// Update functies
	public void ontvangSchade(int schade) {
		levens -= schade;
	}

	public void voegGeldToe(int geld) {
		this.geld = this.geld + geld;
	}

	private void isGameOver() {
		if(levens < 1 || vijandSpawner.isSpelKlaar()) {
			vijandSpawner.pauseAlarms();
			state = SCORESCHERM;
			bepaalScherm();
		}
	}

	private void updateGameDetailDashboard() {
		if (levensTO != null) {
			levensTO.setText("Levens = " + levens);
			geldTO.setText("$" + geld);
			aantalVijandenTO.setText("Vijanden verslagen = " + vijandSpawner.getAantalVerslagen());
			timerTO.setText("Volgende golf: " + vijandSpawner.getGolfTimer());
		}
	}

	public void verdedigersLoop() {
		for (Verdediger v : verdedigersLijst.getVerdedigers()) {

			v.laatsteAanvaltijd++;

			if (v.laatsteAanvaltijd > v.herlaadTijd) {

				for (Vijand j : vijandSpawner.getVijanden()) {

					if (j.getCenterX() < v.getCenterX() + v.radius && j.getCenterX() > v.getCenterX() - v.radius
							&& j.getCenterY() < v.getCenterY() + v.radius
							&& j.getCenterY() > v.getCenterY() - v.radius) {
						j.krijgSchade(v.aanvalsKracht);
						v.laatsteAanvaltijd = 0;
					}
					break;

				}
				if (v.laatsteAanvaltijd < 1) {
					v.setAanvalsSprite();
				}

				if (v.laatsteAanvaltijd >= 4) {
					v.setNormaleSprite();
				}
			}

		}

	}

	// Muis functies
	public void mouseClicked() {
		switch (state) {
		case MENUSCHERM:

			if (menuScherm.getInstellingenKnop().isKnopGeklikt()) {
				state = INSTELLINGENSCHERM;
				bepaalScherm();
			}
			if (menuScherm.getStartKnop().isKnopGeklikt()) {
				state = SPELSCHERM;
				bepaalScherm();
			}
			break;

		case INSTELLINGENSCHERM:
			if (instellingenScherm.getMuziekKnop().isKnopGeklikt()) {
				soundHandler.playMusic(this, backgroundSound);
				instellingenScherm.setMuziekKnopTekst();
			}
			if (instellingenScherm.getGeluidKnop().isKnopGeklikt()) {
				
			}
			if (instellingenScherm.getBackKnop().isKnopGeklikt()) {
				state = MENUSCHERM;
				bepaalScherm();
			}
			break;
			
		case SPELSCHERM:
			
			if (spelScherm.getPauseKnop().isKnopGeklikt()){
				if (getThreadState()) {
					resumeGame();
					vijandSpawner.resumeAlarms();
					pause.setSprite(new Sprite(MEDIA_URL.concat("pause.png")));
				} else {
					pauseGame();
					vijandSpawner.pauseAlarms();
					pause.setSprite(new Sprite(MEDIA_URL.concat("play.png")));
				}
			}
			
			for (Buildable i : buildScreen.Buildables) {

				if (mouseX > i.getX() && mouseX < (i.getX() + i.getWidth()) && mouseY > i.getY()
						&& mouseY < (i.getY() + i.getHeight())) {
					this.selectedVerdediger = i.getVerdediger();
					showMessage(i.getVerdediger().getNaam() + " geselecteerd");

				}

			}

			if (selectedVerdediger != null && mouseX < (16 * tileMap.getTileSize())
					&& mouseY < (10 * tileMap.getTileSize())) {
				if (tileMap.getTileOnPosition(mouseX, mouseY) != null) {
					if (tileMap.getTileOnPosition(mouseX, mouseY) instanceof GrasTile) {
						if (geld - selectedVerdediger.prijs > -1) {
							showMessage(selectedVerdediger.naam + " geplaatst");
							verdedigersLijst.addVerdediger((new Verdediger(selectedVerdediger)),
									tileMap.getTilePixelLocation(tileMap.getTileOnPosition(mouseX, mouseY)));
							geld -= selectedVerdediger.prijs;
							selectedVerdediger = null;

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
			
		case SCORESCHERM:
			if (scoreScherm.getTerugKnop().isKnopGeklikt()){
				vijandSpawner = null;
				state = MENUSCHERM;
				bepaalScherm();
			}
			break;
		default:
			state = MENUSCHERM;
			break;
		}
	}
}
