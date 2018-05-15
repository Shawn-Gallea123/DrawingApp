package com.hfad.drawing;

import android.graphics.Color;

/**
 * Created by ShawnNewPC on 3/8/2018.
 */

// Rectangle class
public class RectangleShape extends CusShape {

    // Ctor
    public RectangleShape(float upL, float upR, float downL, float downR) {
        Left = upL;
        Right = upR;
        Top = downL;
        Bottom = downR;
        sType = 'r';
    }

}
