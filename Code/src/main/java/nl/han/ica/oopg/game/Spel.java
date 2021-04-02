package nl.han.ica.oopg.game;


import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.ica.oopg.screens.BuildScreen;
import nl.han.ica.oopg.screens.Buildable;
import nl.han.ica.oopg.screens.InstellingenScherm;
import nl.han.ica.oopg.screens.Knop;
import nl.han.ica.oopg.screens.MenuScherm;
import nl.han.ica.oopg.screens.ScoreScherm;
import nl.han.ica.oopg.screens.SpelScherm;
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

	public static String MEDIA_URL = "src/main/java/media/";
	//public static String MEDIA_URL = "C:\\\\Users\\\\Joria\\\\Documents\\\\GitHub\\\\Corona-Defence-Force\\\\Code\\\\src\\\\main\\\\java\\\\media\\";
	
	private final int MENUSCHERM = 0;
	private final int INSTELLINGENSCHERM = 1;
	private final int SCORESCHERM = 2;
	private final int SPELSCHERM = 3;
	private int state;
	
	private final int BUILDSCREENXOFFSET = 135;
	private final int BUILDSCREENTEXTOFFSET = 90;
	private final int BUILDSCREENFONTSIZE = 16;
	private final int TILESIZE = 64;
	
	private MenuScherm menuScherm;
	private InstellingenScherm instellingenScherm;
	private SpelScherm spelScherm;
	private ScoreScherm scoreScherm;
	
	private VerdedigersLijst verdedigersLijst;
	private BuildScreen buildScreen;
	private Verdediger selectedVerdediger;
	public Sound backgroundSound;

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
		state = MENUSCHERM;
		
		menuScherm = new MenuScherm(this);
		instellingenScherm = new InstellingenScherm(this);
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
			
            addGameObject(instellingenScherm.getMuziekKnop());
			addGameObject(instellingenScherm.getGeluidKnop());
			addGameObject(instellingenScherm.getBackKnop());
			
			break;

		case SPELSCHERM:
			deleteAllGameOBjects();
			
			view.setBackground(loadImage(MEDIA_URL.concat("SpelBackground.png")));
			geld = 100;
			levens = 1;
			
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
			}
			else {
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

		vijandSpawner = new VijandSpawner(this, vijandMap, vijandSpawnX, vijandSpawnY, tijdTussenVijanden, tijdTussenGolven);
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

			int x = buildScreen.getX() + (BUILDSCREENXOFFSET * (i % 2)) + 12;
			int y = ((i / 2) | 0) * BUILDSCREENXOFFSET + 15;

			addGameObject(buildScreen.Buildables.get(i), x, y, 101);

			TextObject naam = new TextObject(buildScreen.Buildables.get(i).getVerdediger().getNaam(), BUILDSCREENFONTSIZE);
			naam.setForeColor(255, 255, 255, 255);
			x = buildScreen.getX() + (BUILDSCREENXOFFSET * (i % 2)) + 12;
			y = BUILDSCREENTEXTOFFSET + ((i / 2) | 0) * BUILDSCREENXOFFSET + 15;
			addGameObject(naam, x, y, 101);

			TextObject prijs = new TextObject("Cost: " + Integer.toString(buildScreen.Buildables.get(i).getVerdediger().getPrijs()),
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
						//verdedigersLijst.getVerdedigers().get(i).schieten(vijandSpawner.getVijanden().get(j));
						System.out.println(vijandSpawner.getVijanden().get(j).getLevens());
						System.out.println(vijandSpawner.getVijanden().get(j).isLevend());
						break;

					}

				}
			}
		}
	}
	
	// Knop functies
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
				instellingenScherm.soundHandler(this, backgroundSound);
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
				}
				else {
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
					//clearMessage();

				}

			}
		

			if (selectedVerdediger != null && mouseX < (16 * tileMap.getTileSize())
					&& mouseY < (10 * tileMap.getTileSize())) {
				if (tileMap.getTileOnPosition(mouseX, mouseY) != null) {
					if (tileMap.getTileOnPosition(mouseX, mouseY) instanceof GrasTile) {
						if (geld - selectedVerdediger.prijs >= 0) {
							showMessage(selectedVerdediger.naam + " geplaatst");
							verdedigersLijst.addVerdediger(new Verdediger(selectedVerdediger),
									tileMap.getTilePixelLocation(tileMap.getTileOnPosition(mouseX, mouseY)));
							geld -= selectedVerdediger.prijs;
							//clearMessage();
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
