package main;

import entity.NPC_Kakek;
import entity.NPC_Penjual;

public class AssetSetter {

    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setBenda(){
        // position of the benda in the world
        int i = 0;
        int mapNumber = 0;
        gamePanel.benda[mapNumber][i] = gamePanel.sim.rumah;
        gamePanel.benda[mapNumber][i].worldX = gamePanel.sim.rumah.dimensiX * gamePanel.tileSize;
        gamePanel.benda[mapNumber][i].worldY = gamePanel.sim.rumah.dimensiY * gamePanel.tileSize;
        i++;

        
        // position of the benda in the rumah
        // sil : contoh nambah benda di ruangan utama
        // gamePanel.sim.rumah.ruanganRumah.get(0).bendaRuangan.add(new Furnitur_Toilet());
        // gamePanel.sim.rumah.ruanganRumah.get(0).bendaRuangan[0].worldX = 5 * gamePanel.tileSize;
        // gamePanel.sim.rumah.ruanganRumah.get(0).bendaRuangan[0].worldY = 5 * gamePanel.tileSize;

        // gamePanel.sim.rumah.ruanganRumah.get(0).bendaRuangan[1] = new Furnitur_KomporGas();
        // gamePanel.sim.rumah.ruanganRumah.get(0).bendaRuangan[1].worldX = 2 * gamePanel.tileSize;
        // gamePanel.sim.rumah.ruanganRumah.get(0).bendaRuangan[1].worldY = 3 * gamePanel.tileSize;
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
