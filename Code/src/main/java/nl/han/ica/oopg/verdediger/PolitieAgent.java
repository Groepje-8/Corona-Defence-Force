package nl.han.ica.oopg.verdediger;

import nl.han.ica.oopg.game.Spel;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.vijand.Vijand;


public class PolitieAgent extends Verdediger implements IAanval {
	
	private static int radius = 250;
	private static int prijs = 30;
	private static int aanvalsKracht = 5;
	private static String naam = "Politie Agent";
	
	public int schade; // waar is dit voor?
	
	public PolitieAgent(double herlaadTijd, int schade) {
		super(new Sprite(Spel.MEDIA_URL.concat("PolitieAgent1.png")), prijs, radius, aanvalsKracht, naam, 0, herlaadTijd);

		this.schade = schade;
	}
	


	@Override
	public void schieten(Vijand vijand) {
		// TODO Auto-generated method stub
		vijand.krijgSchade(schade);
		laatsteAanvaltijd = 0;
	}

}
