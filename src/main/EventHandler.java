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
        int xDistance = Math.abs(gamePanel.listSim.get(gamePanel.indexCurrentSim).worldX - previousEventX);
        int yDistance = Math.abs(gamePanel.listSim.get(gamePanel.indexCurrentSim).worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gamePanel.tileSize){
            canTouchEvent = true;
        }
        
        // checking for some event
        if (canTouchEvent){
            // for (int i = 0; i < gamePanel.listSim.size(); i++){
            //     if (gamePanel.currentMap == 0){ // DI WORLD
            //         if (hit(0,gamePanel.listSim.get(i).rumah.colRumah, gamePanel.listSim.get(i).rumah.rowRumah, "any",999)){
            //             teleport(1,5,8,0);
            //             gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan = 0;
            //             gamePanel.listSim.get(gamePanel.indexCurrentSim).currentLocation = "Rumah " + gamePanel.listSim.get(i).nama + " (" + gamePanel.listSim.get(i).rumah.ruanganRumah.get(gamePanel.listSim.get(i).indexLocationRuangan).name + ")";
            //         }    
            //     } else if (gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan == 0){ // DI RUANGAN UTAMA
            //         if (hit(1,5,8, "any", 0)){
            //             teleport(0,gamePanel.listSim.get(i).rumah.colRumah, gamePanel.listSim.get(i).rumah.rowRumah, 999);
            //             gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan = 999;
            //             gamePanel.listSim.get(gamePanel.indexCurrentSim).currentLocation = "World";
            //         } else if (hit(1,4,1,"any",gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan)){ // atas
            //             if (gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).up != null){
            //                 teleport(1,5, 8, gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).up));
            //                 gamePanel.listSim.get(gamePanel.indexCurrentSim).currentLocation = "Rumah " + gamePanel.listSim.get(gamePanel.indexCurrentSim).nama + " (" + UtilityTool.capitalizeFirstLetter(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).name) + ")";
            //                 // gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan = gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).up);
            //             } else {
            //                 gamePanel.gameState = gamePanel.dialogState;
            //                 gamePanel.ui.currentDialog = "Tidak ada ruangan di atas!";
            //                 canTouchEvent = false;
            //             }
            //             // UNTUK RUANGAN UTAMA BAWAH UNTUK TELEPORT KE WORLD
            //         // } else if (hit(1,5,8,"any",gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan)){ 
            //         //     if (gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).down != null){
            //         //         teleport(1,4, 1, gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).down));
            //         //         gamePanel.listSim.get(gamePanel.indexCurrentSim).currentLocation = "Rumah " + gamePanel.listSim.get(gamePanel.indexCurrentSim).nama + " (" + UtilityTool.capitalizeFirstLetter(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).name);
            //         //         // gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan = gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).down);
            //         //     } else {
            //         //         gamePanel.gameState = gamePanel.dialogState;
            //         //         gamePanel.ui.currentDialog = "Tidak ada ruangan di bawah!";
            //         //         canTouchEvent = false;
            //         //     }
            //         } else if (hit(1,1,5,"any",gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan)) { // kiri
            //             if (gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).left != null){
            //                 teleport(1,8, 4, gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).left));
            //                 gamePanel.listSim.get(gamePanel.indexCurrentSim).currentLocation = "Rumah " + gamePanel.listSim.get(gamePanel.indexCurrentSim).nama + " (" + UtilityTool.capitalizeFirstLetter(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).name) + ")";
            //                 // gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan = gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).left);
            //             } else {
            //                 gamePanel.gameState = gamePanel.dialogState;
            //                 gamePanel.ui.currentDialog = "Tidak ada ruangan di kiri!";
            //                 canTouchEvent = false;
            //             }
            //         } else if (hit(1,8,4,"any",gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan)) { // kanan
            //             if (gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).right != null){
            //                 teleport(1,1, 5, gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).right));
            //                 gamePanel.listSim.get(gamePanel.indexCurrentSim).currentLocation = "Rumah " + gamePanel.listSim.get(gamePanel.indexCurrentSim).nama + " (" + UtilityTool.capitalizeFirstLetter(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).name) + ")";
            //                 // gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan = gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).right);
            //             } else {
            //                 gamePanel.gameState = gamePanel.dialogState;
            //                 gamePanel.ui.currentDialog = "Tidak ada ruangan di kanan!";
            //                 canTouchEvent = false;
            //             }
            //         }
            //     }
            // }

            // --- BATAS --- //
            if (gamePanel.currentMap == 0){
                if (hit(0,gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.colRumah, gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.rowRumah, "any",999)){
                    teleport(1,5,8,0);
                    gamePanel.listSim.get(gamePanel.indexCurrentSim).currentLocation = "Rumah " + gamePanel.listSim.get(gamePanel.indexCurrentSim).nama + " (" + UtilityTool.capitalizeFirstLetter(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).name) + ")";
                }    
            } else if (gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan == 0){
                if (hit(1,5,8, "any", 999)){
                    teleport(0,gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.colRumah, gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.rowRumah, 999);
                    gamePanel.listSim.get(gamePanel.indexCurrentSim).currentLocation = "World";
                } else if (hit(1,4,1,"any",gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan)){ // atas
                    if (gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).up != null){
                        teleport(1,5, 8, gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).up));
                        gamePanel.listSim.get(gamePanel.indexCurrentSim).currentLocation = "Rumah " + gamePanel.listSim.get(gamePanel.indexCurrentSim).nama + " (" + UtilityTool.capitalizeFirstLetter(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).name) + ")";
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan = gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).up);
                    } else {
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.currentDialog = "Tidak ada ruangan di atas!";
                        canTouchEvent = false;
                    }
                    // UNTUK RUANGAN UTAMA BAWAH UNTUK TELEPORT KE WORLD
                // } else if (hit(1,5,8,"any",gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan)){ 
                //     if (gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).down != null){
                //         teleport(1,4, 1, gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).down));
                //         gamePanel.listSim.get(gamePanel.indexCurrentSim).currentLocation = "Rumah " + gamePanel.listSim.get(gamePanel.indexCurrentSim).nama + " (" + UtilityTool.capitalizeFirstLetter(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).name);
                //         // gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan = gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).down);
                //     } else {
                //         gamePanel.gameState = gamePanel.dialogState;
                //         gamePanel.ui.currentDialog = "Tidak ada ruangan di bawah!";
                //         canTouchEvent = false;
                //     }
                } else if (hit(1,1,5,"any",gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan)) { // kiri
                    if (gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).left != null){
                        teleport(1,8, 4, gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).left));
                        gamePanel.listSim.get(gamePanel.indexCurrentSim).currentLocation = "Rumah " + gamePanel.listSim.get(gamePanel.indexCurrentSim).nama + " (" + UtilityTool.capitalizeFirstLetter(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).name) + ")";
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan = gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).left);
                    } else {
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.currentDialog = "Tidak ada ruangan di kiri!";
                        canTouchEvent = false;
                    }
                } else if (hit(1,8,4,"any",gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan)) { // kanan
                    if (gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).right != null){
                        teleport(1,1, 5, gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).right));
                        gamePanel.listSim.get(gamePanel.indexCurrentSim).currentLocation = "Rumah " + gamePanel.listSim.get(gamePanel.indexCurrentSim).nama + " (" + UtilityTool.capitalizeFirstLetter(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).name) + ")";
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan = gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).right);
                    } else {
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.currentDialog = "Tidak ada ruangan di kanan!";
                        canTouchEvent = false;
                    }
                }
            } else { // SELAIN DI RUANGAN UTAMA DAN WORLD
                if (hit(1,4,1,"any",gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan)){ // atas
                    if (gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).up != null){
                        teleport(1,5, 8, gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).up));
                        gamePanel.listSim.get(gamePanel.indexCurrentSim).currentLocation = "Rumah " + gamePanel.listSim.get(gamePanel.indexCurrentSim).nama + " (" + UtilityTool.capitalizeFirstLetter(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).name) + ")";
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan = gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).up);
                    } else {
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.currentDialog = "Tidak ada ruangan di atas!";
                        canTouchEvent = false;
                    }
                } else if (hit(1,5,8,"any",gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan)){ // bawah
                    if (gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).down != null){
                        teleport(1,4, 1, gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).down));
                        gamePanel.listSim.get(gamePanel.indexCurrentSim).currentLocation = "Rumah " + gamePanel.listSim.get(gamePanel.indexCurrentSim).nama + " (" + UtilityTool.capitalizeFirstLetter(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).name) + ")";
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan = gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).down);
                    } else {
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.currentDialog = "Tidak ada ruangan di bawah!";
                        canTouchEvent = false;
                    }
                } else if (hit(1,1,5,"any",gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan)) { // kiri
                    if (gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).left != null){
                        teleport(1,8, 4, gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).left));
                        gamePanel.listSim.get(gamePanel.indexCurrentSim).currentLocation = "Rumah " + gamePanel.listSim.get(gamePanel.indexCurrentSim).nama + " (" + UtilityTool.capitalizeFirstLetter(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).name) + ")";
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan = gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).left);
                    } else {
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.currentDialog = "Tidak ada ruangan di kiri!";
                        canTouchEvent = false;
                    }
                } else if (hit(1,8,4,"any",gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan)) { // kanan
                    if (gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).right != null){
                        teleport(1,1, 5, gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).right));
                        gamePanel.listSim.get(gamePanel.indexCurrentSim).currentLocation = "Rumah " + gamePanel.listSim.get(gamePanel.indexCurrentSim).nama + " (" + UtilityTool.capitalizeFirstLetter(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).name) + ")";
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan = gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).right);
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
                gamePanel.listSim.get(gamePanel.indexCurrentSim).solidArea.x = gamePanel.listSim.get(gamePanel.indexCurrentSim).worldX + gamePanel.listSim.get(gamePanel.indexCurrentSim).solidArea.x;
                gamePanel.listSim.get(gamePanel.indexCurrentSim).solidArea.y = gamePanel.listSim.get(gamePanel.indexCurrentSim).worldY + gamePanel.listSim.get(gamePanel.indexCurrentSim).solidArea.y;
        
                eventRect[map][col][row].x = col*gamePanel.tileSize + eventRect[map][col][row].x;
                eventRect[map][col][row].y = row*gamePanel.tileSize + eventRect[map][col][row].y;
        
                if (gamePanel.listSim.get(gamePanel.indexCurrentSim).solidArea.intersects(eventRect[map][col][row]) && !eventRect[map][col][row].eventDone){
                    if (gamePanel.listSim.get(gamePanel.indexCurrentSim).direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                        hit = true;
        
                        previousEventX = gamePanel.listSim.get(gamePanel.indexCurrentSim).worldX;
                        previousEventY = gamePanel.listSim.get(gamePanel.indexCurrentSim).worldY; 
                    }
                }
        
                gamePanel.listSim.get(gamePanel.indexCurrentSim).solidArea.x = gamePanel.listSim.get(gamePanel.indexCurrentSim).solidAreaDefaultX;
                gamePanel.listSim.get(gamePanel.indexCurrentSim).solidArea.y = gamePanel.listSim.get(gamePanel.indexCurrentSim).solidAreaDefaultY;
                eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
                eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
            }
        } else {
            if (map == gamePanel.currentMap){
                gamePanel.listSim.get(gamePanel.indexCurrentSim).solidArea.x = gamePanel.listSim.get(gamePanel.indexCurrentSim).worldX + gamePanel.listSim.get(gamePanel.indexCurrentSim).solidArea.x;
                gamePanel.listSim.get(gamePanel.indexCurrentSim).solidArea.y = gamePanel.listSim.get(gamePanel.indexCurrentSim).worldY + gamePanel.listSim.get(gamePanel.indexCurrentSim).solidArea.y;
        
                eventRect[map][col][row].x = col*gamePanel.tileSize + eventRect[map][col][row].x;
                eventRect[map][col][row].y = row*gamePanel.tileSize + eventRect[map][col][row].y;
        
                if (gamePanel.listSim.get(gamePanel.indexCurrentSim).solidArea.intersects(eventRect[map][col][row]) && !eventRect[map][col][row].eventDone){
                    if (gamePanel.listSim.get(gamePanel.indexCurrentSim).direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                        hit = true;
        
                        previousEventX = gamePanel.listSim.get(gamePanel.indexCurrentSim).worldX;
                        previousEventY = gamePanel.listSim.get(gamePanel.indexCurrentSim).worldY; 
                    }
                }
        
                gamePanel.listSim.get(gamePanel.indexCurrentSim).solidArea.x = gamePanel.listSim.get(gamePanel.indexCurrentSim).solidAreaDefaultX;
                gamePanel.listSim.get(gamePanel.indexCurrentSim).solidArea.y = gamePanel.listSim.get(gamePanel.indexCurrentSim).solidAreaDefaultY;
                eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
                eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
            }
        }
        

        return hit;
    }

    public void teleport(int map, int col, int row, int indexRuangan){
        // if (map == 0)
        gamePanel.currentMap = map; // pindahin sim ke map 1
        gamePanel.listSim.get(gamePanel.indexCurrentSim).worldX = col*gamePanel.tileSize; // pindahin sim ke kolom 1
        gamePanel.listSim.get(gamePanel.indexCurrentSim).worldY = row*gamePanel.tileSize;
        previousEventX = gamePanel.listSim.get(gamePanel.indexCurrentSim).worldX;
        previousEventY = gamePanel.listSim.get(gamePanel.indexCurrentSim).worldY;
        canTouchEvent = false;
        gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan = indexRuangan;
    }
}