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

import java.text.DateFormatSymbols;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {
    TextView moodDisplay, dateDisplay, journaledDisplay, waterDisplay, sleepDisplay;
    long mms;
    final String TAG = "Sparky";
    ArrayList<DailyInfo> myList = MainActivity.firebaseHelper.getDailyInfos();


    private CalendarView calendar;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        moodDisplay = findViewById(R.id.moodTV);
        dateDisplay = findViewById(R.id.dateTV);
        journaledDisplay = findViewById(R.id.journaledTV);
        waterDisplay = findViewById(R.id.waterTV);
        sleepDisplay = findViewById(R.id.sleepTV);
        calendar = (CalendarView) findViewById(R.id.calendarView);

        Log.d(TAG, "" + myList);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int day) {
                month++;
//                Toast.makeText(getApplicationContext(), month + "/" + day +"/" + year, Toast.LENGTH_LONG).show();
                displayDate(year, month, day);
                Calendar c = Calendar.getInstance();
                month--;
                c.clear();
                c.set(year, month, day);
                c.set(Calendar.HOUR_OF_DAY, 0);
                c.set(Calendar.MINUTE, 0);
                c.set(Calendar.SECOND, 0);
                c.set(Calendar.MILLISECOND, 0);
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
                break;
            case 1:
                moodDisplay.setText("Mood: High energy, low happiness");
                break;
            case 2:
                moodDisplay.setText("Mood: High energy, high happiness");
                break;
            case 3:
                moodDisplay.setText("Mood: Low energy, low happiness");
                break;
            case 4:
                moodDisplay.setText("Mood: Low energy, High happiness");
                break;
        }

        if(!checkIfJournaled().equals("")) {
            journaledDisplay.setText("Journal:\n" + checkIfJournaled());
        } else {
            journaledDisplay.setText("Seems like you were too lazy to journal...");
        }

        double[] waterAndSleepData = findWaterandSleep();
        if(waterAndSleepData[0] >= 120){
            waterDisplay.setText("You drank " + waterAndSleepData[0] + " oz of water, you sponge.");
        } else if(waterAndSleepData[0] >= 80){
            waterDisplay.setText("You drank " + waterAndSleepData[0] + " oz of water. So close... yet so far.");
        } else if(waterAndSleepData[0] >= 40){
            waterDisplay.setText("You drank " + waterAndSleepData[0] + " oz of water. Your kidneys must be happy with you.");
        } else if(waterAndSleepData[0] > 0){
            waterDisplay.setText("You drank " + waterAndSleepData[0] + " oz of water. Are you okay?");
        }
        else {
            waterDisplay.setText("You didn't log any water...");
        }

        if(waterAndSleepData[1] >= 12){
            sleepDisplay.setText("You slept " + waterAndSleepData[1] + " hours. More than the average horse.");
        } else if(waterAndSleepData[1] >= 8){
            sleepDisplay.setText("You slept " + waterAndSleepData[1] + " hours. Perfectly balanced, as all things should be.");
        } else if(waterAndSleepData[1] >= 4){
            sleepDisplay.setText("You slept " + waterAndSleepData[1] + " hours. So, you're bad at lying down and not doing anything. What happens when you have to do things?");
        } else if(waterAndSleepData[1] > 0){
            sleepDisplay.setText("You slept " + waterAndSleepData[1] + " hours. Sure... let's call it \"sleeping.\"");
        }
        else {
            sleepDisplay.setText("You didn't log any sleep...");
        }

        Log.d(TAG, "Day is loaded");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public int findMoodSelected(){

        for(int i = 0; i < myList.size(); i++){
            if(myList.get(i).equals(new DailyInfo(mms))){
                Log.d(TAG, "yes DailyInfo exists for date (journal): " + myList.indexOf(new DailyInfo(mms)));

                return myList.get(i).getMood();
            }
        }
        return 0;

//        for (Mood currentMood : MoodMeterActivity.myMoods) {
//            Log.d(TAG, date.toString() + " and " + currentMood.getDateCreated());
//            if (currentMood.getDateCreated().equals(date)) {
//                // Found matching mood
//                Log.d(TAG, "Found a mood: " + date.toString() + " and " + currentMood.getDateCreated() + ". The mood is "+ currentMood.getMood());
//                return currentMood.getMood();
//            }
//        }
//        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String checkIfJournaled(){
        LocalDate date = Instant.ofEpochMilli(mms).atZone(ZoneId.systemDefault()).toLocalDate();

//        if(DailyInfo.allData.contains(new DailyInfo())){
            for(int i = 0; i < myList.size(); i++){
                if(myList.get(i).equals(new DailyInfo(mms))){
                    Log.d(TAG, "yes DailyInfo exists for date (journal): " + myList.indexOf(new DailyInfo(mms)));

                    return myList.get(i).isJournaled();
                }
            }
//        }
        return "";
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public double[] findWaterandSleep(){
        LocalDate date = Instant.ofEpochMilli(mms).atZone(ZoneId.systemDefault()).toLocalDate();

//        if(DailyInfo.allData.contains(new DailyInfo())){
        for(int i = 0; i < myList.size(); i++){
            if(myList.get(i).equals(new DailyInfo(mms))){
                Log.d(TAG, "yes DailyInfo exists for date (water): " + myList.indexOf(new DailyInfo(mms)));
                double[] data = {myList.get(i).getWater(),myList.get(i).getSleep()};
                return data;
            }
        }
//        }
        double[] noData ={-1.0,-1.0};
        return noData;
    }


    public void displayDate(int year, int month, int day){
        String monthString = new DateFormatSymbols().getMonths()[month-1];
        dateDisplay.setText(monthString + " " + day + ", " + year);
    }
//    put this in mood class?
}