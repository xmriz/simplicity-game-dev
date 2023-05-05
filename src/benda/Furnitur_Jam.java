package benda;

import java.awt.Rectangle;

import main.GamePanel;

public class Furnitur_Jam extends Furnitur{
    public Furnitur_Jam(GamePanel gamePanel){
        setName("Jam");
        setImage(setupImage("benda/furnitur/jam"));
        setCollision(false);
        setDimensiX(1);
        setDimensiY(1);
        setHarga(10);
        setAksi("Melihat waktu");
        setSolidArea(new Rectangle(0, 0, 48*getDimensiX(), 48*getDimensiY()));
        this.gamePanel = gamePanel;
    }

    @Override
    public void action(){
        // aksi masak
        // gamePanel.gameState = gamePanel.melihatWaktuState;
        gamePanel.setGameState(gamePanel.getMelihatWaktuState());
    }
}
