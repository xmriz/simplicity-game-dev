package benda;

import java.util.*;

import main.GamePanel;

public class Rumah extends Benda {
    public int colRumah;
    public int rowRumah;
    // public int indexRuangan = 0;
    public List<Ruangan> ruanganRumah = new ArrayList<>();

    public Rumah(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        name = "Rumah";
        image = setupImage("benda/rumah");
        colRumah = 10;
        rowRumah = 10;

        // saat rumah dibuat Ruangan utama otomatis dibuat
        Ruangan ruanganUtama = new Ruangan(gamePanel);
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
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.ui.currentDialog = "Ruangan di atas sudah ada!";
            } else {
                ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).up = ruangan;
                ruangan.down = ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan);
                ruanganRumah.add(ruangan);
            }
        } else if (posisi == "down") {
            if (gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan == 0) {
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.ui.currentDialog = "Tidak bisa membuat ruangan di bawah\nruangan utama!";
                return;
            } else {
                if (ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).down != null) {
                    gamePanel.gameState = gamePanel.dialogState;
                    gamePanel.ui.currentDialog = "Ruangan di bawah sudah ada!";
                } else {
                    ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).down = ruangan;
                    ruangan.up = ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan);
                    ruanganRumah.add(ruangan);
                }
            }

        } else if (posisi == "left") {
            if (ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).left != null) {
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.ui.currentDialog = "Ruangan di kiri sudah ada!";
            } else {
                ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).left = ruangan;
                ruangan.right = ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan);
                ruanganRumah.add(ruangan);
            }
        } else if (posisi == "right") {
            if (ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).right != null) {
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.ui.currentDialog = "Ruangan di kanan sudah ada!";
            } else {
                ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).right = ruangan;
                ruangan.left = ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan);
                ruanganRumah.add(ruangan);
            }
        }
    }
}
