package com.example.sparkjoy;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.google.firebase.firestore.FirebaseFirestore;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class PotionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_potion);
        GraphView graph = (GraphView) findViewById(R.id.graph);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6),
                new DataPoint(5, 6),
                new DataPoint(6, 5),
                new DataPoint(7, 3)
        });

        series.setColor(Color.rgb(255, 183, 255));
        series.setThickness(12);
        series.setDrawBackground(true);
        series.setBackgroundColor(Color.argb(80, 255, 183, 255));
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(15);

        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(1, 2),
                new DataPoint(2, 4),
                new DataPoint(3, 6),
                new DataPoint(4, 3),
                new DataPoint(5, 5),
                new DataPoint(6, 2),
                new DataPoint(7, 3)
        });

        series2.setColor(Color.rgb(200, 121, 255));
        series2.setThickness(12);
        series2.setDrawBackground(true);
        series2.setBackgroundColor(Color.argb(80, 200, 121, 255));
        series2.setDrawDataPoints(true);
        series2.setDataPointsRadius(15);

        graph.addSeries(series);
        graph.addSeries(series2);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(1);
        graph.getViewport().setMaxX(7);
    }

}