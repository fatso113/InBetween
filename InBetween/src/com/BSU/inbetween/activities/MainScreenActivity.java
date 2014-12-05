package com.BSU.inbetween.activities;

import com.BSU.inbetween.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainScreenActivity extends Activity
{

	private static Context context;
	Button playButton, helpButton, settingsButton;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this;
		playButton= (Button) findViewById(R.id.playButton);
		helpButton= (Button) findViewById(R.id.helpButton);
		settingsButton= (Button) findViewById(R.id.settingsButton);
		
		playButton.setOnClickListener(playClick);
		helpButton.setOnClickListener(helpClick);
		settingsButton.setOnClickListener(settingsClick);
		
	}
	
	View.OnClickListener playClick= new View.OnClickListener() {
		public void onClick(View v) {
			Intent playGame = new Intent(MainScreenActivity.this, InGameActivity.class);
			MainScreenActivity.this.startActivity(playGame);
		}
	};
	
	View.OnClickListener helpClick= new View.OnClickListener() {
		public void onClick(View v) {
			Intent getHelp = new Intent(MainScreenActivity.this, HelpActivity.class);
			MainScreenActivity.this.startActivity(getHelp);
			
		}
	};
	
	View.OnClickListener settingsClick= new View.OnClickListener() {
		public void onClick(View v) {
			Intent changeSettings = new Intent(MainScreenActivity.this, SettingsActivity.class);
			MainScreenActivity.this.startActivity(changeSettings);
			
		}
	};
	@Override
	public void onBackPressed()
	{
		Intent homeIntent= new Intent(Intent.ACTION_MAIN);
		homeIntent.addCategory(Intent.CATEGORY_HOME);
		homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(homeIntent);
	}
	
	public static Context getContext(){
		return context;
	}
	
}