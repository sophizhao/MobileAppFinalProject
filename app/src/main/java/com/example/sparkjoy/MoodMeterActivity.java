package com.example.sparkjoy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

public class MoodMeterActivity extends AppCompatActivity {
    ImageButton red, yellow, green, blue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_meter);

        red = findViewById(R.id.redButton);
        yellow = findViewById(R.id.yellowButton);
        green = findViewById(R.id.greenButton);
        blue = findViewById(R.id.blueButton);
    }

    public void moodSelected(int mood){
        /**
         * take in param
         * check to see if mood has been selected already (you can only select one mood a day)
         * - do this by looking in firebase (see below)
         * if done: update mood to new selection
         * if not done: add new mood attached to today's date
         *
         * myMemories.clear();        // empties the AL so that it can get a fresh copy of data
         *         db.collection("users").document(uid).collection("myMemoryList")
         *                 .get()
         *                 .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
         *                     @Override
         *                     public void onComplete(@NonNull Task<QuerySnapshot> task) {
         *                         if (task.isSuccessful()) {
         *                             for (DocumentSnapshot doc: task.getResult()) {
         *                                 Memory memory = doc.toObject(Memory.class);
         *                                 myMemories.add(memory);
         *                             }
         *
         *                             Log.i(TAG, "Success reading data: "+ myMemories.toString());
         *                             firestoreCallback.onCallback(myMemories);
         *                         }
         *                         else {
         *                             Log.d(TAG, "Error getting documents: " + task.getException());
         *                         }
         *                     }
         *                 });
         */
    }


}