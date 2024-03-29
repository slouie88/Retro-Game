package com.example.stefanlouie.retrogame;


public class Position {
    float xpos;
    float ypos;

    public Position(float xpos, float ypos) {
        this.xpos = xpos;
        this.ypos = ypos;
    }

    public Position(Position pos) {
        this.xpos = pos.xpos;
        this.ypos = pos.ypos;
    }

    public float distance(Position p) {
        float dx = xpos - p.xpos;
        float dy = ypos - p.ypos;
        return (float) Math.sqrt(dx*dx + dy*dy);
    }
}
