package com.example.weatherapp.database;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

@Table(name = "City")
public class City extends Model{

    @Column(name = "uid", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    public long cityID;

    @Column(name = "city_name")
    public String cityName;

    @Column(name = "is_choosen")
    public boolean cityChoosen;

    public City(){
        super();
    }

    public City(long id, String name, boolean isChoosen){
        super();
        cityID = id;
        cityName = name;
        cityChoosen = isChoosen;
        save();
    }

    public static List<String> getAllNames(){
        List<String> names = new ArrayList<>();
        List<City> cities = new Select().from(City.class).where("is_choosen = ? ", false).orderBy("city_name").execute();
        for (City city: cities){
            names.add(city.cityName);
        }
        return names;
    }

    public static List<City> getByName(String name){
        return new Select().from(City.class).where("city_name = ? ", name).execute();
    }

}
