package data;

import java.io.Serializable;
import java.util.*;

public class DataStorage implements Serializable {

    // Membuat Variabel Save
    int jumlahSim;
    int indexCurrentSim;
    int worldTimeCounter;
    int worldTimeSatuHariCounter;
    boolean isOneSim;
    boolean isCanAddSim;

    ArrayList<String> infoSimStrings = new ArrayList<>();
    ArrayList<Integer> infoSimIntegers = new ArrayList<>();
    ArrayList<Boolean> infoSimBooleans = new ArrayList<>();

    ArrayList<String> itemNamess = new ArrayList<>();
    ArrayList<String> itemAmountss = new ArrayList<>();

    ArrayList<Integer> rumah = new ArrayList<>();
    ArrayList<Integer> ruangan = new ArrayList<>();
    ArrayList<String> bendaRuangan = new ArrayList<>();
    ArrayList<String> namaRuangan = new ArrayList<>();
}
