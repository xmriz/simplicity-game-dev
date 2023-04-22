package benda;

public abstract class Furnitur extends Benda{
    public int dimensiX;
    public int dimensiY;
    public int harga;
    public String aksi;
    public Furnitur(){
        category = "Furnitur";
        stackable = true;
    }
} 
