package nl.han.ica.oopg.verdediger;

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
	public int x;
	public int y;

	Verdediger(int prijs, Sprite sprite, String naam, int radius, double laatsteAanvaltijd, double herlaadTijd) {
		super(sprite);
		this.prijs = prijs;
		this.naam = naam;
		this.radius = radius;
		this.laatsteAanvaltijd = laatsteAanvaltijd;
		this.herlaadTijd = herlaadTijd;
		resize();

	}


	public void kopen() {

	}
	private void resize() {
		super.getImage().resize(128, 128);
		
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
