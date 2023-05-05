package benda;

import java.awt.Rectangle;

import main.GamePanel;

public class Furnitur_KomporGas extends Furnitur {
    public Furnitur_KomporGas(GamePanel gamePanel) {
        setName("Kompor Gas");
        setImage(setupImage("benda/furnitur/kompor_gas"));
        setCollision(false);
        setDimensiX(2);
        setDimensiY(1);
        setHarga(100);
        setAksi("Memasak");
        setSolidArea(new Rectangle(0, 0, 48 * getDimensiX(), 48 * getDimensiY()));
        this.gamePanel = gamePanel;
    }

    @Override
    public void action() {
        // aksi masak
        gamePanel.setGameState(gamePanel.getResepState());
    }
}