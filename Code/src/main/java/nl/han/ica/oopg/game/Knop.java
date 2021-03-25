package nl.han.ica.oopg.game;

import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.TextObject;
import processing.core.PGraphics;

public class Knop extends GameObject {

	private int fontSize;
	private String text;
	private int r = 146;
	private int g = 20;
	private int b = 12;
	private int alpha = 255;
	private int backgroundColor = 0;

	public Knop(int x, int y, int hoogte, int breedte, String text) {
		super(x, y, hoogte, breedte);
		this.text = text;
		fontSize = 20;

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

}
