package com.example.weatherapp;



import com.activeandroid.app.Application;
import com.example.weatherapp.utils.Background;
import com.google.common.eventbus.EventBus;

public class WeatherApplication extends Application {

    private static WeatherApplication sApp;
    private EventBus eventBus;
    private Background background;

    public WeatherApplication(){
        sApp = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        eventBus = new EventBus("weather");
        background = new Background(eventBus);
    }

    public EventBus getEventBus(){
        return eventBus;
    }

    public Background getBackground(){
        return background;
    }

    public static WeatherApplication get(){
        return sApp;
    }
}
