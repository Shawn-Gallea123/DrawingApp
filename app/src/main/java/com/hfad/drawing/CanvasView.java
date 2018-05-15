package com.hfad.drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.service.dreams.DreamService;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

/**
 * Created by ShawnNewPC on 3/7/2018.
 */

// Actual CanvasView drawn on
public class CanvasView extends View {

    // Ctor
    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // OnTouch
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // If touch down, begin creating shape and add to respective shape ArrayList
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (DrawingAttributes.type == 'r') {
                RectangleShape r = new RectangleShape(event.getX(), event.getX(), event.getY(), event.getY());
                r.lineColor = DrawingAttributes.color;
                r.thickness = DrawingAttributes.thickness;
                DrawingAttributes.shapes.add(r);
                DrawingAttributes.originX = event.getX();
                DrawingAttributes.originY = event.getY();
            } else if (DrawingAttributes.type == 'l'){
                Line line = new Line();
                line.type = 'f';
                line.lineColor = DrawingAttributes.color;
                line.thickness = DrawingAttributes.thickness;
                DrawingAttributes.lines.add(line);
            } else if (DrawingAttributes.type == 'L') {
                Line line = new Line();
                line.type = 's';
                line.lineColor = DrawingAttributes.color;
                line.thickness = DrawingAttributes.thickness;
                line.xPoints.add(event.getX());
                line.yPoints.add(event.getY());
                line.xPoints.add(event.getX());
                line.yPoints.add(event.getY());
                DrawingAttributes.lines.add(line);
            } else if (DrawingAttributes.type == 'o'){
                OvalShape o = new OvalShape(event.getX(), event.getX(), event.getY(), event.getY());
                o.lineColor = DrawingAttributes.color;
                o.thickness = DrawingAttributes.thickness;
                DrawingAttributes.shapes.add(o);
                DrawingAttributes.originX = event.getX();
                DrawingAttributes.originY = event.getY();
            } else if (DrawingAttributes.type == 's') {
                // If in select mode, check bounds of all shapes
                CusShape selected = checkBounds(event);

                // Clear all other selected shapes
                for (CusShape shape : DrawingAttributes.shapes) {
                    if (shape != selected) {
                        shape.selected = false;
                    }
                }

                // Store last touch
                DrawingAttributes.lastTouchX = event.getX();
                DrawingAttributes.lastTouchY = event.getY();
            } else {
                // Create ball to animate on screen
                Ball b = new Ball(event.getX() - 50, event.getX() + 50, event.getY() - 50, event.getY() + 50);
                b.lineColor = DrawingAttributes.color;
                b.thickness = DrawingAttributes.thickness;
                DrawingAttributes.shapes.add(b);
            }
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            // User has began swiping

