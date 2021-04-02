package nl.han.ica.oopg.screens;

import java.util.ArrayList;
import nl.han.ica.oopg.game.Spel;

public class InstellingenScherm {
	public Knop muziekKnop;
	public Knop geluidsKnop;
	public Knop backKnop;
	public ArrayList<Knop> knoppen = new ArrayList<Knop>();
	
	private final int xGeluidKnop = 260;
	private final int yGeluidKnop = 350;
	private final int xMuziekKnop = 260;
	private final int yMuziekKnop = 430;
	private final int xBackKnop = 290;
	private final int yBackKnop = 508;
	
	private final int buttonWidth = 300;
	private final int buttonHeight = 90;
	private Spel spel;
	private SoundHandler soundHandler;

	public InstellingenScherm(Spel spel ,SoundHandler soundHandler) {
		this.soundHandler = soundHandler;
		this.spel = spel;
		createMuziekKnop();
		createGeluidKnop();
		createBackKnop();
		knoppen.add(geluidsKnop);
		knoppen.add(muziekKnop);
		knoppen.add(backKnop);
	}

	

	public void createGeluidKnop() {
		geluidsKnop = new Knop(xGeluidKnop, yGeluidKnop, buttonWidth, buttonHeight, soundHandler.geluidTekst, spel);
		muziekKnop.setForeColor(146, 20, 12, 255);
	}

	public void createMuziekKnop() {
		muziekKnop = new Knop(xMuziekKnop, yMuziekKnop, buttonWidth, buttonHeight,soundHandler.muziekTekst, spel);
		muziekKnop.setForeColor(146, 20, 12, 255);
	}

	public void createBackKnop(){
		backKnop = new Knop(xBackKnop, yBackKnop, buttonWidth, buttonHeight,"Terug", spel);
		backKnop.setForeColor(146, 20, 12, 255);
	}
	public Knop getGeluidKnop() {
		return knoppen.get(0);
	}

	public Knop getMuziekKnop() {
		return knoppen.get(1);
	}

	public Knop getBackKnop() {
		return knoppen.get(2);
	}

	public ArrayList<Knop> getKnoppen() {
		return knoppen;
	}

	public void setMuziekKnopTekst() {	
		muziekKnop.setText(soundHandler.muziekTekst);

	}
}
