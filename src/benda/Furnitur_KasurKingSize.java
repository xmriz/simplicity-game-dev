package benda;

import java.awt.Rectangle;

import main.GamePanel;

public class Furnitur_KasurKingSize extends Furnitur{
    public Furnitur_KasurKingSize(GamePanel gamePanel){
       setName("Kasur King Size");
    setImage(setupImage("benda/furnitur/kasur_king_size"));
        setCollision(false);
        setDimensiX(5);
        setDimensiY(2);
        setHarga(150);
        setAksi("Tidur");
        setSolidArea(new Rectangle(0, 0, 48*getDimensiX(), 48*getDimensiY()));
        this.gamePanel = gamePanel;
    }

    @Override
    public void action(){
        // aksi tidur
        gamePanel.setGameState(gamePanel.getInputDurasiTidurState());
    }
}
