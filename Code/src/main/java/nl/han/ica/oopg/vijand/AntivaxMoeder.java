package nl.han.ica.oopg.vijand;

import nl.han.ica.oopg.game.Spel;
import nl.han.ica.oopg.objects.Sprite;

public class AntivaxMoeder extends Vijand {
	private static int levens = 75;
	private static int snelheid = 2;
	private static int beloning = 15;
	
	public AntivaxMoeder(Spel spel) {
		super(spel, new Sprite(Spel.MEDIA_URL.concat("AntivaxMoeder.png")), levens, snelheid, beloning);
		
	}

}
