package com.example.sparkjoy;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Mood {
    private Date dateCreated;
    private int mood;
    /*
    1 - red
    2 - yellow
    3 - green
    4 - blue
     */
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    public Mood(Date dateCreated, int mood) {

        this.mood = mood;
    }

}
