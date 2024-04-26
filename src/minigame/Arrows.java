package minigame;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;

import main.CollisionChecker;
import main.GamePanel;
import main.KeyHandler;

public class Arrows {
    public int screenX;
    public int screenY;
    public int speed;
    public boolean collisionOn = false;
    KeyHandler keyH;
    GamePanel gp;
    Direction direction;
    CollisionChecker collisionChecker;
    BufferedImage img;

    public Arrows(KeyHandler keyH_in, GamePanel gp_in, CollisionChecker collisionChecker_in) {
        keyH = keyH_in;
        gp = gp_in;
        collisionChecker = collisionChecker_in;
        screenY = -2 * gp.tileSize;
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
        screenY += speed;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(this.img, this.screenX, this.screenY, gp.tileSize * 3, gp.tileSize * 3, null);
    }
}
