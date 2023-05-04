package benda;

import java.awt.Rectangle;

import main.GamePanel;

public class Furnitur_TV extends Furnitur{
    public Furnitur_TV(GamePanel gamePanel){
        setName("TV");
        setImage(setupImage("benda/furnitur/tv"));
        setCollision(false);
        setDimensiX(2);
        setDimensiY(2);
        setHarga(100);
        setAksi("Nonton");
        setSolidArea(new Rectangle(0, 0, 48*getDimensiX(), 48*getDimensiY()));
        this.gamePanel = gamePanel;
    }

    @Override
    public void action(){
        // aksi nonton

        gamePanel.setGameState(gamePanel.getInputDurasiNontonState());
    }
}