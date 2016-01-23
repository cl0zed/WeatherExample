package com.example.weatherapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.weatherapp.events.FilledDataBaseEvent;
import com.example.weatherapp.net.UpdateAllCities;
import com.example.weatherapp.utils.FillCitiesTable;
import com.google.common.eventbus.Subscribe;

public class Launch extends EventActivity {

    private final String JSON_PARSED = "JSONParsed";
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        final String PREFERENCES_NAME = "WeatherAppPreferences";
        preferences = this.getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);

        if (!preferences.getBoolean(JSON_PARSED, false)){
            new FillCitiesTable(this).start();
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
        }
    }

    @Subscribe
    public void on(FilledDataBaseEvent event){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(JSON_PARSED, true);
        editor.apply();

        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }
}
