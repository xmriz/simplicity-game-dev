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
        gamePanel.getUi().setCurrentAksi("Boker");
        gamePanel.setGameState(gamePanel.getTimerState());
        gamePanel.getUi().setCurrentAksiDone(false);
        // gamePanel.keyHandler.threadTemp = gamePanel.ui.startTimerThread(10);
        gamePanel.incWorldTimeCounter(10);
        gamePanel.incWorldTimeSatuHariCounter(10);
        gamePanel.getCurrentSim().setIsUdahMakanDalamSatuHari(false);

        for (int i = 0; i < gamePanel.getListSim().size(); i++) {
            gamePanel.getListSim().get(i).getPekerjaan().setWorldTimeCounterForStartJobAfterChangeJob(gamePanel.getListSim().get(i)
                .getPekerjaan().getWorldTimeCounterForStartJobAfterChangeJob() + 10);
            gamePanel.getListSim().get(i).setEfekWaktuTidakTidurCounter(gamePanel.getListSim().get(i).getEfekWaktuTidakTidurCounter()+10);
            if (gamePanel.getListSim().get(i).getIsUdahMakanDalamSatuHari()) {
                gamePanel.getListSim().get(i).setEfekWaktuTidakBuangAirCounter(gamePanel.getListSim().get(i).getEfekWaktuTidakBuangAirCounter()+10);
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
        gamePanel.getUi().setTempDurasi(10);
        gamePanel.getKeyHandler().setThreadTemp(gamePanel.getUi().startTimerThread(10));
        // gamePanel.ui.setelahAksi(10);
    }
}
