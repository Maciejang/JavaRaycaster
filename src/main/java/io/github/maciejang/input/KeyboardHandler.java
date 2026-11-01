package io.github.maciejang.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class KeyboardHandler implements KeyListener {

    private static boolean[] keys = new boolean[1024];

    private static HashMap<String, Integer> keyHash = new HashMap<>(Map.of(
            "w",87,
        "a",65,
        "s",83,
        "d",68,
        "arrowUp",38,
        "arrowLeft", 37,
        "arrowDown",40,
        "arrowRight",39
    ));



    private void printKey(){
        int index = 0;
        for(boolean k : keys){
            if(!k){
                index++;
                continue;
            }
            index++;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        printKey();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
        printKey();
    }


    public static boolean inputUsed(String key){
        return keys[keyHash.get(key)];
    }
}
