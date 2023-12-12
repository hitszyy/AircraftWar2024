package com.example.aircraftwar2024;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;


public class OfflineActivity extends AppCompatActivity {
    private boolean isMusicOn = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline);

        isMusicOn = getIntent().getBooleanExtra("music",false);
        Log.d("OfflineActivity","this music flag is " + isMusicOn);

    }
}