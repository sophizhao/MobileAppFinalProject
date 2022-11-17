package com.example.sparkjoy;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

public class WaterLogActivity extends AppCompatActivity {

    EditText waterLog;
    final String TAG = "Sparky";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_log);
        waterLog = findViewById(R.id.editTextWaterLog);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addWaterButtonClicked(View view){
        Toast.makeText(getApplicationContext(), "Ounces logged!", Toast.LENGTH_SHORT).show();
        double water = Double.parseDouble(waterLog.getText().toString());

// search through data to see if one exists
        //if data exists for today, set journal to true
        //if data doesn't, add new data

        if(DailyInfo.allData.contains(new DailyInfo())){
            int ind = DailyInfo.allData.indexOf(new DailyInfo()); //should only check date?!?!?!?!?!??!!
            DailyInfo.allData.get(ind).setJournaled(true);
            Log.d(TAG, "set ounces logged to true");
        } else {
            DailyInfo.allData.add(new DailyInfo());
            DailyInfo.allData.get(DailyInfo.allData.size()-1).setWater(water);
        }

        waterLog.setText("");
    }


}
