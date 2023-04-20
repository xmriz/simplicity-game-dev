package entity;

import main.*;

public class NPC_Penjual extends Entity{
    GamePanel gamePanel;

    
    public NPC_Penjual(GamePanel gamePanel) {
        super(gamePanel);

        direction = "down";

        getImage();
        setItems();
        setDialog();
    }

    public void getImage(){
        up1 = setupImage("npc/penjual");
        up2 = setupImage("npc/penjual");
        down1 = setupImage("npc/penjual");
        down2 = setupImage("npc/penjual");
        left1 = setupImage("npc/penjual");
        left2 = setupImage("npc/penjual");
        right1 = setupImage("npc/penjual");
        right2 = setupImage("npc/penjual");
    }

    public void setDialog(){
        dialogs.add("Hahaha... Haloo, \nada yang bisa saya bantu?");
        dialogs.add("Saya menjual berbagai macam barang,\nbaik bahan makanan dan furnitur.");
        dialogs.add("Silahkan dipilih sesuai kebutuhan anda.");
    }

    public void setItems(){
    //    FUCK YOUU
    }

    public void speak(){
        super.speak();
        // gamePanel.gameState = gamePanel.beliState;
        // gamePanel.ui.npc = this;
    }
}
