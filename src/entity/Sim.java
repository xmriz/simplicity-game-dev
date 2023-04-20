package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.*;

import benda.*;
import main.*;

public class Sim extends Entity {

    // SIM INFO
    public String nama = "Sim";
    public String pekerjaan = "Pengangguran";
    public int uang = 100;
    public int kesehatan = 80;
    public final int maxKesehatan = 100;
    public int kekenyangan = 80;
    public final int maxKekenyangan = 100;
    public int mood = 80;
    public final int maxMood = 100;

    public Rumah rumah;

    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;

    public List<Benda> inventory = new ArrayList<>();
    public int maxInventorySize = 33;

    // BahanMakanan
    int hasAyam = 0;
    int hasBayam = 0;
    int hasKacang = 0;
    int hasKentang = 0;
    int hasNasi = 0;
    int hasSapi = 0;
    int hasSusu = 0;
    int hasWortel = 0;

    // int Makanan
    int hasBistik = 0;
    int hasNasiAyam = 0;
    int hasNasiKari = 0;
    int hasSusuKacang = 0;
    int hasTumisSayur = 0;


    public Sim(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel);

        this.keyHandler = keyHandler;

        screenX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2); // set the player's position on the screen
                                                                        // (always center)
        screenY = gamePanel.screenHeight / 2 - (gamePanel.tileSize / 2);

        solidArea = new Rectangle(8, 16, 32, 32);

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getImage();
        setItems();
    }

    public void setDefaultValues() { // set the default values of the player
        worldX = 1 * gamePanel.tileSize; // set the player's position in the world
        worldY = 1 * gamePanel.tileSize;
        speed = 4; 
        direction = "down";
    }

    public void setItems(){

        // BAHAN MAKANAN
        inventory.add(new BahanMakanan_Ayam());
        inventory.add(new BahanMakanan_Bayam());
        inventory.add(new BahanMakanan_Kacang());
        inventory.add(new BahanMakanan_Kentang());
        inventory.add(new BahanMakanan_Nasi());
        inventory.add(new BahanMakanan_Sapi());
        inventory.add(new BahanMakanan_Susu());
        inventory.add(new BahanMakanan_Wortel());

        // MAKANAN
        inventory.add(new Makanan_Bistik());
        inventory.add(new Makanan_NasiAyam());
        inventory.add(new Makanan_NasiKari());
        inventory.add(new Makanan_SusuKacang());
        inventory.add(new Makanan_TumisSayur());

        // FURNITUR
        inventory.add(new Furnitur_Jam());
        // to be continuedd
    }

    public void getImage() {
        up1 = setupImage("sim/p2_up_1");
        up2 = setupImage("sim/p2_up_2");
        down1 = setupImage("sim/p2_down_1");
        down2 = setupImage("sim/p2_down_2");
        left1 = setupImage("sim/p2_left_1");
        left2 = setupImage("sim/p2_left_2");
        right1 = setupImage("sim/p2_right_1");
        right2 = setupImage("sim/p2_right_2");
    }

    public void update() { // update the position of the player
        if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {
            if (keyHandler.upPressed) {
                direction = "up";
            } else if (keyHandler.downPressed) {
                direction = "down";
            } else if (keyHandler.leftPressed) {
                direction = "left";
            } else if (keyHandler.rightPressed) {
                direction = "right";
            }

            // check for collision
            collisionOn = false;
            gamePanel.collisionChecker.checkTile(this);

            // check for benda collision
            int bendaIndex = gamePanel.collisionChecker.checkBenda(this, true); // return the index of the benda that the
                                                                                // player is colliding with
            pickUpObject(bendaIndex);                                                                                

            // check npc collision
            int npcIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.npc);
            interactNPC(npcIndex);

            // if there is a collision, sim can't move
            if (!collisionOn) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 12) {
                spriteCounter = 0;
                if (spriteNumber == 1) {
                    spriteNumber = 2;
                } else {
                    spriteNumber = 1;
                }
            }
        }
    }

    public void pickUpObject(int index){
        if (index != 999){ // 999 means there is no collision with benda
            String bendaName = gamePanel.benda[gamePanel.currentMap][index].name;

            // check nama benda (bahanMakanan/) yang diambil dan tambahkan ke inventory
            switch (bendaName) {
                // bahanMakanan
                case "Ayam":
                    hasAyam++;
                    gamePanel.benda[gamePanel.currentMap][index] = null;
                    break;
                case "Bayam":
                    hasBayam++;
                    gamePanel.benda[gamePanel.currentMap][index] = null;
                    break;
                case "Kacang":
                    hasKacang++;
                    gamePanel.benda[gamePanel.currentMap][index] = null;
                    break;
                case "Kentang":
                    hasKentang++;
                    gamePanel.benda[gamePanel.currentMap][index] = null;
                    System.out.println("Kentang: " + hasKentang);
                    break;
                case "Nasi":
                    hasNasi++;
                    gamePanel.benda[gamePanel.currentMap][index] = null;
                    break;
                case "Sapi":
                    hasSapi++;
                    gamePanel.benda[gamePanel.currentMap][index] = null;
                    break;
                case "Susu":
                    hasSusu++;
                    gamePanel.benda[gamePanel.currentMap][index] = null;
                    break;
                case "Wortel":
                    hasWortel++;
                    gamePanel.benda[gamePanel.currentMap][index] = null;
                    break;
                // makanan
                case "Bistik":
                    hasBistik++;
                    gamePanel.benda[gamePanel.currentMap][index] = null;
                    break;
                case "NasiAyam":
                    hasNasiAyam++;
                    gamePanel.benda[gamePanel.currentMap][index] = null;
                    break;
                case "NasiKari": 
                    hasNasiKari++;
                    gamePanel.benda[gamePanel.currentMap][index] = null;
                    break;
                case "SusuKacang": 
                    hasSusuKacang++;
                    gamePanel.benda[gamePanel.currentMap][index] = null;
                    break;
                case "TumisSayur": 
                    hasTumisSayur++;
                    gamePanel.benda[gamePanel.currentMap][index] = null;
                    break;
                // furnitur
                // ....
            }
        }
    }

    public void interactNPC(int i) {
        if (i != 999) {
            if (gamePanel.keyHandler.enterPressed) {
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.npc[gamePanel.currentMap][i].speak();
            }
        }
        gamePanel.keyHandler.enterPressed = false;
    }

    public void draw(Graphics2D g2d) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNumber == 1) {
                    image = up1;
                } else {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNumber == 1) {
                    image = down1;
                } else {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNumber == 1) {
                    image = left1;
                } else {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNumber == 1) {
                    image = right1;
                } else {
                    image = right2;
                }
                break;
        }

        g2d.drawImage(image, screenX, screenY, null);

        // g2d.setColor(Color.red);
        // g2d.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);
    }
}
