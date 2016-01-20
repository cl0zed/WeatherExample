package com.example.weatherapp.utils;

import android.content.Context;
import android.util.Log;

import com.example.weatherapp.R;
import com.example.weatherapp.database.City;
import com.example.weatherapp.database.CitiesResult;
import com.example.weatherapp.database.CityWeather;
import com.example.weatherapp.events.FilledDataBaseEvent;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class FillCitiesTable extends Thread {

    private Context context;
    int i = 0;
    @Override
    public void run() {
        long start = System.currentTimeMillis();
        InputStream inputStream = context.getResources().openRawResource(R.raw.citiesru);

        Gson gson = new Gson();
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ( (line = reader.readLine()) != null){
                ++i;
                CitiesResult result = gson.fromJson(line, CitiesResult.class);
                new City(result.id, result.name, false);
            }
        } catch (IOException e){
            Log.d("Exception", "Read or write exception");
        }

        City city = City.getByName("Moskva").get(0);
        new City(city.cityID, city.cityName, true);
        new CityWeather(city.cityID, city.cityName, "" );

        city = City.getByName("Sankt-Peterburg").get(0);
        new City(city.cityID, city.cityName, true);
        new CityWeather(city.cityID, city.cityName, "" );

        start = System.currentTimeMillis() - start;
        Events.postOnUIThread(new FilledDataBaseEvent());
    }

    @Override
    public synchronized void start() {
        Log.d("City fill", "Thread started");
        super.start();
    }

    public FillCitiesTable(Context context){
        this.context = context;
    }
}
