package nl.han.ica.oopg.vijand;

import nl.han.ica.oopg.game.Spel;
import nl.han.ica.oopg.objects.Sprite;

public class Relschopper extends Vijand {

	public Relschopper() {
		super(50, 5, 10, new Sprite(Spel.MEDIA_URL.concat("Relschopper.png")));
	}
}
