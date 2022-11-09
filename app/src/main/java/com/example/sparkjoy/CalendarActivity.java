package com.example.sparkjoy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {
    TextView moodDisplay;
    long mms;
    final String TAG = "Sparky";

    private CalendarView calendar;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        moodDisplay = findViewById(R.id.moodTV);
        calendar = (CalendarView) findViewById(R.id.calendarView);


        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int day) {
                month++;
                Toast.makeText(getApplicationContext(), month + "/" + day +"/" + year, Toast.LENGTH_LONG).show();
                Calendar c = Calendar.getInstance();
                month--;
                c.set(year, month, day);
                mms = c.getTimeInMillis(); //this is what you want to use later
                Log.d(TAG, "" + mms);
                loadDate();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void loadDate(){

        switch(findMoodSelected()){
            case 0:
                moodDisplay.setText("No mood selected for today. Boring.");
            case 1:
                moodDisplay.setText("Mood: High energy, low happiness");
            case 2:
                moodDisplay.setText("Mood: High energy, high happiness");
            case 3:
                moodDisplay.setText("Mood: Low energy, low happiness");
            case 4:
                moodDisplay.setText("Mood: Low energy, High happiness");
        }

        Log.d(TAG, "Mood selected for day");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public int findMoodSelected(){
        //find selected date in milliseconds and convert to LocalDate
        LocalDate date = Instant.ofEpochMilli(mms).atZone(ZoneId.systemDefault()).toLocalDate();

        for (Mood currentMood : MoodMeterActivity.myMoods) {
            Log.d(TAG, date.toString() + " and " + currentMood.getDateCreated());
            if (currentMood.getDateCreated().equals(date)) {
                // Found matching mood
                Log.d(TAG, "Found a mood: " + date.toString() + " and " + currentMood.getDateCreated() + ". The mood is "+ currentMood.getMood());
                return currentMood.getMood();
            }
        }
        return 0;
    }

//    put this in mood class?
}