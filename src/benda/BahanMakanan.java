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
