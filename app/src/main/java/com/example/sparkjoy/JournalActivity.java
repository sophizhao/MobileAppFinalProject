package com.example.sparkjoy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class JournalActivity extends AppCompatActivity {

    EditText journal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);
        journal = findViewById(R.id.journalEntry);
    }

    public void addJournalButtonClicked(View view){
        Toast.makeText(getApplicationContext(), "Journal added!", Toast.LENGTH_SHORT).show();
        String journalEntry = journal.getText().toString();


        journal.setText("");
    }
}