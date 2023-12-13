package com.example.aircraftwar2024.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.aircraftwar2024.R;

public class OfflineActivity extends AppCompatActivity {
    private boolean isMusicOn = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline);

        isMusicOn = getIntent().getBooleanExtra("music",false);
        Log.d("OfflineActivity","this music flag is " + isMusicOn);

        Button easyBtn = findViewById(R.id.easyBtn);
        Button normalBtn = findViewById(R.id.normalBtn);
        Button hardBtn = findViewById(R.id.hardBtn);


        Intent intent = new Intent(this, GameActivity.class);
        easyBtn.setOnClickListener(view -> {
            intent.putExtra("gameType",1);
            startActivity(intent);
        });

        normalBtn.setOnClickListener(view -> {
            intent.putExtra("gameType",2);
            startActivity(intent);
        });

        hardBtn.setOnClickListener(view -> {
            intent.putExtra("gameType",3);
            startActivity(intent);
        });
    }
}