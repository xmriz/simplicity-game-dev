package benda;

import java.awt.Rectangle;

public class Furnitur_KasurKingSize extends Furnitur{
    public Furnitur_KasurKingSize(){
        name = "Kasur King Size";
        image = setupImage("benda/furnitur/kasur_king_size");
        collision = true;
        dimensiX = 5;
        dimensiY = 2;
        harga = 150;
        aksi = "Tidur";
        solidArea = new Rectangle(0, 0, 48*dimensiX, 48*dimensiY);
    }
}
