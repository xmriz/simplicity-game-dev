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
        int xDistance = Math.abs(gamePanel.currentSim.worldX - previousEventX);
        int yDistance = Math.abs(gamePanel.currentSim.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gamePanel.tileSize){
            canTouchEvent = true;
        }
        
        // checking for some event
        if (canTouchEvent){
            if (gamePanel.currentMap == 0){
                if (hit(0,gamePanel.currentSim.rumah.colRumah, gamePanel.currentSim.rumah.rowRumah, "any",999)){
                    teleport(1,5,8,0);
                    gamePanel.currentSim.indexLocationRuangan = 0;
                    gamePanel.currentSim.currentLocation = "Rumah " + gamePanel.currentSim.nama + " (" + gamePanel.currentSim.rumah.ruanganRumah.get(gamePanel.currentSim.indexLocationRuangan).name + ")";
                }    
            } else if (gamePanel.currentSim.indexLocationRuangan == 0){
                if (hit(1,5,8, "any", 0)){
                    teleport(0,gamePanel.currentSim.rumah.colRumah, gamePanel.currentSim.rumah.rowRumah, 999);
                    gamePanel.currentSim.indexLocationRuangan = 999;
                    gamePanel.currentSim.currentLocation = "World";
                } else if (hit(1,4,1,"any",gamePanel.currentSim.indexLocationRuangan)){ // atas
                    if (gamePanel.currentSim.rumah.ruanganRumah.get(gamePanel.currentSim.indexLocationRuangan).up != null){
                        teleport(1,5, 8, gamePanel.currentSim.rumah.ruanganRumah.indexOf(gamePanel.currentSim.rumah.ruanganRumah.get(gamePanel.currentSim.indexLocationRuangan).up));
                        gamePanel.currentSim.currentLocation = "Rumah " + gamePanel.currentSim.nama + " (" + gamePanel.currentSim.rumah.ruanganRumah.get(gamePanel.currentSim.indexLocationRuangan).name + ")";
                        // gamePanel.currentSim.indexLocationRuangan = gamePanel.currentSim.rumah.ruanganRumah.indexOf(gamePanel.currentSim.rumah.ruanganRumah.get(gamePanel.currentSim.indexLocationRuangan).up);
                    } else {
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.currentDialog = "Tidak ada ruangan di atas!";
                        canTouchEvent = false;
                    }
                    // UNTUK RUANGAN UTAMA BAWAH UNTUK TELEPORT KE WORLD
                // } else if (hit(1,5,8,"any",gamePanel.currentSim.indexLocationRuangan)){ 
                //     if (gamePanel.currentSim.rumah.ruanganRumah.get(gamePanel.currentSim.indexLocationRuangan).down != null){
                //         teleport(1,4, 1, gamePanel.currentSim.rumah.ruanganRumah.indexOf(gamePanel.currentSim.rumah.ruanganRumah.get(gamePanel.currentSim.indexLocationRuangan).down));
                //         gamePanel.currentSim.currentLocation = "Rumah " + gamePanel.currentSim.nama + " (" + gamePanel.currentSim.rumah.ruanganRumah.get(gamePanel.currentSim.indexLocationRuangan).name;
                //         // gamePanel.currentSim.indexLocationRuangan = gamePanel.currentSim.rumah.ruanganRumah.indexOf(gamePanel.currentSim.rumah.ruanganRumah.get(gamePanel.currentSim.indexLocationRuangan).down);
                //     } else {
                //         gamePanel.gameState = gamePanel.dialogState;
                //         gamePanel.ui.currentDialog = "Tidak ada ruangan di bawah!";
                //         canTouchEvent = false;
                //     }
                } else if (hit(1,1,5,"any",gamePanel.currentSim.indexLocationRuangan)) { // kiri
                    if (gamePanel.currentSim.rumah.ruanganRumah.get(gamePanel.currentSim.indexLocationRuangan).left != null){
                        teleport(1,8, 4, gamePanel.currentSim.rumah.ruanganRumah.indexOf(gamePanel.currentSim.rumah.ruanganRumah.get(gamePanel.currentSim.indexLocationRuangan).left));
                        gamePanel.currentSim.currentLocation = "Rumah " + gamePanel.currentSim.nama + " (" + gamePanel.currentSim.rumah.ruanganRumah.get(gamePanel.currentSim.indexLocationRuangan).name + ")";
                        // gamePanel.currentSim.indexLocationRuangan = gamePanel.currentSim.rumah.ruanganRumah.indexOf(gamePanel.currentSim.rumah.ruanganRumah.get(gamePanel.currentSim.indexLocationRuangan).left);
                    } else {
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.currentDialog = "Tidak ada ruangan di kiri!";
                        canTouchEvent = false;
                    }
                } else if (hit(1,8,4,"any",gamePanel.currentSim.indexLocationRuangan)) { // kanan
                    if (gamePanel.currentSim.rumah.ruanganRumah.get(gamePanel.currentSim.indexLocationRuangan).right != null){
                        teleport(1,1, 5, gamePanel.currentSim.rumah.ruanganRumah.indexOf(gamePanel.currentSim.rumah.ruanganRumah.get(gamePanel.currentSim.indexLocationRuangan).right));
                        gamePanel.currentSim.currentLocation = "Rumah " + gamePanel.currentSim.nama + " (" + gamePanel.currentSim.rumah.ruanganRumah.get(gamePanel.currentSim.indexLocationRuangan).name + ")";
                        // gamePanel.currentSim.indexLocationRuangan = gamePanel.currentSim.rumah.ruanganRumah.indexOf(gamePanel.currentSim.rumah.ruanganRumah.get(gamePanel.currentSim.indexLocationRuangan).right);
                    } else {
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.currentDialog = "Tidak ada ruangan di kanan!";
                        canTouchEvent = false;
                    }
                }
            } else { // SELAIN DI RUANGAN UTAMA DAN WORLD
                if (hit(1,4,1,"any",gamePanel.currentSim.indexLocationRuangan)){ // atas
                    if (gamePanel.currentSim.rumah.ruanganRumah.get(gamePanel.currentSim.indexLocationRuangan).up != null){
                        teleport(1,5, 8, gamePanel.currentSim.rumah.ruanganRumah.indexOf(gamePanel.currentSim.rumah.ruanganRumah.get(gamePanel.currentSim.indexLocationRuangan).up));
                        gamePanel.currentSim.currentLocation = "Rumah " + gamePanel.currentSim.nama + " (" + gamePanel.currentSim.rumah.ruanganRumah.get(gamePanel.currentSim.indexLocationRuangan).name + ")";
                        // gamePanel.currentSim.indexLocationRuangan = gamePanel.currentSim.rumah.ruanganRumah.indexOf(gamePanel.currentSim.rumah.ruanganRumah.get(gamePanel.currentSim.indexLocationRuangan).up);
                    } else {
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.currentDialog = "Tidak ada ruangan di atas!";
                        canTouchEvent = false;
                    }
                } else if (hit(1,5,8,"any",gamePanel.currentSim.indexLocationRuangan)){ // bawah
                    if (gamePanel.currentSim.rumah.ruanganRumah.get(gamePanel.currentSim.indexLocationRuangan).down != null){
                        teleport(1,4, 1, gamePanel.currentSim.rumah.ruanganRumah.indexOf(gamePanel.currentSim.rumah.ruanganRumah.get(gamePanel.currentSim.indexLocationRuangan).down));
                        gamePanel.currentSim.currentLocation = "Rumah " + gamePanel.currentSim.nama + " (" + gamePanel.currentSim.rumah.ruanganRumah.get(gamePanel.currentSim.indexLocationRuangan).name + ")";
                        // gamePanel.currentSim.indexLocationRuangan = gamePanel.currentSim.rumah.ruanganRumah.indexOf(gamePanel.currentSim.rumah.ruanganRumah.get(gamePanel.currentSim.indexLocationRuangan).down);
                    } else {
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.currentDialog = "Tidak ada ruangan di bawah!";
                        canTouchEvent = false;
                    }
                } else if (hit(1,1,5,"any",gamePanel.currentSim.indexLocationRuangan)) { // kiri
                    if (gamePanel.currentSim.rumah.ruanganRumah.get(gamePanel.currentSim.indexLocationRuangan).left != null){
                        teleport(1,8, 4, gamePanel.currentSim.rumah.ruanganRumah.indexOf(gamePanel.currentSim.rumah.ruanganRumah.get(gamePanel.currentSim.indexLocationRuangan).left));
                        gamePanel.currentSim.currentLocation = "Rumah " + gamePanel.currentSim.nama + " (" + gamePanel.currentSim.rumah.ruanganRumah.get(gamePanel.currentSim.indexLocationRuangan).name + ")";
                        // gamePanel.currentSim.indexLocationRuangan = gamePanel.currentSim.rumah.ruanganRumah.indexOf(gamePanel.currentSim.rumah.ruanganRumah.get(gamePanel.currentSim.indexLocationRuangan).left);
                    } else {
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.currentDialog = "Tidak ada ruangan di kiri!";
                        canTouchEvent = false;
                    }
                } else if (hit(1,8,4,"any",gamePanel.currentSim.indexLocationRuangan)) { // kanan
                    if (gamePanel.currentSim.rumah.ruanganRumah.get(gamePanel.currentSim.indexLocationRuangan).right != null){
                        teleport(1,1, 5, gamePanel.currentSim.rumah.ruanganRumah.indexOf(gamePanel.currentSim.rumah.ruanganRumah.get(gamePanel.currentSim.indexLocationRuangan).right));
                        gamePanel.currentSim.currentLocation = "Rumah " + gamePanel.currentSim.nama + " (" + gamePanel.currentSim.rumah.ruanganRumah.get(gamePanel.currentSim.indexLocationRuangan).name + ")";
                        // gamePanel.currentSim.indexLocationRuangan = gamePanel.currentSim.rumah.ruanganRumah.indexOf(gamePanel.currentSim.rumah.ruanganRumah.get(gamePanel.currentSim.indexLocationRuangan).right);
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
                gamePanel.currentSim.solidArea.x = gamePanel.currentSim.worldX + gamePanel.currentSim.solidArea.x;
                gamePanel.currentSim.solidArea.y = gamePanel.currentSim.worldY + gamePanel.currentSim.solidArea.y;
        
                eventRect[map][col][row].x = col*gamePanel.tileSize + eventRect[map][col][row].x;
                eventRect[map][col][row].y = row*gamePanel.tileSize + eventRect[map][col][row].y;
        
                if (gamePanel.currentSim.solidArea.intersects(eventRect[map][col][row]) && !eventRect[map][col][row].eventDone){
                    if (gamePanel.currentSim.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                        hit = true;
        
                        previousEventX = gamePanel.currentSim.worldX;
                        previousEventY = gamePanel.currentSim.worldY; 
                    }
                }
        
                gamePanel.currentSim.solidArea.x = gamePanel.currentSim.solidAreaDefaultX;
                gamePanel.currentSim.solidArea.y = gamePanel.currentSim.solidAreaDefaultY;
                eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
                eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
            }
        } else {
            if (map == gamePanel.currentMap){
                gamePanel.currentSim.solidArea.x = gamePanel.currentSim.worldX + gamePanel.currentSim.solidArea.x;
                gamePanel.currentSim.solidArea.y = gamePanel.currentSim.worldY + gamePanel.currentSim.solidArea.y;
        
                eventRect[map][col][row].x = col*gamePanel.tileSize + eventRect[map][col][row].x;
                eventRect[map][col][row].y = row*gamePanel.tileSize + eventRect[map][col][row].y;
        
                if (gamePanel.currentSim.solidArea.intersects(eventRect[map][col][row]) && !eventRect[map][col][row].eventDone){
                    if (gamePanel.currentSim.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                        hit = true;
        
                        previousEventX = gamePanel.currentSim.worldX;
                        previousEventY = gamePanel.currentSim.worldY; 
                    }
                }
        
                gamePanel.currentSim.solidArea.x = gamePanel.currentSim.solidAreaDefaultX;
                gamePanel.currentSim.solidArea.y = gamePanel.currentSim.solidAreaDefaultY;
                eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
                eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
            }
        }
        

        return hit;
    }

    public void teleport(int map, int col, int row, int indexRuangan){
        gamePanel.currentMap = map;
        gamePanel.currentSim.worldX = col*gamePanel.tileSize;
        gamePanel.currentSim.worldY = row*gamePanel.tileSize;
        previousEventX = gamePanel.currentSim.worldX;
        previousEventY = gamePanel.currentSim.worldY;
        canTouchEvent = false;
        gamePanel.currentSim.indexLocationRuangan = indexRuangan;
    }
}