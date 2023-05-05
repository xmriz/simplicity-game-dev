package benda;

import java.awt.Rectangle;

import main.GamePanel;

public class Furnitur_RakBuku extends Furnitur {
    public Furnitur_RakBuku(GamePanel gamePanel) {
        setName("Rak Buku");
        setImage(setupImage("benda/furnitur/rak_buku"));
        setCollision(false);
        setDimensiX(2);
        setDimensiY(1);
        setHarga(50);
        setAksi("Baca Buku");
        setSolidArea(new Rectangle(0, 0, 48 * getDimensiX(), 48 * getDimensiY()));
        this.gamePanel = gamePanel;
    }

    @Override
    public void action() {
        // aksi membaca buku
        gamePanel.setGameState(gamePanel.getInputDurasiBacaBukuState());
    }
}