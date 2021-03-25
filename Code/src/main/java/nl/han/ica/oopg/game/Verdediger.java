package nl.han.ica.oopg.game;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import processing.core.PImage;

public class Verdediger extends SpriteObject {

	public int prijs;
	public String naam;
	public int radius;
	public double laatsteAanvaltijd;
	public double herlaadTijd;
	public boolean bevroren;

	Verdediger(int prijs, Sprite sprite, String naam, int radius, double laatsteAanvaltijd, double herlaadTijd) {
		super(sprite);
		this.prijs = prijs;
		this.naam = naam;
		this.radius = radius;
		this.laatsteAanvaltijd = laatsteAanvaltijd;
		this.herlaadTijd = herlaadTijd;

	}

	public void kopen() {

	}

	public PImage getSprite() {
		return super.getImage();
	}

//	@Override
//	public void draw(PGraphics g) {
//
//	}
//
	@Override
	public void update() {

	}

}
