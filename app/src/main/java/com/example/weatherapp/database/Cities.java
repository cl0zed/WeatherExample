package com.example.weatherapp.database;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name = "Cities")
public class Cities extends Model{

    @Column(name = "uid", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    public long cityID;

    @Column(name = "city_name")
    public String cityName;

    @Column(name = "is_choosen")
    public boolean cityChoosen;

    public Cities(long id, String name, boolean isChoosen){
        super();
        cityID = id;
        cityName = name;
        cityChoosen = isChoosen;
        save();
    }

    public static List<Cities> getByName(String name){
        return new Select().from(Cities.class).where("city_name = ? ", name).execute();
    }

}
