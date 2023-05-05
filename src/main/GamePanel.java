package main;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.*;

import benda.Benda;
import entity.*;
import Environment.EnvironmentManager;
import map.TileManager;
import map.Map;
import data.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3; // 3x scale

    private final int tileSize = originalTileSize * scale; // 48x48 tile (1 kotak)
    private final int maxScreenCol = 16;
    private final int maxScreenRow = 16;
    private final int screenWidth = maxScreenCol * tileSize; // 768 pixels
    private final int screenHeight = maxScreenRow * tileSize; // 768 pixels

    // WORLD SETTINGS
    private final int maxWorldCol = 66;
    private final int maxWorldRow = 66;
    private final int worldWidth = maxWorldCol * tileSize; // 3168 pixels
    private final int worldHeight = maxWorldRow * tileSize; // 3168 pixels
    private int worldTimeCounter = 0; // Time : worldTimeCounter%720 ; Day : worldTimeCounter/720


    // darr ini gua tambah lagi
    private int worldTimeSatuHariCounter = 0; // Time : worldTimeCounter%720 ; Day : worldTimeCounter/720

    // MAP SETTINGS
    private final int maxMap = 2; // Ruangan and world
    // public int listSim.get(indexCurrentSim).currentMap = 0;

    // FPS
    int fps = 60; // frames per second

    // SYSTEM
    private TileManager tileManager = new TileManager(this); // create a new TileManager object
    private KeyHandler keyHandler = new KeyHandler(this); // create a new KeyHandler object
    Sound music = new Sound();
    Sound soundEffect = new Sound();
    AssetSetter assetSetter = new AssetSetter(this); // create a new AssetSetter object
    private CollisionChecker collisionChecker = new CollisionChecker(this); // create a new CollisionChecker object
    private UI ui = new UI(this); // create a new UI object
    private EventHandler eventHandler = new EventHandler(this); // create a new EventHandler object
    EnvironmentManager environmentManager = new EnvironmentManager(this); // create a new EnvironmentManager object
    Map map = new Map(this);
    SaveLoad saveLoad = new SaveLoad(this);
    private CutsceneManager cutsceneManager = new CutsceneManager(this);
    Thread gameThread; // thread for the game

    // Multisim
    private List<Sim> listSim = new ArrayList<>();
    private int indexCurrentSim;
    private boolean isOneSim = true;
    // public Sim currentSim = listSim.get(indexCurrentSim);

    // ENTITY
    // public Sim sim = new Sim(this, keyHandler);
    private Entity npc[][] = new Entity[maxMap][6]; // create an array of NPC objects
    private Entity kokiTemp = new NPC_Koki(this);

    // BENDA
    // @SuppressWarnings("unchecked")
    @SuppressWarnings("unchecked")
    private List<Benda>[] listRumah = new ArrayList[maxMap]; // create an array of ArrayList of Integer objects

    // public Benda rumah[][] = new Benda[maxMap][8]; // create an array of Benda
    // objects yang dapat diletakkan

    // GAME STATE
    private int gameState;
    private final int titleState = 0;
    private final int playState = 1;
    private final int pauseState = 2;
    private final int dialogState = 3;
    private final int simInfoState = 4;
    private final int inventoryState = 5;
    private final int beliState = 6;
    private final int upgradeRumahState = 7;
    private final int inputNamaRuanganState = 8;
    private final int inputKoordinatBendaState = 9;
    private final int addSimState = 10;
    private final int inputKoordinatRumahSimState = 11;
    private final int changeSimState = 12;
    private final int menuState = 13;
    private final int helpState = 14;
    private final int mapState = 15;
    private final int resepState = 16;
    private final int timerState = 17;
    private final int inputDurasiTidurState = 18;
    private final int gameOverState = 19;
    private final int inputDurasiNontonState = 20;
    private final int inputDurasiMandiState = 21;
    private final int inputDurasiShalatState = 22;
    private final int inputDurasiBacaBukuState = 23;
    private final int inputDurasiRadioState = 24;
    private final int inputDurasiSiramTanamanState = 25;
    private final int inputDurasiMainGameState = 26;
    private final int kerjaState = 27;
    private final int inputDurasiKerjaState = 28;
    private final int gantiPekerjaanState = 29;
    private final int inputDurasiOlahragaState = 30;
    private final int saveState = 31;
    private final int melihatWaktuState = 32;
    private final int cutsceneState = 33;

    // Getter and Setter
    public int getTileSize() {
        return tileSize;
    }

    public int getMaxScreenCol() {
        return maxScreenCol;
    }

    public int getMaxScreenRow() {
        return maxScreenRow;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getMaxWorldCol() {
        return maxWorldCol;
    }

    public int getMaxWorldRow() {
        return maxWorldRow;
    }

    public int getWorldWidth() {
        return worldWidth;
    }

    public int getWorldHeight() {
        return worldHeight;
    }

    public int getWorldTimeCounter() {
        return worldTimeCounter;
    }

    public void setWorldTimeCounter(int worldTimeCounter) {
        this.worldTimeCounter = worldTimeCounter;
    }

    public void incWorldTimeCounter(int inc){
        this.worldTimeCounter += inc;
    }

    public int getWorldTimeSatuHariCounter() {
        return worldTimeSatuHariCounter;
    }

    public void setWorldTimeSatuHariCounter(int worldTimeSatuHariCounter) {
        this.worldTimeSatuHariCounter = worldTimeSatuHariCounter;
    }

    public void incWorldTimeSatuHariCounter(int inc){
        this.worldTimeSatuHariCounter += inc;
    }

    public int getMaxMap() {
        return maxMap;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public void setTileManager(TileManager tileManager) {
        this.tileManager = tileManager;
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public void setKeyHandler(KeyHandler keyHandler) {
        this.keyHandler = keyHandler;
    }

    public CollisionChecker getCollisionChecker() {
        return collisionChecker;
    }

    public void setCollisionChecker(CollisionChecker collisionChecker) {
        this.collisionChecker = collisionChecker;
    }

    public UI getUi() {
        return ui;
    }

    public void setUi(UI ui) {
        this.ui = ui;
    }

    public EventHandler getEventHandler() {
        return eventHandler;
    }

    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    public CutsceneManager getCutsceneManager() {
        return cutsceneManager;
    }

    public void setCutsceneManager(CutsceneManager cutsceneManager) {
        this.cutsceneManager = cutsceneManager;
    }

    public List<Sim> getListSim() {
        return listSim;
    }

    public void setListSim(List<Sim> listSim) {
        this.listSim = listSim;
    }

    public int getIndexCurrentSim() {
        return indexCurrentSim;
    }

    public void setIndexCurrentSim(int indexCurrentSim) {
        this.indexCurrentSim = indexCurrentSim;
    }

    public boolean getIsOneSim() {
        return isOneSim;
    }

    public void setIsOneSim(boolean isOneSim) {
        this.isOneSim = isOneSim;
    }

    public Entity[][] getNpc() {
        return npc;
    }

    public void setNpc(Entity[][] npc) {
        this.npc = npc;
    }

    public Entity getKokiTemp() {
        return kokiTemp;
    }

    public void setKokiTemp(Entity kokiTemp) {
        this.kokiTemp = kokiTemp;
    }

    public List<Benda>[] getListRumah() {
        return listRumah;
    }

    public void setListRumah(List<Benda>[] listRumah) {
        this.listRumah = listRumah;
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }


    public int getTitleState() {
        return titleState;
    }

    public int getPlayState() {
        return playState;
    }

    public int getPauseState() {
        return pauseState;
    }

    public int getDialogState() {
        return dialogState;
    }

    public int getSimInfoState() {
        return simInfoState;
    }

    public int getInventoryState() {
        return inventoryState;
    }

    public int getBeliState() {
        return beliState;
    }

    public int getUpgradeRumahState() {
        return upgradeRumahState;
    }

    public int getInputNamaRuanganState() {
        return inputNamaRuanganState;
    }

    public int getInputKoordinatBendaState() {
        return inputKoordinatBendaState;
    }

    public int getAddSimState() {
        return addSimState;
    }

    public int getInputKoordinatRumahSimState() {
        return inputKoordinatRumahSimState;
    }

    public int getChangeSimState() {
        return changeSimState;
    }

    public int getMenuState() {
        return menuState;
    }

    public int getHelpState() {
        return helpState;
    }

    public int getMapState() {
        return mapState;
    }

    public int getResepState() {
        return resepState;
    }

    public int getTimerState() {
        return timerState;
    }

    public int getInputDurasiTidurState() {
        return inputDurasiTidurState;
    }

    public int getGameOverState() {
        return gameOverState;
    }

    public int getInputDurasiNontonState() {
        return inputDurasiNontonState;
    }

    public int getInputDurasiMandiState() {
        return inputDurasiMandiState;
    }

    public int getInputDurasiShalatState() {
        return inputDurasiShalatState;
    }

    public int getInputDurasiBacaBukuState() {
        return inputDurasiBacaBukuState;
    }

    public int getInputDurasiRadioState() {
        return inputDurasiRadioState;
    }

    public int getInputDurasiSiramTanamanState() {
        return inputDurasiSiramTanamanState;
    }

    public int getInputDurasiMainGameState() {
        return inputDurasiMainGameState;
    }

    public int getKerjaState() {
        return kerjaState;
    }

    public int getInputDurasiKerjaState() {
        return inputDurasiKerjaState;
    }

    public  int getGantiPekerjaanState() {
        return gantiPekerjaanState;
    }

    public int getInputDurasiOlahragaState() {
        return inputDurasiOlahragaState;
    }

    public int getSaveState() {
        return saveState;
    }

    public int getMelihatWaktuState() {
        return melihatWaktuState;
    }

    public int getCutsceneState() {
        return cutsceneState;
    }

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
        soundEffect.setVolume(2);
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

        

        for (int i = 0; i < listSim.size(); i++) {
            if (listSim.get(i).getPekerjaan().getWorldTimeCounterForStartJobAfterChangeJob() >= 720) {
                listSim.get(i).getPekerjaan().setIsCanStartPekerjaan(true);
                listSim.get(i).getPekerjaan().setWorldTimeCounterForStartJobAfterChangeJob(0);
                
            }

            if (worldTimeSatuHariCounter >= 720) {
                worldTimeSatuHariCounter = 0;
                listSim.get(i).setEfekWaktuTidakTidurCounter(0);
                listSim.get(i).setEfekWaktuTidakBuangAirCounter(0);
                listSim.get(i).setIsUdahMakanDalamSatuHari(false);
            }

            if (listSim.get(i).getPekerjaan().getTotalDurasiKerjaPerPekerjaan() >= 720) {
                listSim.get(i).getPekerjaan().setIsCanChangePekerjaan(true);
            } else {
                listSim.get(i).getPekerjaan().setIsCanChangePekerjaan(false);   
            }

            if (listSim.get(i).getEfekWaktuTidakTidurCounter() >= 600){
                // kurangin kesejahteraan
                listSim.get(i).setEfekWaktuTidakTidurCounter(0);
                listSim.get(i).setMood(listSim.get(i).getMood() - 5);
                listSim.get(i).setKesehatan(listSim.get(i).getKesehatan() - 5);
                ui.addMessage("-5 mood, -5 kesehatan");
            }

            if (listSim.get(i).getEfekWaktuTidakBuangAirCounter() >= 240){
                // kurangin kesejahteraan
                if (listSim.get(i).getIsUdahMakanDalamSatuHari()){
                    listSim.get(i).setEfekWaktuTidakBuangAirCounter(0);
                    listSim.get(i).setMood(listSim.get(i).getMood() - 5);
                    listSim.get(i).setKesehatan(listSim.get(i).getKesehatan() - 5);
                    ui.addMessage("-5 mood, -5 kesehatan");
                }
            }
        }

        // for (int i = 0; i < listSim.size(); i++) {
            
        // }

        if (gameState == playState) {
            listSim.get(indexCurrentSim).update();
            for (int i = 0; i < npc[listSim.get(indexCurrentSim).getCurrentMap()].length; i++) {
                if (npc[listSim.get(indexCurrentSim).getCurrentMap()][i] != null) {
                    npc[listSim.get(indexCurrentSim).getCurrentMap()][i].update();
                }
            }
        } else if (gameState == pauseState) {
            // do nothing
        } else if (gameState == dialogState) {
            // do nothing
        }

        // memastikan kalau sim berada di world indexRuangan 999
        if (listSim.get(indexCurrentSim).getCurrentMap() == 0) {
            listSim.get(indexCurrentSim).setIndexLocationRuangan(999);
            listSim.get(indexCurrentSim).setCurrentLocation("World");
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

    public Sim getCurrentSim() {
        return listSim.get(indexCurrentSim);
    }

    public Map getMap() {
        return map;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // call the paintComponent method of the parent class

        Graphics2D g2d = (Graphics2D) g; // cast the Graphics object to Graphics2D

        if (gameState == titleState) {
            ui.draw(g2d); // draw the title screen
        } else {
            // draw the background
            if (listSim.get(indexCurrentSim).getCurrentMap() == 0) {
                tileManager.draw(g2d, 999);
            } else {
                tileManager.draw(g2d, listSim.get(indexCurrentSim).getIndexLocationRuangan());
            }

            // draw benda
            if (listSim.get(indexCurrentSim).getCurrentMap() == 0) {
                for (int i = 0; i < listRumah[listSim.get(indexCurrentSim).getCurrentMap()].size(); i++) {
                    if (listRumah[listSim.get(indexCurrentSim).getCurrentMap()].get(i) != null) {
                        listRumah[listSim.get(indexCurrentSim).getCurrentMap()].get(i).draw(g2d, this);
                    }
                }
            } else {
                for (int i = 0; i < listSim.get(listSim.get(indexCurrentSim).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                        .get(listSim.get(indexCurrentSim).getIndexLocationRuangan()).getBendaRuangan().size(); i++) {
                    if (listSim.get(listSim.get(indexCurrentSim).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                            .get(listSim.get(indexCurrentSim).getIndexLocationRuangan()).getBendaRuangan().get(i) != null) {
                        listSim.get(listSim.get(indexCurrentSim).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                                .get(listSim.get(indexCurrentSim).getIndexLocationRuangan()).getBendaRuangan().get(i)
                                .draw(g2d, this);
                    }
                }
            }

            // draw npc
            for (int i = 0; i < npc[listSim.get(indexCurrentSim).getCurrentMap()].length; i++) {
                if (npc[listSim.get(indexCurrentSim).getCurrentMap()][i] != null) {
                    npc[listSim.get(indexCurrentSim).getCurrentMap()][i].draw(g2d);
                }
            }

            // draw sim
            listSim.get(indexCurrentSim).draw(g2d);

            // draw environment
            environmentManager.update();
            environmentManager.draw(g2d);

            // // draw mini map
            // map.drawMiniMap(g2d);

            // draw ui
            ui.draw(g2d);

            // world time
            if (keyHandler.isCheckWorldTime()) {
                g2d.setColor(Color.white);
                g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN,40f));
                g2d.drawString("World Time: " + worldTimeCounter % 720, 10, 700);
                g2d.drawString("World Day: " + worldTimeCounter / 720, 10, 748);
            }

            // current location
            if (keyHandler.isCheckCurrentLocation()) {
                g2d.setColor(Color.white);
                g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN,40f));
                g2d.drawString("Current Location: " + listSim.get(indexCurrentSim).getCurrentLocation(), 10, 700);
            }

        }

        g2d.dispose(); // dispose the Graphics2D object, freeing up memory
    }
}
