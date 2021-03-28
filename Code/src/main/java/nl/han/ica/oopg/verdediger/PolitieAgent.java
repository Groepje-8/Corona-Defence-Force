package nl.han.ica.oopg.verdediger;

import nl.han.ica.oopg.game.Spel;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PImage;

public class PolitieAgent extends Verdediger implements IAanval {
	
	private static int radius = 300;
	private static int prijs = 30;
	private static int aanvalsKracht = 10;
	private static String naam = "Politie Agent";
	
	public int schade; // waar is dit voor?
	
	public PolitieAgent(double laatsteAanvaltijd, double herlaadTijd, int schade) {
		super(new Sprite(Spel.MEDIA_URL.concat("cop.png")), prijs, radius, aanvalsKracht, naam, laatsteAanvaltijd, herlaadTijd);

		this.schade = schade;
	}
	
	public void schieten() {

	}

}
