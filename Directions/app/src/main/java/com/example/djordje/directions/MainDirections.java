package com.example.djordje.directions;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainDirections extends AppCompatActivity {


    public void open(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle("OKay");
        alertDialogBuilder.setItems(myArray, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                        /*dialog.dismiss();*/

                TextView txtState=(TextView)findViewById(R.id.txtState);
                txtState.setText(myArray[which]);
            }
        });
}
