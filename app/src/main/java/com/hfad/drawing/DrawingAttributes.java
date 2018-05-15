package com.hfad.drawing;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Created by ShawnNewPC on 3/8/2018.
 */

public class DrawingAttributes {
    public static int thickness = 1;
    public static int color = Color.RED;
    public static Paint painter = new Paint(Paint.ANTI_ALIAS_FLAG);

    public static ArrayList<Line> lines = new ArrayList<Line>();
    public static ArrayList<CusShape> shapes = new ArrayList<CusShape>();
    public static char type = 'l';
    public static float lastTouchX;
    public static float lastTouchY;
    public static Point screenSize = new Point();

    public DrawingAttributes() {
        painter.setStrokeJoin(Paint.Join.ROUND);
        painter.setStrokeCap(Paint.Cap.ROUND);
        painter.setStyle(Paint.Style.STROKE);
    }

}
