package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Objects;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = Direction.DOWN;
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(new File("res/player/boy_up_1.png"));
            up2 = ImageIO.read(new File("res/player/boy_up_2.png"));
            down1 = ImageIO.read(new File("res/player/boy_down_1.png"));
            down2 = ImageIO.read(new File("res/player/boy_down_2.png"));
            right1 = ImageIO.read(new File("res/player/boy_right_1.png"));
            right2 = ImageIO.read(new File("res/player/boy_right_2.png"));
            left1 = ImageIO.read(new File("res/player/boy_left_1.png"));
            left2 = ImageIO.read(new File("res/player/boy_left_2.png"));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            // player image changes every 12 frames
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                }
                else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

        if (keyH.upPressed) {
            direction = Direction.UP;
            y -= speed;
        }
        else if (keyH.downPressed) {
            direction = Direction.DOWN;
            y += speed;
        }
        else if (keyH.rightPressed) {
            direction = Direction.RIGHT;
            x += speed;
        }
        else if (keyH.leftPressed) {
            direction = Direction.LEFT;
            x -= speed;
        }
    }

    public void draw(Graphics2D g2) {
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = switch (direction) {
            case UP -> {
                if (spriteNum == 1) yield up1;
                else yield up2;
            }
            case DOWN -> {
                if (spriteNum == 1) yield down1;
                else yield down2;
            }
            case LEFT -> {
                if (spriteNum == 1) yield left1;
                else yield left2;
            }
            case RIGHT -> {
                if (spriteNum == 1) yield right1;
                else yield right2;
            }
        };

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
