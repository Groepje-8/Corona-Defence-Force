package nl.han.ica.oopg.verdediger;

import nl.han.ica.oopg.game.Spel;
import nl.han.ica.oopg.objects.Sprite;

public class Dokter extends Verdediger implements IAanval {
	private static int radius = 150;
	private static int prijs = 40;
	private static int schade = 9;
	private static int herlaadTijd = 10;
	private static String naam = "Dokter";

	public Dokter() {
		super(new Sprite(Spel.MEDIA_URL.concat("Dokter1.png")), new Sprite(Spel.MEDIA_URL.concat("Dokter2.png")), prijs,
				radius, schade, naam, 0, herlaadTijd);

	}

	@Override
	public int schieten() {
		// TODO Auto-generated method stub
		laatsteAanvaltijd = 0;
		return schade;
	}

}
