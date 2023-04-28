package entity;

import main.*;
import benda.*;

public class NPC_Koki extends Entity{
    public NPC_Koki(GamePanel gamePanel) {
        super(gamePanel);

        direction = "down";

        getImage();
        setDialog();
        setItems();
    }

    public void getImage(){
        up1 = setupImage("npc/koki");
        up2 = setupImage("npc/koki");
        down1 = setupImage("npc/koki");
        down2 = setupImage("npc/koki");
        left1 = setupImage("npc/koki");
        left2 = setupImage("npc/koki");
        right1 = setupImage("npc/koki");
        right2 = setupImage("npc/koki");
    }

    public void setDialog(){
        dialogs.add("Hahaha... Haloo, \nAnda ingin memakan apa?");
    }

    public void setItems(){
        // BARANG YANG BISA DIMASAK
        // MAKANAN
        inventory.add(new Makanan_Bistik());
        inventory.add(new Makanan_NasiAyam());
        inventory.add(new Makanan_NasiKari());
        inventory.add(new Makanan_SusuKacang());
        inventory.add(new Makanan_TumisSayur());

    }

    public void speak(){
        super.speak();
        gamePanel.gameState = gamePanel.resepState;
    }
}
