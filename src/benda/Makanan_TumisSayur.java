package benda;

public class Makanan_TumisSayur extends Makanan {
    public Makanan_TumisSayur() {
        setName("Tumis Sayur");
        setImage(setupImage("benda/makanan/tumis_sayur"));
        getBahan().add("Wortel");
        getBahan().add("Bayam");
        setKekenyangan(5);
    }
}