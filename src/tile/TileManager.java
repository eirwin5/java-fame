package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10]; // create 10 kinds of tiles
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        loadMap("world01");
        getTileImage();
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new File("res/tile/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new File("res/tile/wall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new File("res/tile/water.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(new File("res/tile/earth.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(new File("res/tile/tree.png"));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(new File("res/tile/sand.png"));
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void loadMap(String map) {
        try {
            InputStream is = new FileInputStream(STR."res/maps/\{map}.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                while (col < gp.maxWorldCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worlCol = 0, worldRow = 0;
        while (worlCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worlCol][worldRow];

            int worldX = worlCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (gp.checkInView(worldX, worldY)) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            worlCol++;

            if (worlCol == gp.maxWorldCol) {
                worlCol = 0;
                worldRow++;
            }
        }
    }
}
