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
    double playtime = 0;
    DecimalFormat format = new DecimalFormat("#0.00");

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
        if (gp.gameState != GameState.PAUSE) {
            playtime += (double) 1 / 60;
        }
        g2.drawString(STR."Time: \{format.format(playtime)}", gp.tileSize*11, 65);
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
        }
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
