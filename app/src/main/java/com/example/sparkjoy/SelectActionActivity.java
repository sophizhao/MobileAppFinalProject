package com.example.sparkjoy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class SelectActionActivity extends AppCompatActivity {
    public final String TAG = "Sparky";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_action);
    }

    public void logOutClicked(View view) {
        MainActivity.firebaseHelper.logOutUser();
        Log.i(TAG, "user logged out");
        Intent intent = new Intent(SelectActionActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void switchMoodMeter(View view) {
        Log.i(TAG, "user is switching to mood meter");
        Intent intent = new Intent(SelectActionActivity.this, MoodMeterActivity.class);
        startActivity(intent);
    }

    public void switchCalendar(View view) {
        Log.i(TAG, "user is switching to calendar");
        Intent intent = new Intent(SelectActionActivity.this, CalendarActivity.class);
        startActivity(intent);
    }

    public void switchWaterLog(View view) {
        Log.i(TAG, "user is switching to water log");
        Intent intent = new Intent(SelectActionActivity.this, WaterLogActivity.class);
        startActivity(intent);
    }

    public void switchSleepTracker(View view) {
        Log.i(TAG, "user is switching to sleep tracker");
        Intent intent = new Intent(SelectActionActivity.this, SleepTrackerActivity.class);
        startActivity(intent);
    }

    public void switchJournal(View view) {
        Log.i(TAG, "user is switching to journal");
        Intent intent = new Intent(SelectActionActivity.this, JournalActivity.class);
        startActivity(intent);
    }

    public void switchPotion(View view) {
        Log.i(TAG, "user is switching to potion");
        Intent intent = new Intent(SelectActionActivity.this, PotionActivity.class);
        startActivity(intent);
    }

}