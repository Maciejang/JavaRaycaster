package io.github.maciejang;

import io.github.maciejang.game.GameLoop;
import io.github.maciejang.game.GameState;
import io.github.maciejang.input.KeyboardHandler;
import io.github.maciejang.ui.GamePanel;
import io.github.maciejang.ui.MainFrame;

import javax.swing.*;

public class Main {



    public static void main(String[] args) {

        SwingUtilities.invokeLater(()->{
            MainFrame mainFrame = new MainFrame();
            KeyboardHandler keyboardHandler = new KeyboardHandler();
            mainFrame.addKeyListener(keyboardHandler);

            GamePanel gamePanel = new GamePanel();
            mainFrame.add(gamePanel);

            GameState gameState = new GameState();
            GameLoop gameLoop = new GameLoop(gamePanel,gameState);


            mainFrame.pack();
            mainFrame.setVisible(true);
            gamePanel.setVisible(true);
            gameLoop.start();
        });
    }
}
