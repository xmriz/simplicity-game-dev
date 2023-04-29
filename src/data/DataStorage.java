package data;

import java.io.Serializable;
import java.util.*;
import java.util.List;
import java.util.ArrayList;
import entity.*;

public class DataStorage implements Serializable {
    // SIM
    String nama;
    String pekerjaan;
    int uang;
    int kesehatan;
    int kekenyangan;
    int mood;
    boolean lightUpdated;
    String currentLocation;
    int indexLocationRuangan;
    int indexBendaYangDisentuh;
    int indexRumahYangDimasuki;
    int currentMap;

    // public Rumah rumah;
    // Benda currentLight;

    ArrayList<String> itemNames = new ArrayList<>();
    ArrayList<Integer> itemAmounts = new ArrayList<>();

    ArrayList<Integer> rumah = new ArrayList<>();
    ArrayList<Integer> ruangan = new ArrayList<>();
    ArrayList<String> bendaRuangan = new ArrayList<>();
    ArrayList<String> namaRuangan = new ArrayList<>();
}
