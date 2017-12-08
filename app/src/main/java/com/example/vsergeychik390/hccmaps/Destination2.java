package com.example.vsergeychik390.hccmaps;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.IOException;
import java.util.List;

public class Destination2 extends AppCompatActivity {


    AStarNav nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination2);

        nav = new AStarNav(this);

        //Getting the spinners to cooperate
        final Spinner floorSpinner = (Spinner) findViewById(R.id.floorSpinner);
        final Spinner roomSpinner = (Spinner) findViewById(R.id.roomSpinner);

        final Spinner destFloorSpinner = (Spinner) findViewById(R.id.destFloorSpinner);
        final Spinner destRoomSpinner = (Spinner) findViewById(R.id.destRoomSpinner);

        String[] fifthFloorRooms = getResources().getStringArray(R.array.rooms_array);
        String[] fourthFloorRooms = getResources().getStringArray(R.array.fourth_floor_rooms_array);
        String[] thirdFloorRooms = getResources().getStringArray(R.array.third_floor_rooms_array);
        String[] secondFloorRooms = getResources().getStringArray(R.array.second_floor_rooms_array);
        String[] firstFloorRooms = getResources().getStringArray(R.array.first_floor_rooms_array);

        final ArrayAdapter<String> fifthFloorAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, fifthFloorRooms);
        final ArrayAdapter<String> fourthFloorAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, fourthFloorRooms);
        final ArrayAdapter<String> thirdFloorAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, thirdFloorRooms);
        final ArrayAdapter<String> secondFloorAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, secondFloorRooms);
        final ArrayAdapter<String> firstFloorAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, firstFloorRooms);


        floorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id){
                if (floorSpinner.getSelectedItemPosition() == 3){
                    roomSpinner.setAdapter(fourthFloorAdapter);
                }else if (floorSpinner.getSelectedItemPosition() == 4){
                    roomSpinner.setAdapter(fifthFloorAdapter);
                }else if (floorSpinner.getSelectedItemPosition() == 2){
                    roomSpinner.setAdapter(thirdFloorAdapter);}
                else if (floorSpinner.getSelectedItemPosition() == 1){
                    roomSpinner.setAdapter(secondFloorAdapter);}
                else if (floorSpinner.getSelectedItemPosition() == 0){
                    roomSpinner.setAdapter(firstFloorAdapter);}
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        destFloorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id){
                if (destFloorSpinner.getSelectedItemPosition() == 3){
                    destRoomSpinner.setAdapter(fourthFloorAdapter);
                }else if (destFloorSpinner.getSelectedItemPosition() == 4){
                    destRoomSpinner.setAdapter(fifthFloorAdapter);
                }else if (destFloorSpinner.getSelectedItemPosition() == 2) {
                    destRoomSpinner.setAdapter(thirdFloorAdapter);
                }else if (destFloorSpinner.getSelectedItemPosition() == 1) {
                    destRoomSpinner.setAdapter(secondFloorAdapter);
                }else if (destFloorSpinner.getSelectedItemPosition() == 0){
                    destRoomSpinner.setAdapter(firstFloorAdapter);}

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

        public void showConfirmButtonClicked(View view){
            //setup the alert builder
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Confirm Location and Destination");
            //Destination spinners
            Spinner destBuildingSpinner = (Spinner)findViewById(R.id.destBuildingSpinner);
            final Spinner destFloorSpinner = (Spinner)findViewById(R.id.destFloorSpinner);
            final Spinner destRoomSpinner = (Spinner)findViewById(R.id.destRoomSpinner);
            //Location spinner objects
            Spinner buildingSpinner = (Spinner)findViewById(R.id.buildingSpinner);
            final Spinner floorSpinner = (Spinner)findViewById(R.id.floorSpinner);
            final Spinner roomSpinner = (Spinner)findViewById(R.id.roomSpinner);
            //Message
            builder.setMessage("Location: " + buildingSpinner.getSelectedItem().toString() + " " +
                    floorSpinner.getSelectedItem().toString() + " Rm " + roomSpinner.getSelectedItem().toString()
                    + "\nDestination:" + destBuildingSpinner.getSelectedItem().toString() + " " + destFloorSpinner.getSelectedItem().toString() +
                    " " + destRoomSpinner.getSelectedItem().toString() + "\nIs this correct?");

            //add the buttons
            builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog,int which){

                    String sRoom = roomSpinner.getSelectedItem().toString();
                    String eRoom = destRoomSpinner.getSelectedItem().toString();
                    String sFloor = floorSpinner.getSelectedItem().toString();
                    String eFloor = destFloorSpinner.getSelectedItem().toString();


                    int[] startCord = new int[]{};
                    int[] endCord = new int[]{};


                    Log.d("Start Floor", sFloor);

                    switch (sFloor){
                        case ("Floor One"):
                            break;
                        case ("Floor Two"):
                            break;
                        case ("Floor Three"):
                            break;
                        case ("Floor Four"):
                            break;
                        case ("Floor Five"):
                            switch(sRoom){
                                case ("518"):
                                    startCord = new int[] {3, 3};
                                    break;
                                case ("516"):
                                    startCord = new int[] {7, 3};
                                    break;
                                case ("514"):
                                    startCord = new int[] {13, 3};
                                    break;
                                case ("513"):
                                    startCord = new int[] {17, 1};
                                    break;
                                case ("509"):
                                    startCord = new int[] {24, 1};
                                    break;
                                case ("507"):
                                    startCord = new int[] {26, 1};
                                    break;
                                case ("505"):
                                    startCord = new int[] {28, 1};
                                    break;
                                case ("503"):
                                    startCord = new int[] {30, 1};
                                    break;
                                case ("501"):
                                    startCord = new int[] {32, 1};
                                    break;
                                case ("502"):
                                    startCord = new int[] {28, 3};
                                    break;
                            }
                            break;

                    }
                    switch (eFloor){
                        case ("Floor One"):
                            break;
                        case ("Floor Two"):
                            break;
                        case ("Floor Three"):
                            break;
                        case ("Floor Four"):
                            break;
                        case ("Floor Five"):
                            switch(sRoom){
                                case ("518"):
                                    endCord = new int[] {3, 3};
                                    break;
                                case ("516"):
                                    endCord = new int[] {7, 3};
                                    break;
                                case ("514"):
                                    endCord = new int[] {13, 3};
                                    break;
                                case ("513"):
                                    endCord = new int[] {17, 1};
                                    break;
                                case ("509"):
                                    endCord = new int[] {24, 1};
                                    break;
                                case ("507"):
                                    endCord = new int[] {26, 1};
                                    break;
                                case ("505"):
                                    endCord = new int[] {28, 1};
                                    break;
                                case ("503"):
                                    endCord = new int[] {30, 1};
                                    break;
                                case ("501"):
                                    endCord = new int[] {32, 1};
                                    break;
                                case ("502"):
                                    endCord = new int[] {28, 3};
                                    break;
                            }
                            break;

                    }


                    List<String[]> route = nav.getPath(startCord, endCord);

                    String routeString = "";

                    Log.d("Route", routeString);

                    for (int i = 0; i < route.size(); i++) {
                        routeString += route.get(i)[0] + "";
                        routeString += route.get(i)[1] + "";
                        routeString += route.get(i)[2] + "\n";
                    }

                    Log.d("Route: ", routeString);

                    SharedPreferences sp = getSharedPreferences("RoutePrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("Route", routeString);
                    editor.commit();

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
