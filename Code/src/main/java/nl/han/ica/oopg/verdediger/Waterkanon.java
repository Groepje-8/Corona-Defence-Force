package nl.han.ica.oopg.verdediger;

import nl.han.ica.oopg.game.Spel;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.vijand.Vijand;

public class Waterkanon extends Verdediger implements IAanval {
	private static int radius = 250;
	private static int prijs = 30;
	private static int schade = 5;
	private static int herlaadTijd = 18;
	private static String naam = "Waterkanon";

	public Waterkanon() {
		super(new Sprite(Spel.MEDIA_URL.concat("AntivaxMoeder.png")), prijs, radius, schade, naam, 0, herlaadTijd);

	}

	@Override
	public void schieten(Vijand vijand) {
		// TODO Auto-generated method stub
		vijand.krijgSchade(schade);
		laatsteAanvaltijd = 0;
	}

}
