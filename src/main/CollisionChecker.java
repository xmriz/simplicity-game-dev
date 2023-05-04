package main;

import entity.Entity;

public class CollisionChecker {

    private GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.getWorldX() + entity.getSolidArea().x; // the x and y coordinates of the entity's solid area
        int entityRightWorldX = entity.getWorldX() + entity.getSolidArea().x + entity.getSolidArea().width;
        int entityTopWorldY = entity.getWorldY() + entity.getSolidArea().y;
        int entityBottomWorldY = entity.getWorldY() + entity.getSolidArea().y + entity.getSolidArea().height;

        int entityLeftCol = entityLeftWorldX / gamePanel.tileSize; // the column and row of the tile the entity is
                                                                   // standing on
        int entityRightCol = entityRightWorldX / gamePanel.tileSize;
        int entityTopRow = entityTopWorldY / gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY / gamePanel.tileSize;

        int tileNum1, tileNum2; // the tile number of the tile the entity is standing on

        switch (entity.getDirection()) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.getSpeed()) / gamePanel.tileSize; // the row of the tile the entity
                                                                                      // will be standing on after
                                                                                      // moving
                tileNum1 = gamePanel.tileManager.mapTileNum[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()][entityLeftCol][entityTopRow]; // the
                                                                                                                // tile
                                                                                                                // number
                                                                                                                // of
                                                                                                                // the
                                                                                                                // tile
                // the entity will be standing
                // on after moving
                tileNum2 = gamePanel.tileManager.mapTileNum[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()][entityRightCol][entityTopRow];
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) { // if
                    entity.setCollisionOn(true);
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()][entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()][entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.setCollisionOn(true);
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()][entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()][entityLeftCol][entityBottomRow];
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.setCollisionOn(true);
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.getSpeed()) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()][entityRightCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()][entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
                    entity.setCollisionOn(true);
                }
                break;
            default:
                break;
        }
    }

    // npc collision
    public int checkEntity(Entity entity, Entity[][] target) {
        int index = 999;

        for (int i = 0; i < target[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()].length; i++) {
            if (target[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()][i] != null) {

                // get entity's solid area position
                entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
                entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;
                // get target's solid area position
                target[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()][i].getSolidArea().x = target[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()][i].getWorldX()
                        + target[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()][i].getSolidArea().x;
                target[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()][i].getSolidArea().y = target[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()][i].getWorldY()
                        + target[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()][i].getSolidArea().y;

                switch (entity.getDirection()) {
                    case "up":
                        entity.getSolidArea().y -= entity.getSpeed();
                        if (entity.getSolidArea().intersects(target[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()][i].getSolidArea())) {
                            entity.setCollisionOn(true);
                            index = i;
                        }
                        break;
                    case "down":
                        entity.getSolidArea().y += entity.getSpeed();
                        if (entity.getSolidArea().intersects(target[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()][i].getSolidArea())) {
                            entity.setCollisionOn(true);
                            index = i;
                        }
                        break;
                    case "left":
                        entity.getSolidArea().x -= entity.getSpeed();
                        if (entity.getSolidArea().intersects(target[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()][i].getSolidArea())) {
                            entity.setCollisionOn(true);
                            index = i;
                        }
                        break;
                    case "right":
                        entity.getSolidArea().x += entity.getSpeed();
                        if (entity.getSolidArea().intersects(target[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()][i].getSolidArea())) {
                            entity.setCollisionOn(true);
                            index = i;
                        }
                        break;
                }
                entity.getSolidArea().x = entity.getSolidAreaDefaultX();
                entity.getSolidArea().y = entity.getSolidAreaDefaultY();
                target[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()][i].getSolidArea().x = target[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()][i].getSolidAreaDefaultX();
                target[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()][i].getSolidArea().y = target[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()][i].getSolidAreaDefaultY();
            }
        }
        return index;
    }

    public void checkSim(Entity entity) {
        // get entity's solid area position
        entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
        entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;
        // get target's solid area position
        gamePanel.listSim.get(gamePanel.indexCurrentSim).getSolidArea().x = gamePanel.listSim.get(gamePanel.indexCurrentSim).getWorldX() + gamePanel.listSim.get(gamePanel.indexCurrentSim).getSolidArea().x;
        gamePanel.listSim.get(gamePanel.indexCurrentSim).getSolidArea().y = gamePanel.listSim.get(gamePanel.indexCurrentSim).getWorldY() + gamePanel.listSim.get(gamePanel.indexCurrentSim).getSolidArea().y;

        switch (entity.getDirection()) {
            case "up":
                entity.getSolidArea().y -= entity.getSpeed();
                if (entity.getSolidArea().intersects(gamePanel.listSim.get(gamePanel.indexCurrentSim).getSolidArea())) {
                    entity.setCollisionOn(true);
                }
                break;
            case "down":
                entity.getSolidArea().y += entity.getSpeed();
                if (entity.getSolidArea().intersects(gamePanel.listSim.get(gamePanel.indexCurrentSim).getSolidArea())) {
                    entity.setCollisionOn(true);
                }
                break;
            case "left":
                entity.getSolidArea().x -= entity.getSpeed();
                if (entity.getSolidArea().intersects(gamePanel.listSim.get(gamePanel.indexCurrentSim).getSolidArea())) {
                    entity.setCollisionOn(true);
                }
                break;
            case "right":
                entity.getSolidArea().x += entity.getSpeed();
                if (entity.getSolidArea().intersects(gamePanel.listSim.get(gamePanel.indexCurrentSim).getSolidArea())) {
                    entity.setCollisionOn(true);
                }
                break;
        }
        entity.getSolidArea().x = entity.getSolidAreaDefaultX();
        entity.getSolidArea().y = entity.getSolidAreaDefaultY();
        gamePanel.listSim.get(gamePanel.indexCurrentSim).getSolidArea().x = gamePanel.listSim.get(gamePanel.indexCurrentSim).getSolidAreaDefaultX();
        gamePanel.listSim.get(gamePanel.indexCurrentSim).getSolidArea().y = gamePanel.listSim.get(gamePanel.indexCurrentSim).getSolidAreaDefaultY();
    }

    public int checkBenda(Entity entity, boolean sim) {
        int index = 999;
        if (gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap() == 0){
            for (int i = 0; i < gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()].size(); i++) {
                if (gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()].get(i) != null) {
                    // get entity's solid area position
                    entity.getSolidArea().x = entity.getWorldX()+ entity.getSolidArea().x;
                    entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;
    
                    // get benda's solid area position
                    gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()].get(i).getSolidArea().x = gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()].get(i).getWorldX()
                            + gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()].get(i).getSolidArea().x;
                    gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()].get(i).getSolidArea().y = gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()].get(i).getWorldY()
                            + gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()].get(i).getSolidArea().y;
    
                    switch (entity.getDirection()) {
                        case "up":
                            entity.getSolidArea().y -= entity.getSpeed();
                            if (entity.getSolidArea().intersects(gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()].get(i).getSolidArea())) {
                                if (gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()].get(i).getCollision() == true) {
                                    entity.setCollisionOn(true);
                                }
                                if (sim){ // jika sim yang menabrak
                                    index = i;
                                }
                            }
                            break;
                        case "down":
                            entity.getSolidArea().y += entity.getSpeed();
                            if (entity.getSolidArea().intersects(gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()].get(i).getSolidArea())) {
                                if (gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()].get(i).getCollision() == true) {
                                    entity.setCollisionOn(true);
                                }
                                if (sim){ // jika sim yang menabrak
                                    index = i;
                                }
                            }
                            break;
                        case "left":
                            entity.getSolidArea().x -= entity.getSpeed();
                            if (entity.getSolidArea().intersects(gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()].get(i).getSolidArea())) {
                                if (gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()].get(i).getCollision() == true) {
                                    entity.setCollisionOn(true);
                                }
                                if (sim){ // jika sim yang menabrak
                                    index = i;
                                }
                            }
                            break;
                        case "right":
                            entity.getSolidArea().x += entity.getSpeed();
                            if (entity.getSolidArea().intersects(gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()].get(i).getSolidArea())) {
                                if (gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()].get(i).getCollision() == true) {
                                    entity.setCollisionOn(true);
                                }
                                if (sim){ // jika sim yang menabrak
                                    index = i;
                                }
                            }
                            break;
                    }
                    entity.getSolidArea().x = entity.getSolidAreaDefaultX();
                    entity.getSolidArea().y = entity.getSolidAreaDefaultY();
                    gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()].get(i).getSolidArea().x = gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()].get(i).getSolidAreaDefaultX();
                    gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()].get(i).getSolidArea().y = gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()].get(i).getSolidAreaDefaultY();
                }
            }
        } else {
            for (int i = 0; i < gamePanel.listSim.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah().get(gamePanel.listSim.get(gamePanel.indexCurrentSim).getIndexLocationRuangan()).getBendaRuangan().size(); i++) {
                if (gamePanel.listSim.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah().get(gamePanel.listSim.get(gamePanel.indexCurrentSim).getIndexLocationRuangan()).getBendaRuangan().get(i) != null) {
                    // get entity's solid area position
                    entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
                    entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;
    
                    // // get benda's solid area position
                    // gamePanel.listSim.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexRumahYangDimasuki).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(i).solidArea.x = gamePanel.listSim.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexRumahYangDimasuki).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(i).worldX
                    //         + gamePanel.listSim.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexRumahYangDimasuki).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(i).solidArea.x;
                    // gamePanel.listSim.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexRumahYangDimasuki).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(i).solidArea.y = gamePanel.listSim.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexRumahYangDimasuki).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(i).worldY
                    //         + gamePanel.listSim.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexRumahYangDimasuki).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(i).solidArea.y;
    
                    switch (entity.getDirection()) {
                        case "up":
                            entity.getSolidArea().y -= entity.getSpeed();
                            if (entity.getSolidArea().intersects(gamePanel.listSim.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah().get(gamePanel.listSim.get(gamePanel.indexCurrentSim).getIndexLocationRuangan()).getBendaRuangan().get(i).getSolidArea())) {
                                if (gamePanel.listSim.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah().get(gamePanel.listSim.get(gamePanel.indexCurrentSim).getIndexLocationRuangan()).getBendaRuangan().get(i).getCollision() == true) {
                                    entity.setCollisionOn(true);
                                }
                                if (sim){ // jika sim yang menabrak
                                    index = i;
                                }
                            }
                            break;
                        case "down":
                            entity.getSolidArea().y += entity.getSpeed();
                            if (entity.getSolidArea().intersects(gamePanel.listSim.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah().get(gamePanel.listSim.get(gamePanel.indexCurrentSim).getIndexLocationRuangan()).getBendaRuangan().get(i).getSolidArea())) {
                                if (gamePanel.listSim.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah().get(gamePanel.listSim.get(gamePanel.indexCurrentSim).getIndexLocationRuangan()).getBendaRuangan().get(i).getCollision() == true) {
                                    entity.setCollisionOn(true);
                                }
                                if (sim){ // jika sim yang menabrak
                                    index = i;
                                }
                            }
                            break;
                        case "left":
                            entity.getSolidArea().x -= entity.getSpeed();
                            if (entity.getSolidArea().intersects(gamePanel.listSim.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah().get(gamePanel.listSim.get(gamePanel.indexCurrentSim).getIndexLocationRuangan()).getBendaRuangan().get(i).getSolidArea())) {
                                if (gamePanel.listSim.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah().get(gamePanel.listSim.get(gamePanel.indexCurrentSim).getIndexLocationRuangan()).getBendaRuangan().get(i).getCollision() == true) {
                                    entity.setCollisionOn(true);
                                }
                                if (sim){ // jika sim yang menabrak
                                    index = i;
                                }
                            }
                            break;
                        case "right":
                            entity.getSolidArea().x += entity.getSpeed();
                            if (entity.getSolidArea().intersects(gamePanel.listSim.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah().get(gamePanel.listSim.get(gamePanel.indexCurrentSim).getIndexLocationRuangan()).getBendaRuangan().get(i).getSolidArea())) {
                                if (gamePanel.listSim.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah().get(gamePanel.listSim.get(gamePanel.indexCurrentSim).getIndexLocationRuangan()).getBendaRuangan().get(i).getCollision() == true) {
                                    entity.setCollisionOn(true);
                                }
                                if (sim){ // jika sim yang menabrak
                                    index = i;
                                }
                            }
                            break;
                    }
                    entity.getSolidArea().x = entity.getSolidAreaDefaultX();
                    entity.getSolidArea().y = entity.getSolidAreaDefaultY();
                    // gamePanel.listSim.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexRumahYangDimasuki).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(i).solidArea.x = gamePanel.listSim.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexRumahYangDimasuki).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(i).solidAreaDefaultX;
                    // gamePanel.listSim.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexRumahYangDimasuki).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(i).solidArea.y = gamePanel.listSim.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexRumahYangDimasuki).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(i).solidAreaDefaultY;
                }
            }
        }

        return index;
    }
}
