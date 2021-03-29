package nl.han.ica.oopg.verdediger;

import nl.han.ica.oopg.tile.TileType;
import processing.core.PGraphics;
import nl.han.ica.oopg.tile.Tile;
import nl.han.ica.oopg.tile.TileMap;

import java.util.ArrayList;

import nl.han.ica.oopg.objects.SpriteObject;

public class VerdedigersMap extends TileMap {
	ArrayList<SpriteObject> Verdedigers = new ArrayList<SpriteObject>();
	private int[][] indexMap;
	private Verdediger[][] verdedigerMap;

	VerdedigersMap(int tileSize, TileType[] tileTypes, int[][] indexMap) {
		super(tileSize, tileTypes, indexMap);

	}

	public void addVerdediger(SpriteObject verdediger, int x, int y) {
//		super.se
		Verdedigers.add(verdediger);
	}


	 public void draw(PGraphics pGraphics) {
	    	
	        if (verdedigerMap != null && indexMap != null) {
	            for (int i = 0; i < verdedigerMap.length; i++) {
	                for (int j = 0; j < verdedigerMap[i].length; j++) {
	                    pGraphics.image(verdedigerMap[i][j].getSprite(), j * super.getTileSize(), i * super.getTileSize());
	                }
	            }
	        }
	    }

	public Verdediger getVerdedigerOnIndex(int x, int y) {
		return verdedigerMap[x][y];
	}

	public Verdediger getVerdedigerOnPosition(int x, int y) {
		return getVerdedigerOnIndex(x * super.getTileSize(), y * super.getTileSize());
	}

}
