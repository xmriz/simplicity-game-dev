package benda;

public class Furnitur_MejaKursi extends Furnitur{
    public Furnitur_MejaKursi(){
        name = "Meja Kursi";
        image = setupImage("benda/furnitur/meja_kursi");
        collision = true;
        dimensiX = 3;
        dimensiY = 3;
        harga = 50;
        aksi = "Makan";
    }
}
