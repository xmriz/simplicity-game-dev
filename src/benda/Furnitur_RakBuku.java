package benda;

import java.awt.Rectangle;

import main.GamePanel;

public class Furnitur_RakBuku extends Furnitur{
    public Furnitur_RakBuku(GamePanel gamePanel){
        name = "Rak Buku";
        image = setupImage("benda/furnitur/rak_buku");
        collision = false;
        dimensiX = 2;
        dimensiY = 1;
        harga = 50;
        aksi = "Baca Buku";
        solidArea = new Rectangle(0, 0, 48*dimensiX, 48*dimensiY);
        this.gamePanel = gamePanel;
    }

    @Override
    public void action(){
        // aksi masak
        System.out.println("Aksi Baca Buku");
        gamePanel.gameState = gamePanel.inputDurasiBacaBukuState;
    }

}