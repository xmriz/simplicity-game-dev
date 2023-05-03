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
            ds.isOneSim = gamePanel.isOneSim;
            ds.worldTimeCounter = gamePanel.worldTimeCounter;
            ds.worldTimeSatuHariCounter = gamePanel.worldTimeSatuHariCounter;

            ds.indexCurrentSim = gamePanel.indexCurrentSim;

            for (int k = 0; k < gamePanel.listSim.size(); k++) {
                ds.infoSimStrings.add(gamePanel.listSim.get(k).nama);
                ds.infoSimStrings.add(gamePanel.listSim.get(k).rumah.posisiUpgrade);
                if (gamePanel.listSim.get(k).rumah.ruanganUpgrade != null) {
                    ds.infoSimStrings.add(gamePanel.listSim.get(k).rumah.ruanganUpgrade.name);
                } else {
                    ds.infoSimStrings.add("");
                }
                ds.infoSimStrings.add(gamePanel.listSim.get(k).currentLocation);
                ds.infoSimStrings.add(gamePanel.listSim.get(k).tempDialogUpgrade);
                ds.infoSimStrings.add(gamePanel.listSim.get(k).tempDialogBarang);
                // ds.infoSimStrings.add(gamePanel.listSim.get(k).currentLocation);
                ds.infoSimIntegers.add(gamePanel.listSim.get(k).uang);
                ds.infoSimIntegers.add(gamePanel.listSim.get(k).kesehatan);
                ds.infoSimIntegers.add(gamePanel.listSim.get(k).kekenyangan);
                ds.infoSimIntegers.add(gamePanel.listSim.get(k).mood);
                ds.infoSimIntegers.add(gamePanel.listSim.get(k).pekerjaan.indexPekerjaan);
                ds.infoSimIntegers.add(gamePanel.listSim.get(k).pekerjaan.durasiKerjaYangBelumDigaji);
                ds.infoSimIntegers.add(gamePanel.listSim.get(k).pekerjaan.totalDurasiKerjaPerPekerjaan);
                ds.infoSimIntegers.add(gamePanel.listSim.get(k).pekerjaan.worldTimeCounterForStartJobAfterChangeJob);
                ds.infoSimIntegers.add(gamePanel.listSim.get(k).rumah.remainingTimeUpgrade);
                ds.infoSimIntegers.add(gamePanel.listSim.get(k).rumah.indexLocationSaatUpgrade);
                ds.infoSimIntegers.add(gamePanel.listSim.get(k).rumah.indexSimSaatUpgrade);
                ds.infoSimIntegers.add(gamePanel.listSim.get(k).remainingTimeBuy);
                ds.infoSimIntegers.add(gamePanel.listSim.get(k).indexSimSaatBeli);
                ds.infoSimIntegers.add(gamePanel.listSim.get(k).itemBuyTempIndex);
                ds.infoSimIntegers.add(gamePanel.listSim.get(k).efekWaktuTidakBuangAirCounter);
                ds.infoSimIntegers.add(gamePanel.listSim.get(k).efekWaktuTidakTidurCounter);
                ds.infoSimIntegers.add(gamePanel.listSim.get(k).indexLocationRuangan);
                ds.infoSimIntegers.add(gamePanel.listSim.get(k).indexRumahYangDimasuki);
                ds.infoSimIntegers.add(gamePanel.listSim.get(k).indexBendaYangDisentuh);
                ds.infoSimIntegers.add(gamePanel.listSim.get(k).currentMap);
                ds.infoSimIntegers.add(gamePanel.listSim.get(k).worldX);
                ds.infoSimIntegers.add(gamePanel.listSim.get(k).worldY);

                ds.infoSimBooleans.add(gamePanel.listSim.get(k).pekerjaan.isCanChangePekerjaan);
                ds.infoSimBooleans.add(gamePanel.listSim.get(k).pekerjaan.isCanStartPekerjaan);
                ds.infoSimBooleans.add(gamePanel.listSim.get(k).rumah.isCanUpgrade);
                ds.infoSimBooleans.add(gamePanel.listSim.get(k).isCanBuy);
                ds.infoSimBooleans.add(gamePanel.listSim.get(k).isUdahMakanDalamSatuHari);
                ds.infoSimBooleans.add(gamePanel.listSim.get(k).isUpgradeDone);
                ds.infoSimBooleans.add(gamePanel.listSim.get(k).isBarangSampai);
                ds.infoSimIntegers.add(gamePanel.listSim.get(k).rumah.jumlahRuangan);
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

                    ds.bendaRuangan.add(gamePanel.listSim.get(k).rumah.ruanganRumah.get(i).index
                            + "");
                    ds.bendaRuangan.add(gamePanel.listSim.get(k).rumah.ruanganRumah.get(i).bendaRuangan.size()
                            + "");
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
            gamePanel.isOneSim = ds.isOneSim;
            gamePanel.worldTimeCounter = ds.worldTimeCounter;
            gamePanel.worldTimeSatuHariCounter = ds.worldTimeSatuHariCounter;

            int tempSize = gamePanel.listSim.size();
            for (int i = 1; i < tempSize; i++) {
                gamePanel.listSim.remove(gamePanel.listSim.size() - 1);
                gamePanel.listRumah[0].remove(gamePanel.listSim.size() - 1);
            }
            for (int k = 1; k < ds.jumlahSim; k++) {
                Sim sim = new Sim(gamePanel, gamePanel.keyHandler);
                gamePanel.listSim.add(sim);
                Rumah rumah = new Rumah(gamePanel);
                gamePanel.listRumah[0].add(rumah);
            }

            int sumStrings = 0;
            int sumIntegers = 0;
            int sumBooleans = 0;
            int sumInventory = 0;
            int sumRumah = 0;
            int num = 0;
            int indexPencariBenda = 0;
            for (int j = 0; j < ds.jumlahSim; j++) {
                // gamePanel.listSim.get(j).indexLocationRuangan = 0;
                // gamePanel.listSim.get(j).indexRumahYangDimasuki = j;
                // gamePanel.listSim.get(j).indexBendaYangDisentuh = 999;
                // gamePanel.listSim.get(j).currentMap = 1;
                // gamePanel.listSim.get(j).worldX = 2 * gamePanel.tileSize;
                // gamePanel.listSim.get(j).worldY = 2 * gamePanel.tileSize;
                gamePanel.listSim.get(j).direction = "down";

                gamePanel.listSim.get(j).nama = ds.infoSimStrings.get(sumStrings);
                gamePanel.listSim.get(j).rumah.posisiUpgrade = ds.infoSimStrings.get(sumStrings + 1);
                Ruangan ruangan = new Ruangan(gamePanel);
                gamePanel.listSim.get(j).rumah.ruanganUpgrade = ruangan;
                gamePanel.listSim.get(j).rumah.ruanganUpgrade.name = ds.infoSimStrings.get(sumStrings + 2);
                gamePanel.listSim.get(j).currentLocation = ds.infoSimStrings.get(sumStrings + 3);
                gamePanel.listSim.get(j).tempDialogUpgrade = ds.infoSimStrings.get(sumStrings + 4);
                gamePanel.listSim.get(j).tempDialogBarang = ds.infoSimStrings.get(sumStrings + 5);

                sumStrings += 6;

                gamePanel.listSim.get(j).pekerjaan.isCanChangePekerjaan = ds.infoSimBooleans.get(sumBooleans);
                gamePanel.listSim.get(j).pekerjaan.isCanStartPekerjaan = ds.infoSimBooleans.get(sumBooleans + 1);
                gamePanel.listSim.get(j).rumah.isCanUpgrade = ds.infoSimBooleans.get(sumBooleans + 2);
                gamePanel.listSim.get(j).isCanBuy = ds.infoSimBooleans.get(sumBooleans + 3);
                gamePanel.listSim.get(j).isUdahMakanDalamSatuHari = ds.infoSimBooleans.get(sumBooleans + 4);
                gamePanel.listSim.get(j).isUpgradeDone = ds.infoSimBooleans.get(sumBooleans + 5);
                gamePanel.listSim.get(j).isBarangSampai = ds.infoSimBooleans.get(sumBooleans + 6);

                sumBooleans += 7;

                gamePanel.listSim.get(j).uang = ds.infoSimIntegers.get(sumIntegers);
                gamePanel.listSim.get(j).kesehatan = ds.infoSimIntegers.get(sumIntegers + 1);
                gamePanel.listSim.get(j).kekenyangan = ds.infoSimIntegers.get(sumIntegers + 2);
                gamePanel.listSim.get(j).mood = ds.infoSimIntegers.get(sumIntegers + 3);
                gamePanel.listSim.get(j).pekerjaan.indexPekerjaan = ds.infoSimIntegers.get(sumIntegers + 4);
                gamePanel.listSim.get(j).pekerjaan.durasiKerjaYangBelumDigaji = ds.infoSimIntegers.get(sumIntegers + 5);
                gamePanel.listSim.get(j).pekerjaan.totalDurasiKerjaPerPekerjaan = ds.infoSimIntegers
                        .get(sumIntegers + 6);
                gamePanel.listSim.get(j).pekerjaan.worldTimeCounterForStartJobAfterChangeJob = ds.infoSimIntegers
                        .get(sumIntegers + 7);
                gamePanel.listSim.get(j).rumah.remainingTimeUpgrade = ds.infoSimIntegers
                        .get(sumIntegers + 8);
                gamePanel.listSim.get(j).rumah.indexLocationSaatUpgrade = ds.infoSimIntegers
                        .get(sumIntegers + 9);
                gamePanel.listSim.get(j).rumah.indexSimSaatUpgrade = ds.infoSimIntegers
                        .get(sumIntegers + 10);
                gamePanel.listSim.get(j).remainingTimeBuy = ds.infoSimIntegers
                        .get(sumIntegers + 11);
                gamePanel.listSim.get(j).indexSimSaatBeli = ds.infoSimIntegers
                        .get(sumIntegers + 12);
                gamePanel.listSim.get(j).itemBuyTempIndex = ds.infoSimIntegers
                        .get(sumIntegers + 13);
                gamePanel.listSim.get(j).efekWaktuTidakBuangAirCounter = ds.infoSimIntegers.get(sumIntegers + 14);
                gamePanel.listSim.get(j).efekWaktuTidakTidurCounter = ds.infoSimIntegers.get(sumIntegers + 15);
                gamePanel.listSim.get(j).indexLocationRuangan = ds.infoSimIntegers.get(sumIntegers + 16);
                gamePanel.listSim.get(j).indexRumahYangDimasuki = ds.infoSimIntegers.get(sumIntegers + 17);
                gamePanel.listSim.get(j).indexBendaYangDisentuh = ds.infoSimIntegers.get(sumIntegers + 18);
                gamePanel.listSim.get(j).currentMap = ds.infoSimIntegers.get(sumIntegers + 19);
                gamePanel.listSim.get(j).worldX = ds.infoSimIntegers.get(sumIntegers + 20);
                gamePanel.listSim.get(j).worldY = ds.infoSimIntegers.get(sumIntegers + 21);
                gamePanel.listSim.get(j).rumah.jumlahRuangan = ds.infoSimIntegers
                        .get(sumIntegers + 22);
                sumIntegers += 23;

                gamePanel.listSim.get(j).inventory.clear();

                int jumlahLoop = 0;
                for (int z = 0; z < Integer.parseInt(ds.itemNamess.get(sumInventory + 1)); z++) {
                    gamePanel.listSim.get(j).inventory.add(getObject(ds.itemNamess.get(sumInventory + 2 + z)));
                    gamePanel.listSim.get(j).inventory.get(z).quantity = Integer
                            .parseInt(ds.itemAmountss.get(sumInventory + 2 + z));
                    jumlahLoop += 1;
                }
                sumInventory += jumlahLoop + 2;

                gamePanel.listSim.get(j).rumah.worldX = ds.rumah.get(sumRumah + 2);
                gamePanel.listSim.get(j).rumah.worldY = ds.rumah.get(sumRumah + 3);
                gamePanel.listSim.get(j).rumah.colRumah = ds.rumah.get(sumRumah + 2) /
                        gamePanel.tileSize;
                gamePanel.listSim.get(j).rumah.rowRumah = ds.rumah.get(sumRumah + 3) /
                        gamePanel.tileSize;
                gamePanel.listRumah[0].set(j, gamePanel.listSim.get(j).rumah);

                Ruangan ruanganUtama = new Ruangan(gamePanel);
                gamePanel.listSim.get(j).rumah.ruanganRumah.clear();
                gamePanel.listSim.get(j).rumah.ruanganRumah.add(ruanganUtama);
                gamePanel.listSim.get(j).rumah.ruanganRumah.get(0).name = "Ruangan Utama";

                for (int i = 0; i < ds.rumah.get(sumRumah + 1) - 1; i++) {
                    Ruangan ruangan1 = new Ruangan(gamePanel);
                    ruangan1.name = ds.namaRuangan.get(i + 1);
                    gamePanel.listSim.get(j).rumah.ruanganRumah.add(ruangan1);
                }

                for (int i = 0; i < ds.rumah.get(sumRumah + 1); i++) {

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

                    gamePanel.listSim.get(j).rumah.ruanganRumah.get(i).index = Integer
                            .parseInt(ds.bendaRuangan.get(indexPencariBenda));

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
                    indexPencariBenda += nums * 5;
                    indexPencariBenda += 2;
                }
                sumRumah += 4;

            }
            gamePanel.indexCurrentSim = ds.indexCurrentSim;

        } catch (

        Exception e) {
            System.out.println("Load Exception!");
        }
    }
}
