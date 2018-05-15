package com.hfad.drawing;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Created by ShawnNewPC on 3/8/2018.
 */

// All currently used Drawing Attributes
public class DrawingAttributes {
    public static int thickness = 1; // Current color
    public static int color = Color.RED; // Current thickness
    public static Paint painter = new Paint(Paint.ANTI_ALIAS_FLAG); // Anti-aliasing

    public static ArrayList<Line> lines = new ArrayList<Line>(); // List of currently drawn lines
    public static ArrayList<CusShape> shapes = new ArrayList<CusShape>(); // List of currently drawn shapes
    public static char type = 'l'; // Currently used type
    public static float lastTouchX; // Last touch X
    public static float lastTouchY; // Last touch Y
    public static float originX; // Origin of currently drawn shape X
    public static float originY; // Origin of currently drawn shape Y
    public static Point screenSize = new Point(); // Size of screen

    // Ctor
    public DrawingAttributes() {
        painter.setStrokeJoin(Paint.Join.ROUND);
        painter.setStrokeCap(Paint.Cap.ROUND);
        painter.setStyle(Paint.Style.STROKE);
    }

}
