package com.letseat.let_s_eat;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.JsonObject;

/**
 * Created by Hojae Jung on 2/11/2017.
 * Updated by Jamin Goo to implement parcelable
 */
public class Place implements Parcelable {
    private String id;
    private String name;
    private boolean open;
    private String icon;
    //private JsonArray photo;

    public Place(JsonObject jo) {
        this.id = jo.get("id").getAsString();
        this.name = jo.get("name").getAsString();
        this.open = jo.get("opening_hours").getAsJsonObject().get("open_now").getAsBoolean();
        this.icon = jo.get("icon").getAsString();
        //this.photo = jo.get("photos").getAsJsonArray();
    }

    protected Place(Parcel in) {
        id = in.readString();
        name = in.readString();
        open = in.readByte() != 0;
        icon = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeByte((byte) (open ? 1 : 0));
        dest.writeString(icon);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Place> CREATOR = new Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public boolean isOpen() {
        return this.open;
    }

    public String getIcon() {
        return this.icon;
    }

    /*
    protected JsonArray getPhoto() {
        return this.photo;
    }
    */
}
