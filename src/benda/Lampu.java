package benda;

import main.GamePanel;

public class Lampu extends Benda {
    private String aksi;
    private int harga = 10;


    public Lampu(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        setName("Lampu");
        setImage(setupImage("benda/lampu"));
        aksi = "Menyalakan lampu";
        setCategory("Equipment");
        setStackable(true);
    }

    public String getAksi(){
        return aksi;
    }

    public void setAksi(String aksi){
        this.aksi=aksi;
    }

    public int getHarga(){
        return harga;
    }

    public void setHarga(int harga){
        this.harga=harga;
    }
}