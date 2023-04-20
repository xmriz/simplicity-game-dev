package benda;

public class Makanan_TumisSayur extends Makanan {
    public Makanan_TumisSayur() {
        name = "Tumis Sayur";
        image = setupImage("benda/makanan/tumis_sayur");
        bahan.add("Wortel");
        bahan.add("Bayam");
        kekenyangan = 5;
    }
}
