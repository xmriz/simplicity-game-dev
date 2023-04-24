package benda;

public class Furnitur_KomporListrik extends Furnitur{
    public Furnitur_KomporListrik(){
        name = "Kompor Listrik";
        image = setupImage("benda/furnitur/kompor_listrik");
        collision = true;
        dimensiX = 1;
        dimensiY = 1;
        harga = 200;
        aksi = "Memasak";
    }
}
