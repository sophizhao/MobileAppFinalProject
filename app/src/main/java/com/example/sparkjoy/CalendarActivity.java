package com.example.sparkjoy;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class CalendarActivity extends AppCompatActivity {

    CalendarView calendar = findViewById(R.id.calendarView);
    TextView moodDisplay = findViewById(R.id.moodTV);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                loadDate();
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
        LocalDate date = Instant.ofEpochMilli(calendar.getDate()).atZone(ZoneId.systemDefault()).toLocalDate();
        for (Mood currentMood : MoodMeterActivity.myMoods) {
            if (currentMood.getDateCreated().equals(date)) {
                // Found matching mood
                return currentMood;
            }
        }
        return null;
    }

    public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

    }
//    put this in mood class?
}