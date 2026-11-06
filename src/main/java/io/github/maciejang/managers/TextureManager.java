package io.github.maciejang.managers;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Map;

public class TextureManager {
    private static final Map<Integer, BufferedImage> textures = new HashMap<>();


    public static void loadTexture(int id, String path) {
        try {
            BufferedImage img = ImageIO.read(TextureManager.class.getResourceAsStream(path));
            textures.put(id, img);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage getTexture(int id) {

        try{
            BufferedImage img = textures.get(id);
            return img;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

}
