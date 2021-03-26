package nl.han.ica.oopg.verdediger;

import nl.han.ica.oopg.objects.Sprite;
import processing.core.PImage;

public class PolitieAgent extends Verdediger implements IAanval {
	public int schade;

	public PolitieAgent(int prijs, String naam, int radius, double laatsteAanvaltijd, double herlaadTijd, int schade) {
		super(prijs, new Sprite(
				"C:\\Users\\Joria\\Documents\\GitHub\\Corona-Defence-Force\\Code\\src\\main\\java\\media\\cop.png"),
				naam, radius, laatsteAanvaltijd, herlaadTijd);

		this.schade = schade;

	}
	

	public void schieten() {

	}

}
