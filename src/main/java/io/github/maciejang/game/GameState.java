package io.github.maciejang.game;

import io.github.maciejang.entities.Entity;
import io.github.maciejang.entities.Player;
import io.github.maciejang.mapObjects.Wall;

import java.util.ArrayList;

public class GameState {

    private Player player;
    private ArrayList<Entity> entities = new ArrayList<>();
    private ArrayList<Wall> walls = new ArrayList<>();

    public GameState(){
        player = new Player();
        entities.add(player);
        walls.add(new Wall(50,50,50,50,50));

        walls.add(new Wall(-30,-30,30,30,150));
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
}
