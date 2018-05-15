package com.hfad.drawing;

import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by ShawnNewPC on 3/10/2018.
 */

// Ball class for bouncing balls
public class Ball extends CusShape {

    // True if moving rightward and downward
    public boolean right = true;
    public boolean down = true;

    // Ctor
    public Ball(float upL, float upR, float downL, float downR) {
        Left = upL;
        Right = upR;
        Top = downL;
        Bottom = downR;
        sType = 'b';
    }

    // Animate Ball relative to screen size
    public void animate() {
        if (Right >= DrawingAttributes.screenSize.x) {
            right = false;
        } else if (Left <= 0) {
            right = true;
        }

        if (Bottom >= DrawingAttributes.screenSize.y) {
            down = false;
        } else if (Top <= 0) {
            down = true;
        }

        if (right) {
            Right += 10;
            Left += 10;
        } else {
            Right -= 10;
            Left -= 10;
        }

        if (down) {
            Top += 10;
            Bottom += 10;
        } else {
            Top -= 10;
            Bottom -= 10;
        }
    }

}
