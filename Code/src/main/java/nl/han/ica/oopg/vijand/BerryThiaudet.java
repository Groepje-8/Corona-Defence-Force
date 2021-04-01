package nl.han.ica.oopg.vijand;

import nl.han.ica.oopg.game.Spel;
import nl.han.ica.oopg.objects.Sprite;

public class BerryThiaudet extends Vijand {
	private static int levens = 250;
	private static int snelheid = 2;
	private static int beloning = 20;
	private static int aanvalsKracht = 3;
	
	public BerryThiaudet(Spel spel) {
		super(spel, new Sprite(Spel.MEDIA_URL.concat("BerryThiaudet.png")), levens, snelheid, beloning, aanvalsKracht);
	}

}
