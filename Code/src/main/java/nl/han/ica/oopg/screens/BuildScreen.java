package nl.han.ica.oopg.screens;
import java.util.ArrayList;


import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.game.Spel;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.verdediger.Dokter;
import nl.han.ica.oopg.verdediger.PolitieAgent;
import nl.han.ica.oopg.verdediger.Verdediger;
import nl.han.ica.oopg.verdediger.Waterkanon;


public class BuildScreen {
	public ArrayList<Verdediger> Verdedigers = new ArrayList<Verdediger>();
	public ArrayList<Buildable> Buildables = new ArrayList<Buildable>();

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
		
		
		Buildable politieAgentBuildable = new Buildable(new Sprite(Spel.MEDIA_URL.concat("PolitieAgent1.png")),politieAgent);
		Buildable dokterBuildable = new Buildable(new Sprite(Spel.MEDIA_URL.concat("Dokter1.png")),dokter);
		Buildable waterkanonBuildable = new Buildable(new Sprite(Spel.MEDIA_URL.concat("AntivaxMoeder.png")),waterkanon);
		
		Verdedigers.add(politieAgent);
		Verdedigers.add(dokter);
		Verdedigers.add(waterkanon);
		Buildables.add(politieAgentBuildable);
		Buildables.add(waterkanonBuildable);
		Buildables.add(dokterBuildable);
		
	}


	


	public int getX() {
		return x;
	}
	public ArrayList<Verdediger> getVerdedigers(){
		return Verdedigers;
	}
	public ArrayList<Buildable> getBuildables(){
		return Buildables;
	}

}

