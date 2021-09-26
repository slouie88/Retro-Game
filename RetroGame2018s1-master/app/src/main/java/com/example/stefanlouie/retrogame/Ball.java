package com.example.stefanlouie.retrogame;

import android.graphics.Canvas;
import android.graphics.Paint;


public class Ball extends Sprite {

    static final float BALLRADIUS = 50f;
    float startx;
    float starty;
    float ballHorizontalMovement;
    float ballVerticalMovement;
    boolean collision;
    boolean userHasHitLast;

    public Ball(float startx, float starty) {
        this.startx = startx;
        this.starty = starty;
        pos = new Position(startx, starty);
        this.ballVerticalMovement = 0.0f;
        this.ballHorizontalMovement = 0.0f;
        collision = false;
        userHasHitLast = false;
    }

    @Override
    // Draws the circle
    public void draw(Canvas c, Paint p) {
        c.drawCircle(pos.xpos * c.getWidth(), pos.ypos * c.getHeight(), BALLRADIUS, p);
    }

    // Checks if the ball has collided with a racket
    public boolean racketCollision(Racket racket) {
        if (pos.distance(racket.pos) < racket.RACKETWIDTH / 2) {
            if ((pos.ypos >= racket.pos.ypos && pos.ypos <= racket.TOP && racket.pos.ypos >= 0.5)
                    || (pos.ypos <= racket.pos.ypos && pos.ypos >= racket.BOTTOM && racket.pos.ypos < 0.5)) {
                collision = true;
                return true;
            }
        }
        return false;
    }


    // Updates the state of the ball for every step of the game
    public void step(Racket racket, Racket racket2) {
        // If the ball has not collided with anything yet, the game is in an initial state
        if (collision == false)
            ballVerticalMovement = 0.025f;
        // Ball rebounding if ball collides with bottom racket
        if (racketCollision(racket)) {
            userHasHitLast = true;
            if ((pos.xpos > (racket.pos.xpos - racket.RACKETWIDTH / 6) && (pos.xpos <= racket.pos.xpos))
                    || ((pos.xpos > racket.pos.xpos) && (pos.xpos < racket.pos.xpos + racket.RACKETWIDTH / 6))) {
                ballVerticalMovement = -0.025f;
                pos.ypos += ballVerticalMovement;
            } else if ((pos.xpos > (racket.pos.xpos - 2 * (racket.RACKETWIDTH / 6))
                    && (pos.xpos <= (racket.pos.xpos - racket.RACKETWIDTH / 6)))) {
                ballHorizontalMovement = -0.025f;
                ballVerticalMovement = -0.025f;
            } else if ((pos.xpos >= (racket.pos.xpos - 3 * (racket.RACKETWIDTH / 6))
                    && (pos.xpos <= (racket.pos.xpos - 2 * (racket.RACKETWIDTH / 6))))) {
                ballHorizontalMovement = -0.025f;
                ballVerticalMovement = -0.0125f;
            } else if ((pos.xpos >= (racket.pos.xpos + racket.RACKETWIDTH / 6))
                    && (pos.xpos < (racket.pos.xpos + 2 * (racket.RACKETWIDTH / 6)))) {
                ballHorizontalMovement = 0.025f;
                ballVerticalMovement = -0.025f;
            } else if ((pos.xpos >= (racket.pos.xpos + 2 * (racket.RACKETWIDTH / 6)))
                    && (pos.xpos <= (racket.pos.xpos + 3 * (racket.RACKETWIDTH / 6)))) {
                ballHorizontalMovement = 0.025f;
                ballVerticalMovement = -0.0125f;
            }
        }
        // Ball rebounding off the top racket
        else if (racketCollision(racket2)) {
            userHasHitLast = false;
            if ((pos.xpos > (racket2.pos.xpos - racket2.RACKETWIDTH / 6) && (pos.xpos <= racket2.pos.xpos))
                    || ((pos.xpos > racket2.pos.xpos) && (pos.xpos < racket2.pos.xpos + racket2.RACKETWIDTH / 6))) {
                ballVerticalMovement = 0.025f;
                pos.ypos += ballVerticalMovement;
            } else if ((pos.xpos > (racket2.pos.xpos - 2 * (racket2.RACKETWIDTH / 6))
                    && (pos.xpos <= (racket2.pos.xpos - racket2.RACKETWIDTH / 6)))) {
                ballHorizontalMovement = -0.025f;
                ballVerticalMovement = 0.025f;
            } else if ((pos.xpos >= (racket2.pos.xpos - 3 * (racket2.RACKETWIDTH / 6))
                    && (pos.xpos <= (racket2.pos.xpos - 2 * (racket2.RACKETWIDTH / 6))))) {
                ballHorizontalMovement = -0.025f;
                ballVerticalMovement = 0.0125f;
            } else if ((pos.xpos >= (racket2.pos.xpos + racket2.RACKETWIDTH / 6))
                    && (pos.xpos < (racket2.pos.xpos + 2 * (racket2.RACKETWIDTH / 6)))) {
                ballHorizontalMovement = 0.025f;
                ballVerticalMovement = 0.025f;
            } else if ((pos.xpos >= (racket2.pos.xpos + 2 * (racket2.RACKETWIDTH / 6)))
                    && (pos.xpos <= (racket2.pos.xpos + 3 * (racket2.RACKETWIDTH / 6)))) {
                ballHorizontalMovement = 0.025f;
                ballVerticalMovement = 0.0125f;
            }
        }
        // If the ball collides with the wall
        else if (pos.xpos <= 0 || pos.xpos >= 1) {
            ballHorizontalMovement = -ballHorizontalMovement;
        }
        // Update the position of the ball
        pos.xpos += ballHorizontalMovement;
        pos.ypos += ballVerticalMovement;
    }

}


