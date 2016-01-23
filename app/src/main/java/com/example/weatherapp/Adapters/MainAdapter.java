package com.example.weatherapp.Adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapp.AdditionalInformation;
import com.example.weatherapp.R;
import com.example.weatherapp.database.CityWeather;
import com.example.weatherapp.database.DayWeatherResult;

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
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.city_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mName.setText(list.get(position).name );
        DayWeatherResult temp = new DayWeatherResult(list.get(position).weather);
        if (temp.dayTemp != -1){
            holder.mTemp.setText("Temp: " + String.valueOf(temp.dayTemp));
        } else {
            holder.mTemp.setText("No information about temperature");
        }

        holder.mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weather = list.get(position).weather;
                if (weather.equals("")){
                    Toast.makeText(context, "No additional information about city. Check your connection and restart app", Toast.LENGTH_LONG).show();
                } else{
                    Intent intent = new Intent(context, AdditionalInformation.class);
                    intent.putExtra("weather", list.get(position).weather);
                    context.startActivity(intent);
                }

            }
        });
        return convertView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.city_name)
        public TextView mName;

        @Bind(R.id.city_temp)
        public TextView mTemp;

        @Bind(R.id.more_info)
        Button mButton;

        ViewHolder(View v){
            super(v);
            ButterKnife.bind(this, v);
        }

    }
}
