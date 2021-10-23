package com.abchihba;

import lib.Window;
import lib.render.Texture;
import org.lwjgl.glfw.GLFW;

import java.awt.*;

public class Main extends Window {
    private final Texture background = Texture.load("background.jpg");
    private final Ball mouseBall;
    private final Environment environment;

    public Main() {
        super(800, 600, "Абчихба", true, "Arial", 40);
        setIcon("icon.jpg");
        mouseBall = new Ball(0, 0, 150, Color.GREEN);
        environment = new Environment(0.25, 600, 1000); // g > 0, т.к. ось Y направлена вниз
    }

    @Override
    protected void onCursorMoved(double x, double y) {
        super.onCursorMoved(x, y);
        mouseBall.x = x;
        mouseBall.y = y;
    }

    @Override
    protected void onMouseButton(int button, int action, int mods) {
        super.onMouseButton(button, action, mods);

        if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT && action != GLFW.GLFW_RELEASE) {
            environment.addBall(new Ball(mouseBall));
        }
    }

    @Override
    protected void onScroll(double dx, double dy) {
        super.onScroll(dx, dy);
        mouseBall.increaseRadius(10 * dy);
    }

    @Override
    protected void onKeyButton(int key, int scancode, int action, int mods) {
        super.onKeyButton(key, scancode, action, mods);

        switch (key) {
            case GLFW.GLFW_KEY_UP: {
                mouseBall.increaseRadius(10);
                break;
            }
            case GLFW.GLFW_KEY_DOWN: {
                mouseBall.increaseRadius(-10);
                break;
            }
        }
    }

    @Override
    protected void onFrame(double elapsed) {
        canvas.drawTexture(background, 0, 0, width, height);
        environment.move(elapsed);
        drawEnvironmentBalls();
        drawMouseBall();
    }

    private void drawEnvironmentBalls() {
        for (Ball ball: environment.getBalls()) {
            drawBall(ball);
        }
    }

    private void drawMouseBall() {
        mouseBall.color = Color.GREEN;
        for (Ball ball: environment.getBalls()) {
            if (mouseBall.intersect(ball)) {
                mouseBall.color = Color.RED;
                break;
            }
        }
        drawBall(mouseBall);
    }

    private void drawBall(Ball ball) {
        double thickness = 5;
        canvas.drawCircle(ball.color.getRGB(), ball.x, ball.y, ball.radius, thickness);
    }

    public static void main(String[] args) {
        new Main().show();
    }
}
