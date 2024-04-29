package main;

import entity.Npc1;
import entity.Npc2;
import object.ObjectChest;
import object.ObjectDoor;
import object.ObjectKey;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        // gp.obj[0] = new ObjectKey();
        // gp.obj[0].worldX = 23 * gp.tileSize;
        // gp.obj[0].worldY = 7 * gp.tileSize;

        // gp.obj[1] = new ObjectKey();
        // gp.obj[1].worldX = 23 * gp.tileSize;
        // gp.obj[1].worldY = 40 * gp.tileSize;

        // gp.obj[2] = new ObjectKey();
        // gp.obj[2].worldX = 38 * gp.tileSize;
        // gp.obj[2].worldY = 8 * gp.tileSize;

        // gp.obj[3] = new ObjectDoor();
        // gp.obj[3].worldX = 10 * gp.tileSize;
        // gp.obj[3].worldY = 11 * gp.tileSize;

        // gp.obj[4] = new ObjectDoor();
        // gp.obj[4].worldX = 8 * gp.tileSize;
        // gp.obj[4].worldY = 38 * gp.tileSize;

        // gp.obj[5] = new ObjectDoor();
        // gp.obj[5].worldX = 12 * gp.tileSize;
        // gp.obj[5].worldY = 22 * gp.tileSize;

        // gp.obj[6] = new ObjectChest();
        // gp.obj[6].worldX = 10 * gp.tileSize;
        // gp.obj[6].worldY = 7 * gp.tileSize;
    }

    public void setNpc() {
        gp.npc[0] = new Npc1(gp);
        gp.npc[0].worldX = 12 * gp.tileSize;
        gp.npc[0].worldY = 7 * gp.tileSize;

        gp.npc[1] = new Npc2(gp);
        gp.npc[1].worldX = 38 * gp.tileSize;
        gp.npc[1].worldY = 36 * gp.tileSize;
    }
}
