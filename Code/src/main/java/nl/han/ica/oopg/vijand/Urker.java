package nl.han.ica.oopg.vijand;

import nl.han.ica.oopg.game.Spel;
import nl.han.ica.oopg.objects.Sprite;

public class Urker extends Vijand {
	private static int levens = 150;
	private static int snelheid = 2;
	private static int beloning = 10;
	private static int aanvalsKracht = 2;
	
	public Urker(Spel spel) {
		super(spel, new Sprite(Spel.MEDIA_URL.concat("Urker.png")), levens, snelheid, beloning, aanvalsKracht);
	}

}
