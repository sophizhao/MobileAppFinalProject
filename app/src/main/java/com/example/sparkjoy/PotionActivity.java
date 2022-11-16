package com.example.sparkjoy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class PotionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_potion);
        GraphView graph = (GraphView) findViewById(R.id.graph);
        graph.addSeries(lineSeries);
    }

    LineGraphSeries<DataPoint> lineSeries = new LineGraphSeries<>(new DataPoint[] {
            new DataPoint(0, 1),
            new DataPoint(1, 5),
            new DataPoint(2, 3),
            new DataPoint(3, 2),
            new DataPoint(4, 6)
    });

    public LineGraphSeries<DataPoint> getLineSeries() {
        return lineSeries;
    }

    public void setLineSeries(LineGraphSeries<DataPoint> lineSeries) {
        this.lineSeries = lineSeries;
    }
}