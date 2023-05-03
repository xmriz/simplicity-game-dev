package benda;

public class Makanan_Bistik extends Makanan{
    public Makanan_Bistik(){
        setName("Bistik");
        setImage(setupImage("benda/makanan/bistik"));
        getBahan().add("Kentang");
        getBahan().add("Sapi");
        setKekenyangan(22);
    }
}