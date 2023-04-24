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
            if (hit(0,gamePanel.sim.rumah.dimensiX, gamePanel.sim.rumah.dimensiY, "any",999)){
                teleport(1,2,2,0);
                gamePanel.sim.locationRuangan = 0;
            } else if (hit(1,5,8, "any", 0)){
                teleport(0,gamePanel.sim.rumah.dimensiX, gamePanel.sim.rumah.dimensiY, 999);
            }
        }
    }

    public boolean hit(int map, int col, int row, String reqDirection, int indexRuangan){
        boolean hit = false;
        
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

        return hit;
    }

    public void teleport(int map, int col, int row, int indexRuangan){
        gamePanel.currentMap = map;
        gamePanel.sim.worldX = col*gamePanel.tileSize;
        gamePanel.sim.worldY = row*gamePanel.tileSize;
        previousEventX = gamePanel.sim.worldX;
        previousEventY = gamePanel.sim.worldY;
        canTouchEvent = false;   
    }
}