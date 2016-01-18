package com.example.weatherapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.example.weatherapp.Adapters.MainAdapter;
import com.example.weatherapp.database.CityWeather;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends EventActivity {


    @Bind(R.id.choosen_city_list)
    protected ListView view;

    @Bind(R.id.toolbar)
    protected Toolbar toolbar;

    @Bind(R.id.add_new_city)
    protected FloatingActionButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        List<CityWeather> weathers = CityWeather.getNames();
        MainAdapter adapter = new MainAdapter(this, weathers);
        view.setAdapter(adapter);
    }

    @OnClick(R.id.add_new_city)
    public void addNewCity(){

    }




}
