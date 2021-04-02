package nl.han.ica.oopg.verdediger;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import processing.core.PImage;

public class Verdediger extends SpriteObject {

	public int prijs, radius, aanvalsKracht;
	public float x, y;
	public String naam;
	public boolean geschoten;
	public double laatsteAanvaltijd, herlaadTijd;
	public Sprite normaleSprite;
	public Sprite aanvalsSprite;

	Verdediger(Sprite currentSprite, Sprite nextSprite, int prijs, int radius, int aanvalsKracht, String naam,
			double laatsteAanvaltijd, double herlaadTijd) {
		super(currentSprite);
		this.normaleSprite = currentSprite;
		this.aanvalsSprite = nextSprite;
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

	public Verdediger(Verdediger copy) {
		super(new Sprite(copy.getImage()));
		this.normaleSprite = copy.normaleSprite;
		this.aanvalsSprite = copy.aanvalsSprite;
		this.x = copy.getCenterX();
		this.y = copy.getCenterY();
		this.prijs = copy.prijs;
		this.radius = copy.radius;
		this.aanvalsKracht = copy.aanvalsKracht;
		this.naam = copy.naam;
		this.laatsteAanvaltijd = copy.laatsteAanvaltijd;
		this.herlaadTijd = copy.herlaadTijd;
		// resize();
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

	public void setAanvalsSprite() {
		super.setSprite(this.aanvalsSprite);
		// resize();
	}

	public void setNormaleSprite() {
		super.setSprite(this.normaleSprite);
		// resize();
	}

	public String getNaam() {
		return this.naam;
	}

	public int getPrijs() {
		return this.prijs;
	}

	public boolean getGeschoten() {
		return geschoten;
	}

	public void setGeschoten(Boolean bool) {
		this.geschoten = bool;
	}

	@Override
	public void update() {

	}

}
