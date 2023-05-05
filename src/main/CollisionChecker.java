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

        int entityLeftCol = entityLeftWorldX / gamePanel.getTileSize(); // the column and row of the tile the entity is
                                                                   // standing on
        int entityRightCol = entityRightWorldX / gamePanel.getTileSize();
        int entityTopRow = entityTopWorldY / gamePanel.getTileSize();
        int entityBottomRow = entityBottomWorldY / gamePanel.getTileSize();

        int tileNum1, tileNum2; // the tile number of the tile the entity is standing on

        switch (entity.getDirection()) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.getSpeed()) / gamePanel.getTileSize(); // the row of the tile the entity
                                                                                      // will be standing on after
                                                                                      // moving
                tileNum1 = gamePanel.getTileManager().getMapTileNum()[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()][entityLeftCol][entityTopRow]; // the
                                                                                                                // tile
                                                                                                                // number
                                                                                                                // of
                                                                                                                // the
                                                                                                                // tile
                // the entity will be standing
                // on after moving
                tileNum2 = gamePanel.getTileManager().getMapTileNum()[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()][entityRightCol][entityTopRow];
                if (gamePanel.getTileManager().getTile()[tileNum1].isCollision() || gamePanel.getTileManager().getTile()[tileNum2].isCollision()) { // if
                    entity.setCollisionOn(true);
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / gamePanel.getTileSize();
                tileNum1 = gamePanel.getTileManager().getMapTileNum()[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()][entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.getTileManager().getMapTileNum()[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()][entityRightCol][entityBottomRow];
                if (gamePanel.getTileManager().getTile()[tileNum1].isCollision() || gamePanel.getTileManager().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / gamePanel.getTileSize();
                tileNum1 = gamePanel.getTileManager().getMapTileNum()[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()][entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.getTileManager().getMapTileNum()[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()][entityLeftCol][entityBottomRow];
                if (gamePanel.getTileManager().getTile()[tileNum1].isCollision() || gamePanel.getTileManager().getTile()[tileNum2].isCollision()) {
                    entity.setCollisionOn(true);
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.getSpeed()) / gamePanel.getTileSize();
                tileNum1 = gamePanel.getTileManager().getMapTileNum()[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()][entityRightCol][entityTopRow];
                tileNum2 = gamePanel.getTileManager().getMapTileNum()[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()][entityRightCol][entityBottomRow];
                if (gamePanel.getTileManager().getTile()[tileNum1].isCollision() || gamePanel.getTileManager().getTile()[tileNum2].isCollision()) {
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

        for (int i = 0; i < target[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()].length; i++) {
            if (target[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()][i] != null) {

                // get entity's solid area position
                entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
                entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;
                // get target's solid area position
                target[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()][i].getSolidArea().x = target[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()][i].getWorldX()
                        + target[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()][i].getSolidArea().x;
                target[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()][i].getSolidArea().y = target[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()][i].getWorldY()
                        + target[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()][i].getSolidArea().y;

                switch (entity.getDirection()) {
                    case "up":
                        entity.getSolidArea().y -= entity.getSpeed();
                        if (entity.getSolidArea().intersects(target[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()][i].getSolidArea())) {
                            entity.setCollisionOn(true);
                            index = i;
                        }
                        break;
                    case "down":
                        entity.getSolidArea().y += entity.getSpeed();
                        if (entity.getSolidArea().intersects(target[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()][i].getSolidArea())) {
                            entity.setCollisionOn(true);
                            index = i;
                        }
                        break;
                    case "left":
                        entity.getSolidArea().x -= entity.getSpeed();
                        if (entity.getSolidArea().intersects(target[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()][i].getSolidArea())) {
                            entity.setCollisionOn(true);
                            index = i;
                        }
                        break;
                    case "right":
                        entity.getSolidArea().x += entity.getSpeed();
                        if (entity.getSolidArea().intersects(target[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()][i].getSolidArea())) {
                            entity.setCollisionOn(true);
                            index = i;
                        }
                        break;
                }
                entity.getSolidArea().x = entity.getSolidAreaDefaultX();
                entity.getSolidArea().y = entity.getSolidAreaDefaultY();
                target[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()][i].getSolidArea().x = target[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()][i].getSolidAreaDefaultX();
                target[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()][i].getSolidArea().y = target[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()][i].getSolidAreaDefaultY();
            }
        }
        return index;
    }

    public void checkSim(Entity entity) {
        // get entity's solid area position
        entity.getSolidArea().x = entity.getWorldX() + entity.getSolidArea().x;
        entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;
        // get target's solid area position
        gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getSolidArea().x = gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getWorldX() + gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getSolidArea().x;
        gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getSolidArea().y = gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getWorldY() + gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getSolidArea().y;

        switch (entity.getDirection()) {
            case "up":
                entity.getSolidArea().y -= entity.getSpeed();
                if (entity.getSolidArea().intersects(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getSolidArea())) {
                    entity.setCollisionOn(true);
                }
                break;
            case "down":
                entity.getSolidArea().y += entity.getSpeed();
                if (entity.getSolidArea().intersects(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getSolidArea())) {
                    entity.setCollisionOn(true);
                }
                break;
            case "left":
                entity.getSolidArea().x -= entity.getSpeed();
                if (entity.getSolidArea().intersects(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getSolidArea())) {
                    entity.setCollisionOn(true);
                }
                break;
            case "right":
                entity.getSolidArea().x += entity.getSpeed();
                if (entity.getSolidArea().intersects(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getSolidArea())) {
                    entity.setCollisionOn(true);
                }
                break;
        }
        entity.getSolidArea().x = entity.getSolidAreaDefaultX();
        entity.getSolidArea().y = entity.getSolidAreaDefaultY();
        gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getSolidArea().x = gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getSolidAreaDefaultX();
        gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getSolidArea().y = gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getSolidAreaDefaultY();
    }

    public int checkBenda(Entity entity, boolean sim) {
        int index = 999;
        if (gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap() == 0){
            for (int i = 0; i < gamePanel.getListRumah()[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()].size(); i++) {
                if (gamePanel.getListRumah()[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()].get(i) != null) {
                    // get entity's solid area position
                    entity.getSolidArea().x = entity.getWorldX()+ entity.getSolidArea().x;
                    entity.getSolidArea().y = entity.getWorldY() + entity.getSolidArea().y;
    
                    // get benda's solid area position
                    gamePanel.getListRumah()[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()].get(i).getSolidArea().x = gamePanel.getListRumah()[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()].get(i).getWorldX()
                            + gamePanel.getListRumah()[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()].get(i).getSolidArea().x;
                    gamePanel.getListRumah()[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()].get(i).getSolidArea().y = gamePanel.getListRumah()[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()].get(i).getWorldY()
                            + gamePanel.getListRumah()[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()].get(i).getSolidArea().y;
    
                    switch (entity.getDirection()) {
                        case "up":
                            entity.getSolidArea().y -= entity.getSpeed();
                            if (entity.getSolidArea().intersects(gamePanel.getListRumah()[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()].get(i).getSolidArea())) {
                                if (gamePanel.getListRumah()[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()].get(i).getCollision() == true) {
                                    entity.setCollisionOn(true);
                                }
                                if (sim){ // jika sim yang menabrak
                                    index = i;
                                }
                            }
                            break;
                        case "down":
                            entity.getSolidArea().y += entity.getSpeed();
                            if (entity.getSolidArea().intersects(gamePanel.getListRumah()[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()].get(i).getSolidArea())) {
                                if (gamePanel.getListRumah()[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()].get(i).getCollision() == true) {
                                    entity.setCollisionOn(true);
                                }
                                if (sim){ // jika sim yang menabrak
                                    index = i;
                                }
                            }
                            break;
                        case "left":
                            entity.getSolidArea().x -= entity.getSpeed();
                            if (entity.getSolidArea().intersects(gamePanel.getListRumah()[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()].get(i).getSolidArea())) {
                                if (gamePanel.getListRumah()[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()].get(i).getCollision() == true) {
                                    entity.setCollisionOn(true);
                                }
                                if (sim){ // jika sim yang menabrak
                                    index = i;
                                }
                            }
                            break;
                        case "right":
                            entity.getSolidArea().x += entity.getSpeed();
                            if (entity.getSolidArea().intersects(gamePanel.getListRumah()[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()].get(i).getSolidArea())) {
                                if (gamePanel.getListRumah()[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()].get(i).getCollision() == true) {
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
                    gamePanel.getListRumah()[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()].get(i).getSolidArea().x = gamePanel.getListRumah()[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()].get(i).getSolidAreaDefaultX();
                    gamePanel.getListRumah()[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()].get(i).getSolidArea().y = gamePanel.getListRumah()[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()].get(i).getSolidAreaDefaultY();
                }
            }
        } else {
            for (int i = 0; i < gamePanel.getListSim().get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah().get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getBendaRuangan().size(); i++) {
                if (gamePanel.getListSim().get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah().get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getBendaRuangan().get(i) != null) {
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
                            if (entity.getSolidArea().intersects(gamePanel.getListSim().get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah().get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getBendaRuangan().get(i).getSolidArea())) {
                                if (gamePanel.getListSim().get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah().get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getBendaRuangan().get(i).getCollision() == true) {
                                    entity.setCollisionOn(true);
                                }
                                if (sim){ // jika sim yang menabrak
                                    index = i;
                                }
                            }
                            break;
                        case "down":
                            entity.getSolidArea().y += entity.getSpeed();
                            if (entity.getSolidArea().intersects(gamePanel.getListSim().get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah().get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getBendaRuangan().get(i).getSolidArea())) {
                                if (gamePanel.getListSim().get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah().get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getBendaRuangan().get(i).getCollision() == true) {
                                    entity.setCollisionOn(true);
                                }
                                if (sim){ // jika sim yang menabrak
                                    index = i;
                                }
                            }
                            break;
                        case "left":
                            entity.getSolidArea().x -= entity.getSpeed();
                            if (entity.getSolidArea().intersects(gamePanel.getListSim().get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah().get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getBendaRuangan().get(i).getSolidArea())) {
                                if (gamePanel.getListSim().get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah().get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getBendaRuangan().get(i).getCollision() == true) {
                                    entity.setCollisionOn(true);
                                }
                                if (sim){ // jika sim yang menabrak
                                    index = i;
                                }
                            }
                            break;
                        case "right":
                            entity.getSolidArea().x += entity.getSpeed();
                            if (entity.getSolidArea().intersects(gamePanel.getListSim().get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah().get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getBendaRuangan().get(i).getSolidArea())) {
                                if (gamePanel.getListSim().get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah().get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getBendaRuangan().get(i).getCollision() == true) {
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
