package minigame;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class HitBox {
    public int x;
    public int y;
    public Direction direction;
    public BufferedImage image;
    public boolean collision = false;
    public Rectangle solidArea;

    public HitBox(int x_in, int y_in, Direction direction_in, String image_addr) {
        x = x_in;
        y = y_in;
        direction = direction_in;
        solidArea = new Rectangle(x_in, y_in, 48, 48);

        try {
            image = ImageIO.read(new File(image_addr));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}