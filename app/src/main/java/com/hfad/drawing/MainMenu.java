package com.hfad.drawing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;

// Main menu activity
public class MainMenu extends Activity {

    // OnCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // Get screen size
        Display display = getWindowManager().getDefaultDisplay();
        display.getSize(DrawingAttributes.screenSize);
    }

    // Enter settings screen
    public void onClickSettings(View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
}
