package com.abchihba;

import lib.Window;

public class Main extends Window {

    public Main() {
        super(800, 800, "Test", true, "Cambria Math", 46);
    }

    @Override
    protected void onFrame(double elapsed) {
    }

    public static void main(String[] args) {
        new Main().show();
    }
}
