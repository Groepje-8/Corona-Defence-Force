package nl.han.ica.oopg.vijand;

import nl.han.ica.oopg.objects.Sprite;

public class Vijand {
	private int levens, snelheid, beloning, x, y;
	private boolean levend, bevries;
	private Sprite plaatje;
	private double laatsteLoopTijd;
	
	public Vijand(int levens, int snelheid, int beloning, Sprite sprite) {
		this.levens = levens;
		this.snelheid = snelheid;
		this.beloning = beloning;
		levend = true;
		bevries = false;
		//x = 
	}
	
	public void lopen() {
		
	}
	
	public void krijgSchade(int schade) {
		levens -= schade;
	}
	
	int getBeloning() {
		return beloning;
	}
	
	
}
