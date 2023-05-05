package benda;

public class Makanan_NasiKari extends Makanan{
    public Makanan_NasiKari(){
        setName("Nasi Kari");
        setImage(setupImage("benda/makanan/nasi_kari"));
        getBahan().add("Nasi");
        getBahan().add("Kentang");
        getBahan().add("Wortel");
        getBahan().add("Sapi");
        setKekenyangan(30);
    }
}