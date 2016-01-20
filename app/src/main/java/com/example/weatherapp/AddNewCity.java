package com.example.weatherapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.weatherapp.database.City;
import com.example.weatherapp.database.CityWeather;
import com.example.weatherapp.utils.Events;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddNewCity extends EventActivity implements SearchView.OnQueryTextListener, AdapterView.OnItemClickListener {

    @Bind(R.id.search_view)
    protected SearchView mSearchView;

    @Bind(R.id.all_cities)
    protected ListView mListView;

    private ArrayAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_city);
        ButterKnife.bind(this);

        initAdapter();
        mListView.setAdapter(mAdapter);
        mListView.setTextFilterEnabled(true);
        mListView.setOnItemClickListener(this);
        setupSearchView();
    }

    private void setupSearchView(){
        mSearchView.setIconifiedByDefault(true);
        mSearchView.setQueryRefinementEnabled(true);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setOnQueryTextListener(this);
        mSearchView.setQueryHint("Search here");
    }

    private void initAdapter(){
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, City.getAllNames());
    }


    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            mListView.clearTextFilter();
        } else {
            mListView.setFilterText(newText);
        }
        return true;
    }

    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String cityName =(String) mAdapter.getItem(position);
        City city = City.getByName(cityName).get(0);
        new City(city.cityID, city.cityName, true);
        new CityWeather(city.cityID, city.cityName, "");
        finish();
    }
}
