package com.example.vsergeychik390.hccmaps;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

public class Destination2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination2);
    }

        public void showConfirmButtonClicked(View view){
            //setup the alert builder
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Confirm Location and Destination");
            //Destination spinners
            Spinner destBuildingSpinner = (Spinner)findViewById(R.id.destBuildingSpinner);
            Spinner destFloorSpinner = (Spinner)findViewById(R.id.destFloorSpinner);
            Spinner destRoomSpinner = (Spinner)findViewById(R.id.destRoomSpinner);
            //Location spinner objects
            Spinner buildingSpinner = (Spinner)findViewById(R.id.buildingSpinner);
            Spinner floorSpinner = (Spinner)findViewById(R.id.floorSpinner);
            Spinner roomSpinner = (Spinner)findViewById(R.id.roomSpinner);
            //Message
            builder.setMessage("Location: " + buildingSpinner.getSelectedItem().toString() + " " +
                    floorSpinner.getSelectedItem().toString() + " Rm " + roomSpinner.getSelectedItem().toString()
                    + "\nDestination:" + destBuildingSpinner.getSelectedItem().toString() + " " + destFloorSpinner.getSelectedItem().toString() +
                    " " + destRoomSpinner.getSelectedItem().toString() + "\nIs this correct?");

            //add the buttons
            builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog,int which){
                    startActivity(new Intent(Destination2.this, Directions.class));
                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog,int which){

                }
            });
            //create and show the alert dialog
            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }
