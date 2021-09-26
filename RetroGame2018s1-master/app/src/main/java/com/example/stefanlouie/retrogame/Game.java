package com.example.stefanlouie.retrogame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;



public class Game {

    public Ball ball;
    public Racket racket;
    public Racket racketAI;
    public Power decreaseWidth;
    public Power increaseWidth;


    // Observable
    //private GameObserver observer;
    //GameActivity watcher;

    // Scores for AI and Player
    public Score playerScore;
    public Score AIScore;
    private float AIVelocity = 0.01f;

    public Game() {
        ball = new Ball(0.5f, 0.5f);
        decreaseWidth = new Power();
        decreaseWidth.color = Color.RED;
        racket = new Racket(0.33f, 0.975f, 0.66f, 0.95f, 0.5f, 0.9f);
        racketAI = new Racket(0.33f, 0.05f, 0.66f, 0.025f, 0.5f, 0.1f);
        playerScore = new Score(0.9f, 0.1f);
        AIScore = new Score(0.9f, -0.1f);
        increaseWidth = new Power();
        increaseWidth.color = Color.GREEN;
    }

    public void draw(Canvas canvas, Paint paint) {
        racket.draw(canvas, paint);
        racketAI.draw(canvas, paint);
        ball.draw(canvas, paint);
        playerScore.draw(canvas, paint);
        AIScore.draw(canvas, paint);
        decreaseWidth.draw(canvas, paint);
        increaseWidth.draw(canvas, paint);

        //Draws dividing line
        paint.setStrokeWidth(4f);
        canvas.drawLine(0f, canvas.getHeight() / 2, canvas.getWidth(), canvas.getHeight() / 2, paint);
    }

    void updateScore() {
        if (ball.pos.ypos <= 0) {
            playerScore.updateScore();
            //scoreUpdated = true;
            ball = new Ball(0.5f, 0.5f);
        } else if (ball.pos.ypos >= 1) {
            AIScore.updateScore();
            //scoreUpdated = true;
            ball = new Ball(0.5f, 0.5f);
        }
    }

    // new touch method
    public void touch(float xpos) {
        if (xpos < racket.RACKETWIDTH / 2)
            racket.pos.xpos = racket.RACKETWIDTH / 2;
        else if (xpos > 1.0f - racket.RACKETWIDTH / 2)
            racket.pos.xpos = 1.0f - racket.RACKETWIDTH / 2;
        else
            racket.pos.xpos = xpos;
    }

    public void step() {
        ball.step(racket, racketAI);
        trackBall();
        updateScore();
        applyPower();
    }

    void trackBall() {
        if (Math.round(racketAI.pos.xpos * 100.00) / 100.00 < Math.round(ball.pos.xpos * 100.00) / 100.00) {
            racketAI.pos.xpos += AIVelocity;
        } else if (Math.round(racketAI.pos.xpos * 100.00) / 100.00 > Math.round(ball.pos.xpos * 100.00) / 100.00) {
            racketAI.pos.xpos -= AIVelocity;
        } else {
            racketAI.pos.xpos = ball.pos.xpos;
        }
    }

    public boolean gameWon() {
        return (playerScore.score == 10 || AIScore.score == 10);
    }

    String scoreRatio(){
        float ps = playerScore.score;
        float as = AIScore.score;
        if (as == 0.00f){
            return "1.0";
        }
        else {
            float score = ps / as;
            return (score + "");
        }
    }

    public void applyPower() {
        if (powerUpHit(decreaseWidth) && ball.userHasHitLast) {
            racket = new Racket(0.43f, 0.975f, 0.56f, 0.95f, 0.5f, 0.9f);
            decreaseWidth = new Power();
            decreaseWidth.color = Color.RED;
        }
        if (powerUpHit(decreaseWidth) && !ball.userHasHitLast) {
            racketAI = new Racket(0.43f, 0.05f, 0.56f, 0.025f, 0.5f, 0.1f);
            decreaseWidth = new Power();
            decreaseWidth.color = Color.RED;
        }
        if (powerUpHit(increaseWidth) && ball.userHasHitLast) {
            racket = new Racket(0.28f, 0.975f, 0.71f, 0.95f, 0.5f, 0.9f);
            increaseWidth = new Power();
            increaseWidth.color = Color.GREEN;
        }
        if (powerUpHit(increaseWidth) && !ball.userHasHitLast) {
            racketAI = new Racket(0.28f, 0.05f, 0.71f, 0.025f, 0.5f, 0.1f);
            increaseWidth = new Power();
            increaseWidth.color = Color.GREEN;
        }
    }

    public boolean powerUpHit(Power power) {
        return (ball.pos.distance(power.pos) <= 0.05);
    }

}
