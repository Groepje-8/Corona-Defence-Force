package nl.han.ica.oopg.verdediger;

import nl.han.ica.oopg.game.Spel;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.vijand.Vijand;

public class Sniper extends Verdediger implements IAanval{
	private static int radius = 350;
	private static int prijs = 30;
	private static int schade = 10;
	private static int herlaadTijd = 30;
	private static String naam = "Sniper";
	
	public Sniper() {
		super(new Sprite(Spel.MEDIA_URL.concat("Sniper1.png")), prijs, radius, schade, naam, 0, herlaadTijd);
	}
	
	@Override
	public void schieten(Vijand vijand) {
		// TODO Auto-generated method stub
		vijand.krijgSchade(schade);
		laatsteAanvaltijd = 0;
	}

}
