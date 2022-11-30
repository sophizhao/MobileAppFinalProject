package com.example.sparkjoy;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;

public class PotionActivity extends AppCompatActivity {
    long mms;
    private static final String TAG = "Sparky";
    ArrayList<DailyInfo> myList = MainActivity.firebaseHelper.getDailyInfos();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        assert currentFirebaseUser != null;
        DocumentReference dataRef = db.collection("users").document(currentFirebaseUser.getUid());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_potion);
        GraphView graph = (GraphView) findViewById(R.id.graph);

        LineGraphSeries<DataPoint> sleepSeries = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(1, 1),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6),
                new DataPoint(5, 6),
                new DataPoint(6, 5),
                new DataPoint(7, 3)
        });

        sleepSeries.setColor(Color.rgb(255, 183, 255));
        sleepSeries.setThickness(12);
        sleepSeries.setDrawBackground(true);
        sleepSeries.setBackgroundColor(Color.argb(80, 255, 183, 255));
        sleepSeries.setDrawDataPoints(true);
        sleepSeries.setDataPointsRadius(15);

//        LineGraphSeries<DataPoint> waterSeries = new LineGraphSeries<>(new DataPoint[] {
//                new DataPoint(1, myList.get((int) findWater())),
//                new DataPoint(2, myList.get(findWater())),
//                new DataPoint(3, myList.get(findWater())),
//                new DataPoint(4, myList.get(findWater())),
//                new DataPoint(5, myList.get(findWater())),
//                new DataPoint(6, myList.get(findWater())),
//                new DataPoint(7, myList.get(findWater()))
//        });

//        waterSeries.setColor(Color.rgb(200, 121, 255));
//        waterSeries.setThickness(12);
//        waterSeries.setDrawBackground(true);
//        waterSeries.setBackgroundColor(Color.argb(80, 200, 121, 255));
//        waterSeries.setDrawDataPoints(true);
//        waterSeries.setDataPointsRadius(15);
//
//        graph.addSeries(sleepSeries);
//        graph.addSeries(waterSeries);
//        graph.getViewport().setXAxisBoundsManual(true);
//        graph.getViewport().setMinX(1);
//        graph.getViewport().setMaxX(7);
    }


    public void onSelectedDayChange(CalendarView calendarView, int year, int month, int day) {
        month++;
//                Toast.makeText(getApplicationContext(), month + "/" + day +"/" + year, Toast.LENGTH_LONG).show();
        displayDate(year, month, day);
        Calendar c = Calendar.getInstance();
        month--;
        c.clear();
        c.set(year, month, day);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        mms = c.getTimeInMillis(); //this is what you want to use later
        Log.d(TAG, "" + mms);
    }

    public void displayDate(int year, int month, int day){
        String monthString = new DateFormatSymbols().getMonths()[month-1];
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public double findSleep() {

        for (int i = 0; i < myList.size(); i++) {
            if (myList.get(i).equals(new DailyInfo(mms))) {
                Log.d(TAG, "yes DailyInfo exists for date (journal): " + myList.indexOf(new DailyInfo(mms)));

                return myList.get(i).getSleep();
            }
        }
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public double findWater() {

        for (int i = 0; i < myList.size(); i++) {
            if (myList.get(i).equals(new DailyInfo(mms))) {
                Log.d(TAG, "yes DailyInfo exists for date (journal): " + myList.indexOf(new DailyInfo(mms)));

                return myList.get(i).getWater();
            }
        }
        return 0;
    }
}