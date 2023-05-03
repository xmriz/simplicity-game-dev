package benda;

import java.awt.Rectangle;

import main.GamePanel;

public class Furnitur_KasurQueenSize extends Furnitur {
    public Furnitur_KasurQueenSize(GamePanel gamePanel){
        setName("Kasur Queen Size");
        setImage(setupImage("benda/furnitur/kasur_queen_size"));
        setCollision(false);
        setDimensiX(4);
        setDimensiY(2);
        setHarga(100);
        setAksi("Tidur");
        setSolidArea(new Rectangle(0, 0, 48*getDimensiX(), 48*getDimensiY()));
        this.gamePanel = gamePanel;
    }

    @Override
    public void action(){
        // aksi tidur
        gamePanel.gameState = gamePanel.inputDurasiTidurState;

    }
}
