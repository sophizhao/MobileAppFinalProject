package com.example.sparkjoy;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SleepTrackerActivity extends AppCompatActivity {

    EditText sleepLog;
    TextView sleepLogTV;
    final String TAG = "Sparky";
    ArrayList<DailyInfo> myList = MainActivity.firebaseHelper.getDailyInfos();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_tracker);
        sleepLog = findViewById(R.id.hoursSleptET);
        sleepLogTV = findViewById(R.id.sleepLogActTV);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addSleepButtonClicked(View view){

        try {
            double sleep = Double.parseDouble(sleepLog.getText().toString());
            Toast.makeText(getApplicationContext(), "Hours logged!", Toast.LENGTH_SHORT).show();

// search through data to see if one exists
            //if data exists for today, set journal to true
            //if data doesn't, add new data

            if(myList.contains(new DailyInfo())){
                int ind = myList.indexOf(new DailyInfo()); //should only check date?!?!?!?!?!??!!
                myList.get(ind).setSleep(sleep);
                MainActivity.firebaseHelper.editData(myList.get(ind));
                Log.d(TAG, "set ounces logged to " + sleep);
            } else {
                DailyInfo newDI = new DailyInfo();
                newDI.setSleep(sleep);
                MainActivity.firebaseHelper.addData(newDI);
            }
            sleepLog.setText("");
            sleepLogTV.setText(""+sleep);

            //Keyboard close on button click below from https://stackoverflow.com/a/27228592
            try {
                InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            } catch (Exception e) {
                // TODO: handle exception
            }
        } catch (Exception e){
            sleepLogTV.setText("Input numbers only, you fool...");
        }


    }

}