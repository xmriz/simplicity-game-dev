package benda;

import java.util.*;

import main.GamePanel;

public class Rumah extends Benda {
    public int colRumah;
    public int rowRumah;
    public int jumlahRuangan = 0;

    // darr yang gua tambah
    public boolean isCanUpgrade = true;
    public String posisiUpgrade = "";
    public int remainingTimeUpgrade = 0;
    public Ruangan ruanganUpgrade;
    public int indexLocationSaatUpgrade = 0;
    public int indexSimSaatUpgrade = 0;
    public boolean isLockUpgrade = true;
    // ----------------------------

    // public int indexRuangan = 0;
    public List<Ruangan> ruanganRumah = new ArrayList<>();

    public Rumah(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        name = "Rumah";
        image = setupImage("benda/rumah");
        // random posisi rumah
        Random rand = new Random();
        colRumah = rand.nextInt(64) + 1;
        rowRumah = rand.nextInt(64) + 1;

        // saat rumah dibuat Ruangan utama otomatis dibuat
        Ruangan ruanganUtama = new Ruangan(gamePanel);
        jumlahRuangan = 1;
        ruanganUtama.name = "Ruangan Utama";
        ruanganRumah.add(ruanganUtama);

        // // tambah ruangan ke dua
        // upgradeRumah("up", "Ruang Tamu");
    }

