package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

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
    public boolean lightUpdated = false;
    public Benda currentLight;

    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;

    public Rumah rumah;


    public Sim(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel);

        rumah = new Rumah();

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
        // barang yang bisa dimiliki
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
        // inventory.add(new Furnitur_Jam());
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
        if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed || keyHandler.enterPressed) {
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
            int bendaIndex = gamePanel.collisionChecker.checkBenda(this, true); // return the index of the benda that theplayer is colliding with
            pickUpObject(bendaIndex);                                                                                

            // check npc collision
            int npcIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.npc);
            interactNPC(npcIndex);

            // check event
            gamePanel.eventHandler.checkEvent();

            // after checking all turn of enterPressed
            gamePanel.keyHandler.enterPressed = false;

            // if there is a collision, sim can't move
            if (!collisionOn && !keyHandler.enterPressed) {
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
            // String text; TODO: tambah message di layar
            if (!(gamePanel.benda[gamePanel.currentMap][index] instanceof Furnitur || gamePanel.benda[gamePanel.currentMap][index] instanceof Rumah)){
                if (canObtainItem(gamePanel.benda[gamePanel.currentMap][index])){
                    // text = "kamu mendapatkan " + gamePanel.benda[gamePanel.currentMap][index].nama;
                } else {
                    // text = "Inventory penuh";
                } 
                // gamePanel.ui.addMessage(text);
                gamePanel.benda[gamePanel.currentMap][index] = null;
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
    }


    public void selectItem(){
        int itemIndex = UI.getItemIndexOnSlot(gamePanel.ui.simSlotRow, gamePanel.ui.simSlotCol);

        if (itemIndex < inventory.size()){
            Benda selectedBenda = inventory.get(itemIndex);

            if (selectedBenda instanceof BahanMakanan) {
                BahanMakanan bahanMakanan = (BahanMakanan) selectedBenda;
                bahanMakanan.eat(this);
                if (bahanMakanan.quantity > 1){
                    bahanMakanan.quantity--;
                } else {
                    inventory.remove(itemIndex);
                }
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.ui.currentDialog = "Anda memakan " + bahanMakanan.name + ".\n" + "Kekenyangan bertambah " + bahanMakanan.kekenyangan + " poin.\nSehingga kekenyangan anda sekarang\nadalah " + kekenyangan + " poin.";
            } else if (selectedBenda instanceof Makanan) {
                Makanan makanan = (Makanan) selectedBenda;
                makanan.eat(this);
                if (makanan.quantity > 1){
                    makanan.quantity--;
                } else {
                    inventory.remove(itemIndex);
                }
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.ui.currentDialog = "Anda memakan " + makanan.name + ".\n" + "Kekenyangan bertambah " + makanan.kekenyangan + " poin.\nSehingga kekenyangan anda sekarang\nadalah " + kekenyangan + " poin.";
            } else if (selectedBenda instanceof Furnitur) {
                // TODO : selesaikann
                // Furnitur furnitur = (Furnitur) selectedBenda;
                // furnitur.use(this);
                // inventory.remove(itemIndex);
            } else if (selectedBenda instanceof Lampu){
                if (selectedBenda == currentLight){ // lampu menyala
                    currentLight = null; // lampu mati
                } else {
                    currentLight = selectedBenda; // nyalakan lampu yang dipilih
                }
                lightUpdated = true;
            }
        }
    }


    public int searchItemInInventory(String itemName){
        int itemIndex = 999;

        for (Benda benda : inventory){
            if (benda.name.equals(itemName)){
                itemIndex = inventory.indexOf(benda);
                break;
            }
        }
        return itemIndex;
    }

    public boolean canObtainItem(Benda item){
        boolean canObtain = false;

        // lampu pada inventory hanya boleh ada 1
        if (item instanceof Lampu){
            int index = searchItemInInventory(item.name);
            if (index != 999){ // item terdapat di inventory

            } else {
                if (inventory.size() < maxInventorySize){
                    inventory.add(item);
                    canObtain = true;
                }
            }
        }

        // check if item is stackable
        else if(item.stackable){
            int index = searchItemInInventory(item.name);
            if (index != 999){
                inventory.get(index).quantity++;
                canObtain = true;
            } else {
                if (inventory.size() < maxInventorySize){
                    inventory.add(item);
                    canObtain = true;
                }
            }
        } else { // not stackable
            if (inventory.size() < maxInventorySize){
                inventory.add(item);
                canObtain = true;
            }
        }

        return canObtain;
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
