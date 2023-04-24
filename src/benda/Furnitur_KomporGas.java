package benda;

public class Furnitur_KomporGas extends Furnitur{
    public Furnitur_KomporGas(){
        name = "Kompor Gas";
        image = setupImage("benda/furnitur/kompor_gas");
        collision = true;
        dimensiX = 2;
        dimensiY = 1;
        harga = 100;
        aksi = "Memasak";
    }
}
