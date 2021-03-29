package nl.han.ica.oopg.vijand;

import java.util.ArrayList;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.game.Spel;
import nl.han.ica.oopg.tile.Tile;

public class VijandSpawner implements IAlarmListener {
	
	private Spel spel;
	private Alarm alarmVijand, alarmGolf;
	private ArrayList<Vijand> vijanden = new ArrayList<>();
	private ArrayList<Vijand> teVerwijderenVijanden = new ArrayList<>();
	private int vijandSpawnX, vijandSpawnY, tijdTussenVijanden, tijdTussenGolven;
	private int aantalGolven, huidigeGolf, vijandIndex;
	int[][] vijandMap;
	
	public VijandSpawner(Spel spel, int[][] vijandMap, int vijandSpawnX, int vijandSpawnY, int tijdTussenVijanden, int tijdTussenGolven) {
		this.spel = spel;
		this.vijandSpawnX = vijandSpawnX;
		this.vijandSpawnY = vijandSpawnY;
		this.tijdTussenVijanden = tijdTussenVijanden;
		this.tijdTussenGolven = tijdTussenGolven;
		this.vijandMap = vijandMap;
		aantalGolven = vijandMap.length;
		huidigeGolf = 0;
		vijandIndex = 0;
	}
	
	public void checkVijandStatus() {
		for (Vijand vijand : vijanden) {
			if (vijand.isLevend() == false) {
				spel.deleteGameObject(vijand);
				teVerwijderenVijanden.add(vijand);
			}
			if (vijand.isSupermarktBereikt() == true) {
				spel.ontvangSchade(1);
				spel.deleteGameObject(vijand);
				teVerwijderenVijanden.add(vijand);
			}
		}
	}
	public void verwijderDodeVijanden() {
		for(Vijand vijand : teVerwijderenVijanden) {
			vijanden.remove(vijand);
		}
		teVerwijderenVijanden.clear();
	}
		
	public void beginAlarmGolf() {
		alarmGolf = new Alarm("Nieuwe Golf", tijdTussenGolven);
	    alarmGolf.addTarget(this);
	    alarmGolf.start();
	    beginAlarmVijand();
	    
	}
	
	public void beginAlarmVijand() {
		if (vijandIndex == vijandMap[huidigeGolf].length) {
			alarmVijand.stop();
		}
		else {
			alarmVijand = new Alarm("Nieuwe Vijand", tijdTussenVijanden);
		    alarmVijand.addTarget(this);
		    alarmVijand.start();
		}
	}
	public ArrayList<Vijand> getVijanden(){
		return this.vijanden;
	}
	
	public void volgendeGolf() {
		huidigeGolf ++;
		vijandIndex = 0;
	}
	
	private void spawnVijand() {
		Vijand nieuweVijand = createVijand(vijandMap[huidigeGolf][vijandIndex]);
		vijanden.add(nieuweVijand);
		spel.addGameObject(nieuweVijand, vijandSpawnX, vijandSpawnY);
		vijandIndex++;
	}
	
	private Vijand createVijand(int vijandTypeIndex){
		if (vijandTypeIndex == 0){
			return new Relschopper(spel);
		}
		return null;
    }
	
	@Override
	public void triggerAlarm(String alarmName) {
		if (alarmName == "Nieuwe Vijand") {
			spawnVijand();
			beginAlarmVijand();
		}
		else if (alarmName == "Nieuwe Golf") {
			volgendeGolf();
			beginAlarmGolf();
		}
	}
}
