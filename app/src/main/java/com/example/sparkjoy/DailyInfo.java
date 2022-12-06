package com.example.sparkjoy;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class DailyInfo {
    private int mood;
    private String journaled;
    private double water;
    private double sleep;
    private long date;
    private DayOfWeek DayOfWeek;
    static ArrayList<DailyInfo> allData = new ArrayList<>();
    final String TAG = "Sparky";
    private String docID;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public DailyInfo() {
        this.mood = 0;
        this.journaled = "";
        this.water = 0.0;
        this.sleep = 0.0;
        Calendar cal = Calendar.getInstance();
        int year  = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int date  = cal.get(Calendar.DATE);
        cal.clear();
        cal.set(year, month, date);
        long todayMillis2 = cal.getTimeInMillis();
        this.date = todayMillis2;
        this.docID = "No docID yet";
        allData.add(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public DailyInfo(long date) {
        this.mood = 0;
        this.journaled = "";
        this.water = 0.0;
        this.sleep = 0.0;
        this.date = date;
        this.docID = "No docID yet";
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
            return this.date ==d.getDate();
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

    public String journal() {
        return journaled;
    }

    public void setJournaled(String journaled) {
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

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getDocID() {
        return docID;
    }

    public void setDocID(String docID) {
        this.docID = docID;
    }
}
