package com.example.weatherapp.net;


import com.example.weatherapp.database.CityWeather;
import com.example.weatherapp.utils.Events;

import java.util.List;

public class UpdateAllCities{

    public UpdateAllCities(){
        List<CityWeather> cities = CityWeather.getNames();
        for (CityWeather city: cities){
            new CityWeatherData(city.uid, city.name).start();
        }
        Events.postOnUIThread("refresh");
    }
}
