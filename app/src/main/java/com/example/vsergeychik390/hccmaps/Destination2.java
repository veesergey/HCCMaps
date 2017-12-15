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
                    Log.d("End Floor", eFloor);

                    Log.d("Start Room", sRoom);
                    Log.d("End Room", eRoom);

                    switch (sFloor){
                        case ("Floor One"):
                            break;
                        case ("Floor Two"):
                            switch(sRoom){
                                case ("201"):
                                    startCord = new int[] {139, 1};
                                    break;
                                case ("203"):
                                    startCord = new int[] {135, 1};
                                    break;
                                case ("204"):
                                    startCord = new int[] {130, 4};
                                    break;
                                case ("205"):
                                    startCord = new int[] {133, 1};
                                    break;
                                case ("211"):
                                    startCord = new int[] {122, 2};
                                    break;
                                case ("215"):
                                    startCord = new int[] {120, 2};
                                    break;
                                case ("217"):
                                    startCord = new int[] {118, 2};
                                    break;
                                case ("219"):
                                    startCord = new int[] {116, 2};
                                    break;
                                case ("220"):
                                    startCord = new int[] {121, 4};
                                    break;
                                case ("221"):
                                    startCord = new int[] {114, 2};
                                    break;
                                case ("222"):
                                    startCord = new int[] {119, 4};
                                    break;
                                case ("224"):
                                    startCord = new int[] {115, 4};
                                    break;
                                case ("226"):
                                    startCord = new int[] {111, 4};
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case ("Floor Three"):
                            switch(sRoom){
                                case ("302"):
                                    startCord = new int[] {97, 5};
                                    break;
                                case ("303"):
                                    startCord = new int[] {92, 1};
                                    break;
                                case ("307"):
                                    startCord = new int[] {88, 1};
                                    break;
                                case ("309"):
                                    startCord = new int[] {86, 1};
                                    break;
                                case ("311"):
                                    startCord = new int[] {84, 1};
                                    break;
                                case ("312"):
                                    startCord = new int[] {85, 4};
                                    break;
                                case ("313"):
                                    startCord = new int[] {82, 1};
                                    break;
                                case ("314"):
                                    startCord = new int[] {83, 4};
                                    break;
                                case ("315"):
                                    startCord = new int[] {80, 1};
                                    break;
                                case ("316"):
                                    startCord = new int[] {79, 4};
                                    break;
                                case ("319"):
                                    startCord = new int[] {78, 1};
                                    break;
                                case ("322"):
                                    startCord = new int[] {75, 4};
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case ("Floor Four"):
                            switch(sRoom){
                                case ("401"):
                                    startCord = new int[] {67, 1};
                                    break;
                                case ("402"):
                                    startCord = new int[] {65, 3};
                                    break;
                                case ("403"):
                                    startCord = new int[] {65, 1};
                                    break;
                                case ("407"):
                                    startCord = new int[] {63, 1};
                                    break;
                                case ("409"):
                                    startCord = new int[] {61, 1};
                                    break;
                                case ("417"):
                                    startCord = new int[] {52, 1};
                                    break;
                                case ("419"):
                                    startCord = new int[] {50, 1};
                                    break;
                                case ("420"):
                                    startCord = new int[] {49, 3};
                                    break;
                                case ("421"):
                                    startCord = new int[] {48, 1};
                                    break;
                                case ("422"):
                                    startCord = new int[] {43, 3};
                                    break;
                                case ("423"):
                                    startCord = new int[] {46, 1};
                                    break;
                                case ("424"):
                                    startCord = new int[] {40, 3};
                                    break;
                                case ("425"):
                                    startCord = new int[] {44, 1};
                                    break;
                                case ("427"):
                                    startCord = new int[] {42, 1};
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case ("Floor Five"):
                            switch(sRoom){
                                case ("501"):
                                    startCord = new int[] {33, 1};
                                    break;
                                case ("502"):
                                    startCord = new int[] {29, 3};
                                    break;
                                case ("503"):
                                    startCord = new int[] {31, 1};
                                    break;
                                case ("505"):
                                    startCord = new int[] {29, 1};
                                    break;
                                case ("507"):
                                    startCord = new int[] {27, 1};
                                    break;
                                case ("509"):
                                    startCord = new int[] {25, 1};
                                    break;
                                case ("513"):
                                    startCord = new int[] {17, 1};
                                    break;
                                case ("514"):
                                    startCord = new int[] {13, 3};
                                    break;
                                case ("516"):
                                    startCord = new int[] {7, 3};
                                    break;
                                case ("518"):
                                    startCord = new int[] {3, 3};
                                    break;
                                default:
                                    break;
                            }
                            break;

                    }
                    switch (eFloor){
                        case ("Floor One"):
                            break;
                        case ("Floor Two"):
                            switch(eRoom){
                                case ("201"):
                                    endCord = new int[] {139, 1};
                                    break;
                                case ("203"):
                                    endCord = new int[] {135, 1};
                                    break;
                                case ("204"):
                                    endCord = new int[] {130, 4};
                                    break;
                                case ("205"):
                                    endCord = new int[] {133, 1};
                                    break;
                                case ("211"):
                                    endCord = new int[] {122, 2};
                                    break;
                                case ("215"):
                                    endCord = new int[] {120, 2};
                                    break;
                                case ("217"):
                                    endCord = new int[] {118, 2};
                                    break;
                                case ("219"):
                                    endCord = new int[] {116, 2};
                                    break;
                                case ("220"):
                                    endCord = new int[] {121, 4};
                                    break;
                                case ("221"):
                                    endCord = new int[] {114, 2};
                                    break;
                                case ("222"):
                                    endCord = new int[] {119, 4};
                                    break;
                                case ("224"):
                                    endCord = new int[] {115, 4};
                                    break;
                                case ("226"):
                                    endCord = new int[] {111, 4};
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case ("Floor Three"):
                            switch(eRoom){
                                case ("302"):
                                    endCord = new int[] {97, 5};
                                    break;
                                case ("303"):
                                    endCord = new int[] {92, 1};
                                    break;
                                case ("307"):
                                    endCord = new int[] {88, 1};
                                    break;
                                case ("309"):
                                    endCord = new int[] {86, 1};
                                    break;
                                case ("311"):
                                    endCord = new int[] {84, 1};
                                    break;
                                case ("312"):
                                    endCord = new int[] {85, 4};
                                    break;
                                case ("313"):
                                    endCord = new int[] {82, 1};
                                    break;
                                case ("314"):
                                    endCord = new int[] {83, 4};
                                    break;
                                case ("315"):
                                    endCord = new int[] {80, 1};
                                    break;
                                case ("316"):
                                    endCord = new int[] {79, 4};
                                    break;
                                case ("319"):
                                    endCord = new int[] {78, 1};
                                    break;
                                case ("322"):
                                    endCord = new int[] {75, 4};
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case ("Floor Four"):
                            switch(eRoom){
                                case ("401"):
                                    endCord = new int[] {67, 1};
                                    break;
                                case ("402"):
                                    endCord = new int[] {65, 3};
                                    break;
                                case ("403"):
                                    endCord = new int[] {65, 1};
                                    break;
                                case ("407"):
                                    endCord = new int[] {63, 1};
                                    break;
                                case ("409"):
                                    endCord = new int[] {61, 1};
                                    break;
                                case ("417"):
                                    endCord = new int[] {52, 1};
                                    break;
                                case ("419"):
                                    endCord = new int[] {50, 1};
                                    break;
                                case ("420"):
                                    endCord = new int[] {49, 3};
                                    break;
                                case ("421"):
                                    endCord = new int[] {48, 1};
                                    break;
                                case ("422"):
                                    endCord = new int[] {43, 3};
                                    break;
                                case ("423"):
                                    endCord = new int[] {46, 1};
                                    break;
                                case ("424"):
                                    endCord = new int[] {40, 3};
                                    break;
                                case ("425"):
                                    endCord = new int[] {44, 1};
                                    break;
                                case ("427"):
                                    endCord = new int[] {42, 1};
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case ("Floor Five"):
                            switch(eRoom){
                                case ("501"):
                                    endCord = new int[] {33, 1};
                                    break;
                                case ("502"):
                                    endCord = new int[] {29, 3};
                                    break;
                                case ("503"):
                                    endCord = new int[] {31, 1};
                                    break;
                                case ("505"):
                                    endCord = new int[] {29, 1};
                                    break;
                                case ("507"):
                                    endCord = new int[] {27, 1};
                                    break;
                                case ("509"):
                                    endCord = new int[] {25, 1};
                                    break;
                                case ("513"):
                                    endCord = new int[] {17, 1};
                                    break;
                                case ("514"):
                                    endCord = new int[] {13, 3};
                                    break;
                                case ("516"):
                                    endCord = new int[] {7, 3};
                                    break;
                                case ("518"):
                                    endCord = new int[] {3, 3};
                                    break;
                                default:
                                    break;
                            }
                            break;

                    }


                    Log.d("Start Cord", String.valueOf(startCord[1]) + "," + String.valueOf(startCord[0]));
                    Log.d("End Cord", String.valueOf(endCord[1]) + "," + String.valueOf(endCord[0]));

                    List<String[]> route = nav.getPath(startCord, endCord);

                    String routeString = "";

                    Log.d("Route", routeString);

                    for (int i = 0; i < route.size(); i++) {
                        if (route.get(i)[2].contains("NT")) {
                        }else {
                            routeString += route.get(i)[0] + "";
                            routeString += route.get(i)[1] + "";
                            routeString += route.get(i)[2] + "\n";
                        }
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
