package com.example.khamisbuol.retrogame2018s1;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Score extends Sprite {
    public int score = 0;
    public float xpos;
    public float ypos;

    public Score(float xpos, float ypos) {
        pos = new Position(this.xpos = xpos, this.ypos = ypos);
    }

    // Method for updating the score
    public void updateScore() {
        score++;
    }

    @Override
    public void draw(Canvas c, Paint p) {
        float h = c.getHeight();
        float w = c.getWidth();

        float scorePosX = w * xpos;
        float scorePosY = (h / 2) + (h * ypos);

        p.setTextSize(100);
        c.save();
        c.rotate(90, scorePosX, scorePosY);
        c.drawText(score + "", scorePosX, scorePosY, p);
        c.restore();
    }
}
