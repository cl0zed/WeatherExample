package com.example.weatherapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.weatherapp.database.City;
import com.example.weatherapp.utils.FillCitiesTable;
import com.google.common.eventbus.Subscribe;

import java.util.List;

public class Launch extends EventActivity {

    private final String PREFERNCES_NAME = "WeatherAppPreferences";
    private final String JSON_PARSED = "JSONParsed";
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        preferences = this.getSharedPreferences(PREFERNCES_NAME, MODE_PRIVATE);
        /*SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(JSON_PARSED, false);
        editor.apply();*/

        if (!preferences.getBoolean(JSON_PARSED, false)){
            new FillCitiesTable(this).start();
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
        }
    }

    @Subscribe
    public void on(String result){
        List<City> cities = City.getByName("Moskva");
        int i = 0;
        for (City city:cities){
            ++i;
        }
        Toast.makeText(this, "Time is: " + result + "  :", Toast.LENGTH_LONG).show();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(JSON_PARSED, true);
        editor.apply();

        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);

        Log.d("Complete parsing", "Parsing is complete, Moskva count: " + i);
    }
}
