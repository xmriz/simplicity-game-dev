package benda;

import java.util.*;

import entity.Sim;

public abstract class Makanan extends Benda {
    public List<String> bahan = new ArrayList<>();
    public int kekenyangan;
    public Makanan(){
        category = "Makanan";
        stackable = true;
    }

    public void eat(Sim sim){
        sim.kekenyangan += kekenyangan;
        if (sim.kekenyangan > sim.maxKekenyangan){
            sim.kekenyangan = sim.maxKekenyangan;
        }
        if (sim.mood > sim.maxMood) {
            sim.mood = sim.maxMood;
        }
        if (sim.kesehatan > sim.maxKesehatan) {
            sim.kesehatan = sim.maxKesehatan;
        }
    }

}
