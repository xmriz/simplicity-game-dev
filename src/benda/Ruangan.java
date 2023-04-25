package benda;

import map.TileManager;

import main.GamePanel;

public class Ruangan {
    TileManager tileManager;

    public String name = "Ruangan";
    public Ruangan up;
    public Ruangan down;
    public Ruangan left;
    public Ruangan right;

    public Benda bendaRuangan[] = new Benda[10]; // maksimal 10 benda di ruangan, sil : ini kalau lu mau buat jadi list juga sabi biar add nya gampang

    public int[][] mapRuangan; // setara dengan mapTileNum[1] di TileManager

    public Ruangan(GamePanel gamePanel) {
        tileManager = gamePanel.tileManager;
        mapRuangan = tileManager.mapTileNum[1];
    }

    
}
