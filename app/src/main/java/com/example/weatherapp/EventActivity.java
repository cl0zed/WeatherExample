package com.example.weatherapp;

import android.support.v7.app.AppCompatActivity;

import com.example.weatherapp.utils.Events;

public class EventActivity  extends AppCompatActivity {

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
}
