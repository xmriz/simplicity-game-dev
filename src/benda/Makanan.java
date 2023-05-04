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
        sim.setKekenyangan(sim.getKekenyangan()+kekenyangan);
        if (sim.getKekenyangan() > sim.maxKekenyangan){
            sim.setKekenyangan(sim.maxKekenyangan);
        }
        if (sim.getMood() > sim.maxMood) {
            sim.setMood(sim.maxMood);
        }
        if (sim.getKesehatan() > sim.maxKesehatan) {
            sim.setKesehatan(sim.maxKesehatan);
        }
    }

}
