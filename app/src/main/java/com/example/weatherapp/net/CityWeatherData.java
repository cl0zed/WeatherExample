package com.example.weatherapp.net;


import android.util.Log;


import com.example.weatherapp.database.CityWeather;
import com.example.weatherapp.utils.Events;
import com.google.gson.Gson;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class CityWeatherData extends Thread {

    private long cityID;
    private String cityName;
    private boolean adapterUpdate;

    @Override
    public void run() {
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/").build();
        WeatherAPI api = restAdapter.create(WeatherAPI.class);
        Call<ResponseBody> result = api.getWeather(cityID, 7, "44db6a862fba0b067b1930da0d769e98");
        try{
            String weatherResult = result.execute().body().string();
            new CityWeather(cityID, cityName, weatherResult);
        }catch (Exception e){
            Log.d("Exception", "result exception");
        }
        if (adapterUpdate){
            Events.postOnUIThread("refresh");
        }
    }

    @Override
    public synchronized void start() {
        super.start();
    }

    public CityWeatherData(long id, String name, boolean isUpdate){
        this.cityID = id;
        this.cityName = name;
        this.adapterUpdate = isUpdate;
    }
}

interface WeatherAPI{
    @GET("/data/2.5/forecast/daily")
    Call<ResponseBody> getWeather(@Query("id") long id, @Query("cnt") int count, @Query("appid") String apiID);
}
