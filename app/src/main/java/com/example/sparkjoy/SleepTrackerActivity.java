package com.example.sparkjoy;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SleepTrackerActivity extends AppCompatActivity {

    EditText sleepLog;
    final String TAG = "Sparky";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_tracker);
        sleepLog = findViewById(R.id.hoursSleptET);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addSleepButtonClicked(View view){
        Toast.makeText(getApplicationContext(), "Hours logged!", Toast.LENGTH_SHORT).show();
        double sleep = Double.parseDouble(sleepLog.getText().toString());

// search through data to see if one exists
        //if data exists for today, set journal to true
        //if data doesn't, add new data

        if(DailyInfo.allData.contains(new DailyInfo())){
            int ind = DailyInfo.allData.indexOf(new DailyInfo()); //should only check date?!?!?!?!?!??!!
            DailyInfo.allData.get(ind).setSleep(sleep);
            Log.d(TAG, "set hours logged to " + sleep);
        } else {
            DailyInfo.allData.add(new DailyInfo());
            DailyInfo.allData.get(DailyInfo.allData.size()-1).setSleep(sleep);
        }

        sleepLog.setText("");
    }

}