package benda;

import java.awt.Rectangle;

import main.GamePanel;

public class Furnitur_Toilet extends Furnitur{
    public Furnitur_Toilet(GamePanel gamePanel){
        name = "Toilet";
        image = setupImage("benda/furnitur/toilet");
        collision = false;
        dimensiX = 1;
        dimensiY = 1;
        harga = 50;
        aksi = "Buang air";
        solidArea = new Rectangle(0, 0, 48*dimensiX, 48*dimensiY);
        this.gamePanel = gamePanel;
    }

    @Override
    public void action(){
        // aksi buang air
        System.out.println("Aksi Buang Air");
    }
}
