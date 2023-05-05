package main;

import entity.*;

public class AssetSetter {

    private GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setBenda(){ // dilakukan satu kali saja
        // position of the benda in the world
        int mapNumber = 0;
        for (int x = 0; x < gamePanel.getListSim().size(); x++){
            gamePanel.getListRumah()[mapNumber].add(gamePanel.getListSim().get(x).getRumah());
            gamePanel.getListRumah()[mapNumber].get(x).setWorldX(gamePanel.getListSim().get(x).getRumah().getColRumah() * gamePanel.getTileSize());
            gamePanel.getListRumah()[mapNumber].get(x).setWorldY(gamePanel.getListSim().get(x).getRumah().getRowRumah() * gamePanel.getTileSize());
            // gamePanel.listRumah[mapNumber][x] = gamePanel.listSim.get(x).rumah;
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
        gamePanel.getNpc()[mapNumber][i] = new NPC_Kakek(gamePanel);
        gamePanel.getNpc()[mapNumber][i].setWorldX(2 * gamePanel.getTileSize());
        gamePanel.getNpc()[mapNumber][i].setWorldY(2 * gamePanel.getTileSize());
        i++;

        gamePanel.getNpc()[mapNumber][i] = new NPC_Kakek(gamePanel);
        gamePanel.getNpc()[mapNumber][i].setWorldX(63 * gamePanel.getTileSize());
        gamePanel.getNpc()[mapNumber][i].setWorldY(63 * gamePanel.getTileSize());
        i++;

        gamePanel.getNpc()[mapNumber][i] = new NPC_Kakek(gamePanel);
        gamePanel.getNpc()[mapNumber][i].setWorldX(2 * gamePanel.getTileSize());
        gamePanel.getNpc()[mapNumber][i].setWorldY( 63 * gamePanel.getTileSize());
        i++;

        gamePanel.getNpc()[mapNumber][i] = new NPC_Kakek(gamePanel);
        gamePanel.getNpc()[mapNumber][i].setWorldX(63 * gamePanel.getTileSize());
        gamePanel.getNpc()[mapNumber][i].setWorldY(2 * gamePanel.getTileSize());
        i++;

        gamePanel.getNpc()[mapNumber][i] = new NPC_Penjual(gamePanel);
        gamePanel.getNpc()[mapNumber][i].setWorldX(6 * gamePanel.getTileSize());
        gamePanel.getNpc()[mapNumber][i].setWorldY(6 * gamePanel.getTileSize());
        i++;
        
        // MAP 2
        mapNumber = 1;
    }
}
