package nl.han.ica.oopg.verdediger;


import processing.core.PGraphics;
import processing.core.PVector;
import java.util.ArrayList;
import nl.han.ica.oopg.game.Spel;


public class VerdedigersLijst {

	private Spel spel;
	private int tileSize;
	private Verdediger[][] verdedigerMap;
	private ArrayList<Verdediger> verdedigers = new ArrayList<Verdediger>();

	public VerdedigersLijst(Spel spel, int tileSize) {
		this.spel = spel;
		this.tileSize = tileSize;
		verdedigerMap = new Verdediger[tileSize][tileSize];
	}

	public void addVerdediger(Verdediger verdediger, PVector xy) {

		//verdedigerMap[x / tileSize][y / tileSize] = verdediger;
		verdediger.resize(tileSize);
		verdedigers.add(verdediger);
		spel.addGameObject(verdedigers.get(verdedigers.size() -1), xy.x, xy.y ,101);
		
	}

	public void draw(PGraphics pGraphics) {

		if (verdedigerMap != null) {
			for (int i = 0; i < verdedigerMap.length; i++) {
				for (int j = 0; j < verdedigerMap[i].length; j++) {
					if (verdedigerMap[i][j] instanceof Verdediger) {
						pGraphics.image(verdedigerMap[i][j].getSprite(), j * tileSize, i * tileSize);
					}

				}
			}
		}
	}
	
	public ArrayList<Verdediger> getVerdedigers(){
		return this.verdedigers;
	}

	public boolean isVerdediger(int x, int y) {
		if (getVerdedigerOnPosition(x, y) instanceof Verdediger) {
			return true;
		} else {
			return false;
		}
	}

	public Verdediger getVerdedigerOnIndex(int x, int y) {
		return verdedigerMap[x][y];
	}

	public Verdediger getVerdedigerOnPosition(int x, int y) {
		return getVerdedigerOnIndex(x / tileSize, y / tileSize);
	}

	public void print() {
		if (verdedigerMap != null) {
			for (int i = 0; i < verdedigerMap.length; i++) {
				for (int j = 0; j < verdedigerMap[i].length; j++) {
					if (verdedigerMap[i][j] instanceof Verdediger) {
						System.out.println(verdedigerMap[i][j].naam);
					}

				}
			}
		}
	}

}
