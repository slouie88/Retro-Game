package com.example.stefanlouie.retrogame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import java.io.Serializable;


public class GameActivity extends AppCompatActivity implements Serializable{
    String filename = "time.ser";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }


    public void endActivity(String endingScore) {
        Intent intent = new Intent(this, RetryActivity.class);
        intent.putExtra("Score", endingScore);
        startActivity(intent);
        finish();
    }
}
