package com.hfad.drawing;

import android.graphics.Color;

/**
 * Created by ShawnNewPC on 3/10/2018.
 */

// Custom shape
public class CusShape {

    // Left, Right, Top, and Bottom of shape
    public float Left;
    public float Right;
    public float Top;
    public float Bottom;

    // Color, thickness, if selected and shape type
    public int lineColor = Color.BLACK;
    public int thickness = 1;
    public boolean selected = false;
    char sType;

}
