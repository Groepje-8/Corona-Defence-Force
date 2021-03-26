package nl.han.ica.oopg.screens;

import nl.han.ica.oopg.dashboard.*;
import nl.han.ica.oopg.objects.*;

public class scoreScherm {
	private TextObject dashboardText;
	private final int xScoreBoard = 0;
	private final int yScoreBoard = 0;
	private final int xTerugKnop = 200;
	private final int yTerugKnop = 200;
	private final int buttonWidth = 100;
	private final int buttonHeight = 100;
	private int fontSize;
	
	scoreScherm(int score){
		this.fontSize = 11;
		createScoreBoard(score);
		createTerugKnop();
	}

	public int getxScoreBoard() {
		return xScoreBoard;
	}

	public int getyScoreBoard() {
		return yScoreBoard;
	}

	public int getxTerugKnop() {
		return xTerugKnop;
	}

	public int getyTerugKnop() {
		return yTerugKnop;
	}

	public int getKnopBreedte() {
		return buttonWidth;
	}

	public int getKnopHoogte() {
		return buttonHeight;
	}

	public Dashboard createScoreBoard(int score) {
		Dashboard dashboard = new Dashboard(xScoreBoard, yScoreBoard, buttonWidth, buttonHeight);
		dashboardText = new TextObject("Je score is:" + score, fontSize);
		dashboard.addGameObject(dashboardText);
		// dit moet naar de spel klasse.
		// addDashboard(dashboard);
		return dashboard;
	}

	public Dashboard createTerugKnop() {
		Dashboard dashboard = new Dashboard(xTerugKnop, yTerugKnop, buttonWidth, buttonHeight);
		dashboardText = new TextObject("Ga terug naar main menu", fontSize);
		dashboard.addGameObject(dashboardText);
		// dit moet naar de spel klasse.
		// addDashboard(dashboard);
		return dashboard;
	}

}
