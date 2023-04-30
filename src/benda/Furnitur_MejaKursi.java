package benda;

import java.awt.Rectangle;

import main.GamePanel;

public class Furnitur_MejaKursi extends Furnitur{
    public Furnitur_MejaKursi(GamePanel gamePanel){
        name = "Meja Kursi";
        image = setupImage("benda/furnitur/meja_kursi");
        collision = false;
        dimensiX = 3;
        dimensiY = 3;
        harga = 50;
        aksi = "Makan";
        solidArea = new Rectangle(0, 0, 48*dimensiX, 48*dimensiY);
        this.gamePanel = gamePanel;
    }

    @Override
    public void action(){
        // aksi makan
        gamePanel.gameState = gamePanel.inventoryState;
    }
}
