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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_potion);
        GraphView graph = (GraphView) findViewById(R.id.graph);
        Calendar cal = Calendar.getInstance();
        int year  = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int date  = cal.get(Calendar.DATE);
        cal.clear();
        cal.set(year, month, date);
        mms = cal.getTimeInMillis();
        double[] waterAndSleepData = findWaterandSleep();

        date = checkNewDay(date, month);
        month = checkNewMonth(date, month);
        cal.clear();
        cal.set(year, month, date);
        mms = cal.getTimeInMillis();
        double[] waterAndSleepData2 = findWaterandSleep();

        date = checkNewDay(date, month);
        month = checkNewMonth(date, month);
        cal.clear();
        cal.set(year, month, date);
        mms = cal.getTimeInMillis();
        double[] waterAndSleepData3 = findWaterandSleep();

        date = checkNewDay(date, month);
        month = checkNewMonth(date, month);
        cal.clear();
        cal.set(year, month, date);
        mms = cal.getTimeInMillis();
        double[] waterAndSleepData4 = findWaterandSleep();

        date = checkNewDay(date, month);
        month = checkNewMonth(date, month);
        cal.clear();
        cal.set(year, month, date);
        mms = cal.getTimeInMillis();
        double[] waterAndSleepData5 = findWaterandSleep();

        date = checkNewDay(date, month);
        month = checkNewMonth(date, month);
        cal.clear();
        cal.set(year, month, date);
        mms = cal.getTimeInMillis();
        double[] waterAndSleepData6 = findWaterandSleep();
        
        date = checkNewDay(date, month);
        month = checkNewMonth(date, month);
        cal.clear();
        cal.set(year, month, date);
        mms = cal.getTimeInMillis();
        double[] waterAndSleepData7 = findWaterandSleep();

        LineGraphSeries<DataPoint> sleepSeries = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(1, waterAndSleepData[1]),
                new DataPoint(2, waterAndSleepData2[1]),
                new DataPoint(3, waterAndSleepData3[1]),
                new DataPoint(4, waterAndSleepData4[1]),
                new DataPoint(5, waterAndSleepData5[1]),
                new DataPoint(6, waterAndSleepData6[1]),
                new DataPoint(7, waterAndSleepData7[1])
        });

        sleepSeries.setColor(Color.rgb(255, 183, 255));
        sleepSeries.setThickness(12);
        sleepSeries.setDrawBackground(true);
        sleepSeries.setBackgroundColor(Color.argb(80, 255, 183, 255));
        sleepSeries.setDrawDataPoints(true);
        sleepSeries.setDataPointsRadius(15);

        LineGraphSeries<DataPoint> waterSeries = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(1, waterAndSleepData[0]),
                new DataPoint(2, waterAndSleepData2[0]),
                new DataPoint(3, waterAndSleepData3[0]),
                new DataPoint(4, waterAndSleepData4[0]),
                new DataPoint(5, waterAndSleepData5[0]),
                new DataPoint(6, waterAndSleepData6[0]),
                new DataPoint(7, waterAndSleepData7[0])
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

    public int checkNewDay(int date, int month){
        if(date - 1 == 0 && (month == 2 || month == 4 || month == 6 || month == 8
        || month == 9 || month == 11 || month == 1)){
            return 31;
        }else if(date - 1 == 0 && (month == 5 || month == 7 || month == 10 || month == 12)){
            return 30;
        }else if(date - 1 == 0 && month == 3){
            return 28;
        }else{
            return date - 1;
        }
    }

    public int checkNewMonth(int date, int month){
        if (date - 1 == 0 && month != 1){
            return month - 1;
        }else if(date - 1 == 0 && month == 1){
            return 12;
        }
        return month;
    }
}