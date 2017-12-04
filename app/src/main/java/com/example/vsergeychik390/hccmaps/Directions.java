package com.example.vsergeychik390.hccmaps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class Directions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directions);

        String route = "";

        SharedPreferences sp = getSharedPreferences("RoutePrefs",
                        Context.MODE_PRIVATE);
        route = sp.getString("Route", route);



        TextView routeBox = (TextView) findViewById(R.id.messageBox);

        routeBox.setText(route);


    }
}
