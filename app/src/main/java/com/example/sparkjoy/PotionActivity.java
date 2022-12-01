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
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;

@RequiresApi(api = Build.VERSION_CODES.O)
public class PotionActivity extends AppCompatActivity {
    long mms;
    private static final String TAG = "Sparkplug";
    ArrayList<DailyInfo> myList = MainActivity.firebaseHelper.getDailyInfos();
    double[] waterAndSleepData = findWaterandSleep();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_potion);
        GraphView graph = (GraphView) findViewById(R.id.graph);
        //mms = c.getTimeInMillis();

        LineGraphSeries<DataPoint> sleepSeries = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(1, waterAndSleepData[0]),
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

        LineGraphSeries<DataPoint> waterSeries = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(1, waterAndSleepData[0]),
                new DataPoint(2, waterAndSleepData[0]),
                new DataPoint(3, waterAndSleepData[0]),
                new DataPoint(4, waterAndSleepData[0]),
                new DataPoint(5, waterAndSleepData[0]),
                new DataPoint(6, waterAndSleepData[0]),
                new DataPoint(7, waterAndSleepData[0])
        });

        waterSeries.setColor(Color.rgb(200, 121, 255));
        waterSeries.setThickness(12);
        waterSeries.setDrawBackground(true);
        waterSeries.setBackgroundColor(Color.argb(80, 200, 121, 255));
        waterSeries.setDrawDataPoints(true);
        waterSeries.setDataPointsRadius(15);

        graph.addSeries(sleepSeries);
        graph.addSeries(waterSeries);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(1);
        graph.getViewport().setMaxX(7);
    }

    public double[] findWaterandSleep(){
        LocalDate date = Instant.ofEpochMilli(mms).atZone(ZoneId.systemDefault()).toLocalDate();

        for(int i = 0; i < myList.size(); i++){
            if(myList.get(i).equals(new DailyInfo(mms))){
                Log.d(TAG, "yes DailyInfo exists for date (water): " + myList.indexOf(new DailyInfo(mms)));
                double[] data = {myList.get(i).getWater(),myList.get(i).getSleep()};
                Log.d(TAG, "datalist is" + data.toString());
                return data;
            }
        }
        return new double[]{-1.0,-1.0};
    }
}