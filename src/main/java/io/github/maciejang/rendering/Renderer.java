package io.github.maciejang.rendering;

import io.github.maciejang.entities.Player;
import io.github.maciejang.game.GameState;
import io.github.maciejang.mapObjects.Wall;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Renderer {

    private int MAX_DISTACE = 800;

    public void render(Graphics2D g2d, GameState gameState, int screenWidth, int screenHeight){

        Player player = gameState.getPlayer();
        double FOV = Math.toRadians(player.getFOV());

        for(int i=0; i < screenWidth;i++){

            double rayAngle = (player.getRotation() - (FOV/2) + ((double)i/screenWidth) * FOV);

            double distance = 0;
            boolean hit = false;
            Color hitColor = Color.black;
            int visualHeight = 0;

            g2d.setColor(Color.WHITE);
            while(!hit && distance < MAX_DISTACE){
                distance += 3.0;
                int rayX = (int) (player.getX() + distance * Math.cos(rayAngle));
                int rayY = (int) (player.getY() + distance * Math.sin(rayAngle));

                for(Wall w : gameState.getWalls()){
                    if(w.getX() <= rayX && rayX <= w.getX() + w.getWidth() &&
                            w.getY() <= rayY && rayY <= w.getY() + w.getHeight()){
                        hit = true;
                        hitColor = w.getColor();
                        visualHeight = w.getVisualHeight();
                        break;
                    }
                }
            }

            double correctedDistance = distance * Math.cos(rayAngle-player.getRotation());
            if(hit){
                double projPlaneDist = (screenWidth/2.0) / Math.tan(FOV/2.0);
                int wallHeight = (int) (visualHeight*projPlaneDist / correctedDistance);
                int startY = (screenHeight / 2) - (wallHeight / 2);
                int endY = (screenHeight / 2) + (wallHeight / 2);

                g2d.setColor(hitColor);
                g2d.drawLine(i, startY, i, endY);
            }



        }


    }
}
