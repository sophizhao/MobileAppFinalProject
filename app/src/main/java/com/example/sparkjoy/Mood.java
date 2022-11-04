package com.example.sparkjoy;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.time.LocalDate;



public class Mood {
    public LocalDate dateCreated;
    private int mood;
    /*
    1 - red
    2 - yellow
    3 - green
    4 - blue
     */


    @RequiresApi(api = Build.VERSION_CODES.O)
    public Mood(int mood) throws ParseException {
        this.dateCreated = java.time.LocalDate.now();
        System.out.println(dateCreated);
        this.mood = mood;
    }

    public boolean equals(Object o){
        if(o instanceof Mood){
            Mood p = (Mood) o;
            return this.dateCreated.equals(p.getDateCreated());
        } else
            return false;
    }

    @Override
    public String toString() {
        return "Mood{" +
                "dateCreated=" + dateCreated +
                ", mood=" + mood +
                '}';
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }
}
