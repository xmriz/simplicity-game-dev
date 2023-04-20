package benda;

import java.util.*;

public abstract class Makanan extends Benda {
    public List<String> bahan = new ArrayList<>();
    public int kekenyangan;
    public Makanan(){
        category = "Makanan";
    }
}
