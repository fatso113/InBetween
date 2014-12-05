package com.BSU.inbetween.activities;

import java.util.Locale;

import com.BSU.inbetween.R;
import com.edu.BSU.inbetween.common.HelpTextParser;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HelpActivity extends Activity {

	String localLanguage;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		localLanguage=Locale.getDefault().getLanguage();
		AssetManager assetManager= getAssets();
		HelpTextParser.setAssets(assetManager);
		setHelpText();
		setVersion();
		
		RelativeLayout helpText= (RelativeLayout) findViewById(R.id.help_text_all);
		helpText.setScrollY(1);
		
		TextView feedbackLink=(TextView) findViewById(R.id.feedback_text);
		Linkify.addLinks(feedbackLink, Linkify.ALL);
		
	}	
	
	private void setHelpText(){
		String filename;
		String output;
		if(localLanguage.equals("es"))//Spanish
			filename="Rules_es.txt";
		else if(localLanguage.equals("fr"))//French
			filename="Rules_fr.txt";
		else if(localLanguage.equals("ne"))//Nepali
			filename="Rules_ne.txt";
		else
			filename="Rules.txt";//default, English
		output=HelpTextParser.parseHelpFile(filename);
		TextView help=(TextView) findViewById(R.id.help_text);
		help.setText(output);
	}
	
	private void setVersion(){
		PackageInfo pInfo;
		String version="";
		try {
			pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			version=pInfo.versionName;
		} catch (NameNotFoundException e) {
			Log.i("setVerion", e.getMessage());
			version="Sorry, error getting app version.";
		}
		
		TextView appVersion=(TextView) findViewById(R.id.help_appVersion);
		String vText=appVersion.getText().toString();
		appVersion.setText(vText+": "+version+"\n");
		
	}
	
}
