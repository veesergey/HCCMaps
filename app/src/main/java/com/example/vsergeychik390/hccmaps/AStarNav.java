package com.example.vsergeychik390.hccmaps; /**
 * Created by jacobbashista on 11/17/17.
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import GridNav.GridNav;
import GridNav.Options;
import GridNav.Vertex;


public class AStarNav {

// Example Use
//    AStarNav nav = new AStarNav();
//
//    char[][] charMatrix = {
//            {'@', '@', '@', '@', '@', '@', '@'},
//            {'@', '@', '.', '.', '@', '@', '@'},
//            {'@', '@', '.', '@', '@', '@', '@'},
//            {'@', '@', '.', '.', '@', '@', '@'},
//            {'@', '@', '.', '@', '@', '@', '@'},
//            {'@', '@', '.', '@', '@', '@', '@'},
//            {'@', '@', '.', '@', '@', '@', '@'},
//            {'@', '@', '.', '.', '@', '@', '@'},
//            {'@', '@', '.', '@', '@', '@', '@'},
//            {'@', '@', '.', '@', '@', '@', '@'},
//            {'@', '@', '.', '@', '@', '@', '@'},
//            {'@', '@', '.', '@', '@', '@', '@'},
//            {'@', '@', '.', '@', '@', '@', '@'},
//            {'@', '@', '.', '.', '@', '@', '@'},
//            {'@', '@', '.', '@', '@', '@', '@'},
//            {'@', '@', '.', '@', '@', '@', '@'},
//            {'@', '@', '.', '@', '@', '.', '@'},
//            {'@', '.', '.', '.', '.', '.', '@'},
//            {'@', '@', '.', '@', '@', '.', '@'},
//            {'@', '@', '.', '.', '@', '@', '@'},
//            {'@', '@', '.', '@', '@', '@', '@'},
//            {'@', '.', '.', '.', '.', '.', '@'},
//            {'@', '@', '.', '@', '@', '@', '@'},
//            {'@', '@', '.', '@', '@', '@', '@'},
//            {'@', '.', '.', '@', '@', '@', '@'},
//            {'@', '@', '.', '@', '@', '@', '@'},
//            {'@', '.', '.', '@', '@', '@', '@'},
//            {'@', '@', '.', '@', '@', '@', '@'},
//            {'@', '.', '.', '.', '@', '@', '@'},
//            {'@', '@', '.', '@', '@', '@', '@'},
//            {'@', '.', '.', '@', '@', '@', '@'},
//            {'@', '@', '.', '@', '@', '@', '@'},
//            {'@', '.', '.', '@', '@', '@', '@'},
//            {'@', '@', '@', '@', '@', '@', '@'}
//    };
//
//
//    int[] start = {28, 3};
//    int[] end = {17, 1};
//
//    TextView routeText = findViewById(R.id.mainText);
//
//    String routeString = "";
//
//
//    List<String[]> route = nav.getPath(start, end, charMatrix);
//
//        for (int i = 0; i < route.size(); i++){
//        routeString += route.get(i)[0] + " ";
//        routeString += route.get(i)[1] + "\n";
//    }
//
//    routeText.setText(routeString);


    public AStarNav(){

    }


    public List<String[]> getPath(int[] startCord, int[] endCord,char[][] charMatrix){
        GridNav gn = new GridNav();


        gn.loadCharMatrix(charMatrix);

        int[] start = startCord;
        int[] goal = endCord;
        ArrayDeque<Vertex> bestRoute = gn.route(start, goal, Options.ASTAR, Options.NO_HEURISTIC, false);

        List pHis = new ArrayList();

        List<String[]> route = new ArrayList<>();


        int i = 0;
        boolean wasStraight = false;
        String heading = "";
        String cordData;

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
                        cardinal = "North ";
                        heading = "North";
                    } else if (p0.getY() < p2.getY()) {
                        cardinal = "South ";
                        heading = "South";
                    } else if (p0.getX() < p2.getX()) {
                        cardinal = "East ";
                        heading = "East";
                    } else {
                        cardinal = "West ";
                        heading = "West";
                    }

                    String[] direction = new String[]{task, cardinal};

                    route.add(direction);

                    wasStraight = true;
                } else if (exactAngle == 90) {
                    task = "Turn ";

                    if (heading == "North" && (p0.getX() > p2.getX())) {
                        cardinal = "West ";
                    } else if (heading == "North" && (p0.getX() < p2.getX())) {
                        cardinal = "East ";
                    } else if (heading == "South" && (p0.getX() > p2.getX())) {
                        cardinal = "West ";
                    } else if (heading == "South" && (p0.getX() < p2.getX())) {
                        cardinal = "East ";
                    } else if (heading == "East" && (p0.getY() > p2.getY())) {
                        cardinal = "South ";
                    } else if (heading == "East" && (p0.getY() < p2.getY())) {
                        cardinal = "North ";
                    } else if (heading == "West" && (p0.getY() > p2.getY())) {
                        cardinal = "North ";
                    } else {
                        cardinal = "South ";
                    }
                    String[] direction = new String[]{task, cardinal};
                    route.add(direction);
                    wasStraight = false;
                }
            }
        }

        return route;

    }

    String checkCordData(Vertex cCord){
        //Check Michelle's Database Here.
        return "No Data";
    }

    Double findAngle(Vertex p0,Vertex p1, Vertex p2) {
        Double a = Math.pow(p1.getX()-p0.getX(),2) + Math.pow(p1.getY()-p0.getY(),2);
        Double b = Math.pow(p1.getX()-p2.getX(),2) + Math.pow(p1.getY()-p2.getY(),2);
        Double c = Math.pow(p2.getX()-p0.getX(),2) + Math.pow(p2.getY()-p0.getY(),2);
        return Math.acos( (a+b-c) / Math.sqrt(4*a*b) );
    }

}
