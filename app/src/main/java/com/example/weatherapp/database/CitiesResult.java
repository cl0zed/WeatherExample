package com.example.weatherapp.database;


import com.google.gson.annotations.SerializedName;

public class CitiesResult {

    @SerializedName("_id")
    public long id;

    @SerializedName("name")
    public String name;

    @SerializedName("country")
    public String country;

    public Coordinates coordinates;

    @Override
    public String toString() {
        return "Id: " + id + "\t\tname: " + name + "\t\tcountry: " + country;
    }
}
class Coordinates {

    @SerializedName("lon")
    public double lon;

    @SerializedName("lat")
    public double lat;
}
