package minigame;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;

public class Arrows {
    public int screenX;
    public int screenY;
    public int speed;
    public int time;
    public boolean collisionOn = false;
    KeyHandler keyH;
    GamePanel gp;
    Direction direction;
    HitBoxManager hitBoxManager;
    BufferedImage img;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);

    public Arrows(KeyHandler keyH_in, GamePanel gp_in, HitBoxManager hitBoxManager_in) {
        keyH = keyH_in;
        gp = gp_in;
        hitBoxManager = hitBoxManager_in;
        screenY = -4 * gp.tileSize;
        direction = getRandomDirection();

        try {
            switch (direction) {
                case UP:
                    this.screenX = 505 - gp.tileSize;
                    this.img = ImageIO.read(new File("res/shapes/upFall.png"));
                    break;
                case DOWN:
                    this.screenX = 505 - gp.tileSize;
                    this.img = ImageIO.read(new File("res/shapes/downFall.png"));
                    break;
                case RIGHT:
                    this.screenX = 505 + (gp.tileSize * 2);
                    this.img = ImageIO.read(new File("res/shapes/rightFall.png"));
                    break;
                case LEFT:
                    this.screenX = 505 - (gp.tileSize * 4);
                    this.img = ImageIO.read(new File("res/shapes/leftFall.png"));
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Random rand = new Random();
        speed = rand.nextInt((7 - 5) + 1) + 5;

        time = rand.nextInt((100000 - 100) + 1) + 100;
        System.out.println(time);
    }

    private Direction getRandomDirection() {
        Random random = new Random();
        int randomNumber = random.nextInt(4) + 1;
        switch (randomNumber) {
            case 1:
                return Direction.UP;
            case 2:
                return Direction.DOWN;
            case 3:
                return Direction.LEFT;
            case 4:
                return Direction.RIGHT;
            default:
                return Direction.LEFT;
        }
    }

    public void update() {
        if (direction == Direction.UP && keyH.upPressed) {
            if (this.solidArea.intersects(hitBoxManager.getHitBoxSolidArea(Direction.UP))) {
                this.screenY = -4 * gp.tileSize;
                gp.arrowsCollected++;
            }
        } else if (direction == Direction.DOWN && keyH.downPressed) {
            if (this.solidArea.intersects(hitBoxManager.getHitBoxSolidArea(Direction.DOWN))) {
                this.screenY = -4 * gp.tileSize;
                gp.arrowsCollected++;
            }
        } else if (direction == Direction.RIGHT && keyH.rightPressed) {
            if (this.solidArea.intersects(hitBoxManager.getHitBoxSolidArea(Direction.RIGHT))) {
                this.screenY = -4 * gp.tileSize;
                gp.arrowsCollected++;

            }
        } else if (direction == Direction.LEFT && keyH.leftPressed) {
            if (this.solidArea.intersects(hitBoxManager.getHitBoxSolidArea(Direction.LEFT))) {
                this.screenY = -4 * gp.tileSize;
                gp.arrowsCollected++;
            }
        }
        screenY += speed;
        this.solidArea.x = screenX;
        this.solidArea.y = screenY;

        if (this.screenY > gp.screenHeight) {
            this.screenY = -4 * gp.tileSize;
            gp.arrowsMissed++;
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(this.img, this.screenX, this.screenY, gp.tileSize * 3, gp.tileSize * 3, null);
    }
}
