package com.example.vsergeychik390.hccmaps;

import android.content.Intent;
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

        Intent intent = getIntent();
        String directions = intent.getStringExtra("directions");



        TextView routeBox = (TextView) findViewById(R.id.messageBox);

        routeBox.setText(directions);


    }
}
