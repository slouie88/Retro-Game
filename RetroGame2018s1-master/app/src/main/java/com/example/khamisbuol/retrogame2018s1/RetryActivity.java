package com.example.khamisbuol.retrogame2018s1;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.io.File;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.InputStream;
import java.io.InputStreamReader;

import static android.os.Environment.getExternalStorageDirectory;

public class RetryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retry);

        // Collect the current score sent from the last intent "GameActivity.java"
        Intent intent = getIntent();
        String scoreStr = intent.getStringExtra("Score");
        float score = Float.valueOf(scoreStr);

        // Display the current score after the game
        TextView scoreTV = (TextView)findViewById(R.id.score);
        scoreTV.setText("Your Score (Player/AI): "+score);


        // Check if the current score was a new high score, if yes, then update it in HighScore.txt
        String highScore = load();
        System.out.println("THIS IS MEANT TO BE SAVED SCORE "+highScore);
        float highScoreFloat = Float.valueOf(highScore);
        float scoreCompare = Float.compare(score, highScoreFloat);

        if (highScore.equals("") || scoreCompare > 0){
            save(scoreStr);
            highScore = scoreStr;
        }

        // Display the High Score
        TextView highScoreTV = (TextView)findViewById(R.id.highscore);
        highScoreTV.setText("High Score (Player/AI): "+highScore);

    }

    public void playGame(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        finish();
    }

    public void returnToMenu(View view){
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void save(String newHighScore) {
        try {
            FileOutputStream fou = openFileOutput("HighScore.txt", MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fou);
            outputStreamWriter.write(newHighScore);
            outputStreamWriter.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Save: Unable to open file HighScore.txt");
        }
        catch(IOException ex) {
            System.out.println("Save: Error reading file HighScore.txt");
        }
    }

    public String load() {

        String savedHighScore = "";

        try {
            InputStream inputStream = openFileInput("HighScore.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String inputStr = "";
                String outputStr = "";

                while ( (inputStr = bufferedReader.readLine()) != null ) {
                    outputStr = inputStr;
                }

                inputStream.close();
                savedHighScore = outputStr;
            }

        }
        catch(FileNotFoundException ex) {
            System.out.println("Load: Unable to open file HighScore.txt");
        }
        catch(IOException ex) {
            System.out.println("Load: Error reading file HighScore.txt");
        }

        return savedHighScore;
    }

}
