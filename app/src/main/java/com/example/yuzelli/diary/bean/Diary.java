package com.example.yuzelli.diary.bean;

import java.io.Serializable;

/**
 * Created by 51644 on 2017/2/18.
 */

public class Diary implements Serializable {
    private String title;
    private String time ;
    private String contnent;
    private String weather;

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContnent() {
        return contnent;
    }

    public void setContnent(String contnent) {
        this.contnent = contnent;
    }
}
