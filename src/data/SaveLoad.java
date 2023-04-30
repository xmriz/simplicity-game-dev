package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import benda.*;
import entity.*;

import main.GamePanel;

public class SaveLoad {
    GamePanel gamePanel;

    public SaveLoad(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public Benda getObject(String itemName) {
        Benda obj = null;

        switch (itemName) {
            case "Ayam":
                obj = new BahanMakanan_Ayam();
                break;
            case "Bayam":
                obj = new BahanMakanan_Bayam();
                break;
            case "Kacang":
                obj = new BahanMakanan_Kacang();
                break;
            case "Kentang":
                obj = new BahanMakanan_Kentang();
                break;
            case "Nasi":
                obj = new BahanMakanan_Nasi();
                break;
            case "Sapi":
                obj = new BahanMakanan_Sapi();
                break;
            case "Wortel":
                obj = new BahanMakanan_Wortel();
                break;
            case "Susu":
                obj = new BahanMakanan_Susu();
                break;
            case "Jam":
                obj = new Furnitur_Jam(gamePanel);
                break;
            case "Kasur King Size":
                obj = new Furnitur_KasurKingSize(gamePanel);
                break;
            case "Kasur Queen Size":
                obj = new Furnitur_KasurQueenSize(gamePanel);
                break;
            case "Kasur Single":
                obj = new Furnitur_KasurSingle(gamePanel);
                break;
            case "Kompor Gas":
                obj = new Furnitur_KomporGas(gamePanel);
                break;
            case "Kompor Listrik":
                obj = new Furnitur_KomporListrik(gamePanel);
                break;
            case "Meja Kursi":
                obj = new Furnitur_MejaKursi(gamePanel);
                break;
            case "Toilet":
                obj = new Furnitur_Toilet(gamePanel);
                break;
            case "Bistik":
                obj = new Makanan_Bistik();
                break;
            case "Nasi Ayam":
                obj = new Makanan_NasiAyam();
                break;
            case "Nasi Kari":
                obj = new Makanan_NasiKari();
                break;
            case "Susu Kacang":
                obj = new Makanan_SusuKacang();
                break;
            case "Tumis Sayur":
                obj = new Makanan_TumisSayur();
                break;
            case "Lampu":
                obj = new Lampu(gamePanel);
                break;
            case "Meja PC":
                obj = new Furnitur_MejaPC(gamePanel);
                break;
            case "Pot Bunga":
                obj = new Furnitur_PotBunga(gamePanel);
                break;
            case "Radio":
                obj = new Furnitur_Radio(gamePanel);
                break;
            case "Rak Buku":
                obj = new Furnitur_RakBuku(gamePanel);
                break;
            case "Sajadah":
                obj = new Furnitur_Sajadah(gamePanel);
                break;
            case "TV":
                obj = new Furnitur_TV(gamePanel);
                break;
        }

        return obj;
    }

    public void save(String string) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(new File("src/res/" + string + ".dat")));
            DataStorage ds = new DataStorage();

            ds.jumlahSim = gamePanel.listSim.size();
            ds.indexCurrentSim = gamePanel.indexCurrentSim;
            System.out.println(ds.jumlahSim);

