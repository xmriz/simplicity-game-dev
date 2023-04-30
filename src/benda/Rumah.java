package benda;

import java.util.*;

import main.GamePanel;

public class Rumah extends Benda {
    public int colRumah;
    public int rowRumah;
    public int jumlahRuangan;
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
                if (gamePanel.listSim.get(gamePanel.indexCurrentSim).uang < 18000) {
                    gamePanel.ui.charIndex = 0;
                    gamePanel.ui.combinedText = "";
                    gamePanel.gameState = gamePanel.dialogState;
                    gamePanel.ui.currentDialog = "Uang tidak cukup untuk melakukan\nupgrade rumah!";
                    return;
                } else {
                    ruanganRumah
                            .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).up = ruangan;
                    ruangan.down = ruanganRumah
                            .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan);
                    ruanganRumah.add(ruangan);
                    gamePanel.ui.charIndex = 0;
                    gamePanel.ui.combinedText = "";
                    gamePanel.gameState = gamePanel.dialogState;
                    gamePanel.ui.currentDialog = "Berhasil menambah ruangan.";
                    gamePanel.getCurrentSim().uang -= 18000;
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
                    if (gamePanel.listSim.get(gamePanel.indexCurrentSim).uang < 18000) {
                        gamePanel.ui.charIndex = 0;
                        gamePanel.ui.combinedText = "";
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.currentDialog = "Uang tidak cukup untuk melakukan\nupgrade rumah!";
                        return;
                    } else {
                        ruanganRumah
                                .get(gamePanel.listSim
                                        .get(gamePanel.indexCurrentSim).indexLocationRuangan).down = ruangan;
                        ruangan.up = ruanganRumah
                                .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan);
                        ruanganRumah.add(ruangan);
                        gamePanel.ui.charIndex = 0;
                        gamePanel.ui.combinedText = "";
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.currentDialog = "Berhasil menambah ruangan.";
                        gamePanel.getCurrentSim().uang -= 18000;
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
                if (gamePanel.listSim.get(gamePanel.indexCurrentSim).uang < 18000) {
                    gamePanel.ui.charIndex = 0;
                    gamePanel.ui.combinedText = "";
                    gamePanel.gameState = gamePanel.dialogState;
                    gamePanel.ui.currentDialog = "Uang tidak cukup untuk melakukan\nupgrade rumah!";
                    return;
                } else {
                    ruanganRumah
                            .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).left = ruangan;
                    ruangan.right = ruanganRumah
                            .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan);
                    ruanganRumah.add(ruangan);
                    gamePanel.ui.charIndex = 0;
                    gamePanel.ui.combinedText = "";
                    gamePanel.gameState = gamePanel.dialogState;
                    gamePanel.ui.currentDialog = "Berhasil menambah ruangan.";
                    gamePanel.getCurrentSim().uang -= 18000;
                }
            }
        } else if (posisi == "right") {
            if (ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).right != null) {
                gamePanel.ui.charIndex = 0;
                gamePanel.ui.combinedText = "";
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.ui.currentDialog = "Ruangan di kanan sudah ada!";
            } else {
                if (gamePanel.listSim.get(gamePanel.indexCurrentSim).uang < 18000) {
                    gamePanel.ui.charIndex = 0;
                    gamePanel.ui.combinedText = "";
                    gamePanel.gameState = gamePanel.dialogState;
                    gamePanel.ui.currentDialog = "Uang tidak cukup untuk melakukan\nupgrade rumah!";
                    return;
                } else {
                    ruanganRumah
                            .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).right = ruangan;
                    ruangan.left = ruanganRumah
                            .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan);
                    ruanganRumah.add(ruangan);
                    gamePanel.ui.charIndex = 0;
                    gamePanel.ui.combinedText = "";
                    gamePanel.gameState = gamePanel.dialogState;
                    gamePanel.ui.currentDialog = "Berhasil menambah ruangan.";
                    gamePanel.getCurrentSim().uang -= 18000;
                }
            }
        }
    }
}
