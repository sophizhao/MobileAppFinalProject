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

import java.time.LocalDate;
import java.util.ArrayList;

public class JournalActivity extends AppCompatActivity {

    EditText journal;
    TextView prompt;
    final String TAG = "Sparky";
    ArrayList<DailyInfo> myList = MainActivity.firebaseHelper.getDailyInfos();
    String[] prompts = {"What do you need to get off your chest?",
            "What is the latest gossip that you want to talk about?",
            "What's one thing that you can't tell anyone, even though you want to?",
            "Life is what you make of it. Thoughts?",
            "Biggest pet peeve?",
            "What annoyed you today?",
            "How is your witchy era going?",
            "What do you care the least about?",
            "Most unhinged thing you saw today?",
            "Tell the story of an interesting event in a boring place.",
            "Thoughts on Britain?",
            "Worst purchase you made today?",
            "Who do you hate the most (respectfully)?",
            "What happened today that you would never let happen tomorrow?",
            "Worst decision you made today?"
    };

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);
        journal = findViewById(R.id.journalEntry);
        prompt = findViewById(R.id.promptTV);
        generatePrompt();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void generatePrompt(){
        int index = (int) Math.floor(Math.random() * prompts.length);
        prompt.setText(prompts[index]);


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addJournalButtonClicked(View view){
        Toast.makeText(getApplicationContext(), "Journal added!", Toast.LENGTH_SHORT).show();
        String journalEntry = journal.getText().toString();
        Log.d(TAG, journalEntry);

// search through data to see if one exists
        //if data exists for today, set journal to true
        //if data doesn't, add new data
        if(myList.contains(new DailyInfo())){
            int ind = myList.indexOf(new DailyInfo()); //should only check date?!?!?!?!?!??!!
            myList.get(ind).setJournaled(journalEntry);
            MainActivity.firebaseHelper.editData(myList.get(ind));
            Log.d(TAG, "set journal logged");
        } else {
            DailyInfo newDI = new DailyInfo();
            newDI.setJournaled(journalEntry);
            MainActivity.firebaseHelper.addData(newDI);
        }


        journal.setText("");
    }

}