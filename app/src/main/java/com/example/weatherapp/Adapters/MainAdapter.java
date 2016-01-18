package com.example.weatherapp.Adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.weatherapp.R;
import com.example.weatherapp.database.CityWeather;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainAdapter extends BaseAdapter {

    Context context;
    List<CityWeather> list;

    public MainAdapter(Context context, List<CityWeather> weathers){
        this.context = context;
        list = weathers;
    }

    @Override
    public int getCount() {
        Log.d("Count", "Count is: "+ list.size());
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        Log.d("Item", "Count is: "+ list.get(position));
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.city_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mName.setText(list.get(position).name);
        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return convertView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.city_name)
        public TextView mName;

        @Bind(R.id.more_info)
        Button mButton;

        ViewHolder(View v){
            super(v);
            ButterKnife.bind(this, v);
        }

    }
}
