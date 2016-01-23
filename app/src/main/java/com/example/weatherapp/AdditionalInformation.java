package com.example.weatherapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.weatherapp.Adapters.AdditionalAdapter;
import com.google.common.eventbus.Subscribe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AdditionalInformation extends EventActivity {

    @Bind(R.id.more_info_list)
    protected ListView view;

    @Bind(R.id.toolbar)
    protected Toolbar toolbar;

    private AdditionalAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_information);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        String weather = intent.getStringExtra("weather");
        Log.d("Weather", weather);
        initAdapter(weather);
        view.setAdapter(mAdapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter = null;
    }

    private void initAdapter(String weather){
        JSONArray array = null;
        if (weather.equals("")){
            Toast.makeText(this, "No internet. Check connection", Toast.LENGTH_LONG).show();
        } else {
            try{
                JSONObject jsonObject = new JSONObject(weather);
                array = jsonObject.getJSONArray("list");
            } catch(JSONException e){
                Log.e("Error", "Can't create json array");
            }

            mAdapter = new AdditionalAdapter(array, this);
        }

    }
}
