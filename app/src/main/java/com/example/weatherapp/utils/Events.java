package com.example.weatherapp.utils;


import android.util.Log;

import com.example.weatherapp.WeatherApplication;

public class Events {

    public static void postOnUIThread(Object object){
        WeatherApplication.get().getBackground().postEventOnUIThread(object);
    }

    public static void post(Object object){
        WeatherApplication.get().getEventBus().post(object);
    }

    public static void register(Object object){
        WeatherApplication.get().getEventBus().register(object);
    }

    public static void unregister(Object object){
        try{
            WeatherApplication.get().getEventBus().unregister(object);
        } catch (Exception e){
            Log.d("Fail", "Cant unregister");
        }
    }
}
