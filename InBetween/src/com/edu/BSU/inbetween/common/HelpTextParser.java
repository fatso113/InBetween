package com.edu.BSU.inbetween.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.util.Log;

public class HelpTextParser {
	
	private static AssetManager assetFiles;
	
	public static void setAssets(AssetManager assets){
		assetFiles=assets;
	}
	
	public static String parseHelpFile(String filename){
		String output;
		InputStream is = null;
		try {
			is = assetFiles.open("txt/"+filename);
			output=readTextFile(is);
		} 
		catch (IOException e) {
			output="Sorry, help file not found.";
			Log.i("IO error", e.getMessage());
		}
		return output;
	}
	
	private static String readTextFile(InputStream inputStream) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte buf[] = new byte[1024];
		int len;
		try {
			while ((len = inputStream.read(buf)) != -1) {
				outputStream.write(buf, 0, len);
		}
		outputStream.close();
		inputStream.close();
		} catch (IOException e) {
			Log.i("IO error", e.getMessage());
			return "Sorry, help file not found.";
		}
		return outputStream.toString();
		}
}
