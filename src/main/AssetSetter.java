package main;

import benda.*;
import entity.NPC;

public class AssetSetter {

    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setBenda(){
        // position of the benda in the world
        int mapNumber = 0;
        gamePanel.benda[mapNumber][0] = new Rumah();
        gamePanel.benda[mapNumber][0].worldX = 2 * gamePanel.tileSize;
        gamePanel.benda[mapNumber][0].worldY = 2 * gamePanel.tileSize;
        
        gamePanel.benda[mapNumber][1] = new BahanMakanan_Kentang();
        gamePanel.benda[mapNumber][1].worldX = 3 * gamePanel.tileSize;
        gamePanel.benda[mapNumber][1].worldY = 3 * gamePanel.tileSize;

        gamePanel.benda[mapNumber][2] = new BahanMakanan_Sapi();
        gamePanel.benda[mapNumber][2].worldX = 4 * gamePanel.tileSize;
        gamePanel.benda[mapNumber][2].worldY = 4 * gamePanel.tileSize;
        
        gamePanel.benda[mapNumber][3] = new Furnitur_Jam();
        gamePanel.benda[mapNumber][3].worldX = 5 * gamePanel.tileSize;
        gamePanel.benda[mapNumber][3].worldY = 5 * gamePanel.tileSize;

        // position of the benda in the rumah
        mapNumber = 1;
    }

    public void setNPC() {
        int mapNumber = 0;

        gamePanel.npc[mapNumber][0] = new NPC(gamePanel);
        gamePanel.npc[mapNumber][0].worldX = 2 * gamePanel.tileSize;
        gamePanel.npc[mapNumber][0].worldY = 2 * gamePanel.tileSize;

        gamePanel.npc[mapNumber][1] = new NPC(gamePanel);
        gamePanel.npc[mapNumber][1].worldX = 63 * gamePanel.tileSize;
        gamePanel.npc[mapNumber][1].worldY = 63 * gamePanel.tileSize;

        gamePanel.npc[mapNumber][2] = new NPC(gamePanel);
        gamePanel.npc[mapNumber][2].worldX = 2 * gamePanel.tileSize;
        gamePanel.npc[mapNumber][2].worldY = 63 * gamePanel.tileSize;

        gamePanel.npc[mapNumber][3] = new NPC(gamePanel);
        gamePanel.npc[mapNumber][3].worldX = 63 * gamePanel.tileSize;
        gamePanel.npc[mapNumber][3].worldY = 2 * gamePanel.tileSize;
    }
}
