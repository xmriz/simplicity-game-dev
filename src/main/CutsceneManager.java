package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class CutsceneManager {
    GamePanel gamePanel;
    Graphics2D g2d;
    public int sceneNum;
    public int scenePhase;
    int counter = 0;
    float alpha = 0.0f;
    int y;

    // scene number
    public final int ending = 1;

    public CutsceneManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void draw (Graphics2D g2d){
        this.g2d = g2d;

        switch (sceneNum){
            case ending : scene_ending(); break;
        }
    }

    public void scene_ending(){
        if (scenePhase == 0){ // fade in black background
            alpha += 0.005f;
            if (alpha >= 1.0f){
                alpha = 1.0f;
            }
            drawBlackBackground(alpha);
            if (alpha == 1.0f){
                scenePhase = 1;
            }
        }

        if (scenePhase == 1){ // phase nampilin tulisan simplicity dan logo

        } 

        if (scenePhase == 2){ // credit scene

        }
    }

    public void drawBlackBackground (float alpha){
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    }
}
