package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (gp.gameState == GameState.PLAY) {
            switch (code) {
                case KeyEvent.VK_W, KeyEvent.VK_UP -> {
                    upPressed = true;
                }
                case KeyEvent.VK_S, KeyEvent.VK_DOWN -> {
                    downPressed = true;
                }
                case KeyEvent.VK_A, KeyEvent.VK_LEFT -> {
                    leftPressed = true;
                }
                case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> {
                    rightPressed = true;
                }
                case KeyEvent.VK_P -> {
                    gp.gameState = GameState.PAUSE;
                }
                case KeyEvent.VK_ENTER -> {
                    enterPressed = true;
                }
            }
        }
        else if (gp.gameState == GameState.PAUSE) {
            if (code == KeyEvent.VK_P) gp.gameState = GameState.PLAY;
        }
        else if (gp.gameState == GameState.DIALOGUE) {
            if (code == KeyEvent.VK_X) gp.gameState = GameState.PLAY;
        }
        else if (gp.gameState == GameState.TITLE) {
            switch (code) {
                case KeyEvent.VK_W, KeyEvent.VK_UP -> {
                    if (gp.ui.commandNum > 0) {
                        gp.ui.commandNum--;
                    }
                }
                case KeyEvent.VK_S, KeyEvent.VK_DOWN -> {
                    if (gp.ui.commandNum < 1) {
                        gp.ui.commandNum++;
                    }
                }
                case KeyEvent.VK_ENTER -> {
                    if (gp.ui.commandNum == 0) {
                        gp.gameState = GameState.PLAY;
                    } else if (gp.ui.commandNum == 1) System.exit(0);
                }
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_W, KeyEvent.VK_UP -> {
                upPressed = false;
            }
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> {
                downPressed = false;
            }
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> {
                leftPressed = false;
            }
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> {
                rightPressed = false;
            }
//            case KeyEvent.VK_ENTER -> enterPressed = false;
        }
    }
}
