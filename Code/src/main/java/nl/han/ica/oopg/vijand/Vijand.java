package nl.han.ica.oopg.vijand;

import java.util.List;

import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.game.Spel;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import nl.han.ica.oopg.tile.SupermarktTile;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.WegTile;
import processing.core.PVector;

public class Vijand extends SpriteObject implements ICollidableWithTiles {
	private int levens, beloning;
	private boolean levend, bevries;
	private Sprite plaatje;
	private double laatsteLoopTijd;
	private TileMap tileMap;
	Spel spel;
	private float xMidden, yMidden;
	
	public Vijand(int levens, int snelheid, int beloning, Sprite sprite, TileMap tileMap, Spel spel) {
		super(sprite);
		setSpeed(snelheid);
		setDirection(270);
		this.levens = levens;
		this.beloning = beloning;
		this.tileMap = tileMap;
		this.spel = spel;
		levend = true;
		bevries = false;
		
	}
	
	public void lopenLangsPad() {
		xMidden = getCenterX();
		yMidden = getCenterY();
		
		int[] array = getDirectionChangeBox(13, 2);
		if (xMidden > array[0] && xMidden < array[1] && yMidden > array[2] && yMidden < array[3]) {
			setDirection(180);
			System.out.println("Succes");
		}
		
	}
	
	private int[] getDirectionChangeBox(int x, int y) {
		int[] array = {x * 64 + 31, x * 64 + 33, y * 64 + 111, y * 64 + 113};
		
		return array;
	}
	
	public void krijgSchade(int schade) {
		levens -= schade;
	}
	
	public void collidedWithSupermarkt() {
		levend = false;
	}
	
	int getBeloning() {
		return beloning;
	}

	@Override
	public void update() {
		lopenLangsPad();
		
	}

	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
        for (CollidedTile ct : collidedTiles) {
            if (ct.getTile() instanceof SupermarktTile) {
                try {
                	levend = false;
                	System.out.println("DOOD");
                } 
                catch (TileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }  
    }
	
}