            for (int k = 0; k < gamePanel.listSim.size(); k++) {
                ds.infoSimStrings.add(gamePanel.listSim.get(k).nama);
                ds.infoSimStrings.add(gamePanel.listSim.get(k).pekerjaan);
                // ds.infoSimStrings.add(gamePanel.listSim.get(k).currentLocation);
                ds.infoSimIntegers.add(gamePanel.listSim.get(k).uang);
                ds.infoSimIntegers.add(gamePanel.listSim.get(k).kesehatan);
                ds.infoSimIntegers.add(gamePanel.listSim.get(k).kekenyangan);
                ds.infoSimIntegers.add(gamePanel.listSim.get(k).mood);
                // ds.infoSimIntegers.add(gamePanel.listSim.get(k).solidArea.x);
                // ds.infoSimIntegers.add(gamePanel.listSim.get(k).solidArea.y);
                // ds.infoSimIntegers.add(gamePanel.listSim.get(k).indexLocationRuangan);
                // ds.infoSimIntegers.add(gamePanel.listSim.get(k).indexBendaYangDisentuh);
                // ds.infoSimIntegers.add(gamePanel.listSim.get(k).indexRumahYangDimasuki);
                // ds.infoSimIntegers.add(gamePanel.listSim.get(k).currentMap);

                ds.itemNamess.add(k + "");
                ds.itemNamess.add(gamePanel.listSim.get(k).inventory.size() + "");
                ds.itemAmountss.add(k + "");
                ds.itemAmountss.add(gamePanel.listSim.get(k).inventory.size() + "");

                for (int z = 0; z < gamePanel.listSim.get(k).inventory.size(); z++) {
                    ds.itemNamess.add(gamePanel.listSim.get(k).inventory.get(z).name);
                    ds.itemAmountss.add(gamePanel.listSim.get(k).inventory.get(z).quantity + "");
                }

                ds.rumah.add(k);
                ds.rumah.add(gamePanel.listSim.get(k).rumah.jumlahRuangan);
                ds.rumah.add(gamePanel.listSim.get(k).rumah.worldX);
                ds.rumah.add(gamePanel.listSim.get(k).rumah.worldY);

                for (int i = 0; i < gamePanel.listSim.get(k).rumah.jumlahRuangan; i++) {
                    ds.ruangan.add(gamePanel.listSim.get(k).rumah.ruanganRumah.get(i).index);
                    ds.namaRuangan.add(gamePanel.listSim.get(k).rumah.ruanganRumah.get(i).name);
                    if (gamePanel.listSim.get(k).rumah.ruanganRumah.get(i).up != null) {
                        ds.ruangan.add(gamePanel.listSim.get(k).rumah.ruanganRumah.get(i).up.index);
                    } else {
                        ds.ruangan.add(null);
                    }
                    if (gamePanel.listSim.get(k).rumah.ruanganRumah.get(i).down != null) {
                        ds.ruangan.add(gamePanel.listSim.get(k).rumah.ruanganRumah.get(i).down.index);
                    } else {
                        ds.ruangan.add(null);
                    }
                    if (gamePanel.listSim.get(k).rumah.ruanganRumah.get(i).right != null) {
                        ds.ruangan.add(gamePanel.listSim.get(k).rumah.ruanganRumah.get(i).right.index);
                    } else {
                        ds.ruangan.add(null);
                    }
                    if (gamePanel.listSim.get(k).rumah.ruanganRumah.get(i).left != null) {
                        ds.ruangan.add(gamePanel.listSim.get(k).rumah.ruanganRumah.get(i).left.index);
                    } else {
                        ds.ruangan.add(null);
                    }
                    System.out.println(0);

                    ds.bendaRuangan.add(gamePanel.listSim.get(k).rumah.ruanganRumah.get(i).index
                            + "");
                    ds.bendaRuangan.add(gamePanel.listSim.get(k).rumah.ruanganRumah.get(i).bendaRuangan.size()
                            + "");
                    System.out.println(ds.bendaRuangan);
                    for (int j = 0; j < gamePanel.listSim.get(k).rumah.ruanganRumah.get(i).bendaRuangan.size(); j++) {
                        ds.bendaRuangan
                                .add(gamePanel.listSim.get(k).rumah.ruanganRumah.get(i).bendaRuangan.get(j).name);
                        ds.bendaRuangan
                                .add(gamePanel.listSim.get(k).rumah.ruanganRumah.get(i).bendaRuangan.get(j).worldX
                                        + "");
                        ds.bendaRuangan
                                .add(gamePanel.listSim.get(k).rumah.ruanganRumah.get(i).bendaRuangan.get(j).worldY
                                        + "");
                        ds.bendaRuangan
                                .add(gamePanel.listSim.get(k).rumah.ruanganRumah.get(i).bendaRuangan.get(j).solidArea.x
                                        + "");
                        ds.bendaRuangan
                                .add(gamePanel.listSim.get(k).rumah.ruanganRumah.get(i).bendaRuangan.get(j).solidArea.y
                                        + "");

                        System.out.println(8);
                        System.out.println(ds.bendaRuangan);
                    }

                }

            }

            // ds.nama = gamePanel.getCurrentSim().nama;
            // ds.pekerjaan = gamePanel.getCurrentSim().pekerjaan;
            // ds.uang = gamePanel.getCurrentSim().uang;
            // ds.kesehatan = gamePanel.getCurrentSim().kesehatan;
            // ds.kekenyangan = gamePanel.getCurrentSim().kekenyangan;
            // ds.mood = gamePanel.getCurrentSim().mood;
            // ds.lightUpdated = gamePanel.getCurrentSim().lightUpdated;
            // ds.currentLocation = gamePanel.getCurrentSim().currentLocation;
            // ds.indexLocationRuangan = gamePanel.getCurrentSim().indexLocationRuangan;
            // ds.indexBendaYangDisentuh = gamePanel.getCurrentSim().indexBendaYangDisentuh;
            // ds.indexRumahYangDimasuki = gamePanel.getCurrentSim().indexRumahYangDimasuki;
            // ds.currentMap = gamePanel.getCurrentSim().currentMap;

