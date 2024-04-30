package entity;

import main.GamePanel;
import main.GameState;
import main.KeyHandler;
import main.SoundType;
import object.ObjectType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Player extends Entity {

    public final int screenX, screenY;
    KeyHandler keyH;
    public boolean hasPompom = false;
    public boolean hasBow = false;
    public boolean hasUniform = false;
    public boolean hasMegaphone = false;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 9; // player's position on the world map
        worldY = gp.tileSize * 8;
        speed = 4;
        direction = Direction.DOWN;
    }

    public void getPlayerImage() {
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

        // TODO: FIGURE OUT ENTERPRESSED HERE OR NOT!
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed) {
            if (gp.gameState == GameState.PLAY) {
                // player image changes every 12 frames
                spriteCounter++;
                if (spriteCounter > 12) {
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

            // collision
            collisionOn = false;
            // tile collision
            gp.collisionChecker.checkTile(this);

            // object collision
            pickUpObject(gp.collisionChecker.checkObject(this, true));

            // npc collision
            int npcIndex = gp.collisionChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            if (gp.gameState == GameState.PLAY && !collisionOn) {
                switch (direction) {
                    case UP -> worldY -= speed;
                    case DOWN -> worldY += speed;
                    case LEFT -> worldX -= speed;
                    case RIGHT -> worldX += speed;
                }
            }
        }
    }

    public void pickUpObject(int index) {
        if (index != 999) {
            ObjectType objectType = gp.obj[index].type;
            switch (objectType) {
                case POMPOM:
                    hasPompom = true;
                    gp.playSoundEffect(SoundType.COIN.ordinal());
                    gp.obj[index] = null;
                    break;
                case UNIFORM:
                    hasUniform = true;
                    gp.playSoundEffect(SoundType.COIN.ordinal());
                    gp.obj[index] = null;
                    break;
                case MEGAPHONE:
                    hasMegaphone = true;
                    gp.playSoundEffect(SoundType.COIN.ordinal());
                    gp.obj[index] = null;
                    break;
                case BOW:
                    hasBow = true;
                    gp.playSoundEffect(SoundType.COIN.ordinal());
                    gp.obj[index] = null;
                    break;
                case HEART_BLANK:
                    break;
                case HEART_FULL:
                    break;
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

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

    public void interactNPC(int i) {
        if (i != 999) {
            if (gp.gameState == GameState.DIALOGUE && gp.keyH.enterPressed) {
                gp.npc[i].newDialogue();
                gp.npc[i].speak();
                gp.keyH.enterPressed = false;
            } else {
                gp.gameState = GameState.DIALOGUE;
                gp.npc[i].speak();
            }
        }
    }
}
