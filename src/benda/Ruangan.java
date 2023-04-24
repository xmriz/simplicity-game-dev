package benda;

import map.TileManager;

import main.GamePanel;

public class Ruangan {
    TileManager tileManager;

    public String name = "Ruangan";
    Ruangan up;
    Ruangan down;
    Ruangan left;
    Ruangan right;

    public Furnitur furnitur[];

    public int[][] mapRuangan;

    public Ruangan(GamePanel gamePanel) {
        tileManager = gamePanel.tileManager;
        mapRuangan = tileManager.mapTileNum[1];
    }
}
