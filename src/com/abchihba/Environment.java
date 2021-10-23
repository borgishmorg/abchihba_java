package com.abchihba;

import java.util.ArrayList;
import java.util.List;

public class Environment {
    private final List<Ball> balls;
    private final double energyLoss;
    private final double floorY;
    private final double gravityConstant;

    /**
     * @param energyLoss доля энегрии, которая тратится при ударении
     * @param floorY Y координата пола
     * @param gravityConstant ускорение свободного падения
     */
    public Environment(double energyLoss, double floorY, double gravityConstant) {
        balls = new ArrayList<Ball>();
        this.energyLoss = energyLoss;
        this.floorY = floorY;
        this.gravityConstant = gravityConstant;
    }

    public void move(double dt) {
        for(Ball ball: balls)
            moveBall(ball, dt);
    }

    private void moveBall(Ball ball, double dt) {
        ball.vy += gravityConstant * dt;
        ball.y += ball.vy * dt;

        if (ball.y + ball.radius > floorY) { // знак больше т.к. ось Y направлена вниз
            ball.y = floorY - ball.radius;
            double energy = ball.vy * ball.vy;
            energy *= (1-energyLoss);
            ball.vy = -1 * Math.signum(ball.vy) * Math.pow(energy, .5);
        }
    }

    public void addBall(Ball ball) {
        balls.add(ball);
    }

    public List<Ball> getBalls() {
        return balls;
    }
}
