package benda;

public class Furnitur_KasurSingle extends Furnitur{
    public Furnitur_KasurSingle(){
        name = "Kasur Single";
        image = setupImage("benda/furnitur/kasur_single");
        collision = true;
        dimensiX = 4;
        dimensiY = 1;
        harga = 50;
        aksi = "Tidur";

    }
}
