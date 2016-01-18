package com.example.weatherapp.Adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.weatherapp.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AllCitiesAdapter extends BaseAdapter implements Filterable {

    Context context;
    List<String> list;

    public AllCitiesAdapter(Context context, List<String> weathers){
        this.context = context;
        list = weathers;
    }

    @Override
    public int getCount() {
        Log.d("Count", "Count is: " + list.size());
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        Log.d("Item", "Count is: "+ list.get(position));
        return list.get(position);
    }

    @Override
    public Filter getFilter() {
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
            convertView = inflater.inflate(R.layout.city_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mName.setText(list.get(position));
        return convertView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.city_name)
        public TextView mName;


        ViewHolder(View v){
            super(v);
            ButterKnife.bind(this, v);
        }

    }
}
