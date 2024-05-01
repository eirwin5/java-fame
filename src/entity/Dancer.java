package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.GameState;
import main.KeyHandler;

public class Dancer extends Entity {
    KeyHandler keyH;
    int screenX;
    int screenY;

    public Dancer(GamePanel gp, KeyHandler keyH_in) {
        super(gp);
        direction = Direction.DOWN;
        speed = 1;

        screenX = 100;
        screenY = 288 - gp.tileSize;
        keyH = keyH_in;
        getDancerImage();
    }

    public void getDancerImage() {
        try {
            up1 = ImageIO.read(new File("res/player/dancer_1.png"));
            up2 = ImageIO.read(new File("res/player/dancer_2.png"));
            down1 = ImageIO.read(new File("res/player/dancer_3.png"));
            down2 = ImageIO.read(new File("res/player/dancer_4.png"));
            right1 = ImageIO.read(new File("res/player/dancer_5.png"));
            right2 = ImageIO.read(new File("res/player/dancer_6.png"));
            left1 = ImageIO.read(new File("res/player/dancer_7.png"));
            left2 = ImageIO.read(new File("res/player/dancer_8.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

            if (keyH.upPressed) {
                direction = Direction.UP;
            } else if (keyH.downPressed) {
                direction = Direction.DOWN;
            } else if (keyH.rightPressed) {
                direction = Direction.RIGHT;
            } else if (keyH.leftPressed) {
                direction = Direction.LEFT;
            }
        }

        if (gp.getGameState() == GameState.PLAY && !collisionOn) {
            switch (direction) {
                case UP -> worldY -= speed;
                case DOWN -> worldY += speed;
                case LEFT -> worldX -= speed;
                case RIGHT -> worldX += speed;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = switch (direction) {
            case UP -> {
                if (spriteNum == 1)
                    yield up1;
                else
                    yield up2;
            }
            case DOWN -> {
                if (spriteNum == 1)
                    yield down1;
                else
                    yield down2;
            }
            case LEFT -> {
                if (spriteNum == 1)
                    yield left1;
                else
                    yield left2;
            }
            case RIGHT -> {
                if (spriteNum == 1)
                    yield right1;
                else
                    yield right2;
            }
        };

        g2.drawImage(image, screenX, screenY, gp.tileSize * 3, gp.tileSize * 3, null);
    }
}
