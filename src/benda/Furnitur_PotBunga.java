package benda;

import java.awt.Rectangle;

import main.GamePanel;

public class Furnitur_PotBunga extends Furnitur {
    public Furnitur_PotBunga(GamePanel gamePanel) {
        setName("Pot Bunga");
        setImage(setupImage("benda/furnitur/pot_bunga"));
        setCollision(false);
        setDimensiX(1);
        setDimensiY(1);
        setHarga(15);
        setAksi("Siram Tanaman");
        setSolidArea(new Rectangle(0, 0, 48 * getDimensiX(), 48 * getDimensiY()));
        this.gamePanel = gamePanel;
    }

    @Override
    public void action() {
        // aksi nyiram tanaman
        gamePanel.setGameState(gamePanel.getInputDurasiSiramTanamanState());
    }
}