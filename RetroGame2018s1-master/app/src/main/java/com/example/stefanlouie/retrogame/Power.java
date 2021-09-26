package com.example.stefanlouie.retrogame;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Random;

public class Power extends Sprite {
    Random random = new Random();
    public float RADIUS = 30f;
    public int color;

    // Starting position of a given power
    public float xpos = 0.1f + random.nextFloat() * 0.8f;
    public float ypos = 0.1f + random.nextFloat() * 0.8f;

    Power() {
        this.color = Integer.valueOf(color);
        pos = new Position(xpos, ypos);
    }

    @Override
    public void draw(Canvas c, Paint p) {
        p = new Paint();
        p.setColor(color);
        c.drawCircle(pos.xpos * c.getWidth(), pos.ypos * c.getHeight(), RADIUS, p);
    }
}
