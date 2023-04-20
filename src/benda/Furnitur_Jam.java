package benda;

public class Furnitur_Jam extends Furnitur{
    public Furnitur_Jam(){
        name = "Jam";
        image = setupImage("benda/furnitur/jam");
        collision = true;
        dimensiX = 1;
        dimensiY = 1;
        harga = 10;
        aksi = "Melihat waktu";

    }
}
