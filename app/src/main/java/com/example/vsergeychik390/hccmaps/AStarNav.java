package com.example.vsergeychik390.hccmaps; /**
 * Created by jacobbashista on 11/17/17.
 */

import android.content.Context;
import android.util.Log;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import GridNav.GridNav;
import GridNav.Options;
import GridNav.Vertex;


public class AStarNav {

    private Context context;

    public AStarNav(Context current){
        this.context = current;
    }


    public List<String[]> getPath(int[] startCord, int[] endCord){
        GridNav gn = new GridNav();


        InputStream in = context.getResources().openRawResource(R.raw.kittredge);
        try {
            File file = File.createTempFile("floor", ".map");

            file.deleteOnExit();
            FileOutputStream out = new FileOutputStream(file);
            IOUtils.copy(in, out);
            gn.loadCharMatrix(gn.dotMapToCharMatrix(file));


        }catch(Exception ex) {
            ex.printStackTrace();
        }



        int[] start = startCord;
        int[] goal = endCord;
        ArrayDeque<Vertex> bestRoute = gn.route(start, goal, Options.ASTAR, Options.NO_HEURISTIC, false);

        List pHis = new ArrayList();

        List<String[]> route = new ArrayList<>();

        Log.d("Start", String.valueOf(start[0]));
        Log.d("End", String.valueOf(goal[0]));


        int i = 0;
        boolean wasStraight = false;
        String heading = "";
        String cordData;

        Log.d("Route: ", bestRoute.toString());

        while(!bestRoute.isEmpty()) {

            i++;

            Vertex v = bestRoute.pop();

            pHis.add(0, v);

            if (pHis.size() > 2) {

                Vertex p0 = (Vertex) pHis.get(0);
                Vertex p1 = (Vertex) pHis.get(1);
                Vertex p2 = (Vertex) pHis.get(2);


                Double angle = findAngle(v, p1, p2) * 180 / 3.14;

                int exactAngle = angle.intValue();

                cordData = checkCordData(p0);

                String task;
                String cardinal;


                if (exactAngle == 180 && wasStraight == false) {


                    task = "Straight ";

                    if (p0.getY() > p2.getY()) {
                        cardinal = "North, ";
                        heading = "North";
                    } else if (p0.getY() < p2.getY()) {
                        cardinal = "South, ";
                        heading = "South";
                    } else if (p0.getX() < p2.getX()) {
                        cardinal = "East, ";
                        heading = "East";
                    } else {
                        cardinal = "West, ";
                        heading = "West";
                    }

                    String[] direction = new String[]{task, cardinal, checkCordData(p0)};

                    route.add(direction);

                    wasStraight = true;
                } else if (exactAngle == 90) {
                    task = "Turn, ";

                    if (heading == "North" && (p0.getX() > p2.getX())) {
                        cardinal = "West, ";
                    } else if (heading == "North" && (p0.getX() < p2.getX())) {
                        cardinal = "East, ";
                    } else if (heading == "South" && (p0.getX() > p2.getX())) {
                        cardinal = "West, ";
                    } else if (heading == "South" && (p0.getX() < p2.getX())) {
                        cardinal = "East, ";
                    } else if (heading == "East" && (p0.getY() > p2.getY())) {
                        cardinal = "South, ";
                    } else if (heading == "East" && (p0.getY() < p2.getY())) {
                        cardinal = "North, ";
                    } else if (heading == "West" && (p0.getY() > p2.getY())) {
                        cardinal = "North, ";
                    } else {
                        cardinal = "South, ";
                    }
                    String[] direction = new String[]{task, cardinal, checkCordData(p0)};
                    route.add(direction);
                    wasStraight = false;
                }
            }
        }

        Log.d("Route", route.toString());

        return route;

    }

    String checkCordData(Vertex cCord){


        int x = cCord.getX();
        int y = cCord.getY();

        String data = "No Data";


        InputStream inputStream = context.getResources().openRawResource(R.raw.floor5);
        CSVFile csvFile = new CSVFile(inputStream);
        List<String[]> pointsList = csvFile.read();

        for (int i = 0; i < pointsList.size(); i++){

            String[] line = pointsList.get(i);

            int lX = Integer.parseInt(line[0]);
            int lY = Integer.parseInt(line[1]);
            String type = line[2];
            String name = line[3];

            if(lX == x && lY == y){
                data = name + " " + type;
            }

        }

        return data;
    }

    Double findAngle(Vertex p0,Vertex p1, Vertex p2) {
        Double a = Math.pow(p1.getX()-p0.getX(),2) + Math.pow(p1.getY()-p0.getY(),2);
        Double b = Math.pow(p1.getX()-p2.getX(),2) + Math.pow(p1.getY()-p2.getY(),2);
        Double c = Math.pow(p2.getX()-p0.getX(),2) + Math.pow(p2.getY()-p0.getY(),2);
        return Math.acos( (a+b-c) / Math.sqrt(4*a*b) );
    }

}
