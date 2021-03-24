package nl.han.ica.oopg.tile;

import nl.han.ica.oopg.objects.Sprite;

public class WegTile extends Tile {
	private WegTile volgendeWeg;
	
	public WegTile(Sprite sprite) {
		super(sprite);
	}
	
	public WegTile getVolgendeWeg(){
		return volgendeWeg;
	}
	
	public void setVolgendeWeg(WegTile volgendeWeg) {
		this.volgendeWeg = volgendeWeg;
	}
}
