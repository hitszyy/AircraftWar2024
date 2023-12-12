package com.example.aircraftwar2024;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private boolean isMusicOn = false;
    private RadioButton onBtn;
    private RadioButton offBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startBtn = findViewById(R.id.startBtn);
        Button exitBtn = findViewById(R.id.exitBtn);
        onBtn = findViewById(R.id.startMusic);
        offBtn = findViewById(R.id.closeMusic);
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
            if(this.onBtn.isChecked()){
                isMusicOn = true;
            } else {
                isMusicOn = false;
            }
            intent.putExtra("music", isMusicOn);
            startActivity(intent);
        }else if(v.getId() == R.id.exitBtn) {
            finish();
        }
    }
}