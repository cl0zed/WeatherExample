package com.example.weatherapp.Adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.weatherapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AdditionalAdapter extends BaseAdapter {

    JSONArray weather;
    Context context;

    public AdditionalAdapter(JSONArray weather, Context context){
        this.weather = weather;
        this.context = context;
    }

    @Override
    public int getCount() {
        return weather.length();
    }

    @Override
    public Object getItem(int position) {
        try{
            return weather.getJSONObject(position);
        } catch (JSONException e){
            Log.e("Error", "Can't return json object");
        }
        return null;
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
            convertView = inflater.inflate(R.layout.weather_info_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        try{
            fillHolder(holder, weather.getJSONObject(position));
        } catch (JSONException e){
            Log.e("Error", "Fill holder error");
        }

        return convertView;
    }

    private void fillHolder(ViewHolder holder, JSONObject object){

        try{
            long seconds = object.getLong("dt") * 1000;

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(seconds);

            String date = "" + calendar.get(Calendar.DAY_OF_MONTH) + "."
                        + (calendar.get(Calendar.MONTH) + 1) + "."
                        + calendar.get(Calendar.YEAR);


            holder.currentDate.setText("Date: " + date);

            JSONObject jsonTemp = object.getJSONObject("temp");
            holder.dayTemp.setText("Daily temp: " + String.valueOf(jsonTemp.getDouble("day")));
            holder.nightTemp.setText("Night temp: " + String.valueOf(jsonTemp.getDouble("night")));
            holder.morningTemp.setText("Morning temp: " + String.valueOf(jsonTemp.getDouble("morn")));

            holder.dayPressure.setText("Pressure: " + String.valueOf(object.getDouble("pressure")));
            holder.dayHumidity.setText("Humidity: " +String.valueOf(object.getInt("humidity")));

            JSONArray jsonDesc = object.getJSONArray("weather");
            JSONObject description = jsonDesc.getJSONObject(0);
            holder.weatherDescription.setText("Description: " + description.getString("description"));

        }catch (JSONException e){
            Log.e("Error", "Can't parse weather");
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.date_text)
        public TextView currentDate;

        @Bind(R.id.day_temp_text)
        public TextView dayTemp;

        @Bind(R.id.night_temp_text)
        public TextView nightTemp;

        @Bind(R.id.morning_temp_text)
        public TextView morningTemp;

        @Bind(R.id.pressure_text)
        public TextView dayPressure;

        @Bind(R.id.humidity_text)
        public TextView dayHumidity;

        @Bind(R.id.weather_description)
        public TextView weatherDescription;

        ViewHolder(View v){
            super(v);
            ButterKnife.bind(this, v);
        }
    }
}
