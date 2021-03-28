package nl.han.ica.oopg.vijand;

import nl.han.ica.oopg.game.Spel;
import nl.han.ica.oopg.objects.Sprite;

public class Relschopper extends Vijand {
	private static int levens = 50;
	private static int snelheid = 2;
	private static int beloning = 10;
	
	public Relschopper(Spel spel) {
		super(spel, new Sprite(Spel.MEDIA_URL.concat("Relschopper.png")), levens, snelheid, beloning);
	}
}
