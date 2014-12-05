package com.BSU.inbetween.activities;

import java.io.IOException;
import java.util.ArrayList;

import com.BSU.inbetween.R;
import com.edu.BSU.inbetween.common.AI_Player;
import com.edu.BSU.inbetween.common.IntegerValues;
import com.edu.BSU.inbetween.common.StringValues;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;

/**
 * The purpose of SharedValues is to save or load a game state.
 * Input and output of default saving/loading will be an array list of 
 * generic objects. All gets() will return either an int or an AI_Player.
 * When a get() is called, one of two variables will be returned. The first
 * variable is the name of the object you are trying to get, such as startingMoney.
 * If that value exists, that value will be returned, if it does not exist or is
 * corrupt than the second variable will be returned, in startingMoney case, it will
 * be DEFAULT_STARTING_MONEY as defined in R.integer.
 * For saving values, you decide the variable to save to (startingMoney) and the
 * second value will be the value you want to save.
 */

public class SharedValues {
	
	private static SharedValues instance = null;
	private Resources resources;
	private SharedPreferences sharedValues;
	private SharedPreferences.Editor editor;
	private final int DEFAULT_STARTING_MONEY = IntegerValues.StartingMoney.getValue();
	private final int DEFAULT_STARTING_ANTE = IntegerValues.StartingAnte.getValue();
	private final int DEFAULT_POT_SIZE = IntegerValues.StartingPotSize.getValue();
	private final int DEFAULT_AMOUNT_OF_AI_PLAYERS = IntegerValues.StartingAmountOfAiPlayers.getValue();
	private final String startingMoney = StringValues.StartingMoneySaveLabel.toString();
	private final String startingAnteAmount = StringValues.AnteAmountSaveLabel.toString();
	private final String startingPotSize = StringValues.PotSizeSaveLabel.toString();
	private final String startingAIPlayers = StringValues.AmountOfAIPlayersSaveLabel.toString();
	private boolean DEFAULT_AI_VIEW_HAND_STATUS;
	private String DEFAULT_AI_PLAYER;
	@SuppressWarnings("unused")
	private String DEFAULT_NULL_AI_PLAYER;
	private String aiPlayer;
	
	private SharedValues(SharedPreferences sharedPref, Resources resources) throws IOException{
		this.resources = resources;
		this.sharedValues = sharedPref;
		this.editor = this.sharedValues.edit();
		populateVariables();
	}
	
	protected static SharedValues getInstance(SharedPreferences sharedPref, Resources resources) throws IOException{
		if(instance == null) instance = new SharedValues(sharedPref, resources);
		return instance;
	}
	
	public static SharedValues getCreatedInstance(){
		if(instance == null) return null;
		return instance;
	}
	
	private void populateVariables() {
		this.DEFAULT_AI_VIEW_HAND_STATUS	= Boolean.valueOf(this.resources.getString(R.string.defaultStartingAIViewHandStatus));
		this.DEFAULT_AI_PLAYER = new AI_Player(this.DEFAULT_STARTING_MONEY, this.DEFAULT_AI_VIEW_HAND_STATUS).toString();
		this.DEFAULT_NULL_AI_PLAYER = "null,null,null";
		this.aiPlayer = this.resources.getString(R.string.sharedAIPlayer);
	}

	public ArrayList<Object> loadValues() throws IOException{
		ArrayList<Object> loadedValues = new ArrayList<Object>();
		loadedValues.add(this.sharedValues.getInt(startingMoney, DEFAULT_STARTING_MONEY));
		loadedValues.add(this.sharedValues.getInt(startingAnteAmount, DEFAULT_STARTING_ANTE));
		loadedValues.add(this.sharedValues.getInt(startingPotSize, DEFAULT_POT_SIZE));
		loadedValues.add(this.sharedValues.getInt(startingAIPlayers, DEFAULT_AMOUNT_OF_AI_PLAYERS));
		for(int i = 1; i <= this.sharedValues.getInt(startingAIPlayers, DEFAULT_AMOUNT_OF_AI_PLAYERS); i++){
			loadedValues.add(this.sharedValues.getString((aiPlayer + i), DEFAULT_AI_PLAYER));
		}
		return loadedValues;
	}

	public Editor saveValues(ArrayList<Object> gameObjects) throws IOException{
		if(gameObjects != null){
			saveUserDefinedVariables(gameObjects);
		} else {
			saveDefaultVariables();
		}
		return this.editor;
	}
	
	private void saveUserDefinedVariables(ArrayList<Object> gameObjects) {
		this.editor.putInt(startingMoney,Integer.parseInt(gameObjects.get(0).toString()));
		this.editor.putInt(startingAnteAmount,Integer.parseInt(gameObjects.get(1).toString()));
		this.editor.putInt(startingPotSize,Integer.parseInt(gameObjects.get(2).toString()));
		int aiPlayersInRound = Integer.parseInt(gameObjects.get(3).toString());
		this.editor.putInt(startingAIPlayers,aiPlayersInRound);
		for(int i = 1; i <= aiPlayersInRound; i++){
			this.editor.putString((aiPlayer + i), gameObjects.get(i + 3).toString());
		}
		this.editor.commit();
		this.editor.apply();
	}

	private void saveDefaultVariables() {
		this.editor.putInt(startingMoney, DEFAULT_STARTING_MONEY);
		this.editor.putInt(startingAnteAmount, DEFAULT_STARTING_ANTE);
		this.editor.putInt(startingPotSize, DEFAULT_POT_SIZE);
		this.editor.putInt(startingAIPlayers, DEFAULT_AMOUNT_OF_AI_PLAYERS);
		for(int i = 1; i <= DEFAULT_AMOUNT_OF_AI_PLAYERS; i++){
			this.editor.putString((aiPlayer + i), DEFAULT_AI_PLAYER);
		}
		this.editor.commit();
		this.editor.apply();
	}

	public int getStartingMoney(){
		return this.sharedValues.getInt(startingMoney, DEFAULT_STARTING_MONEY);
	}
	
	public int getAnteAmount(){
		return this.sharedValues.getInt(startingAnteAmount, DEFAULT_STARTING_ANTE);
	}
	
	public int getPotSize(){
		return this.sharedValues.getInt(startingPotSize, DEFAULT_POT_SIZE);
	}
	
	public int getAmountOfPlayers(){
		return this.sharedValues.getInt(startingAIPlayers, DEFAULT_AMOUNT_OF_AI_PLAYERS);
	}
	
	public AI_Player getAIPlayer(int playerIndex){
		if(playerIndex <= 0 || playerIndex > getAmountOfPlayers()){
			return new AI_Player(DEFAULT_STARTING_MONEY, DEFAULT_AI_VIEW_HAND_STATUS);
		} else {
			String[] parameters = this.sharedValues.getString(("aiPlayer" + playerIndex), DEFAULT_AI_PLAYER).split(",");
			return new AI_Player(Integer.parseInt(parameters[0]), Boolean.parseBoolean(parameters[1]));
		}
	}	
	
	public ArrayList<AI_Player> getListOfAIPlayers(){
		ArrayList<AI_Player> listOfAIs = new ArrayList<AI_Player>();
		for(int i = 1; i <= getAmountOfPlayers(); i++){
			String[] parameters = this.sharedValues.getString((aiPlayer + i), DEFAULT_AI_PLAYER).split(",");
			listOfAIs.add(new AI_Player(Integer.parseInt(parameters[0]), Boolean.parseBoolean(parameters[1])));
		}
		return listOfAIs;
	}
	
}
