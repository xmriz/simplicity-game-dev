package benda;

public class Furnitur_Toilet extends Furnitur{
    public Furnitur_Toilet(){
        name = "Toilet";
        image = setupImage("benda/furnitur/toilet");
        collision = true;
        dimensiX = 1;
        dimensiY = 1;
        harga = 50;
        aksi = "Buang air";
    }
}
