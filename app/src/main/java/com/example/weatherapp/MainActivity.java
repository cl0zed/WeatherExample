package com.example.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.weatherapp.Adapters.MainAdapter;
import com.example.weatherapp.database.CityWeather;
import com.example.weatherapp.net.UpdateAllCities;
import com.google.common.eventbus.Subscribe;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends EventActivity implements AdapterView.OnItemClickListener{


    private boolean backPressed;

    @Bind(R.id.choosen_city_list)
    protected ListView view;

    @Bind(R.id.toolbar)
    protected Toolbar toolbar;

    MainAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        new UpdateAllCities();
        setSupportActionBar(toolbar);
        initAdapter();

    }

    public void initAdapter(){
        List<CityWeather> weathers = CityWeather.getNames();
        mAdapter = new MainAdapter(this, weathers);
        view.setOnItemClickListener(this);
        view.setAdapter(mAdapter);
    }

    @OnClick(R.id.add_new_city)
    public void addNewCity(){
        Intent intent = new Intent(this, AddNewCity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        backPressed = false;
        initAdapter();
    }

    @Override
    public void onBackPressed() {
        if (backPressed){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else{
            Toast.makeText(this, this.getString(R.string.back_button_exit_app), Toast.LENGTH_LONG).show();
            backPressed = true;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CityWeather weather = (CityWeather) mAdapter.getItem(position);
        if (weather.weather.equals("")){
            Toast.makeText(this, this.getString(R.string.no_connection), Toast.LENGTH_LONG).show();
        } else{
            Intent intent = new Intent(this, AdditionalInformation.class);
            intent.putExtra("weather", weather.weather);
            startActivity(intent);
        }
    }

    @Subscribe
    public void on(String result){
        initAdapter();
    }
}
