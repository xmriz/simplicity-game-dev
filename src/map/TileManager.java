package map;

import java.awt.Graphics2D;
import java.io.*;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {
    GamePanel gamePanel;
    public Tile[] tile;
    public int[][][] mapTileNum;

    public TileManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        tile = new Tile[14]; // jumlah tile yang ada
        mapTileNum = new int[gamePanel.maxMap][gamePanel.maxWorldCol][gamePanel.maxWorldRow];
        getTileImage();
        loadMap("data/maps/world.txt", 0);
        loadMap("data/maps/ruangan.txt", 1);
    }

    public void getTileImage(){
            setupImage(0, "grass", false);
            setupImage(1, "water_center", true);
            setupImage(2, "water_up", true);
            setupImage(3, "water_down", true);
            setupImage(4, "water_left", true);
            setupImage(5, "water_right", true);
            setupImage(6, "water_corner1", true);
            setupImage(7, "water_corner2", true);
            setupImage(8, "water_corner3", true);
            setupImage(9, "water_corner4", true);
            setupImage(10, "floor", false);
            setupImage(11, "wall", true);
            setupImage(12, "stair", false);
            setupImage(13, "blank", true);
    }

    public void setupImage(int index, String imagePath, boolean collision){
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(new FileInputStream(new File("assets/tiles/" + imagePath +".png")));
            tile[index].image = UtilityTool.scaleImage(tile[index].image, gamePanel.tileSize, gamePanel.tileSize);
            tile[index].collision = collision;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadMap(String mapPath, int map){
        try {
            BufferedReader br = new BufferedReader(new FileReader(mapPath));

            int row = 0;
            int col = 0;

            while (row < gamePanel.maxWorldRow && col < gamePanel.maxWorldCol){
                String line = br.readLine();
                while (col < gamePanel.maxWorldCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[map][col][row] = num;
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

    public void draw(Graphics2D g2d, int indexRuangan){
        int worldCol = 0; 
        int worldRow = 0;
        int tileNum;

        while (worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow){
            if (gamePanel.currentMap == 0){
                tileNum = mapTileNum[gamePanel.currentMap][worldCol][worldRow];
            } else{
                tileNum = gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(indexRuangan).mapRuangan[worldCol][worldRow];
            }

            int worldX = worldCol * gamePanel.tileSize; // position of the tile in the world 
            int worldY = worldRow * gamePanel.tileSize;
            int screenX = worldX - gamePanel.listSim.get(gamePanel.indexCurrentSim).worldX + gamePanel.listSim.get(gamePanel.indexCurrentSim).screenX; // position of the tile in the screen
            int screenY = worldY - gamePanel.listSim.get(gamePanel.indexCurrentSim).worldY + gamePanel.listSim.get(gamePanel.indexCurrentSim).screenY;

            if (worldX - gamePanel.tileSize < gamePanel.listSim.get(gamePanel.indexCurrentSim).worldX + gamePanel.listSim.get(gamePanel.indexCurrentSim).screenX && worldX + gamePanel.tileSize > gamePanel.listSim.get(gamePanel.indexCurrentSim).worldX - gamePanel.listSim.get(gamePanel.indexCurrentSim).screenX
                && worldY - gamePanel.tileSize < gamePanel.listSim.get(gamePanel.indexCurrentSim).worldY + gamePanel.listSim.get(gamePanel.indexCurrentSim).screenY && worldY + gamePanel.tileSize > gamePanel.listSim.get(gamePanel.indexCurrentSim).worldY - gamePanel.listSim.get(gamePanel.indexCurrentSim).screenY){
                    g2d.drawImage(tile[tileNum].image, screenX, screenY, null);
            }

            worldCol++;

            if (worldCol == gamePanel.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
