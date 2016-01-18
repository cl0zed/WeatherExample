package com.example.weatherapp.database;

import android.database.Cursor;

import com.activeandroid.Cache;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "CityWeather")
public class CityWeather extends Model {

    @Column(name = "uid", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    public long uid;

    @Column(name = "name")
    public String name;

    @Column(name = "weather")
    public String weather;

    public CityWeather(){
        super();
    }

    public static List<CityWeather> getNames(){
        return new Select().from(CityWeather.class).execute();
    }
    public CityWeather(long id, String name, String weather){
        super();
        this.uid = id;
        this.name = name;
        this.weather = weather;
        save();
    }
}
