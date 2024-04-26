package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

// parent class of all object classes, similar to entity class or player class
public class SuperObject {
    public BufferedImage image;
    public String name;
    public ObjectType type;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (gp.checkInView(worldX, worldY)) {
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}
