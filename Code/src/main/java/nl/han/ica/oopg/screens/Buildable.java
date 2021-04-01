package nl.han.ica.oopg.screens;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import nl.han.ica.oopg.verdediger.Verdediger;

public class Buildable extends SpriteObject{
	Verdediger verdediger;

	
	
	Buildable(Sprite sprite,Verdediger verdediger){
		super(sprite);
		super.getImage().resize(90, 90);
		super.setHeight(120);
		super.setWidth(110);

		this.verdediger = verdediger;

		
		
	}
	
	@Override
	public void update(){}
	
	public Verdediger getVerdediger() {
		return verdediger;
	}
	
	
	
	

}
