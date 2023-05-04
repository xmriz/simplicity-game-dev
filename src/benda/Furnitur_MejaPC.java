package benda;

import java.awt.Rectangle;

import main.GamePanel;

public class Furnitur_MejaPC extends Furnitur{
    public Furnitur_MejaPC(GamePanel gamePanel){
        setName("Meja PC");
        setImage(setupImage("benda/furnitur/meja_pc"));
        setCollision(false);
        setDimensiX(3);
        setDimensiY(3);
        setHarga(75);
        setAksi("Main Game");
        setSolidArea(new Rectangle(0, 0, 48*getDimensiX(), 48*getDimensiY()));
        this.gamePanel = gamePanel;
    }

    @Override
    public void action(){
        // aksi tidur
        gamePanel.setGameState(gamePanel.getInputDurasiMainGameState());
    }
}
