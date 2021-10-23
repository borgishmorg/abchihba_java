package com.abchihba;

import lib.Window;
import lib.render.Texture;

public class Main extends Window {
    private final Texture background = Texture.load("background.jpg");

    public Main() {
        super(800, 600, "Абчихба", true, "Arial", 40);
        setIcon("icon.jpg");
    }

    @Override
    protected void onFrame(double elapsed) {
        canvas.drawTexture(background, 0, 0, width, height);
    }

    public static void main(String[] args) {
        new Main().show();
    }
}
