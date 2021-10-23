package com.abchihba;

import lib.Window;
import lib.render.Texture;
import org.lwjgl.glfw.GLFW;

import java.awt.*;
import java.util.ArrayList;

public class Main extends Window {
    private final Texture background = Texture.load("background.jpg");
    private int pointsCount;
    private ArrayList<Vector> pointsPositions;

    public Main(int pointsCount) {
        super(800, 600, "Абчихба", true, "Arial", 40);
        setIcon("icon.jpg");

        this.pointsCount = pointsCount;
        createPointsPositions();
    }

    private void createPointsPositions() {
        this.pointsPositions = new ArrayList<Vector>();
        double angle = 2*Math.PI / this.pointsCount;
        for (int i = 0; i < this.pointsCount; i++) {
            Vector vector = new Vector(200, 0);
            vector.rotate(angle * i);
            this.pointsPositions.add(vector);
        }
    }

    @Override
    protected void onScroll(double dx, double dy) {
        super.onScroll(dx, dy);
        this.pointsCount = Math.max(pointsCount + (int) dy, 3);
        createPointsPositions();
    }

    @Override
    protected void onKeyButton(int key, int scancode, int action, int mods) {
        super.onKeyButton(key, scancode, action, mods);
        if(action == GLFW.GLFW_RELEASE)
            return;

        switch (key) {
            case GLFW.GLFW_KEY_UP: {
                this.pointsCount = pointsCount + 1;
                createPointsPositions();
                break;
            }
            case GLFW.GLFW_KEY_DOWN: {
                this.pointsCount = Math.max(pointsCount - 1, 3);
                createPointsPositions();
                break;
            }
        }
    }

    @Override
    protected void onFrame(double elapsed) {
        canvas.drawTexture(background, 0, 0, width, height);
        drawPolygon();
    }

    private Vector getPoint(int i) {
        return pointsPositions.get(i % pointsPositions.size());
    }

    private void drawPolygon() {
        double centerX = width / 2.;
        double centerY = height / 2.;

        for(int side = 0; side < pointsPositions.size(); side++) {
            Vector start = getPoint(side);;
            Vector end = getPoint(side+1);
            canvas.drawLine(
                    Color.BLACK.getRGB(),
                    start.x + centerX,
                    start.y + centerY,
                    end.x+centerX,
                    end.y+centerY,
                    5
            );
        }
    }

    public static void main(String[] args) {
        new Main(6).show();
    }
}
