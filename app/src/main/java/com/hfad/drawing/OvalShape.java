package com.hfad.drawing;

import android.graphics.Color;

/**
 * Created by ShawnNewPC on 3/9/2018.
 */

public class OvalShape extends CusShape {

    public OvalShape(float upL, float upR, float downL, float downR) {
        Left = upL;
        Right = upR;
        Top = downL;
        Bottom = downR;
        sType = 'o';
    }


}
