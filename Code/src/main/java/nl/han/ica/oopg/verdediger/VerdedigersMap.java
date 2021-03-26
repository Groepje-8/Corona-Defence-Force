package nl.han.ica.oopg.verdediger;

import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.tile.TileMap;

import java.util.ArrayList;

import nl.han.ica.oopg.objects.SpriteObject;

public class VerdedigersMap extends TileMap {
	ArrayList<SpriteObject> Verdedigers = new ArrayList<SpriteObject>();

	VerdedigersMap(int tileSize, TileType[] tileTypes, int[][] indexMap) {
		super(tileSize, tileTypes, indexMap);

	}
	
	public void addVerdediger(SpriteObject verdediger,int x, int y){
//		super.se
		Verdedigers.add(verdediger);
	}

}
