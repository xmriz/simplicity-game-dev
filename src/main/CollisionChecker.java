package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.worldX + entity.solidArea.x; // the x and y coordinates of the entity's solid area
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gamePanel.tileSize; // the column and row of the tile the entity is
                                                                   // standing on
        int entityRightCol = entityRightWorldX / gamePanel.tileSize;
        int entityTopRow = entityTopWorldY / gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY / gamePanel.tileSize;

        int tileNum1, tileNum2; // the tile number of the tile the entity is standing on

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize; // the row of the tile the entity
                                                                                      // will be standing on after
                                                                                      // moving
                tileNum1 = gamePanel.tileManager.mapTileNum[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap][entityLeftCol][entityTopRow]; // the
                                                                                                                // tile
                                                                                                                // number
                                                                                                                // of
                                                                                                                // the
                                                                                                                // tile
                // the entity will be standing
                // on after moving
                tileNum2 = gamePanel.tileManager.mapTileNum[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap][entityRightCol][entityTopRow];
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) { // if
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap][entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap][entityLeftCol][entityBottomRow];
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap][entityRightCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap][entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            default:
                break;
        }
    }

    // npc collision
    public int checkEntity(Entity entity, Entity[][] target) {
        int index = 999;

        for (int i = 0; i < target[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap].length; i++) {
            if (target[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap][i] != null) {

                // get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                // get target's solid area position
                target[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap][i].solidArea.x = target[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap][i].worldX
                        + target[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap][i].solidArea.x;
                target[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap][i].solidArea.y = target[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap][i].worldY
                        + target[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap][i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(target[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap][i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(target[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap][i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(target[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap][i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(target[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap][i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap][i].solidArea.x = target[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap][i].solidAreaDefaultX;
                target[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap][i].solidArea.y = target[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap][i].solidAreaDefaultY;
            }
        }
        return index;
    }

    public void checkSim(Entity entity) {
        // get entity's solid area position
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;
        // get target's solid area position
        gamePanel.listSim.get(gamePanel.indexCurrentSim).solidArea.x = gamePanel.listSim.get(gamePanel.indexCurrentSim).worldX + gamePanel.listSim.get(gamePanel.indexCurrentSim).solidArea.x;
        gamePanel.listSim.get(gamePanel.indexCurrentSim).solidArea.y = gamePanel.listSim.get(gamePanel.indexCurrentSim).worldY + gamePanel.listSim.get(gamePanel.indexCurrentSim).solidArea.y;

        switch (entity.direction) {
            case "up":
                entity.solidArea.y -= entity.speed;
                if (entity.solidArea.intersects(gamePanel.listSim.get(gamePanel.indexCurrentSim).solidArea)) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                if (entity.solidArea.intersects(gamePanel.listSim.get(gamePanel.indexCurrentSim).solidArea)) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                if (entity.solidArea.intersects(gamePanel.listSim.get(gamePanel.indexCurrentSim).solidArea)) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                if (entity.solidArea.intersects(gamePanel.listSim.get(gamePanel.indexCurrentSim).solidArea)) {
                    entity.collisionOn = true;
                }
                break;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gamePanel.listSim.get(gamePanel.indexCurrentSim).solidArea.x = gamePanel.listSim.get(gamePanel.indexCurrentSim).solidAreaDefaultX;
        gamePanel.listSim.get(gamePanel.indexCurrentSim).solidArea.y = gamePanel.listSim.get(gamePanel.indexCurrentSim).solidAreaDefaultY;
    }

    public int checkBenda(Entity entity, boolean sim) {
        int index = 999;
        if (gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap == 0){
            for (int i = 0; i < gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap].size(); i++) {
                if (gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap].get(i) != null) {
                    // get entity's solid area position
                    entity.solidArea.x = entity.worldX + entity.solidArea.x;
                    entity.solidArea.y = entity.worldY + entity.solidArea.y;
    
                    // get benda's solid area position
                    gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap].get(i).solidArea.x = gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap].get(i).worldX
                            + gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap].get(i).solidArea.x;
                    gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap].get(i).solidArea.y = gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap].get(i).worldY
                            + gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap].get(i).solidArea.y;
    
                    switch (entity.direction) {
                        case "up":
                            entity.solidArea.y -= entity.speed;
                            if (entity.solidArea.intersects(gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap].get(i).solidArea)) {
                                if (gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap].get(i).collision == true) {
                                    entity.collisionOn = true;
                                }
                                if (sim){ // jika sim yang menabrak
                                    index = i;
                                }
                            }
                            break;
                        case "down":
                            entity.solidArea.y += entity.speed;
                            if (entity.solidArea.intersects(gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap].get(i).solidArea)) {
                                if (gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap].get(i).collision == true) {
                                    entity.collisionOn = true;
                                }
                                if (sim){ // jika sim yang menabrak
                                    index = i;
                                }
                            }
                            break;
                        case "left":
                            entity.solidArea.x -= entity.speed;
                            if (entity.solidArea.intersects(gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap].get(i).solidArea)) {
                                if (gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap].get(i).collision == true) {
                                    entity.collisionOn = true;
                                }
                                if (sim){ // jika sim yang menabrak
                                    index = i;
                                }
                            }
                            break;
                        case "right":
                            entity.solidArea.x += entity.speed;
                            if (entity.solidArea.intersects(gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap].get(i).solidArea)) {
                                if (gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap].get(i).collision == true) {
                                    entity.collisionOn = true;
                                }
                                if (sim){ // jika sim yang menabrak
                                    index = i;
                                }
                            }
                            break;
                    }
                    entity.solidArea.x = entity.solidAreaDefaultX;
                    entity.solidArea.y = entity.solidAreaDefaultY;
                    gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap].get(i).solidArea.x = gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap].get(i).solidAreaDefaultX;
                    gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap].get(i).solidArea.y = gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap].get(i).solidAreaDefaultY;
                }
            }
        } else {
            for (int i = 0; i < gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.size(); i++) {
                if (gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(i) != null) {
                    // get entity's solid area position
                    entity.solidArea.x = entity.worldX + entity.solidArea.x;
                    entity.solidArea.y = entity.worldY + entity.solidArea.y;
    
                    // get benda's solid area position
                    gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(i).solidArea.x = gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(i).worldX
                            + gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(i).solidArea.x;
                    gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(i).solidArea.y = gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(i).worldY
                            + gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(i).solidArea.y;
    
                    switch (entity.direction) {
                        case "up":
                            entity.solidArea.y -= entity.speed;
                            if (entity.solidArea.intersects(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(i).solidArea)) {
                                if (gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(i).collision == true) {
                                    entity.collisionOn = true;
                                }
                                if (sim){ // jika sim yang menabrak
                                    index = i;
                                }
                            }
                            break;
                        case "down":
                            entity.solidArea.y += entity.speed;
                            if (entity.solidArea.intersects(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(i).solidArea)) {
                                if (gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(i).collision == true) {
                                    entity.collisionOn = true;
                                }
                                if (sim){ // jika sim yang menabrak
                                    index = i;
                                }
                            }
                            break;
                        case "left":
                            entity.solidArea.x -= entity.speed;
                            if (entity.solidArea.intersects(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(i).solidArea)) {
                                if (gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(i).collision == true) {
                                    entity.collisionOn = true;
                                }
                                if (sim){ // jika sim yang menabrak
                                    index = i;
                                }
                            }
                            break;
                        case "right":
                            entity.solidArea.x += entity.speed;
                            if (entity.solidArea.intersects(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(i).solidArea)) {
                                if (gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(i).collision == true) {
                                    entity.collisionOn = true;
                                }
                                if (sim){ // jika sim yang menabrak
                                    index = i;
                                }
                            }
                            break;
                    }
                    entity.solidArea.x = entity.solidAreaDefaultX;
                    entity.solidArea.y = entity.solidAreaDefaultY;
                    gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(i).solidArea.x = gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(i).solidAreaDefaultX;
                    gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(i).solidArea.y = gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(i).solidAreaDefaultY;
                }
            }
        }

        return index;
    }
}
