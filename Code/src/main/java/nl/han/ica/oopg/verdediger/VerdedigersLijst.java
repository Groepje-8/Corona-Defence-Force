package nl.han.ica.oopg.verdediger;

import processing.core.PVector;
import java.util.ArrayList;
import nl.han.ica.oopg.game.Spel;

public class VerdedigersLijst {

	private Spel spel;
	private int tileSize;
	private ArrayList<Verdediger> verdedigers = new ArrayList<Verdediger>();

	public VerdedigersLijst(Spel spel, int tileSize) {
		this.spel = spel;
		this.tileSize = tileSize;
	}

	public void addVerdediger(Verdediger verdediger, PVector xy) {

		// verdedigerMap[x / tileSize][y / tileSize] = verdediger;
		verdediger.resize(tileSize);
		verdedigers.add(verdediger);
		spel.addGameObject(verdedigers.get(verdedigers.size() - 1), xy.x, xy.y, 101);

	}

	public ArrayList<Verdediger> getVerdedigers() {
		return this.verdedigers;
	}

	public boolean isVerdediger(int x, int y) {
		boolean isAlEenVerdediger = false;
		for (Verdediger v : verdedigers) {
			if (v.getCenterX() == x && v.getCenterY() == y) {
				isAlEenVerdediger = true;
			}

		}
		return isAlEenVerdediger;
	}

}
