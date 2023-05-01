package benda;

import java.util.*;

import main.GamePanel;

public class Rumah extends Benda {
    public int colRumah;
    public int rowRumah;
    public int jumlahRuangan;

    // darr yang gua tambah
    public boolean isCanUpgrade = true;
    public String posisiUpgrade = "";
    public int remainingTimeUpgrade = 0;
    public Ruangan ruanganUpgrade;
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
                        setIsCanUpgradeToTrueAfter18Minutes();
                        // ruanganRumah
                        //         .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).up = ruangan;
                        // ruangan.down = ruanganRumah
                        //         .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan);
                        // ruanganRumah.add(ruangan);
                        // gamePanel.ui.charIndex = 0;
                        // gamePanel.ui.combinedText = "";
                        // gamePanel.gameState = gamePanel.dialogState;
                        // gamePanel.ui.currentDialog = "Pembangunan ruangan telah\nselesai.";
                        // gamePanel.getCurrentSim().uang -= 1500;
                    } else{
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
                            setIsCanUpgradeToTrueAfter18Minutes();
                            ruanganUpgrade = ruangan;
                            // ruanganRumah
                            //         .get(gamePanel.listSim
                            //                 .get(gamePanel.indexCurrentSim).indexLocationRuangan).down = ruangan;
                            // ruangan.up = ruanganRumah
                            //         .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan);
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
                        setIsCanUpgradeToTrueAfter18Minutes();
                        ruanganUpgrade = ruangan;
                        // ruanganRumah
                        //         .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).left = ruangan;
                        // ruangan.right = ruanganRumah
                        //         .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan);
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
                        setIsCanUpgradeToTrueAfter18Minutes();
                        ruanganUpgrade = ruangan;
                        // ruanganRumah
                        //         .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).right = ruangan;
                        // ruangan.left = ruanganRumah
                        //         .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan);
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
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                    remainingTimeUpgrade = 18 * 60 * 1000; // waktu mundur dalam detik
                    while (remainingTimeUpgrade > 0) {
                        System.out.println("Waktu tersisa: " + remainingTimeUpgrade + " detik");
                        try {
                            Thread.sleep(1000); // tunggu 1 detik
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            isCanUpgrade = true; // atur isCanUpgrade menjadi true setelah 18 menit
                            remainingTimeUpgrade = 0;
                            System.out.println("Waktu diskip!");
                            if (posisiUpgrade == "up"){
                                ruanganRumah
                                        .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).up = ruanganUpgrade;
                                ruanganUpgrade.down = ruanganRumah
                                        .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan);
                                ruanganRumah.add(ruanganUpgrade);
                                gamePanel.ui.charIndex = 0;
                                gamePanel.ui.combinedText = "";
                                gamePanel.gameState = gamePanel.dialogState;
                                gamePanel.ui.currentDialog = "Pembangunan ruangan telah\nselesai.";
                                gamePanel.getCurrentSim().uang -= 1500;
                            } else if (posisiUpgrade == "down"){
                                ruanganRumah
                                        .get(gamePanel.listSim
                                                .get(gamePanel.indexCurrentSim).indexLocationRuangan).down = ruanganUpgrade;
                                ruanganUpgrade.up = ruanganRumah
                                        .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan);
                                ruanganRumah.add(ruanganUpgrade);
                                gamePanel.ui.charIndex = 0;
                                gamePanel.ui.combinedText = "";
                                gamePanel.gameState = gamePanel.dialogState;
                                gamePanel.ui.currentDialog = "Pembangunan ruangan telah\nselesai.";
                                gamePanel.getCurrentSim().uang -= 1500;
                            } else if (posisiUpgrade == "left"){
                                ruanganRumah
                                        .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).left = ruanganUpgrade;
                                ruanganUpgrade.right = ruanganRumah
                                        .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan);
                                ruanganRumah.add(ruanganUpgrade);
                                gamePanel.ui.charIndex = 0;
                                gamePanel.ui.combinedText = "";
                                gamePanel.gameState = gamePanel.dialogState;
                                gamePanel.ui.currentDialog = "Pembangunan ruangan telah\nselesai.";
                                gamePanel.getCurrentSim().uang -= 1500;
                            } else if (posisiUpgrade == "right"){
                                ruanganRumah
                                        .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).right = ruanganUpgrade;
                                ruanganUpgrade.left = ruanganRumah
                                        .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan);
                                ruanganRumah.add(ruanganUpgrade);
                                gamePanel.ui.charIndex = 0;
                                gamePanel.ui.combinedText = "";
                                gamePanel.gameState = gamePanel.dialogState;
                                gamePanel.ui.currentDialog = "Pembangunan ruangan telah\nselesai.";
                                gamePanel.getCurrentSim().uang -= 1500;
                            }
                        }
                        remainingTimeUpgrade--;
                    }
                    System.out.println("Waktu habis!");
                    isCanUpgrade = true; // atur isCanUpgrade menjadi true setelah 18 menit
                    if (posisiUpgrade == "up"){
                        ruanganRumah
                                .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).up = ruanganUpgrade;
                        ruanganUpgrade.down = ruanganRumah
                                .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan);
                        ruanganRumah.add(ruanganUpgrade);
                        gamePanel.ui.charIndex = 0;
                        gamePanel.ui.combinedText = "";
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.currentDialog = "Pembangunan ruangan telah\nselesai.";
                        gamePanel.getCurrentSim().uang -= 1500;
                    } else if (posisiUpgrade == "down"){
                        ruanganRumah
                                .get(gamePanel.listSim
                                        .get(gamePanel.indexCurrentSim).indexLocationRuangan).down = ruanganUpgrade;
                        ruanganUpgrade.up = ruanganRumah
                                .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan);
                        ruanganRumah.add(ruanganUpgrade);
                        gamePanel.ui.charIndex = 0;
                        gamePanel.ui.combinedText = "";
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.currentDialog = "Pembangunan ruangan telah\nselesai.";
                        gamePanel.getCurrentSim().uang -= 1500;
                    } else if (posisiUpgrade == "left"){
                        ruanganRumah
                                .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).left = ruanganUpgrade;
                        ruanganUpgrade.right = ruanganRumah
                                .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan);
                        ruanganRumah.add(ruanganUpgrade);
                        gamePanel.ui.charIndex = 0;
                        gamePanel.ui.combinedText = "";
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.currentDialog = "Pembangunan ruangan telah\nselesai.";
                        gamePanel.getCurrentSim().uang -= 1500;
                    } else if (posisiUpgrade == "right"){
                        ruanganRumah
                                .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).right = ruanganUpgrade;
                        ruanganUpgrade.left = ruanganRumah
                                .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan);
                        ruanganRumah.add(ruanganUpgrade);
                        gamePanel.ui.charIndex = 0;
                        gamePanel.ui.combinedText = "";
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.currentDialog = "Pembangunan ruangan telah\nselesai.";
                        gamePanel.getCurrentSim().uang -= 1500;
                    }
            }
        });
        t.start(); // mulai thread
    }
}
