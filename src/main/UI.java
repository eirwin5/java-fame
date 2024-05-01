package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

public class UI {
    GamePanel gp;
    Font arial_80;
    Font arial_40;
    Graphics2D g2;

    BufferedImage pompomImage;
    BufferedImage pompomGrayImage;
    BufferedImage bowImage;
    BufferedImage bowGrayImage;
    BufferedImage uniformImage;
    BufferedImage uniformGrayImage;
    BufferedImage megaphoneImage;
    BufferedImage megaphoneGrayImage;
    BufferedImage heartImage;
    BufferedImage heartBlankImage;

    public String currentDialogue;
    public int commandNum = 0;
    double playtime = 0;
    DecimalFormat format = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80 = new Font("Arial", Font.PLAIN, 80);
        setupImages();
    }

    private void setupImages() {
        try {
            pompomImage = ImageIO.read(new File("res/object/pompom.png"));
            pompomGrayImage = ImageIO.read(new File("res/object/pompom_gray.png"));
            bowImage = ImageIO.read(new File("res/object/bow.png"));
            bowGrayImage = ImageIO.read(new File("res/object/bow_gray.png"));
            uniformImage = ImageIO.read(new File("res/object/uniform.png"));
            uniformGrayImage = ImageIO.read(new File("res/object/uniform_gray.png"));
            megaphoneImage = ImageIO.read(new File("res/object/megaphone.png"));
            megaphoneGrayImage = ImageIO.read(new File("res/object/megaphone_gray.png"));
            heartBlankImage = ImageIO.read(new File("res/object/heart_blank.png"));
            heartImage = ImageIO.read(new File("res/object/heart_full.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(arial_80);
        g2.setColor(Color.white);

        switch (gp.gameState) {
            case PAUSE:
                drawPauseScreen();
                gp.stopMusic();
                break;
            case PLAY:
                if (gp.treasureHunt) {
                    drawPlayScreen();
                }
                // gp.music.play();
                break;
            case DIALOGUE:
                drawDialogueScreen();
                break;
            case DIALOGUE_OPTIONS:
                drawOptions();
                break;
            case TITLE:
                drawTitleScreen();
                break;
            case MINI_GAME:
                drawMiniGameScreen();
                break;
            case GAME_OVER:
                drawGameOverScreen();
                break;
        }
    }

    public void drawGameOverScreen() {
        // title
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "Game Over";
        int x = getXForCenterText(text);
        int y = gp.tileSize * 3;
        g2.drawString(text, x, y);

        // pic
        x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
        y += gp.tileSize * 2;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

        // menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        text = "Try again";
        x = getXForCenterText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "Quit";
        x = getXForCenterText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    public void drawMiniGameScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));
        g2.setColor(Color.white);
        g2.drawString(STR."Score: \{gp.arrowsCollected}", gp.tileSize / 2, 65);

        if (gp.arrowsMissed == 0) {
            g2.drawImage(heartImage, gp.screenWidth - 16 - gp.tileSize, gp.tileSize / 2, gp.tileSize, gp.tileSize,
                    null);
            g2.drawImage(heartImage, gp.screenWidth - 16 - (gp.tileSize * 2), gp.tileSize / 2, gp.tileSize,
                    gp.tileSize,
                    null);
            g2.drawImage(heartImage, gp.screenWidth - 16 - (gp.tileSize * 3), gp.tileSize / 2, gp.tileSize,
                    gp.tileSize,
                    null);

        }
        if (gp.arrowsMissed == 1) {
            g2.drawImage(heartBlankImage, gp.screenWidth - 16 - gp.tileSize, gp.tileSize / 2, gp.tileSize, gp.tileSize,
                    null);
            g2.drawImage(heartImage, gp.screenWidth - 16 - (gp.tileSize * 2), gp.tileSize / 2, gp.tileSize,
                    gp.tileSize,
                    null);
            g2.drawImage(heartImage, gp.screenWidth - 16 - (gp.tileSize * 3), gp.tileSize / 2, gp.tileSize,
                    gp.tileSize,
                    null);
        }
        if (gp.arrowsMissed == 2) {
            g2.drawImage(heartBlankImage, gp.screenWidth - 16 - gp.tileSize, gp.tileSize / 2, gp.tileSize, gp.tileSize,
                    null);
            g2.drawImage(heartBlankImage, gp.screenWidth - 16 - (gp.tileSize * 2), gp.tileSize / 2, gp.tileSize,
                    gp.tileSize,
                    null);
            g2.drawImage(heartImage, gp.screenWidth - 16 - (gp.tileSize * 3), gp.tileSize / 2, gp.tileSize,
                    gp.tileSize,
                    null);
        }
        if (gp.arrowsMissed == 3) {
            g2.drawImage(heartBlankImage, gp.screenWidth - 16 - gp.tileSize, gp.tileSize / 2, gp.tileSize, gp.tileSize,
                    null);
            g2.drawImage(heartBlankImage, gp.screenWidth - 16 - (gp.tileSize * 2), gp.tileSize / 2, gp.tileSize,
                    gp.tileSize,
                    null);
            g2.drawImage(heartBlankImage, gp.screenWidth - 16 - (gp.tileSize * 3), gp.tileSize / 2, gp.tileSize,
                    gp.tileSize,
                    null);
        }
    }

    public void drawOptions() {
        drawDialogueScreen();
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
        g2.setColor(Color.white);
        String optionA = "Not yet...";
        String optionB = "Ready!";

        int x = gp.tileSize;
        int y = gp.tileSize * 8;
        int width = gp.screenWidth - (gp.tileSize * 10);
        int height = gp.tileSize * 2;

        drawSubwindow(x + (gp.tileSize / 2), y, width, height);
        g2.drawString(optionA, x + gp.tileSize, y + gp.tileSize);

        x = x * 8 + (gp.tileSize / 2);

        drawSubwindow(x, y, width, height);
        g2.drawString(optionB, x + gp.tileSize, y + gp.tileSize);
    }

    public void drawPlayScreen() {
        g2.setFont(arial_40);

        g2.setColor(Color.white);
        int gap = 60;
        int x = gp.screenWidth - gap;
        int y = gp.tileSize;

        int outlineX = x - 4;
        int outlineY = 35;
        int outlineWidth = 55;
        int outlineHeight = gp.tileSize * 4 + 55;
        int arc = 30;

        g2.fillRoundRect(outlineX, outlineY, outlineWidth, outlineHeight, arc, arc);
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(outlineX, outlineY, outlineWidth, outlineHeight, arc, arc);

        g2.drawImage(gp.player.hasPompom ? pompomImage : pompomGrayImage, x, y, gp.tileSize,
                gp.tileSize, null);
        y += gap;

        g2.drawImage(gp.player.hasUniform ? uniformImage : uniformGrayImage, x, y, gp.tileSize,
                gp.tileSize, null);
        y += gap + 5;

        g2.drawImage(gp.player.hasBow ? bowImage : bowGrayImage, x, y, gp.tileSize,
                gp.tileSize, null);
        y += gap - 5;

        g2.drawImage(gp.player.hasMegaphone ? megaphoneImage : megaphoneGrayImage, x, y, gp.tileSize,
                gp.tileSize, null);

        if (gp.gameState != GameState.PAUSE) {
            playtime += (double) 1 / 60;
            if (playtime > 120) {
                gp.gameState = GameState.GAME_OVER;
            }
        }
        g2.drawString(STR."Time: \{format.format(playtime)}", gp.tileSize / 2, 65);

    }

    public void drawTitleScreen() {

        // title
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "Cheer rush game";
        int x = getXForCenterText(text);
        int y = gp.tileSize * 3;

        // shadow
        g2.setColor(Color.gray);
        g2.drawString(text, x + 5, y + 5);
        g2.setColor(Color.PINK);
        g2.drawString(text, x, y);

        // pic
        x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
        y += gp.tileSize * 2;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

        // menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        text = "New Game";
        x = getXForCenterText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "Quit";
        x = getXForCenterText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    public void drawDialogueScreen() {
        // window
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;

        drawSubwindow(x, y, width, height);

        g2.setFont(new Font("SuperBoys", Font.PLAIN, 28));
        x += gp.tileSize;
        y += gp.tileSize;

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawSubwindow(int x, int y, int width, int height) {
        Color bgColor = new Color(0, 0, 0, 220);
        g2.setColor(bgColor);
        g2.fillRoundRect(x, y, width, height, 15, 35);

        Color frameColor = new Color(255, 255, 255);
        g2.setColor(frameColor);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);

    }

    public void drawPauseScreen() {
        String text = "PAUSED";
        int x = getXForCenterText(text), y = gp.screenHeight / 2;
        g2.drawString(text, x, y);
    }

    public int getXForCenterText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth / 2 - length / 2;
    }
}
