package com.example.weatherapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.activeandroid.util.SQLiteUtils;
import com.example.weatherapp.database.Cities;
import com.example.weatherapp.utils.Events;
import com.example.weatherapp.utils.FillCitiesTable;
import com.google.common.eventbus.Subscribe;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private final String PREFERNCES_NAME = "WeatherAppPreferences";
    private final String JSON_PARSED = "JSONParsed";
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = this.getSharedPreferences(PREFERNCES_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(JSON_PARSED, false);
        editor.apply();

        if (!preferences.getBoolean(JSON_PARSED, false)){
            new FillCitiesTable(this).start();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Events.register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Events.unregister(this);
    }

    @Subscribe
    public void on(String result){
        List<Cities> cities = Cities.getByName("Moskva");
        int i = 0;
        for (Cities city:cities){
            ++i;
        }
        Toast.makeText(this, "Time is: " + result + "  :", Toast.LENGTH_LONG).show();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(JSON_PARSED, true);
        editor.apply();
    }
}
