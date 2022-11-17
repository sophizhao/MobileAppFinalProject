package com.example.sparkjoy;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

public class DailyInfo {
    private int mood;
    private boolean journaled;
    private double water;
    private double sleep;
    private LocalDate date;
    private DayOfWeek DayOfWeek;
    static ArrayList<DailyInfo> allData = new ArrayList<>();
    final String TAG = "Sparky";

    @RequiresApi(api = Build.VERSION_CODES.O)
    public DailyInfo() {
        this.mood = 0;
        this.journaled = false;
        this.water = 0.0;
        this.sleep = 0.0;
        this.date = java.time.LocalDate.now();
        allData.add(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public DailyInfo(LocalDate date) {
        this.mood = 0;
        this.journaled = false;
        this.water = 0.0;
        this.sleep = 0.0;
        this.date = date;
    }

//    public ArrayList<DailyInfo> weekly(LocalDate d){
////        ArrayList<DailyInfo> weeklyData = new ArrayList<>();
////        for(int i = 0; i < 7; i++){
////
////        }
////        return weeklyData;
////    }

    /* https://stackoverflow.com/a/46445252
    overrided equals() method should allow the .contains method in the Calendar activity and more to check
    if DailyInfo with the same date exists
     */
    public boolean equals(Object o){
        if(o instanceof DailyInfo){
            DailyInfo d = (DailyInfo) o;
            Log.d(TAG, "found a DailyInfo object by date");
            return this.date.equals(d.getDate());
        } else
            return false;
    }

    @Override
    public String toString() {
        return "DailyInfo{" +
                "mood=" + mood +
                ", journaled=" + journaled +
                ", water=" + water +
                ", sleep=" + sleep +
                ", date=" + date +
                '}';
    }

    public DayOfWeek getDayOfWeek (){
        return DayOfWeek;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public boolean isJournaled() {
        return journaled;
    }

    public void setJournaled(boolean journaled) {
        this.journaled = journaled;
    }

    public double getWater() {
        return water;
    }

    public void setWater(double water) {
        this.water = water;
    }

    public double getSleep() {
        return sleep;
    }

    public void setSleep(double sleep) {
        this.sleep = sleep;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
