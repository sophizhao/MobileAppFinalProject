package com.example.sparkjoy;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

public class DailyInfo {
    private Mood mood;
    private boolean journaled;
    private double water;
    private double sleep;
    private LocalDate date;
    private DayOfWeek DayOfWeek;

    public DailyInfo(Mood mood, boolean journaled, double water, double sleep, LocalDate date) {
        this.mood = mood;
        this.journaled = journaled;
        this.water = water;
        this.sleep = sleep;
        this.date = date;
    }

    public ArrayList<DailyInfo> weekly(LocalDate d){
        ArrayList<DailyInfo> weeklyData = new ArrayList<>();
        for(int i = 0; i < 7; i++){

        }
        return weeklyData;
    }

    public DayOfWeek getDayOfWeek (){
        return DayOfWeek;
    }

    public Mood getMood() {
        return mood;
    }

    public void setMood(Mood mood) {
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
