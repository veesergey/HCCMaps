package com.example.vsergeychik390.hccmaps;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;

import java.util.List;

public class Destination2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination2);

        //Michelle Hudela
        //used from: http://javapapers.com/android/android-read-csv-file/
        InputStream inputStream = getResources().openRawResource(R.raw.floor5);
        CSVFile csvFile = new CSVFile(inputStream);
        List floor5 = csvFile.read();
    }

        public void showConfirmButtonClicked(View view){
            //setup the alert builder
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Confirm Location and Destination");
            //Destination spinners
            Spinner destBuildingSpinner = (Spinner)findViewById(R.id.destBuildingSpinner);
            Spinner destFloorSpinner = (Spinner)findViewById(R.id.destFloorSpinner);
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

                    int[] startCord = new int[]{};
                    int[] endCord = new int[]{};

                    switch (sRoom){
                        case "502":
                            startCord = new int[]{28,3};
                            break;
                        case "503":
                            startCord = new int[]{30,1};
                            break;
                        case "505":
                            startCord = new int[]{28,1};
                            break;
                        case "507":
                            startCord = new int[]{26,1};
                            break;
                        case "509":
                            startCord = new int[]{24,1};
                            break;
                        case "513":
                            startCord = new int[]{17,3};
                            break;
                        case "514":
                            startCord = new int[]{13,3};
                            break;
                        case "516":
                            startCord = new int[]{7,3};
                            break;
                        case "518":
                            startCord = new int[]{3,3};
                            break;
                        default:
                            break;
                    }
                    switch (eRoom){
                        case "502":
                            endCord = new int[]{28,3};
                            break;
                        case "503":
                            endCord = new int[]{30,1};
                            break;
                        case "505":
                            endCord = new int[]{28,1};
                            break;
                        case "507":
                            endCord = new int[]{26,1};
                            break;
                        case "509":
                            endCord = new int[]{24,1};
                            break;
                        case "513":
                            endCord = new int[]{17,3};
                            break;
                        case "514":
                            endCord = new int[]{13,3};
                            break;
                        case "516":
                            endCord = new int[]{7,3};
                            break;
                        case "518":
                            endCord = new int[]{3,3};
                            break;
                        default:
                            break;
                    }

                    char[][] charMatrix = {
                            {'@', '@', '@', '@', '@', '@', '@'},
                            {'@', '@', '.', '.', '@', '@', '@'},
                            {'@', '@', '.', '@', '@', '@', '@'},
                            {'@', '@', '.', '.', '@', '@', '@'},
                            {'@', '@', '.', '@', '@', '@', '@'},
                            {'@', '@', '.', '@', '@', '@', '@'},
                            {'@', '@', '.', '@', '@', '@', '@'},
                            {'@', '@', '.', '.', '@', '@', '@'},
                            {'@', '@', '.', '@', '@', '@', '@'},
                            {'@', '@', '.', '@', '@', '@', '@'},
                            {'@', '@', '.', '@', '@', '@', '@'},
                            {'@', '@', '.', '@', '@', '@', '@'},
                            {'@', '@', '.', '@', '@', '@', '@'},
                            {'@', '@', '.', '.', '@', '@', '@'},
                            {'@', '@', '.', '@', '@', '@', '@'},
                            {'@', '@', '.', '@', '@', '@', '@'},
                            {'@', '@', '.', '@', '@', '.', '@'},
                            {'@', '.', '.', '.', '.', '.', '@'},
                            {'@', '@', '.', '@', '@', '.', '@'},
                            {'@', '@', '.', '.', '@', '@', '@'},
                            {'@', '@', '.', '@', '@', '@', '@'},
                            {'@', '.', '.', '.', '.', '.', '@'},
                            {'@', '@', '.', '@', '@', '@', '@'},
                            {'@', '@', '.', '@', '@', '@', '@'},
                            {'@', '.', '.', '@', '@', '@', '@'},
                            {'@', '@', '.', '@', '@', '@', '@'},
                            {'@', '.', '.', '@', '@', '@', '@'},
                            {'@', '@', '.', '@', '@', '@', '@'},
                            {'@', '.', '.', '.', '@', '@', '@'},
                            {'@', '@', '.', '@', '@', '@', '@'},
                            {'@', '.', '.', '@', '@', '@', '@'},
                            {'@', '@', '.', '@', '@', '@', '@'},
                            {'@', '.', '.', '@', '@', '@', '@'},
                            {'@', '@', '@', '@', '@', '@', '@'}
                    };

                    AStarNav nav = new AStarNav();

                    List<String[]> route = nav.getPath(startCord, endCord, charMatrix);

                    Intent directionInt = new Intent(Destination2.this, Directions.class);

                    String routeString = "";

                    for (int i = 0; i < route.size(); i++){
                        routeString += route.get(i)[0] + " ";
                        routeString += route.get(i)[1] + "\n";
                    }

                    Log.d("Route: ", routeString);

                    directionInt.putExtra("directions", routeString);

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
