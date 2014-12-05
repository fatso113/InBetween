package com.BSU.inbetween.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class ErrorDialog extends DialogFragment{

	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("We're sorry, there was internal error. InBetween needs to close.")
               .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   Intent homeIntent= new Intent(Intent.ACTION_MAIN);
                	   homeIntent.addCategory(Intent.CATEGORY_HOME);
                	   homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                	   startActivity(homeIntent);
                   }
               });
        return builder.create();
    }
}