package nl.han.ica.oopg.verdediger;

import nl.han.ica.oopg.game.Spel;
import nl.han.ica.oopg.objects.Sprite;

public class Sniper extends Verdediger implements IAanval {
	private static int radius = 350;
	private static int prijs = 30;
	private static int schade = 10;
	private static int herlaadTijd = 30;
	private static String naam = "Sniper";

	public Sniper() {
		super(new Sprite(Spel.MEDIA_URL.concat("Sniper1.png")), new Sprite(Spel.MEDIA_URL.concat("Sniper2.png")), prijs,
				radius, schade, naam, 0, herlaadTijd);
	}

	@Override
	public int schieten() {
		// TODO Auto-generated method stub
		laatsteAanvaltijd = 0;
		return schade;
	}

	@Override
	public void verdedigersAnimatie() {
		if (laatsteAanvaltijd < 4) {
			this.setSprite(new Sprite(Spel.MEDIA_URL.concat("Sniper2.png")));
		} else {
			this.setSprite(new Sprite(Spel.MEDIA_URL.concat("Sniper1.png")));

		}
	}

}
