package com.example.sparkjoy;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

public class WaterLogActivity extends AppCompatActivity {

    EditText waterLog;
    TextView waterLogTV;
    final String TAG = "Sparky";
    ArrayList<DailyInfo> myList = MainActivity.firebaseHelper.getDailyInfos();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // get ArrayList of data from firebase
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_log);
        waterLog = findViewById(R.id.editTextWaterLog);
        waterLogTV = findViewById(R.id.waterLogActTV);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addWaterButtonClicked(View view){
        Toast.makeText(getApplicationContext(), "Ounces logged!", Toast.LENGTH_SHORT).show();
        double water = Double.parseDouble(waterLog.getText().toString());

// search through data to see if one exists
        //if data exists for today, set journal to true
        //if data doesn't, add new data

        if(myList.contains(new DailyInfo())){
            int ind = myList.indexOf(new DailyInfo()); //should only check date?!?!?!?!?!??!!
            myList.get(ind).setWater(water);
            MainActivity.firebaseHelper.editData(myList.get(ind));
            Log.d(TAG, "set ounces logged to " + water);
        } else {
            DailyInfo newDI = new DailyInfo();
            newDI.setWater(water);
            MainActivity.firebaseHelper.addData(newDI);
        }

        waterLog.setText("");
        waterLogTV.setText(""+water);
    }


}
