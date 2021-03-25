package nl.han.ica.oopg.tile;

import nl.han.ica.oopg.objects.Sprite;
import processing.core.PVector;

public class WegTile extends Tile {
	private int richting = 0;
	private boolean laatsteWeg = false;
	
	public WegTile(Sprite sprite) {
		super(sprite);
	}

	public void setLaatsteWegTrue() {
		laatsteWeg = true;
	}

	public int getRichting() {
		return richting;
	}

	public void setRichting(int richting){
		this.richting = richting;
	}
	
}
