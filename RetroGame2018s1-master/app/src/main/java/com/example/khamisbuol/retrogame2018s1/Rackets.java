package com.example.khamisbuol.retrogame2018s1;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;


public class Rackets extends ArrayList<Racket> {



    public void step() {

    }

    public void draw(Canvas c, Paint p) {
        for (Racket r : this) {
            r.draw(c, p);
        }
    }
}