    public void upgradeRumah(String posisi, String nama) {
        Ruangan ruangan = new Ruangan(gamePanel);
        ruangan.name = nama;
        if (posisi == "up") {
            if (ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).up != null) {
                gamePanel.ui.charIndex = 0;
                gamePanel.ui.combinedText = "";
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.ui.currentDialog = "Ruangan di atas sudah ada!";
            } else {
                if (gamePanel.listSim.get(gamePanel.indexCurrentSim).uang < 1500) {
                    gamePanel.ui.charIndex = 0;
                    gamePanel.ui.combinedText = "";
                    gamePanel.gameState = gamePanel.dialogState;
                    gamePanel.ui.currentDialog = "Uang tidak cukup untuk melakukan\nupgrade rumah!";
                    return;
                } else {
                    if (isCanUpgrade) {
                        posisiUpgrade = posisi;
                        isCanUpgrade = false;
                        ruanganUpgrade = ruangan;
                        indexLocationSaatUpgrade = gamePanel.listSim
                                .get(gamePanel.indexCurrentSim).indexLocationRuangan;
                        indexSimSaatUpgrade = gamePanel.indexCurrentSim;
                        remainingTimeUpgrade = 18 * 60;
                        // ruanganRumah
                        // .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).up
                        // = ruangan;
                        // ruangan.down = ruanganRumah
                        // .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan);
                        // ruanganRumah.add(ruangan);
                        // gamePanel.ui.charIndex = 0;
                        // gamePanel.ui.combinedText = "";
                        // gamePanel.gameState = gamePanel.dialogState;
                        // gamePanel.ui.currentDialog = "Pembangunan ruangan telah\nselesai.";
                        // gamePanel.getCurrentSim().uang -= 1500;
                    } else {
                        gamePanel.ui.charIndex = 0;
                        gamePanel.ui.combinedText = "";
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.currentDialog = "Terdapat ruangan yang sedang\ndibangun!";
                    }
                }
            }
        } else if (posisi == "down") {
            if (gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan == 0) {
                gamePanel.ui.charIndex = 0;
                gamePanel.ui.combinedText = "";
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.ui.currentDialog = "Tidak bisa membuat ruangan di bawah\nruangan utama!";
                return;
            } else {
                if (ruanganRumah
                        .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).down != null) {
                    gamePanel.ui.charIndex = 0;
                    gamePanel.ui.combinedText = "";
                    gamePanel.gameState = gamePanel.dialogState;
                    gamePanel.ui.currentDialog = "Ruangan di bawah sudah ada!";
                } else {
                    if (gamePanel.listSim.get(gamePanel.indexCurrentSim).uang < 1500) {
                        gamePanel.ui.charIndex = 0;
                        gamePanel.ui.combinedText = "";
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.currentDialog = "Uang tidak cukup untuk melakukan\nupgrade rumah!";
                        return;
                    } else {
                        if (isCanUpgrade) {
                            posisiUpgrade = posisi;
                            isCanUpgrade = false;
                            indexLocationSaatUpgrade = gamePanel.listSim
                                    .get(gamePanel.indexCurrentSim).indexLocationRuangan;
                            indexSimSaatUpgrade = gamePanel.indexCurrentSim;
                            remainingTimeUpgrade = 18 * 60;
                            ruanganUpgrade = ruangan;
                            // ruanganRumah
                            // .get(gamePanel.listSim
                            // .get(gamePanel.indexCurrentSim).indexLocationRuangan).down = ruangan;
                            // ruangan.up = ruanganRumah
                            // .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan);
                            // ruanganRumah.add(ruangan);
                            // gamePanel.ui.charIndex = 0;
                            // gamePanel.ui.combinedText = "";
                            // gamePanel.gameState = gamePanel.dialogState;
                            // gamePanel.ui.currentDialog = "Pembangunan ruangan telah\nselesai.";
                            // gamePanel.getCurrentSim().uang -= 1500;
                        } else {
                            gamePanel.ui.charIndex = 0;
                            gamePanel.ui.combinedText = "";
                            gamePanel.gameState = gamePanel.dialogState;
                            gamePanel.ui.currentDialog = "Terdapat ruangan yang sedang\ndibangun!";
                        }
                    }
                }
            }

        } else if (posisi == "left") {
            if (ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).left != null) {
                gamePanel.ui.charIndex = 0;
                gamePanel.ui.combinedText = "";
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.ui.currentDialog = "Ruangan di kiri sudah ada!";
            } else {
                if (gamePanel.listSim.get(gamePanel.indexCurrentSim).uang < 1500) {
                    gamePanel.ui.charIndex = 0;
                    gamePanel.ui.combinedText = "";
                    gamePanel.gameState = gamePanel.dialogState;
                    gamePanel.ui.currentDialog = "Uang tidak cukup untuk melakukan\nupgrade rumah!";
                    return;
                } else {
                    if (isCanUpgrade) {
                        posisiUpgrade = posisi;
                        isCanUpgrade = false;
                        indexLocationSaatUpgrade = gamePanel.listSim
                                .get(gamePanel.indexCurrentSim).indexLocationRuangan;
                        indexSimSaatUpgrade = gamePanel.indexCurrentSim;
                        remainingTimeUpgrade = 18 * 60;
                        ruanganUpgrade = ruangan;
                        // ruanganRumah
                        // .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).left
                        // = ruangan;
                        // ruangan.right = ruanganRumah
                        // .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan);
                        // ruanganRumah.add(ruangan);
                        // gamePanel.ui.charIndex = 0;
                        // gamePanel.ui.combinedText = "";
                        // gamePanel.gameState = gamePanel.dialogState;
                        // gamePanel.ui.currentDialog = "Pembangunan ruangan telah\nselesai.";
                        // gamePanel.getCurrentSim().uang -= 1500;
                    } else {
                        gamePanel.ui.charIndex = 0;
                        gamePanel.ui.combinedText = "";
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.currentDialog = "Terdapat ruangan yang sedang\ndibangun!";
                    }
                }
            }
        } else if (posisi == "right") {
            if (ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).right != null) {
                gamePanel.ui.charIndex = 0;
                gamePanel.ui.combinedText = "";
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.ui.currentDialog = "Ruangan di kanan sudah ada!";
            } else {
                if (gamePanel.listSim.get(gamePanel.indexCurrentSim).uang < 1500) {
                    gamePanel.ui.charIndex = 0;
                    gamePanel.ui.combinedText = "";
                    gamePanel.gameState = gamePanel.dialogState;
                    gamePanel.ui.currentDialog = "Uang tidak cukup untuk melakukan\nupgrade rumah!";
                    return;
                } else {
                    if (isCanUpgrade) {
                        posisiUpgrade = posisi;
                        isCanUpgrade = false;
                        indexLocationSaatUpgrade = gamePanel.listSim
                                .get(gamePanel.indexCurrentSim).indexLocationRuangan;
                        indexSimSaatUpgrade = gamePanel.indexCurrentSim;
                        remainingTimeUpgrade = 18 * 60;
                        ruanganUpgrade = ruangan;
                        // ruanganRumah
                        // .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).right
                        // = ruangan;
                        // ruangan.left = ruanganRumah
                        // .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan);
                        // ruanganRumah.add(ruangan);
                        // gamePanel.ui.charIndex = 0;
                        // gamePanel.ui.combinedText = "";
                        // gamePanel.gameState = gamePanel.dialogState;
                        // gamePanel.ui.currentDialog = "Pembangunan ruangan telah\nselesai.";
                        // gamePanel.getCurrentSim().uang -= 1500;
                    } else {
                        gamePanel.ui.charIndex = 0;
                        gamePanel.ui.combinedText = "";
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.currentDialog = "Terdapat ruangan yang sedang\ndibangun!";
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
            System.out.println("Waktu tersisa: " + remainingTimeUpgrade + " detik");
            if (remainingTimeUpgrade < 0) {
                remainingTimeUpgrade = 0;
            }
            if (remainingTimeUpgrade == 0) {
                isCanUpgrade = true; // atur isCanUpgrade menjadi true setelah 18 menit
                ruanganUpgrade.index = jumlahRuangan;
                System.out.println(ruanganUpgrade.index);
                if (posisiUpgrade.equals("up")) {
                    System.out.println(0);
                    ruanganRumah
                            .get(gamePanel.listSim
                                    .get(indexSimSaatUpgrade).rumah.indexLocationSaatUpgrade).up = ruanganUpgrade;
                    ruanganUpgrade.down = ruanganRumah
                            .get(gamePanel.listSim.get(indexSimSaatUpgrade).rumah.indexLocationSaatUpgrade);
                    ruanganRumah.add(ruanganUpgrade);
                    // gamePanel.ui.charIndex = 0;
                    // gamePanel.ui.combinedText = "";
                    // gamePanel.gameState = gamePanel.dialogState;
                    // gamePanel.ui.currentDialog = "Pembangunan rumah " +
                    // gamePanel.listSim.get(indexSimSaatUpgrade).nama
                    // + " telah selesai.";
                    gamePanel.listSim.get(indexSimSaatUpgrade).uang -= 1500;
                } else if (posisiUpgrade.equals("down")) {
                    ruanganRumah
                            .get(gamePanel.listSim
                                    .get(indexSimSaatUpgrade).rumah.indexLocationSaatUpgrade).down = ruanganUpgrade;
                    ruanganUpgrade.up = ruanganRumah
                            .get(gamePanel.listSim.get(indexSimSaatUpgrade).rumah.indexLocationSaatUpgrade);
                    ruanganRumah.add(ruanganUpgrade);
                    // gamePanel.ui.charIndex = 0;
                    // gamePanel.ui.combinedText = "";
                    // gamePanel.gameState = gamePanel.dialogState;
                    // gamePanel.ui.currentDialog = "Pembangunan rumah " +
                    // gamePanel.listSim.get(indexSimSaatUpgrade).nama
                    // + " telah selesai.";
                    gamePanel.listSim.get(indexSimSaatUpgrade).uang -= 1500;
                } else if (posisiUpgrade.equals("left")) {
                    ruanganRumah
                            .get(gamePanel.listSim
                                    .get(indexSimSaatUpgrade).rumah.indexLocationSaatUpgrade).left = ruanganUpgrade;
                    ruanganUpgrade.right = ruanganRumah
                            .get(gamePanel.listSim.get(indexSimSaatUpgrade).rumah.indexLocationSaatUpgrade);
                    ruanganRumah.add(ruanganUpgrade);
                    // gamePanel.ui.charIndex = 0;
                    // gamePanel.ui.combinedText = "";
                    // gamePanel.gameState = gamePanel.dialogState;
                    // gamePanel.ui.currentDialog = "Pembangunan rumah " +
                    // gamePanel.listSim.get(indexSimSaatUpgrade).nama
                    // + " telah selesai.";
                    gamePanel.listSim.get(indexSimSaatUpgrade).uang -= 1500;
                } else if (posisiUpgrade.equals("right")) {
                    ruanganRumah
                            .get(gamePanel.listSim
                                    .get(indexSimSaatUpgrade).rumah.indexLocationSaatUpgrade).right = ruanganUpgrade;
                    ruanganUpgrade.left = ruanganRumah
                            .get(gamePanel.listSim.get(indexSimSaatUpgrade).rumah.indexLocationSaatUpgrade);
                    ruanganRumah.add(ruanganUpgrade);
                    // gamePanel.ui.charIndex = 0;
                    // gamePanel.ui.combinedText = "";
                    // gamePanel.gameState = gamePanel.dialogState;
                    // gamePanel.ui.currentDialog = "Pembangunan rumah " +
                    // gamePanel.listSim.get(indexSimSaatUpgrade).nama
                    // + " telah selesai.";
                    // gamePanel.listSim.get(indexSimSaatUpgrade).tempDialogUpgrade = "Pembangunan
                    // rumah "
                    // + gamePanel.listSim.get(indexSimSaatUpgrade).nama
                    // + " telah selesai.";
                    gamePanel.listSim.get(indexSimSaatUpgrade).uang -= 1500;
                }
                jumlahRuangan += 1;
                gamePanel.listSim.get(indexSimSaatUpgrade).tempDialogUpgrade = "Pembangunan rumah "
                        + gamePanel.listSim.get(indexSimSaatUpgrade).nama
                        + " telah selesai.";
                gamePanel.listSim.get(indexSimSaatUpgrade).isUpgradeDone = true;
                System.out.println(ruanganUpgrade.index);
                System.out.println(ruanganUpgrade.up);
                System.out.println(ruanganUpgrade.left);
                System.out.println(ruanganUpgrade.right);

            }
        }
    }
}
