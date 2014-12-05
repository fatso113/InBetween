package com.edu.BSU.inbetween.common;

import java.util.ArrayList;

public class GameObjects {

	private int startingMoney;
	private int anteAmount;
	private int startingPotSize;
	private int numberOfAiPlayers;
	private ArrayList<AI_Player> listOfPlayers = new ArrayList<AI_Player>();
	
	public GameObjects(int money, int ante, int potSize, int numberOfPlayers, ArrayList<AI_Player> listOfPlayers)
	{
		this.startingMoney = money;
		this.anteAmount = ante;
		this.startingPotSize = potSize;
		this.numberOfAiPlayers = numberOfPlayers;
		this.listOfPlayers = listOfPlayers;
	}
	
	public ArrayList<Object> toArray(){
		ArrayList<Object> objects = new ArrayList<Object>();
		objects.add(this.startingMoney);
		objects.add(this.anteAmount);
		objects.add(this.startingPotSize);
		objects.add(this.numberOfAiPlayers);
		for(AI_Player player: this.listOfPlayers){
			objects.add(player);
		}
		return objects;
	}
	
	public Integer[] getTableValues(){
		Integer[] values = new Integer[4];
		values[0] = this.startingMoney;
		values[1] = this.anteAmount;
		values[2] = this.startingPotSize;
		values[3] = this.numberOfAiPlayers;
		return values;
	}
	
	public ArrayList<AI_Player> getListOfAiPlayers(){
		return this.listOfPlayers;
	}
	
}
