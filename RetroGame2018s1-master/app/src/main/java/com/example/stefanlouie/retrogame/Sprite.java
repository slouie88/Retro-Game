package com.example.stefanlouie.retrogame;

import android.graphics.Canvas;
import android.graphics.Paint;


public abstract class Sprite {

    Position pos;

    public abstract void draw(Canvas c, Paint p);


}
