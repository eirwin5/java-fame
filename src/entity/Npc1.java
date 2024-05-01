package entity;

import main.GamePanel;
import main.GameState;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.Random;

public class Npc1 extends Entity {
    public Npc1(GamePanel gp) {
        super(gp);

        direction = Direction.DOWN;
        speed = 0;
        getNpcImage();
        setDialogue();
    }

    public void setAction() {
        actionLockCounter++;

        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // pick a random number from 1 to 100

            if (i <= 25) {
                direction = Direction.UP;
            }
            if (i > 25 && i <= 50) {
                direction = Direction.DOWN;
            }
            if (i > 50 && i <= 75) {
                direction = Direction.LEFT;
            }
            if (i > 75 && i <= 100) {
                direction = Direction.RIGHT;
            }
            actionLockCounter = 0;
        }
    }

    public void getNpcImage() {
        try {
            up1 = ImageIO.read(new File("res/npc/cheerleader_up_1.png"));
            up2 = ImageIO.read(new File("res/npc/cheerleader_up_2.png"));
            down1 = ImageIO.read(new File("res/npc/cheerleader_down_1.png"));
            down2 = ImageIO.read(new File("res/npc/cheerleader_down_2.png"));
            right1 = ImageIO.read(new File("res/npc/cheerleader_right_1.png"));
            right2 = ImageIO.read(new File("res/npc/cheerleader_right_2.png"));
            left1 = ImageIO.read(new File("res/npc/cheerleader_left_1.png"));
            left2 = ImageIO.read(new File("res/npc/cheerleader_left_2.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDialogue() {
        dialogues[0] = "Hello! You must be the new girl!\nI'm Tasha, nice to meet you.";
        dialogues[1] = "Oh you want to join the cheer team?\nThat's great. Just go to--";
        dialogues[2] = "Oh no! I just realized I was supposed\nto bring Coach Tammy the equipment\nfor tryouts! I lost it all in the\nwoods nearby!";
        dialogues[3] = "I'm so busy over here. Would\nyou be able to go find them and bring\nthem to Coach? It would\nprobably give you a leg up in tryouts.";
        dialogues[4] = "Thank you so much. She's at the school.\n After you have everything, follow\nthe tiled path to find her. ";
        dialogues[5] = "Please hurry! You have 2 minutes\nto get them to her.";
    }

    public void setNewDialogue() {
        dialogues[0] = "Did you get them to her yet?\nIt's very important!";
        dialogues[1] = null;
        dialogues[2] = null;
        dialogues[3] = null;
        dialogues[4] = null;
        dialogues[5] = null;
    }

    public void speak() {
        if (dialogues[dialogueIndex] == null) {
            gp.gameState = GameState.PLAY; // automatically exit the dialogue
            gp.treasureHunt = true;
            setNewDialogue();
            dialogueIndex = 0;
        }
        super.speak();
    }

    public void newDialogue() {
        dialogueIndex++;
    }
}
