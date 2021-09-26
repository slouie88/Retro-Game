package com.example.stefanlouie.retrogame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;




public class Racket extends Sprite {
    // Define parameters for drawing rectangle/racket (positions are relative to the width and height of the canvas)
    public float LEFT; //= 0.33F;
    public float RIGHT; //= 0.66F;
    public float TOP;// = 0.95F;
    public float BOTTOM;// = 0.90F;
    public float RACKETWIDTH; //= 0.33f;

    // Define starting positions for the Racket
    public float STARTX;// = 0.5f;
    public float STARTY;// = 0.9f;

    public Racket() {
        pos = new Position(STARTX, STARTY);
    }

    public Racket(float left, float top, float right, float bottom, float startx, float starty) {
        this.LEFT = left;
        this.TOP = top;
        this.RIGHT = right;
        this.BOTTOM = bottom;
        RACKETWIDTH = right - left;
        pos = new Position(this.STARTX = startx, this.STARTY = starty);
    }

    @Override
    public void draw(Canvas c, Paint p) {

        // Get the width and height of the canvas
        int w = c.getWidth();
        int h = c.getHeight();

        // Obtain the current position of the racket and calculate the change in x-position
        float currentx = pos.xpos;
        float dx = Math.abs(currentx - STARTX);

        // Draw the racket if user moves to the left of initial position
        if (currentx < STARTX) {
            c.drawRect((LEFT - dx) * w, TOP * h, (RIGHT - dx) * w, BOTTOM * h, p);
            RIGHT -= dx;
            LEFT -= dx;
            STARTX = currentx;
        }
        // Draw the racket if user moves to the right of initial position
        else if (currentx > STARTX) {
            c.drawRect((LEFT + dx) * w, TOP * h, (RIGHT + dx) * w, BOTTOM * h, p);
            RIGHT += dx;
            LEFT += dx;
            STARTX = currentx;
        }
        // Draw the racket if no changes are made to the initial position
        else {
            c.drawRect(LEFT * w, TOP * h, RIGHT * w, BOTTOM * h, p);
        }
    }
}
