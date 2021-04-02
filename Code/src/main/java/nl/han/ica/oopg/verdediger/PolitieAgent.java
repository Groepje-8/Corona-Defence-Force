package nl.han.ica.oopg.verdediger;

import nl.han.ica.oopg.game.Spel;
import nl.han.ica.oopg.objects.Sprite;

public class PolitieAgent extends Verdediger implements IAanval {

	private static int radius = 250;
	private static int prijs = 30;
	private static int schade = 5;
	private static int herlaadTijd = 18;

	private static String naam = "Politie Agent";

	public PolitieAgent() {
		super(new Sprite(Spel.MEDIA_URL.concat("PolitieAgent1.png")),
				new Sprite(Spel.MEDIA_URL.concat("PolitieAgent2.png")), prijs, radius, schade, naam, 0, herlaadTijd);

	}

	@Override
	public int schieten() {
		// TODO Auto-generated method stub
		laatsteAanvaltijd = 0;
		return schade;
	}

}
