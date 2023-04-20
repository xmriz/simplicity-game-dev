package benda;

public class Makanan_Bistik extends Makanan{
    public Makanan_Bistik(){
        name = "Bistik";
        image = setupImage("benda/makanan/bistik");
        bahan.add("Kentang");
        bahan.add("Sapi");
        kekenyangan = 22;
    }
}
