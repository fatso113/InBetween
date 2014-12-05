package com.BSU.inbetween.activities;

import com.BSU.inbetween.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class SettingsDefaultDialog extends DialogFragment{

	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getString(R.string.SettingsDefaultDialog).toString())
               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   SettingsDefaultDialog.this.onResume();
                   }
               });
        return builder.create();
    }

}
