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
        int mapNumber = 0;
        for (int x = 0; x < gamePanel.listSim.size(); x++){
            gamePanel.listRumah[mapNumber].add(gamePanel.listSim.get(x).rumah);
            gamePanel.listRumah[mapNumber].get(x).worldX = gamePanel.listSim.get(x).rumah.colRumah * gamePanel.tileSize;
            gamePanel.listRumah[mapNumber].get(x).worldY = gamePanel.listSim.get(x).rumah.rowRumah * gamePanel.tileSize;
            // gamePanel.listRumah[mapNumber][x] = gamePanel.listSim.get(x).rumah;
            // TODO : SELESAIKAN SET SEMUA RUMAH LIST SIM
            // gamePanel.rumah[mapNumber][x].worldX = gamePanel.currentSim.rumah.dimensiX * gamePanel.tileSize;
            // gamePanel.rumah[mapNumber][x].worldY = gamePanel.currentSim.rumah.dimensiY * gamePanel.tileSize;
        }

        
        // position of the benda in the rumah
        // sil : contoh nambah benda di ruangan utama
        // gamePanel.currentSim.rumah.ruanganRumah.get(0).bendaRuangan.add(new Furnitur_Toilet());
        // gamePanel.currentSim.rumah.ruanganRumah.get(0).bendaRuangan[0].worldX = 5 * gamePanel.tileSize;
        // gamePanel.currentSim.rumah.ruanganRumah.get(0).bendaRuangan[0].worldY = 5 * gamePanel.tileSize;

        // gamePanel.currentSim.rumah.ruanganRumah.get(0).bendaRuangan[1] = new Furnitur_KomporGas();
        // gamePanel.currentSim.rumah.ruanganRumah.get(0).bendaRuangan[1].worldX = 2 * gamePanel.tileSize;
        // gamePanel.currentSim.rumah.ruanganRumah.get(0).bendaRuangan[1].worldY = 3 * gamePanel.tileSize;
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
