package main;

import entity.NPC;

public class AssetSetter {

    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setNPC() {
        gamePanel.npc[0] = new NPC(gamePanel);
        gamePanel.npc[0].worldX = 1 * gamePanel.tileSize;
        gamePanel.npc[0].worldY = 1 * gamePanel.tileSize;

        gamePanel.npc[1] = new NPC(gamePanel);
        gamePanel.npc[1].worldX = 64 * gamePanel.tileSize;
        gamePanel.npc[1].worldY = 64 * gamePanel.tileSize;

        gamePanel.npc[2] = new NPC(gamePanel);
        gamePanel.npc[2].worldX = 1 * gamePanel.tileSize;
        gamePanel.npc[2].worldY = 64 * gamePanel.tileSize;

        gamePanel.npc[3] = new NPC(gamePanel);
        gamePanel.npc[3].worldX = 64 * gamePanel.tileSize;
        gamePanel.npc[3].worldY = 1 * gamePanel.tileSize;
    }
}
