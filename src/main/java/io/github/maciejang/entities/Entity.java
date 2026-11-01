package io.github.maciejang.entities;

import java.awt.*;

public abstract class Entity {
    private float x,y;
    private float width;

    public Entity(){
        x = 0;
        y = 0;
        width = 10;
    }

    public Entity(int width, int x, int y){
        this.x = x;
        this.y = y;
        this.width = width;
    }

    public abstract void render(Graphics2D g2d);

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }
}
