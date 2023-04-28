package benda;

import java.awt.Rectangle;

import main.GamePanel;

public class Furnitur_KomporListrik extends Furnitur{
    public Furnitur_KomporListrik(GamePanel gamePanel){
        name = "Kompor Listrik";
        image = setupImage("benda/furnitur/kompor_listrik");
        collision = false;
        dimensiX = 1;
        dimensiY = 1;
        harga = 200;
        aksi = "Memasak";
        solidArea = new Rectangle(0, 0, 48*dimensiX, 48*dimensiY);
        this.gamePanel = gamePanel;
    }

    @Override
    public void action(){
        // aksi masak
        System.out.println("Aksi Masak");
    }
}
