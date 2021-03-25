package nl.han.ica.oopg.game;

import nl.han.ica.oopg.objects.Sprite;
import processing.core.PImage;


public class PolitieAgent extends Verdediger implements IAanval{
	public int schade;
	
	
	
	
	PolitieAgent(int prijs,Sprite sprite,String naam,int radius,double laatsteAanvaltijd,double herlaadTijd,int schade){
		super(prijs,sprite,naam,radius,laatsteAanvaltijd,herlaadTijd);
		this.schade = schade;
		
	}
	public void schieten() {
		
	}
	

}
