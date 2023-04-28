package benda;

import java.awt.Rectangle;

import main.GamePanel;

public class Furnitur_KasurQueenSize extends Furnitur {
    public Furnitur_KasurQueenSize(GamePanel gamePanel){
        name = "Kasur Queen Size";
        image = setupImage("benda/furnitur/kasur_queen_size");
        collision = false;
        dimensiX = 4;
        dimensiY = 2;
        harga = 100;
        aksi = "Tidur";
        solidArea = new Rectangle(0, 0, 48*dimensiX, 48*dimensiY);
        this.gamePanel = gamePanel;
    }

    @Override
    public void action(){
        // aksi tidur
        System.out.println("Aksi Tidur");

    }
}
