package benda;

import java.util.*;

import entity.Sim;

public abstract class Makanan extends Benda {
    public List<String> bahan = new ArrayList<>();
    public int kekenyangan;
    public Makanan(){
        category = "Makanan";
    }

    public void eat(Sim sim){
        sim.kekenyangan += kekenyangan;
        if (sim.kekenyangan > sim.maxKekenyangan){
            sim.kekenyangan = sim.maxKekenyangan;
        }
    }

}
