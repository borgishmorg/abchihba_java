package com.abchihba;

import lib.Window;
import lib.render.Texture;
import org.lwjgl.glfw.GLFW;

import java.awt.*;
import java.util.ArrayList;

public class Main extends Window {
    private final Texture background = Texture.load("background.jpg");
    private final double thickness = 5;
    private double circleRadius = 150;
    private double mouseX = 0;
    private double mouseY = 0;
    private double centerX = width / 2.;
    private double centerY = height / 2.;

    public Main() {
        super(800, 600, "Абчихба", true, "Arial", 40);
        setIcon("icon.jpg");
    }

    @Override
    protected void onCursorMoved(double x, double y) {
        super.onCursorMoved(x, y);
        mouseX = x;
        mouseY = y;
    }

    @Override
    protected void onScroll(double dx, double dy) {
        super.onScroll(dx, dy);
        circleRadius = Math.max(circleRadius + 10 *(int) dy, 1);;
    }

    @Override
    protected void onKeyButton(int key, int scancode, int action, int mods) {
        super.onKeyButton(key, scancode, action, mods);

        switch (key) {
            case GLFW.GLFW_KEY_UP: {
                circleRadius = circleRadius + 10;
                break;
            }
            case GLFW.GLFW_KEY_DOWN: {
                this.circleRadius = Math.max(circleRadius - 10, 1);
                break;
            }
        }
    }

    @Override
    protected void onFrame(double elapsed) {
        canvas.drawTexture(background, 0, 0, width, height);
        drawCenterCircle();
        drawMouseCircle();
    }

    private void drawCenterCircle() {
        canvas.drawCircle(Color.BLACK.getRGB(), centerX, centerY, circleRadius, thickness);
    }

    private void drawMouseCircle() {
        int color = Color.GREEN.getRGB();

        double d = Math.sqrt(Math.pow(mouseX-centerX, 2) + Math.pow(mouseY-centerY, 2));

        // Добавил thickness  - 1, что бы учитывалась граница (так выглядит красивее ^_^)
        // Случай, когда одна окружность вснутри другой можно не рассматривать, т.к. их радиусы равны
        if (d < 2*circleRadius + thickness  - 1) {
            color = Color.RED.getRGB();
        }

        canvas.drawCircle(color, mouseX, mouseY, circleRadius, thickness);
    }

    public static void main(String[] args) {
        new Main().show();
    }
}
