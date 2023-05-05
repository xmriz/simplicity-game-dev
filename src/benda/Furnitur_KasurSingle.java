package benda;

import java.awt.Rectangle;

import main.GamePanel;

public class Furnitur_KasurSingle extends Furnitur {
    public Furnitur_KasurSingle(GamePanel gamePanel) {
        setName("Kasur Single");
        setImage(setupImage("benda/furnitur/kasur_single"));
        setCollision(false);
        setDimensiX(4);
        setDimensiY(1);
        setHarga(50);
        setAksi("Tidur");
        setSolidArea(new Rectangle(0, 0, 48 * getDimensiX(), 48 * getDimensiY()));
        this.gamePanel = gamePanel;
    }

    @Override
    public void action() {
        // aksi tidur
        gamePanel.setGameState(gamePanel.getInputDurasiTidurState());
    }
}
