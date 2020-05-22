package com.example.khamisbuol.retrogame2018s1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class NewRacket extends Sprite {
    // Define parameters for drawing rectangle/racket (positions are relative to the width and height of the canvas)
    public float LEFT = 0.30F;
    public float RIGHT = 0.69F;
    public float TOP;// = 0.95F;
    public float BOTTOM;// = 0.90F;
    public float RACKETWIDTH; //= 0.33f;

    static final float RADIUS = 230f;
    static final float INNERRADIUS = 200f;

    // Define starting positions for the Racket
    public float STARTX;// = 0.5f;
    public float STARTY;// = 0.5f;
    Paint white;// = new Paint();
    public static final int BLACK = Color.BLACK;

    public NewRacket(float TOP, float BOTTOM, float STARTX, float STARTY) {
        this.RACKETWIDTH = 0.33f;
        this.TOP = TOP;
        this.BOTTOM = BOTTOM;
        pos = new Position(this.STARTX = STARTX, this.STARTY = STARTY);
        white = new Paint();
        white.setColor(BLACK);
    }

    public NewRacket() {
        pos = new Position(STARTX, STARTY);

    }

    @Override
    public void draw(Canvas c, Paint p) {
        float w = c.getWidth();
        float h = c.getHeight();

    }
}
