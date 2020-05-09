package com.denniscorvers.recipeexporter.gui.graphics;

public class Colour {

    public static final Colour WHITE = new Colour(255,255,255,255);
    public static final Colour BLACK = new Colour(0,0,0,255);

    private int r;
    private int g;
    private int b;
    private int a;

    public Colour(int colour) {
        this.setRGBA(colour);
    }

    public Colour(int r, int g, int b) {
        clamp(r, b, g, 255);
    }

    public Colour(int r, int g, int b, int a) {
        clamp(r, g, b, a);
    }

    public int r() {
        return r;
    }

    public int g() {
        return g;
    }

    public int b() {
        return b;
    }

    public int a() {
        return a;
    }

    private void clamp(int r, int g, int b, int a) {
        this.r = r < 0 ? 0 : Math.min(r, 255);
        this.g = g < 0 ? 0 : Math.min(g, 255);
        this.b = b < 0 ? 0 : Math.min(b, 255);
        this.a = a < 0 ? 0 : Math.min(a, 255);
    }

    public int toRGBA() {
        return (r << 24) | (g << 16) | (b << 8) | a;
    }

    public int toARGB(){
        return (a << 24) | (r << 16) | (g << 8) | b;
    }

    public void setRGBA(int value) {
        r = ((value & 0xff000000) >>> 24);
        g = ((value & 0x00ff0000) >>> 16);
        b = ((value & 0x0000ff00) >>> 8);
        a = ((value & 0x000000ff));
    }
}
