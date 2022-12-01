package com.example.sparkjoy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class IntroductionActivity extends AppCompatActivity {

    public ArrayList<String> textViewList = new ArrayList<>(5);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
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