package data;

import java.io.Serializable;
import java.util.*;
import java.util.List;
import java.util.ArrayList;
import entity.*;

public class DataStorage implements Serializable {

    int jumlahSim;
    int indexCurrentSim;
    int worldTimeCounter;
    boolean isOneSim;

    // SIM
    ArrayList<String> infoSimStrings = new ArrayList<>();
    ArrayList<Integer> infoSimIntegers = new ArrayList<>();
    ArrayList<Boolean> infoSimBooleans = new ArrayList<>();

    String nama;
    String pekerjaan;
    int uang;
    int kesehatan;
    int kekenyangan;
    int mood;
    boolean lightUpdated;
    // String currentLocation;
    // int indexLocationRuangan;
    // int indexBendaYangDisentuh;
    // int indexRumahYangDimasuki;
    // int currentMap;

    // public Rumah rumah;
    // Benda currentLight;

    ArrayList<String> itemNames = new ArrayList<>();
    ArrayList<Integer> itemAmounts = new ArrayList<>();
    List<List<String>> listItemNames = new ArrayList<>();
    List<List<Integer>> listItemAmounts = new ArrayList<>();

    ArrayList<String> itemNamess = new ArrayList<>();
    ArrayList<String> itemAmountss = new ArrayList<>();

    ArrayList<Integer> rumah = new ArrayList<>();
    ArrayList<Integer> ruangan = new ArrayList<>();
    ArrayList<String> bendaRuangan = new ArrayList<>();
    ArrayList<String> namaRuangan = new ArrayList<>();
}
