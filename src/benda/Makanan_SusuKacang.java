package benda;

public class Makanan_SusuKacang extends Makanan{
    public Makanan_SusuKacang(){
        setName("Susu Kacang");
        setImage(setupImage("benda/makanan/susu_kacang"));
        getBahan().add("Susu");
        getBahan().add("Kacang");
        setKekenyangan(5);
    }
}