package entity;

import main.*;
import benda.*;

public class NPC_Penjual extends Entity{
    
    public NPC_Penjual(GamePanel gamePanel) {
        super(gamePanel);

        setDirection("down");

        getImage();
        setDialog();
        setItems();
    }

    public void getImage(){
        setUp1(setupImage("npc/penjual"));
        setUp2(setupImage("npc/penjual"));
        setDown1(setupImage("npc/penjual"));
        setDown2(setupImage("npc/penjual"));
        setLeft1(setupImage("npc/penjual"));
        setLeft2(setupImage("npc/penjual"));
       setRight1(setupImage("npc/penjual"));
        setRight2(setupImage("npc/penjual"));
    }

    public void setDialog(){
        dialogs.add("Hahaha... Haloo, \nada yang bisa saya bantu?");
    }

    public void setItems(){
        // BARANG YANG BISA DIJUAL
        // BAHAN MAKANAN
        inventory.add(new BahanMakanan_Ayam());
        inventory.add(new BahanMakanan_Bayam());
        inventory.add(new BahanMakanan_Kacang());
        inventory.add(new BahanMakanan_Kentang());
        inventory.add(new BahanMakanan_Nasi());
        inventory.add(new BahanMakanan_Sapi());
        inventory.add(new BahanMakanan_Susu());
        inventory.add(new BahanMakanan_Wortel());

        // FURNITUR
        inventory.add(new Furnitur_KasurSingle(gamePanel));
        inventory.add(new Furnitur_KasurQueenSize(gamePanel));
        inventory.add(new Furnitur_KasurKingSize(gamePanel));
        inventory.add(new Furnitur_Toilet(gamePanel));
        inventory.add(new Furnitur_KomporGas(gamePanel));
        inventory.add(new Furnitur_KomporListrik(gamePanel));
        inventory.add(new Furnitur_MejaKursi(gamePanel));
        inventory.add(new Furnitur_Jam(gamePanel));
        inventory.add(new Furnitur_TV(gamePanel));
        inventory.add(new Furnitur_Shower(gamePanel));
        inventory.add(new Furnitur_Sajadah(gamePanel));
        inventory.add(new Furnitur_RakBuku(gamePanel));
        inventory.add(new Furnitur_Radio(gamePanel));
        inventory.add(new Furnitur_PotBunga(gamePanel));
        inventory.add(new Furnitur_MejaPC(gamePanel));

        // LAMPU
        inventory.add(new Lampu(gamePanel));
    }

    public void speak(){
        super.speak();
        gamePanel.gameState = gamePanel.beliState;
    }
}
