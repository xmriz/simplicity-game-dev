package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import benda.*;
import main.*;

public class Sim extends Entity {

    // SIM INFO
    private String nama = "Sim";
    private Pekerjaan pekerjaan = new Pekerjaan();
    private int uang = 9999999;
    private int kesehatan = 80;
    private final int maxKesehatan = 100;
    private int kekenyangan = 80;
    private final int maxKekenyangan = 100;
    private int mood = 80;
    private final int maxMood = 100;
    private boolean lightUpdated = false;
    private Benda currentLight;
    private String currentLocation;
    private int indexLocationRuangan = 999;
    private int indexBendaYangDisentuh = 999;
    private int indexRumahYangDimasuki = 999;
    private int currentMap = 0;
    private static String keteranganMati = "";
    private boolean isMati = false;
    private boolean isUpgradeDone = false;
    private boolean isBarangSampai = false;
    private String tempDialogUpgrade = "";
    private String tempDialogBarang = "";
    private boolean isCanBuy = true;
    private int remainingTimeBuy = 0;
    private int indexSimSaatBeli = 0;
    private int itemBuyTempIndex = 0;
    private boolean isLockBuy = true;
    private int efekWaktuTidakTidurCounter = 0;
    private int efekWaktuTidakBuangAirCounter = 0;
    private boolean isUdahMakanDalamSatuHari = false;

    KeyHandler keyHandler;

    private final int screenX;
    private final int screenY;
    private Rumah rumah;

