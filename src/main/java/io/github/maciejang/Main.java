package io.github.maciejang;

import io.github.maciejang.game.GameLoop;
import io.github.maciejang.game.GameState;
import io.github.maciejang.input.KeyboardHandler;
import io.github.maciejang.ui.GamePanel;
import io.github.maciejang.ui.MainFrame;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {



    public static void main(String[] args) {

        SwingUtilities.invokeLater(()->{
            MainFrame mainFrame = new MainFrame();
            KeyboardHandler keyboardHandler = new KeyboardHandler();
            mainFrame.addKeyListener(keyboardHandler);

            GameState gameState = new GameState();
            GamePanel gamePanel = new GamePanel(gameState);
            mainFrame.add(gamePanel);


            GameLoop gameLoop = new GameLoop(gamePanel,gameState);


            mainFrame.pack();
            mainFrame.setVisible(true);
            gamePanel.setVisible(true);
            gameLoop.start();

            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);

                    gameLoop.stop();

                    mainFrame.dispose();

                    System.exit(0);
                }
            });
        });
    }
}
