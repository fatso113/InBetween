package com.BSU.inbetween.activities;

import com.BSU.inbetween.R;
import com.edu.BSU.inbetween.common.GameObjects;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class LeaveGameDialog extends DialogFragment{
	static GameObjects objects;
	
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getString(R.string.LeaveGameDialog).toString())
               .setPositiveButton(getString(R.string.LeaveGameDialog_Resume).toString(), 
            		   new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   LeaveGameDialog.this.onResume(); //resume game
                   }
               })
               .setNegativeButton(getString(R.string.LeaveGameDialog_Exit).toString(), 
            		   new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   Intent menu = new Intent(getActivity(),MainScreenActivity.class);
                	   LeaveGameDialog.this.startActivity(menu);
                	   //showSaveDialog();
                   }

				/*private void showSaveDialog() {
					SaveGameDialog.objects = objects;
					DialogFragment saveDialog = new SaveGameDialog();
					saveDialog.show(getFragmentManager(), "Would you like to save your game?");
				}*/
               });
        return builder.create();
    }
	
	public static void saveObjects(GameObjects objects){
		LeaveGameDialog.objects = objects;
	}

}
