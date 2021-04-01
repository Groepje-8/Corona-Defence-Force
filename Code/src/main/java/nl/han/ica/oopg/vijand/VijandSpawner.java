package nl.han.ica.oopg.vijand;

import java.util.ArrayList;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.game.Spel;
import nl.han.ica.oopg.tile.Tile;

public class VijandSpawner implements IAlarmListener {
	
	private Spel spel;
	private Alarm alarmVijand, alarmGolf, alarmTimer ;
	private ArrayList<Vijand> vijanden = new ArrayList<>();
	private ArrayList<Vijand> teVerwijderenVijanden = new ArrayList<>();
	private int vijandSpawnX, vijandSpawnY, tijdTussenVijanden, tijdTussenGolven;
	private int aantalGolven, huidigeGolf, vijandIndex, verslagenVijanden, tijdVolgendeGolf;
	int[][] vijandMap;
	
	public VijandSpawner(Spel spel, int[][] vijandMap, int vijandSpawnX, int vijandSpawnY, int tijdTussenVijanden, int tijdTussenGolven) {
		this.spel = spel;
		this.vijandSpawnX = vijandSpawnX * 64 + 16;
		this.vijandSpawnY = vijandSpawnY * 64 + 16;
		this.tijdTussenVijanden = tijdTussenVijanden;
		this.tijdTussenGolven = tijdTussenGolven;
		this.vijandMap = vijandMap;
		aantalGolven = vijandMap.length;
		tijdVolgendeGolf = tijdTussenGolven;
		huidigeGolf = -1;
		verslagenVijanden = 0;
	}
	
	public void checkVijandStatus() {
		for (Vijand vijand : vijanden) {
			if (vijand.isLevend() == false) {
				spel.deleteGameObject(vijand);
				teVerwijderenVijanden.add(vijand);
				spel.voegGeldToe(vijand.getBeloning());
				verslagenVijanden++;
			}
			if (vijand.isSupermarktBereikt() == true) {
				spel.ontvangSchade(vijand.getAanvalskracht());
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
	
	public boolean isSpelKlaar() {
		if (vijanden.size() == 0 && vijandIndex == vijandMap[aantalGolven - 1].length && huidigeGolf == aantalGolven - 1 ) {
			return true;
		}
		return false;
	}
	
	public void beginEersteGolf() {
		alarmGolf = new Alarm("Nieuwe Golf", tijdTussenGolven);
	    alarmGolf.addTarget(this);
	    alarmGolf.start();
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
	
	public void beginAlarmTimer() {
		alarmTimer = new Alarm("Nieuwe Seconde", 1);
	    alarmTimer.addTarget(this);
	    alarmTimer.start();
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
		switch (vijandTypeIndex){
		case 0:
			return new Relschopper(spel);
		case 1:
			return new AntivaxMoeder(spel);
		case 2:
			return new Urker(spel);
		case 3:
			return new FrangeLans(spel);
		case 4:
			return new BerryThiaudet(spel);
		}
		return null;
    }
	
	@Override
	public void triggerAlarm(String alarmName) {
		if (alarmName == "Nieuwe Seconde") {
			tijdVolgendeGolf--;
			beginAlarmTimer();
		}
		else if (alarmName == "Nieuwe Vijand") {
			spawnVijand();
			beginAlarmVijand();
		}
		else if (alarmName == "Nieuwe Golf") {
			volgendeGolf();
			beginAlarmGolf();
			if (huidigeGolf == aantalGolven - 1){
				alarmGolf.stop();
				alarmTimer.stop();
			}
			tijdVolgendeGolf = tijdTussenGolven;
		}
	}
	
	public ArrayList<Vijand> getVijanden(){
		return this.vijanden;
	}
	
	public String getGolfTimer() {
		return "" + tijdVolgendeGolf;
	}
	
	public String getGolfNummer() {
		return "" + huidigeGolf;
	}
	
	public String getAantalVerslagen() {
		return "" + verslagenVijanden;
	}
}
