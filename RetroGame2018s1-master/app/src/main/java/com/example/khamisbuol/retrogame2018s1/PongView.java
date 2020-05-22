package com.example.khamisbuol.retrogame2018s1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by khamisbuol on 11/4/18.
 */

public class PongView extends View implements View.OnTouchListener, Runnable {
    public static final int DEFAULTCOLOUR = Color.WHITE;
    public static final int STEPDELAY = 50;
    Paint paint;
    Handler repaintHandler;
    Game game;

    public PongView(Context context, AttributeSet as) {
        super(context, as);

        paint = new Paint();
        paint.setColor(DEFAULTCOLOUR);
        this.setOnTouchListener(this);
        game = new Game();

        repaintHandler = new Handler();
        repaintHandler.postDelayed(this, STEPDELAY);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float w = (float) v.getWidth();
        // float h = (float) v.getHeight();

        game.touch(event.getX() / w);
        return true;
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        game.draw(canvas, paint);
    }

    public boolean step() {
        game.step();
//        game.trackBall();
//        game.updateScore();
//        game.increaseWidth();
        if (game.gameWon()) {
            Context context = this.getContext();
            while (!(context instanceof GameActivity))
                context = ((GameActivity) context).getBaseContext();
            ((GameActivity) context).endActivity(game.scoreRatio());
            return false;
        }
        this.invalidate();
        return true;
    }

    @Override
    public void run() {
        if (step()) {
            repaintHandler.postDelayed(this, PongView.STEPDELAY);
        }
    }
}
