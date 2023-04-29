package benda;

import java.awt.Rectangle;

import main.GamePanel;

public class Furnitur_Radio extends Furnitur {
    public Furnitur_Radio(GamePanel gamePanel) {
        name = "Radio";
        image = setupImage("benda/furnitur/jam");
        collision = false;
        dimensiX = 1;
        dimensiY = 1;
        harga = 50;
        aksi = "Menyetel Radio";
        solidArea = new Rectangle(0, 0, 48 * dimensiX, 48 * dimensiY);
        this.gamePanel = gamePanel;
    }

    @Override
    public void action() {
        // aksi tidur
        System.out.println("Setel Radio");
        gamePanel.gameState = gamePanel.inputDurasiRadioState;
    }
}