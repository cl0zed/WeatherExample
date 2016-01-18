package com.example.weatherapp.utils;

import android.content.Context;
import android.util.Log;

import com.example.weatherapp.R;
import com.example.weatherapp.database.CitiesResult;
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
        InputStream inputStream = context.getResources().openRawResource(R.raw.cities);
        Gson gson = new Gson();
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ( (line = reader.readLine()) != null){
                CitiesResult result = gson.fromJson(line, CitiesResult.class);
            }
        } catch (IOException e){
        }
        start = System.currentTimeMillis() - start;
        Events.postOnUIThread("" + start);
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
