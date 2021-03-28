package nl.han.ica.oopg.vijand;

import nl.han.ica.oopg.game.Spel;
import nl.han.ica.oopg.objects.Sprite;

public class Relschopper extends Vijand {

	public Relschopper(Spel spel) {
		//super(50, 2, 10, new Sprite("C:\\Users\\Joria\\Documents\\GitHub\\Corona-Defence-Force\\Code\\src\\main\\java\\media\\Relschopper.png"),tileMap, spel);
		super(50, 2, 10, new Sprite(Spel.MEDIA_URL.concat("Relschopper.png")), spel);

	}
}
