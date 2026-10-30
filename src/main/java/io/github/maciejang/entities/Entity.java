package io.github.maciejang.entities;

public abstract class Entity {
    private int x,y;
    private int width;

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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
