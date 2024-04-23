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

        // TODO: figure these out
        screenX = 100;
        screenY = 288 - gp.tileSize;
        keyH = keyH_in;
        getDancerImage();
    }

    public void getDancerImage() {
        try {
            up1 = ImageIO.read(new File("res/player/girl_up_1.png"));
            up2 = ImageIO.read(new File("res/player/girl_up_2.png"));
            down1 = ImageIO.read(new File("res/player/girl_down_1.png"));
            down2 = ImageIO.read(new File("res/player/girl_down_2.png"));
            right1 = ImageIO.read(new File("res/player/girl_right_1.png"));
            right2 = ImageIO.read(new File("res/player/girl_right_2.png"));
            left1 = ImageIO.read(new File("res/player/girl_left_1.png"));
            left2 = ImageIO.read(new File("res/player/girl_left_2.png"));
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

        if (gp.gameState == GameState.PLAY && !collisionOn) {
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

        g2.drawImage(image, screenX, screenY, gp.tileSize * 2, gp.tileSize * 2, null);
    }
}
