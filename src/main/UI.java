package main;

import object.ObjectKey;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Font arial_80;
    Font arial_40;
    Graphics2D g2;
    BufferedImage keyImage;
    public String currentDialogue;
//    double playtime = 0;
//    DecimalFormat format = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80 = new Font("Arial", Font.PLAIN, 80);
        ObjectKey key = new ObjectKey();
        keyImage = key.image;
    }
    public void draw(Graphics2D g2) {
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
        g2.drawString(STR."x \{gp.player.hasKey}", 74, 65);
//        if (gp.gameState != GameState.PAUSE) {
//            playtime += (double) 1 / 60;
//        }
//        g2.drawString(STR."Time: \{format.format(playtime)}", gp.tileSize*11, 65);
        this.g2 = g2;
        g2.setFont(arial_80);
        g2.setColor(Color.white);

        switch(gp.gameState) {
            case PAUSE:
                drawPauseScreen();
                gp.stopMusic();
                break;
            case PLAY:
                // TODO: restart playing music
                break;
            case DIALOGUE:
                drawDialogueScreen();
                break;
        }
    }

    public void drawDialogueScreen() {
        // window
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4);
        int height = gp.tileSize*4;

        drawSubwindow(x,y,width,height);

        g2.setFont(new Font("SuperBoys", Font.PLAIN, 28));
        x+= gp.tileSize;
        y += gp.tileSize;

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }

//        g2.drawString(currentDialogue, x, y);
    }

    public void drawSubwindow(int x, int y, int width, int height) {
        Color bgColor = new Color(0,0,0,220);
        g2.setColor(bgColor);
        g2.fillRoundRect(x,y,width,height,15, 35);

        Color frameColor = new Color (255,255,255);
        g2.setColor(frameColor);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5,y+5,width-10,height-10, 25,25);

    }

    public void drawPauseScreen() {
        String text = "PAUSED";
        int x = getXForCenterText(text), y = gp.screenHeight/2;
        g2.drawString(text, x, y);
    }

    public int getXForCenterText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth/2 - length/2;
    }
}
