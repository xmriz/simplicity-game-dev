package benda;

import main.GamePanel;

public class Lampu extends Benda {
    public String aksi;
    public int harga = 10;

    public Lampu(GamePanel gamePanel){
        super();
        name = "Lampu";
        image = setupImage("benda/lampu");
        aksi = "Menerangi sekeliling kamu";
        category = "Equipment";
        stackable = true;
    }
}
