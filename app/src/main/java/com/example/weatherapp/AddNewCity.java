package com.example.weatherapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.weatherapp.database.City;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddNewCity extends EventActivity implements SearchView.OnQueryTextListener {

    @Bind(R.id.search_view)
    protected SearchView searchView;

    @Bind(R.id.all_cities)
    protected ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_add_new_city);

        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                City.getAllNames()));
        listView.setTextFilterEnabled(true);
        setupSearchView();
    }

    private void setupSearchView(){
        searchView.setIconifiedByDefault(true);
        searchView.setQueryRefinementEnabled(true);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("Search here");
    }

    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            listView.clearTextFilter();
        } else {
            listView.setFilterText(newText.toString());
        }
        return true;
    }

    public boolean onQueryTextSubmit(String query) {
        return false;
    }
}
