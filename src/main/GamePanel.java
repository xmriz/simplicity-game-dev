package main;

import java.awt.*;
import javax.swing.*;

import benda.Benda;
import entity.*;
import map.TileManager;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3; // 3x scale

    public final int tileSize = originalTileSize * scale; // 48x48 tile (1 kotak)
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 16;
    public final int screenWidth = maxScreenCol * tileSize; // 768 pixels
    public final int screenHeight = maxScreenRow * tileSize; // 768 pixels

    // WORLD SETTINGS
    public final int maxWorldCol = 66;
    public final int maxWorldRow = 66;
    public final int worldWidth = maxWorldCol * tileSize; // 3168 pixels
    public final int worldHeight = maxWorldRow * tileSize; // 3168 pixels

    // FPS
    int fps = 60; // frames per second

    // SYSTEM
    AssetSetter assetSetter = new AssetSetter(this); // create a new AssetSetter object
    TileManager tileManager = new TileManager(this); // create a new TileManager object
    public KeyHandler keyHandler = new KeyHandler(this); // create a new KeyHandler object
    public UI ui = new UI(this); // create a new UI object
    Thread gameThread; // thread for the game
    public CollisionChecker collisionChecker = new CollisionChecker(this); // create a new CollisionChecker object

    // ENTITY 
    public Sim sim = new Sim(this, keyHandler);
    public NPC npc[] = new NPC[4]; // create an array of NPC objects

    // BENDA
    public Benda benda[] = new Benda[8]; // create an array of Benda objects

    // GAME STATE
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogState = 3;
    public final int simInfoState = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // set the size of the panel
        this.setBackground(Color.black); // set the background color of the panel
        this.setDoubleBuffered(true); // set the panel to be double buffered
        this.addKeyListener(keyHandler); // add the key listener to the panel
        this.setFocusable(true); // set the panel to be focusable (so that it can receive key events)
    }

    public void setupGame(){
        gameState = playState; // set the game state to play state
        assetSetter.setBenda(); // setup the benda
        assetSetter.setNPC(); // setup the assets
    }

    public void startGameThread() { // start the game thread
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() { // the game loop
        double drawInterval = 1000000000.0/fps; // 0.0167 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }            
        }
    }

    public void update() {
        if (gameState == playState) {
            sim.update();
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }
        } else if (gameState == pauseState) {
            // do nothing
        } else if (gameState == dialogState) {
            // do nothing
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // call the paintComponent method of the parent class

        Graphics2D g2d = (Graphics2D) g; // cast the Graphics object to Graphics2D

        // draw the background
        tileManager.draw(g2d);
        
        
        // draw benda
        for (int i = 0; i < benda.length; i++) {
            if (benda[i] != null) {
                benda[i].draw(g2d, this);
            }
        }
        
        // draw sim
        sim.draw(g2d);
        
        // draw npc
        for (int i = 0; i < npc.length; i++) {
            if (npc[i] != null) {
                npc[i].draw(g2d);
            }
        }
        
        // draw ui
        ui.draw(g2d);

        g2d.dispose(); // dispose the Graphics2D object, freeing up memory
    }
}
