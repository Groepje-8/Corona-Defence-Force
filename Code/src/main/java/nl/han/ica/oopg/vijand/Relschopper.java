package nl.han.ica.oopg.vijand;

import nl.han.ica.oopg.game.Spel;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.TileMap;

public class Relschopper extends Vijand {

	public Relschopper(TileMap tileMap, Spel spel) {
		super(50, 1, 10, new Sprite(Spel.MEDIA_URL.concat("Relschopper.png")), tileMap, spel);
	}
}
