package benda;

import java.awt.Rectangle;

public class Furnitur_Toilet extends Furnitur{
    public Furnitur_Toilet(){
        name = "Toilet";
        image = setupImage("benda/furnitur/toilet");
        collision = false;
        dimensiX = 1;
        dimensiY = 1;
        harga = 50;
        aksi = "Buang air";
        solidArea = new Rectangle(0, 0, 48*dimensiX, 48*dimensiY);

    }

    @Override
    public void action(){
        // aksi buang air
    }
}
