package nl.han.ica.oopg.vijand;

import nl.han.ica.oopg.game.Spel;
import nl.han.ica.oopg.objects.Sprite;

public class FrangeLans extends Vijand{
	private static int levens = 125;
	private static int snelheid = 2;
	private static int beloning = 15;
	private static int aanvalsKracht = 2;
	
	private int versterkRadius;
	
	public FrangeLans(Spel spel) {
		super(spel, new Sprite(Spel.MEDIA_URL.concat("FrangeLans.png")), levens, snelheid, beloning, aanvalsKracht);
		
	}
	
	public void versterkVijanden() {
		
	}
}
