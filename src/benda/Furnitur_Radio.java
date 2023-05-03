package benda;

import java.awt.Rectangle;

import main.GamePanel;

public class Furnitur_Radio extends Furnitur {
    public Furnitur_Radio(GamePanel gamePanel) {
        setName("Radio");
       setImage(setupImage("benda/furnitur/radio"));
        setCollision(false);
        setDimensiX(1);
        setDimensiY(1);
        setHarga(50);
        setAksi("Menyetel Radio");
        setSolidArea(new Rectangle(0, 0, 48 * getDimensiX(), 48 * getDimensiY()));
        this.gamePanel = gamePanel;
    }

    @Override
    public void action() {
        // aksi tidur
        System.out.println("Setel Radio");
        gamePanel.gameState = gamePanel.inputDurasiRadioState;
    }
}