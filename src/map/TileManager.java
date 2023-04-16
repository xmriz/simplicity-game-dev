package map;

import java.awt.Graphics2D;
import java.io.*;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
    GamePanel gamePanel;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        tile = new Tile[12];
        mapTileNum = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
        getTileImage();
        loadMap("data/maps/world.txt");
    }

    public void getTileImage(){
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new FileInputStream(new File("assets/world/grass.png")));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new FileInputStream(new File("assets/world/water_center.png")));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new FileInputStream(new File("assets/world/water_up.png")));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(new FileInputStream(new File("assets/world/water_down.png")));
            tile[3].collision = true;

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(new FileInputStream(new File("assets/world/water_left.png")));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(new FileInputStream(new File("assets/world/water_right.png")));
            tile[5].collision = true;

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(new FileInputStream(new File("assets/world/water_corner1.png")));
            tile[6].collision = true;

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(new FileInputStream(new File("assets/world/water_corner2.png")));
            tile[7].collision = true;

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(new FileInputStream(new File("assets/world/water_corner3.png")));
            tile[8].collision = true;

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(new FileInputStream(new File("assets/world/water_corner4.png")));
            tile[9].collision = true;

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(new FileInputStream(new File("assets/world/floor.png")));

            tile[11] = new Tile();
            tile[11].image = ImageIO.read(new FileInputStream(new File("assets/world/wall.png")));
            tile[11].collision = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String mapPath){
        try {
            BufferedReader br = new BufferedReader(new FileReader(mapPath));

            int row = 0;
            int col = 0;

            while (row < gamePanel.maxWorldRow && col < gamePanel.maxWorldCol){
                String line = br.readLine();
                while (col < gamePanel.maxWorldCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gamePanel.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();    
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2d){
        int worldCol = 0; 
        int worldRow = 0;

        while (worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow){
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gamePanel.tileSize; // position of the tile in the world 
            int worldY = worldRow * gamePanel.tileSize;
            int screenX = worldX - gamePanel.sim.worldX + gamePanel.sim.screenX; // position of the tile in the screen
            int screenY = worldY - gamePanel.sim.worldY + gamePanel.sim.screenY;

            if (worldX - gamePanel.tileSize < gamePanel.sim.worldX + gamePanel.sim.screenX && worldX + gamePanel.tileSize > gamePanel.sim.worldX - gamePanel.sim.screenX
                && worldY - gamePanel.tileSize < gamePanel.sim.worldY + gamePanel.sim.screenY && worldY + gamePanel.tileSize > gamePanel.sim.worldY - gamePanel.sim.screenY){
                    g2d.drawImage(tile[tileNum].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
            }

            worldCol++;

            if (worldCol == gamePanel.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
