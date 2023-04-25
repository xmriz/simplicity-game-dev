package benda;

import java.awt.Rectangle;

public class Furnitur_MejaKursi extends Furnitur{
    public Furnitur_MejaKursi(){
        name = "Meja Kursi";
        image = setupImage("benda/furnitur/meja_kursi");
        collision = false;
        dimensiX = 3;
        dimensiY = 3;
        harga = 50;
        aksi = "Makan";
        solidArea = new Rectangle(0, 0, 48*dimensiX, 48*dimensiY);

    }
}
