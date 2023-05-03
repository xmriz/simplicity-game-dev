package benda;

import java.util.*;

import entity.Sim;

public abstract class Makanan extends Benda {
    private List<String> bahan = new ArrayList<>();
    private int kekenyangan;
    public Makanan(){
        setCategory("Makanan");
        setStackable(true);
    }

    public List<String> getBahan(){
        return bahan;
    }

    public void setBahan(List<String> bahan){
        this.bahan=bahan;
    }

    public int getKekenyangan(){
        return kekenyangan;
    }

    public void setKekenyangan(int kekenyangan){
        this.kekenyangan=kekenyangan;
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
