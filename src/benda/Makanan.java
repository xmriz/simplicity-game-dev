package benda;

import java.util.*;

import entity.Sim;

public abstract class Makanan extends Benda {
    private List<String> bahan = new ArrayList<>();
    private int kekenyangan;
    public Makanan(){
        category = "Makanan";
        stackable = true;
    }

    public void eat(Sim sim){
        sim.kekenyangan += kekenyangan;
        if (sim.kekenyangan > sim.maxKekenyangan){
            sim.kekenyangan = sim.maxKekenyangan;
        }
    }

    public int getKekenyangan(){
        return kekenyangan;
    }

    public List<String> getBahan(){
        return bahan;
    }

    public void setKekenyangan(int kekenyangan){
        this.kekenyangan=kekenyangan;
    }

    public void setBahan(List<String> bahan){
        this.bahan=bahan;
    }

}
