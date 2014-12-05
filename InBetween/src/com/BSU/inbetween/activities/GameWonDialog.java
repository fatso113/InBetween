package com.BSU.inbetween.activities;

import com.BSU.inbetween.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class GameWonDialog extends DialogFragment{

	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getString(R.string.GameWonDialog).toString())
               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   Intent menu = new Intent(getActivity(), MainScreenActivity.class);
                	   GameWonDialog.this.startActivity(menu);
                   }
               });
        return builder.create();
    }
}
