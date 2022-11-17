package com.example.sparkjoy;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;

public class MoodMeterActivity extends AppCompatActivity {
    ImageButton red, yellow, green, blue;
    final String TAG = "Sparky";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_meter);

        red = findViewById(R.id.redButton);
        yellow = findViewById(R.id.yellowButton);
        green = findViewById(R.id.greenButton);
        blue = findViewById(R.id.blueButton);
    }


    /**
     * take in param
     * check to see if mood has been selected already (you can only select one mood a day)
     * - do this by looking in firebase (see below)
     * if done: update mood to new selection
     * if not done: add new mood attached to today's date
     *

     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void moodSelected(View view) throws ParseException {
        int intMood = Integer.parseInt(view.getTag().toString());
        Toast.makeText(getApplicationContext(), "Mood selected!", Toast.LENGTH_SHORT).show();

        if(DailyInfo.allData.contains(new DailyInfo())){
            int ind = DailyInfo.allData.indexOf(new DailyInfo()); //should only check date?!?!?!?!?!??!!
            DailyInfo.allData.get(ind).setMood(intMood);
            Log.d(TAG, "set mood");
        } else {
            DailyInfo.allData.add(new DailyInfo());
            DailyInfo.allData.get(DailyInfo.allData.size()-1).setMood(intMood);
        }
//        boolean alreadyAdded = false;
//        for(int i = 0; i < myMoods.size(); i++){
//            if (myMoods.get(i).getDateCreated().isEqual(java.time.LocalDate.now())){ //if it is today
//                myMoods.get(i).setMood(intMood);
//                alreadyAdded = true;
//                System.out.println(myMoods.get(i));
//            }
//        }
//        if (!alreadyAdded){
//            Mood newMood = new Mood(intMood);
//            myMoods.add(newMood);
//            Log.d(TAG, "Mood set to " + newMood);
//            alreadyAdded = true;
//        }

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void moodSelected(View view, String mood) throws ParseException {

//        myMoods.clear();        // empties the AL so that it can get a fresh copy of data
//        db.collection("users").document(uid).collection("myMemoryList")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (DocumentSnapshot doc: task.getResult()) {
//                                Memory memory = doc.toObject(Memory.class);
//                                myMemories.add(memory);
//                            }
//
//                            Log.i(TAG, "Success reading data: "+ myMemories.toString());
//                            firestoreCallback.onCallback(myMemories);
//                        }
//                        else {
//                            Log.d(TAG, "Error getting documents: " + task.getException());
//                        }
//                    }
//                });
    }


}