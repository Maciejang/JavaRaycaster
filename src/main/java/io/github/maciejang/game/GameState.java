package io.github.maciejang.game;

import io.github.maciejang.entities.Entity;
import io.github.maciejang.entities.Player;
import io.github.maciejang.mapObjects.Wall;

import java.util.ArrayList;

public class GameState {

    private Player player;
    private ArrayList<Entity> entities = new ArrayList<>();
    private ArrayList<Wall> walls = new ArrayList<>();

    private int[] groundSkyID = {1,2};

    public GameState(){
        player = new Player();
        entities.add(player);
        walls.add(new Wall(50,50,50,50,50));

        walls.add(new Wall(-30,-30,30,30,150));

        for (int i = 0; i < 500; i+=50) {

            int xt = (int) (i * Math.cos(Math.toRadians(i)));
            int yt = (int) (i * Math.sin(Math.toRadians(i)));


            walls.add(new Wall(xt,yt,50,50,50));
        }

    }


    public void update(){
        player.userInput();

    }

    public ArrayList<Entity> getEntities(){
        return entities;
    }

    public ArrayList<Wall> getWalls(){
        return walls;
    }

    public Player getPlayer() {
        return player;
    }

    public int[] getGroundSkyID() {
        return groundSkyID;
    }

    public void setGroundSkyID(int[] groundSkyID) {
        this.groundSkyID = groundSkyID;
    }
}
