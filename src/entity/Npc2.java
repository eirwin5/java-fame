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
            up1 = ImageIO.read(new File("res/npc/coach_up_1.png"));
            up2 = ImageIO.read(new File("res/npc/coach_up_2.png"));
            down1 = ImageIO.read(new File("res/npc/coach_down_1.png"));
            down2 = ImageIO.read(new File("res/npc/coach_down_2.png"));
            right1 = ImageIO.read(new File("res/npc/coach_right_1.png"));
            right2 = ImageIO.read(new File("res/npc/coach_right_2.png"));
            left1 = ImageIO.read(new File("res/npc/coach_left_1.png"));
            left2 = ImageIO.read(new File("res/npc/coach_left_2.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDialogue() {
        dialogues[0] = "I'm sorry I'm too busy to talk right now.";
    }

    public void setNewDialogue() {
        dialogues[0] = "Oh my gosh that's exactly what I've\nbeen looking for! Thank you so\nmuch for getting this equipment\nto me!";
        dialogues[1] = "Are you new around here? I could\nreally use someone like you on my team.";
        dialogues[2] = "Are you free to tryout right now?\n It's a just quick dance routine.";
        dialogues[3] = "Great! The rules are simple:\nYou have to get 30 moves correct.\nIf you miss a move, you lose a point.\nLose more than 3 points and you're cut.";
    }

    public void speak() {
        if (dialogues[dialogueIndex] == null) {
            if (gp.tryouts) {
                gp.setGameState(GameState.MINI_GAME);
                gp.timeMiniGameStarted = System.currentTimeMillis();
            } else {
                gp.setGameState(GameState.PLAY);
                dialogueIndex = 0;
            }
        }
        super.speak();
    }

    public void newDialogue() {
        dialogueIndex++;
    }
}
