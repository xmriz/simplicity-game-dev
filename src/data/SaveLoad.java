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

            // Save data
            ds.jumlahSim = gamePanel.getListSim().size();
            ds.isOneSim = gamePanel.getIsOneSim();
            ds.worldTimeCounter = gamePanel.getWorldTimeCounter();
            ds.worldTimeSatuHariCounter = gamePanel.getWorldTimeSatuHariCounter();
            ds.isCanAddSim = gamePanel.getIsCanAddSim();

            ds.indexCurrentSim = gamePanel.getIndexCurrentSim();

            for (int k = 0; k < gamePanel.getListSim().size(); k++) {
                ds.infoSimStrings.add(gamePanel.getListSim().get(k).getNama());
                ds.infoSimStrings.add(gamePanel.getListSim().get(k).getRumah().getPosisiUpgrade());
                if (gamePanel.getListSim().get(k).getRumah().getRuanganUpgrade() != null) {
                    ds.infoSimStrings.add(gamePanel.getListSim().get(k).getRumah().getRuanganUpgrade().getName());
                } else {
                    ds.infoSimStrings.add("");
                }
                ds.infoSimStrings.add(gamePanel.getListSim().get(k).getCurrentLocation());
                ds.infoSimStrings.add(gamePanel.getListSim().get(k).getTempDialogUpgrade());
                ds.infoSimStrings.add(gamePanel.getListSim().get(k).getTempDialogBarang());
                ds.infoSimIntegers.add(gamePanel.getListSim().get(k).getUang());
                ds.infoSimIntegers.add(gamePanel.getListSim().get(k).getKesehatan());
                ds.infoSimIntegers.add(gamePanel.getListSim().get(k).getKekenyangan());
                ds.infoSimIntegers.add(gamePanel.getListSim().get(k).getMood());
                ds.infoSimIntegers.add(gamePanel.getListSim().get(k).getPekerjaan().getIndexPekerjaan());
                ds.infoSimIntegers.add(gamePanel.getListSim().get(k).getPekerjaan().getDurasiKerjaYangBelumDigaji());
                ds.infoSimIntegers.add(gamePanel.getListSim().get(k).getPekerjaan().getTotalDurasiKerjaPerPekerjaan());
                ds.infoSimIntegers.add(
                        gamePanel.getListSim().get(k).getPekerjaan().getWorldTimeCounterForStartJobAfterChangeJob());
                ds.infoSimIntegers.add(gamePanel.getListSim().get(k).getRumah().getRemainingTimeUpgrade());
                ds.infoSimIntegers.add(gamePanel.getListSim().get(k).getRumah().getIndexLocationSaatUpgrade());
                ds.infoSimIntegers.add(gamePanel.getListSim().get(k).getRumah().getIndexSimSaatUpgrade());
                ds.infoSimIntegers.add(gamePanel.getListSim().get(k).getRemainingTimeBuy());
                ds.infoSimIntegers.add(gamePanel.getListSim().get(k).getIndexSimSaatBeli());
                ds.infoSimIntegers.add(gamePanel.getListSim().get(k).getItemBuyTempIndex());
                ds.infoSimIntegers.add(gamePanel.getListSim().get(k).getEfekWaktuTidakBuangAirCounter());
                ds.infoSimIntegers.add(gamePanel.getListSim().get(k).getEfekWaktuTidakTidurCounter());
                ds.infoSimIntegers.add(gamePanel.getListSim().get(k).getIndexLocationRuangan());
                ds.infoSimIntegers.add(gamePanel.getListSim().get(k).getIndexRumahYangDimasuki());
                ds.infoSimIntegers.add(gamePanel.getListSim().get(k).getIndexBendaYangDisentuh());
                ds.infoSimIntegers.add(gamePanel.getListSim().get(k).getCurrentMap());
                ds.infoSimIntegers.add(gamePanel.getListSim().get(k).getWorldX());
                ds.infoSimIntegers.add(gamePanel.getListSim().get(k).getWorldY());
                ds.infoSimBooleans.add(gamePanel.getListSim().get(k).getPekerjaan().getIsCanChangePekerjaan());
                ds.infoSimBooleans.add(gamePanel.getListSim().get(k).getPekerjaan().getIsCanStartPekerjaan());
                ds.infoSimBooleans.add(gamePanel.getListSim().get(k).getRumah().getIsCanUpgrade());
                ds.infoSimBooleans.add(gamePanel.getListSim().get(k).getIsCanBuy());
                ds.infoSimBooleans.add(gamePanel.getListSim().get(k).getIsUdahMakanDalamSatuHari());
                ds.infoSimBooleans.add(gamePanel.getListSim().get(k).getIsUpgradeDone());
                ds.infoSimBooleans.add(gamePanel.getListSim().get(k).getIsBarangSampai());
                ds.infoSimIntegers.add(gamePanel.getListSim().get(k).getRumah().getJumlahRuangan());

                ds.itemNamess.add(k + "");
                ds.itemNamess.add(gamePanel.getListSim().get(k).getInventory().size() + "");
                ds.itemAmountss.add(k + "");
                ds.itemAmountss.add(gamePanel.getListSim().get(k).getInventory().size() + "");

                for (int z = 0; z < gamePanel.getListSim().get(k).getInventory().size(); z++) {
                    ds.itemNamess.add(gamePanel.getListSim().get(k).getInventory().get(z).getName());
                    ds.itemAmountss.add(gamePanel.getListSim().get(k).getInventory().get(z).getQuantity() + "");
                }

                ds.rumah.add(k);
                ds.rumah.add(gamePanel.getListSim().get(k).getRumah().getJumlahRuangan());
                ds.rumah.add(gamePanel.getListSim().get(k).getRumah().getWorldX());
                ds.rumah.add(gamePanel.getListSim().get(k).getRumah().getWorldY());

                for (int i = 0; i < gamePanel.getListSim().get(k).getRumah().getJumlahRuangan(); i++) {
                    ds.ruangan.add(gamePanel.getListSim().get(k).getRumah().getRuanganRumah().get(i).getIndex());
                    ds.namaRuangan.add(gamePanel.getListSim().get(k).getRumah().getRuanganRumah().get(i).getName());
                    if (gamePanel.getListSim().get(k).getRumah().getRuanganRumah().get(i).getUp() != null) {
                        ds.ruangan.add(
                                gamePanel.getListSim().get(k).getRumah().getRuanganRumah().get(i).getUp().getIndex());
                    } else {
                        ds.ruangan.add(null);
                    }
                    if (gamePanel.getListSim().get(k).getRumah().getRuanganRumah().get(i).getDown() != null) {
                        ds.ruangan.add(
                                gamePanel.getListSim().get(k).getRumah().getRuanganRumah().get(i).getDown().getIndex());
                    } else {
                        ds.ruangan.add(null);
                    }
                    if (gamePanel.getListSim().get(k).getRumah().getRuanganRumah().get(i).getRight() != null) {
                        ds.ruangan.add(gamePanel.getListSim().get(k).getRumah().getRuanganRumah().get(i).getRight()
                                .getIndex());
                    } else {
                        ds.ruangan.add(null);
                    }
                    if (gamePanel.getListSim().get(k).getRumah().getRuanganRumah().get(i).getLeft() != null) {
                        ds.ruangan.add(
                                gamePanel.getListSim().get(k).getRumah().getRuanganRumah().get(i).getLeft().getIndex());
                    } else {
                        ds.ruangan.add(null);
                    }

                    ds.bendaRuangan.add(gamePanel.getListSim().get(k).getRumah().getRuanganRumah().get(i).getIndex()
                            + "");
                    ds.bendaRuangan.add(
                            gamePanel.getListSim().get(k).getRumah().getRuanganRumah().get(i).getBendaRuangan().size()
                                    + "");
                    for (int j = 0; j < gamePanel.getListSim().get(k).getRumah().getRuanganRumah().get(i)
                            .getBendaRuangan().size(); j++) {
                        ds.bendaRuangan
                                .add(gamePanel.getListSim().get(k).getRumah().getRuanganRumah().get(i).getBendaRuangan()
                                        .get(j).getName());
                        ds.bendaRuangan
                                .add(gamePanel.getListSim().get(k).getRumah().getRuanganRumah().get(i).getBendaRuangan()
                                        .get(j).getWorldX()
                                        + "");
                        ds.bendaRuangan
                                .add(gamePanel.getListSim().get(k).getRumah().getRuanganRumah().get(i).getBendaRuangan()
                                        .get(j).getWorldY()
                                        + "");
                        ds.bendaRuangan
                                .add(gamePanel.getListSim().get(k).getRumah().getRuanganRumah().get(i).getBendaRuangan()
                                        .get(j).getSolidArea().x
                                        + "");
                        ds.bendaRuangan
                                .add(gamePanel.getListSim().get(k).getRumah().getRuanganRumah().get(i).getBendaRuangan()
                                        .get(j).getSolidArea().y
                                        + "");
                    }
                }
            }
            oos.writeObject(ds);
        } catch (Exception e) {
            System.out.println("Save Exception!");
        }
    }

    public void load(String string) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("src/res/" + string + ".dat")));
            DataStorage ds = (DataStorage) ois.readObject();

            // Load Data
            gamePanel.setIndexCurrentSim(0);
            gamePanel.setIsOneSim(ds.isOneSim);
            gamePanel.setWorldTimeCounter(ds.worldTimeCounter);
            gamePanel.setWorldTimeSatuHariCounter(ds.worldTimeSatuHariCounter);
            gamePanel.setIsCanAddSim(ds.isCanAddSim);

            int tempSize = gamePanel.getListSim().size();
            for (int i = 1; i < tempSize; i++) {
                gamePanel.getListSim().remove(gamePanel.getListSim().size() - 1);
                gamePanel.getListRumah()[0].remove(gamePanel.getListSim().size() - 1);
            }
            for (int k = 1; k < ds.jumlahSim; k++) {
                Sim sim = new Sim(gamePanel, gamePanel.getKeyHandler());
                gamePanel.getListSim().add(sim);
                Rumah rumah = new Rumah(gamePanel);
                gamePanel.getListRumah()[0].add(rumah);
            }

            int sumStrings = 0;
            int sumIntegers = 0;
            int sumBooleans = 0;
            int sumInventory = 0;
            int sumRumah = 0;
            int num = 0;
            int indexPencariBenda = 0;
            for (int j = 0; j < ds.jumlahSim; j++) {
                gamePanel.getListSim().get(j).setDirection("down");

                gamePanel.getListSim().get(j).setNama(ds.infoSimStrings.get(sumStrings));
                gamePanel.getListSim().get(j).getRumah().setPosisiUpgrade(ds.infoSimStrings.get(sumStrings + 1));
                Ruangan ruangan = new Ruangan(gamePanel);
                gamePanel.getListSim().get(j).getRumah().setRuanganUpgrade(ruangan);
                gamePanel.getListSim().get(j).getRumah().getRuanganUpgrade()
                        .setName(ds.infoSimStrings.get(sumStrings + 2));
                gamePanel.getListSim().get(j).setCurrentLocation(ds.infoSimStrings.get(sumStrings + 3));
                gamePanel.getListSim().get(j).setTempDialogUpgrade(ds.infoSimStrings.get(sumStrings + 4));
                gamePanel.getListSim().get(j).setTempDialogBarang(ds.infoSimStrings.get(sumStrings + 5));
                sumStrings += 6;

                gamePanel.getListSim().get(j).getPekerjaan()
                        .setIsCanChangePekerjaan(ds.infoSimBooleans.get(sumBooleans));
                gamePanel.getListSim().get(j).getPekerjaan()
                        .setIsCanStartPekerjaan(ds.infoSimBooleans.get(sumBooleans + 1));
                gamePanel.getListSim().get(j).getRumah().setIsCanUpgrade(ds.infoSimBooleans.get(sumBooleans + 2));
                gamePanel.getListSim().get(j).setIsCanBuy(ds.infoSimBooleans.get(sumBooleans + 3));
                gamePanel.getListSim().get(j).setIsUdahMakanDalamSatuHari(ds.infoSimBooleans.get(sumBooleans + 4));
                gamePanel.getListSim().get(j).setIsUpgradeDone(ds.infoSimBooleans.get(sumBooleans + 5));
                gamePanel.getListSim().get(j).setIsBarangSampai(ds.infoSimBooleans.get(sumBooleans + 6));
                sumBooleans += 7;

                gamePanel.getListSim().get(j).setUang(ds.infoSimIntegers.get(sumIntegers));
                gamePanel.getListSim().get(j).setKesehatan(ds.infoSimIntegers.get(sumIntegers + 1));
                gamePanel.getListSim().get(j).setKekenyangan(ds.infoSimIntegers.get(sumIntegers + 2));
                gamePanel.getListSim().get(j).setMood(ds.infoSimIntegers.get(sumIntegers + 3));
                gamePanel.getListSim().get(j).getPekerjaan().setIndexPekerjaan(ds.infoSimIntegers.get(sumIntegers + 4));
                gamePanel.getListSim().get(j).getPekerjaan()
                        .setDurasiKerjaYangBelumDigaji(ds.infoSimIntegers.get(sumIntegers + 5));
                gamePanel.getListSim().get(j).getPekerjaan().setTotalDurasiKerjaPerPekerjaan(ds.infoSimIntegers
                        .get(sumIntegers + 6));
                gamePanel.getListSim().get(j).getPekerjaan()
                        .setWorldTimeCounterForStartJobAfterChangeJob(ds.infoSimIntegers
                                .get(sumIntegers + 7));
                gamePanel.getListSim().get(j).getRumah().setRemainingTimeUpgrade(ds.infoSimIntegers
                        .get(sumIntegers + 8));
                gamePanel.getListSim().get(j).getRumah().setIndexLocationSaatUpgrade(ds.infoSimIntegers
                        .get(sumIntegers + 9));
                gamePanel.getListSim().get(j).getRumah().setIndexSimSaatUpgrade(ds.infoSimIntegers
                        .get(sumIntegers + 10));
                gamePanel.getListSim().get(j).setRemainingTimeBuy(ds.infoSimIntegers.get(sumIntegers + 11));
                gamePanel.getListSim().get(j).setIndexSimSaatBeli(ds.infoSimIntegers.get(sumIntegers + 12));
                gamePanel.getListSim().get(j).setItemBuyTempIndex(ds.infoSimIntegers.get(sumIntegers + 13));
                gamePanel.getListSim().get(j)
                        .setEfekWaktuTidakBuangAirCounter(ds.infoSimIntegers.get(sumIntegers + 14));
                gamePanel.getListSim().get(j).setEfekWaktuTidakTidurCounter(ds.infoSimIntegers.get(sumIntegers + 15));
                gamePanel.getListSim().get(j).setIndexLocationRuangan(ds.infoSimIntegers.get(sumIntegers + 16));
                gamePanel.getListSim().get(j).setIndexRumahYangDimasuki(ds.infoSimIntegers.get(sumIntegers + 17));
                gamePanel.getListSim().get(j).setIndexBendaYangDisentuh(ds.infoSimIntegers.get(sumIntegers + 18));
                gamePanel.getListSim().get(j).setCurrentMap(ds.infoSimIntegers.get(sumIntegers + 19));
                gamePanel.getListSim().get(j).setWorldX(ds.infoSimIntegers.get(sumIntegers + 20));
                gamePanel.getListSim().get(j).setWorldY(ds.infoSimIntegers.get(sumIntegers + 21));
                gamePanel.getListSim().get(j).getRumah().setJumlahRuangan(ds.infoSimIntegers
                        .get(sumIntegers + 22));
                sumIntegers += 23;

                gamePanel.getListSim().get(j).getInventory().clear();

                int jumlahLoop = 0;
                for (int z = 0; z < Integer.parseInt(ds.itemNamess.get(sumInventory + 1)); z++) {
                    gamePanel.getListSim().get(j).getInventory()
                            .add(getObject(ds.itemNamess.get(sumInventory + 2 + z)));
                    gamePanel.getListSim().get(j).getInventory().get(z).setQuantity(Integer
                            .parseInt(ds.itemAmountss.get(sumInventory + 2 + z)));
                    jumlahLoop += 1;
                }
                sumInventory += jumlahLoop + 2;

                gamePanel.getListSim().get(j).getRumah().setWorldX(ds.rumah.get(sumRumah + 2));
                gamePanel.getListSim().get(j).getRumah().setWorldY(ds.rumah.get(sumRumah + 3));
                gamePanel.getListSim().get(j).getRumah().setColRumah(ds.rumah.get(sumRumah + 2) /
                        gamePanel.getTileSize());
                gamePanel.getListSim().get(j).getRumah().setRowRumah(ds.rumah.get(sumRumah + 3) /
                        gamePanel.getTileSize());
                gamePanel.getListRumah()[0].set(j, gamePanel.getListSim().get(j).getRumah());

                Ruangan ruanganUtama = new Ruangan(gamePanel);
                gamePanel.getListSim().get(j).getRumah().getRuanganRumah().clear();
                gamePanel.getListSim().get(j).getRumah().getRuanganRumah().add(ruanganUtama);
                gamePanel.getListSim().get(j).getRumah().getRuanganRumah().get(0).setName("Ruangan Utama");

                for (int i = 0; i < ds.rumah.get(sumRumah + 1) - 1; i++) {
                    Ruangan ruangan1 = new Ruangan(gamePanel);
                    ruangan1.setName(ds.namaRuangan.get(i + 1));
                    gamePanel.getListSim().get(j).getRumah().getRuanganRumah().add(ruangan1);
                }

                for (int i = 0; i < ds.rumah.get(sumRumah + 1); i++) {
                    if (ds.ruangan.get(num + 1) != null) {
                        gamePanel.getListSim().get(j).getRumah().getRuanganRumah()
                                .get(i).setUp(gamePanel.getListSim().get(j).getRumah().getRuanganRumah()
                                        .get(ds.ruangan.get(num + 1)));
                    }
                    if (ds.ruangan.get(num + 2) != null) {
                        if (i != 0) {
                            gamePanel.getListSim().get(j).getRumah().getRuanganRumah()
                                    .get(i).setDown(gamePanel.getListSim().get(j).getRumah().getRuanganRumah()
                                            .get(ds.ruangan.get(num + 2)));
                        }
                    }
                    if (ds.ruangan.get(num + 3) != null) {
                        gamePanel.getListSim().get(j).getRumah().getRuanganRumah()
                                .get(i).setRight(gamePanel.getListSim().get(j).getRumah().getRuanganRumah()
                                        .get(ds.ruangan.get(num + 3)));
                    }
                    if (ds.ruangan.get(num + 4) != null) {
                        gamePanel.getListSim().get(j).getRumah().getRuanganRumah()
                                .get(i).setLeft(gamePanel.getListSim().get(j).getRumah().getRuanganRumah()
                                        .get(ds.ruangan.get(num + 4)));
                    }
                    num += 5;

                    gamePanel.getListSim().get(j).getRumah().getRuanganRumah().get(i).setIndex(Integer
                            .parseInt(ds.bendaRuangan.get(indexPencariBenda)));

                    int nums = 0;
                    for (int x = 0; x < Integer.parseInt(ds.bendaRuangan.get(indexPencariBenda +
                            1)); x++) {
                        gamePanel.getListSim().get(j).getRumah().getRuanganRumah().get(i).getBendaRuangan()
                                .add(getObject(ds.bendaRuangan.get(indexPencariBenda + 2 + x * 5)));
                        gamePanel.getListSim().get(j).getRumah().getRuanganRumah().get(i).getBendaRuangan().get(x)
                                .setWorldX(Integer
                                        .parseInt(ds.bendaRuangan.get(indexPencariBenda + 3 + x * 5)));
                        gamePanel.getListSim().get(j).getRumah().getRuanganRumah().get(i).getBendaRuangan().get(x)
                                .setWorldY(Integer
                                        .parseInt(ds.bendaRuangan.get(indexPencariBenda + 4 + x * 5)));
                        gamePanel.getListSim().get(j).getRumah().getRuanganRumah().get(i).getBendaRuangan().get(x)
                                .getSolidArea().x = Integer
                                        .parseInt(ds.bendaRuangan.get(indexPencariBenda + 5 + x * 5));
                        gamePanel.getListSim().get(j).getRumah().getRuanganRumah().get(i).getBendaRuangan().get(x)
                                .getSolidArea().y = Integer
                                        .parseInt(ds.bendaRuangan.get(indexPencariBenda + 6 + x * 5));
                        nums += 1;
                    }
                    indexPencariBenda += nums * 5;
                    indexPencariBenda += 2;
                }
                sumRumah += 4;
            }
            gamePanel.setIndexCurrentSim(ds.indexCurrentSim);
        } catch (Exception e) {
            System.out.println("Load Exception!");
        }
    }
}
