package benda;

public class Makanan_NasiAyam extends Makanan{
    public Makanan_NasiAyam(){
        setName("Nasi Ayam");
        setImage(setupImage("benda/makanan/nasi_ayam"));
        getBahan().add("Nasi");
        getBahan().add("Ayam");
        setKekenyangan(16);
    }
}