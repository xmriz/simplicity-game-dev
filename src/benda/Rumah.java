package benda;

import java.util.*;

import main.GamePanel;

public class Rumah extends Benda {
    private int colRumah;
    private int rowRumah;
    private int jumlahRuangan = 0;

    // darr yang gua tambah
    private boolean isCanUpgrade = true;
    private String posisiUpgrade = "";
    private int remainingTimeUpgrade = 0;
    private Ruangan ruanganUpgrade;
    private int indexLocationSaatUpgrade = 0;
    private int indexSimSaatUpgrade = 0;
    private boolean isLockUpgrade = true;


    // ----------------------------

    // public int indexRuangan = 0;
    private List<Ruangan> ruanganRumah = new ArrayList<>();

    public List<Ruangan> getRuanganRumah(){
        return ruanganRumah;
    }

    public void setRuanganRumah(List<Ruangan> ruanganRumah){
        this.ruanganRumah=ruanganRumah;
    }

    public int getColRumah(){
        return colRumah;
    }

    public void setColRumah(int colRumah){
        this.colRumah=colRumah;
    }

    public int getRowRumah(){
        return rowRumah;
    }

    public void setRowRumah(int rowRumah){
        this.rowRumah=rowRumah;
    }
    public int getJumlahRuangan(){
        return jumlahRuangan;
    }

    public void setJumlahRuangan(int jumlahRuangan){
        this.jumlahRuangan=jumlahRuangan;
    }

    public boolean getIsCanUpgrade(){
        return isCanUpgrade;
    }

    public void setIsCanUpgrade(boolean isCanUpgrade){
        this.isCanUpgrade=isCanUpgrade;
    }

    public String getPosisiUpgrade(){
        return posisiUpgrade;
    }

    public void setPosisiUpgrade(String posisiUpgrade){
        this.posisiUpgrade=posisiUpgrade;
    }

    public int getRemainingTimeUpgrade(){
        return remainingTimeUpgrade;
    }

    public void setRemainingTimeUpgrade(int remainingTimeUpgrade){
        this.remainingTimeUpgrade=remainingTimeUpgrade;
    }

    public void decRemainingTimeUpgrade(int remainingTimeUpgrade){
        this.remainingTimeUpgrade-=remainingTimeUpgrade;
    }

    public Ruangan getRuanganUpgrade(){
        return ruanganUpgrade;
    }

    public void setRuanganUpgrade(Ruangan ruanganUpgrade){
        this.ruanganUpgrade=ruanganUpgrade;
    }

    public int getIndexLocationSaatUpgrade(){
        return indexLocationSaatUpgrade;
    }

    public void setIndexLocationSaatUpgrade(int indexLocationSaatUpgrade){
        this.indexLocationSaatUpgrade=indexLocationSaatUpgrade;
    }

    public int getIndexSimSaatUpgrade(){
        return indexSimSaatUpgrade;
    }

    public void setIndexSimSaatUpgrade(int indexSimSaatUpgrade){
        this.indexSimSaatUpgrade=indexSimSaatUpgrade;
    }

    public boolean getIsLockUpgrade(){
        return isLockUpgrade;
    }

    public void setIsLockUpgrade(boolean isLockUpgrade){
        this.isLockUpgrade=isLockUpgrade;
    }


    public Rumah(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        setName("Rumah");
        setImage(setupImage("benda/rumah"));
        // random posisi rumah
        Random rand = new Random();
        colRumah = rand.nextInt(64) + 1;
        rowRumah = rand.nextInt(64) + 1;

        // saat rumah dibuat Ruangan utama otomatis dibuat
        Ruangan ruanganUtama = new Ruangan(gamePanel);
        jumlahRuangan = 1;
        ruanganUtama.setName("Ruangan Utama");
        ruanganRumah.add(ruanganUtama);

        // // tambah ruangan ke dua
        // upgradeRumah("up", "Ruang Tamu");
    }

