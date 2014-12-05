package com.BSU.inbetween.activities;

import java.io.IOException;

import com.edu.BSU.inbetween.common.*;
import com.BSU.inbetween.activities.SharedValues;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class SaveGameDialog extends DialogFragment {
	static GameObjects objects;
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage("Would you like to save before leaving?")
				.setPositiveButton("Save game",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								 saveCurrentSettings(objects);
							}
						})
				.setNeutralButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								SaveGameDialog.this.onResume();
							}
						})
				.setNegativeButton("Exit without saving",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Intent menu = new Intent(getActivity(),
										MainScreenActivity.class);
								SaveGameDialog.this.startActivity(menu);
							}
						});
		return builder.create();
	}

	public static void saveCurrentSettings(GameObjects objects) {
		SharedValues values = SharedValues.getCreatedInstance();
		try {
			values.saveValues(objects.toArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
