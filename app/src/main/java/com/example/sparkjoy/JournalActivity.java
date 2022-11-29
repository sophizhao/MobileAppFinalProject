package com.example.sparkjoy;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;

public class JournalActivity extends AppCompatActivity {

    EditText journal;
    final String TAG = "Sparky";
    ArrayList<DailyInfo> myList = MainActivity.firebaseHelper.getDailyInfos();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);
        journal = findViewById(R.id.journalEntry);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addJournalButtonClicked(View view){
        Toast.makeText(getApplicationContext(), "Journal added!", Toast.LENGTH_SHORT).show();
        String journalEntry = journal.getText().toString();

// search through data to see if one exists
        //if data exists for today, set journal to true
        //if data doesn't, add new data
        if(myList.contains(new DailyInfo())){
            int ind = myList.indexOf(new DailyInfo()); //should only check date?!?!?!?!?!??!!
            myList.get(ind).setJournaled(true);
            MainActivity.firebaseHelper.editData(myList.get(ind));
            Log.d(TAG, "set journal logged to " + true);
        } else {
            DailyInfo newDI = new DailyInfo();
            newDI.setJournaled(true);
            MainActivity.firebaseHelper.addData(newDI);
        }


        journal.setText("");
    }

}