            // BNTR

            // for (int i = 0; i < gamePanel.getCurrentSim().inventory.size(); i++) {
            // ds.itemNames.add(gamePanel.getCurrentSim().inventory.get(i).name);
            // ds.itemAmounts.add(gamePanel.getCurrentSim().inventory.get(i).quantity);
            // }

            // ds.rumah.add(0);
            // ds.rumah.add(gamePanel.getCurrentSim().rumah.jumlahRuangan);
            // ds.rumah.add(gamePanel.getCurrentSim().rumah.worldX);
            // ds.rumah.add(gamePanel.getCurrentSim().rumah.worldY);

            // System.out.println("a");

            // for (int i = 0; i < gamePanel.getCurrentSim().rumah.jumlahRuangan; i++) {
            // ds.ruangan.add(gamePanel.getCurrentSim().rumah.ruanganRumah.get(i).index);
            // ds.namaRuangan.add(gamePanel.getCurrentSim().rumah.ruanganRumah.get(i).name);
            // if (gamePanel.getCurrentSim().rumah.ruanganRumah.get(i).up != null) {
            // ds.ruangan.add(gamePanel.getCurrentSim().rumah.ruanganRumah.get(i).up.index);
            // } else {
            // ds.ruangan.add(null);
            // }
            // if (gamePanel.getCurrentSim().rumah.ruanganRumah.get(i).down != null) {
            // ds.ruangan.add(gamePanel.getCurrentSim().rumah.ruanganRumah.get(i).down.index);
            // } else {
            // ds.ruangan.add(null);
            // }
            // if (gamePanel.getCurrentSim().rumah.ruanganRumah.get(i).right != null) {
            // ds.ruangan.add(gamePanel.getCurrentSim().rumah.ruanganRumah.get(i).right.index);
            // } else {
            // ds.ruangan.add(null);
            // }
            // if (gamePanel.getCurrentSim().rumah.ruanganRumah.get(i).left != null) {
            // ds.ruangan.add(gamePanel.getCurrentSim().rumah.ruanganRumah.get(i).left.index);
            // } else {
            // ds.ruangan.add(null);
            // }
            // System.out.println(0);

            // ds.bendaRuangan.add(gamePanel.getCurrentSim().rumah.ruanganRumah.get(i).index
            // + "");
            // ds.bendaRuangan.add(gamePanel.getCurrentSim().rumah.ruanganRumah.get(i).bendaRuangan.size()
            // + "");
            // System.out.println(ds.bendaRuangan);
            // for (int j = 0; j <
            // gamePanel.getCurrentSim().rumah.ruanganRumah.get(i).bendaRuangan.size(); j++)
            // {
            // ds.bendaRuangan.add(gamePanel.getCurrentSim().rumah.ruanganRumah.get(i).bendaRuangan.get(j).name);
            // ds.bendaRuangan
            // .add(gamePanel.getCurrentSim().rumah.ruanganRumah.get(i).bendaRuangan.get(j).worldX
            // + "");
            // ds.bendaRuangan
            // .add(gamePanel.getCurrentSim().rumah.ruanganRumah.get(i).bendaRuangan.get(j).worldY
            // + "");
            // System.out.println(8);
            // System.out.println(ds.bendaRuangan);
            // }

            // }
            // System.out.println(ds.bendaRuangan);

            // System.out.println(ds.ruangan);

