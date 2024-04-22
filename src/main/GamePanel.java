package main;

import entity.Entity;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize*maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize*maxScreenRow; // 576 pixels

    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    // Frames per second
    int FPS = 60;

    // System
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    public UI ui = new UI(this);
    public Sound soundEffect = new Sound();
    public Sound music = new Sound();

    // Entity and object
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public Player player = new Player(this, keyH);
    public SuperObject[] obj = new SuperObject[10]; // room for 10 objects to be on the screen at once
    public Entity[] npc = new Entity[2];

    // Game state
    public GameState gameState;
    public boolean optionFlag = false;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.gameState = GameState.TITLE;
    }

    public void setupGame() {
        assetSetter.setObject();
        assetSetter.setNpc();
        gameState = GameState.TITLE;
        playMusic(SoundType.MUSIC.ordinal());
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameThread != null) {
            double drawInterval = (double) 1000000000 / FPS;
            double delta = 0;
            long lastTime = System.nanoTime();
            long currentTime;
            long timer = 0;

            while (gameThread != null) {
                currentTime = System.nanoTime();
                delta += (currentTime - lastTime) / drawInterval;
                timer += (currentTime - lastTime);
                lastTime = currentTime;
                if (delta >= 1) {
                    update();
                    repaint();
                    delta--;
                }
                if (timer >= 1000000000) {
                    timer = 0;
                }
            }
        }
    }

    public boolean checkInView(int worldX, int worldY) {
        return worldX + tileSize > player.worldX - player.screenX &&
                worldX - tileSize < player.worldX + player.screenX &&
                worldY + tileSize > player.worldY - player.screenY &&
                worldY - tileSize < player.worldY + player.screenY;
    }

    public void update() {
        // do not update player information while the game is paused
        if (gameState == GameState.PLAY) {
            player.update();
            for (Entity o : npc) {
                if (o != null) o.update();
            }
        }
        else if (gameState == GameState.DIALOGUE) {
            player.update();
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // title screen
        if (gameState == GameState.TITLE) {
            ui.draw(g2);
        } else {
            handleGame(g2);
        }
    }

    public void handleGame(Graphics2D g2) {
            // tiles
            tileM.draw(g2);

            // objects
            for (SuperObject o : obj) {
                if (o != null) {
                    o.draw(g2, this);
                }
            }

            //npc
            for (Entity n : npc) {
                if (n != null) {
                    n.draw(g2);
                }
            }

            //player
            player.draw(g2);

            // ui
            ui.draw(g2);

            g2.dispose();
        }

    public void playMusic(int i) {
        music.setFile(i);
//        music.play();
        music.stop();
//        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSoundEffect(int i) {
        soundEffect.setFile(i);
        soundEffect.play();
    }
}
