package benda;

import java.awt.Rectangle;

import main.GamePanel;

public class Furnitur_KomporGas extends Furnitur{
    public Furnitur_KomporGas(GamePanel gamePanel){
        name = "Kompor Gas";
        image = setupImage("benda/furnitur/kompor_gas");
        collision = false;
        dimensiX = 2;
        dimensiY = 1;
        harga = 100;
        aksi = "Memasak";
        solidArea = new Rectangle(0, 0, 48*dimensiX, 48*dimensiY);
        this.gamePanel = gamePanel;
    }

    @Override
    public void action(){
        // aksi masak
        gamePanel.gameState = gamePanel.resepState;
    }

}
