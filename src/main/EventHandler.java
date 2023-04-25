package main;


public class EventHandler {
    GamePanel gamePanel;
    EventRect eventRect[][][];

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    
    public EventHandler(GamePanel gamePanel){
        this.gamePanel = gamePanel;

        eventRect = new EventRect[gamePanel.maxMap][gamePanel.maxWorldCol][gamePanel.maxWorldRow];

        int map = 0;
        int col = 0, row = 0;

        while (map < gamePanel.maxMap && col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow){
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;
            if (col == gamePanel.maxWorldCol){
                col = 0;
                row++;

                if (row == gamePanel.maxWorldRow){
                    row = 0;
                    map++;
                } 
            }
        }
    }

    public void checkEvent(){
        // check if the sim is more than 1 tile away from the event
        int xDistance = Math.abs(gamePanel.sim.worldX - previousEventX);
        int yDistance = Math.abs(gamePanel.sim.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gamePanel.tileSize){
            canTouchEvent = true;
        }
        
        // checking for some event
        if (canTouchEvent){
            if (gamePanel.currentMap == 0){
                if (hit(0,gamePanel.sim.rumah.dimensiX, gamePanel.sim.rumah.dimensiY, "any",999)){
                    teleport(1,5,8,0);
                    gamePanel.sim.indexLocationRuangan = 0;
                    gamePanel.sim.currentLocation = "Rumah " + gamePanel.sim.nama + " (" + gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).name + ")";
                }    
            } else if (gamePanel.sim.indexLocationRuangan == 0){
                if (hit(1,5,8, "any", 0)){
                    teleport(0,gamePanel.sim.rumah.dimensiX, gamePanel.sim.rumah.dimensiY, 999);
                    gamePanel.sim.indexLocationRuangan = 999;
                    gamePanel.sim.currentLocation = "World";
                } else if (hit(1,4,1,"any",gamePanel.sim.indexLocationRuangan)){ // atas
                    if (gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).up != null){
                        teleport(1,5, 8, gamePanel.sim.rumah.ruanganRumah.indexOf(gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).up));
                        gamePanel.sim.currentLocation = "Rumah " + gamePanel.sim.nama + " (" + gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).name + ")";
                        // gamePanel.sim.indexLocationRuangan = gamePanel.sim.rumah.ruanganRumah.indexOf(gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).up);
                    } else {
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.currentDialog = "Tidak ada ruangan di atas!";
                        canTouchEvent = false;
                    }
                    // UNTUK RUANGAN UTAMA BAWAH UNTUK TELEPORT KE WORLD
                // } else if (hit(1,5,8,"any",gamePanel.sim.indexLocationRuangan)){ 
                //     if (gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).down != null){
                //         teleport(1,4, 1, gamePanel.sim.rumah.ruanganRumah.indexOf(gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).down));
                //         gamePanel.sim.currentLocation = "Rumah " + gamePanel.sim.nama + " (" + gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).name;
                //         // gamePanel.sim.indexLocationRuangan = gamePanel.sim.rumah.ruanganRumah.indexOf(gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).down);
                //     } else {
                //         gamePanel.gameState = gamePanel.dialogState;
                //         gamePanel.ui.currentDialog = "Tidak ada ruangan di bawah!";
                //         canTouchEvent = false;
                //     }
                } else if (hit(1,1,5,"any",gamePanel.sim.indexLocationRuangan)) { // kiri
                    if (gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).left != null){
                        teleport(1,8, 4, gamePanel.sim.rumah.ruanganRumah.indexOf(gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).left));
                        gamePanel.sim.currentLocation = "Rumah " + gamePanel.sim.nama + " (" + gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).name + ")";
                        // gamePanel.sim.indexLocationRuangan = gamePanel.sim.rumah.ruanganRumah.indexOf(gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).left);
                    } else {
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.currentDialog = "Tidak ada ruangan di kiri!";
                        canTouchEvent = false;
                    }
                } else if (hit(1,8,4,"any",gamePanel.sim.indexLocationRuangan)) { // kanan
                    if (gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).right != null){
                        teleport(1,1, 5, gamePanel.sim.rumah.ruanganRumah.indexOf(gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).right));
                        gamePanel.sim.currentLocation = "Rumah " + gamePanel.sim.nama + " (" + gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).name + ")";
                        // gamePanel.sim.indexLocationRuangan = gamePanel.sim.rumah.ruanganRumah.indexOf(gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).right);
                    } else {
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.currentDialog = "Tidak ada ruangan di kanan!";
                        canTouchEvent = false;
                    }
                }
            } else { // SELAIN DI RUANGAN UTAMA DAN WORLD
                if (hit(1,4,1,"any",gamePanel.sim.indexLocationRuangan)){ // atas
                    if (gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).up != null){
                        teleport(1,5, 8, gamePanel.sim.rumah.ruanganRumah.indexOf(gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).up));
                        gamePanel.sim.currentLocation = "Rumah " + gamePanel.sim.nama + " (" + gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).name + ")";
                        // gamePanel.sim.indexLocationRuangan = gamePanel.sim.rumah.ruanganRumah.indexOf(gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).up);
                    } else {
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.currentDialog = "Tidak ada ruangan di atas!";
                        canTouchEvent = false;
                    }
                } else if (hit(1,5,8,"any",gamePanel.sim.indexLocationRuangan)){ // bawah
                    if (gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).down != null){
                        teleport(1,4, 1, gamePanel.sim.rumah.ruanganRumah.indexOf(gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).down));
                        gamePanel.sim.currentLocation = "Rumah " + gamePanel.sim.nama + " (" + gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).name + ")";
                        // gamePanel.sim.indexLocationRuangan = gamePanel.sim.rumah.ruanganRumah.indexOf(gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).down);
                    } else {
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.currentDialog = "Tidak ada ruangan di bawah!";
                        canTouchEvent = false;
                    }
                } else if (hit(1,1,5,"any",gamePanel.sim.indexLocationRuangan)) { // kiri
                    if (gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).left != null){
                        teleport(1,8, 4, gamePanel.sim.rumah.ruanganRumah.indexOf(gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).left));
                        gamePanel.sim.currentLocation = "Rumah " + gamePanel.sim.nama + " (" + gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).name + ")";
                        // gamePanel.sim.indexLocationRuangan = gamePanel.sim.rumah.ruanganRumah.indexOf(gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).left);
                    } else {
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.currentDialog = "Tidak ada ruangan di kiri!";
                        canTouchEvent = false;
                    }
                } else if (hit(1,8,4,"any",gamePanel.sim.indexLocationRuangan)) { // kanan
                    if (gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).right != null){
                        teleport(1,1, 5, gamePanel.sim.rumah.ruanganRumah.indexOf(gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).right));
                        gamePanel.sim.currentLocation = "Rumah " + gamePanel.sim.nama + " (" + gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).name + ")";
                        // gamePanel.sim.indexLocationRuangan = gamePanel.sim.rumah.ruanganRumah.indexOf(gamePanel.sim.rumah.ruanganRumah.get(gamePanel.sim.indexLocationRuangan).right);
                    } else {
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.currentDialog = "Tidak ada ruangan di kanan!";
                        canTouchEvent = false;
                    }
                }
            }
        }
    }

    public boolean hit(int map, int col, int row, String reqDirection, int indexRuangan){
        boolean hit = false;
        if (map == 0){
            if (map == gamePanel.currentMap){
                gamePanel.sim.solidArea.x = gamePanel.sim.worldX + gamePanel.sim.solidArea.x;
                gamePanel.sim.solidArea.y = gamePanel.sim.worldY + gamePanel.sim.solidArea.y;
        
                eventRect[map][col][row].x = col*gamePanel.tileSize + eventRect[map][col][row].x;
                eventRect[map][col][row].y = row*gamePanel.tileSize + eventRect[map][col][row].y;
        
                if (gamePanel.sim.solidArea.intersects(eventRect[map][col][row]) && !eventRect[map][col][row].eventDone){
                    if (gamePanel.sim.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                        hit = true;
        
                        previousEventX = gamePanel.sim.worldX;
                        previousEventY = gamePanel.sim.worldY; 
                    }
                }
        
                gamePanel.sim.solidArea.x = gamePanel.sim.solidAreaDefaultX;
                gamePanel.sim.solidArea.y = gamePanel.sim.solidAreaDefaultY;
                eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
                eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
            }
        } else {
            if (map == gamePanel.currentMap){
                gamePanel.sim.solidArea.x = gamePanel.sim.worldX + gamePanel.sim.solidArea.x;
                gamePanel.sim.solidArea.y = gamePanel.sim.worldY + gamePanel.sim.solidArea.y;
        
                eventRect[map][col][row].x = col*gamePanel.tileSize + eventRect[map][col][row].x;
                eventRect[map][col][row].y = row*gamePanel.tileSize + eventRect[map][col][row].y;
        
                if (gamePanel.sim.solidArea.intersects(eventRect[map][col][row]) && !eventRect[map][col][row].eventDone){
                    if (gamePanel.sim.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                        hit = true;
        
                        previousEventX = gamePanel.sim.worldX;
                        previousEventY = gamePanel.sim.worldY; 
                    }
                }
        
                gamePanel.sim.solidArea.x = gamePanel.sim.solidAreaDefaultX;
                gamePanel.sim.solidArea.y = gamePanel.sim.solidAreaDefaultY;
                eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
                eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
            }
        }
        

        return hit;
    }

    public void teleport(int map, int col, int row, int indexRuangan){
        gamePanel.currentMap = map;
        gamePanel.sim.worldX = col*gamePanel.tileSize;
        gamePanel.sim.worldY = row*gamePanel.tileSize;
        previousEventX = gamePanel.sim.worldX;
        previousEventY = gamePanel.sim.worldY;
        canTouchEvent = false;
        gamePanel.sim.indexLocationRuangan = indexRuangan;
    }
}