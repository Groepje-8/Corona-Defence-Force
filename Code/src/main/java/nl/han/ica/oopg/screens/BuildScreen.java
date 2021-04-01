package nl.han.ica.oopg.screens;
import java.util.ArrayList;


import nl.han.ica.oopg.dashboard.Dashboard;


import nl.han.ica.oopg.verdediger.Dokter;
import nl.han.ica.oopg.verdediger.PolitieAgent;
import nl.han.ica.oopg.verdediger.Verdediger;
import nl.han.ica.oopg.verdediger.Waterkanon;


public class BuildScreen {
	public ArrayList<Verdediger> Verdedigers = new ArrayList<Verdediger>();

	private int x = 1024;
	private int y = 0;
	private int width = 256;
	private int height = 720;
	
	public BuildScreen(){

		fillVerdedigers();
		
	}
	private void fillVerdedigers() {
		PolitieAgent politieAgent = new PolitieAgent();
		Dokter dokter = new Dokter();
		Waterkanon waterkanon = new Waterkanon();
		Verdedigers.add(politieAgent);
		Verdedigers.add(dokter);
		Verdedigers.add(waterkanon);
	}


	


	public int getX() {
		return x;
	}
	public ArrayList<Verdediger> getVerdedigers(){
		return Verdedigers;
	}

}

