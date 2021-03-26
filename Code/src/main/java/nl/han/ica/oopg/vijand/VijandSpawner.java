package nl.han.ica.oopg.vijand;

import java.util.ArrayList;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.game.Spel;
import nl.han.ica.oopg.tile.Tile;

public class VijandSpawner implements IAlarmListener {
	
	private Spel spel;
	private Alarm alarm;
	private ArrayList<Vijand> vijanden = new ArrayList<>();
	private int tijdPerVijand = 1;
	private int vijandSpawnX = 1028 + 16;
	private int vijandSpawnY = 128 + 16;
	private int vijandCounter = 0;
	private int huidigeGolf = 0;
	private int vijandIndex = 0;
	private int vijandMap[][] = {
		{0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0}
	};
	
	public VijandSpawner(Spel spel) {
		this.spel = spel;
	}
	
	public void checkVijandStatus() {
		for (Vijand vijand : vijanden) {
			if (vijand.isLevend() == false) {
				spel.deleteGameObject(vijand);
			}
			if (vijand.isSupermarktBereikt() == true) {
				//spel.ontangSchade();
				spel.deleteGameObject(vijand);
			}
		}
	}
	
	public void volgendeGolf() {
		huidigeGolf ++;
		vijandIndex = 0;
	}
		
	public void beginGolf() {
		alarm = new Alarm("Nieuwe Vijand", tijdPerVijand);
	    alarm.addTarget(this);
	    alarm.start();
	}
	
	private void spawnVijand() {
		if (vijandIndex == vijandMap[huidigeGolf].length) {
			alarm.stop();
		}
		else {
			vijanden.add(createVijand(vijandMap[huidigeGolf][vijandIndex]));
			spel.addGameObject(vijanden.get(vijandCounter), vijandSpawnX, vijandSpawnY);
			vijandCounter++;
			vijandIndex++;
		}
	}
	
	private Vijand createVijand(int vijandTypeIndex){
		if (vijandTypeIndex == 0){
			return new Relschopper(spel);
		}
		return null;
    }
	
	@Override
	public void triggerAlarm(String alarmName) {
		spawnVijand();
		beginGolf();
	}
}
