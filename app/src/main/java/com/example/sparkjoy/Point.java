package com.example.sparkjoy;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Point {
    int xValue, yValue;


    public Point() {
    }

    public Point(int xValue, int yValue) {
        this.xValue = xValue;
        this.yValue = yValue;
    }

    public int getxValue() {
        return xValue;
    }

    public void setxValue(int xValue) {
        this.xValue = xValue;
    }

    public int getyValue() {
        return yValue;
    }

    public void setyValue(int yValue) {
        this.yValue = yValue;
    }
}
