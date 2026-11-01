package io.github.maciejang.entities;

import io.github.maciejang.input.KeyboardHandler;

import java.awt.*;

public class Player extends Entity{
    private float speed = 2;
    private double rotation = 0;
    private double FOV = 60;

    public void userInput(){
        double rot = getRotation();

        if(KeyboardHandler.inputUsed("w")){
            setX((float) (getX() + speed * Math.cos(rot)));
            setY((float) (getY() + speed * Math.sin(rot)));
        }
        if(KeyboardHandler.inputUsed("s")){
            setX((float) (getX() - speed * Math.cos(rot)));
            setY((float) (getY() - speed * Math.sin(rot)));
        }

        if(KeyboardHandler.inputUsed("a")){
            setX((float) (getX() + speed * Math.sin(rot)));
            setY((float) (getY() - speed * Math.cos(rot)));
        }
        if(KeyboardHandler.inputUsed("d")){
            setX((float) (getX() - speed * Math.sin(rot)));
            setY((float) (getY() + speed * Math.cos(rot)));
        }

        if(KeyboardHandler.inputUsed("arrowLeft")){
            setRotation(getRotation() - 0.05);
        } else if (KeyboardHandler.inputUsed("arrowRight")) {
            setRotation(getRotation() + 0.05);
        }
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public float getSpeed() {
        return speed;
    }

    @Override
    public void render(Graphics2D g2d) {

    }

    public double getFOV() {
        return FOV;
    }

    public void setFOV(double FOV) {
        this.FOV = FOV;
    }
}
