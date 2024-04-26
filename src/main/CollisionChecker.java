package main;

import entity.Entity;
import object.SuperObject;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        // find their col and row numbers
        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case UP:
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case DOWN:
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case LEFT:
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case RIGHT:
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public int checkObject(Entity entity, boolean player) {
        int pickedUpIndex = 999;
        int objectIndex = 0;
        // check if the player is hitting any object
        for (SuperObject o : gp.obj) {
            if (o != null) {
                // get the solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // get the objects solid area position
                o.solidArea.x = o.worldX + o.solidArea.x;
                o.solidArea.y = o.worldY + o.solidArea.y;

                switch (entity.direction) {
                    case UP:
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(o.solidArea)) {
                            if (o.collision && player) {
                                entity.collisionOn = true;
                                pickedUpIndex = objectIndex;
                            }
                        }
                        break;
                    case DOWN:
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(o.solidArea)) {
                            if (o.collision && player) {
                                entity.collisionOn = true;
                                pickedUpIndex = objectIndex;
                            }
                        }
                        break;
                    case LEFT:
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(o.solidArea)) {
                            if (o.collision && player) {
                                pickedUpIndex = objectIndex;
                                entity.collisionOn = true;
                            }
                        }
                        break;
                    case RIGHT:
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(o.solidArea)) {
                            if (o.collision && player) {
                                pickedUpIndex = objectIndex;
                                entity.collisionOn = true;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                o.solidArea.x = o.solidAreaDefaultX;
                o.solidArea.y = o.solidAreaDefaultY;
            }
            objectIndex++;
        }
        // return the index of the object, so we can program the reaction accordingly
        return pickedUpIndex;
    }

    // check NPC collision
    public int checkEntity(Entity entity, Entity[] target) {
        int entityHitIndex = 999;
        int entityIndex = 0;
        // check if the player is hitting any object
        for (Entity npc : target) {
            if (npc != null) {
                // get the solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                // get the objects solid area position
                npc.solidArea.x = npc.worldX + npc.solidArea.x;
                npc.solidArea.y = npc.worldY + npc.solidArea.y;

                switch (entity.direction) {
                    case UP:
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(npc.solidArea)) {
                            entity.collisionOn = true;
                            entityHitIndex = entityIndex;
                        }
                        break;
                    case DOWN:
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(npc.solidArea)) {
                            entity.collisionOn = true;
                            entityHitIndex = entityIndex;
                        }
                        break;
                    case LEFT:
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(npc.solidArea)) {
                            entityHitIndex = entityIndex;
                            entity.collisionOn = true;
                        }
                        break;
                    case RIGHT:
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(npc.solidArea)) {
                            entityHitIndex = entityIndex;
                            entity.collisionOn = true;
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                npc.solidArea.x = npc.solidAreaDefaultX;
                npc.solidArea.y = npc.solidAreaDefaultY;
            }
            entityIndex++;
        }
        // return the index of the object, so we can program the reaction accordingly
        return entityHitIndex;
    }

    public void checkPlayer(Entity entity) {

        // get the solid area position
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        // get the objects solid area position
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        switch (entity.direction) {
            case UP:
                entity.solidArea.y -= entity.speed;
                if (entity.solidArea.intersects(gp.player.solidArea)) {
                    entity.collisionOn = true;
                }
                break;
            case DOWN:
                entity.solidArea.y += entity.speed;
                if (entity.solidArea.intersects(gp.player.solidArea)) {
                    entity.collisionOn = true;
                }
                break;
            case LEFT:
                entity.solidArea.x -= entity.speed;
                if (entity.solidArea.intersects(gp.player.solidArea)) {
                    entity.collisionOn = true;
                }
                break;
            case RIGHT:
                entity.solidArea.x += entity.speed;
                if (entity.solidArea.intersects(gp.player.solidArea)) {
                    entity.collisionOn = true;
                }
                break;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
    }
}