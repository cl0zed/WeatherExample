package com.example.weatherapp.database;




import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DayWeatherResult {

    public double dayTemp = -1;

    public DayWeatherResult(String weather){
        try{
            JSONObject json = new JSONObject(weather);
            JSONArray array = json.getJSONArray("list");

            JSONObject tempObj = array.getJSONObject(0).getJSONObject("temp");
            dayTemp = tempObj.getDouble("day");
        } catch (JSONException e){
            Log.e("Error", "Cant parse temp json");
        }


    }
}