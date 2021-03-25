package nl.han.ica.oopg.vijand;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

public class Vijand extends SpriteObject {
	private int levens, snelheid, beloning;
	private boolean levend, bevries;
	private Sprite plaatje;
	private double laatsteLoopTijd;
	
	public Vijand(int levens, int snelheid, int beloning, Sprite sprite) {
		super(sprite);
		this.levens = levens;
		this.snelheid = snelheid;
		this.beloning = beloning;
		levend = true;
		bevries = false;
		
	}
	
	public void lopen() {
		
	}
	
	public void krijgSchade(int schade) {
		levens -= schade;
	}
	
	int getBeloning() {
		return beloning;
	}
	
	public void tekenVijand() {
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
}