    // TEMPORARY VARIABLES
    private int tempInt = -1;
    private Benda tempBenda;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Pekerjaan getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(Pekerjaan pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public int getUang() {
        return uang;
    }

    public void setUang(int uang) {
        this.uang = uang;
    }

    public int getKesehatan() {
        return kesehatan;
    }

    public void setKesehatan(int kesehatan) {
        this.kesehatan = kesehatan;
    }

    public int getMaxKesehatan() {
        return maxKesehatan;
    }

    public int getKekenyangan() {
        return kekenyangan;
    }

    public void setKekenyangan(int kekenyangan) {
        this.kekenyangan = kekenyangan;
    }

    public int getMaxKekenyangan() {
        return maxKekenyangan;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public int getMaxMood() {
        return maxMood;
    }

    public boolean getLightUpdated() {
        return lightUpdated;
    }

    public void setLightUpdated(boolean lightUpdated) {
        this.lightUpdated = lightUpdated;
    }

    public Benda getCurrentLight() {
        return currentLight;
    }

    public void setCurrentLight(Benda currentLight) {
        this.currentLight = currentLight;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public int getIndexLocationRuangan() {
        return indexLocationRuangan;
    }

    public void setIndexLocationRuangan(int indexLocationRuangan) {
        this.indexLocationRuangan = indexLocationRuangan;
    }

    public int getIndexBendaYangDisentuh() {
        return indexBendaYangDisentuh;
    }

    public void setIndexBendaYangDisentuh(int indexBendaYangDisentuh) {
        this.indexBendaYangDisentuh = indexBendaYangDisentuh;
    }

    public int getIndexRumahYangDimasuki() {
        return indexRumahYangDimasuki;
    }

    public void setIndexRumahYangDimasuki(int indexRumahYangDimasuki) {
        this.indexRumahYangDimasuki = indexRumahYangDimasuki;
    }

    public int getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(int currentMap) {
        this.currentMap = currentMap;
    }

    public static String getKeteranganMati() {
        return keteranganMati;
    }

    public static void setKeteranganMati(String newKeteranganMati) {
        keteranganMati = newKeteranganMati;
    }

    public boolean getIsMati() {
        return isMati;
    }

    public void setIsMati(boolean isMati) {
        this.isMati = isMati;
    }

    public boolean getIsUpgradeDone() {
        return isUpgradeDone;
    }

    public void setIsUpgradeDone(boolean isUpgradeDone) {
        this.isUpgradeDone = isUpgradeDone;
    }

    public boolean getIsBarangSampai() {
        return isBarangSampai;
    }

    public void setIsBarangSampai(boolean isBarangSampai) {
        this.isBarangSampai = isBarangSampai;
    }

    public String getTempDialogUpgrade() {
        return tempDialogUpgrade;
    }

    public void setTempDialogUpgrade(String tempDialogUpgrade) {
        this.tempDialogUpgrade = tempDialogUpgrade;
    }

    public String getTempDialogBarang() {
        return tempDialogBarang;
    }

    public void setTempDialogBarang(String tempDialogBarang) {
        this.tempDialogBarang = tempDialogBarang;
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public Rumah getRumah() {
        return rumah;
    }

    public void setRumah(Rumah rumah) {
        this.rumah = rumah;
    }

    public int getTempInt() {
        return tempInt;
    }

    public void setTempInt(int tempInt) {
        this.tempInt = tempInt;
    }

    public Benda getTempBenda() {
        return tempBenda;
    }

    public void setTempBenda(Benda tempBenda) {
        this.tempBenda = tempBenda;
    }

    public boolean getIsCanBuy() {
        return isCanBuy;
    }

    public void setIsCanBuy(boolean isCanBuy) {
        this.isCanBuy = isCanBuy;
    }

    public int getRemainingTimeBuy() {
        return remainingTimeBuy;
    }

    public void setRemainingTimeBuy(int remainingTimeBuy) {
        this.remainingTimeBuy = remainingTimeBuy;
    }

    public int getIndexSimSaatBeli() {
        return indexSimSaatBeli;
    }

    public void setIndexSimSaatBeli(int indexSimSaatBeli) {
        this.indexSimSaatBeli = indexSimSaatBeli;
    }

    public int getItemBuyTempIndex() {
        return itemBuyTempIndex;
    }

    public void setItemBuyTempIndex(int itemBuyTempIndex) {
        this.itemBuyTempIndex = itemBuyTempIndex;
    }

    public boolean getIsLockBuy() {
        return isLockBuy;
    }

    public void setIsLockBuy(boolean isLockBuy) {
        this.isLockBuy = isLockBuy;
    }

    public int getEfekWaktuTidakTidurCounter() {
        return efekWaktuTidakTidurCounter;
    }

    public void setEfekWaktuTidakTidurCounter(int efekWaktuTidakTidurCounter) {
        this.efekWaktuTidakTidurCounter = efekWaktuTidakTidurCounter;
    }

    public int getEfekWaktuTidakBuangAirCounter() {
        return efekWaktuTidakBuangAirCounter;
    }

    public void setEfekWaktuTidakBuangAirCounter(int efekWaktuTidakBuangAirCounter) {
        this.efekWaktuTidakBuangAirCounter = efekWaktuTidakBuangAirCounter;
    }

    public boolean getIsUdahMakanDalamSatuHari() {
        return isUdahMakanDalamSatuHari;
    }

    public void setIsUdahMakanDalamSatuHari(boolean isUdahMakanDalamSatuHari) {
        this.isUdahMakanDalamSatuHari = isUdahMakanDalamSatuHari;
    }

    public Sim(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel);
        rumah = new Rumah(gamePanel);
        this.keyHandler = keyHandler;

        // set sim to his own rumah
        currentMap = 1;
        indexRumahYangDimasuki = 0;
        indexLocationRuangan = 0;

        // random pekerjaan index 0-4
        Random rand = new Random();
        pekerjaan.setIndexPekerjaan(rand.nextInt(5));

        screenX = gamePanel.getScreenWidth() / 2 - (gamePanel.getTileSize() / 2); // set the player's position on the
                                                                                  // screen
        // (always center)
        screenY = gamePanel.getScreenHeight() / 2 - (gamePanel.getTileSize() / 2);

        setSolidArea(new Rectangle(8, 16, 32, 32));
        setSolidAreaDefaultX(getSolidArea().x);
        setSolidAreaDefaultY(getSolidArea().y);
        setDefaultValues();
        getImage();
        setItems();
    }

    public void setDefaultValues() { // set the default values of the player
        setWorldX(2 * gamePanel.getTileSize()); // set the player's position in his own rumah
        setWorldY(2 * gamePanel.getTileSize());
        indexLocationRuangan = 0;
        setSpeed(4);
        setDirection("down");
    }

    public void setItems() {
        // OBJEK BASIC
        getInventory().add(new Furnitur_KasurSingle(gamePanel));
        getInventory().add(new Furnitur_Toilet(gamePanel));
        getInventory().add(new Furnitur_KomporGas(gamePanel));
        getInventory().add(new Furnitur_MejaKursi(gamePanel));
        getInventory().add(new Furnitur_Jam(gamePanel));
        getInventory().add(new Furnitur_Radio(gamePanel));
    }

    public void getImage() {
        setUp1(setupImage("sim/p2_up_1"));
        setUp2(setupImage("sim/p2_up_2"));
        setDown1(setupImage("sim/p2_down_1"));
        setDown2(setupImage("sim/p2_down_2"));
        setLeft1(setupImage("sim/p2_left_1"));
        setLeft2(setupImage("sim/p2_left_2"));
        setRight1(setupImage("sim/p2_right_1"));
        setRight2(setupImage("sim/p2_right_2"));
    }

    public void update() { // update the position of the player
        if (keyHandler.isUpPressed() || keyHandler.isDownPressed() || keyHandler.isLeftPressed()
                || keyHandler.isRightPressed()
                || keyHandler.isEnterPressed()) {
            if (keyHandler.isUpPressed()) {
                setDirection("up");
            } else if (keyHandler.isDownPressed()) {
                setDirection("down");
            } else if (keyHandler.isLeftPressed()) {
                setDirection("left");
            } else if (keyHandler.isRightPressed()) {
                setDirection("right");
            }

            // check for collision
            setCollisionOn(false);
            gamePanel.getCollisionChecker().checkTile(this);

            // check for benda collision
            indexBendaYangDisentuh = gamePanel.getCollisionChecker().checkBenda(this, true); // return the index of the
                                                                                             // benda
            // that theplayer is colliding
            // with
            usingBenda(indexBendaYangDisentuh);

            // check npc collision
            int npcIndex = gamePanel.getCollisionChecker().checkEntity(this, gamePanel.getNpc());
            interactNPC(npcIndex);

            // check event
            gamePanel.getEventHandler().checkEvent();

            // after checking all turn of enterPressed
            gamePanel.getKeyHandler().setEnterPressed(false);

            // if there is a collision, sim can't move
            if (!getCollisionOn() && !keyHandler.isEnterPressed()) {
                switch (getDirection()) {
                    case "up":
                        decWorldY(getSpeed());
                        break;
                    case "down":
                        incWorldY(getSpeed());
                        break;
                    case "left":
                        decWorldX(getSpeed());
                        break;
                    case "right":
                        incWorldX(getSpeed());
                        break;
                }
            }

            incSpriteCounter(1);
            if (getSpriteCounter() > 12) {
                setSpriteCounter(0);
                if (getSpriteNumber() == 1) {
                    setSpriteNumber(2);
                } else {
                    setSpriteNumber(1);
                }
            }
        }

        if (mood <= 0) {
            if (gamePanel.getListSim().size() == 1) {
                gamePanel.setIsOneSim(true);
                gamePanel.setWorldTimeCounter(0);
                gamePanel.setWorldTimeSatuHariCounter(0);
            }
            keteranganMati = "Depresi";
            isMati = true;
        } else if (kekenyangan <= 0) {
            if (gamePanel.getListSim().size() == 1) {
                gamePanel.setIsOneSim(true);
                gamePanel.setWorldTimeCounter(0);
                gamePanel.setWorldTimeSatuHariCounter(0);
            }
            keteranganMati = "Kelaparan";
            isMati = true;
        } else if (kesehatan <= 0) {
            if (gamePanel.getListSim().size() == 1) {
                gamePanel.setIsOneSim(true);
                gamePanel.setWorldTimeCounter(0);
                gamePanel.setWorldTimeSatuHariCounter(0);
            }
            keteranganMati = "Sakit";
            isMati = true;
        }
    }

    public void pickUpObject(int index) {
        if (index != 999) {
            if (gamePanel.getListSim().get(indexRumahYangDimasuki).rumah.getRuanganRumah().get(indexLocationRuangan)
                    .getBendaRuangan()
                    .get(index) instanceof Furnitur) {
                if (canObtainItem(gamePanel.getListSim().get(indexRumahYangDimasuki).rumah.getRuanganRumah()
                        .get(indexLocationRuangan).getBendaRuangan().get(index))) {
                    Furnitur furniturTemp = (Furnitur) gamePanel.getListSim().get(indexRumahYangDimasuki).rumah
                            .getRuanganRumah()
                            .get(indexLocationRuangan).getBendaRuangan().get(index);
                    gamePanel.getListSim().get(indexRumahYangDimasuki).rumah.getRuanganRumah()
                            .get(indexLocationRuangan).getBendaRuangan().remove(index);
                    gamePanel.getUi().setCharIndex(0);
                    gamePanel.getUi().setCombinedText("");
                    gamePanel.setGameState(gamePanel.getDialogState());
                    gamePanel.getUi().setCurrentDialog("Berhasil mengambil " + furniturTemp.getName());
                }
            }
        }
    }

    public void interactNPC(int i) {
        if (i != 999) {
            gamePanel.getUi().setCombinedText("");
            gamePanel.getUi().setCharIndex(0);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                gamePanel.setGameState(gamePanel.getDialogState());
                gamePanel.getNpc()[currentMap][i].speak();
            }
        }
    }

    public void usingBenda(int indexBendayangDisentuh) {
        if (gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).indexRumahYangDimasuki != 999) {
            if (indexBendayangDisentuh != 999) {
                if (gamePanel.getKeyHandler().isEnterPressed()) {
                    gamePanel.getKeyHandler().setCheckWorldTime(false);
                    gamePanel.getKeyHandler().setCheckCurrentLocation(false);
                    int indexRumahYangDimasuki = gamePanel.getListSim()
                            .get(gamePanel.getIndexCurrentSim()).indexRumahYangDimasuki;
                    int indexLocationRuangan = gamePanel.getListSim()
                            .get(gamePanel.getIndexCurrentSim()).indexLocationRuangan;
                    Furnitur furniturTemp = (Furnitur) gamePanel.getListSim().get(indexRumahYangDimasuki).rumah
                            .getRuanganRumah()
                            .get(indexLocationRuangan).getBendaRuangan().get(indexBendayangDisentuh);
                    furniturTemp.action();
                }
            }
        }
    }

    public void selectItem() {
        int itemIndex = UI.getItemIndexOnSlot(gamePanel.getUi().getSimSlotRow(), gamePanel.getUi().getSimSlotCol());

        if (itemIndex < getInventory().size()) {
            Benda selectedBenda = getInventory().get(itemIndex);

            if (selectedBenda instanceof BahanMakanan) {
                if (currentMap == 0) {
                    gamePanel.getUi().setCharIndex(0);
                    gamePanel.getUi().setCombinedText("");
                    gamePanel.setGameState(gamePanel.getDialogState());
                    gamePanel.getUi().setCurrentDialog(("Anda tidak bisa makan di luar rumah."));
                } else {
                    if (indexBendaYangDisentuh != 999) {
                        if (gamePanel.getListSim().get(indexRumahYangDimasuki).rumah.getRuanganRumah()
                                .get(indexLocationRuangan).getBendaRuangan()
                                .get(indexBendaYangDisentuh) instanceof Furnitur_MejaKursi) {
                            BahanMakanan bahanMakanan = (BahanMakanan) selectedBenda;
                            bahanMakanan.eat(this);
                            gamePanel.incWorldTimeCounter(30);
                            gamePanel.incWorldTimeSatuHariCounter(30);

                            for (int i = 0; i < gamePanel.getListSim().size(); i++) {
                                gamePanel.getListSim().get(i).pekerjaan
                                        .setWorldTimeCounterForStartJobAfterChangeJob(gamePanel.getListSim()
                                                .get(i).pekerjaan.getWorldTimeCounterForStartJobAfterChangeJob() + 30);
                                gamePanel.getListSim().get(i).efekWaktuTidakTidurCounter += 30;
                                if (gamePanel.getListSim().get(i).isUdahMakanDalamSatuHari) {
                                    gamePanel.getListSim().get(i).efekWaktuTidakBuangAirCounter += 30;
                                }
                            }

                            if (bahanMakanan.getQuantity() > 1) {
                                bahanMakanan.decQuantity(1);
                            } else {
                                getInventory().remove(itemIndex);
                            }
                            gamePanel.getUi().setCurrentAksiCadangan(
                                    "Makan " + bahanMakanan.getName() + "\nKekenyangan bertambah "
                                            + bahanMakanan.getKekenyangan());
                            gamePanel.getUi().setCurrentAksi("Makan");
                            gamePanel.setGameState(gamePanel.getTimerState());
                            gamePanel.getUi().setCurrentAksiDone(false);
                            isUdahMakanDalamSatuHari = true;
                            gamePanel.getUi().setTempDurasi(30);
                            gamePanel.getKeyHandler().setThreadTemp(gamePanel.getUi().startTimerThread(30));

                        } else {
                            gamePanel.getUi().setCharIndex(0);
                            gamePanel.getUi().setCombinedText("");
                            gamePanel.setGameState(gamePanel.getDialogState());
                            gamePanel.getUi().setCurrentDialog("Anda harus makan di meja makan!");
                        }
                        indexBendaYangDisentuh = 999;
                    } else {
                        gamePanel.getUi().setCharIndex(0);
                        gamePanel.getUi().setCombinedText("");
                        gamePanel.setGameState(gamePanel.getDialogState());
                        gamePanel.getUi().setCurrentDialog("Anda harus makan di meja makan!");
                    }
                }
            } else if (selectedBenda instanceof Makanan) {
                if (currentMap == 0) {
                    gamePanel.getUi().setCharIndex(0);
                    gamePanel.getUi().setCombinedText("");
                    gamePanel.setGameState(gamePanel.getDialogState());
                    gamePanel.getUi().setCurrentDialog("Anda tidak bisa makan di luar rumah.");
                } else {
                    if (indexBendaYangDisentuh != 999) {
                        if (gamePanel.getListSim().get(indexRumahYangDimasuki).rumah.getRuanganRumah()
                                .get(indexLocationRuangan).getBendaRuangan()
                                .get(indexBendaYangDisentuh) instanceof Furnitur_MejaKursi) {
                            Makanan makanan = (Makanan) selectedBenda;
                            makanan.eat(this);
                            gamePanel.incWorldTimeCounter(30);
                            gamePanel.incWorldTimeSatuHariCounter(30);
                            for (int i = 0; i < gamePanel.getListSim().size(); i++) {
                                gamePanel.getListSim().get(i).pekerjaan
                                        .setWorldTimeCounterForStartJobAfterChangeJob(gamePanel.getListSim()
                                                .get(i).pekerjaan.getWorldTimeCounterForStartJobAfterChangeJob() + 30);
                                gamePanel.getListSim().get(i).efekWaktuTidakTidurCounter += 30;
                                if (gamePanel.getListSim().get(i).isUdahMakanDalamSatuHari) {
                                    gamePanel.getListSim().get(i).efekWaktuTidakBuangAirCounter += 30;
                                }
                            }
                            if (makanan.getQuantity() > 1) {
                                makanan.decQuantity(1);
                            } else {
                                getInventory().remove(itemIndex);
                            }
                            gamePanel.getUi()
                                    .setCurrentAksiCadangan("Makan " + makanan.getName() + "\nKekenyangan bertambah "
                                            + makanan.getKekenyangan());
                            gamePanel.getUi().setCurrentAksi("Makan");
                            gamePanel.setGameState(gamePanel.getTimerState());
                            gamePanel.getUi().setCurrentAksiDone(false);
                            isUdahMakanDalamSatuHari = true;
                            gamePanel.getUi().setTempDurasi(30);
                            gamePanel.getKeyHandler().setThreadTemp(gamePanel.getUi().startTimerThread(30));
                        } else {
                            gamePanel.getUi().setCharIndex(0);
                            gamePanel.getUi().setCombinedText("");
                            gamePanel.setGameState(gamePanel.getDialogState());
                            gamePanel.getUi().setCurrentDialog("Anda harus makan di meja makan!");
                        }
                    } else {
                        gamePanel.getUi().setCharIndex(0);
                        gamePanel.getUi().setCombinedText("");
                        gamePanel.setGameState(gamePanel.getDialogState());
                        gamePanel.getUi().setCurrentDialog("Anda harus makan di meja makan!");
                    }
                }

            } else if (selectedBenda instanceof Furnitur) {
                if (currentMap == 0) {
                    gamePanel.getUi().setCharIndex(0);
                    gamePanel.getUi().setCombinedText("");
                    gamePanel.setGameState(gamePanel.getDialogState());
                    gamePanel.getUi().setCurrentDialog("Tidak dapat memasang furnitur di luar\nrumah!");
                } else {
                    tempInt = itemIndex;
                    Furnitur furnitur = (Furnitur) selectedBenda;
                    if (furnitur.getQuantity() > 1) {
                        furnitur.decQuantity(1);
                    } else {
                        getInventory().remove(itemIndex);
                    }
                    tempBenda = furnitur;
                    gamePanel.setGameState(gamePanel.getInputKoordinatBendaState());

                }

            } else if (selectedBenda instanceof Lampu) {
                if (selectedBenda == currentLight) { // lampu menyala
                    currentLight = null; // lampu mati
                } else {
                    currentLight = selectedBenda; // nyalakan lampu yang dipilih
                }
                lightUpdated = true;
            }
        }
    }

    public int searchItemInInventory(String itemName) {
        int itemIndex = 999;

        for (Benda benda : getInventory()) {
            if (benda.getName().equals(itemName)) {
                itemIndex = getInventory().indexOf(benda);
                break;
            }
        }
        return itemIndex;
    }

    public boolean canObtainItem(Benda item) {
        boolean canObtain = false;

        // lampu pada inventory hanya boleh ada 1
        if (item instanceof Lampu) {
            int index = searchItemInInventory(item.getName());
            if (index != 999) { // item terdapat di inventory

            } else {
                if (getInventory().size() < getMaxInventorySize()) {
                    getInventory().add(item);
                    getInventory().get(getInventory().size() - 1).setQuantity(1);
                    canObtain = true;
                }
            }
        }

        // check if item is stackable
        else if (item.getStackable()) {
            int index = searchItemInInventory(item.getName());
            if (index != 999) {
                getInventory().get(index).incQuantity(1);
                canObtain = true;
            } else {
                if (getInventory().size() < getMaxInventorySize()) {
                    getInventory().add(item);
                    getInventory().get(getInventory().size() - 1).setQuantity(1);
                    canObtain = true;
                }
            }
        } else { // not stackable
            if (getInventory().size() < getMaxInventorySize()) {
                getInventory().add(item);
                getInventory().get(getInventory().size() - 1).setQuantity(1);
                canObtain = true;
            }
        }

        return canObtain;
    }

    public void draw(Graphics2D g2d) {
        BufferedImage image = null;

        switch (getDirection()) {
            case "up":
                if (getSpriteNumber() == 1) {
                    image = getUp1();
                } else {
                    image = getUp2();
                }
                break;
            case "down":
                if (getSpriteNumber() == 1) {
                    image = getDown1();
                } else {
                    image = getDown2();
                }
                break;
            case "left":
                if (getSpriteNumber() == 1) {
                    image = getLeft1();
                } else {
                    image = getLeft2();
                }
                break;
            case "right":
                if (getSpriteNumber() == 1) {
                    image = getRight1();
                } else {
                    image = getRight2();
                }
                break;
        }
        g2d.drawImage(image, screenX, screenY, null);
    }

    public void setIsCanBuyToTrue() {
        if (isLockBuy == false) {
            if (remainingTimeBuy < 0) {
                remainingTimeBuy = 0;
            }
            if (remainingTimeBuy == 0) {
                isCanBuy = true; // atur isCanUpgrade menjadi true setelah 18 menit
                if (gamePanel.getNpc()[0][4].getInventory().get(itemBuyTempIndex) instanceof BahanMakanan) {
                    BahanMakanan makanan = (BahanMakanan) gamePanel.getNpc()[0][4].getInventory().get(itemBuyTempIndex);
                    if (gamePanel.getListSim().get(indexSimSaatBeli).canObtainItem(makanan)) {
                        isBarangSampai = true;
                        tempDialogBarang = "Pembelian barang oleh "
                                + gamePanel.getListSim().get(indexSimSaatBeli).nama + "\nberhasil";
                    } else {
                        gamePanel.getListSim().get(indexSimSaatBeli).uang += makanan.getHarga();
                        gamePanel.getUi().setSubState(0);
                        gamePanel.getUi().setCharIndex(0);
                        gamePanel.getUi().setCombinedText("");
                        gamePanel.setGameState(gamePanel.getDialogState());
                        gamePanel.getUi().setCurrentDialog("Inventory penuh");
                    }
                } else if (gamePanel.getNpc()[0][4].getInventory().get(itemBuyTempIndex) instanceof Furnitur) {
                    Furnitur furnitur = (Furnitur) gamePanel.getNpc()[0][4].getInventory().get(itemBuyTempIndex);
                    if (gamePanel.getListSim().get(indexSimSaatBeli).canObtainItem(furnitur)) {
                        isBarangSampai = true;
                        tempDialogBarang = "Pembelian barang oleh "
                                + gamePanel.getListSim().get(indexSimSaatBeli).nama + "\nberhasil";
                    } else {
                        gamePanel.getListSim().get(indexSimSaatBeli).uang += furnitur.getHarga();
                        gamePanel.getUi().setSubState(0);
                        gamePanel.getUi().setCharIndex(0);
                        gamePanel.getUi().setCombinedText("");
                        gamePanel.setGameState(gamePanel.getDialogState());
                        gamePanel.getUi().setCurrentDialog("Inventory penuh");
                    }
                } else if (gamePanel.getNpc()[0][4].getInventory().get(itemBuyTempIndex) instanceof Lampu) {
                    Lampu lampu = (Lampu) gamePanel.getNpc()[0][4].getInventory().get(itemBuyTempIndex);
                    if (gamePanel.getListSim().get(indexSimSaatBeli).canObtainItem(lampu)) {
                        isBarangSampai = true;
                        tempDialogBarang = "Pembelian barang oleh "
                                + gamePanel.getListSim().get(indexSimSaatBeli).nama + "\nberhasil";
                    } else {
                        gamePanel.getListSim().get(indexSimSaatBeli).uang += lampu.getHarga();
                        gamePanel.getUi().setSubState(0);
                        gamePanel.getUi().setCharIndex(0);
                        gamePanel.getUi().setCombinedText("");
                        gamePanel.setGameState(gamePanel.getDialogState());
                        gamePanel.getUi().setCurrentDialog("Inventory penuh");
                    }
                }
            }
        }
    }
}
