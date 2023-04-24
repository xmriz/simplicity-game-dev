package benda;

import java.awt.Rectangle;

public class Furnitur_KasurQueenSize extends Furnitur {
    public Furnitur_KasurQueenSize(){
        name = "Kasur Queen Size";
        image = setupImage("benda/furnitur/kasur_queen_size");
        collision = true;
        dimensiX = 4;
        dimensiY = 2;
        harga = 100;
        aksi = "Tidur";
        solidArea = new Rectangle(0, 0, 48*dimensiX, 48*dimensiY);

    }
}
