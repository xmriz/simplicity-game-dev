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
    private int uang = 99999999;
    private int kesehatan = 80;
    public final int maxKesehatan = 100;
    private int kekenyangan = 80;
    public final int maxKekenyangan = 100;
    private int mood = 80;
    public final int maxMood = 100;
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

    public final int screenX;
    public final int screenY;

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

    public int getKekenyangan(){
        return kekenyangan;
    }

    public void setKekenyangan(int kekenyangan){
        this.kekenyangan=kekenyangan;
    }

    public int getMood(){
        return mood;
    }

    public void setMood(int mood){
        this.mood=mood;
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
        pekerjaan.indexPekerjaan = rand.nextInt(5);

        screenX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2); // set the player's position on the screen
                                                                        // (always center)
        screenY = gamePanel.screenHeight / 2 - (gamePanel.tileSize / 2);

        solidArea = new Rectangle(8, 16, 32, 32);

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getImage();
        setItems();
    }

    public void setDefaultValues() { // set the default values of the player
        worldX = 2 * gamePanel.tileSize; // set the player's position in his own rumah
        worldY = 2 * gamePanel.tileSize;
        // indexRumahYangDimasuki = gamePanel.listSim.size();
        indexLocationRuangan = 0;
        speed = 4;
        direction = "down";
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
        inventory.add(new Furnitur_KasurSingle(gamePanel));
        inventory.add(new Furnitur_Toilet(gamePanel));
        inventory.add(new Furnitur_KomporGas(gamePanel));
        inventory.add(new Furnitur_MejaKursi(gamePanel));
        inventory.add(new Furnitur_Jam(gamePanel));
        inventory.add(new Furnitur_Radio(gamePanel));
        // to be continuedd
    }

    public void getImage() {
        up1 = setupImage("sim/p2_up_1");
        up2 = setupImage("sim/p2_up_2");
        down1 = setupImage("sim/p2_down_1");
        down2 = setupImage("sim/p2_down_2");
        left1 = setupImage("sim/p2_left_1");
        left2 = setupImage("sim/p2_left_2");
        right1 = setupImage("sim/p2_right_1");
        right2 = setupImage("sim/p2_right_2");
    }

    public void update() { // update the position of the player
        if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed
                || keyHandler.enterPressed) {
            if (keyHandler.upPressed) {
                direction = "up";
            } else if (keyHandler.downPressed) {
                direction = "down";
            } else if (keyHandler.leftPressed) {
                direction = "left";
            } else if (keyHandler.rightPressed) {
                direction = "right";
            }

            // check for collision
            collisionOn = false;
            gamePanel.collisionChecker.checkTile(this);

            // check for benda collision
            indexBendaYangDisentuh = gamePanel.collisionChecker.checkBenda(this, true); // return the index of the benda
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
            int npcIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.npc);
            interactNPC(npcIndex);

            // check event
            gamePanel.eventHandler.checkEvent();

            // after checking all turn of enterPressed
            gamePanel.keyHandler.enterPressed = false;

            // if there is a collision, sim can't move
            if (!collisionOn && !keyHandler.enterPressed) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 12) {
                spriteCounter = 0;
                if (spriteNumber == 1) {
                    spriteNumber = 2;
                } else {
                    spriteNumber = 1;
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
            if (gamePanel.listSim.size() == 1) {
                gamePanel.isOneSim = true;
                gamePanel.worldTimeCounter = 0;
                gamePanel.worldTimeSatuHariCounter = 0;
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
            if (gamePanel.listSim.size() == 1) {
                gamePanel.isOneSim = true;
                gamePanel.worldTimeCounter = 0;
                gamePanel.worldTimeSatuHariCounter = 0;
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
            if (gamePanel.listSim.size() == 1) {
                gamePanel.isOneSim = true;
                gamePanel.worldTimeCounter = 0;
                gamePanel.worldTimeSatuHariCounter = 0;
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
            if (gamePanel.listSim.get(indexRumahYangDimasuki).rumah.getRuanganRumah().get(indexLocationRuangan).getBendaRuangan()
                    .get(index) instanceof Furnitur) {
                if (canObtainItem(gamePanel.listSim.get(indexRumahYangDimasuki).rumah.getRuanganRumah()
                        .get(indexLocationRuangan).getBendaRuangan().get(index))) {
                    Furnitur furniturTemp = (Furnitur) gamePanel.listSim.get(indexRumahYangDimasuki).rumah.getRuanganRumah()
                            .get(indexLocationRuangan).getBendaRuangan().get(index);
                    gamePanel.listSim.get(indexRumahYangDimasuki).rumah.getRuanganRumah()
                            .get(indexLocationRuangan).getBendaRuangan().remove(index);
                    gamePanel.ui.charIndex = 0;
                    gamePanel.ui.combinedText = "";
                    gamePanel.gameState = gamePanel.dialogState;
                    gamePanel.ui.currentDialog = "Berhasil mengambil " + furniturTemp.getName();
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
            gamePanel.ui.combinedText = "";
            gamePanel.ui.charIndex = 0;
            if (gamePanel.keyHandler.enterPressed) {
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.npc[currentMap][i].speak();
            }
        }
    }

    public void usingBenda(int indexBendayangDisentuh) {
        // gamePanel.listSim.get(gamePanel.indexCurrentSim).indexRumahYangDimasuki
        if (gamePanel.listSim.get(gamePanel.indexCurrentSim).indexRumahYangDimasuki != 999) {
            if (indexBendayangDisentuh != 999) {
                if (gamePanel.keyHandler.enterPressed) {
                    int indexRumahYangDimasuki = gamePanel.listSim
                            .get(gamePanel.indexCurrentSim).indexRumahYangDimasuki;
                    int indexLocationRuangan = gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan;
                    Furnitur furniturTemp = (Furnitur) gamePanel.listSim.get(indexRumahYangDimasuki).rumah.getRuanganRumah()
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

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void selectItem() {
        int itemIndex = UI.getItemIndexOnSlot(gamePanel.ui.simSlotRow, gamePanel.ui.simSlotCol);

        if (itemIndex < inventory.size()) {
            Benda selectedBenda = inventory.get(itemIndex);

            if (selectedBenda instanceof BahanMakanan) {
                if (currentMap == 0) {
                    gamePanel.ui.charIndex = 0;
                    gamePanel.ui.combinedText = "";
                    gamePanel.gameState = gamePanel.dialogState;
                    gamePanel.ui.currentDialog = "Anda tidak bisa makan di luar rumah.";
                } else {
                    if (indexBendaYangDisentuh != 999) {
                        if (gamePanel.listSim.get(indexRumahYangDimasuki).rumah.getRuanganRumah()
                                .get(indexLocationRuangan).getBendaRuangan()
                                .get(indexBendaYangDisentuh) instanceof Furnitur_MejaKursi) {
                            BahanMakanan bahanMakanan = (BahanMakanan) selectedBenda;
                            bahanMakanan.eat(this);
                            gamePanel.worldTimeCounter += 30;
                            gamePanel.worldTimeSatuHariCounter += 30;

                            for (int i = 0; i < gamePanel.listSim.size(); i++) {
                                gamePanel.listSim.get(i).pekerjaan.worldTimeCounterForStartJobAfterChangeJob += 30;
                                gamePanel.listSim.get(i).efekWaktuTidakTidurCounter += 30;
                                if (gamePanel.listSim.get(i).isUdahMakanDalamSatuHari) {
                                    gamePanel.listSim.get(i).efekWaktuTidakBuangAirCounter += 30;
                                }
                            }

                            if (bahanMakanan.getQuantity() > 1) {
                                bahanMakanan.decQuantity(1);
                            } else {
                                inventory.remove(itemIndex);
                            }
                            gamePanel.ui.currentAksiCadangan = "Makan " + bahanMakanan.getName() + "\nKekenyangan bertambah "
                                    + bahanMakanan.getKekenyangan();
                            gamePanel.ui.currentAksi = "Makan";
                            gamePanel.gameState = gamePanel.timerState;
                            gamePanel.ui.currentAksiDone = false;
                            isUdahMakanDalamSatuHari = true;
                            gamePanel.ui.tempDurasi = 30;
                            gamePanel.keyHandler.threadTemp = gamePanel.ui.startTimerThread(30);
                            // gamePanel.ui.setelahAksi(30);

                        } else {
                            gamePanel.ui.charIndex = 0;
                            gamePanel.ui.combinedText = "";
                            gamePanel.gameState = gamePanel.dialogState;
                            gamePanel.ui.currentDialog = "Anda harus makan di meja makan!";
                        }
                        indexBendaYangDisentuh = 999;
                    } else {
                        gamePanel.ui.charIndex = 0;
                        gamePanel.ui.combinedText = "";
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.currentDialog = "Anda harus makan di meja makan!";
                    }
                }
            } else if (selectedBenda instanceof Makanan) {
                if (currentMap == 0) {
                    gamePanel.ui.charIndex = 0;
                    gamePanel.ui.combinedText = "";
                    gamePanel.gameState = gamePanel.dialogState;
                    gamePanel.ui.currentDialog = "Anda tidak bisa makan di luar rumah.";
                } else {
                    if (indexBendaYangDisentuh != 999) {
                        if (gamePanel.listSim.get(indexRumahYangDimasuki).rumah.getRuanganRumah()
                                .get(indexLocationRuangan).getBendaRuangan()
                                .get(indexBendaYangDisentuh) instanceof Furnitur_MejaKursi) {
                            Makanan makanan = (Makanan) selectedBenda;
                            makanan.eat(this);
                            gamePanel.worldTimeCounter += 30;
                            gamePanel.worldTimeSatuHariCounter += 30;
                            for (int i = 0; i < gamePanel.listSim.size(); i++) {
                                gamePanel.listSim.get(i).pekerjaan.worldTimeCounterForStartJobAfterChangeJob += 30;
                                gamePanel.listSim.get(i).efekWaktuTidakTidurCounter += 30;
                                if (gamePanel.listSim.get(i).isUdahMakanDalamSatuHari) {
                                    gamePanel.listSim.get(i).efekWaktuTidakBuangAirCounter += 30;
                                }
                            }
                            if (makanan.getQuantity() > 1) {
                                makanan.decQuantity(1);
                            } else {
                                inventory.remove(itemIndex);
                            }
                            gamePanel.ui.currentAksiCadangan = "Makan " + makanan.getName() + "\nKekenyangan bertambah "
                                    + makanan.getKekenyangan();
                            gamePanel.ui.currentAksi = "Makan";
                            gamePanel.gameState = gamePanel.timerState;
                            gamePanel.ui.currentAksiDone = false;
                            isUdahMakanDalamSatuHari = true;
                            gamePanel.ui.tempDurasi = 30;
                            gamePanel.keyHandler.threadTemp = gamePanel.ui.startTimerThread(30);
                            // gamePanel.ui.setelahAksi(30);
                        } else {
                            gamePanel.ui.charIndex = 0;
                            gamePanel.ui.combinedText = "";
                            gamePanel.gameState = gamePanel.dialogState;
                            gamePanel.ui.currentDialog = "Anda harus makan di meja makan!";
                        }
                    } else {
                        gamePanel.ui.charIndex = 0;
                        gamePanel.ui.combinedText = "";
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.currentDialog = "Anda harus makan di meja makan!";
                    }
                }

            } else if (selectedBenda instanceof Furnitur) {
                if (currentMap == 0) {
                    gamePanel.ui.charIndex = 0;
                    gamePanel.ui.combinedText = "";
                    gamePanel.gameState = gamePanel.dialogState;
                    gamePanel.ui.currentDialog = "Tidak dapat memasang furnitur di luar\nrumah!";
                } else {
                    tempInt = itemIndex;
                    Furnitur furnitur = (Furnitur) selectedBenda;
                    if (furnitur.getQuantity()> 1) {
                        furnitur.decQuantity(1);
                    } else {
                        inventory.remove(itemIndex);
                    }
                    tempBenda = furnitur;
                    gamePanel.gameState = gamePanel.inputKoordinatBendaState;

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

        for (Benda benda : inventory) {
            if (benda.getName().equals(itemName)) {
                itemIndex = inventory.indexOf(benda);
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
                if (inventory.size() < maxInventorySize) {
                    inventory.add(item);
                    inventory.get(inventory.size() - 1).setQuantity(1);
                    canObtain = true;
                }
            }
        }

        // check if item is stackable
        else if (item.getStackable()) {
            int index = searchItemInInventory(item.getName());
            if (index != 999) {
                inventory.get(index).incQuantity(1);
                canObtain = true;
            } else {
                if (inventory.size() < maxInventorySize) {
                    inventory.add(item);
                    inventory.get(inventory.size() - 1).setQuantity(1);
                    canObtain = true;
                }
            }
        } else { // not stackable
            if (inventory.size() < maxInventorySize) {
                inventory.add(item);
                inventory.get(inventory.size() - 1).setQuantity(1);
                canObtain = true;
            }
        }

        return canObtain;
    }

    public void draw(Graphics2D g2d) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNumber == 1) {
                    image = up1;
                } else {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNumber == 1) {
                    image = down1;
                } else {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNumber == 1) {
                    image = left1;
                } else {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNumber == 1) {
                    image = right1;
                } else {
                    image = right2;
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
                if (gamePanel.npc[0][4].inventory.get(itemBuyTempIndex) instanceof BahanMakanan) {
                    BahanMakanan makanan = (BahanMakanan) gamePanel.npc[0][4].inventory.get(itemBuyTempIndex);
                    if (gamePanel.listSim.get(indexSimSaatBeli).canObtainItem(makanan)) {
                        // gamePanel.ui.charIndex = 0;
                        // gamePanel.ui.combinedText = "";
                        // gamePanel.gameState = gamePanel.dialogState;
                        // gamePanel.ui.currentDialog = "Pembelian barang oleh "
                        // + gamePanel.listSim.get(indexSimSaatBeli).nama + " berhasil";
                        isBarangSampai = true;
                        tempDialogBarang = "Pembelian barang oleh "
                                + gamePanel.listSim.get(indexSimSaatBeli).nama + "\nberhasil";
                    } else {
                        gamePanel.listSim.get(indexSimSaatBeli).uang += makanan.getHarga();
                        gamePanel.ui.subState = 0;
                        gamePanel.ui.charIndex = 0;
                        gamePanel.ui.combinedText = "";
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.currentDialog = "Inventory penuh";
                    }
                } else if (gamePanel.npc[0][4].inventory.get(itemBuyTempIndex) instanceof Furnitur) {
                    Furnitur furnitur = (Furnitur) gamePanel.npc[0][4].inventory.get(itemBuyTempIndex);
                    if (gamePanel.listSim.get(indexSimSaatBeli).canObtainItem(furnitur)) {
                        // gamePanel.ui.charIndex = 0;
                        // gamePanel.ui.combinedText = "";
                        // gamePanel.gameState = gamePanel.dialogState;
                        // gamePanel.ui.currentDialog = "Pembelian barang oleh "
                        // + gamePanel.listSim.get(indexSimSaatBeli).nama + " berhasil";
                        isBarangSampai = true;
                        tempDialogBarang = "Pembelian barang oleh "
                                + gamePanel.listSim.get(indexSimSaatBeli).nama + "\nberhasil";
                    } else {
                        gamePanel.listSim.get(indexSimSaatBeli).uang += furnitur.getHarga();
                        gamePanel.ui.subState = 0;
                        gamePanel.ui.charIndex = 0;
                        gamePanel.ui.combinedText = "";
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.currentDialog = "Inventory penuh";
                    }
                } else if (gamePanel.npc[0][4].inventory.get(itemBuyTempIndex) instanceof Lampu) {
                    Lampu lampu = (Lampu) gamePanel.npc[0][4].inventory.get(itemBuyTempIndex);
                    if (gamePanel.listSim.get(indexSimSaatBeli).canObtainItem(lampu)) {
                        // gamePanel.ui.charIndex = 0;
                        // gamePanel.ui.combinedText = "";
                        // gamePanel.gameState = gamePanel.dialogState;
                        // gamePanel.ui.currentDialog = "Pembelian barang oleh "
                        // + gamePanel.listSim.get(indexSimSaatBeli).nama + " berhasil";
                        isBarangSampai = true;
                        tempDialogBarang = "Pembelian barang oleh "
                                + gamePanel.listSim.get(indexSimSaatBeli).nama + "\nberhasil";
                    } else {
                        gamePanel.listSim.get(indexSimSaatBeli).uang += lampu.getHarga();
                        gamePanel.ui.subState = 0;
                        gamePanel.ui.charIndex = 0;
                        gamePanel.ui.combinedText = "";
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.currentDialog = "Inventory penuh";

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
        // gamePanel.ui.charIndex = 0;
        // gamePanel.ui.combinedText = "";
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.currentDialog = "Pembelian barang oleh "
        // + gamePanel.listSim.get(indexSimSaatBeli).nama + " berhasil";
        // } else {
        // gamePanel.ui.subState = 0;
        // gamePanel.ui.charIndex = 0;
        // gamePanel.ui.combinedText = "";
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.currentDialog = "Inventory penuh";
        // }
        // } else {
        // gamePanel.ui.subState = 0;
        // gamePanel.ui.charIndex = 0;
        // gamePanel.ui.combinedText = "";
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.currentDialog = "Uang tidak cukup";
        // // drawDialogScreen(); // ini ga perlu karena sudah ada di atas
        // }
        // } else if (gamePanel.npc[0][4].inventory.get(itemBuyTempIndex) instanceof
        // Furnitur) {
        // Furnitur furnitur = (Furnitur)
        // gamePanel.npc[0][4].inventory.get(itemBuyTempIndex);
        // if (gamePanel.listSim.get(indexSimSaatBeli).uang >= furnitur.harga) {
        // if (gamePanel.listSim.get(indexSimSaatBeli).canObtainItem(furnitur)) {
        // gamePanel.listSim.get(indexSimSaatBeli).uang -= furnitur.harga;
        // gamePanel.ui.charIndex = 0;
        // gamePanel.ui.combinedText = "";
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.currentDialog = "Pembelian barang oleh "
        // + gamePanel.listSim.get(indexSimSaatBeli).nama + " berhasil";
        // } else {
        // gamePanel.ui.subState = 0;
        // gamePanel.ui.charIndex = 0;
        // gamePanel.ui.combinedText = "";
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.currentDialog = "Inventory penuh";
        // }
        // } else {
        // gamePanel.ui.subState = 0;
        // gamePanel.ui.charIndex = 0;
        // gamePanel.ui.combinedText = "";
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.currentDialog = "Uang tidak cukup";
        // // drawDialogScreen(); // ini ga perlu karena sudah ada di atas
        // }
        // } else if (gamePanel.npc[0][4].inventory.get(itemBuyTempIndex) instanceof
        // Lampu) {
        // Lampu lampu = (Lampu) gamePanel.npc[0][4].inventory.get(itemBuyTempIndex);
        // if (gamePanel.listSim.get(indexSimSaatBeli).uang >= lampu.harga) {
        // if (gamePanel.listSim.get(indexSimSaatBeli).canObtainItem(lampu)) {
        // gamePanel.listSim.get(indexSimSaatBeli).uang -= lampu.harga;
        // gamePanel.ui.charIndex = 0;
        // gamePanel.ui.combinedText = "";
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.currentDialog = "Pembelian barang oleh "
        // + gamePanel.listSim.get(indexSimSaatBeli).nama + " berhasil";
        // } else {
        // if (gamePanel.listSim.get(indexSimSaatBeli).inventory
        // .size() >= gamePanel.listSim.get(indexSimSaatBeli).maxInventorySize) {
        // gamePanel.ui.subState = 0;
        // gamePanel.ui.charIndex = 0;
        // gamePanel.ui.combinedText = "";
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.currentDialog = "Inventory penuh";
        // } else {
        // gamePanel.ui.subState = 0;
        // gamePanel.ui.charIndex = 0;
        // gamePanel.ui.combinedText = "";
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.currentDialog = "Anda sudah memiliki lampu!";
        // }
        // }
        // } else {
        // gamePanel.ui.subState = 0;
        // gamePanel.ui.charIndex = 0;
        // gamePanel.ui.combinedText = "";
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.currentDialog = "Uang tidak cukup";
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
        // gamePanel.ui.charIndex = 0;
        // gamePanel.ui.combinedText = "";
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.currentDialog = "Pembelian barang oleh "
        // + gamePanel.listSim.get(indexSimSaatBeli).nama + " berhasil";
        // } else {
        // gamePanel.ui.subState = 0;
        // gamePanel.ui.charIndex = 0;
        // gamePanel.ui.combinedText = "";
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.currentDialog = "Inventory penuh";
        // }
        // } else {
        // gamePanel.ui.subState = 0;
        // gamePanel.ui.charIndex = 0;
        // gamePanel.ui.combinedText = "";
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.currentDialog = "Uang tidak cukup";
        // // drawDialogScreen(); // ini ga perlu karena sudah ada di atas
        // }
        // } else if (gamePanel.npc[0][4].inventory.get(itemBuyTempIndex) instanceof
        // Furnitur) {
        // Furnitur furnitur = (Furnitur)
        // gamePanel.npc[0][4].inventory.get(itemBuyTempIndex);
        // if (gamePanel.listSim.get(indexSimSaatBeli).uang >= furnitur.harga) {
        // if (gamePanel.listSim.get(indexSimSaatBeli).canObtainItem(furnitur)) {
        // gamePanel.listSim.get(indexSimSaatBeli).uang -= furnitur.harga;
        // gamePanel.ui.charIndex = 0;
        // gamePanel.ui.combinedText = "";
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.currentDialog = "Pembelian barang oleh "
        // + gamePanel.listSim.get(indexSimSaatBeli).nama + " berhasil";
        // } else {
        // gamePanel.ui.subState = 0;
        // gamePanel.ui.charIndex = 0;
        // gamePanel.ui.combinedText = "";
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.currentDialog = "Inventory penuh";
        // }
        // } else {
        // gamePanel.ui.subState = 0;
        // gamePanel.ui.charIndex = 0;
        // gamePanel.ui.combinedText = "";
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.currentDialog = "Uang tidak cukup";
        // // drawDialogScreen(); // ini ga perlu karena sudah ada di atas
        // }
        // } else if (gamePanel.npc[0][4].inventory.get(itemBuyTempIndex) instanceof
        // Lampu) {
        // Lampu lampu = (Lampu) gamePanel.npc[0][4].inventory.get(itemBuyTempIndex);
        // if (gamePanel.listSim.get(indexSimSaatBeli).uang >= lampu.harga) {
        // if (gamePanel.listSim.get(indexSimSaatBeli).canObtainItem(lampu)) {
        // gamePanel.listSim.get(indexSimSaatBeli).uang -= lampu.harga;
        // gamePanel.ui.charIndex = 0;
        // gamePanel.ui.combinedText = "";
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.currentDialog = "Pembelian barang oleh "
        // + gamePanel.listSim.get(indexSimSaatBeli).nama + " berhasil";
        // } else {
        // if (gamePanel.listSim.get(indexSimSaatBeli).inventory
        // .size() >= gamePanel.listSim.get(indexSimSaatBeli).maxInventorySize) {
        // gamePanel.ui.subState = 0;
        // gamePanel.ui.charIndex = 0;
        // gamePanel.ui.combinedText = "";
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.currentDialog = "Inventory penuh";
        // } else {
        // gamePanel.ui.subState = 0;
        // gamePanel.ui.charIndex = 0;
        // gamePanel.ui.combinedText = "";
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.currentDialog = "Anda sudah memiliki lampu!";
        // }
        // }
        // } else {
        // gamePanel.ui.subState = 0;
        // gamePanel.ui.charIndex = 0;
        // gamePanel.ui.combinedText = "";
        // gamePanel.gameState = gamePanel.dialogState;
        // gamePanel.ui.currentDialog = "Uang tidak cukup";
        // // drawDialogScreen(); nggk perlu ini karena sudah ada di atas
        // }
        // }

    }

}
