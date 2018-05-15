package com.hfad.drawing;

import android.graphics.Color;

import java.util.ArrayList;

/**
 * Created by ShawnNewPC on 3/8/2018.
 */

// Line class
public class Line {
    public ArrayList<Float> xPoints = new ArrayList<Float>(); // X points
    public ArrayList<Float> yPoints = new ArrayList<Float>(); // Y points
    public int lineColor = Color.BLACK; // Line color
    public int thickness = 1; // Line thickness
    public char type; // Type
}
