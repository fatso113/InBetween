package com.edu.BSU.inbetween.common;

import com.BSU.inbetween.R;
import com.BSU.inbetween.activities.MainScreenActivity;

public enum StringValues {

	ApplicationName (R.string.app_name),
	StartingMoneySaveLabel (R.string.sharedStartingMoney),
	AnteAmountSaveLabel	(R.string.sharedAnteAmount),
	PotSizeSaveLabel (R.string.sharedPotSize),
	AmountOfAIPlayersSaveLabel (R.string.sharedNumberOfAIPlayers),
	AIPlayer1SaveLabel (R.string.sharedAIPlayer1),
	AIPlayer2SaveLabel (R.string.sharedAIPlayer2),
	AIPlayer3SaveLabel (R.string.sharedAIPlayer3),
	AIPlayer4SaveLabel (R.string.sharedAIPlayer4),
	AIPlayer5SaveLabel (R.string.sharedAIPlayer5),
	SavedSettingsSharedValues (R.string.savedSettingsSharedValues);
	
	int value;
	
	private StringValues(int s){
		this.value = s;
	}
	
	@Override
	public String toString(){
		return MainScreenActivity.getContext().getResources().getString(value);
	}
	
}
