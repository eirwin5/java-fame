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
                    if (gp.gameState == GameState.PLAY) {
                        gp.gameState = GameState.PAUSE;
                    }
                    else if (gp.gameState == GameState.PAUSE) {
                        gp.gameState = GameState.PLAY;
                    }
                }
                case KeyEvent.VK_ENTER -> {
                    enterPressed = true;
                }
                case KeyEvent.VK_X -> {
                    if (gp.gameState == GameState.DIALOGUE) {
                        gp.gameState = GameState.PLAY;
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
