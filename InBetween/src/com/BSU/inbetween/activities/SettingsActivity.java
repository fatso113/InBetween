package com.BSU.inbetween.activities;

import java.io.IOException;
import java.util.ArrayList;

import com.BSU.inbetween.R;
import com.edu.BSU.inbetween.common.AI_Player;
import com.edu.BSU.inbetween.common.IntegerValues;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

public class SettingsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		Log.i("Enum test", String.valueOf(IntegerValues.StartingAnte.getValue()));
	}
	
	public void saveCurrentSettings(View v) {
		ArrayList<Object> saveObjects = new ArrayList<Object>();
		saveObjects.add(getStartingMoney());
		saveObjects.add(getStartingAnte());
		saveObjects.add(getStartingPotSize());
		saveObjects.add(getNumberOfAIPlayers());
		for(int i = 1; i <= getMaxAmountOfPlayers(); i++){
			saveObjects.add(newAIPlayer());
		}
		try {
			SharedValues values = SharedValues.getInstance(this.getSharedPreferences("GameObjects", 4), this.getResources());
			values.saveValues(saveObjects);
		} catch (IOException e) {
			showErrorDialog();
			e.printStackTrace();
		}
		showSettingsSavedDialog();
	}
	
	public void restoreDefaultSettings(View v){
		try {
			SharedValues values = SharedValues.getInstance(this.getSharedPreferences("GameObjects", 4), this.getResources());
			values.saveValues(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		showSettingsDefaultDialog();
	}
	
	private int getStartingMoney() {
		EditText startingMoney = (((EditText) findViewById(R.id.startMoney)));
		int defaultStartingMoney = this.getResources().getInteger(R.integer.defaultStartingMoney);
		int parsedStartingMoney = tryCatch(startingMoney, defaultStartingMoney);
		if(parsedStartingMoney == 0 || parsedStartingMoney < defaultStartingMoney){
			return defaultStartingMoney;
		} else {
			return parsedStartingMoney;
		}
	}
	
	private int getStartingAnte() {
		EditText startingAnteAmount = (((EditText) findViewById(R.id.defaultAnte)));
		int defaultAnteAmount = this.getResources().getInteger(R.integer.defaultStartingAnte);
		int anteAmount = tryCatch(startingAnteAmount, defaultAnteAmount);
		if(anteAmount == 0 || anteAmount >= getStartingMoney() || anteAmount > (getStartingMoney())/20){
			return defaultAnteAmount;
		} else {
			return anteAmount;
		}
	}
	
	private int getStartingPotSize() {
		EditText startingPotSize = (((EditText) findViewById(R.id.potSize)));
		int defaultPotSize = this.getResources().getInteger(R.integer.defaultStartingPotSize);
		int potSize = tryCatch(startingPotSize, defaultPotSize);
		if(potSize == 0 || potSize <= getStartingAnte() || potSize * 20 < getStartingAnte() || potSize < defaultPotSize){
			return defaultPotSize;
		} else {
			return potSize;
		}
	}
	
	private int getNumberOfAIPlayers() {
		EditText amountOfAiPlayers = (((EditText) findViewById(R.id.numCPUplayers)));
		int defaultStartingAIs = this.getResources().getInteger(R.integer.defaultStartingAmountOfAIPlayers);
		int maxAmountOfAIs = getMaxAmountOfPlayers();
		int startingAmountOfAiPlayers = tryCatch(amountOfAiPlayers, defaultStartingAIs);
		if(startingAmountOfAiPlayers <= 0 || startingAmountOfAiPlayers > maxAmountOfAIs){
			return defaultStartingAIs;
		} else {
			return startingAmountOfAiPlayers;
		}
	}
	
	private String newAIPlayer() {
		int money = getStartingMoney();
		boolean viewHandStatus = ((Switch) findViewById(R.id.cpuDifficultySwitch)).isChecked();
		return new AI_Player(money, viewHandStatus).toString();
	}
	
	private int getMaxAmountOfPlayers() {
		return this.getResources().getInteger(R.integer.maxAmountOfAiPlayers);
	}
	
	private int tryCatch(EditText input, int defaultValue){
		int output;
		try{
			output = Integer.valueOf(input.getText().toString());
		} catch (NumberFormatException e){
			output = defaultValue;
		}
		return output;
	}
	
	private void showErrorDialog(){
		DialogFragment errorDialog = new ErrorDialog();
		errorDialog.show(getFragmentManager(), "Error");
	}
	
	private void showSettingsSavedDialog(){
		DialogFragment savedDialog = new SettingsSavedDialog();
		savedDialog.show(getFragmentManager(), "Settings saved");
	}
	
	private void showSettingsDefaultDialog(){
		DialogFragment defaultDialog = new SettingsDefaultDialog();
		defaultDialog.show(getFragmentManager(), "Default settings restored");
	}

}
