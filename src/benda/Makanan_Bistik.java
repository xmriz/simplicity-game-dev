package benda;

public class Makanan_Bistik extends Makanan{
    public Makanan_Bistik(){
        name = "Bistik";
        image = setupImage("benda/makanan/bistik");
        getBahan().add("Kentang");
        getBahan().add("Sapi");
        setKekenyangan(22);
    }
}
