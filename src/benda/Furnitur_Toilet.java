package benda;

import java.awt.Rectangle;

import main.GamePanel;

public class Furnitur_Toilet extends Furnitur {
    public Furnitur_Toilet(GamePanel gamePanel) {
        setName("Toilet");
        setImage(setupImage("benda/furnitur/toilet"));
        setCollision(false);
        setDimensiX(1);
        setDimensiY(1);
        setHarga(50);
        setAksi("Boker");
        setSolidArea(new Rectangle(0, 0, 48*getDimensiX(), 48*getDimensiY()));
        this.gamePanel = gamePanel;
    }

    @Override
    public void action() {
        // aksi boker
        gamePanel.ui.currentAksi = "Boker";
        gamePanel.gameState = gamePanel.timerState;
        gamePanel.ui.currentAksiDone = false;
        // gamePanel.keyHandler.threadTemp = gamePanel.ui.startTimerThread(10);
        gamePanel.worldTimeCounter += 10;
        gamePanel.worldTimeSatuHariCounter += 10;
        gamePanel.getCurrentSim().isUdahMakanDalamSatuHari = false;

        for (int i = 0; i < gamePanel.listSim.size(); i++) {
            gamePanel.listSim.get(i).pekerjaan.worldTimeCounterForStartJobAfterChangeJob += 10;
            gamePanel.listSim.get(i).efekWaktuTidakTidurCounter += 10;
            if (gamePanel.listSim.get(i).isUdahMakanDalamSatuHari) {
                gamePanel.listSim.get(i).efekWaktuTidakBuangAirCounter += 10;
            }
        }
        gamePanel.getCurrentSim().kekenyangan -= 20;
        gamePanel.getCurrentSim().mood += 10;
        gamePanel.getCurrentSim().efekWaktuTidakBuangAirCounter = 0;
        if (gamePanel.getCurrentSim().mood > gamePanel.getCurrentSim().maxMood) {
            gamePanel.getCurrentSim().mood = gamePanel.getCurrentSim().maxMood;
        }
        if (gamePanel.getCurrentSim().kesehatan > gamePanel.getCurrentSim().maxKesehatan) {
            gamePanel.getCurrentSim().kesehatan = gamePanel.getCurrentSim().maxKesehatan;
        }
        if (gamePanel.getCurrentSim().kekenyangan > gamePanel.getCurrentSim().maxKekenyangan) {
            gamePanel.getCurrentSim().kekenyangan = gamePanel.getCurrentSim().maxKekenyangan;
        }
        gamePanel.ui.tempDurasi = 10;
        gamePanel.keyHandler.threadTemp = gamePanel.ui.startTimerThread(10);
        // gamePanel.ui.setelahAksi(10);
    }
}
