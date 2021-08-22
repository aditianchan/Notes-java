package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

public class frontactivity extends AppCompatActivity {
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frontactivity);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // TODO: Your application init goes here.
                Intent mInHome = new Intent(frontactivity.this, MainActivity.class);
                startActivity(mInHome);
                finish();
            }
        }, 3000);


    }


}