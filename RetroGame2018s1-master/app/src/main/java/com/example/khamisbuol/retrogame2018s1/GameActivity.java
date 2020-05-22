package com.example.khamisbuol.retrogame2018s1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
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