    public void upgradeRumah(String posisi, String nama) {
        Ruangan ruangan = new Ruangan(gamePanel);
        ruangan.setName(nama);
        if (posisi == "up") {
            if (ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).getIndexLocationRuangan()).getUp() != null) {
                gamePanel.ui.setCharIndex(0);
                gamePanel.ui.setCombinedText("");
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.ui.setCurrentDialog("Ruangan di atas sudah ada!");
            } else {
                if (gamePanel.listSim.get(gamePanel.indexCurrentSim).getUang() < 1500) {
                    gamePanel.ui.setCharIndex(0);
                    gamePanel.ui.setCombinedText("");
                    gamePanel.gameState = gamePanel.dialogState;
                    gamePanel.ui.setCurrentDialog("Uang tidak cukup untuk melakukan\nupgrade rumah!");
                    return;
                } else {
                    if (isCanUpgrade) {
                        posisiUpgrade = posisi;
                        isCanUpgrade = false;
                        ruanganUpgrade = ruangan;
                        indexLocationSaatUpgrade = gamePanel.listSim
                                .get(gamePanel.indexCurrentSim).getIndexLocationRuangan();
                        indexSimSaatUpgrade = gamePanel.indexCurrentSim;
                        remainingTimeUpgrade = 18 * 60;
                        // ruanganRumah
                        // .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).up
                        // = ruangan;
                        // ruangan.down = ruanganRumah
                        // .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan);
                        // ruanganRumah.add(ruangan);
                        // gamePanel.ui.setCharIndex(0);
                        // gamePanel.ui.setCombinedText("");
                        // gamePanel.gameState = gamePanel.dialogState;
                        // gamePanel.ui.setCurrentDialog"Pembangunan ruangan telah\nselesai.";
                        // gamePanel.getCurrentSim().uang -= 1500;
                    } else {
                        gamePanel.ui.setCharIndex(0);
                        gamePanel.ui.setCombinedText("");
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.setCurrentDialog("Terdapat ruangan yang sedang\ndibangun!");
                    }
                }
            }
        } else if (posisi == "down") {
            if (gamePanel.listSim.get(gamePanel.indexCurrentSim).getIndexLocationRuangan() == 0) {
                gamePanel.ui.setCharIndex(0);
                gamePanel.ui.setCombinedText("");
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.ui.setCurrentDialog("Tidak bisa membuat ruangan di bawah\nruangan utama!");
                return;
            } else {
                if (ruanganRumah
                        .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).getIndexLocationRuangan()).getDown() != null) {
                    gamePanel.ui.setCharIndex(0);
                    gamePanel.ui.setCombinedText("");
                    gamePanel.gameState = gamePanel.dialogState;
                    gamePanel.ui.setCurrentDialog("Ruangan di bawah sudah ada!");
                } else {
                    if (gamePanel.listSim.get(gamePanel.indexCurrentSim).getUang() < 1500) {
                        gamePanel.ui.setCharIndex(0);
                        gamePanel.ui.setCombinedText("");
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.setCurrentDialog("Uang tidak cukup untuk melakukan\nupgrade rumah!");
                        return;
                    } else {
                        if (isCanUpgrade) {
                            posisiUpgrade = posisi;
                            isCanUpgrade = false;
                            indexLocationSaatUpgrade = gamePanel.listSim
                                    .get(gamePanel.indexCurrentSim).getIndexLocationRuangan();
                            indexSimSaatUpgrade = gamePanel.indexCurrentSim;
                            remainingTimeUpgrade = 18 * 60;
                            ruanganUpgrade = ruangan;
                            // ruanganRumah
                            // .get(gamePanel.listSim
                            // .get(gamePanel.indexCurrentSim).indexLocationRuangan).down = ruangan;
                            // ruangan.up = ruanganRumah
                            // .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan);
                            // ruanganRumah.add(ruangan);
                            // gamePanel.ui.setCharIndex(0);
                            // gamePanel.ui.setCombinedText("");
                            // gamePanel.gameState = gamePanel.dialogState;
                            // gamePanel.ui.setCurrentDialog"Pembangunan ruangan telah\nselesai.";
                            // gamePanel.getCurrentSim().uang -= 1500;
                        } else {
                            gamePanel.ui.setCharIndex(0);
                            gamePanel.ui.setCombinedText("");
                            gamePanel.gameState = gamePanel.dialogState;
                            gamePanel.ui.setCurrentDialog("Terdapat ruangan yang sedang\ndibangun!");
                        }
                    }
                }
            }

        } else if (posisi == "left") {
            if (ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).getIndexLocationRuangan()).getLeft() != null) {
                gamePanel.ui.setCharIndex(0);
                gamePanel.ui.setCombinedText("");
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.ui.setCurrentDialog("Ruangan di kiri sudah ada!");
            } else {
                if (gamePanel.listSim.get(gamePanel.indexCurrentSim).getUang() < 1500) {
                    gamePanel.ui.setCharIndex(0);
                    gamePanel.ui.setCombinedText("");
                    gamePanel.gameState = gamePanel.dialogState;
                    gamePanel.ui.setCurrentDialog("Uang tidak cukup untuk melakukan\nupgrade rumah!");
                    return;
                } else {
                    if (isCanUpgrade) {
                        posisiUpgrade = posisi;
                        isCanUpgrade = false;
                        indexLocationSaatUpgrade = gamePanel.listSim
                                .get(gamePanel.indexCurrentSim).getIndexLocationRuangan();
                        indexSimSaatUpgrade = gamePanel.indexCurrentSim;
                        remainingTimeUpgrade = 18 * 60;
                        ruanganUpgrade = ruangan;
                        // ruanganRumah
                        // .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).left
                        // = ruangan;
                        // ruangan.right = ruanganRumah
                        // .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan);
                        // ruanganRumah.add(ruangan);
                        // gamePanel.ui.setCharIndex(0);
                        // gamePanel.ui.setCombinedText("");
                        // gamePanel.gameState = gamePanel.dialogState;
                        // gamePanel.ui.setCurrentDialog"Pembangunan ruangan telah\nselesai.";
                        // gamePanel.getCurrentSim().uang -= 1500;
                    } else {
                        gamePanel.ui.setCharIndex(0);
                        gamePanel.ui.setCombinedText("");
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.setCurrentDialog("Terdapat ruangan yang sedang\ndibangun!");
                    }
                }
            }
        } else if (posisi == "right") {
            if (ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).getIndexLocationRuangan()).getRight() != null) {
                gamePanel.ui.setCharIndex(0);
                gamePanel.ui.setCombinedText("");
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.ui.setCurrentDialog("Ruangan di kanan sudah ada!");
            } else {
                if (gamePanel.listSim.get(gamePanel.indexCurrentSim).getUang() < 1500) {
                    gamePanel.ui.setCharIndex(0);
                    gamePanel.ui.setCombinedText("");
                    gamePanel.gameState = gamePanel.dialogState;
                    gamePanel.ui.setCurrentDialog("Uang tidak cukup untuk melakukan\nupgrade rumah!");
                    return;
                } else {
                    if (isCanUpgrade) {
                        posisiUpgrade = posisi;
                        isCanUpgrade = false;
                        indexLocationSaatUpgrade = gamePanel.listSim
                                .get(gamePanel.indexCurrentSim).getIndexLocationRuangan();
                        indexSimSaatUpgrade = gamePanel.indexCurrentSim;
                        remainingTimeUpgrade = 18 * 60;
                        ruanganUpgrade = ruangan;
                        // ruanganRumah
                        // .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).right
                        // = ruangan;
                        // ruangan.left = ruanganRumah
                        // .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan);
                        // ruanganRumah.add(ruangan);
                        // gamePanel.ui.setCharIndex(0);
                        // gamePanel.ui.setCombinedText("");
                        // gamePanel.gameState = gamePanel.dialogState;
                        // gamePanel.ui.setCurrentDialog"Pembangunan ruangan telah\nselesai.";
                        // gamePanel.getCurrentSim().uang -= 1500;
                    } else {
                        gamePanel.ui.setCharIndex(0);
                        gamePanel.ui.setCombinedText("");
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.setCurrentDialog("Terdapat ruangan yang sedang\ndibangun!");
                    }
                }
            }
        }
    }

    public void setIsCanUpgradeToTrueAfter18Minutes() {
        // if (remainingTimeUpgrade == 0) {
        // remainingTimeUpgrade = 18 * 60; // waktu mundur dalam detik
        // }
        if (isLockUpgrade == false) {
            if (remainingTimeUpgrade < 0) {
                remainingTimeUpgrade = 0;
            }
            if (remainingTimeUpgrade == 0) {
                isCanUpgrade = true; // atur isCanUpgrade menjadi true setelah 18 menit
                ruanganUpgrade.setIndex(jumlahRuangan);
                if (posisiUpgrade.equals("up")) {
                    ruanganRumah
                            .get(gamePanel.listSim
                                    .get(indexSimSaatUpgrade).getRumah().indexLocationSaatUpgrade).setUp(ruanganUpgrade);
                    ruanganUpgrade.setDown(ruanganRumah
                            .get(gamePanel.listSim.get(indexSimSaatUpgrade).getRumah().indexLocationSaatUpgrade));
                    ruanganRumah.add(ruanganUpgrade);
                    // gamePanel.ui.setCharIndex(0);
                    // gamePanel.ui.setCombinedText("");
                    // gamePanel.gameState = gamePanel.dialogState;
                    // gamePanel.ui.setCurrentDialog"Pembangunan rumah " +
                    // gamePanel.listSim.get(indexSimSaatUpgrade).nama
                    // + " telah selesai.";
                    gamePanel.listSim.get(indexSimSaatUpgrade).setUang(gamePanel.listSim.get(indexSimSaatUpgrade).getUang()-1500);
                } else if (posisiUpgrade.equals("down")) {
                    ruanganRumah
                            .get(gamePanel.listSim
                                    .get(indexSimSaatUpgrade).getRumah().indexLocationSaatUpgrade).setDown(ruanganUpgrade);
                    ruanganUpgrade.setUp(ruanganRumah
                            .get(gamePanel.listSim.get(indexSimSaatUpgrade).getRumah().indexLocationSaatUpgrade));
                    ruanganRumah.add(ruanganUpgrade);
                    // gamePanel.ui.setCharIndex(0);
                    // gamePanel.ui.setCombinedText("");
                    // gamePanel.gameState = gamePanel.dialogState;
                    // gamePanel.ui.setCurrentDialog"Pembangunan rumah " +
                    // gamePanel.listSim.get(indexSimSaatUpgrade).nama
                    // + " telah selesai.";
                    gamePanel.listSim.get(indexSimSaatUpgrade).setUang(gamePanel.listSim.get(indexSimSaatUpgrade).getUang()-1500);
                } else if (posisiUpgrade.equals("left")) {
                    ruanganRumah
                            .get(gamePanel.listSim
                                    .get(indexSimSaatUpgrade).getRumah().indexLocationSaatUpgrade).setLeft(ruanganUpgrade);
                    ruanganUpgrade.setRight(ruanganRumah
                            .get(gamePanel.listSim.get(indexSimSaatUpgrade).getRumah().indexLocationSaatUpgrade));
                    ruanganRumah.add(ruanganUpgrade);
                    // gamePanel.ui.setCharIndex(0);
                    // gamePanel.ui.setCombinedText("");
                    // gamePanel.gameState = gamePanel.dialogState;
                    // gamePanel.ui.setCurrentDialog"Pembangunan rumah " +
                    // gamePanel.listSim.get(indexSimSaatUpgrade).nama
                    // + " telah selesai.";
                    gamePanel.listSim.get(indexSimSaatUpgrade).setUang(gamePanel.listSim.get(indexSimSaatUpgrade).getUang()-1500);
                } else if (posisiUpgrade.equals("right")) {
                    ruanganRumah
                            .get(gamePanel.listSim
                                    .get(indexSimSaatUpgrade).getRumah().indexLocationSaatUpgrade).setRight(ruanganUpgrade);
                    ruanganUpgrade.setLeft(ruanganRumah
                            .get(gamePanel.listSim.get(indexSimSaatUpgrade).getRumah().indexLocationSaatUpgrade));
                    ruanganRumah.add(ruanganUpgrade);
                    // gamePanel.ui.setCharIndex(0);
                    // gamePanel.ui.setCombinedText("");
                    // gamePanel.gameState = gamePanel.dialogState;
                    // gamePanel.ui.setCurrentDialog"Pembangunan rumah " +
                    // gamePanel.listSim.get(indexSimSaatUpgrade).nama
                    // + " telah selesai.";
                    // gamePanel.listSim.get(indexSimSaatUpgrade).tempDialogUpgrade = "Pembangunan
                    // rumah "
                    // + gamePanel.listSim.get(indexSimSaatUpgrade).nama
                    // + " telah selesai.";
                    gamePanel.listSim.get(indexSimSaatUpgrade).setUang(gamePanel.listSim.get(indexSimSaatUpgrade).getUang()-1500);
                }
                jumlahRuangan += 1;
                gamePanel.listSim.get(indexSimSaatUpgrade).setTempDialogUpgrade("Pembangunan rumah " + gamePanel.listSim.get(indexSimSaatUpgrade).getNama() + " telah selesai.");
                gamePanel.listSim.get(indexSimSaatUpgrade).setIsUpgradeDone(true);
            }
        }
    }
}
