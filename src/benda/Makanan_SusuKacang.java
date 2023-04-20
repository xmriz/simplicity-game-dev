package benda;

public class Makanan_SusuKacang extends Makanan{
    public Makanan_SusuKacang(){
        name = "Susu Kacang";
        image = setupImage("benda/makanan/susu_kacang");
        bahan.add("Susu");
        bahan.add("Kacang");
        kekenyangan = 5;
    }
}
