package main;

import java.awt.*;
import javax.swing.*;

import entity.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3; // 3x scale

    public final int tileSize = originalTileSize * scale; // 48x48 tile (1 kotak)
    final int maxScreenCol = 16;
    final int maxScreenRow = 16;
    final int screenWidth = maxScreenCol * tileSize; // 768 pixels
    final int screenHeight = maxScreenRow * tileSize; // 768 pixels

    // FPS
    int fps = 60; // frames per second

    KeyHandler keyHandler = new KeyHandler(); // create a new KeyHandler object
    Thread gameThread; // thread for the game
    Sim sim = new Sim(this, keyHandler);


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // set the size of the panel
        this.setBackground(Color.black); // set the background color of the panel
        this.setDoubleBuffered(true); // set the panel to be double buffered
        this.addKeyListener(keyHandler); // add the key listener to the panel
        this.setFocusable(true); // set the panel to be focusable (so that it can receive key events)
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
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // call the paintComponent method of the parent class

        Graphics2D g2d = (Graphics2D) g; // cast the Graphics object to Graphics2D

        sim.draw(g2d); // draw the sim

        g2d.dispose(); // dispose the Graphics2D object, freeing up memory
    }
}
