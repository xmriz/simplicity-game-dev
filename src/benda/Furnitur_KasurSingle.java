package benda;

import java.awt.Rectangle;

public class Furnitur_KasurSingle extends Furnitur{
    public Furnitur_KasurSingle(){
        name = "Kasur Single";
        image = setupImage("benda/furnitur/kasur_single");
        collision = false;
        dimensiX = 4;
        dimensiY = 1;
        harga = 50;
        aksi = "Tidur";
        solidArea = new Rectangle(0, 0, 48*dimensiX, 48*dimensiY);
        
    }

    public void action(){
        // aksi tidur
    }
}
