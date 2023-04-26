package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

import benda.*;
import entity.*;

public class UI {
    GamePanel gamePanel;
    Graphics2D g2d;

    Font maruMonica, purisaB;

    String inputFirstSimName = "";

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialog = "";
    public int commandNumber = 0;
    public int titleScreenState = 0; // 0 = first screen, 1 = second screen, 2 = third screen
    public int simSlotCol = 0, simSlotRow = 0; // default slot position
    public int npcSlotCol = 0, npcSlotRow = 0; // default slot position for npc
    public int subState = 0;
    public int counter = 0;

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        try {
            maruMonica = Font.createFont(Font.TRUETYPE_FONT,
                    new FileInputStream(new File("data/fonts/x12y16pxMaruMonica.ttf")));
            purisaB = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("data/fonts/Purisa Bold.ttf")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showMessage(String message) {
        this.message = message;
        messageOn = true;
    }

    public void draw(Graphics2D g2d) {
        this.g2d = g2d;

        g2d.setFont(maruMonica);
        g2d.setColor(Color.WHITE);

        if (gamePanel.gameState == gamePanel.titleState) { // if game is in title
            drawTitleScreen();
        } else if (gamePanel.gameState == gamePanel.playState) { // if game is playing
            // later
        } else if (gamePanel.gameState == gamePanel.pauseState) { // if game is paused
            drawPauseScreen();
        } else if (gamePanel.gameState == gamePanel.dialogState) { // if game is in dialog
            drawDialogScreen();
        } else if (gamePanel.gameState == gamePanel.simInfoState) { // if game is in sim info
            drawSimInfoScreen();
        } else if (gamePanel.gameState == gamePanel.inventoryState) { // if game is in inventory
            drawInventoryScreen(gamePanel.sim, simSlotCol, simSlotRow);
        } else if (gamePanel.gameState == gamePanel.beliState) { // if game is in beli
            drawBeliScreen();
        } else if (gamePanel.gameState == gamePanel.menuState) {
            drawMenuScreen();
        } else if (gamePanel.gameState == gamePanel.helpState) {
            drawHelpScreen();
        }
    }

    // TITLE SCREEN
    public void drawTitleScreen() {
        if (titleScreenState == 0) {
            // background
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);

            // TITLE NAME
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 96f));
            String title = "SIM-PLICITY";
            int x = getXforCenteredText(title);
            int y = gamePanel.tileSize * 4 + 10;

            // shadow
            g2d.setColor(Color.GRAY);
            g2d.drawString(title, x + 5, y + 5);
            // main color
            g2d.setColor(Color.BLACK);
            g2d.drawString(title, x, y);

