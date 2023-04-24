package main;

import benda.*;
import entity.NPC_Kakek;
import entity.NPC_Penjual;

public class AssetSetter {

    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setBenda(){
        // position of the benda in the world
        // furnitur
        int i = 0;
        int mapNumber = 0;
        gamePanel.benda[mapNumber][i] = gamePanel.sim.rumah;
        gamePanel.benda[mapNumber][i].worldX = gamePanel.sim.rumah.dimensiX * gamePanel.tileSize;
        gamePanel.benda[mapNumber][i].worldY = gamePanel.sim.rumah.dimensiY * gamePanel.tileSize;
        i++;
        
        gamePanel.benda[mapNumber][i] = new Lampu(gamePanel);
        gamePanel.benda[mapNumber][i].worldX = 6 * gamePanel.tileSize;
        gamePanel.benda[mapNumber][i].worldY = 7 * gamePanel.tileSize;
        i++;

        gamePanel.benda[mapNumber][i] = new Furnitur_Jam();
        gamePanel.benda[mapNumber][i].worldX = 7 * gamePanel.tileSize;
        gamePanel.benda[mapNumber][i].worldY = 7 * gamePanel.tileSize;
        i++;
        

        // position of the benda in the rumah
        mapNumber = 1;
    }

    public void setNPC() {
        int i = 0;
        // MAP 1
        int mapNumber = 0;
        gamePanel.npc[mapNumber][i] = new NPC_Kakek(gamePanel);
        gamePanel.npc[mapNumber][i].worldX = 2 * gamePanel.tileSize;
        gamePanel.npc[mapNumber][i].worldY = 2 * gamePanel.tileSize;
        i++;

        gamePanel.npc[mapNumber][i] = new NPC_Kakek(gamePanel);
        gamePanel.npc[mapNumber][i].worldX = 63 * gamePanel.tileSize;
        gamePanel.npc[mapNumber][i].worldY = 63 * gamePanel.tileSize;
        i++;

        gamePanel.npc[mapNumber][i] = new NPC_Kakek(gamePanel);
        gamePanel.npc[mapNumber][i].worldX = 2 * gamePanel.tileSize;
        gamePanel.npc[mapNumber][i].worldY = 63 * gamePanel.tileSize;
        i++;

        gamePanel.npc[mapNumber][i] = new NPC_Kakek(gamePanel);
        gamePanel.npc[mapNumber][i].worldX = 63 * gamePanel.tileSize;
        gamePanel.npc[mapNumber][i].worldY = 2 * gamePanel.tileSize;
        i++;

        gamePanel.npc[mapNumber][i] = new NPC_Penjual(gamePanel);
        gamePanel.npc[mapNumber][i].worldX = 6 * gamePanel.tileSize;
        gamePanel.npc[mapNumber][i].worldY = 6 * gamePanel.tileSize;
        i++;
        
        gamePanel.npc[mapNumber][i] = new NPC_Penjual(gamePanel);
        gamePanel.npc[mapNumber][i].worldX = 7 * gamePanel.tileSize;
        gamePanel.npc[mapNumber][i].worldY = 7 * gamePanel.tileSize;
        i++;
        
        // MAP 2
        mapNumber = 1;
    }
}
