package com.example.vsergeychik390.hccmaps;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class DestinationPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_page);
        Intent intent = getIntent();
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
        builder.setPositiveButton("Confirm", null);
        builder.setNegativeButton("Cancel",null);

        //create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
