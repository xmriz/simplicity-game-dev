package benda;

import java.awt.Rectangle;

import main.GamePanel;

public class Furnitur_MejaKursi extends Furnitur {
    public Furnitur_MejaKursi(GamePanel gamePanel) {
        setName("Meja Kursi");
        setImage(setupImage("benda/furnitur/meja_kursi"));
        setCollision(false);
        setDimensiX(3);
        setDimensiY(3);
        setHarga(50);
        setAksi("Makan");
        setSolidArea(new Rectangle(0, 0, 48 * getDimensiX(), 48 * getDimensiY()));
        this.gamePanel = gamePanel;
    }

    @Override
    public void action() {
        // aksi makan
        gamePanel.setGameState(gamePanel.getInventoryState());
    }
}