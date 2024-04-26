package minigame;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import main.GamePanel;

public class HitBoxManager {
    GamePanel gp;
    public HitBox[] hitBoxes;

    public HitBoxManager(GamePanel gp) {
        this.gp = gp;
        hitBoxes = new HitBox[4];
        loadBoxes();
    }

    public void loadBoxes() {
        int keys_x_center = 505;

        int x = keys_x_center - gp.tileSize;
        int y = 144 - gp.tileSize;
        hitBoxes[Direction.UP.ordinal()] = new HitBox(x, y, Direction.UP, "res/shapes/UpHB.png");

        y = 432 - gp.tileSize;
        hitBoxes[Direction.DOWN.ordinal()] = new HitBox(x, y, Direction.DOWN, "res/shapes/DownHB.png");

        y = 288 - gp.tileSize;
        x = keys_x_center + (gp.tileSize * 2);
        hitBoxes[Direction.RIGHT.ordinal()] = new HitBox(x, y, Direction.RIGHT, "res/shapes/RightHB.png");

        x = keys_x_center - (gp.tileSize * 4);
        hitBoxes[Direction.LEFT.ordinal()] = new HitBox(x, y, Direction.LEFT, "res/shapes/LeftHB.png");
    }

    public Rectangle getHitBoxSolidArea(Direction dir) {
        return hitBoxes[dir.ordinal()].solidArea;
    }

    public void draw(Graphics2D g2) {
        for (HitBox h : hitBoxes) {
            g2.drawImage(h.image, h.x, h.y, gp.tileSize * 3, gp.tileSize * 3, null);
        }
    }
}
