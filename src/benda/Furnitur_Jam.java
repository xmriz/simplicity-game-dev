package benda;

import java.awt.Rectangle;

public class Furnitur_Jam extends Furnitur{
    public Furnitur_Jam(){
        name = "Jam";
        image = setupImage("benda/furnitur/jam");
        collision = false;
        dimensiX = 1;
        dimensiY = 1;
        harga = 10;
        aksi = "Melihat waktu";
        solidArea = new Rectangle(0, 0, 48*dimensiX, 48*dimensiY);
    }

    @Override
    public void action(){
        // aksi melihat waktu
    }
}
