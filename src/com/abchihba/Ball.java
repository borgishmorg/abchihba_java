package com.abchihba;

import java.awt.*;

public class Ball {
    public double x;
    public double y;
    public double vy;
    public double radius;
    public Color color;

    public Ball(Ball ball) {
        x = ball.x;
        y = ball.y;
        radius = ball.radius;
        color = ball.color;
    }

    public Ball(double x, double y, double radius, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    public void increaseRadius(double dr) {
        radius = Math.max(radius + dr, 1);
    }

    public boolean intersect(Ball other) {
        double d = Math.sqrt(Math.pow(x-other.x, 2) + Math.pow(y- other.y, 2));

        return d < radius + other.radius && ! (d < Math.abs(radius- other.radius));
    }
}
