package io.github.maciejang.game;

import io.github.maciejang.ui.GamePanel;

public class GameLoop implements Runnable{
    private Thread gameThread;
    private boolean running = false;

    private final double FPS = 60;
    private final double UPS = 1_000_000_000/FPS;

    private final GameState gameState;
    private final GamePanel gamePanel;

    public GameLoop(GamePanel gamePanel, GameState gameState){
        this.gamePanel = gamePanel;
        this.gameState = gameState;
    }

    public synchronized void start(){
        if(running){
            return;
        }
        running = true;
        gameThread = new Thread(this, "Game loop thread");
        gameThread.start();
    }

    public synchronized void stop(){
        if(!running){
            return;
        }
        running = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double accumulator = 0.0;

        while(running){
             long now = System.nanoTime();
             long elapsed = now - lastTime;
             lastTime = now;

             accumulator += elapsed;

             while(accumulator > UPS){
                 gameState.update();
                 gamePanel.repaint();
                 accumulator -= UPS;
             }
            try{
                Thread.sleep(1);
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }

    }
}
