package com.example.stefanlouie.retrogame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class WelcomeActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        createFile();
    }

    public void playGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        finish();
    }

    public void createFile() {
        try {
            FileOutputStream fou = openFileOutput("HighScore.txt", MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fou);
            outputStreamWriter.write("0");
            outputStreamWriter.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Save: Unable to open file HighScore.txt");
        }
        catch(IOException ex) {
            System.out.println("Save: Error reading file HighScore.txt");
        }
    }


}
