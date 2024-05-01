package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    GamePanel gp;
    public int worldX, worldY, speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public Direction direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    String[] dialogues = new String[10];
    int dialogueIndex = 0;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() {
    } // used by npc

    public void speak() {
        gp.ui.currentDialogue = dialogues[dialogueIndex];

        switch (gp.player.direction) {
            case UP -> direction = Direction.DOWN;
            case DOWN -> direction = Direction.UP;
            case LEFT -> direction = Direction.RIGHT;
            case RIGHT -> direction = Direction.LEFT;
        }

    } // used by npc

    public void newDialogue() {
        dialogueIndex++;
        gp.playSpeakSound();
    }

    public void setNewDialogue() {
    }

    public void update() {
        setAction();
        collisionOn = false;
        gp.collisionChecker.checkTile(this);
        gp.collisionChecker.checkObject(this, false);
        gp.collisionChecker.checkPlayer(this);

        if (!collisionOn) {
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
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (gp.checkInView(worldX, worldY)) {
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}
