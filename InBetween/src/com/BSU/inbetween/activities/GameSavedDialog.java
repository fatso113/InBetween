package com.BSU.inbetween.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class GameSavedDialog extends DialogFragment{

	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Game Saved")
               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   Intent menu = new Intent(getActivity(), MainScreenActivity.class);
                	   GameSavedDialog.this.startActivity(menu);
                   }
               });
        return builder.create();
    }
}