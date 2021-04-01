package nl.han.ica.oopg.verdediger;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import processing.core.PImage;

public class Verdediger extends SpriteObject {

	public int prijs, radius, aanvalsKracht;
	public float x, y;
	public String naam;
	public boolean bevroren;
	public double laatsteAanvaltijd, herlaadTijd;
	
	Verdediger(Sprite sprite, int prijs, int radius, int aanvalsKracht, String naam, double laatsteAanvaltijd, double herlaadTijd) {
		super(sprite);
		super.setHeight(90);
		super.setWidth(90);
		this.x = getCenterX();
		this.y = getCenterY();
		this.prijs = prijs;
		this.radius = radius;
		this.aanvalsKracht = aanvalsKracht;
		this.naam = naam;
		this.laatsteAanvaltijd = laatsteAanvaltijd;
		this.herlaadTijd = herlaadTijd;
		resize();

	}
	public Verdediger(Verdediger copy){
		super(new Sprite(copy.getImage()));
		this.x = copy.getCenterX();
		this.y = copy.getCenterY();
		this.prijs = copy.prijs;
		this.radius = copy.radius;
		this.aanvalsKracht = copy.aanvalsKracht;
		this.naam = copy.naam;
		this.laatsteAanvaltijd = copy.laatsteAanvaltijd;
		this.herlaadTijd = copy.herlaadTijd;
		//resize();
	}


	public void kopen() {

	}
	private void resize() {
		super.getImage().resize(90, 90);
		
	}
	public void resize(int tileSize) {
		super.getImage().resize(tileSize, tileSize);
	}


	public PImage getSprite() {
		return super.getImage();
	}
	public String getNaam() {
		return this.naam;
	}
	public int getPrijs() {
		return this.prijs;
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
