package nl.han.ica.oopg.screens;

import nl.han.ica.oopg.game.Spel;
import nl.han.ica.oopg.objects.GameObject;
import processing.core.PGraphics;

public class Knop extends GameObject {

	private int fontSize;
	private String text;
	private int r = 146;
	private int g = 20;
	private int b = 12;
	private int alpha = 255;

	
	private Spel spel;

	public Knop(int x, int y, int breedte, int hoogte, String text, Spel spel) {
		super(x, y, breedte, hoogte);
		this.text = text;
		this.spel = spel;
		
		fontSize = 30;
	}

	@Override
	public void draw(PGraphics g) {
		
		g.fill(this.r, this.g, this.b, this.alpha);
		g.textAlign(g.LEFT, g.TOP);
		g.textSize(fontSize);
		g.text(text, x, y);
	}
	
	@Override
	public void update() {

	}
	public void setText(String text) {
		this.text = text;
	}
	
	public void setForeColor(int r, int g, int b, int alpha) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.alpha = alpha;
	}
	
	public boolean isKnopGeklikt() {
		if (spel.mouseX > x && spel.mouseX < (x + width) && spel.mouseY > y && spel.mouseY < (y + height)) {
			return true;
		}
		return false;
	}

}
