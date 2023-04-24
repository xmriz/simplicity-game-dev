package benda;

import main.GamePanel;

public class Lampu extends Benda {
    public String aksi;
    public int harga = 10;

    public Lampu(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        name = "Lampu";
        image = setupImage("benda/lampu");
        aksi = "Menyalakan lampu";
        category = "Equipment";
        stackable = true;
    }
}
