package com.BSU.inbetween.activities;

import com.BSU.inbetween.R;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class PausedActivity extends Activity {

	Button resumeButton, exitButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setFullScreen();
		
		resumeButton= (Button) findViewById(R.id.resumeButton);
		exitButton= (Button) findViewById(R.id.exitButton);
		
		resumeButton.setOnClickListener(resumeClick);
		exitButton.setOnClickListener(exitClick);
}
	private void setFullScreen() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_paused);
		}
	
	View.OnClickListener resumeClick= new View.OnClickListener() {
		public void onClick(View v) {
			Intent openMainActivity= new Intent(PausedActivity.this, InGameActivity.class); 
			openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); 
			startActivity(openMainActivity);
		}
	};
	
	View.OnClickListener exitClick= new View.OnClickListener() {
		public void onClick(View v) {
			showLeaveGameDialog();
		}
		private void showLeaveGameDialog() {
		    DialogFragment leaveDialog = new LeaveGameDialog();
		    leaveDialog.show(getFragmentManager(), "Are you sure you want to leave?");
		}
	};
	
}