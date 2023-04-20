package benda;

public class Makanan_NasiAyam extends Makanan{
    public Makanan_NasiAyam(){
        name = "Nasi Ayam";
        image = setupImage("benda/makanan/nasi_ayam");
        bahan.add("Nasi");
        bahan.add("Ayam");
        kekenyangan = 16;
    }
}
