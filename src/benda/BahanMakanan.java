package benda;

import entity.*;

public abstract class BahanMakanan extends Benda {
    private int harga;
    private int kekenyangan;
    public BahanMakanan(){
        setCategory("Bahan Makanan");
        setStackable(true);
    }

    public int getHarga(){
        return harga;
    }

    public void setHarga(int harga){
        this.harga=harga;
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
