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
        gamePanel.getCurrentSim().setIsUdahMakanDalamSatuHari(false);

        for (int i = 0; i < gamePanel.listSim.size(); i++) {
            gamePanel.listSim.get(i).getPekerjaan().setWorldTimeCounterForStartJobAfterChangeJob(gamePanel.listSim.get(i)
                .getPekerjaan().getWorldTimeCounterForStartJobAfterChangeJob() + 10);
            gamePanel.listSim.get(i).setEfekWaktuTidakTidurCounter(gamePanel.listSim.get(i).getEfekWaktuTidakTidurCounter()+10);
            if (gamePanel.listSim.get(i).getIsUdahMakanDalamSatuHari()) {
                gamePanel.listSim.get(i).setEfekWaktuTidakBuangAirCounter(gamePanel.listSim.get(i).getEfekWaktuTidakBuangAirCounter()+10);
            }
        }
        gamePanel.getCurrentSim().setKekenyangan(gamePanel.getCurrentSim().getKekenyangan()-20);
        gamePanel.getCurrentSim().setMood(gamePanel.getCurrentSim().getMood()+10);
        gamePanel.getCurrentSim().setEfekWaktuTidakBuangAirCounter(0);
        if (gamePanel.getCurrentSim().getMood() > gamePanel.getCurrentSim().getMaxMood()) {
            gamePanel.getCurrentSim().setMood(gamePanel.getCurrentSim().getMaxMood());
        }
        if (gamePanel.getCurrentSim().getKesehatan() > gamePanel.getCurrentSim().getMaxKesehatan()) {
            gamePanel.getCurrentSim().setKesehatan(gamePanel.getCurrentSim().getMaxKesehatan());
        }
        if (gamePanel.getCurrentSim().getKekenyangan() > gamePanel.getCurrentSim().getMaxKekenyangan()) {
            gamePanel.getCurrentSim().setKekenyangan(gamePanel.getCurrentSim().getMaxKekenyangan());
        }
        gamePanel.ui.tempDurasi = 10;
        gamePanel.keyHandler.threadTemp = gamePanel.ui.startTimerThread(10);
        // gamePanel.ui.setelahAksi(10);
    }
}
