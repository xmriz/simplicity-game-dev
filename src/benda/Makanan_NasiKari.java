package benda;

public class Makanan_NasiKari extends Makanan{
    public Makanan_NasiKari(){
        name = "Nasi Kari";
        image = setupImage("benda/makanan/nasi_kari");
        bahan.add("Nasi");
        bahan.add("Kentang");
        bahan.add("Wortel");
        bahan.add("Sapi");
        kekenyangan = 30;
    }
}
