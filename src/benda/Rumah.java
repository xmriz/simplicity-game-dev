package benda;

import java.util.*;

import main.GamePanel;

public class Rumah extends Benda{
    public int dimensiX;
    public int dimensiY;
    public int indexRuangan = 0;
    public List<Ruangan> ruanganRumah = new ArrayList<>();

    
    public Rumah(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        name = "Rumah";
        image = setupImage("benda/rumah");
        dimensiX = 10;
        dimensiY = 10;
        
        // saat rumah dibuat Ruangan utama otomatis dibuat
        Ruangan ruanganUtama = new Ruangan(gamePanel);
        ruanganUtama.name = "Ruangan Utama";
        ruanganRumah.add(ruanganUtama);

        // tambah ruangan ke dua
        upgradeRumah("up", "Ruang Tamu");
    }

    public void upgradeRumah(String posisi, String nama){
        Ruangan ruangan = new Ruangan(gamePanel);
        ruangan.name = nama;
        if (posisi == "up"){
            if (ruanganRumah.get(indexRuangan).up != null){
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.ui.currentDialog = "Ruangan di atas sudah ada";
            } else {
                ruanganRumah.get(indexRuangan).up = ruangan;
                ruangan.down = ruanganRumah.get(indexRuangan);
            }
        } else if (posisi == "down"){
            if (ruanganRumah.get(indexRuangan).down != null){
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.ui.currentDialog = "Ruangan di bawah sudah ada";
            } else {
                ruanganRumah.get(indexRuangan).down = ruangan;
                ruangan.up = ruanganRumah.get(indexRuangan);
            }
        } else if (posisi == "left"){
            if (ruanganRumah.get(indexRuangan).left != null){
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.ui.currentDialog = "Ruangan di kiri sudah ada";
            } else {
                ruanganRumah.get(indexRuangan).left = ruangan;
                ruangan.right = ruanganRumah.get(indexRuangan);
            }
        } else if (posisi == "right"){
            if (ruanganRumah.get(indexRuangan).right != null){
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.ui.currentDialog = "Ruangan di kanan sudah ada";
            } else {
                ruanganRumah.get(indexRuangan).right = ruangan;
                ruangan.left = ruanganRumah.get(indexRuangan);
            }
        }
        ruanganRumah.add(ruangan);
    }
}
