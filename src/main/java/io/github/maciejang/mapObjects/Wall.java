package io.github.maciejang.mapObjects;

import java.awt.*;

public class Wall {
    private float width = 50,height = 50;
    private int visualHeight = 60;
    private float x=0,y=0;
    private Color color = new Color(0,0,255);

    public Wall() {

    }

    public Wall(float x, float y, float width, float height, int visualHeight){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.visualHeight = visualHeight;
    }


    public float getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public float getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getVisualHeight() {
        return visualHeight;
    }

    public void setVisualHeight(int visualHeight) {
        this.visualHeight = visualHeight;
    }
}
