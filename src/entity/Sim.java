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

    KeyHandler keyHandler;

    private final int screenX;
    private final int screenY;

    private Rumah rumah;

    // TEMPORARY VARIABLES
    private int tempInt = -1;
    private Benda tempBenda;

    private boolean isCanBuy = true;
    private int remainingTimeBuy = 0;
    private int indexSimSaatBeli = 0;
    private int itemBuyTempIndex = 0;
    private boolean isLockBuy = true;

    // gw nambah lagi darr, buat waktu efek tidak tidur, dan efek tidak buang air
    private int efekWaktuTidakTidurCounter = 0;
    private int efekWaktuTidakBuangAirCounter = 0;
    private boolean isUdahMakanDalamSatuHari = false;

    // --------------------------------------------------------------------------

    public String getNama(){
        return nama;
    }

    public void setNama(String nama){
        this.nama=nama;
    }

    public Pekerjaan getPekerjaan(){
        return pekerjaan;
    }
    
    public void setPekerjaan(Pekerjaan pekerjaan){
        this.pekerjaan=pekerjaan;
    }

    public int getUang(){
        return uang;
    }

    public void setUang(int uang){
        this.uang=uang;
    }

    public int getKesehatan(){
        return kesehatan;
    }

    public void setKesehatan(int kesehatan){
        this.kesehatan=kesehatan;
    }

    public int getMaxKesehatan(){
        return maxKesehatan;
    }

    public int getKekenyangan(){
        return kekenyangan;
    }

    public void setKekenyangan(int kekenyangan){
        this.kekenyangan=kekenyangan;
    }

    public int getMaxKekenyangan(){
        return maxKekenyangan;
    }

    public int getMood(){
        return mood;
    }

    public void setMood(int mood){
        this.mood=mood;
    }

    public int getMaxMood(){
        return maxMood;
    }

    public boolean getLightUpdated(){
        return lightUpdated;
    }

    public void setLightUpdated(boolean lightUpdated){
        this.lightUpdated=lightUpdated;
    } 

    public Benda getCurrentLight(){
        return currentLight;
    }

    public void setCurrentLight(Benda currentLight){
        this.currentLight=currentLight;
    }

    public String getCurrentLocation(){
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation){
        this.currentLocation=currentLocation;
    }

    public int getIndexLocationRuangan(){
        return indexLocationRuangan;
    }

    public void setIndexLocationRuangan(int indexLocationRuangan){
        this.indexLocationRuangan=indexLocationRuangan;
    }

    public int getIndexBendaYangDisentuh(){
        return indexBendaYangDisentuh;
    }

    public void setIndexBendaYangDisentuh(int indexBendaYangDisentuh){
        this.indexBendaYangDisentuh=indexBendaYangDisentuh;
    }

    public int getIndexRumahYangDimasuki(){
        return indexRumahYangDimasuki;
    }

    public void setIndexRumahYangDimasuki(int indexRumahYangDimasuki){
        this.indexRumahYangDimasuki=indexRumahYangDimasuki;
    }

    public int getCurrentMap(){
        return currentMap;
    }

    public void setCurrentMap(int currentMap){
        this.currentMap=currentMap;
    }

    public static String getKeteranganMati(){
        return keteranganMati;
    }

    public static void setKeteranganMati(String newKeteranganMati){
        keteranganMati=newKeteranganMati;
    }

    public boolean getIsMati(){
        return isMati;
    }

    public void setIsMati(boolean isMati){
        this.isMati=isMati;
    }

    public boolean getIsUpgradeDone(){
        return isUpgradeDone;
    }

    public void setIsUpgradeDone(boolean isUpgradeDone){
        this.isUpgradeDone=isUpgradeDone;
    }

    public boolean getIsBarangSampai(){
        return isBarangSampai;
    }

    public void setIsBarangSampai(boolean isBarangSampai){
        this.isBarangSampai=isBarangSampai;
    }

    public String getTempDialogUpgrade(){
        return tempDialogUpgrade;
    }

    public void setTempDialogUpgrade(String tempDialogUpgrade){
        this.tempDialogUpgrade=tempDialogUpgrade;
    }

    public String getTempDialogBarang(){
        return tempDialogBarang;
    }

    public void setTempDialogBarang(String tempDialogBarang){
        this.tempDialogBarang=tempDialogBarang;
    }

    public int getScreenX(){
        return screenX;
    }

    public int getScreenY(){
        return screenY;
    }

    public Rumah getRumah(){
        return rumah;
    }

    public void setRumah(Rumah rumah){
        this.rumah=rumah;
    }

    public int getTempInt(){
        return tempInt;
    }

    public void setTempInt(int tempInt){
        this.tempInt=tempInt;
    }

    public Benda getTempBenda(){
        return tempBenda;
    }

    public void setTempBenda(Benda tempBenda){
        this.tempBenda=tempBenda;
    }

    public boolean getIsCanBuy(){
        return isCanBuy;
    }

    public void setIsCanBuy(boolean isCanBuy){
        this.isCanBuy=isCanBuy;
    }

    public int getRemainingTimeBuy(){
        return remainingTimeBuy;
    }

    public void setRemainingTimeBuy(int remainingTimeBuy){
        this.remainingTimeBuy=remainingTimeBuy;
    }

    public int getIndexSimSaatBeli(){
        return indexSimSaatBeli;
    }

    public void setIndexSimSaatBeli(int indexSimSaatBeli){
        this.indexSimSaatBeli=indexSimSaatBeli;
    }

    public int getItemBuyTempIndex(){
        return itemBuyTempIndex;
    }

    public void setItemBuyTempIndex(int itemBuyTempIndex){
        this.itemBuyTempIndex=itemBuyTempIndex;
    }

    public boolean getIsLockBuy(){
        return isLockBuy;
    }

    public void setIsLockBuy(boolean isLockBuy){
        this.isLockBuy=isLockBuy;
    }

    public int getEfekWaktuTidakTidurCounter(){
        return efekWaktuTidakTidurCounter;
    }

    public void setEfekWaktuTidakTidurCounter(int efekWaktuTidakTidurCounter){
        this.efekWaktuTidakTidurCounter=efekWaktuTidakTidurCounter;
    }

    public int getEfekWaktuTidakBuangAirCounter(){
        return efekWaktuTidakBuangAirCounter;
    }

    public void setEfekWaktuTidakBuangAirCounter(int efekWaktuTidakBuangAirCounter){
        this.efekWaktuTidakBuangAirCounter=efekWaktuTidakBuangAirCounter;
    }

    public boolean getIsUdahMakanDalamSatuHari(){
        return isUdahMakanDalamSatuHari;
    }

    public void setIsUdahMakanDalamSatuHari(boolean isUdahMakanDalamSatuHari){
        this.isUdahMakanDalamSatuHari=isUdahMakanDalamSatuHari;
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

        screenX = gamePanel.getScreenWidth() / 2 - (gamePanel.getTileSize() / 2); // set the player's position on the screen
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
        // indexRumahYangDimasuki = gamePanel.listSim.size();
        indexLocationRuangan = 0;
        setSpeed(4);
        setDirection("down");
    }

    public void setItems() {
        // // barang yang bisa dimiliki
        // // BAHAN MAKANAN
        // inventory.add(new BahanMakanan_Ayam());
        // inventory.add(new BahanMakanan_Bayam());
        // inventory.add(new BahanMakanan_Kacang());
        // inventory.add(new BahanMakanan_Kentang());
        // inventory.add(new BahanMakanan_Nasi());
        // inventory.add(new BahanMakanan_Sapi());
        // inventory.add(new BahanMakanan_Susu());
        // inventory.add(new BahanMakanan_Wortel());

        // // MAKANAN
        // inventory.add(new Makanan_Bistik());
        // inventory.add(new Makanan_NasiAyam());
        // inventory.add(new Makanan_NasiKari());
        // inventory.add(new Makanan_SusuKacang());
        // inventory.add(new Makanan_TumisSayur());

        // FURNITUR
        // OBJEK BASIC
        getInventory().add(new Furnitur_KasurSingle(gamePanel));
        getInventory().add(new Furnitur_Toilet(gamePanel));
        getInventory().add(new Furnitur_KomporGas(gamePanel));
        getInventory().add(new Furnitur_MejaKursi(gamePanel));
        getInventory().add(new Furnitur_Jam(gamePanel));
        getInventory().add(new Furnitur_Radio(gamePanel));
        // to be continuedd
    }

    public void getImage() {
        setUp1(setupImage("sim/p2_up_1"));
        setUp2(setupImage("sim/p2_up_2"));
        setDown1( setupImage("sim/p2_down_1"));
        setDown2(setupImage("sim/p2_down_2"));
        setLeft1(setupImage("sim/p2_left_1"));
        setLeft2(setupImage("sim/p2_left_2"));
        setRight1(setupImage("sim/p2_right_1"));
        setRight2(setupImage("sim/p2_right_2"));
    }

    public void update() { // update the position of the player
        if (keyHandler.isUpPressed() || keyHandler.isDownPressed() || keyHandler.isLeftPressed() || keyHandler.isRightPressed()
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
            indexBendaYangDisentuh = gamePanel.getCollisionChecker().checkBenda(this, true); // return the index of the benda
                                                                                        // that theplayer is colliding
                                                                                        // with
            usingBenda(indexBendaYangDisentuh);
            // System.out.println(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexRumahYangDimasuki);

            // indexRumahYangDimasuki = indexBendaYangDisentuh;
            // System.out.println(indexBendaYangDisentuh); // ngasih index benda yang
            // disentuh
            // pickUpObject(indexBendaYangDisentuh);

            // System.out.println(indexBendaYangDisentuh);
            // System.out.println(worldX);
            // System.out.println(rumah.ruanganRumah.get(0).bendaRuangan.get(0).worldX);
            // System.out.println(solidArea.x);
            // System.out.println(rumah.ruanganRumah.get(0).bendaRuangan.get(0).solidArea.x);

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
            // berlaku hanya jika sim yang mati adalah sim index 0
            // if (gamePanel.listSim.size() > 1) {
            // gamePanel.listSim.remove(gamePanel.indexCurrentSim);
            // gamePanel.listRumah[0].remove(gamePanel.indexCurrentSim);
            // for (int i = 0; i < gamePanel.listSim.size(); i++) {
            // if (gamePanel.listSim.get(i).indexRumahYangDimasuki != 999) {
            // if (gamePanel.listSim.get(i).indexRumahYangDimasuki >
            // gamePanel.indexCurrentSim) {
            // gamePanel.listSim.get(i).indexRumahYangDimasuki--;
            // } else if (gamePanel.listSim.get(i).indexRumahYangDimasuki ==
            // gamePanel.indexCurrentSim) {
            // gamePanel.listSim.get(i).indexRumahYangDimasuki = i;
            // gamePanel.listSim.get(i).setDefaultValues();
            // }
            // }
            // }
            // gamePanel.indexCurrentSim = 0;

            // // for (int i = gamePanel.indexCurrentSim; i < gamePanel.listSim.size();
            // i++){
            // // if (gamePanel.listSim.get(i).indexRumahYangDimasuki != 999){
            // // gamePanel.listSim.get(i).indexRumahYangDimasuki--;
            // // }
            // // }
            // } else
            if (gamePanel.getListSim().size() == 1) {
                gamePanel.setIsOneSim(true);
                gamePanel.setWorldTimeCounter(0);
                gamePanel.setWorldTimeSatuHariCounter(0);
            }

            keteranganMati = "Depresi";
            isMati = true;
            // gamePanel.listSim.remove(gamePanel.indexCurrentSim);
            // gamePanel.listRumah[0].remove(gamePanel.indexCurrentSim);
            // if (gamePanel.indexCurrentSim == 0){
            // gamePanel.indexCurrentSim++;
            // } else {
            // gamePanel.indexCurrentSim--;
            // }
            // if (gamePanel.listSim.get(gamePanel.indexCurrentSim).indexRumahYangDimasuki
            // == 0){
            // gamePanel.listSim.get(gamePanel.indexCurrentSim).indexRumahYangDimasuki++;
            // } else {
            // gamePanel.listSim.get(gamePanel.indexCurrentSim).indexRumahYangDimasuki--;
            // }
        } else if (kekenyangan <= 0) {
            // if (gamePanel.listSim.size() > 1) {
            // gamePanel.listSim.remove(gamePanel.indexCurrentSim);
            // gamePanel.listRumah[0].remove(gamePanel.indexCurrentSim);
            // for (int i = 0; i < gamePanel.listSim.size(); i++) {
            // if (gamePanel.listSim.get(i).indexRumahYangDimasuki != 999) {
            // if (gamePanel.listSim.get(i).indexRumahYangDimasuki >
            // gamePanel.indexCurrentSim) {
            // gamePanel.listSim.get(i).indexRumahYangDimasuki--;
            // } else if (gamePanel.listSim.get(i).indexRumahYangDimasuki ==
            // gamePanel.indexCurrentSim) {
            // gamePanel.listSim.get(i).indexRumahYangDimasuki = i;
            // gamePanel.listSim.get(i).setDefaultValues();
            // }
            // }
            // }
            // gamePanel.indexCurrentSim = 0;
            // } else
            if (gamePanel.getListSim().size() == 1) {
                gamePanel.setIsOneSim(true);
                gamePanel.setWorldTimeCounter(0);
                gamePanel.setWorldTimeSatuHariCounter(0);
            }
            keteranganMati = "Kelaparan";
            isMati = true;
            // gamePanel.listSim.remove(gamePanel.indexCurrentSim);
            // gamePanel.listRumah[0].remove(gamePanel.indexCurrentSim);
            // if (gamePanel.indexCurrentSim == 0){
            // gamePanel.indexCurrentSim++;
            // } else {
            // gamePanel.indexCurrentSim--;
            // }
            // if (gamePanel.listSim.get(gamePanel.indexCurrentSim).indexRumahYangDimasuki
            // == 0){
            // gamePanel.listSim.get(gamePanel.indexCurrentSim).indexRumahYangDimasuki++;
            // } else {
            // gamePanel.listSim.get(gamePanel.indexCurrentSim).indexRumahYangDimasuki--;
            // }
        } else if (kesehatan <= 0) {
            // if (gamePanel.listSim.size() > 1) {
            // gamePanel.listSim.remove(gamePanel.indexCurrentSim);
            // gamePanel.listRumah[0].remove(gamePanel.indexCurrentSim);
            // for (int i = 0; i < gamePanel.listSim.size(); i++) {
            // if (gamePanel.listSim.get(i).indexRumahYangDimasuki != 999) {
            // if (gamePanel.listSim.get(i).indexRumahYangDimasuki >
            // gamePanel.indexCurrentSim) {
            // gamePanel.listSim.get(i).indexRumahYangDimasuki--;
            // } else if (gamePanel.listSim.get(i).indexRumahYangDimasuki ==
            // gamePanel.indexCurrentSim) {
            // gamePanel.listSim.get(i).indexRumahYangDimasuki = i;
            // gamePanel.listSim.get(i).setDefaultValues();
            // }
            // }
            // }
            // gamePanel.indexCurrentSim = 0;
            // } else
            if (gamePanel.getListSim().size() == 1) {
                gamePanel.setIsOneSim(true);
                gamePanel.setWorldTimeCounter(0);
                gamePanel.setWorldTimeSatuHariCounter(0);
            }
            keteranganMati = "Sakit";
            isMati = true;
            // gamePanel.listSim.remove(gamePanel.indexCurrentSim);
            // gamePanel.listRumah[0].remove(gamePanel.indexCurrentSim);
            // if (gamePanel.indexCurrentSim == 0){
            // gamePanel.indexCurrentSim++;
            // } else {
            // gamePanel.indexCurrentSim--;
            // }
            // if (gamePanel.listSim.get(gamePanel.indexCurrentSim).indexRumahYangDimasuki
            // == 0){
            // gamePanel.listSim.get(gamePanel.indexCurrentSim).indexRumahYangDimasuki++;
            // } else {
            // gamePanel.listSim.get(gamePanel.indexCurrentSim).indexRumahYangDimasuki--;
            // }
        }

    }

    public void pickUpObject(int index) {
        if (index != 999) {
            if (gamePanel.getListSim().get(indexRumahYangDimasuki).rumah.getRuanganRumah().get(indexLocationRuangan).getBendaRuangan()
                    .get(index) instanceof Furnitur) {
                if (canObtainItem(gamePanel.getListSim().get(indexRumahYangDimasuki).rumah.getRuanganRumah()
                        .get(indexLocationRuangan).getBendaRuangan().get(index))) {
                    Furnitur furniturTemp = (Furnitur) gamePanel.getListSim().get(indexRumahYangDimasuki).rumah.getRuanganRumah()
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

        // if (index != 999){ // 999 means there is no collision with benda
        // if (!(gamePanel.benda[currentMap][index] instanceof Furnitur ||
        // gamePanel.benda[currentMap][index] instanceof Rumah)){
        // if (canObtainItem(gamePanel.benda[currentMap][index])){
        // // text = "kamu mendapatkan " + gamePanel.benda[currentMap][index].nama;
        // } else {
        // // text = "Inventory penuh";
        // }
        // // gamePanel.ui.addMessage(text);
        // gamePanel.benda[currentMap][index] = null;
        // }
        // }
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
        // gamePanel.listSim.get(gamePanel.indexCurrentSim).indexRumahYangDimasuki
        if (gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).indexRumahYangDimasuki != 999) {
            if (indexBendayangDisentuh != 999) {
                if (gamePanel.getKeyHandler().isEnterPressed()) {
                    int indexRumahYangDimasuki = gamePanel.getListSim()
                            .get(gamePanel.getIndexCurrentSim()).indexRumahYangDimasuki;
                    int indexLocationRuangan = gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).indexLocationRuangan;
                    Furnitur furniturTemp = (Furnitur) gamePanel.getListSim().get(indexRumahYangDimasuki).rumah.getRuanganRumah()
                            .get(indexLocationRuangan).getBendaRuangan().get(indexBendayangDisentuh);
                    furniturTemp.action();
                }
            }

            // gamePanel.listSim.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexRumahYangDimasuki).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(i)
            // System.out.println(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexRumahYangDimasuki);
            // Benda bendaTemp =
            // gamePanel.listSim.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexRumahYangDimasuki).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(i);
            // if (gamePanel.keyHandler.enterPressed && bendaTemp instanceof Furnitur){ //
            // klw di tekan enter dan benda yang disentuh adalah furnitur walaupun memang
            // furnitur
            // Furnitur furniturTemp = (Furnitur) bendaTemp;
            // furniturTemp.action();
            // }
        }
    }

   /*  public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }*/

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
                                gamePanel.getListSim().get(i).pekerjaan.setWorldTimeCounterForStartJobAfterChangeJob(gamePanel.getListSim()
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
                            gamePanel.getUi().setCurrentAksiCadangan("Makan " + bahanMakanan.getName() + "\nKekenyangan bertambah "
                                    + bahanMakanan.getKekenyangan());
                            gamePanel.getUi().setCurrentAksi("Makan");
                            gamePanel.setGameState(gamePanel.getTimerState());
                            gamePanel.getUi().setCurrentAksiDone(false);
                            isUdahMakanDalamSatuHari = true;
                            gamePanel.getUi().setTempDurasi(30);
                            gamePanel.getKeyHandler().setThreadTemp(gamePanel.getUi().startTimerThread(30));
                            // gamePanel.ui.setelahAksi(30);

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
                                gamePanel.getListSim().get(i).pekerjaan.setWorldTimeCounterForStartJobAfterChangeJob(gamePanel.getListSim()
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
                            gamePanel.getUi().setCurrentAksiCadangan("Makan " + makanan.getName() + "\nKekenyangan bertambah "
                                    + makanan.getKekenyangan());
                            gamePanel.getUi().setCurrentAksi("Makan");
                            gamePanel.setGameState(gamePanel.getTimerState());
                            gamePanel.getUi().setCurrentAksiDone(false);
                            isUdahMakanDalamSatuHari = true;
                            gamePanel.getUi().setTempDurasi(30);
                            gamePanel.getKeyHandler().setThreadTemp(gamePanel.getUi().startTimerThread(30));
                            // gamePanel.ui.setelahAksi(30);
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
                    if (furnitur.getQuantity()> 1) {
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
            if (getInventory().size()< getMaxInventorySize()) {
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

        // g2d.setColor(Color.red);
        // g2d.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);
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
                        // gamePanel.ui.setCharIndex(0);
                        // gamePanel.ui.setCombinedText("");
                        // gamePanel.gameState = gamePanel.dialogState;
                        // gamePanel.ui.setCurrentDialog"Pembelian barang oleh "
                        // + gamePanel.listSim.get(indexSimSaatBeli).nama + " berhasil";
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
                        // gamePanel.ui.setCharIndex(0);
                        // gamePanel.ui.setCombinedText("");
                        // gamePanel.gameState = gamePanel.dialogState;
                        // gamePanel.ui.setCurrentDialog"Pembelian barang oleh "
                        // + gamePanel.listSim.get(indexSimSaatBeli).nama + " berhasil";
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
                        // gamePanel.ui.setCharIndex(0);
                        // gamePanel.ui.setCombinedText("");
                        // gamePanel.gameState = gamePanel.dialogState;
                        // gamePanel.ui.setCurrentDialog"Pembelian barang oleh "
                        // + gamePanel.listSim.get(indexSimSaatBeli).nama + " berhasil";
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

        // while (remainingTimeBuy > 0) {
        // System.out.println("Waktu tersisa: " + remainingTimeBuy + " detik");
        // try {
        // Thread.sleep(1000); // tunggu 1 detik
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // isCanBuy = true; // atur isCanBuy menjadi true setelah 18 menit
        // remainingTimeBuy = 0;
        // System.out.println("Waktu diskip!");
        // // tambah aksi

        // if (gamePanel.npc[0][4].inventory.get(itemBuyTempIndex) instanceof
        // BahanMakanan) {
        // BahanMakanan makanan = (BahanMakanan)
        // gamePanel.npc[0][4].inventory.get(itemBuyTempIndex);
        // if (gamePanel.listSim.get(indexSimSaatBeli).uang >= makanan.harga) {
        // if (gamePanel.listSim.get(indexSimSaatBeli).canObtainItem(makanan)) {
        // gamePanel.listSim.get(indexSimSaatBeli).uang -= makanan.harga;
        // gamePanel.ui.setCharIndex(0);
        // gamePanel.ui.setCombinedText("");
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.setCurrentDialog"Pembelian barang oleh "
        // + gamePanel.listSim.get(indexSimSaatBeli).nama + " berhasil";
        // } else {
        // gamePanel.ui.setSubState(0);
        // gamePanel.ui.setCharIndex(0);
        // gamePanel.ui.setCombinedText("");
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.setCurrentDialog("Inventory penuh");
        // }
        // } else {
        // gamePanel.ui.setSubState(0);
        // gamePanel.ui.setCharIndex(0);
        // gamePanel.ui.setCombinedText("");
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.setCurrentDialog"Uang tidak cukup";
        // // drawDialogScreen(); // ini ga perlu karena sudah ada di atas
        // }
        // } else if (gamePanel.npc[0][4].inventory.get(itemBuyTempIndex) instanceof
        // Furnitur) {
        // Furnitur furnitur = (Furnitur)
        // gamePanel.npc[0][4].inventory.get(itemBuyTempIndex);
        // if (gamePanel.listSim.get(indexSimSaatBeli).uang >= furnitur.harga) {
        // if (gamePanel.listSim.get(indexSimSaatBeli).canObtainItem(furnitur)) {
        // gamePanel.listSim.get(indexSimSaatBeli).uang -= furnitur.harga;
        // gamePanel.ui.setCharIndex(0);
        // gamePanel.ui.setCombinedText("");
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.setCurrentDialog"Pembelian barang oleh "
        // + gamePanel.listSim.get(indexSimSaatBeli).nama + " berhasil";
        // } else {
        // gamePanel.ui.setSubState(0);
        // gamePanel.ui.setCharIndex(0);
        // gamePanel.ui.setCombinedText("");
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.setCurrentDialog("Inventory penuh");
        // }
        // } else {
        // gamePanel.ui.setSubState(0);
        // gamePanel.ui.setCharIndex(0);
        // gamePanel.ui.setCombinedText("");
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.setCurrentDialog"Uang tidak cukup";
        // // drawDialogScreen(); // ini ga perlu karena sudah ada di atas
        // }
        // } else if (gamePanel.npc[0][4].inventory.get(itemBuyTempIndex) instanceof
        // Lampu) {
        // Lampu lampu = (Lampu) gamePanel.npc[0][4].inventory.get(itemBuyTempIndex);
        // if (gamePanel.listSim.get(indexSimSaatBeli).uang >= lampu.harga) {
        // if (gamePanel.listSim.get(indexSimSaatBeli).canObtainItem(lampu)) {
        // gamePanel.listSim.get(indexSimSaatBeli).uang -= lampu.harga;
        // gamePanel.ui.setCharIndex(0);
        // gamePanel.ui.setCombinedText("");
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.setCurrentDialog"Pembelian barang oleh "
        // + gamePanel.listSim.get(indexSimSaatBeli).nama + " berhasil";
        // } else {
        // if (gamePanel.listSim.get(indexSimSaatBeli).inventory
        // .size() >= gamePanel.listSim.get(indexSimSaatBeli).maxInventorySize) {
        // gamePanel.ui.setSubState(0);
        // gamePanel.ui.setCharIndex(0);
        // gamePanel.ui.setCombinedText("");
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.setCurrentDialog"Inventory penuh";
        // } else {
        // gamePanel.ui.setSubState(0);
        // gamePanel.ui.setCharIndex(0);
        // gamePanel.ui.setCombinedText("");
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.setCurrentDialog"Anda sudah memiliki lampu!";
        // }
        // }
        // } else {
        // gamePanel.ui.setSubState(0);
        // gamePanel.ui.setCharIndex(0);
        // gamePanel.ui.setCombinedText("");
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.setCurrentDialog"Uang tidak cukup";
        // // drawDialogScreen(); nggk perlu ini karena sudah ada di atas
        // }
        // }

        // }
        // remainingTimeBuy--;
        // }
        // System.out.println("Waktu habis!");
        // isCanBuy = true; // atur isCanBuy menjadi true setelah 18 menit
        // remainingTimeBuy = 0;
        // // tambah aksi

        // if (gamePanel.npc[0][4].inventory.get(itemBuyTempIndex) instanceof
        // BahanMakanan) {
        // BahanMakanan makanan = (BahanMakanan)
        // gamePanel.npc[0][4].inventory.get(itemBuyTempIndex);
        // if (gamePanel.listSim.get(indexSimSaatBeli).uang >= makanan.harga) {
        // if (gamePanel.listSim.get(indexSimSaatBeli).canObtainItem(makanan)) {
        // gamePanel.listSim.get(indexSimSaatBeli).uang -= makanan.harga;
        // gamePanel.ui.setCharIndex(0);
        // gamePanel.ui.setCombinedText("");
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.setCurrentDialog"Pembelian barang oleh "
        // + gamePanel.listSim.get(indexSimSaatBeli).nama + " berhasil";
        // } else {
        // gamePanel.ui.setSubState(0);
        // gamePanel.ui.setCharIndex(0);
        // gamePanel.ui.setCombinedText("");
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.setCurrentDialog"Inventory penuh";
        // }
        // } else {
        // gamePanel.ui.setSubState(0);
        // gamePanel.ui.setCharIndex(0);
        // gamePanel.ui.setCombinedText("");
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.setCurrentDialog"Uang tidak cukup";
        // // drawDialogScreen(); // ini ga perlu karena sudah ada di atas
        // }
        // } else if (gamePanel.npc[0][4].inventory.get(itemBuyTempIndex) instanceof
        // Furnitur) {
        // Furnitur furnitur = (Furnitur)
        // gamePanel.npc[0][4].inventory.get(itemBuyTempIndex);
        // if (gamePanel.listSim.get(indexSimSaatBeli).uang >= furnitur.harga) {
        // if (gamePanel.listSim.get(indexSimSaatBeli).canObtainItem(furnitur)) {
        // gamePanel.listSim.get(indexSimSaatBeli).uang -= furnitur.harga;
        // gamePanel.ui.setCharIndex(0);
        // gamePanel.ui.setCombinedText("");
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.setCurrentDialog"Pembelian barang oleh "
        // + gamePanel.listSim.get(indexSimSaatBeli).nama + " berhasil";
        // } else {
        // gamePanel.ui.setSubState(0);
        // gamePanel.ui.setCharIndex(0);
        // gamePanel.ui.setCombinedText("");
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.setCurrentDialog"Inventory penuh";
        // }
        // } else {
        // gamePanel.ui.setSubState(0);
        // gamePanel.ui.setCharIndex(0);
        // gamePanel.ui.setCombinedText("");
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.setCurrentDialog"Uang tidak cukup";
        // // drawDialogScreen(); // ini ga perlu karena sudah ada di atas
        // }
        // } else if (gamePanel.npc[0][4].inventory.get(itemBuyTempIndex) instanceof
        // Lampu) {
        // Lampu lampu = (Lampu) gamePanel.npc[0][4].inventory.get(itemBuyTempIndex);
        // if (gamePanel.listSim.get(indexSimSaatBeli).uang >= lampu.harga) {
        // if (gamePanel.listSim.get(indexSimSaatBeli).canObtainItem(lampu)) {
        // gamePanel.listSim.get(indexSimSaatBeli).uang -= lampu.harga;
        // gamePanel.ui.setCharIndex(0);
        // gamePanel.ui.setCombinedText("");
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.setCurrentDialog"Pembelian barang oleh "
        // + gamePanel.listSim.get(indexSimSaatBeli).nama + " berhasil";
        // } else {
        // if (gamePanel.listSim.get(indexSimSaatBeli).inventory
        // .size() >= gamePanel.listSim.get(indexSimSaatBeli).maxInventorySize) {
        // gamePanel.ui.setSubState(0);
        // gamePanel.ui.setCharIndex(0);
        // gamePanel.ui.setCombinedText("");
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.setCurrentDialog"Inventory penuh";
        // } else {
        // gamePanel.ui.setSubState(0);
        // gamePanel.ui.setCharIndex(0);
        // gamePanel.ui.setCombinedText("");
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.setCurrentDialog"Anda sudah memiliki lampu!";
        // }
        // }
        // } else {
        // gamePanel.ui.setSubState(0);
        // gamePanel.ui.setCharIndex(0);
        // gamePanel.ui.setCombinedText("");
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.setCurrentDialog"Uang tidak cukup";
        // // drawDialogScreen(); nggk perlu ini karena sudah ada di atas
        // }
        // }

    }

}
