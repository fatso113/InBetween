package com.BSU.inbetween.activities;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.*;
import android.widget.*;
import android.widget.SeekBar.*;

import com.BSU.inbetween.*;
import com.edu.BSU.inbetween.common.*;

public class InGameActivity extends Activity implements GameListener {
	
	private boolean isRoundOver = false;
	private boolean isGameOver = false;
    private GameSession session;
    private GameListener listener;
    private boolean roundOver;
    private boolean displayAIOne = false;
    private boolean displayAITwo = false;
    private boolean displayAIThree = false;
    private boolean displayAIFour = false;
    private boolean displayAIFive = false;
	private boolean betting;
	private int betAmount = 1;
    private int aiPlayers;
    private int startingMoney;
    private int defaultPotSize;
    private int anteAmount;
	private String fileName = StringValues.SavedSettingsSharedValues.toString();
    private Button betButton;
    private Button passButton;
    private Button pauseButton;
    private Button nextRoundButton;
    private View layoutAI1;
    private View layoutAI2;
    private View layoutAI3;
    private View layoutAI4;
    private View layoutAI5;
    private View gameView;
    private TextView playerMoneyText;
    private TextView potText;

    // TODO LIST
    // View fragments?
    // Scrap generic settings screen with actual settings/shared values screen
    // Add custom listener to update fields as a live game
    // Move all code within this Activity to GameSession and report back with GameListener
    // Animations: Simple slide on fold
    // Animations: flip cards on bet
    // Animations: zoom up card on bet
    // GamePlay: allow for skipping of AI player betting
    // All cards are hidden to begin with
    // Player hand is hidden unless they bet
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setFullScreenLandscape();
		loadSavedSettings();
		initializeSession();
		populateVariablesAndButtons();
		prepareGameValues(); 
	}
	
	private void setFullScreenLandscape() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_in_game);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	}

	private void loadSavedSettings() {
		SharedValues values = null;
		try {
			values = SharedValues.getInstance(this.getSharedPreferences(fileName, 4),this.getResources());
		} catch (Exception e) {
            Log.i("Shared Values", "Error Loading S.V.");
		} finally {
			aiPlayers = values.getAmountOfPlayers();
			startingMoney = values.getStartingMoney();
			defaultPotSize = values.getPotSize();
			anteAmount = values.getAnteAmount();
		}
	}

	private void initializeSession() {
		session = new GameSession(aiPlayers, startingMoney, defaultPotSize, anteAmount, super.getAssets());
		session.populatePlayerList();
	}
	
	private void populateVariablesAndButtons() {
        listener = this;
		betButton = (Button) findViewById(R.id.bet);
		passButton = (Button) findViewById(R.id.pass);
		pauseButton = (Button) findViewById(R.id.pause);
		nextRoundButton = (Button) findViewById(R.id.nextRound);
		betButton.setOnClickListener(betClick);
		passButton.setOnClickListener(passClick);
		pauseButton.setOnClickListener(pauseClick);
		nextRoundButton.setOnClickListener(nextRoundClick);
		nextRoundButton.setEnabled(false);
        layoutAI1 = findViewById(R.id.ai1Hand);
        layoutAI2 = findViewById(R.id.ai2Hand);
        layoutAI3 = findViewById(R.id.ai3Hand);
        layoutAI4 = findViewById(R.id.ai4Hand);
        layoutAI5 = findViewById(R.id.ai5Hand);
        playerMoneyText = (TextView) findViewById(R.id.playerMoney);
        potText = (TextView) findViewById(R.id.pot_text);
        gameView = findViewById(R.id.InGameLayout);
	}
	
	private void prepareGameValues() {
		session.prepareGameValues();
		startRound();
	}
	
	private void startRound() {
		toggleButtonStatus(false);
		hideTopCards();
		session.startNewRound();
		displayAIPlayers();
		placeAIPlayersOnScreen();
		updateDisplay();
		setSeekBar();
		determineButtonDisplay();
		displayAIPlayers();
		displayGameValues();
		updateDisplay();
	}

	private void toggleButtonStatus(boolean isRoundOver) {
		passButton.setEnabled(!isRoundOver);
		betButton.setEnabled(!isRoundOver);
		nextRoundButton.setEnabled(isRoundOver);
	}
	
	private void hideTopCards() {
		ImageView flippedCardPlayer = (ImageView) findViewById(R.id.playerFlippedCard);
		ImageView flippedCardAI1 = (ImageView) findViewById(R.id.ai1flippedCard);
		ImageView flippedCardAI2 = (ImageView) findViewById(R.id.ai2flippedCard);
		ImageView flippedCardAI3 = (ImageView) findViewById(R.id.ai3flippedCard);
		ImageView flippedCardAI4 = (ImageView) findViewById(R.id.ai4flippedCard);
		ImageView flippedCardAI5 = (ImageView) findViewById(R.id.ai5flippedCard);
		flippedCardPlayer.setVisibility(View.INVISIBLE);
		flippedCardAI1.setVisibility(View.INVISIBLE);
		flippedCardAI2.setVisibility(View.INVISIBLE);
		flippedCardAI3.setVisibility(View.INVISIBLE);
		flippedCardAI4.setVisibility(View.INVISIBLE);
		flippedCardAI5.setVisibility(View.INVISIBLE);
	}
	
	private void placeAIPlayersOnScreen() {
		for(int index = 0; index < session.aiPlayerList.size(); index++) {
			determineDrawablePlayer(index, session.aiPlayerList.get(index).isKicked());
		}
	}
	
	private void updateDisplay() {
		gameView.invalidate();
	}

	private void setSeekBar() {
		SeekBar betBar = (SeekBar) findViewById(R.id.betBar);
		betBar.setMax(Player.determineMaxBet(session) - 1);
		betBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				betAmount = progress + 1;
				TextView text = (TextView) findViewById(R.id.betAmount);
				text.setText(String.valueOf(betAmount));
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {}
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {}
		});
	}
	
	private void determineButtonDisplay() {
		if(session.cardsAreSameValue(session.getPlayer()) || InBetweenRules.isRangeOfCardsOne(session.getPlayer().getHand())){
			betButton.setEnabled(false);
		} else {
			betButton.setEnabled(true);
		}
	}

	private void displayAIPlayers() {
        layoutAI1.setVisibility(displayAIOne ? View.VISIBLE : View.INVISIBLE);
        layoutAI2.setVisibility(displayAITwo ? View.VISIBLE : View.INVISIBLE);
        layoutAI3.setVisibility(displayAIThree ? View.VISIBLE : View.INVISIBLE);
        layoutAI4.setVisibility(displayAIFour ? View.VISIBLE : View.INVISIBLE);
        layoutAI5.setVisibility(displayAIFive ? View.VISIBLE : View.INVISIBLE);
    }
	
	private void displayGameValues() {
		displayPlayerMoney();
		displayPlayerHand();
		displayAIPlayerHands();
		displayPotText();
		updateDisplay();
	}

	private void displayPlayerMoney() {
		playerMoneyText.setText(String.valueOf(session.getPlayer().getMoney().getAmount()));
	}

	private void displayPlayerHand() {
		updateCardImage(session.getPlayer().getHand().getFirstCard(), R.id.playerCard1);
		updateCardImage(session.getPlayer().getHand().getSecondCard(), R.id.playerCard2);
	}
	
	private void displayAIPlayerHands() {
		for (int i = 0; i < session.aiPlayerList.size(); i++) {
			switch (i) {
			case 0: {
				updateCardImage(session.aiPlayerList.get(i).getHand().getFirstCard(), R.id.ai1Card1);
				updateCardImage(session.aiPlayerList.get(i).getHand().getSecondCard(), R.id.ai1Card2);
				updateAIText(i, R.id.ai1Money);
				break;
			}
			case 1: {
				updateCardImage(session.aiPlayerList.get(i).getHand().getFirstCard(), R.id.ai2Card1);
				updateCardImage(session.aiPlayerList.get(i).getHand().getSecondCard(), R.id.ai2Card2);
				updateAIText(i, R.id.ai2Money);
				break;
			}
			case 2: {
				updateCardImage(session.aiPlayerList.get(i).getHand().getFirstCard(), R.id.ai3Card1);
				updateCardImage(session.aiPlayerList.get(i).getHand().getSecondCard(), R.id.ai3Card2);
				updateAIText(i, R.id.ai3Money);
				break;
			}
			case 3: {
				updateCardImage(session.aiPlayerList.get(i).getHand().getFirstCard(), R.id.ai4Card1);
				updateCardImage(session.aiPlayerList.get(i).getHand().getSecondCard(), R.id.ai4Card2);
				updateAIText(i, R.id.ai4Money);
				break;
			}
			case 4: {
				updateCardImage(session.aiPlayerList.get(i).getHand().getFirstCard(), R.id.ai5Card1);
				updateCardImage(session.aiPlayerList.get(i).getHand().getSecondCard(), R.id.ai5Card2);
				updateAIText(i, R.id.ai5Money);
				break;
			}
			default: {
				break;
			}
			}
		}
	}

	private void displayPotText() {
		TextView potText = (TextView) findViewById(R.id.pot_text);
		potText.setText(String.valueOf(session.getPot().getPotSize()));
	}
	
	View.OnClickListener betClick = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			betting = true;
			toggleBetAndPassButton(true);
			takePlayerTurn();
			determineGameOver();
		}
	};

	View.OnClickListener passClick = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			betting = false;
			toggleBetAndPassButton(true);
			takePlayerTurn();
			determineGameOver();
		}
	};
	
	View.OnClickListener nextRoundClick = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			if (session.doesGameContinue()) {
				displayAIPlayers();
				isRoundOver = false;
				toggleBetAndPassButton(false);
				toggleButtonStatus(isRoundOver);
				displayGameValues();
				determineGameOver();
				startRound();
			} //else {
				//determineGameOver();
			// }
		}
	};
	
	View.OnClickListener pauseClick = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent getHelp = new Intent(InGameActivity.this, PausedActivity.class);
			InGameActivity.this.startActivity(getHelp);
		}
	};

	protected void toggleBetAndPassButton(boolean flipCase) {
		passButton.setEnabled(!flipCase);
		betButton.setEnabled(!flipCase);
	}
	
	private void takePlayerTurn() {
		if (betting) {
			takeBetAction();
		}
		else{
			foldCards(R.id.playerCard1, R.id.playerCard2);
		}
		postBetAction();
		updateDisplay();
	}

	private void takeBetAction() {
		adjustMoneyAndPotForPlayerBetting();
		Card topCard = revealTopPlayerCard();
		determineIfPlayerWinsHand(topCard);
	}

	private void adjustMoneyAndPotForPlayerBetting() {
		session.getPlayer().removeMoney(betAmount);
		session.getPot().addToPot(betAmount);
		updateDisplay();
	}
	
	private Card revealTopPlayerCard() {
		Card topCard = Dealer.getCard();
		updateCardImage(topCard, R.id.playerFlippedCard);
		return topCard;
	}
	
	private void updateCardImage(Card card, int id) {
		ImageView aCard = (ImageView) findViewById(id);
		aCard.setVisibility(View.VISIBLE);
		Drawable newCard = CardMapper.getCardImage(card, getResources());
		aCard.setImageDrawable(newCard);
	}

	private void determineIfPlayerWinsHand(Card topCard) {
		if (topCard.isBetween(session.getPlayer().getHand())) {
			session.getPlayer().addMoney(session.getPot().collectWinnings(betAmount));
		}
	}
	
	private void foldCards(int id1, int id2){
		ImageView card1 = (ImageView) findViewById(id1);
		ImageView card2 = (ImageView) findViewById(id2);
		Drawable newCard = getResources().getDrawable(R.drawable.card_back);
		card1.setImageDrawable(newCard);
		card2.setImageDrawable(newCard);
	}

	private void postBetAction() {
		prepareTimeInterval();
	}
	
	private void prepareTimeInterval() {
		int timeBlock = 0;
		int timeBetweenTurn = 750;
		for(AI_Player player: session.aiPlayerList){
			if(!player.isKicked()){
				timeBlock += 1;
			}
		}
		int totalTime = (timeBlock * timeBetweenTurn) + timeBetweenTurn;
		cycleThroughAiPlayers(totalTime,timeBetweenTurn);
	}
	
	private void cycleThroughAiPlayers(int totalTime, int blockTime) {
		new CountDownTimer(totalTime, blockTime) {
			private int index = 0;
			@Override
			public void onTick(long millisUntilFinished) {
                // Loops through players until we find a player that is active
				while(session.aiPlayerList.get(index).isKicked()){
					index++;
				}
				int betAmount = session.aiPlayerList.get(index).getBetAmount();
				if (betAmount > 0) {
					Log.i("BETTING", "Index: " + index + " Bet Amount: " + betAmount);
					Card flippedCard = Dealer.getCard();
					session.takeAIPlayerTurn(betAmount, index, flippedCard);
					updateFlippedCardImage(index, flippedCard);
				} else{
					Log.i("FOLDING", "Index to fold: " + index);
					foldAI_Index(index);
				}
				determineDrawablePlayer(index, session.aiPlayerList.get(index).isKicked());
				index++;
				updateGameTextValues();
				updateDisplay();
			}
			@Override
			public void onFinish() {
				updateGameTextValues();
				isRoundOver = true;
				toggleButtonStatus(isRoundOver);
				updateDisplay();
			}
		}.start();
	}

	private void updateFlippedCardImage(int index, Card revealedCard) {
		switch (index) {
		case 0: {
			updateCardImage(revealedCard, R.id.ai1flippedCard);
			break;
		}
		case 1: {
			updateCardImage(revealedCard, R.id.ai2flippedCard);
			break;
		}
		case 2: {
			updateCardImage(revealedCard, R.id.ai3flippedCard);
			break;
		}
		case 3: {
			updateCardImage(revealedCard, R.id.ai4flippedCard);
			break;
		}
		case 4: {
			updateCardImage(revealedCard, R.id.ai5flippedCard);
			break;
		}
		default: {
			break;
		}
		}
	}
	
	private void foldAI_Index(int aiIndex){
		switch (aiIndex) {
		case 0: {
			foldCards(R.id.ai1Card1, R.id.ai1Card2);
			break;
		}
		case 1: {
			foldCards(R.id.ai2Card1, R.id.ai2Card2);
			break;
		}
		case 2: {
			foldCards(R.id.ai3Card1, R.id.ai3Card2);
			break;
		}
		case 3: {
			foldCards(R.id.ai4Card1, R.id.ai4Card2);
			break;
		}
		case 4: {
			foldCards(R.id.ai5Card1, R.id.ai5Card2);
			break;
		}
		default: {
			break;
		}
		}
	}

	private void updateGameTextValues() {
		displayPotText();
		TextView playerMoneyText = (TextView) findViewById(R.id.playerMoney);
		playerMoneyText.setText(String.valueOf(session.getPlayer().getMoney().getAmount()));
		for (int i = 0; i < session.aiPlayerList.size(); i++) {
			switch (i) {
			case 0: {
				updateAIText(i, R.id.ai1Money);
				break;
			}
			case 1: {
				updateAIText(i, R.id.ai2Money);
				break;
			}
			case 2: {
				updateAIText(i, R.id.ai3Money);
				break;
			}
			case 3: {
				updateAIText(i, R.id.ai4Money);
				break;
			}
			case 4: {
				updateAIText(i, R.id.ai5Money);
				break;
			}
			default: {
				break;
			}
			}
		}
	}
	
	private void updateAIText(int index, int id) {
		TextView aiMoneyText = (TextView) findViewById(id);
		aiMoneyText.setText(String.valueOf(session.aiPlayerList.get(index).getMoney().getAmount()));
	}
	
	private void determineDrawablePlayer(int index, boolean kickStatus) {
		switch(index) {
		case 0: {
			displayAIOne = !kickStatus;
			break;
		}
		case 1: {
			displayAITwo = !kickStatus;
			break;
		}
		case 2: {
			displayAIThree = !kickStatus;
			break;
		}
		case 3: {
			displayAIFour = !kickStatus;
			break;
		}
		case 4: {
			displayAIFive = !kickStatus;
			break;
		}
		default: {
			break;
		}}
	}
	
	private void determineGameOver() {
        if (!session.doesGameContinue()) {
            isGameOver = true;
            gameOver(session.thereAreAIsLeft());
        }
    }

	private void GAMEOVER() {
		DialogFragment gameOverDialog = new GameOverDialog();
		gameOverDialog.show(getFragmentManager(), "Game Over");
	}

	private void showEndGameDialog() {
		DialogFragment victoryDialog = new GameWonDialog();
		victoryDialog.show(getFragmentManager(), "Game Over");
	}
	
	@Override
	public void onBackPressed() {
		showLeaveGameDialog();
	}
	
	private void showLeaveGameDialog() {
		DialogFragment leaveDialog = new LeaveGameDialog();
		leaveDialog.show(getFragmentManager(), "Are you sure you want to leave?");
	}

    @Override
    public void onPotChange(int newAmount) {
        
    }

    @Override
    public void onPlayerMoneyChange(int newValue) {

    }

    @Override
    public void onAiMoneyChange(int index, int newValue) {

    }

    @Override
    public void onPlayerBet() {

    }

    @Override
    public void onAIPlayerBet(int index, int betAmount) {

    }

    @Override
    public void onRoundOver() {

    }

    @Override
    public void gameOver(boolean lose) {
        potText.setText(lose ? "You lost!" : "You won!");
        showEndGameDialog();
    }

    @Override
    public void onAIKick(int index) {

    }

    @Override
    public void onPlayerKick() {

    }

}