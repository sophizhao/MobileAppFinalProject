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

public class JournalActivity extends AppCompatActivity {

    EditText journal;
    final String TAG = "Sparky";

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
        //if data doesn't, idk just pass

        if(DailyInfo.allData.contains(new DailyInfo())){
            int ind = DailyInfo.allData.indexOf(new DailyInfo()); //should only check date?!?!?!?!?!??!!
            DailyInfo.allData.get(ind).setJournaled(true);
            Log.d(TAG, "set journal to true");
        }

        journal.setText("");
    }

}