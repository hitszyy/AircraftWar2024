package com.example.aircraftwar2024.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.aircraftwar2024.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private boolean isMusicOn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startBtn = findViewById(R.id.startBtn);
        Button exitBtn = findViewById(R.id.exitBtn);
        RadioButton onBtn = findViewById(R.id.startMusic);
        RadioButton offBtn = findViewById(R.id.closeMusic);
        startBtn.setOnClickListener(this);
        exitBtn.setOnClickListener(this);

        if(onBtn.isChecked()){
            isMusicOn = true;
        } else {
            isMusicOn = false;
        }
    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.startBtn ) {
            Intent intent = new Intent(this, OfflineActivity.class);
            intent.putExtra("music", isMusicOn);
            startActivity(intent);
        }else if(v.getId() == R.id.exitBtn) {
            finish();
        }
    }
}