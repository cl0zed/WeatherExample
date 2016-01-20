package com.example.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.Toast;

import com.example.weatherapp.Adapters.MainAdapter;
import com.example.weatherapp.database.CityWeather;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends EventActivity{


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
        setSupportActionBar(toolbar);

        initAdapter();
        view.setAdapter(mAdapter);
    }

    public void initAdapter(){
        List<CityWeather> weathers = CityWeather.getNames();
        mAdapter = new MainAdapter(this, weathers);
        mAdapter.notifyDataSetChanged();
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
        view.setAdapter(mAdapter);
    }

    @Override
    public void onBackPressed() {
        if (backPressed){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else{
            Toast.makeText(this, "Press one more time to exit", Toast.LENGTH_LONG).show();
            backPressed = true;
        }
    }
}
