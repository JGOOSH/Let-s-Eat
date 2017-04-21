package com.letseat.let_s_eat;

import java.util.Random;

/**
 * Created by GOOSH on 2/17/2017.
 * Created to have sample place data
 * Will randomly create values for the attributes beside the name
 * Will substitute with real data in future
 */

public class TestPlace {
    private String id;
    private String name;
    private boolean open;
    private double distance;

    //random obj for randomizing the attributes
    Random random = new Random();

    public TestPlace(String name){
        String id = Integer.toString(name.hashCode());
        this.name = name;
        this.open = random.nextInt(1) == 1;
        this.distance = random.nextDouble()*100;
    }

    public String getName(){
        return name;
    }

    public String getId() {return id; }

    public boolean getOpen() {return open;}

    public double getDistance() {return distance;}
}
