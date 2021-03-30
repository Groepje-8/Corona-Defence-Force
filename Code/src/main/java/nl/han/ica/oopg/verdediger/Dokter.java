package nl.han.ica.oopg.verdediger;

import nl.han.ica.oopg.game.Spel;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.vijand.Vijand;

public class Dokter extends Verdediger implements IAanval{
	private static int radius = 150;
	private static int prijs = 40;
	private static int schade = 9;
	private static int herlaadTijd = 10;
	private static String naam = "Dokter";

	public Dokter() {
		super(new Sprite(Spel.MEDIA_URL.concat("Dokter1.png")), prijs, radius, schade, naam, 0, herlaadTijd);
		
	}
	@Override
	public void schieten(Vijand vijand) {
		// TODO Auto-generated method stub
		vijand.krijgSchade(schade);
		laatsteAanvaltijd = 0;
	}

}
