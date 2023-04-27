package main;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.*;

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
    // public int listSim.get(indexCurrentSim).currentMap = 0;

    // FPS
    int fps = 60; // frames per second

    // SYSTEM
    public TileManager tileManager = new TileManager(this); // create a new TileManager object
    public KeyHandler keyHandler = new KeyHandler(this); // create a new KeyHandler object
    Sound music = new Sound();
    Sound soundEffect = new Sound();
    AssetSetter assetSetter = new AssetSetter(this); // create a new AssetSetter object
    public CollisionChecker collisionChecker = new CollisionChecker(this); // create a new CollisionChecker object
    public UI ui = new UI(this); // create a new UI object
    public EventHandler eventHandler = new EventHandler(this); // create a new EventHandler object
    EnvironmentManager environmentManager = new EnvironmentManager(this); // create a new EnvironmentManager object
    Thread gameThread; // thread for the game

    // Multisim
    public List<Sim> listSim = new ArrayList<>();
    public int indexCurrentSim;

    // ENTITY
    // public Sim sim = new Sim(this, keyHandler);
    public Entity npc[][] = new Entity[maxMap][6]; // create an array of NPC objects

    // BENDA
    // @SuppressWarnings("unchecked")
    @SuppressWarnings("unchecked")
    public List<Benda>[] listRumah = new ArrayList[maxMap]; // create an array of ArrayList of Integer objects

    // public Benda rumah[][] = new Benda[maxMap][8]; // create an array of Benda
    // objects yang dapat diletakkan

    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogState = 3;
    public final int simInfoState = 4;
    public final int inventoryState = 5;
    public final int beliState = 6;
    public final int upgradeRumahState = 7;
    public final int inputNamaRuanganState = 8;
    public final int inputKoordinatBendaState = 9;
    public final int addSimState = 10;
    public final int inputKoordinatRumahSimState = 11;
    public final int changeSimState = 12;
    public final int menuState = 13;
    public final int helpState = 14;

    public GamePanel() {
        for (int i = 0; i < maxMap; i++) {
            listRumah[i] = new ArrayList<Benda>();
        }
        listSim.add(new Sim(this, keyHandler)); // nambah sim pertama

        indexCurrentSim = 0;

        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // set the size of the panel
        this.setBackground(Color.black); // set the background color of the panel
        this.setDoubleBuffered(true); // set the panel to be double buffered
        this.addKeyListener(keyHandler); // add the key listener to the panel
        this.setFocusable(true); // set the panel to be focusable (so that it can receive key events)
    }

    public void setupGame() {
        gameState = titleState; // set the default game state to titleState
        assetSetter.setBenda(); // setup the benda
        assetSetter.setNPC(); // setup the assets
        environmentManager.setup(); // setup the environment
        playMusic(0);
    }

    public void startGameThread() { // start the game thread
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() { // the game loop
        double drawInterval = 1000000000.0 / fps; // 0.0167 seconds
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
            listSim.get(indexCurrentSim).update();
            for (int i = 0; i < npc[listSim.get(indexCurrentSim).currentMap].length; i++) {
                if (npc[listSim.get(indexCurrentSim).currentMap][i] != null) {
                    npc[listSim.get(indexCurrentSim).currentMap][i].update();
                }
            }
        } else if (gameState == pauseState) {
            // do nothing
        } else if (gameState == dialogState) {
            // do nothing
        }

        // memastikan kalau sim berada di world indexRuangan 999
        if (listSim.get(indexCurrentSim).currentMap == 0) {
            listSim.get(indexCurrentSim).indexLocationRuangan = 999;
            listSim.get(indexCurrentSim).currentLocation = "World";
        }
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSoundEffect(int i) {
        soundEffect.setFile(i);
        soundEffect.play();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // call the paintComponent method of the parent class

        Graphics2D g2d = (Graphics2D) g; // cast the Graphics object to Graphics2D

        if (gameState == titleState) {
            ui.draw(g2d); // draw the title screen
        } else {
            // draw the background
            if (listSim.get(indexCurrentSim).currentMap == 0) {
                tileManager.draw(g2d, 999);
            } else {
                tileManager.draw(g2d, listSim.get(indexCurrentSim).indexLocationRuangan);
            }

            // draw benda
            if (listSim.get(indexCurrentSim).currentMap == 0) {
                for (int i = 0; i < listRumah[listSim.get(indexCurrentSim).currentMap].size(); i++) {
                    if (listRumah[listSim.get(indexCurrentSim).currentMap].get(i) != null) {
                        listRumah[listSim.get(indexCurrentSim).currentMap].get(i).draw(g2d, this);
                    }
                }
            } else {
                for (int i = 0; i < listSim.get(listSim.get(indexCurrentSim).indexRumahYangDimasuki).rumah.ruanganRumah
                        .get(listSim.get(indexCurrentSim).indexLocationRuangan).bendaRuangan.size(); i++) {
                    if (listSim.get(listSim.get(indexCurrentSim).indexRumahYangDimasuki).rumah.ruanganRumah
                            .get(listSim.get(indexCurrentSim).indexLocationRuangan).bendaRuangan.get(i) != null) {
                        listSim.get(listSim.get(indexCurrentSim).indexRumahYangDimasuki).rumah.ruanganRumah
                                .get(listSim.get(indexCurrentSim).indexLocationRuangan).bendaRuangan.get(i)
                                .draw(g2d, this);
                    }
                }
            }

            // draw npc
            for (int i = 0; i < npc[listSim.get(indexCurrentSim).currentMap].length; i++) {
                if (npc[listSim.get(indexCurrentSim).currentMap][i] != null) {
                    npc[listSim.get(indexCurrentSim).currentMap][i].draw(g2d);
                }
            }

            // draw sim
            listSim.get(indexCurrentSim).draw(g2d);

            // draw environment
            environmentManager.update();
            environmentManager.draw(g2d);

            // draw ui
            ui.draw(g2d);

            // world time
            if (keyHandler.checkWorldTime) {
                keyHandler.checkCurrentLocation = false;
                g2d.setColor(Color.white);
                g2d.setFont(g2d.getFont().deriveFont(40f));
                g2d.drawString("World Time: " + worldTime, 10, 700);
            }

            // current location
            if (keyHandler.checkCurrentLocation) {
                keyHandler.checkWorldTime = false;
                g2d.setColor(Color.white);
                g2d.setFont(g2d.getFont().deriveFont(40f));
                g2d.drawString("Current Location: " + listSim.get(indexCurrentSim).currentLocation, 10, 700);
            }
        }

        g2d.dispose(); // dispose the Graphics2D object, freeing up memory
    }
}
