package entity;

import main.*;
import benda.*;

public class NPC_Penjual extends Entity {

    public NPC_Penjual(GamePanel gamePanel) {
        super(gamePanel);

        setDirection("down");
        getImage();
        setDialog();
        setItems();
    }

    public void getImage() {
        setUp1(setupImage("npc/penjual"));
        setUp2(setupImage("npc/penjual"));
        setDown1(setupImage("npc/penjual"));
        setDown2(setupImage("npc/penjual"));
        setLeft1(setupImage("npc/penjual"));
        setLeft2(setupImage("npc/penjual"));
        setRight1(setupImage("npc/penjual"));
        setRight2(setupImage("npc/penjual"));
    }

    public void setDialog() {
        getDialogs().add("Hahaha... Haloo, \nada yang bisa saya bantu?");
    }

    public void setItems() {
        // BARANG YANG BISA DIJUAL
        // BAHAN MAKANAN
        getInventory().add(new BahanMakanan_Ayam());
        getInventory().add(new BahanMakanan_Bayam());
        getInventory().add(new BahanMakanan_Kacang());
        getInventory().add(new BahanMakanan_Kentang());
        getInventory().add(new BahanMakanan_Nasi());
        getInventory().add(new BahanMakanan_Sapi());
        getInventory().add(new BahanMakanan_Susu());
        getInventory().add(new BahanMakanan_Wortel());

        // FURNITUR
        getInventory().add(new Furnitur_KasurSingle(gamePanel));
        getInventory().add(new Furnitur_KasurQueenSize(gamePanel));
        getInventory().add(new Furnitur_KasurKingSize(gamePanel));
        getInventory().add(new Furnitur_Toilet(gamePanel));
        getInventory().add(new Furnitur_KomporGas(gamePanel));
        getInventory().add(new Furnitur_KomporListrik(gamePanel));
        getInventory().add(new Furnitur_MejaKursi(gamePanel));
        getInventory().add(new Furnitur_Jam(gamePanel));
        getInventory().add(new Furnitur_TV(gamePanel));
        getInventory().add(new Furnitur_Shower(gamePanel));
        getInventory().add(new Furnitur_Sajadah(gamePanel));
        getInventory().add(new Furnitur_RakBuku(gamePanel));
        getInventory().add(new Furnitur_Radio(gamePanel));
        getInventory().add(new Furnitur_PotBunga(gamePanel));
        getInventory().add(new Furnitur_MejaPC(gamePanel));

        // LAMPU
        getInventory().add(new Lampu(gamePanel));
    }

    public void speak() {
        super.speak();
        gamePanel.setGameState(gamePanel.getBeliState());
    }
}