            // Write the DataStorage object
            oos.writeObject(ds);

        } catch (Exception e) {
            System.out.println("Save Exception!");
        }
    }

    public void load(String string) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("src/res/" + string + ".dat")));
            DataStorage ds = (DataStorage) ois.readObject();
            gamePanel.indexCurrentSim = 0;

            System.out.println(ds.jumlahSim);
            System.out.println(ds.indexCurrentSim);
            for (int i = 1; i < gamePanel.listSim.size(); i++) {
                gamePanel.listSim.remove(gamePanel.listSim.size() - 1);
                gamePanel.listRumah[0].remove(gamePanel.listSim.size() - 1);
            }
            for (int k = 1; k < ds.jumlahSim; k++) {
                Sim sim = new Sim(gamePanel, gamePanel.keyHandler);
                gamePanel.listSim.add(sim);
                Rumah rumah = new Rumah(gamePanel);
                gamePanel.listRumah[0].add(rumah);
            }
            System.out.println(gamePanel.listSim.size());

            int sumStrings = 0;
            int sumIntegers = 0;
            int sumInventory = 0;
            int sumRumah = 0;
            int num = 0;
            int indexPencariBenda = 0;
            for (int j = 0; j < ds.jumlahSim; j++) {
                gamePanel.listSim.get(j).indexLocationRuangan = 0;
                gamePanel.listSim.get(j).indexRumahYangDimasuki = j;
                gamePanel.listSim.get(j).indexBendaYangDisentuh = 999;
                gamePanel.listSim.get(j).currentMap = 1;
                gamePanel.listSim.get(j).worldX = 2 * gamePanel.tileSize;
                gamePanel.listSim.get(j).worldY = 2 * gamePanel.tileSize;
                gamePanel.listSim.get(j).direction = "down";

                gamePanel.listSim.get(j).nama = ds.infoSimStrings.get(sumStrings);
                gamePanel.listSim.get(j).pekerjaan = ds.infoSimStrings.get(sumStrings + 1);
                sumStrings += 2;

                gamePanel.listSim.get(j).uang = ds.infoSimIntegers.get(sumIntegers);
                gamePanel.listSim.get(j).kesehatan = ds.infoSimIntegers.get(sumIntegers + 1);
                gamePanel.listSim.get(j).kekenyangan = ds.infoSimIntegers.get(sumIntegers + 2);
                gamePanel.listSim.get(j).mood = ds.infoSimIntegers.get(sumIntegers + 3);
                // gamePanel.listSim.get(j).solidArea.x = ds.infoSimIntegers.get(sumIntegers +
                // 4);
                // gamePanel.listSim.get(j).solidArea.y = ds.infoSimIntegers.get(sumIntegers +
                // 5);
                System.out.println(77);
                sumIntegers += 4;

                gamePanel.listSim.get(j).inventory.clear();

                int jumlahLoop = 0;
                System.out.println(22);
                for (int z = 0; z < Integer.parseInt(ds.itemNamess.get(sumInventory + 1)); z++) {
                    System.out.println(90);
                    gamePanel.listSim.get(j).inventory.add(getObject(ds.itemNamess.get(sumInventory + 2 + z)));
                    System.out.println(100);
                    System.out.println(ds.itemAmountss);
                    System.out.println(gamePanel.listSim.get(j).inventory.size());
                    System.out.println(gamePanel.listSim.get(j).inventory.get(z).name);
                    System.out.println(gamePanel.listSim.get(j).inventory.get(z).quantity);
                    gamePanel.listSim.get(j).inventory.get(z).quantity = Integer
                            .parseInt(ds.itemAmountss.get(sumInventory + 2 + z));
                    System.out.println(02);
                    jumlahLoop += 1;
                }
                sumInventory += jumlahLoop + 2;
                System.out.println(99);

                gamePanel.listSim.get(j).rumah.worldX = ds.rumah.get(sumRumah + 2);
                gamePanel.listSim.get(j).rumah.worldY = ds.rumah.get(sumRumah + 3);
                gamePanel.listSim.get(j).rumah.colRumah = ds.rumah.get(sumRumah + 2) /
                        gamePanel.tileSize;
                gamePanel.listSim.get(j).rumah.rowRumah = ds.rumah.get(sumRumah + 3) /
                        gamePanel.tileSize;
                gamePanel.listRumah[0].set(j, gamePanel.listSim.get(j).rumah);
                gamePanel.listSim.get(j).currentLocation = "Rumah "
                        + gamePanel.listSim.get(j).nama + " (Ruangan Utama)";

                Ruangan ruanganUtama = new Ruangan(gamePanel);
                gamePanel.listSim.get(j).rumah.ruanganRumah.clear();
                gamePanel.listSim.get(j).rumah.ruanganRumah.add(ruanganUtama);
                gamePanel.listSim.get(j).rumah.ruanganRumah.get(0).name = "Ruangan Utama";
                System.out.println(400);
                System.out.println(ds.rumah);
                System.out.println(sumRumah);

                System.out.println(ds.rumah.get(sumRumah + 1));

                for (int i = 0; i < ds.rumah.get(sumRumah + 1) - 1; i++) {
                    Ruangan ruangan = new Ruangan(gamePanel);
                    ruangan.name = ds.namaRuangan.get(i + 1);
                    gamePanel.listSim.get(j).rumah.ruanganRumah.add(ruangan);
                }

                System.out.println(12300);

                for (int i = 0; i < ds.rumah.get(sumRumah + 1); i++) {
                    System.out.println(ds.rumah.get(sumRumah + 1));
                    System.out.println(0);

                    if (ds.ruangan.get(num + 1) != null) {
                        gamePanel.listSim.get(j).rumah.ruanganRumah
                                .get(i).up = gamePanel.listSim.get(j).rumah.ruanganRumah.get(ds.ruangan.get(num + 1));
                    }
                    if (ds.ruangan.get(num + 2) != null) {
                        if (i != 0) {
                            gamePanel.listSim.get(j).rumah.ruanganRumah
                                    .get(i).down = gamePanel.listSim.get(j).rumah.ruanganRumah
                                            .get(ds.ruangan.get(num + 2));
                        }
                    }
                    if (ds.ruangan.get(num + 3) != null) {
                        gamePanel.listSim.get(j).rumah.ruanganRumah
                                .get(i).right = gamePanel.listSim.get(j).rumah.ruanganRumah
                                        .get(ds.ruangan.get(num + 3));
                    }
                    if (ds.ruangan.get(num + 4) != null) {
                        gamePanel.listSim.get(j).rumah.ruanganRumah
                                .get(i).left = gamePanel.listSim.get(j).rumah.ruanganRumah
                                        .get(ds.ruangan.get(num + 4));
                    }
                    num += 5;

                    int nums = 0;
                    for (int x = 0; x < Integer.parseInt(ds.bendaRuangan.get(indexPencariBenda +
                            1)); x++) {
                        gamePanel.listSim.get(j).rumah.ruanganRumah.get(i).bendaRuangan
                                .add(getObject(ds.bendaRuangan.get(indexPencariBenda + 2 + x * 5)));
                        gamePanel.listSim.get(j).rumah.ruanganRumah.get(i).bendaRuangan.get(x).worldX = Integer
                                .parseInt(ds.bendaRuangan.get(indexPencariBenda + 3 + x * 5));
                        gamePanel.listSim.get(j).rumah.ruanganRumah.get(i).bendaRuangan.get(x).worldY = Integer
                                .parseInt(ds.bendaRuangan.get(indexPencariBenda + 4 + x * 5));
                        gamePanel.listSim.get(j).rumah.ruanganRumah.get(i).bendaRuangan.get(x).solidArea.x = Integer
                                .parseInt(ds.bendaRuangan.get(indexPencariBenda + 5 + x * 5));
                        gamePanel.listSim.get(j).rumah.ruanganRumah.get(i).bendaRuangan.get(x).solidArea.y = Integer
                                .parseInt(ds.bendaRuangan.get(indexPencariBenda + 6 + x * 5));

                        nums += 1;
                    }
                    System.out.println(0);
                    indexPencariBenda += nums * 5;
                    indexPencariBenda += 2;
                    System.out.println(indexPencariBenda);
                    System.out.println(100);
                }
                sumRumah += 4;
                System.out.println(200);

            }
            System.out.println(1203);

            // System.out.println(gamePanel.listSim.get(0).nama);
            // System.out.println(gamePanel.listSim.get(1).nama);
            // System.out.println(gamePanel.listSim.size());

            // gamePanel.getCurrentSim().nama = ds.nama;
            // gamePanel.getCurrentSim().pekerjaan = ds.pekerjaan;
            // gamePanel.getCurrentSim().uang = ds.uang;
            // gamePanel.getCurrentSim().kesehatan = ds.kesehatan;
            // gamePanel.getCurrentSim().kekenyangan = ds.kekenyangan;
            // gamePanel.getCurrentSim().mood = ds.mood;
            // gamePanel.getCurrentSim().lightUpdated = ds.lightUpdated;
            // gamePanel.getCurrentSim().currentLocation = ds.currentLocation;
            // gamePanel.getCurrentSim().indexLocationRuangan = ds.indexLocationRuangan;
            // gamePanel.getCurrentSim().indexBendaYangDisentuh = ds.indexBendaYangDisentuh;
            // gamePanel.getCurrentSim().indexRumahYangDimasuki = ds.indexRumahYangDimasuki;
            // gamePanel.getCurrentSim().currentMap = ds.currentMap;

            // RABAS

            // gamePanel.getCurrentSim().inventory.clear();
            // for (int i = 0; i < ds.itemNames.size(); i++) {
            // gamePanel.getCurrentSim().inventory.add(getObject(ds.itemNames.get(i)));
            // gamePanel.getCurrentSim().inventory.get(i).quantity = ds.itemAmounts.get(i);
            // }

            // gamePanel.getCurrentSim().rumah.worldX = ds.rumah.get(2);
            // gamePanel.getCurrentSim().rumah.worldY = ds.rumah.get(3);
            // gamePanel.getCurrentSim().rumah.colRumah = ds.rumah.get(2) /
            // gamePanel.tileSize;
            // gamePanel.getCurrentSim().rumah.rowRumah = ds.rumah.get(3) /
            // gamePanel.tileSize;

            // Ruangan ruanganUtama = gamePanel.getCurrentSim().rumah.ruanganRumah.get(0);
            // gamePanel.getCurrentSim().rumah.ruanganRumah.clear();
            // gamePanel.getCurrentSim().rumah.ruanganRumah.add(ruanganUtama);
            // gamePanel.getCurrentSim().rumah.ruanganRumah.get(0).name = "Ruangan Utama";
            // for (int i = 0; i < ds.rumah.get(1) - 1; i++) {
            // Ruangan ruangan = new Ruangan(gamePanel);
            // ruangan.name = ds.namaRuangan.get(i + 1);
            // gamePanel.getCurrentSim().rumah.ruanganRumah.add(ruangan);
            // }

            // int num = 0;
            // int indexPencariBenda = 0;
            // for (int i = 0; i < ds.rumah.get(1); i++) {

            // if (ds.ruangan.get(num + 1) != null) {
            // gamePanel.getCurrentSim().rumah.ruanganRumah
            // .get(i).up =
            // gamePanel.getCurrentSim().rumah.ruanganRumah.get(ds.ruangan.get(num + 1));
            // }
            // if (ds.ruangan.get(num + 2) != null) {
            // if (i != 0) {
            // gamePanel.getCurrentSim().rumah.ruanganRumah
            // .get(i).down = gamePanel.getCurrentSim().rumah.ruanganRumah
            // .get(ds.ruangan.get(num + 2));
            // }
            // }
            // if (ds.ruangan.get(num + 3) != null) {
            // gamePanel.getCurrentSim().rumah.ruanganRumah
            // .get(i).right =
            // gamePanel.getCurrentSim().rumah.ruanganRumah.get(ds.ruangan.get(num + 3));
            // }
            // if (ds.ruangan.get(num + 4) != null) {
            // gamePanel.getCurrentSim().rumah.ruanganRumah
            // .get(i).left =
            // gamePanel.getCurrentSim().rumah.ruanganRumah.get(ds.ruangan.get(num + 4));
            // }
            // num += 5;

            // int nums = 0;
            // for (int j = 0; j < Integer.parseInt(ds.bendaRuangan.get(indexPencariBenda +
            // 1)); j++) {
            // gamePanel.getCurrentSim().rumah.ruanganRumah.get(i).bendaRuangan
            // .add(getObject(ds.bendaRuangan.get(indexPencariBenda + 2 + j * 3)));
            // gamePanel.getCurrentSim().rumah.ruanganRumah.get(i).bendaRuangan.get(j).worldX
            // = Integer
            // .parseInt(ds.bendaRuangan.get(indexPencariBenda + 3 + j * 3));
            // gamePanel.getCurrentSim().rumah.ruanganRumah.get(i).bendaRuangan.get(j).worldY
            // = Integer
            // .parseInt(ds.bendaRuangan.get(indexPencariBenda + 4 + j * 3));
            // System.out.println(gamePanel.getCurrentSim().rumah.ruanganRumah.get(i).bendaRuangan.size());
            // System.out.println(gamePanel.getCurrentSim().rumah.ruanganRumah.get(i).bendaRuangan.get(j).name);
            // System.out.println(gamePanel.getCurrentSim().rumah.ruanganRumah.get(i).bendaRuangan.get(j).worldX);
            // System.out.println(gamePanel.getCurrentSim().rumah.ruanganRumah.get(i).bendaRuangan.get(j).worldY);
            // nums += 1;
            // }
            // System.out.println(0);
            // indexPencariBenda += nums * 3;
            // indexPencariBenda += 2;
            // System.out.println(indexPencariBenda);
            // System.out.println(100);
            // }
            gamePanel.indexCurrentSim = ds.indexCurrentSim;

            System.out.println('a');

        } catch (

        Exception e) {
            System.out.println("Load Exception!");
        }
    }
}
