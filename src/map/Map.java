package map;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
// import java.awt.AlphaComposite;
// import java.awt.Color;

import main.GamePanel;

public class Map extends TileManager {
    GamePanel gamePanel;
    public BufferedImage worldMap[];
    public boolean mapOn = false;

    public Map(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        createWorldMap();
    }

    public void createWorldMap() {
        worldMap = new BufferedImage[gamePanel.maxMap];
        int worldMapWidth = gamePanel.tileSize * gamePanel.maxWorldCol;
        int worldMapHeight = gamePanel.tileSize * gamePanel.maxWorldRow;

        for (int i = 0; i < gamePanel.maxMap; i++) {
            worldMap[i] = new BufferedImage(worldMapWidth, worldMapHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = (Graphics2D) worldMap[i].createGraphics();

            int col = 0;
            int row = 0;

            while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {
                int tileNum = mapTileNum[i][col][row];
                int x = gamePanel.tileSize * col;
                int y = gamePanel.tileSize * row;
                g2d.drawImage(tile[tileNum].image, x, y, null);

                // if (gamePanel.listRumah[gamePanel.currentMap].get(i) != null) {
                // g2d.drawImage(gamePanel.listRumah[gamePanel.currentMap].get(i).image, x, y,
                // null);
                // }

                col++;
                if (col == gamePanel.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
        }
    }

    // public void drawFullMapScreen(Graphics2D g2d) {
    // // Background Color
    // g2d.setColor(Color.black);
    // g2d.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);

    // // Draw Map
    // int width = 500;
    // int height = 500;
    // int x = gamePanel.screenWidth / 2 - width / 2;
    // int y = gamePanel.screenHeight / 2 - height / 2;
    // g2d.drawImage(worldMap[gamePanel.currentMap], x, y, width, height, null);

    // // Draw Player
    // double scale = (double) (gamePanel.tileSize * gamePanel.maxWorldCol) / width;
    // int playerX = (int) (x + gamePanel.getCurrentSim().getWorldX() / scale);
    // int playerY = (int) (y + gamePanel.getCurrentSim().getWorldY() / scale);
    // int playerSize = (int) (gamePanel.tileSize / scale) * 5;
    // g2d.drawImage(gamePanel.getCurrentSim().down1, playerX, playerY, playerSize,
    // playerSize, null);

    // }

    // public void drawMiniMap(Graphics2D g2d) {
    // if (mapOn == true) {
    // // Draw Map
    // int width = 200;
    // int height = 200;
    // int x = gamePanel.screenWidth - width - 50;
    // int y = 50;

    // g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
    // g2d.drawImage(worldMap[gamePanel.currentMap], x, y, width, height, null);

    // // Draw Player
    // double scale = (double) (gamePanel.tileSize * gamePanel.maxWorldCol) / width;
    // int playerX = (int) (x + gamePanel.currentSim.screenX / scale);
    // int playerY = (int) (y + gamePanel.currentSim.screenY / scale);
    // int playerSize = (int) (gamePanel.tileSize / scale);
    // g2d.drawImage(gamePanel.currentSim.down1, playerX, playerY, playerSize,
    // playerSize, null);

    // g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    // }
    // }
}