            // small-logo image
            BufferedImage logo = null;
            try {
                logo = ImageIO.read(new FileInputStream(new File("assets/logo/small-logo.png")));
                // resize
                logo = UtilityTool.resizeImage(logo, logo.getWidth() / 4, logo.getHeight() / 4);
                // draw in center
                x = gamePanel.screenWidth / 2 - logo.getWidth() / 2;
                y += gamePanel.tileSize;
                g2d.drawImage(logo, x, y, null);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // MENU
            g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 48f));

            String text = "NEW GAME";
            x = getXforCenteredText(text);
            y += logo.getHeight() + gamePanel.tileSize * 3 - 35;
            g2d.drawString(text, x, y);
            if (commandNumber == 0) {
                g2d.drawString(">", x - gamePanel.tileSize, y);
            }

            text = "LOAD GAME";
            x = getXforCenteredText(text);
            y += gamePanel.tileSize;
            g2d.drawString(text, x, y);
            if (commandNumber == 1) {
                g2d.drawString(">", x - gamePanel.tileSize, y);
            }

            text = "EXIT";
            x = getXforCenteredText(text);
            y += gamePanel.tileSize;
            g2d.drawString(text, x, y);
            if (commandNumber == 2) {
                g2d.drawString(">", x - gamePanel.tileSize, y);
            }
        } else if (titleScreenState == 1) {
            updateTitleScreen1Input(inputFirstSimName);
        }
    }

    public void updateTitleScreen1Input(String input) {
        // first sim name input
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 42f));

        String text = "Masukkan nama sim anda:";
        int x = getXforCenteredText(text);
        int y = gamePanel.tileSize * 4 + 10;
        g2d.drawString(text, x, y);
        text = "(maks. 25 karakter)";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize;
        g2d.drawString(text, x, y);

        // meminta input nama karakter
        g2d.fillRect(170, y + gamePanel.tileSize, gamePanel.screenWidth - 2 * 170, gamePanel.tileSize);
        Graphics2D g2d2 = (Graphics2D) g2d.create();
        g2d2.setColor(Color.BLACK);
        g2d2.setFont(g2d2.getFont().deriveFont(Font.PLAIN, 37f));
        g2d2.drawString(input, 183, y + gamePanel.tileSize * 2 - 10);
        text = "Oke";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize * 3;
        g2d.drawString(text, x, y);
        if (commandNumber == 0) {
            g2d.drawString(">", x - gamePanel.tileSize, y);
        }

        text = "Back";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize * 2;
        g2d.drawString(text, x, y);
        if (commandNumber == 1) {
            g2d.drawString(">", x - gamePanel.tileSize, y);
        }
    }

    // PAUSE SCREEN
    public void drawPauseScreen() {
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 80f));

        String message = "PAUSED";

        int x = getXforCenteredText(message);
        int y = gamePanel.screenHeight / 2;

        g2d.drawString(message, x, y);
    }

    // DIALOG SCREEN
    public void drawDialogScreen() {
        // WINDOW
        int x = gamePanel.tileSize * 2;
        int y = gamePanel.tileSize / 2;
        int width = gamePanel.screenWidth - gamePanel.tileSize * 4;
        int height = gamePanel.tileSize * 4;
        drawSubWindow(x, y, width, height);

        // TEXT
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 32f));
        x += gamePanel.tileSize;
        y += gamePanel.tileSize;
        for (String line : currentDialog.split("\n")) {
            g2d.drawString(line, x, y);
            y += g2d.getFontMetrics().getHeight();
        }
    }

    // SIM INFO SCREEN
    public void drawSimInfoScreen() {
        // CREATE A FRAME
        final int lineHeight = 35;
        final int frameX = gamePanel.tileSize * 2;
        final int frameY = gamePanel.tileSize;
        final int frameWidth = gamePanel.tileSize * 12;
        final int frameHeight = frameY + gamePanel.tileSize + lineHeight * 6;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // TEXT
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(40f));

        // SIM INFO
        final int textXSIMINFO = getXforCenteredText("SIM INFO");
        int textYSIMINFO = frameY + gamePanel.tileSize;
        g2d.drawString("SIM INFO", textXSIMINFO, textYSIMINFO);

        // SIM INFO TEXT
        g2d.setFont(g2d.getFont().deriveFont(32f));
        int textX = frameX + 20;
        int textY = textYSIMINFO + lineHeight + 20;
        g2d.drawString("Nama", textX, textY);
        textY += lineHeight;
        g2d.drawString("Pekerjaan", textX, textY);
        textY += lineHeight;
        g2d.drawString("Uang", textX, textY);
        textY += lineHeight;
        g2d.drawString("Kesehatan", textX, textY);
        textY += lineHeight;
        g2d.drawString("Kekenyangan", textX, textY);
        textY += lineHeight;
        g2d.drawString("Mood", textX, textY);

        // SIM INFO VALUE
        int tailX = frameX + frameWidth / 3;
        textY = textYSIMINFO + lineHeight + 20;
        String value;
        g2d.drawString(" : " + gamePanel.sim.nama, tailX, textY);
        textY += lineHeight;
        g2d.drawString(" : " + gamePanel.sim.pekerjaan, tailX, textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.sim.uang);
        g2d.drawString(" : " + value, tailX, textY);
        textY += lineHeight;
        value = String.valueOf(" : " + gamePanel.sim.kesehatan + "/" + gamePanel.sim.maxKesehatan);
        g2d.drawString(value, tailX, textY);
        textY += lineHeight;
        value = String.valueOf(" : " + gamePanel.sim.kekenyangan + "/" + gamePanel.sim.maxKekenyangan);
        g2d.drawString(value, tailX, textY);
        textY += lineHeight;
        value = String.valueOf(" : " + gamePanel.sim.mood + "/" + gamePanel.sim.maxMood);
        g2d.drawString(value, tailX, textY);
    }

    // INVENTORY SCREEN
    public void drawInventoryScreen(Entity entity, int slotCol, int slotRow) {
        // create frame
        int frameX = gamePanel.tileSize * 2;
        int frameY = gamePanel.tileSize;
        int frameWidth = gamePanel.screenWidth - gamePanel.tileSize * 4;
        int frameHeight = gamePanel.screenHeight - gamePanel.tileSize * 11;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // text
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(40f));

        // inventory title
        int textYINVENTORY = frameY + gamePanel.tileSize;
        if (entity instanceof Sim) {
            // for sim
            final int textXINVENTORY = getXforCenteredText("INVENTORY");
            g2d.drawString("INVENTORY", textXINVENTORY, textYINVENTORY);
        } else if (entity instanceof NPC_Penjual) {
            // for penjual
            final int textXINVENTORY = getXforCenteredText("SHOP");
            g2d.drawString("SHOP", textXINVENTORY, textYINVENTORY);
        }

        // slot
        final int slotXstart = frameX + 19;
        final int slotYstart = textYINVENTORY + 19;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gamePanel.tileSize + 1;

        // draw entity items
        for (int i = 0; i < entity.inventory.size(); i++) {
            // equip cursor
            if (entity.inventory.get(i) == gamePanel.sim.currentLight) {
                g2d.setColor(new Color(240, 190, 90));
                g2d.fillRoundRect(slotX, slotY, gamePanel.tileSize, gamePanel.tileSize, 10, 10);
            }

            g2d.drawImage(entity.inventory.get(i).image, slotX, slotY, gamePanel.tileSize, gamePanel.tileSize,
                    null);

            // display amount
            if (entity == gamePanel.sim && entity.inventory.get(i).quantity > 1) {
                g2d.setFont(g2d.getFont().deriveFont(32f));
                int amountX, amountY;

                String s = "" + entity.inventory.get(i).quantity;
                amountX = getXforAlignToRightText(s, slotX + 44);
                amountY = slotY + gamePanel.tileSize;

                // shadow
                g2d.setColor(new Color(60, 60, 60));
                g2d.drawString(s, amountX, amountY);
                // number
                g2d.setColor(Color.WHITE);
                g2d.drawString(s, amountX - 3, amountY - 3);
            }

            slotX += slotSize;
            // jika kelipatan 11 maka pindah ke baris bawah
            if (i % 11 == 10) {
                slotX = slotXstart;
                slotY += slotSize;
            }
        }

        // cursor
        int cursorX = slotXstart + (slotSize * slotCol);
        int cursorY = slotYstart + (slotSize * slotRow);
        int cursorWidth = gamePanel.tileSize;
        int cursorHeight = gamePanel.tileSize;
        // draw cursor
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(3)); // ngubah ukuran stroke
        g2d.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

        // item info
        int iFrameX = frameX;
        int iFrameY = frameY + frameHeight + 10;
        int iFrameWidth = frameWidth;
        int iFrameHeight = gamePanel.tileSize * 6 - 20;
        drawSubWindow(iFrameX, iFrameY, iFrameWidth, iFrameHeight);

        // ITEM INFO TITLE
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(40f));
        final int textXITEMINFO = getXforCenteredText("ITEM INFO");
        int textYITEMINFO = iFrameY + gamePanel.tileSize;
        g2d.drawString("ITEM INFO", textXITEMINFO, textYITEMINFO);

        // draw item info
        int lineHeight = 35;
        int textX = iFrameX + 20;
        int textY = textYITEMINFO + lineHeight + 20;
        g2d.setFont(g2d.getFont().deriveFont(32f));

        int itemIndex = getItemIndexOnSlot(slotRow, slotCol);
        if (itemIndex < entity.inventory.size()) {
            if (entity.inventory.get(itemIndex) != null) {
                if (entity.inventory.get(itemIndex) instanceof BahanMakanan) {
                    // write item info
                    g2d.drawString("Nama", textX, textY);
                    textY += lineHeight;
                    g2d.drawString("Kategori", textX, textY);
                    textY += lineHeight;
                    g2d.drawString("Harga", textX, textY);
                    textY += lineHeight;
                    g2d.drawString("Kekenyangan", textX, textY);
                    // write item value
                    int iTailX = iFrameX + iFrameWidth / 3;
                    textY = textYITEMINFO + lineHeight + 20;
                    String iValue;
                    BahanMakanan bahanMakanan = (BahanMakanan) entity.inventory.get(itemIndex);
                    g2d.drawString(" : " + bahanMakanan.name, iTailX, textY);
                    textY += lineHeight;
                    g2d.drawString(" : " + bahanMakanan.category, iTailX, textY);
                    textY += lineHeight;
                    iValue = String.valueOf(" : " + bahanMakanan.harga);
                    g2d.drawString(iValue, iTailX, textY);
                    textY += lineHeight;
                    iValue = String.valueOf(" : " + bahanMakanan.kekenyangan);
                    g2d.drawString(iValue, iTailX, textY);

                    textY = textYITEMINFO + lineHeight + 20;
                } else if (entity.inventory.get(itemIndex) instanceof Makanan) {
                    // write item info
                    g2d.drawString("Nama", textX, textY);
                    textY += lineHeight;
                    g2d.drawString("Kategori", textX, textY);
                    textY += lineHeight;
                    g2d.drawString("Bahan", textX, textY);
                    textY += lineHeight;
                    g2d.drawString("Kekenyangan", textX, textY);
                    // write item value
                    int iTailX = iFrameX + iFrameWidth / 3;
                    textY = textYITEMINFO + lineHeight + 20;
                    String iValue;
                    Makanan makanan = (Makanan) entity.inventory.get(itemIndex);
                    g2d.drawString(" : " + makanan.name, iTailX, textY);
                    textY += lineHeight;
                    g2d.drawString(" : " + makanan.category, iTailX, textY);
                    textY += lineHeight;
                    iValue = String.valueOf(" : " + makanan.bahan);
                    g2d.drawString(iValue, iTailX, textY);
                    textY += lineHeight;
                    iValue = String.valueOf(" : " + makanan.kekenyangan);
                    g2d.drawString(iValue, iTailX, textY);

                    textY = textYITEMINFO + lineHeight + 20;
                } else if (entity.inventory.get(itemIndex) instanceof Furnitur) {
                    // write item info
                    g2d.drawString("Nama", textX, textY);
                    textY += lineHeight;
                    g2d.drawString("Kategori", textX, textY);
                    textY += lineHeight;
                    g2d.drawString("Dimensi", textX, textY);
                    textY += lineHeight;
                    g2d.drawString("Harga", textX, textY);
                    textY += lineHeight;
                    g2d.drawString("Aksi", textX, textY);
                    // write item value
                    int iTailX = iFrameX + iFrameWidth / 3;
                    textY = textYITEMINFO + lineHeight + 20;
                    String iValue;
                    Furnitur furnitur = (Furnitur) entity.inventory.get(itemIndex);
                    g2d.drawString(" : " + furnitur.name, iTailX, textY);
                    textY += lineHeight;
                    g2d.drawString(" : " + furnitur.category, iTailX, textY);
                    textY += lineHeight;
                    iValue = String.valueOf(" : " + furnitur.dimensiX + "x" + furnitur.dimensiY);
                    g2d.drawString(iValue, iTailX, textY);
                    textY += lineHeight;
                    iValue = String.valueOf(" : " + furnitur.harga);
                    g2d.drawString(iValue, iTailX, textY);
                    textY += lineHeight;
                    iValue = String.valueOf(" : " + furnitur.aksi);
                    g2d.drawString(iValue, iTailX, textY);

                    textY = textYITEMINFO + lineHeight + 20;
                } else if (entity.inventory.get(itemIndex) instanceof Lampu) {
                    // write item info
                    g2d.drawString("Nama", textX, textY);
                    textY += lineHeight;
                    g2d.drawString("Kategori", textX, textY);
                    textY += lineHeight;
                    g2d.drawString("Harga", textX, textY);
                    textY += lineHeight;
                    g2d.drawString("Aksi", textX, textY);
                    // write item value
                    int iTailX = iFrameX + iFrameWidth / 3;
                    textY = textYITEMINFO + lineHeight + 20;
                    String iValue;
                    Lampu lampu = (Lampu) entity.inventory.get(itemIndex);
                    g2d.drawString(" : " + lampu.name, iTailX, textY);
                    textY += lineHeight;
                    g2d.drawString(" : " + lampu.category, iTailX, textY);
                    textY += lineHeight;
                    iValue = String.valueOf(" : " + lampu.harga);
                    g2d.drawString(iValue, iTailX, textY);
                    textY += lineHeight;
                    iValue = String.valueOf(" : " + lampu.aksi);
                    g2d.drawString(iValue, iTailX, textY);

                    textY = textYITEMINFO + lineHeight + 20;
                }
            }
        }
    }

    public void drawBeliScreen() {
        switch (subState) {
            case 0:
                drawBeliSelect();
                break;
            case 1:
                drawBeliBuy();
                break;
        }
        gamePanel.keyHandler.enterPressed = false;
    }

    public void drawBeliSelect() {
        drawDialogScreen();

        // DRAW WINDOW
        int x = gamePanel.tileSize * 11;
        int y = gamePanel.tileSize * 4;
        int width = gamePanel.tileSize * 3;
        int height = (int) (gamePanel.tileSize * 2.5);
        drawSubWindow(x, y, width, height);

        // DRAW TEXTS
        x += gamePanel.tileSize;
        y += gamePanel.tileSize;
        g2d.drawString("Buy", x, y);
        if (commandNumber == 0) {
            g2d.drawString(">", x - gamePanel.tileSize / 2, y);
            if (gamePanel.keyHandler.enterPressed) {
                subState = 1;
            }
        }
        y += gamePanel.tileSize;
        g2d.drawString("Leave", x, y);
        if (commandNumber == 1) {
            g2d.drawString(">", x - gamePanel.tileSize / 2, y);
            if (gamePanel.keyHandler.enterPressed) {
                commandNumber = 0;
                gamePanel.gameState = gamePanel.playState;
            }
        }
    }

    public void drawBeliBuy() {
        drawInventoryScreen(gamePanel.npc[0][4], npcSlotCol, npcSlotRow);

        // draw hint window
        int x = gamePanel.tileSize * 2;
        int y = gamePanel.tileSize * 13;
        int width = gamePanel.tileSize * 6;
        int height = gamePanel.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2d.drawString("[ESC/T] Back", x + 24, y + 60);

        // draw sim uang window
        x = gamePanel.tileSize * 8;
        y = gamePanel.tileSize * 13;
        width = gamePanel.tileSize * 6;
        height = gamePanel.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2d.drawString("Sim Uang : " + gamePanel.sim.uang, x + 24, y + 60);

        // buy an item
        int itemIndex = getItemIndexOnSlot(npcSlotRow, npcSlotCol);
        if (itemIndex < gamePanel.npc[0][4].inventory.size()) {
            if (gamePanel.keyHandler.enterPressed) {
                if (gamePanel.npc[0][4].inventory.get(itemIndex) instanceof BahanMakanan) {
                    BahanMakanan makanan = (BahanMakanan) gamePanel.npc[0][4].inventory.get(itemIndex);
                    if (gamePanel.sim.uang >= makanan.harga) {
                        if (gamePanel.sim.canObtainItem(makanan)) {
                            gamePanel.sim.uang -= makanan.harga;
                        } else {
                            subState = 0;
                            gamePanel.gameState = gamePanel.dialogState;
                            currentDialog = "Inventory penuh";
                        }
                    } else {
                        subState = 0;
                        gamePanel.gameState = gamePanel.dialogState;
                        currentDialog = "Uang tidak cukup";
                        drawDialogScreen();
                    }
                } else if (gamePanel.npc[0][4].inventory.get(itemIndex) instanceof Furnitur) {
                    Furnitur furnitur = (Furnitur) gamePanel.npc[0][4].inventory.get(itemIndex);
                    if (gamePanel.sim.uang >= furnitur.harga) {
                        if (gamePanel.sim.canObtainItem(furnitur)) {
                            gamePanel.sim.uang -= furnitur.harga;
                        } else {
                            subState = 0;
                            gamePanel.gameState = gamePanel.dialogState;
                            currentDialog = "Inventory penuh";
                        }
                    } else {
                        subState = 0;
                        gamePanel.gameState = gamePanel.dialogState;
                        currentDialog = "Uang tidak cukup";
                        drawDialogScreen();
                    }
                } else if (gamePanel.npc[0][4].inventory.get(itemIndex) instanceof Lampu) {
                    Lampu lampu = (Lampu) gamePanel.npc[0][4].inventory.get(itemIndex);
                    if (gamePanel.sim.uang >= lampu.harga) {
                        if (gamePanel.sim.canObtainItem(lampu)) {
                            gamePanel.sim.uang -= lampu.harga;
                        } else {
                            if (gamePanel.sim.inventory.size() >= gamePanel.sim.maxInventorySize) {
                                subState = 0;
                                gamePanel.gameState = gamePanel.dialogState;
                                currentDialog = "Inventory penuh";
                            } else {
                                subState = 0;
                                gamePanel.gameState = gamePanel.dialogState;
                                currentDialog = "Anda sudah memiliki lampu!";
                            }
                        }
                    } else {
                        subState = 0;
                        gamePanel.gameState = gamePanel.dialogState;
                        currentDialog = "Uang tidak cukup";
                        drawDialogScreen();
                    }
                }
            }
        }

    }

    // Menu SCREEN
    public void drawMenuScreen() {
        // create frame
        int frameX = gamePanel.tileSize * 4;
        int frameY = gamePanel.tileSize * 3;
        int frameWidth = gamePanel.screenWidth - gamePanel.tileSize * 8;
        int frameHeight = gamePanel.screenHeight - gamePanel.tileSize * 6;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // title
        g2d.setFont(g2d.getFont().deriveFont(32f));
        String text = "OPTIONS";
        int x = getXforCenteredText(text);
        int y = gamePanel.tileSize * 4;
        g2d.setColor(Color.WHITE);
        g2d.drawString(text, x, y);

        // texts
        g2d.setFont(g2d.getFont().deriveFont(32f));
        x = gamePanel.tileSize * 5;
        text = "Music";
        y += gamePanel.tileSize * 2;
        g2d.drawString(text, x, y);
        if (commandNumber == 0) {
            int xRect = x + gamePanel.tileSize * 4;
            int yRect = y - 24;
            g2d.drawString(">", x - gamePanel.tileSize / 2, y);
            g2d.drawRect(xRect, yRect, 120, 24);
            int volumeWidth = 24 * gamePanel.music.volumeScale;
            g2d.fillRect(xRect, yRect, volumeWidth, 24);
            if (gamePanel.keyHandler.enterPressed) {
                commandNumber = 0;
                gamePanel.gameState = gamePanel.playState;
            }
        }

        text = "Sound Effect";
        y += gamePanel.tileSize;
        g2d.drawString(text, x, y);
        if (commandNumber == 1) {
            int xRect = x + gamePanel.tileSize * 4;
            int yRect = y - 24;
            g2d.drawString(">", x - gamePanel.tileSize / 2, y);
            g2d.drawRect(xRect, yRect, 120, 24);
            int volumeWidth = 24 * gamePanel.soundEffect.volumeScale;
            g2d.fillRect(xRect, yRect, volumeWidth, 24);
            if (gamePanel.keyHandler.enterPressed) {
                commandNumber = 0;
                gamePanel.gameState = gamePanel.playState;
            }
        }

        text = "Help";
        y += gamePanel.tileSize;
        g2d.drawString(text, x, y);
        if (commandNumber == 2) {
            g2d.drawString(">", x - gamePanel.tileSize / 2, y);
            if (gamePanel.keyHandler.enterPressed) {
                commandNumber = 0;
                gamePanel.gameState = gamePanel.playState;
            }
        }

        text = "Save";
        y += gamePanel.tileSize;
        g2d.drawString(text, x, y);
        if (commandNumber == 3) {
            g2d.drawString(">", x - gamePanel.tileSize / 2, y);
            if (gamePanel.keyHandler.enterPressed) {
                commandNumber = 0;
                gamePanel.gameState = gamePanel.playState;
            }
        }

        text = "Main Menu";
        y += gamePanel.tileSize;
        g2d.drawString(text, x, y);
        if (commandNumber == 4) {
            g2d.drawString(">", x - gamePanel.tileSize / 2, y);
            if (gamePanel.keyHandler.enterPressed) {
                commandNumber = 0;
                gamePanel.gameState = gamePanel.playState;
            }
        }

        text = "Back";
        y += gamePanel.tileSize * 2;
        g2d.drawString(text, x, y);
        if (commandNumber == 5) {
            g2d.drawString(">", x - gamePanel.tileSize / 2, y);
            if (gamePanel.keyHandler.enterPressed) {
                commandNumber = 0;
                gamePanel.gameState = gamePanel.playState;
            }
        }
    }

    public void drawHelpScreen() {
        // create frame
        int frameX = gamePanel.tileSize * 4;
        int frameY = gamePanel.tileSize * 3;
        int frameWidth = gamePanel.screenWidth - gamePanel.tileSize * 8;
        int frameHeight = gamePanel.screenHeight - gamePanel.tileSize * 6;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // title
        g2d.setFont(g2d.getFont().deriveFont(32f));
        String text = "HELP";
        int x = getXforCenteredText(text);
        int y = gamePanel.tileSize * 4;
        g2d.setColor(Color.WHITE);
        g2d.drawString(text, x, y);

        // texts
        g2d.setFont(g2d.getFont().deriveFont(32f));
        x = gamePanel.tileSize * 5;
        text = "T: Melihat Waktu";
        y += gamePanel.tileSize * 2;
        g2d.drawString(text, x, y);

        text = "U: Upgrade Rumah";
        y += gamePanel.tileSize;
        g2d.drawString(text, x, y);

        text = "I: Melihat Inventory";
        y += gamePanel.tileSize;
        g2d.drawString(text, x, y);

        text = "P: Pause";
        y += gamePanel.tileSize;
        g2d.drawString(text, x, y);

        text = "L: Melihat Lokasi";
        y += gamePanel.tileSize;
        g2d.drawString(text, x, y);

        text = "C: Melihat Sim Info";
        y += gamePanel.tileSize;
        g2d.drawString(text, x, y);

        text = "B: Beli Barang";
        y += gamePanel.tileSize;
        g2d.drawString(text, x, y);
    }

    public static int getItemIndexOnSlot(int slotRow, int slotCol) {
        int index = slotRow * 11 + slotCol;
        return index;
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        // grey window
        g2d.setColor(new Color(0, 0, 0, 210));
        g2d.fillRoundRect(x, y, width, height, 35, 35);

        // white border
        g2d.setColor(new Color(255, 255, 255));
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public int getXforCenteredText(String message) {
        int length = (int) g2d.getFontMetrics().getStringBounds(message, g2d).getWidth();

        int x = gamePanel.screenWidth / 2 - length / 2;

        return x;
    }

    public int getXforAlignToRightText(String message, int tailX) {
        int length = (int) g2d.getFontMetrics().getStringBounds(message, g2d).getWidth();

        int x = tailX - length;

        return x;
    }
}
