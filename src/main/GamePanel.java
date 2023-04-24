package main;

import java.awt.*;
import javax.swing.*;

import benda.Benda;
import entity.*;
import environment.EnvironmentManager;
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
    public static int worldTime = 720; // waktu satu hari adalah 720 detik
    public int worldDay = 0; // hari pertama

    // MAP SETTINGS
    public final int maxMap = 2; // Ruangan and world
    public int currentMap = 0;

    // FPS
    int fps = 60; // frames per second

    // SYSTEM
    public TileManager tileManager = new TileManager(this); // create a new TileManager object
    public KeyHandler keyHandler = new KeyHandler(this); // create a new KeyHandler object
    AssetSetter assetSetter = new AssetSetter(this); // create a new AssetSetter object
    public CollisionChecker collisionChecker = new CollisionChecker(this); // create a new CollisionChecker object
    public UI ui = new UI(this); // create a new UI object
    public EventHandler eventHandler = new EventHandler(this); // create a new EventHandler object
    EnvironmentManager environmentManager = new EnvironmentManager(this); // create a new EnvironmentManager object
    Thread gameThread; // thread for the game

    // ENTITY 
    public Sim sim = new Sim(this, keyHandler);
    public Entity npc[][] = new Entity[maxMap][6]; // create an array of NPC objects

    // BENDA
    public Benda benda[][] = new Benda[maxMap][8]; // create an array of Benda objects yang dapat diletakkan

    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogState = 3;
    public final int simInfoState = 4;
    public final int inventoryState = 5;
    public final int beliState = 6;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // set the size of the panel
        this.setBackground(Color.black); // set the background color of the panel
        this.setDoubleBuffered(true); // set the panel to be double buffered
        this.addKeyListener(keyHandler); // add the key listener to the panel
        this.setFocusable(true); // set the panel to be focusable (so that it can receive key events)
    }

    public void setupGame(){
        gameState = titleState; // set the default game state to titleState
        assetSetter.setBenda(); // setup the benda
        assetSetter.setNPC(); // setup the assets
        environmentManager.setup(); // setup the environment
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
            for (int i = 0; i < npc[currentMap].length; i++) {
                if (npc[currentMap][i] != null) {
                    npc[currentMap][i].update();
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

        if (gameState == titleState){
            ui.draw(g2d);
        } else {
            // draw the background
            tileManager.draw(g2d);

            // draw benda
            for (int i = 0; i < benda[currentMap].length; i++) {
                if (benda[currentMap][i] != null) {
                    benda[currentMap][i].draw(g2d, this);
                }
            }

            // draw npc
            for (int i = 0; i < npc[currentMap].length; i++) {
                if (npc[currentMap][i] != null) {
                    npc[currentMap][i].draw(g2d);
                }
            }

            // draw sim
            sim.draw(g2d);

            // draw environment
            environmentManager.update();
            environmentManager.draw(g2d);

            // draw ui
            ui.draw(g2d);

            // world time
            if (keyHandler.checkWorldTime){
                g2d.setColor(Color.white);
                g2d.setFont(g2d.getFont().deriveFont(40f));
                g2d.drawString("World Time: " + worldTime, 10, 50); 
            }
        }


        g2d.dispose(); // dispose the Graphics2D object, freeing up memory
    }
}
