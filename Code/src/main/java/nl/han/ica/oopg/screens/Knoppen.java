package nl.han.ica.oopg.screens;

import java.util.ArrayList;

import nl.han.ica.oopg.dashboard.Dashboard;

public class Knoppen extends Dashboard {
	ArrayList<Knop> knoppenLijst = new ArrayList<Knop>();
	int layerPosition = 100;

	Knoppen(float x, float y, float width, float heigth) {
		super(x, y, width, heigth);		
		this.setBackground(30, 30, 30);

	}

	public void addKnop(Knop knop,float layerPosition) {
		this.addGameObject(knop,layerPosition);
	}
	
	public void refresh(Knop knop) {
		for(Knop i :knoppenLijst) {
			if (i.equals(knop)) {
				i = knop;
			}
		}
	}
	public ArrayList<Knop> getKnoppen(){
		return knoppenLijst;
	}
	
	
}
