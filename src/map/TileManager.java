package map;

import java.awt.Graphics2D;
import java.io.*;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {
    GamePanel gamePanel;
    private Tile[] tile;
    private int[][][] mapTileNum;

    public TileManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        tile = new Tile[15]; // jumlah tile yang ada
        mapTileNum = new int[gamePanel.getMaxMap()][gamePanel.getMaxWorldCol()][gamePanel.getMaxWorldRow()];
        getTileImage();
        loadMap("data/maps/world.txt", 0);
        loadMap("data/maps/ruangan.txt", 1);
    }
    
    public Tile[] getTile() {
        return tile;
    }


    public void setTile(Tile[] tile) {
        this.tile = tile;
    }


    public int[][][] getMapTileNum() {
        return mapTileNum;
    }


    public void setMapTileNum(int[][][] mapTileNum) {
        this.mapTileNum = mapTileNum;
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
            setupImage(12, "door", false);
            setupImage(13, "blank", true);
            setupImage(14, "door2", false);
    }

    public void setupImage(int index, String imagePath, boolean collision){
        try {
            tile[index] = new Tile();
            tile[index].setImage(ImageIO.read(new FileInputStream(new File("assets/tiles/" + imagePath +".png"))));
            tile[index].setImage(UtilityTool.scaleImage(tile[index].getImage(), gamePanel.getTileSize(), gamePanel.getTileSize()));
            tile[index].setCollision(collision);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void loadMap(String mapPath, int map){
        try {
            BufferedReader br = new BufferedReader(new FileReader(mapPath));

            int row = 0;
            int col = 0;

            while (row < gamePanel.getMaxWorldRow() && col < gamePanel.getMaxWorldCol()){
                String line = br.readLine();
                while (col < gamePanel.getMaxWorldCol()){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[map][col][row] = num;
                    col++;
                }
                if (col == gamePanel.getMaxWorldCol()){
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

        while (worldCol < gamePanel.getMaxWorldCol() && worldRow < gamePanel.getMaxWorldRow()){
            if (gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap() == 0){
                tileNum = mapTileNum[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()][worldCol][worldRow];
            } else{
                tileNum = gamePanel.getListSim().get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah().get(indexRuangan).getMapRuangan()[worldCol][worldRow];
            }

            int worldX = worldCol * gamePanel.getTileSize(); // position of the tile in the world 
            int worldY = worldRow * gamePanel.getTileSize();
            int screenX = worldX - gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getWorldX() + gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getScreenX(); // position of the tile in the screen
            int screenY = worldY - gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getWorldY() + gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getScreenY();

            if (worldX - gamePanel.getTileSize() < gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getWorldX() + gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getScreenX() && worldX + gamePanel.getTileSize() > gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getWorldX() - gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getScreenX()
                && worldY - gamePanel.getTileSize() < gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getWorldY() + gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getScreenY() && worldY + gamePanel.getTileSize() > gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getWorldY() - gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getScreenY()){
                    g2d.drawImage(tile[tileNum].getImage(), screenX, screenY, null);
            }

            worldCol++;

            if (worldCol == gamePanel.getMaxWorldCol()){
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
