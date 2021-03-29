package nl.han.ica.oopg.vijand;

import java.util.List;

import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.game.Spel;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import nl.han.ica.oopg.tile.SupermarktTile;

public class Vijand extends SpriteObject implements ICollidableWithTiles {
	private int levens, beloning;
	private float xMidden, yMidden;
	private boolean levend, supermarktBereikt, bevries;
	private double laatsteLoopTijd;
	private Spel spel;
	
	
	public Vijand(Spel spel, Sprite sprite, int levens, int snelheid, int beloning) {
		super(sprite);
		setSpeed(snelheid);
		setDirection(270);
		this.levens = levens;
		this.beloning = beloning;
		this.spel = spel;
		levend = true;
		supermarktBereikt = false;
		bevries = false;
	}
	
	@Override
	public void update() {
		lopenLangsPad();
		if (this.levens < 1) {
			levend = false;
		}
		
	}
	
	public void lopenLangsPad() {
		xMidden = getCenterX();
		yMidden = getCenterY();
		
		changeDirectionOnCoordinates(13, 2, 180);
		changeDirectionOnCoordinates(13, 7, 270);
		changeDirectionOnCoordinates(9, 7, 0);
		changeDirectionOnCoordinates(9, 4, 90);
		changeDirectionOnCoordinates(11, 4, 0);
		changeDirectionOnCoordinates(11, 1, 270);
		changeDirectionOnCoordinates(4, 1, 180);
		changeDirectionOnCoordinates(4, 4, 90);
		changeDirectionOnCoordinates(6, 4, 180);
		changeDirectionOnCoordinates(6, 7, 270);
		changeDirectionOnCoordinates(1, 7, 0);
	}
	
	private void changeDirectionOnCoordinates(int x, int y, int richting) {
		int[] Coordinaten = {x * 64 + 30, x * 64 + 34, y * 64 + 30, y * 64 + 34};
		if (xMidden > Coordinaten[0] && xMidden < Coordinaten[1] && yMidden > Coordinaten[2] && yMidden < Coordinaten[3]) {
			setDirection(richting);
		}
	}
	public int getLevens() {
		return levens;
	}
	public void krijgSchade(int schade) {
		levens -= schade;
	}
	
	public boolean isSupermarktBereikt() {
		return supermarktBereikt;
	}
	
	public boolean isLevend() {
		return levend;
	}
	
	int getBeloning() {
		return beloning;
	}
	public float getXMidden() {
		return this.xMidden;
	}
	public float getYMidden() {
		return this.yMidden;
	}

	
	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
        for (CollidedTile ct : collidedTiles) {
            if (ct.getTile() instanceof SupermarktTile) {
                try {
                	supermarktBereikt = true;
                } 
                catch (TileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }  
    }
	
}
