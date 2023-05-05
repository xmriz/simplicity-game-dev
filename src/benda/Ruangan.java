package benda;

import map.TileManager;

import main.GamePanel;

import java.util.*;;

public class Ruangan {
    TileManager tileManager;

    private String name = "Ruangan";
    private int index = 0;
    private Ruangan up;
    private Ruangan down;
    private Ruangan left;
    private Ruangan right;
    private List<Benda> bendaRuangan = new ArrayList<>();
    private int[][] mapRuangan; // setara dengan mapTileNum[1] di TileManager

    public List<Benda> getBendaRuangan() {
        return bendaRuangan;
    }

    public void setBendaRuangan(List<Benda> bendaRuangan) {
        this.bendaRuangan = bendaRuangan;
    }

    public int[][] getMapRuangan() {
        return mapRuangan;
    }

    public void setMapRuangan(int[][] mapRuangan) {
        this.mapRuangan = mapRuangan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Ruangan getUp() {
        return up;
    }

    public void setUp(Ruangan up) {
        this.up = up;
    }

    public Ruangan getDown() {
        return down;
    }

    public void setDown(Ruangan down) {
        this.down = down;
    }

    public Ruangan getLeft() {
        return left;
    }

    public void setLeft(Ruangan left) {
        this.left = left;
    }

    public Ruangan getRight() {
        return right;
    }

    public void setRight(Ruangan right) {
        this.right = right;
    }

    public Ruangan(GamePanel gamePanel) {
        tileManager = gamePanel.getTileManager();
        mapRuangan = tileManager.getMapTileNum()[1];
    }
}
