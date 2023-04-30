package benda;

import java.awt.Rectangle;

import main.GamePanel;

public class Furnitur_Toilet extends Furnitur{
    public Furnitur_Toilet(GamePanel gamePanel){
        name = "Toilet";
        image = setupImage("benda/furnitur/toilet");
        collision = false;
        dimensiX = 1;
        dimensiY = 1;
        harga = 50;
        aksi = "Buang air";
        solidArea = new Rectangle(0, 0, 48*dimensiX, 48*dimensiY);
        this.gamePanel = gamePanel;
    }

    @Override
    public void action(){
        // aksi buang air
        gamePanel.ui.currentAksi = "boker";
        gamePanel.gameState = gamePanel.timerState;
        gamePanel.keyHandler.threadTemp = gamePanel.ui.startTimerThread(10);
        gamePanel.worldTimeCounter += 10;
        gamePanel.getCurrentSim().kekenyangan -= 20;
        gamePanel.getCurrentSim().mood += 10;
        if (gamePanel.getCurrentSim().mood > gamePanel.getCurrentSim().maxMood) {
            gamePanel.getCurrentSim().mood = gamePanel.getCurrentSim().maxMood;
        }
        if (gamePanel.getCurrentSim().kesehatan > gamePanel.getCurrentSim().maxKesehatan) {
            gamePanel.getCurrentSim().kesehatan = gamePanel.getCurrentSim().maxKesehatan;
        }
        if (gamePanel.getCurrentSim().kekenyangan > gamePanel.getCurrentSim().maxKekenyangan) {
            gamePanel.getCurrentSim().kekenyangan = gamePanel.getCurrentSim().maxKekenyangan;
        }
    }
}
