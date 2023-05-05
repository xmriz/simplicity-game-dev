package entity;

import main.*;
import benda.*;

public class NPC_Koki extends Entity{
    public NPC_Koki(GamePanel gamePanel) {
        super(gamePanel);

        setDirection("down");

        getImage();
        setDialog();
        setItems();
    }

    public void getImage(){
        setUp1(setupImage("npc/koki"));
        setUp2(setupImage("npc/koki"));
        setDown1(setupImage("npc/koki"));
        setDown2(setupImage("npc/koki"));
        setLeft1(setupImage("npc/koki"));
        setLeft2(setupImage("npc/koki"));
        setRight1(setupImage("npc/koki"));
        setRight2(setupImage("npc/koki"));
    }

    public void setDialog(){
        getDialogs().add("Hahaha... Haloo, \nAnda ingin memakan apa?");
    }

    public void setItems(){
        // BARANG YANG BISA DIMASAK
        // MAKANAN
        getInventory().add(new Makanan_Bistik());
        getInventory().add(new Makanan_NasiAyam());
        getInventory().add(new Makanan_NasiKari());
        getInventory().add(new Makanan_SusuKacang());
        getInventory().add(new Makanan_TumisSayur());

    }

    public void speak(){
        super.speak();
        gamePanel.setGameState(gamePanel.getResepState());
    }
}
