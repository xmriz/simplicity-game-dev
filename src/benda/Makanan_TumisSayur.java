package benda;

public class Makanan_TumisSayur extends Makanan {
    public Makanan_TumisSayur() {
        name = "Tumis Sayur";
        image = setupImage("benda/makanan/tumis_sayur");
        getBahan().add("Wortel");
        getBahan().add("Bayam");
        setKekenyangan(5);
    }
}
