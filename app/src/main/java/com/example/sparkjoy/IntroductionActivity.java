package com.example.sparkjoy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class IntroductionActivity extends AppCompatActivity {

    private static final String TAG = "Denna";
    public ArrayList<String> textViewList = new ArrayList<>(5);
    public int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
    }

    public void cycleText(View view){
        textViewList.add("Oh…it’s you. Ugh.");
        textViewList.add("I suppose I should introduce myself. My name is Sparkplug and this is my app, Spark Joy. You seem sad today. Maybe start tracking your mood with the Mood Meter?");
        textViewList.add("Or, since the circles under your eyes are darker than usual, you might want to use the sleep tracker.");
        textViewList.add("You need more than an ounce of water per day–you know that, right? Perhaps the Water Log will put that into perspective...");
        textViewList.add("If you want to stay dehydrated and tired, I suggest you be productive too. Journal perhaps?");

        TextView textView = (TextView)findViewById(R.id.textView1);
        textView.setText(textViewList.get(counter));
        counter += 1;

        if (counter > 4){
            counter = 0;
        }
    }

    public void switchHome(View view) {
        Log.i(TAG, "user is switching to login screen");
        Intent intent = new Intent(IntroductionActivity.this, MainActivity.class);
        startActivity(intent);
        counter = 0;
    }



    public void hide(View view) {

        TextView txtView = (TextView)findViewById(R.id.textView1);

        //Toggle
        if (txtView.getVisibility() == View.VISIBLE)
            txtView.setVisibility(View.INVISIBLE);
        else
            txtView.setVisibility(View.VISIBLE);

        //If you want it only one time
        //txtView.setVisibility(View.VISIBLE);

    }
}