package benda;

public class Rumah extends Benda{
    public int dimensiX;
    public int dimensiY;
    
    public Rumah(){
        name = "Rumah";
        image = setupImage("benda/rumah");
        dimensiX = 10;
        dimensiY = 10;
    }
}
