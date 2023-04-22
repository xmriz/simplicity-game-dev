package benda;

import entity.*;

public abstract class BahanMakanan extends Benda {
    public int harga;
    public int kekenyangan;
    public BahanMakanan(){
        category = "Bahan Makanan";
        stackable = true;
    }

    public void eat(Sim sim){
        sim.kekenyangan += kekenyangan;
        if (sim.kekenyangan > sim.maxKekenyangan){
            sim.kekenyangan = sim.maxKekenyangan;
        }
    }
} 
