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
                tileNum1 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityLeftCol][entityTopRow]; // the
                                                                                                                // tile
                                                                                                                // number
                                                                                                                // of
                                                                                                                // the
                                                                                                                // tile
                // the entity will be standing
                // on after moving
                tileNum2 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityRightCol][entityTopRow];
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) { // if
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityLeftCol][entityBottomRow];
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityRightCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[gamePanel.currentMap][entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                    System.out.println("1");
                    System.out.println(gamePanel.tileManager.tile[tileNum1].getClass().toString());
                    System.out.println("collide:\ncol: " + entityRightCol + "\nrow: " + entityTopRow);
                }
                break;
            default:
                break;
        }
    }

    // npc collision
    public int checkEntity(Entity entity, Entity[][] target) {
        int index = 999;

        for (int i = 0; i < target[gamePanel.currentMap].length; i++) {
            if (target[gamePanel.currentMap][i] != null) {

                // get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                // get target's solid area position
                target[gamePanel.currentMap][i].solidArea.x = target[gamePanel.currentMap][i].worldX
                        + target[gamePanel.currentMap][i].solidArea.x;
                target[gamePanel.currentMap][i].solidArea.y = target[gamePanel.currentMap][i].worldY
                        + target[gamePanel.currentMap][i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(target[gamePanel.currentMap][i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(target[gamePanel.currentMap][i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(target[gamePanel.currentMap][i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(target[gamePanel.currentMap][i].solidArea)) {
                            entity.collisionOn = true;
                            System.out.println("2");
                            index = i;
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[gamePanel.currentMap][i].solidArea.x = target[gamePanel.currentMap][i].solidAreaDefaultX;
                target[gamePanel.currentMap][i].solidArea.y = target[gamePanel.currentMap][i].solidAreaDefaultY;
            }
        }
        return index;
    }

    public void checkSim(Entity entity) {
        // get entity's solid area position
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;
        // get target's solid area position
        gamePanel.sim.solidArea.x = gamePanel.sim.worldX + gamePanel.sim.solidArea.x;
        gamePanel.sim.solidArea.y = gamePanel.sim.worldY + gamePanel.sim.solidArea.y;

        switch (entity.direction) {
            case "up":
                entity.solidArea.y -= entity.speed;
                if (entity.solidArea.intersects(gamePanel.sim.solidArea)) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                if (entity.solidArea.intersects(gamePanel.sim.solidArea)) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                if (entity.solidArea.intersects(gamePanel.sim.solidArea)) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                if (entity.solidArea.intersects(gamePanel.sim.solidArea)) {
                    entity.collisionOn = true;
                    System.out.println("3");
                }
                break;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gamePanel.sim.solidArea.x = gamePanel.sim.solidAreaDefaultX;
        gamePanel.sim.solidArea.y = gamePanel.sim.solidAreaDefaultY;
    }

    public int checkBenda(Entity entity, boolean sim) {
        int index = 999;
        if (gamePanel.currentMap == 0){
            for (int i = 0; i < gamePanel.benda[gamePanel.currentMap].length; i++) {
                if (gamePanel.benda[gamePanel.currentMap][i] != null) {
                    // get entity's solid area position
                    entity.solidArea.x = entity.worldX + entity.solidArea.x;
                    entity.solidArea.y = entity.worldY + entity.solidArea.y;
    
                    // get benda's solid area position
                    gamePanel.benda[gamePanel.currentMap][i].solidArea.x = gamePanel.benda[gamePanel.currentMap][i].worldX
                            + gamePanel.benda[gamePanel.currentMap][i].solidArea.x;
                    gamePanel.benda[gamePanel.currentMap][i].solidArea.y = gamePanel.benda[gamePanel.currentMap][i].worldY
                            + gamePanel.benda[gamePanel.currentMap][i].solidArea.y;
    
                    switch (entity.direction) {
                        case "up":
                            entity.solidArea.y -= entity.speed;
                            if (entity.solidArea.intersects(gamePanel.benda[gamePanel.currentMap][i].solidArea)) {
                                if (gamePanel.benda[gamePanel.currentMap][i].collision == true) {
                                    entity.collisionOn = true;
                                }
                                if (sim){ // jika sim yang menabrak
                                    index = i;
                                }
                            }
                            break;
                        case "down":
                            entity.solidArea.y += entity.speed;
                            if (entity.solidArea.intersects(gamePanel.benda[gamePanel.currentMap][i].solidArea)) {
                                if (gamePanel.benda[gamePanel.currentMap][i].collision == true) {
                                    entity.collisionOn = true;
                                }
                                if (sim){ // jika sim yang menabrak
                                    index = i;
                                }
                            }
                            break;
                        case "left":
                            entity.solidArea.x -= entity.speed;
                            if (entity.solidArea.intersects(gamePanel.benda[gamePanel.currentMap][i].solidArea)) {
                                if (gamePanel.benda[gamePanel.currentMap][i].collision == true) {
                                    entity.collisionOn = true;
                                }
                                if (sim){ // jika sim yang menabrak
                                    index = i;
                                }
                            }
                            break;
                        case "right":
                            entity.solidArea.x += entity.speed;
                            if (entity.solidArea.intersects(gamePanel.benda[gamePanel.currentMap][i].solidArea)) {
                                if (gamePanel.benda[gamePanel.currentMap][i].collision == true) {
                                    entity.collisionOn = true;
                                    System.out.println("4");
                                }
                                if (sim){ // jika sim yang menabrak
                                    index = i;
                                }
                            }
                            break;
                    }
                    entity.solidArea.x = entity.solidAreaDefaultX;
                    entity.solidArea.y = entity.solidAreaDefaultY;
                    gamePanel.benda[gamePanel.currentMap][i].solidArea.x = gamePanel.benda[gamePanel.currentMap][i].solidAreaDefaultX;
                    gamePanel.benda[gamePanel.currentMap][i].solidArea.y = gamePanel.benda[gamePanel.currentMap][i].solidAreaDefaultY;
                }
            }
        } else {
            for (int i = 0; i < gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).bendaRuangan.length; i++) {
                if (gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).bendaRuangan[i] != null) {
                    // get entity's solid area position
                    entity.solidArea.x = entity.worldX + entity.solidArea.x;
                    entity.solidArea.y = entity.worldY + entity.solidArea.y;
    
                    // get benda's solid area position
                    gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).bendaRuangan[i].solidArea.x = gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).bendaRuangan[i].worldX
                            + gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).bendaRuangan[i].solidArea.x;
                    gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).bendaRuangan[i].solidArea.y = gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).bendaRuangan[i].worldY
                            + gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).bendaRuangan[i].solidArea.y;
    
                    switch (entity.direction) {
                        case "up":
                            entity.solidArea.y -= entity.speed;
                            if (entity.solidArea.intersects(gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).bendaRuangan[i].solidArea)) {
                                if (gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).bendaRuangan[i].collision == true) {
                                    entity.collisionOn = true;
                                }
                                if (sim){ // jika sim yang menabrak
                                    index = i;
                                }
                            }
                            break;
                        case "down":
                            entity.solidArea.y += entity.speed;
                            if (entity.solidArea.intersects(gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).bendaRuangan[i].solidArea)) {
                                if (gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).bendaRuangan[i].collision == true) {
                                    entity.collisionOn = true;
                                }
                                if (sim){ // jika sim yang menabrak
                                    index = i;
                                }
                            }
                            break;
                        case "left":
                            entity.solidArea.x -= entity.speed;
                            if (entity.solidArea.intersects(gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).bendaRuangan[i].solidArea)) {
                                if (gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).bendaRuangan[i].collision == true) {
                                    entity.collisionOn = true;
                                }
                                if (sim){ // jika sim yang menabrak
                                    index = i;
                                }
                            }
                            break;
                        case "right":
                            entity.solidArea.x += entity.speed;
                            if (entity.solidArea.intersects(gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).bendaRuangan[i].solidArea)) {
                                if (gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).bendaRuangan[i].collision == true) {
                                    entity.collisionOn = true;
                                    System.out.println("9" + gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).bendaRuangan.length);
                                }
                                if (sim){ // jika sim yang menabrak
                                    index = i;
                                }
                            }
                            break;
                    }
                    entity.solidArea.x = entity.solidAreaDefaultX;
                    entity.solidArea.y = entity.solidAreaDefaultY;
                    gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).bendaRuangan[i].solidArea.x = gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).bendaRuangan[i].solidAreaDefaultX;
                    gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).bendaRuangan[i].solidArea.y = gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).bendaRuangan[i].solidAreaDefaultY;
                }
            }
        }

        return index;
    }
}
