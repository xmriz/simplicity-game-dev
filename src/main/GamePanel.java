package main;

import java.awt.*;
import javax.swing.*;

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
    KeyHandler keyHandler = new KeyHandler(); // create a new KeyHandler object
    Thread gameThread; // thread for the game
    public CollisionChecker collisionChecker = new CollisionChecker(this); // create a new CollisionChecker object

    // ENTITY 
    public Sim sim = new Sim(this, keyHandler);
    public NPC npc[] = new NPC[4]; // create an array of NPC objects

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // set the size of the panel
        this.setBackground(Color.black); // set the background color of the panel
        this.setDoubleBuffered(true); // set the panel to be double buffered
        this.addKeyListener(keyHandler); // add the key listener to the panel
        this.setFocusable(true); // set the panel to be focusable (so that it can receive key events)
    }

    public void setupGame(){
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
        sim.update();
        for (int i = 0; i < npc.length; i++) {
            if (npc[i] != null) {
                npc[i].update();
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // call the paintComponent method of the parent class

        Graphics2D g2d = (Graphics2D) g; // cast the Graphics object to Graphics2D

        tileManager.draw(g2d); // draw the tile manager
        
        for (int i = 0; i < npc.length; i++) {
            if (npc[i] != null) {
                npc[i].draw(g2d); // draw the NPC
            }
        }

        sim.draw(g2d); // draw the sim

        g2d.dispose(); // dispose the Graphics2D object, freeing up memory
    }
}