            // Adjust last created rectangle or oval
            if (DrawingAttributes.type == 'r' || DrawingAttributes.type == 'o') {
                float originX = DrawingAttributes.originX;
                float originY = DrawingAttributes.originY;
                float x = event.getX();
                float y = event.getY();

                // Adjust according to origin touch point of shape
                if (x > originX) {
                    DrawingAttributes.shapes.get(DrawingAttributes.shapes.size() - 1).Right = event.getX();
                } else {
                    DrawingAttributes.shapes.get(DrawingAttributes.shapes.size() - 1).Left = event.getX();
                }

                if (y < originY) {
                    DrawingAttributes.shapes.get(DrawingAttributes.shapes.size() - 1).Top = event.getY();
                } else {
                    DrawingAttributes.shapes.get(DrawingAttributes.shapes.size() - 1).Bottom = event.getY();
                }


            } else if (DrawingAttributes.type == 'L') {
                DrawingAttributes.lines.get(DrawingAttributes.lines.size() - 1).xPoints.set(DrawingAttributes.lines.get(DrawingAttributes.lines.size() - 1).xPoints.size() - 1, event.getX());
                DrawingAttributes.lines.get(DrawingAttributes.lines.size() - 1).yPoints.set(DrawingAttributes.lines.get(DrawingAttributes.lines.size() - 1).yPoints.size() - 1, event.getY());
            } else if (DrawingAttributes.type == 'l'){
                DrawingAttributes.lines.get(DrawingAttributes.lines.size() - 1).xPoints.add(event.getX());
                DrawingAttributes.lines.get(DrawingAttributes.lines.size() - 1).yPoints.add(event.getY());
            } else if (DrawingAttributes.type == 's') {
                // If in select mode, begin moving shape by distance dragged

                CusShape selShape = null;

                for (CusShape shape : DrawingAttributes.shapes) {
                    if (shape.selected) {
                        selShape = shape;
                        break;
                    }
                }

                if (selShape != null) {
                    float diffX = event.getX() - DrawingAttributes.lastTouchX;
                    float diffY = event.getY() - DrawingAttributes.lastTouchY;

                    selShape.Left += diffX;
                    selShape.Right += diffX;
                    selShape.Top += diffY;
                    selShape.Bottom += diffY;
                }

                // Store last touch
                DrawingAttributes.lastTouchX = event.getX();
                DrawingAttributes.lastTouchY = event.getY();

            }
        }

        // Redraw screen
        invalidate();
        return true;
    }


    // OnDraw
    @Override
    public void onDraw(Canvas canvas) {

        // Iterate through and draw all lines
        for (Line line : DrawingAttributes.lines) {
            setColor(line.lineColor);
            DrawingAttributes.painter.setStrokeWidth(line.thickness);

            if (line.type == 'f') {

                int len = line.xPoints.size();
                for (int i = 0; i < len - 1; ++i) {
                    canvas.drawLine(line.xPoints.get(i), line.yPoints.get(i),
                            line.xPoints.get(i + 1), line.yPoints.get(i + 1), DrawingAttributes.painter);
                }
            } else {
                canvas.drawLine(line.xPoints.get(0), line.yPoints.get(0),
                        line.xPoints.get(1), line.yPoints.get(1), DrawingAttributes.painter);
            }
        }

        // Iterate through and draw all shapes
        for (CusShape shape : DrawingAttributes.shapes) {
            setColor(shape.lineColor);
            DrawingAttributes.painter.setStrokeWidth(shape.thickness);

            if (shape.sType == 'r') {
                canvas.drawRect(shape.Left, shape.Top, shape.Right, shape.Bottom, DrawingAttributes.painter);
            } else if (shape.sType == 'o') {
                canvas.drawOval(shape.Left, shape.Top, shape.Right, shape.Bottom, DrawingAttributes.painter);
            } else {
                Ball b = (Ball) shape;
                b.animate();
                canvas.drawOval(b.Left, b.Top, b.Right, b.Bottom, DrawingAttributes.painter);
            }

        }
    }

    // Set currently used color
    public void setColor(int color) {
        if (color == 0) {
            DrawingAttributes.painter.setColor(Color.RED);
        } else if (color == 1) {
            DrawingAttributes.painter.setColor(Color.YELLOW);
        } else if (color == 2) {
            DrawingAttributes.painter.setColor(Color.GREEN);
        } else if (color == 3) {
            DrawingAttributes.painter.setColor(Color.BLUE);
        } else if (color == 4) {
            DrawingAttributes.painter.setColor(Color.MAGENTA);
        }

        // Redraw
        invalidate();

    }

    // Check bounds of shapes, returning selected shape
    private CusShape checkBounds(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        int len = DrawingAttributes.shapes.size();

        for (int i = len - 1; i >= 0; i--) {
            CusShape shape = DrawingAttributes.shapes.get(i);
            if (x >= shape.Left && x <= shape.Right && y >= shape.Top && y <= shape.Bottom) {
                shape.selected = true;
                return shape;
            }
        }
        return null;
    }
}
