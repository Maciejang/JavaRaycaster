package io.github.maciejang.ui;

import javax.swing.*;
import java.awt.*;

import io.github.maciejang.game.GameState;
import io.github.maciejang.input.KeyboardHandler;
import io.github.maciejang.rendering.Renderer;

public class GamePanel extends JPanel {

    private final Renderer renderer;
    private final GameState gameState;

    public GamePanel(){
        renderer = new Renderer();
        gameState = new GameState();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        renderer.render(g2d,gameState,getWidth(),getHeight());

        g2d.dispose();
    }
}
