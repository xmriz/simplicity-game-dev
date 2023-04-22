package benda;

public class Makanan_NasiKari extends Makanan{
    public Makanan_NasiKari(){
        name = "Nasi Kari";
        image = setupImage("benda/makanan/nasi_kari");
        getBahan().add("Nasi");
        getBahan().add("Kentang");
        getBahan().add("Wortel");
        getBahan().add("Sapi");
        setKekenyangan(30);
    }
}
