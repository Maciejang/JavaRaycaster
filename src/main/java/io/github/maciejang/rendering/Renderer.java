package io.github.maciejang.rendering;

import io.github.maciejang.entities.Player;
import io.github.maciejang.game.GameState;
import io.github.maciejang.mapObjects.Wall;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Renderer {

    private int MAX_DISTACE = 800;
    private double STEP = 2.0;

    private Color shadeRay(Color clr, double distance){
        int c = (int) (255 * (1-distance/(MAX_DISTACE/2)));
        int r = Math.min(255,clr.getRed()+c);
        int g = Math.min(255,clr.getGreen()+c);
        int b = Math.max(Math.min(255,c),100);
         return new Color(0,0,b);
    }

    public void render(Graphics2D g2d, GameState gameState, int screenWidth, int screenHeight){

        Player player = gameState.getPlayer();
        double FOV = Math.toRadians(player.getFOV());

        int hitStreak = 0;
        for(int i=0; i < screenWidth;i++){
            double rayAngle = (player.getRotation() - (FOV/2) + ((double)i/screenWidth) * FOV);

            double distance = 0;
            boolean hit = false;
            Color hitColor = Color.black;
            int visualHeight = 0;

            g2d.setColor(Color.WHITE);

            hitStreak++;

            while(!hit && distance < MAX_DISTACE){
                distance += STEP;
                int rayX = (int) (player.getX() + distance * Math.cos(rayAngle));
                int rayY = (int) (player.getY() + distance * Math.sin(rayAngle));

                for(Wall w : gameState.getWalls()){

                    if(w.getX() <= rayX && rayX <= w.getX() + w.getWidth() &&
                            w.getY() <= rayY && rayY <= w.getY() + w.getHeight()) {
                        hit = true;

//                        hitColor = shadeRay(w.getColor(),distance);

                        hitColor = w.getColor();

                        visualHeight = w.getVisualHeight();
                        break;
                    }
                }

            }

            double correctedDistance = distance * Math.cos(rayAngle-player.getRotation());
            if(hit){
                hitStreak++;
                double projPlaneDist = (screenWidth/2.0) / Math.tan(FOV/2.0);
                int wallHeight = (int) (visualHeight * projPlaneDist / correctedDistance);
                int startY = (screenHeight / 2) + (wallHeight / 2);
                int endY = (screenHeight / 2) - (wallHeight / 2);

                g2d.setColor(hitColor);
                g2d.drawLine(i, startY, i, endY);

                g2d.setColor(Color.WHITE);
            }else{
                hitStreak = 0;
            }
        }


    }
}
