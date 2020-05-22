package com.example.khamisbuol.retrogame2018s1;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

Game game = new Game();

    /* The following test code makes up the Black-Box testing
    * conducted on the Game class*/

    @Test
    public void racketMovementTest() {
        game.touch(0.4f);
        assertTrue("Expected racket x-position: 0.4f, got: " + game.racket.pos.xpos, game.racket.pos.xpos == 0.4f);
        game.touch(0.7f);
        assertTrue("Expected racket x-position: 0.7f, got: " + game.racket.pos.xpos, game.racket.pos.xpos == 0.7f);
    }

    @Test
    public void racketBoundaryTest() {
        game.touch(0.1f);
        assertFalse("Expected racket x-position " + game.racket.RACKETWIDTH / 2 + ", got: " + game.racket.pos.xpos,
                game.racket.pos.xpos == 0.1f);
        assertTrue("Expected racket x-position " + game.racket.RACKETWIDTH / 2 + ", got: " + game.racket.pos.xpos,
                game.racket.pos.xpos == game.racket.RACKETWIDTH / 2);
        game.touch(0.9f);
        assertTrue("Expected racket x-position " + (1.0f - game.racket.RACKETWIDTH / 2) + ", got: " + game.racket.pos.xpos,
                game.racket.pos.xpos == 1.0f - game.racket.RACKETWIDTH / 2);
    }

    @Test
    public void racketCollisionTest() {
        game.step();
        assertFalse("Game is still in initial state and ball should not have collided with any racket",
                game.ball.collision);
        for (int i = 0; i < 38; i++)
            game.step();
        assertTrue("Game no longer in initial state as the ball has been hit",
                game.ball.collision);
        game.ball.pos.ypos = game.racket.pos.ypos;
        assertTrue("Ball should have collided with player's racket, but didn't", game.ball.racketCollision(game.racket));
        game.ball.pos.ypos = game.racketAI.pos.ypos;
        assertTrue("Ball should have collided with AI's racket, but didn't", game.ball.racketCollision(game.racketAI));
    }

    @Test
    public void updateAIScoreTest() {
        game.ball.pos.ypos = 1.0f;
        game.updateScore();
        assertTrue("Expected AI score is 1, but got: " + game.AIScore.score, game.AIScore.score == 1);
        game.ball.pos.ypos = 1.0f;
        game.updateScore();
        assertTrue("Expected AI score is 2, but got: " + game.AIScore.score, game.AIScore.score == 2);
        game.ball.pos.ypos = 1.0f;
        game.updateScore();
        assertTrue("Expected AI score is 3, but got: " + game.AIScore.score, game.AIScore.score == 3);
    }

    @Test
    public void updatePlayerScoreTest() {
        game.ball.pos.ypos = 0.0f;
        game.updateScore();
        assertTrue("Expected Player score is 1, but got: " + game.playerScore.score, game.playerScore.score == 1);
        game.ball.pos.ypos = 0.0f;
        game.updateScore();
        assertTrue("Expected Player score is 2, but got: " + game.playerScore.score, game.playerScore.score == 2);
        game.ball.pos.ypos = 0.0f;
        game.updateScore();
        assertTrue("Expected Player score is 3, but got: " + game.playerScore.score, game.playerScore.score == 3);
    }

    // Whitebox test for testing ball.step()
    @Test
    public void ballStepTest() {
        game.ball.collision = false;
        game.ball.step(game.racket, game.racketAI);
        assertTrue("In intial state of game after the first step, the ball's y position should be greater than its starting y position",
                game.ball.pos.ypos > game.ball.starty);
        game.ball.pos.ypos = game.racket.pos.ypos;
        game.ball.step(game.racket, game.racketAI);
        assertTrue("After colliding with bottom racket, y-position of ball should be less than the racket's",
                game.ball.pos.ypos < game.racket.pos.ypos);
        game.ball.pos.ypos = game.racketAI.pos.ypos;
        game.ball.step(game.racket, game.racketAI);
        assertTrue("After colliding with top racket, y-position of ball should be greater than the racket's",
                game.ball.pos.ypos > game.racketAI.pos.ypos);
        game.ball.pos.xpos = 0.0f;
        game.ball.ballHorizontalMovement = -0.05f;
        game.ball.step(game.racket, game.racketAI);
        assertTrue("If ball hits left wall, the ball's x-position should be moving in the opposite direction",
                game.ball.pos.xpos > 0.0f);
        game.ball.pos.xpos = 1.0f;
        game.ball.ballHorizontalMovement = 0.05f;
        game.ball.step(game.racket, game.racketAI);
        assertTrue("If ball hits right wall, the ball's x-position should be moving in the opposite direction",
                game.ball.pos.xpos < 1.0f);


    }

}
