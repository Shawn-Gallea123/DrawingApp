package com.hfad.drawing;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.app.AlertDialog;

import java.util.ArrayList;

public class Settings extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Spinner thickSpin = findViewById(R.id.spinner);
        thickSpin.setSelection(4);
    }

    public void onClickCanvas(View view) {

        Spinner spinner = findViewById(R.id.spinner);
        String thickStr = String.valueOf(spinner.getSelectedItem());
        int thick = (spinner.getSelectedItemPosition() + 1) * 2;
        DrawingAttributes.thickness = thick;

        Spinner colorSpinner = findViewById(R.id.colorSpinner);
        int col = colorSpinner.getSelectedItemPosition();
        DrawingAttributes.color = col;

        Spinner modeSpinner = findViewById(R.id.modeSpinner);
        int mode = modeSpinner.getSelectedItemPosition();
        System.out.println(modeSpinner.getSelectedItem().toString());
        if (mode == 0) {
            DrawingAttributes.type = 'l';
        } else if (mode == 1) {
            DrawingAttributes.type = 'L';
        } else if (mode == 2) {
            DrawingAttributes.type = 'r';
        } else if (mode == 3){
            DrawingAttributes.type = 'o';
        } else if (mode == 4){
            DrawingAttributes.type = 's';
        } else {
            DrawingAttributes.type = 'b';
        }

        Intent intent = new Intent(this, Canvas.class);
        startActivity(intent);
    }

    public void onClickClear(View view) {
        AlertDialog.Builder aDB = new AlertDialog.Builder(this);
        aDB.setMessage("Are you sure you want to clear the canvas?");
        aDB.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DrawingAttributes.shapes = new ArrayList<CusShape>();
                DrawingAttributes.lines = new ArrayList<Line>();
            }
        });

        aDB.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Nothing
            }
        });

        aDB.show();
    }

}
