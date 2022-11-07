package com.example.sparkjoy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SelectActionActivity extends AppCompatActivity {
    public final String TAG = "Sparky";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_action);
    }

    public void switchMoodMeter(View view) {
        Log.i(TAG, "user is switching to mood meter");
        Intent intent = new Intent(SelectActionActivity.this, MoodMeterActivity.class);
        startActivity(intent);
    }

    public void switchCalendar(View view) {
        Log.i(TAG, "user is switching to calendar");
        Intent intent = new Intent(SelectActionActivity.this, CalendarActivity.class);
        startActivity(intent);
    }

    public void switchWaterLog(View view) {
        Log.i(TAG, "user is switching to water log");
        Intent intent = new Intent(SelectActionActivity.this, WaterLogActivity.class);
        startActivity(intent);
    }

    public void switchSleepTracker(View view) {
        Log.i(TAG, "user is switching to sleep tracker");
        Intent intent = new Intent(SelectActionActivity.this, SleepTrackerActivity.class);
        startActivity(intent);
    }

    public void switchJournal(View view) {
        Log.i(TAG, "user is switching to journal");
        Intent intent = new Intent(SelectActionActivity.this, JournalActivity.class);
        startActivity(intent);
    }


}