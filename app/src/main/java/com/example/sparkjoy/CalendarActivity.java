package com.example.sparkjoy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class CalendarActivity extends AppCompatActivity {
    TextView moodDisplay;
    CalendarView calendar;
    long mms;
    final String TAG = "Sparky";


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        moodDisplay = findViewById(R.id.moodTV);
        calendar = (CalendarView) findViewById(R.id.calendarView);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
//        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
//                mms = calendar.getDate();
//                Log.d(TAG, "Date Changed (mms): " + mms);
//                loadDate();
//            }
//        });

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                mms = calendar.getDate();
                Log.d(TAG, "onSelectedDayChange: mm/dd/yyyy: " + mms);
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void loadDate(){
        switch(findMoodSelected().getMood()){
            case 1:
                moodDisplay.setText("Mood: High energy, low happiness");
            case 2:
                moodDisplay.setText("Mood: High energy, high happiness");
            case 3:
                moodDisplay.setText("Mood: Low energy, low happiness");
            case 4:
                moodDisplay.setText("Mood: Low energy, High happiness");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Mood findMoodSelected(){
        //find selected date in milliseconds and convert to LocalDate
        LocalDate date = Instant.ofEpochMilli(mms).atZone(ZoneId.systemDefault()).toLocalDate();
        for (Mood currentMood : MoodMeterActivity.myMoods) {
            if (currentMood.getDateCreated().equals(date)) {
                // Found matching mood
                return currentMood;
            }
        }
        return null;
    }

//    put this in mood class?
}