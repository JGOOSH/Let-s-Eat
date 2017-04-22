package com.letseat.let_s_eat;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Random;

/**
 * Created by GOOSH on 2/17/2017.
 * Created to have sample place data
 * Will randomly create values for the attributes beside the name
 * Will substitute with real data in future
 */

public class TestPlace implements Parcelable {
    //parcel data types
    private String id;
    private String name;
    private boolean open;
    private double distance;

    //random obj for randomizing the attributes
    Random random = new Random();

    public TestPlace(Parcel p){
        id = p.readString();
        name = p.readString();
        open =  (p.readInt() == 0) ? false: true;
        distance = p.readDouble();
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeInt(open ? 1 : 0); //if open, write 1 otherwise 0
        dest.writeDouble(distance);
    }
    public static final Parcelable.Creator<TestPlace> CREATOR
            = new Parcelable.Creator<TestPlace>() {
        public TestPlace createFromParcel(Parcel p){
            return new TestPlace(p);
        }

        public TestPlace[] newArray(int size){
            return new TestPlace[size];
        }
    };

    }
