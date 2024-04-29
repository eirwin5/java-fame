package entity;

import main.GamePanel;
import main.GameState;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.Random;

public class Npc2 extends Entity {
    public Npc2(GamePanel gp) {
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
            up1 = ImageIO.read(new File("res/npc/oldman_up_1.png"));
            up2 = ImageIO.read(new File("res/npc/oldman_up_2.png"));
            down1 = ImageIO.read(new File("res/npc/oldman_down_1.png"));
            down2 = ImageIO.read(new File("res/npc/oldman_down_2.png"));
            right1 = ImageIO.read(new File("res/npc/oldman_right_1.png"));
            right2 = ImageIO.read(new File("res/npc/oldman_right_2.png"));
            left1 = ImageIO.read(new File("res/npc/oldman_left_1.png"));
            left2 = ImageIO.read(new File("res/npc/oldman_left_2.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDialogue() {
        dialogues[0] = "This text is different!";
        dialogues[1] = "Test";
        dialogues[2] = "Test again";
        dialogues[3] = "One more time";
    }

    public void speak() {
        super.speak();
    }

    public void newDialogue() {
        if (dialogueIndex == 2) {
            gp.gameState = GameState.DIALOGUE_OPTIONS;
        }
        dialogueIndex++;
    }
}
