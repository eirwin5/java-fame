package main;

import entity.Npc1;
import entity.Npc2;
import object.ObjectBow;
import object.ObjectMegaphone;
import object.ObjectPomPom;
import object.ObjectUniform;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new ObjectPomPom();
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;

        gp.obj[1] = new ObjectMegaphone();
        gp.obj[1].worldX = 38 * gp.tileSize;
        gp.obj[1].worldY = 8 * gp.tileSize;

        gp.obj[2] = new ObjectUniform();
        gp.obj[2].worldX = 21 * gp.tileSize;
        gp.obj[2].worldY = 29 * gp.tileSize;

        gp.obj[3] = new ObjectBow();
        gp.obj[3].worldX = 9 * gp.tileSize;
        gp.obj[3].worldY = 36 * gp.tileSize;
    }

    public void setNpc() {
        gp.npc[0] = new Npc1(gp);
        gp.npc[0].worldX = 12 * gp.tileSize;
        gp.npc[0].worldY = 7 * gp.tileSize;

        gp.npc[1] = new Npc2(gp);
        gp.npc[1].worldX = 38 * gp.tileSize;
        gp.npc[1].worldY = 36 * gp.tileSize;
    }

    public void resetAssets() {
        gp.obj[0] = null;
        gp.obj[1] = null;
        gp.obj[2] = null;
        gp.obj[3] = null;
        gp.npc[0] = null;
        gp.npc[1] = null;
        setObject();
        setNpc();
    }
}
