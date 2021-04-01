package nl.han.ica.oopg.vijand;

import nl.han.ica.oopg.game.Spel;
import nl.han.ica.oopg.objects.Sprite;

public class Relschopper extends Vijand {
	private static int levens = 50;
	private static int snelheid = 3;
	private static int beloning = 5;
	private static int aanvalsKracht = 1;
	
	public Relschopper(Spel spel) {
		super(spel, new Sprite(Spel.MEDIA_URL.concat("Relschopper.png")), levens, snelheid, beloning, aanvalsKracht);
	}
}
