package com.abchihba;

public class Vector{
    public double x;
    public double y;

    public Vector(Vector v) {
        this.x = v.x;
        this.y = v.y;
    }

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void rotate(double angle) {
        double newX = x*Math.cos(angle) - y*Math.sin(angle);
        double newY = x*Math.sin(angle) + y*Math.cos(angle);
        x = newX;
        y = newY;
    }

    @Override
    public String toString() {
        return "Vector{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}