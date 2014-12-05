package com.edu.BSU.inbetween.common;

import java.util.ArrayList;

import android.app.Activity;
import android.content.res.AssetManager;

public class GameSession extends Activity
{
	public ArrayList<AI_Player> aiPlayerList = new ArrayList<AI_Player>();
	private Pot gamePot;
	private int AIPlayerCount;
	private int potValue;
	//private ArrayList<AI_Player> listOfPlayers;	DELETE??
	private int startingMoney;
	public int anteAmount;
	private Player player;
	private AssetManager assets;
	public boolean antesAreDetermined = false;
	
	public GameSession()
	{
		
	}
	
	public GameSession(int AIPlayerCount, int startingMoney, int defaultPotValue, int anteAmount, AssetManager assetManager) {
		this.AIPlayerCount = AIPlayerCount;
		this.potValue = defaultPotValue;
		this.anteAmount = anteAmount;
		this.assets = assetManager;
		this.startingMoney = startingMoney;
		player = new Player(startingMoney);
		CardXMLParser.setAssetFiles(assets);
	}
	
	public void prepareGameValues() {
		gamePot = new Pot(new Money(potValue));
	}
	
	public void startNewRound() {
		Dealer.generateDeck();
		antesAreDetermined = false;
		payAntes();
		dealHands();
		returnAnteIfSameValue();
		antesAreDetermined = true;
	}
	
	public void payAntes() {
		payPlayerAnte();
		payAIPlayerAntes();
	}
	
	private void payPlayerAnte() {
		if (InBetweenRules.doesPlayerHaveEnoughMoney(player.getMoney(), anteAmount)) {
			player.payAnte(anteAmount);
			gamePot.addToPot(anteAmount);
		} else {
			// GAME OVER
		}
	}
	
	private void payAIPlayerAntes() {
		for (AI_Player aiPlayer: aiPlayerList) {
			if (InBetweenRules.doesPlayerHaveEnoughMoney(aiPlayer.getMoney(), anteAmount)) {
				aiPlayer.payAnte(anteAmount);
				gamePot.addToPot(anteAmount);
			} else {
				aiPlayer.kickAI();
			}
		}
	}

	public void dealHands() {
		setPlayerHand();
		setAIPlayerHands();
	}
	
	private void setPlayerHand() {
		player.setHand(Dealer.dealHand());
	}
	
	private void setAIPlayerHands() {
		for (AI_Player aiPlayer : aiPlayerList) {
			if (!aiPlayer.isKicked()) {
				aiPlayer.setHand(Dealer.dealHand());
			}
		}
	}

	private void returnAnteIfSameValue() {
		checkPlayerHandForSameValue();
		checkAIPlayersHandsForSameValue();
	}

	private void checkPlayerHandForSameValue() {
		if(cardsAreSameValue(getPlayer())){
			int returnAmount = getPot().returnAnteAmount(anteAmount);
			getPlayer().addMoney(returnAmount);
		}
	}
	
	private void checkAIPlayersHandsForSameValue() {
		for (AI_Player aiPlayer : aiPlayerList) {
			if (cardsAreSameValue(aiPlayer)) {
				int returnAnte = getPot().returnAnteAmount(anteAmount);
				aiPlayer.addMoney(returnAnte);
			}
		}
	}
		
	public boolean cardsAreSameValue(Player currentPlayer) {
		return currentPlayer.getHand().areCardsSameValue();
	}

	public void populatePlayerList() {
		for (int i = 0; i < AIPlayerCount; i++) {
			aiPlayerList.add(new AI_Player(startingMoney, false));
		}
	}
	
	public void takeAIPlayerTurn(int betAmount, int index, Card topCard) {
		aiPlayerList.get(index).removeMoney(betAmount);
		gamePot.addToPot(betAmount);
		if (topCard.isBetween(aiPlayerList.get(index).getHand())) {
			aiPlayerList.get(index).addMoney(gamePot.collectWinnings(betAmount));
		}
	}
	
	public boolean doesGameContinue() {
		if (InBetweenRules.doesPlayerHaveEnoughMoney(player.getMoney(), anteAmount)) {
			if (InBetweenRules.areEnoughActivePlayers(aiPlayerList)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean thereAreAIsLeft(){
		boolean aisRemain = false;
		for(AI_Player ai: aiPlayerList){
			if(!ai.isKicked()){
				aisRemain = true;
				break;
			}
		}
		return aisRemain;
	}

	public Pot getPot() {
		return gamePot;
	}
	
	public Player getPlayer() {
		return player;
	}
	
}
