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
	private int vijandSpawnX, vijandSpawnY, tijdTussenVijanden;
	private int aantalGolven, huidigeGolf, vijandCounter, vijandIndex;
	int[][] vijandMap;
	
	public VijandSpawner(Spel spel, int[][] vijandMap, int vijandSpawnX, int vijandSpawnY, int tijdTussenVijanden) {
		this.spel = spel;
		this.vijandSpawnX = vijandSpawnX;
		this.vijandSpawnY = vijandSpawnY;
		this.tijdTussenVijanden = tijdTussenVijanden;
		this.vijandMap = vijandMap;
		aantalGolven = vijandMap.length;
		huidigeGolf = 0;
		vijandIndex = 0;
		vijandCounter = 0;
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
		alarm = new Alarm("Nieuwe Vijand", tijdTussenVijanden);
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
