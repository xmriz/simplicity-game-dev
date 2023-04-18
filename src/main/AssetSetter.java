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
        gamePanel.benda[0] = new BahanMakanan_Kentang();
        gamePanel.benda[0].worldX = 3 * gamePanel.tileSize;
        gamePanel.benda[0].worldY = 3 * gamePanel.tileSize;

        gamePanel.benda[1] = new BahanMakanan_Sapi();
        gamePanel.benda[1].worldX = 4 * gamePanel.tileSize;
        gamePanel.benda[1].worldY = 4 * gamePanel.tileSize;

        gamePanel.benda[2] = new Furnitur_Jam();
        gamePanel.benda[2].worldX = 5 * gamePanel.tileSize;
        gamePanel.benda[2].worldY = 5 * gamePanel.tileSize;

    }

    public void setNPC() {
        gamePanel.npc[0] = new NPC(gamePanel);
        gamePanel.npc[0].worldX = 2 * gamePanel.tileSize;
        gamePanel.npc[0].worldY = 2 * gamePanel.tileSize;

        gamePanel.npc[1] = new NPC(gamePanel);
        gamePanel.npc[1].worldX = 63 * gamePanel.tileSize;
        gamePanel.npc[1].worldY = 63 * gamePanel.tileSize;

        gamePanel.npc[2] = new NPC(gamePanel);
        gamePanel.npc[2].worldX = 2 * gamePanel.tileSize;
        gamePanel.npc[2].worldY = 63 * gamePanel.tileSize;

        gamePanel.npc[3] = new NPC(gamePanel);
        gamePanel.npc[3].worldX = 63 * gamePanel.tileSize;
        gamePanel.npc[3].worldY = 2 * gamePanel.tileSize;
    }
}
