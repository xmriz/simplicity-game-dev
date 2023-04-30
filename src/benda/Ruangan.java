package benda;

import map.TileManager;

import main.GamePanel;

import java.util.*;;

public class Ruangan {
    TileManager tileManager;

    public String name = "Ruangan";
    public int index = 0;
    public Ruangan up;
    public Ruangan down;
    public Ruangan left;
    public Ruangan right;

    // public Benda bendaRuangan[] = new Benda[10]; // maksimal 10 benda di ruangan,
    // sil : ini kalau lu mau buat jadi list juga sabi biar add nya gampang
    public List<Benda> bendaRuangan = new ArrayList<>();

    public int[][] mapRuangan; // setara dengan mapTileNum[1] di TileManager

    public Ruangan(GamePanel gamePanel) {
        tileManager = gamePanel.tileManager;
        mapRuangan = tileManager.mapTileNum[1];
    }

    // public int getJumlahBendaRuangan{
    // return bendaRuangan.size();
    // }

}
