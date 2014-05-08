package com.me.solar.logic;


import com.me.ressources.Credits;

public class GameLogicService
{
    // Globale Spiellogik wird hier eingefügt!
	
	public Credits credits;
	
	
	public void StartGame()
	{
		setStartRessourcesCredits(1000, 0.1);
	}
	
	private void setStartRessourcesCredits(double Value, double RaiseRate){
		
		credits = new Credits();
		credits.setValue(Value);
		credits.setRaiseRate(RaiseRate);
    }
}
