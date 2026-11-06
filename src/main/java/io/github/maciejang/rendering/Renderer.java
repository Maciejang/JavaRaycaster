package io.github.maciejang.rendering;

import io.github.maciejang.entities.Player;
import io.github.maciejang.game.GameState;
import io.github.maciejang.managers.TextureManager;
import io.github.maciejang.mapObjects.Wall;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Renderer {

    private final int MAX_DISTANCE = 1000;
    private final double STEP = 2.0;
    private final int LINE_WIDTH = 10;

    private Color shadeRay(Color clr, double distance) {
        double factor = Math.max(0.0, 1.0 - (distance / (MAX_DISTANCE )));
        int r = (int) (clr.getRed() * factor);
        int g = (int) (clr.getGreen() * factor);
        int b = (int) (clr.getBlue() * factor);
        return new Color(Math.min(255, r), Math.min(255, g), Math.min(255, b));
    }
    public void render(Graphics2D g2d, GameState gameState, int screenWidth, int screenHeight){

        Player player = gameState.getPlayer();
        double FOV = Math.toRadians(player.getFOV());

        int[] groundSky = gameState.getGroundSkyID();

        g2d.setColor(new Color(0,170,255));
        g2d.fillRect(0,0,screenWidth,screenHeight);
        g2d.setColor(Color.darkGray);
        g2d.fillRect(0,screenHeight-(screenHeight/2),screenWidth,screenHeight/2);


        int hitCount = 0;
        int hitStreak = 0;

        boolean hitC = false;
        for(int i=0; i < screenWidth;i+=LINE_WIDTH){
            double rayAngle = (player.getRotation() - (FOV/2) + ((double)i/(screenWidth)) * FOV);

            double distance = 0;
            boolean hit = false;
            Color hitColor = Color.black;
            int visualHeight = 0;

            g2d.setColor(Color.WHITE);

            hitStreak++;

            while(!hit && distance < MAX_DISTANCE){
                distance += STEP;
                int rayX = (int) (player.getX() + distance * Math.cos(rayAngle));
                int rayY = (int) (player.getY() + distance * Math.sin(rayAngle));

                for(Wall w : gameState.getWalls()){

                    if(w.getX() <= rayX && rayX <= w.getX() + w.getWidth() &&
                            w.getY() <= rayY && rayY <= w.getY() + w.getHeight()) {
                        hit = true;

                        hitColor = shadeRay(w.getColor(),distance);

                        visualHeight = w.getVisualHeight();
                        break;
                    }
                }

            }



            if(hit){
                double correctedDistance = distance * Math.cos(rayAngle-player.getRotation());
                if (correctedDistance < 1) correctedDistance = 1;
                hitStreak++;
                double projPlaneDist = (screenWidth/2.0) / Math.tan(FOV/2.0);
                int wallHeight = (int) (visualHeight * projPlaneDist / correctedDistance);
                int startY = (screenHeight / 2) + (wallHeight / 2);
                int endY = (screenHeight / 2) - (wallHeight / 2);


//                if(hitC){
//                    g2d.setColor(Color.BLUE);
//                    hitC = false;
//                }else{
//                    g2d.setColor(Color.RED);
//                    hitC = true;
//                }

                g2d.setColor(hitColor);

                g2d.setStroke(new BasicStroke(LINE_WIDTH));
                g2d.drawLine(i, startY, i, endY);

                g2d.setColor(Color.WHITE);

                hitCount++;
            }else{
                hitStreak = 0;
            }
        }

        g2d.drawString("Lines per render: " + hitCount, 20,20);


    }
}
