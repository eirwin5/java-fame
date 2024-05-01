package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

public class UI {
    GamePanel gp;
    Font superCerealFont;
    Font poppinsFont;

    Color lightPink;
    Color lightBlue;
    Color transparentLightBlue;
    Color purple;
    Color lightPurple;
    Color darkPink;
    Color teal;

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
    public double playtime = 0;
    DecimalFormat format = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;
        setupImages();
        setupFonts();
        setupColors();
    }

    private void setupColors() {
        int opacity = 174;
        transparentLightBlue = new Color(174, 234, 241, opacity);
        lightBlue = new Color(174, 234, 241);
        lightPink = new Color(255, 176, 212);
        lightPurple = new Color(151, 146, 213);
        darkPink = new Color(235, 95, 160);
        purple = new Color(118, 112, 192);
        teal = new Color(0, 128, 143);

        // transparentLightGreen = new Color(141, 211, 136, opacity);
        // yellow = new Color(255, 222, 135);
        // green = new Color(93, 162, 89);
    }

    private void setupFonts() {
        try {
            InputStream is = new FileInputStream("res/font/Poppins.ttf");
            poppinsFont = Font.createFont(Font.TRUETYPE_FONT, is);
            is = new FileInputStream("res/font/SuperCereal.ttf");
            superCerealFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        g2.setFont(poppinsFont);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        g2.setColor(Color.white);

        switch (gp.getGameState()) {
            case PAUSE:
                drawPauseScreen();
                break;
            case PLAY:
                if (gp.treasureHunt) {
                    drawPlayScreen();
                }
                break;
            case DIALOGUE:
                drawDialogueScreen();
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
            case WIN:
                drawWinScreen();
                break;
        }
    }

    public void drawWinScreen() {
        g2.setColor(lightBlue);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // title
        g2.setColor(teal);
        g2.setFont(superCerealFont);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "Win!";
        int x = getXForCenterText(text);
        int y = gp.tileSize * 3;
        g2.drawString(text, x, y);

        // pic
        x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
        y += gp.tileSize * 2;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

        // menu
        g2.setColor(commandNum == 0 ? darkPink : teal);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        text = "Try again";
        x = getXForCenterText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.setColor(darkPink);
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "Quit";
        x = getXForCenterText(text);
        y += gp.tileSize;
        g2.setColor(commandNum == 1 ? darkPink : teal);
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.setColor(darkPink);
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    public void drawGameOverScreen() {
        // title
        g2.setFont(superCerealFont);

        g2.setColor(lightPink);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setColor(darkPink);
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
        g2.setColor(commandNum == 0 ? teal : darkPink);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        text = "Play again";
        x = getXForCenterText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.setColor(teal);
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "Quit";
        x = getXForCenterText(text);
        y += gp.tileSize;
        g2.setColor(commandNum == 1 ? teal : darkPink);
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.setColor(teal);
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
        g2.setFont(poppinsFont);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));

        g2.setColor(new Color(151, 146, 213, 174));
        int gap = 60;
        int x = gp.screenWidth - gap;
        int y = gp.tileSize;

        int outlineX = x - 4;
        int outlineY = 35;
        int outlineWidth = 55;
        int outlineHeight = gp.tileSize * 4 + 55;
        int arc = 30;

        g2.fillRoundRect(outlineX, outlineY, outlineWidth, outlineHeight, arc, arc);
        g2.setColor(purple);
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

        drawTimer();
    }

    public void drawTimer() {
        int outlineX = 20;
        int outlineY = 20;
        int outlineWidth = 280;
        int outlineHeight = 60;
        int arc = 30;

        g2.setColor(transparentLightBlue);
        g2.fillRoundRect(outlineX, outlineY, outlineWidth, outlineHeight, arc, arc);
        g2.setColor(teal);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(outlineX, outlineY, outlineWidth, outlineHeight, arc, arc);
        if (gp.getGameState() != GameState.PAUSE) {
            playtime += (double) 1 / 60;
            if (playtime > 120) {
                gp.setGameState(GameState.GAME_OVER);
            }
        }
        g2.setColor(teal);
        g2.drawString(STR."Time: \{format.format(playtime)}", outlineX + 8, 65);
    }

    public void drawTitleScreen() {
        g2.setColor(new Color(255, 238, 193));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // title
        g2.setFont(superCerealFont);
        g2.setColor(lightPink);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 78F));
        String text = "Cheer Rush Game";
        int x = getXForCenterText(text);
        int y = gp.tileSize * 3;

        // shadow
        g2.setColor(darkPink);
        g2.drawString(text, x + 5, y + 5);
        g2.setColor(Color.PINK);
        g2.drawString(text, x, y);

        // pic
        x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
        y += gp.tileSize * 2;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

        // menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        g2.setColor(commandNum == 0 ? purple : lightPurple);
        text = "New Game";
        x = getXForCenterText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.setColor(purple);
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "Quit";
        x = getXForCenterText(text);
        y += gp.tileSize;
        g2.setColor(commandNum == 1 ? purple : lightPurple);
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.setColor(purple);
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
