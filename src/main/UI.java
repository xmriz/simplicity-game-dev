package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import benda.*;
import entity.*;
// import map.Map;

public class UI {
    GamePanel gamePanel;
    Graphics2D g2d;

    Font maruMonica;

    String inputFirstSimName = "";

    String inputNamaRuangan = "";

    String inputText = "";

    boolean inputTextDone = false;

    public int durasiTimer = 0;
    public int tempDurasi = 0;

    public String currentAksi = "";
    public String currentAksiCadangan = "";
    public boolean currentAksiDone = false;

    public boolean messageOn = false;
    // public String message = "";
    // int messageCounter = 0;
    ArrayList <String> message = new ArrayList<> ();
    ArrayList <Integer> messageCounter = new ArrayList<> ();

    public boolean gameFinished = false;
    public String currentDialog = "";
    public int commandNumber = 0;
    public int titleScreenState = 0; // 0 = first screen, 1 = second screen, 2 = third screen
    public int simSlotCol = 0, simSlotRow = 0; // default slot position
    public int npcSlotCol = 0, npcSlotRow = 0; // default slot position for npc
    public int listSimSlotCol = 0, listSimSlotRow = 0; // default slot position for list sim
    public int kokiSlotCol = 0, kokiSlotRow = 0; // default slot position for koki
    public int subState = 0;
    public int counter = 0;
    public int charIndex = 0;
    public String combinedText = "";
    String currentDialog2 = "";

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        try {
            maruMonica = Font.createFont(Font.TRUETYPE_FONT,
                    new FileInputStream(new File("data/fonts/x12y16pxMaruMonica.ttf")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addMessage(String message) {
        this.message.add(message);
        messageCounter.add(0);
    }

    public void draw(Graphics2D g2d) {
        this.g2d = g2d;

        g2d.setFont(maruMonica);
        g2d.setColor(Color.WHITE);

        // if (messageOn == true) {
            
        // }

        if (gamePanel.gameState == gamePanel.titleState) { // if game is in title
            drawTitleScreen();
        } else if (gamePanel.gameState == gamePanel.playState) { // if game is playing
            // later
            drawMiniMap(g2d);
            // drawMessage();
        } else if (gamePanel.gameState == gamePanel.pauseState) { // if game is paused
            drawPauseScreen();
        } else if (gamePanel.gameState == gamePanel.dialogState) { // if game is in dialog
            drawDialogScreen();
        } else if (gamePanel.gameState == gamePanel.simInfoState) { // if game is in sim info
            drawSimInfoScreen();
        } else if (gamePanel.gameState == gamePanel.inventoryState) { // if game is in inventory
            drawInventoryScreen(gamePanel.listSim.get(gamePanel.indexCurrentSim), simSlotCol, simSlotRow);
        } else if (gamePanel.gameState == gamePanel.beliState) { // if game is in beli
            drawBeliScreen();
        } else if (gamePanel.gameState == gamePanel.upgradeRumahState) { // if game is in upgrade rumah
            drawUpgradeRumahScreen();
        } else if (gamePanel.gameState == gamePanel.inputNamaRuanganState) { // if game is in input nama ruangan
            drawInputNamaRuanganScreen("Input nama:");
        } else if (gamePanel.gameState == gamePanel.inputKoordinatBendaState) { // if game is in input koordinat
            drawInputKoordinatBendaScreen("Input koordinat:");
        } else if (gamePanel.gameState == gamePanel.addSimState) {
            drawInputSimNameScreen("Input nama:");
        } else if (gamePanel.gameState == gamePanel.inputKoordinatRumahSimState) {
            drawInputKoordinatRumahSimScreen("Input koordinat:");
        } else if (gamePanel.gameState == gamePanel.menuState) {
            drawMenuScreen();
        } else if (gamePanel.gameState == gamePanel.helpState) {
            drawHelpScreen();
        } else if (gamePanel.gameState == gamePanel.changeSimState) {
            drawChangeSimScreen();
        } else if (gamePanel.gameState == gamePanel.mapState) {
            drawFullMapScreen(g2d);
        } else if (gamePanel.gameState == gamePanel.resepState) {
            drawResepScreen();
        } else if (gamePanel.gameState == gamePanel.inputDurasiTidurState) {
            drawInputDurasiTidurScreen("Input Durasi Tidur:");
        } else if (gamePanel.gameState == gamePanel.inputDurasiNontonState) {
            drawInputDurasiNontonScreen("Input Durasi Nonton:");
        } else if (gamePanel.gameState == gamePanel.inputDurasiMandiState) {
            drawInputDurasiMandiScreen("Input Durasi Mandi:");
        } else if (gamePanel.gameState == gamePanel.inputDurasiBacaBukuState) {
            drawInputDurasiBacaBukuScreen("Input Durasi Baca: ");
        } else if (gamePanel.gameState == gamePanel.inputDurasiShalatState) {
            drawInputDurasiShalatScreen("Input Durasi Shalat: ");
        } else if (gamePanel.gameState == gamePanel.inputDurasiRadioState) {
            drawInputDurasiRadioScreen("Input Durasi Radio:");
        } else if (gamePanel.gameState == gamePanel.timerState) {
            drawTimerScreen();
        } else if (gamePanel.gameState == gamePanel.gameOverState) {
            drawGameOverScreen();
        } else if (gamePanel.gameState == gamePanel.inputDurasiSiramTanamanState) {
            drawInputDurasiSiramTanamanScreen("Input Durasi Siram Tanaman:");
        } else if (gamePanel.gameState == gamePanel.inputDurasiMainGameState) {
            drawInputDurasiMainGameScreen("Input Durasi Main Game:");
        } else if (gamePanel.gameState == gamePanel.kerjaState) {
            drawKerjaScreen("Menu Kerja");
        } else if (gamePanel.gameState == gamePanel.inputDurasiKerjaState) {
            drawInputDurasiKerjaScreen("Input Durasi Kerja:");
        } else if (gamePanel.gameState == gamePanel.gantiPekerjaanState) {
            drawGantiPekerjaanScreen("Pilih Pekerjaan");
        } else if (gamePanel.gameState == gamePanel.saveState) {
            drawSaveScreen();
        } else if (gamePanel.gameState == gamePanel.inputDurasiOlahragaState) {
            drawInputDurasiOlahragaScreen("Input Durasi Olahraga:");
        } else if (gamePanel.gameState == gamePanel.melihatWaktuState){
            drawMelihatWaktuScreen();
        } else if (gamePanel.gameState == gamePanel.cutsceneState){
            // CUTSCENE
            gamePanel.cutsceneManager.draw(g2d);
        }
    }

    // MESSAGE
    public void drawMessage(){
        int messageX = gamePanel.tileSize;
        int messageY = gamePanel.tileSize * 10;
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 25f));

        for (int i = 0; i < message.size(); i++){
            g2d.setColor(Color.WHITE);
            g2d.drawString(message.get(i), messageX, messageY);

            int counter = messageCounter.get(i) + 1;
            messageCounter.set(i, counter);
            messageY += 40;

            if (messageCounter.get(i) > 180 ){
                message.remove(i);
                messageCounter.remove(i);
            }

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
        } else if (titleScreenState == 2) {
            loadScreen();
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

    public void loadScreen() {
        // first sim name input
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 42f));

        String text = "PILIH LOAD FILE";
        int x = getXforCenteredText(text);
        int y = gamePanel.tileSize * 4 + 10;
        g2d.drawString(text, x, y);

        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 40f));
        text = "LOAD FILE 1";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize * 3;
        g2d.drawString(text, x, y);
        if (commandNumber == 0) {
            g2d.drawString(">", x - gamePanel.tileSize, y);
        }

        text = "LOAD FILE 2";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize * 2;
        g2d.drawString(text, x, y);
        if (commandNumber == 1) {
            g2d.drawString(">", x - gamePanel.tileSize, y);
        }

        text = "LOAD FILE 3";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize * 2;
        g2d.drawString(text, x, y);
        if (commandNumber == 2) {
            g2d.drawString(">", x - gamePanel.tileSize, y);
        }

        text = "Back";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize * 2;
        g2d.drawString(text, x, y);
        if (commandNumber == 3) {
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

        char characters[] = currentDialog.toCharArray();

        if (charIndex < characters.length) {
            gamePanel.playSoundEffect(4);
            String s = String.valueOf(characters[charIndex]);
            combinedText = combinedText + s;
            currentDialog2 = combinedText;
            charIndex++;
        }

        for (String line : currentDialog2.split("\n")) {
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
        g2d.drawString(" : " + gamePanel.listSim.get(gamePanel.indexCurrentSim).getNama(), tailX, textY);
        textY += lineHeight;
        g2d.drawString(" : "
                + gamePanel.getCurrentSim().getPekerjaan().listPekerjaan[gamePanel.getCurrentSim().getPekerjaan().indexPekerjaan],
                tailX, textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).getUang());
        g2d.drawString(" : " + value, tailX, textY);
        textY += lineHeight;
        value = String.valueOf(" : " + gamePanel.listSim.get(gamePanel.indexCurrentSim).getKesehatan() + "/"
                + gamePanel.listSim.get(gamePanel.indexCurrentSim).getMaxKesehatan());
        g2d.drawString(value, tailX, textY);
        textY += lineHeight;
        value = String.valueOf(" : " + gamePanel.listSim.get(gamePanel.indexCurrentSim).getKekenyangan() + "/"
                + gamePanel.listSim.get(gamePanel.indexCurrentSim).getMaxKekenyangan());
        g2d.drawString(value, tailX, textY);
        textY += lineHeight;
        value = String.valueOf(" : " + gamePanel.listSim.get(gamePanel.indexCurrentSim).getMood() + "/"
                + gamePanel.listSim.get(gamePanel.indexCurrentSim).getMaxMood());
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
        } else if (entity instanceof NPC_Koki) {
            // for koki
            final int textXINVENTORY = getXforCenteredText("RESEP MAKANAN");
            g2d.drawString("RESEP MAKANAN", textXINVENTORY, textYINVENTORY);
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
            if (entity.inventory.get(i) == gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentLight()) {
                g2d.setColor(new Color(240, 190, 90));
                g2d.fillRoundRect(slotX, slotY, gamePanel.tileSize, gamePanel.tileSize, 10, 10);
            }

            g2d.drawImage(entity.inventory.get(i).getImage(), slotX, slotY, gamePanel.tileSize, gamePanel.tileSize,
                    null);

            // display amount
            if (entity == gamePanel.listSim.get(gamePanel.indexCurrentSim) && entity.inventory.get(i).getQuantity() > 1) {
                g2d.setFont(g2d.getFont().deriveFont(32f));
                int amountX, amountY;

                String s = "" + entity.inventory.get(i).getQuantity();
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
        int iFrameHeight;
        if (entity instanceof NPC_Koki) {
            iFrameHeight = gamePanel.tileSize * 6 - 50;
        } else {
            iFrameHeight = gamePanel.tileSize * 6 - 20;
        }
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
                    g2d.drawString(" : " + bahanMakanan.getName(), iTailX, textY);
                    textY += lineHeight;
                    g2d.drawString(" : " + bahanMakanan.getCategory(), iTailX, textY);
                    textY += lineHeight;
                    iValue = String.valueOf(" : " + bahanMakanan.getHarga());
                    g2d.drawString(iValue, iTailX, textY);
                    textY += lineHeight;
                    iValue = String.valueOf(" : " + bahanMakanan.getKekenyangan());
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
                    g2d.drawString(" : " + makanan.getName(), iTailX, textY);
                    textY += lineHeight;
                    g2d.drawString(" : " + makanan.getCategory(), iTailX, textY);
                    textY += lineHeight;
                    iValue = String.valueOf(" : " + makanan.getBahan());
                    g2d.drawString(iValue, iTailX, textY);
                    textY += lineHeight;
                    iValue = String.valueOf(" : " + makanan.getKekenyangan());
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
                    g2d.drawString(" : " + furnitur.getName(), iTailX, textY);
                    textY += lineHeight;
                    g2d.drawString(" : " + furnitur.getCategory(), iTailX, textY);
                    textY += lineHeight;
                    iValue = String.valueOf(" : " + furnitur.getDimensiX() + "x" + furnitur.getDimensiY());
                    g2d.drawString(iValue, iTailX, textY);
                    textY += lineHeight;
                    iValue = String.valueOf(" : " + furnitur.getHarga());
                    g2d.drawString(iValue, iTailX, textY);
                    textY += lineHeight;
                    iValue = String.valueOf(" : " + furnitur.getAksi());
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
                    g2d.drawString(" : " + lampu.getName(), iTailX, textY);
                    textY += lineHeight;
                    g2d.drawString(" : " + lampu.getCategory(), iTailX, textY);
                    textY += lineHeight;
                    iValue = String.valueOf(" : " + lampu.getHarga());
                    g2d.drawString(iValue, iTailX, textY);
                    textY += lineHeight;
                    iValue = String.valueOf(" : " + lampu.getAksi());
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

    // // fungsi thread buat beli barang
    // public void setIsCanBuyToTrue() {
    // Thread t = new Thread(new Runnable() {
    // @Override
    // public void run() {
    // remainingTimeUpgrade = 18 * 60 * 1000; // waktu mundur dalam detik
    // while (remainingTimeUpgrade > 0) {
    // System.out.println("Waktu tersisa: " + remainingTimeUpgrade + " detik");
    // try {
    // Thread.sleep(1000); // tunggu 1 detik
    // } catch (InterruptedException e) {
    // e.printStackTrace();
    // gamePanel.getCurrentSim().isCanBuy = true; // atur isCanUpgrade menjadi true
    // setelah 18 menit
    // gamePanel.getCurrentSim().remainingTimeBuy = 0;
    // System.out.println("Waktu diskip!");
    // // tambahin fungsi buat beli
    // }
    // remainingTimeUpgrade--;
    // }
    // System.out.println("Waktu habis!");
    // isCanUpgrade = true; // atur isCanUpgrade menjadi true setelah 18 menit
    // // tambahin fungsi buat beli
    // }
    // });
    // t.start(); // mulai thread
    // }

    // -------------------------------

    public void drawBeliBuy() {
        drawInventoryScreen(gamePanel.npc[0][4], npcSlotCol, npcSlotRow);

        // draw hint window
        int x = gamePanel.tileSize * 2;
        int y = gamePanel.tileSize * 13;
        int width = gamePanel.tileSize * 6;
        int height = gamePanel.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2d.drawString("[ESC/B] Back", x + 24, y + 60);

        // draw sim uang window
        x = gamePanel.tileSize * 8;
        y = gamePanel.tileSize * 13;
        width = gamePanel.tileSize * 6;
        height = gamePanel.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2d.drawString("Sim Uang : " + gamePanel.listSim.get(gamePanel.indexCurrentSim).getUang(), x + 24, y + 60);

        // buy an item
        int itemIndex = getItemIndexOnSlot(npcSlotRow, npcSlotCol);
        if (itemIndex < gamePanel.npc[0][4].inventory.size()) {
            if (gamePanel.keyHandler.enterPressed) {
                if (gamePanel.getCurrentSim().getIsCanBuy()) {
                    int harga = 0;
                    if (gamePanel.npc[0][4].inventory.get(itemIndex) instanceof BahanMakanan) {
                        BahanMakanan makanan = (BahanMakanan) gamePanel.npc[0][4].inventory.get(itemIndex);
                        harga = makanan.getHarga();
                    } else if (gamePanel.npc[0][4].inventory.get(itemIndex) instanceof Furnitur) {
                        Furnitur furnitur = (Furnitur) gamePanel.npc[0][4].inventory.get(itemIndex);
                        harga = furnitur.getHarga();
                    } else if (gamePanel.npc[0][4].inventory.get(itemIndex) instanceof Lampu) {
                        Lampu lampu = (Lampu) gamePanel.npc[0][4].inventory.get(itemIndex);
                        harga = lampu.getHarga();
                        if (gamePanel.getCurrentSim().searchItemInInventory(lampu.getName()) != 999) {
                            gamePanel.ui.subState = 0;
                            gamePanel.ui.charIndex = 0;
                            gamePanel.ui.combinedText = "";
                            gamePanel.gameState = gamePanel.dialogState;
                            gamePanel.ui.currentDialog = "Anda sudah memiliki lampu!";
                        }
                    }
                    if (gamePanel.getCurrentSim().getUang() < harga) {
                        gamePanel.ui.subState = 0;
                        gamePanel.ui.charIndex = 0;
                        gamePanel.ui.combinedText = "";
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.currentDialog = "Uang tidak cukup!";
                    } else {
                        gamePanel.getCurrentSim().setIsCanBuy(false);
                        gamePanel.getCurrentSim().setIndexSimSaatBeli(gamePanel.indexCurrentSim);
                        gamePanel.getCurrentSim().setItemBuyTempIndex(itemIndex);
                        gamePanel.getCurrentSim().setUang(gamePanel.getCurrentSim().getUang() - harga);
                        Random rand = new Random();
                        gamePanel.getCurrentSim().setRemainingTimeBuy((rand.nextInt(5) + 1) * 30);
                        gamePanel.ui.subState = 0;
                        gamePanel.ui.charIndex = 0;
                        gamePanel.ui.combinedText = "";
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.currentDialog = "Anda berhasil membeli "
                                + gamePanel.npc[0][4].inventory.get(itemIndex).getName() + ".\nSilahkan tunggu "
                                + gamePanel.getCurrentSim().getRemainingTimeBuy() + " detik.";
                        // gamePanel.getCurrentSim().setIsCanBuyToTrue();
                    }

                } else {
                    gamePanel.ui.subState = 0;
                    gamePanel.ui.charIndex = 0;
                    gamePanel.ui.combinedText = "";
                    gamePanel.gameState = gamePanel.dialogState;
                    gamePanel.ui.currentDialog = "Tidak dapat membeli barang.\nMasih dalam proses pembelian barang\nsebelumnya.";
                }

                // if (gamePanel.npc[0][4].inventory.get(itemIndex) instanceof BahanMakanan) {
                // BahanMakanan makanan = (BahanMakanan)
                // gamePanel.npc[0][4].inventory.get(itemIndex);
                // if (gamePanel.listSim.get(gamePanel.indexCurrentSim).uang >= makanan.harga) {
                // if (gamePanel.listSim.get(gamePanel.indexCurrentSim).canObtainItem(makanan))
                // {
                // gamePanel.listSim.get(gamePanel.indexCurrentSim).uang -= makanan.harga;
                // } else {
                // subState = 0;
                // charIndex = 0;
                // combinedText = "";
                // gamePanel.gameState = gamePanel.dialogState;
                // currentDialog = "Inventory penuh";
                // }
                // } else {
                // subState = 0;
                // charIndex = 0;
                // combinedText = "";
                // gamePanel.gameState = gamePanel.dialogState;
                // currentDialog = "Uang tidak cukup";
                // // drawDialogScreen(); // ini ga perlu karena sudah ada di atas
                // }
                // } else if (gamePanel.npc[0][4].inventory.get(itemIndex) instanceof Furnitur)
                // {
                // Furnitur furnitur = (Furnitur) gamePanel.npc[0][4].inventory.get(itemIndex);
                // if (gamePanel.listSim.get(gamePanel.indexCurrentSim).uang >= furnitur.harga)
                // {
                // if (gamePanel.listSim.get(gamePanel.indexCurrentSim).canObtainItem(furnitur))
                // {
                // gamePanel.listSim.get(gamePanel.indexCurrentSim).uang -= furnitur.harga;
                // } else {
                // subState = 0;
                // charIndex = 0;
                // combinedText = "";
                // gamePanel.gameState = gamePanel.dialogState;
                // currentDialog = "Inventory penuh";
                // }
                // } else {
                // subState = 0;
                // charIndex = 0;
                // combinedText = "";
                // gamePanel.gameState = gamePanel.dialogState;
                // currentDialog = "Uang tidak cukup";
                // // drawDialogScreen(); // ini ga perlu karena sudah ada di atas
                // }
                // } else if (gamePanel.npc[0][4].inventory.get(itemIndex) instanceof Lampu) {
                // Lampu lampu = (Lampu) gamePanel.npc[0][4].inventory.get(itemIndex);
                // if (gamePanel.listSim.get(gamePanel.indexCurrentSim).uang >= lampu.harga) {
                // if (gamePanel.listSim.get(gamePanel.indexCurrentSim).canObtainItem(lampu)) {
                // gamePanel.listSim.get(gamePanel.indexCurrentSim).uang -= lampu.harga;
                // } else {
                // if (gamePanel.listSim.get(gamePanel.indexCurrentSim).inventory
                // .size() >= gamePanel.listSim.get(gamePanel.indexCurrentSim).maxInventorySize)
                // {
                // subState = 0;
                // charIndex = 0;
                // combinedText = "";
                // gamePanel.gameState = gamePanel.dialogState;
                // currentDialog = "Inventory penuh";
                // } else {
                // subState = 0;
                // charIndex = 0;
                // combinedText = "";
                // gamePanel.gameState = gamePanel.dialogState;
                // currentDialog = "Anda sudah memiliki lampu!";
                // }
                // }
                // } else {
                // subState = 0;
                // charIndex = 0;
                // combinedText = "";
                // gamePanel.gameState = gamePanel.dialogState;
                // currentDialog = "Uang tidak cukup";
                // // drawDialogScreen(); nggk perlu ini karena sudah ada di atas
                // }
                // }

            }
        }

    }

    public void drawChangeSimScreen() {
        // batas

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
        final int textXINVENTORY = getXforCenteredText("LIST SIM");
        g2d.drawString("LIST SIM", textXINVENTORY, textYINVENTORY);

        // slot
        final int slotXstart = frameX + 19;
        final int slotYstart = textYINVENTORY + 19;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gamePanel.tileSize + 1;

        // draw entity items
        for (int i = 0; i < gamePanel.listSim.size(); i++) {

            // kuningin sim yang dipilih
            if (gamePanel.listSim.get(i) == gamePanel.listSim.get(gamePanel.indexCurrentSim)) {
                g2d.setColor(new Color(240, 190, 90));
                g2d.fillRoundRect(slotX, slotY, gamePanel.tileSize, gamePanel.tileSize, 10, 10);
            }

            g2d.drawImage(gamePanel.listSim.get(i).down1, slotX, slotY, gamePanel.tileSize, gamePanel.tileSize, null);

            slotX += slotSize;
            // jika kelipatan 11 maka pindah ke baris bawah
            if (i % 11 == 10) {
                slotX = slotXstart;
                slotY += slotSize;
            }
        }

        // cursor
        int cursorX = slotXstart + (slotSize * listSimSlotCol);
        int cursorY = slotYstart + (slotSize * listSimSlotRow);
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
        int iFrameHeight = gamePanel.tileSize * 6 - 20 - 35 - 30;
        drawSubWindow(iFrameX, iFrameY, iFrameWidth, iFrameHeight);

        // ITEM INFO TITLE
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(40f));
        final int textXITEMINFO = getXforCenteredText("SIM INFO");
        int textYITEMINFO = iFrameY + gamePanel.tileSize;
        g2d.drawString("SIM INFO", textXITEMINFO, textYITEMINFO);

        // draw item info
        int lineHeight = 35;
        int textX = iFrameX + 20;
        int textY = textYITEMINFO + lineHeight + 20;
        g2d.setFont(g2d.getFont().deriveFont(32f));

        int itemIndex = getItemIndexOnSlot(listSimSlotRow, listSimSlotCol);
        if (itemIndex < gamePanel.listSim.size()) {
            if (gamePanel.listSim.get(itemIndex) != null) {

                // write item info
                g2d.drawString("Nama", textX, textY);
                textY += lineHeight;
                g2d.drawString("Pekerjaan", textX, textY);
                textY += lineHeight;
                g2d.drawString("Koordinat Rumah", textX, textY);
                // write item value
                int iTailX = iFrameX + iFrameWidth / 3 + gamePanel.tileSize;
                textY = textYITEMINFO + lineHeight + 20;
                String iValue;
                Sim sim = gamePanel.listSim.get(itemIndex);
                g2d.drawString(" : " + sim.getNama(), iTailX, textY);
                textY += lineHeight;
                g2d.drawString(" : " + sim.getPekerjaan().listPekerjaan[sim.getPekerjaan().indexPekerjaan], iTailX, textY);
                textY += lineHeight;
                iValue = String.valueOf(" : " + sim.getRumah().getColRumah() + ", " + sim.getRumah().getRowRumah());
                g2d.drawString(iValue, iTailX, textY);

                textY = textYITEMINFO + lineHeight + 20;
            }
        }

        // batas
    }

    public void drawUpgradeRumahScreen() {
        // draw window
        int x = getXforCenteredText("UPGRADE RUMAH");
        x -= 3 * gamePanel.tileSize;
        int y = gamePanel.tileSize * 4;
        int width = gamePanel.screenWidth - 2 * x;
        int height = gamePanel.screenHeight - y - gamePanel.tileSize * 4;
        drawSubWindow(x, y, width, height);

        // draw UPGRADE RUMAH text
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 35f));
        x = getXforCenteredText("UPGRADE RUMAH");
        y += gamePanel.tileSize + 5;
        g2d.drawString("UPGRADE RUMAH", x, y);

        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        String text = "UP";
        x = getXforCenteredText(text);
        y += 2 * gamePanel.tileSize;
        g2d.drawString(text, x, y);
        if (commandNumber == 0) {
            g2d.drawString(">", x - gamePanel.tileSize, y);
        }

        text = "DOWN";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize;
        g2d.drawString(text, x, y);
        if (commandNumber == 1) {
            g2d.drawString(">", x - gamePanel.tileSize, y);
        }

        text = "LEFT";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize;
        g2d.drawString(text, x, y);
        if (commandNumber == 2) {
            g2d.drawString(">", x - gamePanel.tileSize, y);
        }

        text = "RIGHT";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize;
        g2d.drawString(text, x, y);
        if (commandNumber == 3) {
            g2d.drawString(">", x - gamePanel.tileSize, y);
        }

        // draw hint text
        x = gamePanel.tileSize * 2;
        y = gamePanel.tileSize * 13;
        width = gamePanel.tileSize * 6;
        height = gamePanel.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        g2d.drawString("Harga: 1500", x + 24, y + 60);
    }

    public void drawInputKoordinatBendaScreen(String judul) {
        // draw window
        int x = getXforCenteredText(judul);
        x -= 3 * gamePanel.tileSize;
        int y = gamePanel.tileSize * 4;
        int width = gamePanel.screenWidth - 2 * x;
        int height = gamePanel.screenHeight - y - gamePanel.tileSize * 5;
        drawSubWindow(x, y, width, height);

        // draw judul text
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 35f));
        x = getXforCenteredText(judul);
        y += gamePanel.tileSize + 5;
        g2d.drawString(judul, x, y);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        String text = "(format : x,y)";
        String textTemp = "(maks. 15 karakter)";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize - 10;
        g2d.drawString(text, x, y);

        // draw input text
        // x -= 40;
        x = getXforCenteredText(textTemp);
        y += gamePanel.tileSize * 2;
        width = width - 2 * gamePanel.tileSize + 15;
        height = gamePanel.tileSize;
        g2d.fillRect(x, y, width, height);

        Graphics2D g2d2 = (Graphics2D) g2d.create();
        g2d2.setColor(Color.BLACK);
        g2d2.setFont(g2d2.getFont().deriveFont(Font.PLAIN, 30f));
        g2d2.drawString(gamePanel.ui.inputText, x + 10, y + gamePanel.tileSize - 14);
    }

    public void drawInputNamaRuanganScreen(String judul) {
        // draw window
        int x = getXforCenteredText(judul);
        x -= 3 * gamePanel.tileSize;
        int y = gamePanel.tileSize * 4;
        int width = gamePanel.screenWidth - 2 * x;
        int height = gamePanel.screenHeight - y - gamePanel.tileSize * 5;
        drawSubWindow(x, y, width, height);

        // draw judul text
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 35f));
        x = getXforCenteredText(judul);
        y += gamePanel.tileSize + 5;
        g2d.drawString(judul, x, y);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        String text = "(maks. 15 karakter)";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize - 10;
        g2d.drawString(text, x, y);

        // draw input text
        // x -= 40;
        y += gamePanel.tileSize * 2;
        width = width - 2 * gamePanel.tileSize + 15;
        height = gamePanel.tileSize;
        g2d.fillRect(x, y, width, height);

        Graphics2D g2d2 = (Graphics2D) g2d.create();
        g2d2.setColor(Color.BLACK);
        g2d2.setFont(g2d2.getFont().deriveFont(Font.PLAIN, 30f));
        g2d2.drawString(gamePanel.ui.inputText, x + 10, y + gamePanel.tileSize - 14);
    }

    public void drawInputSimNameScreen(String judul) {
        int x = getXforCenteredText(judul);
        x -= 3 * gamePanel.tileSize;
        int y = gamePanel.tileSize * 4;
        int width = gamePanel.screenWidth - 2 * x;
        int height = gamePanel.screenHeight - y - gamePanel.tileSize * 5;
        drawSubWindow(x, y, width, height);

        // draw judul text
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 35f));
        x = getXforCenteredText(judul);
        y += gamePanel.tileSize + 5;
        g2d.drawString(judul, x, y);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        String text = "(maks. 15 karakter)";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize - 10;
        g2d.drawString(text, x, y);

        // draw input text
        // x -= 40;
        y += gamePanel.tileSize * 2;
        width = width - 2 * gamePanel.tileSize + 15;
        height = gamePanel.tileSize;
        g2d.fillRect(x, y, width, height);

        Graphics2D g2d2 = (Graphics2D) g2d.create();
        g2d2.setColor(Color.BLACK);
        g2d2.setFont(g2d2.getFont().deriveFont(Font.PLAIN, 30f));
        g2d2.drawString(gamePanel.ui.inputText, x + 10, y + gamePanel.tileSize - 14);

    }

    public void drawInputKoordinatRumahSimScreen(String judul) {
        // draw window
        int x = getXforCenteredText(judul);
        x -= 3 * gamePanel.tileSize;
        int y = gamePanel.tileSize * 4;
        int width = gamePanel.screenWidth - 2 * x;
        int height = gamePanel.screenHeight - y - gamePanel.tileSize * 5;
        drawSubWindow(x, y, width, height);

        // draw judul text
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 35f));
        x = getXforCenteredText(judul);
        y += gamePanel.tileSize + 5;
        g2d.drawString(judul, x, y);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        String text = "(format : x,y)";
        String textTemp = "(maks. 15 karakter)";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize - 10;
        g2d.drawString(text, x, y);

        // draw input text
        // x -= 40;
        x = getXforCenteredText(textTemp);
        y += gamePanel.tileSize * 2;
        width = width - 2 * gamePanel.tileSize + 15;
        height = gamePanel.tileSize;
        g2d.fillRect(x, y, width, height);

        Graphics2D g2d2 = (Graphics2D) g2d.create();
        g2d2.setColor(Color.BLACK);
        g2d2.setFont(g2d2.getFont().deriveFont(Font.PLAIN, 30f));
        g2d2.drawString(gamePanel.ui.inputText, x + 10, y + gamePanel.tileSize - 14);
    }

    // RESEP SCREEN
    public void drawResepScreen() {
        drawInventoryScreen(gamePanel.kokiTemp, kokiSlotCol, kokiSlotRow);

        // draw hint window
        int x = gamePanel.tileSize * 2;
        int y = gamePanel.tileSize * 13;
        int width = gamePanel.tileSize * 6;
        int height = gamePanel.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2d.drawString("[ESC] Back", x + 24, y + 60);

        // cook makanan
        int itemIndex = getItemIndexOnSlot(kokiSlotRow, kokiSlotCol);
        if (itemIndex < gamePanel.kokiTemp.inventory.size()) {
            if (gamePanel.keyHandler.enterPressed) {
                if (gamePanel.kokiTemp.inventory.get(itemIndex) instanceof Makanan) {
                    Makanan makanan = (Makanan) gamePanel.kokiTemp.inventory.get(itemIndex);
                    // check inventory containsAll makanan.bahan
                    java.util.List<String> bahanInInventory = new java.util.ArrayList<>();
                    for (int i = 0; i < gamePanel.listSim.get(gamePanel.indexCurrentSim).inventory.size(); i++) {
                        if (gamePanel.listSim.get(gamePanel.indexCurrentSim).inventory.get(i) instanceof BahanMakanan) {
                            BahanMakanan bahan = (BahanMakanan) gamePanel.listSim
                                    .get(gamePanel.indexCurrentSim).inventory.get(i);
                            bahanInInventory.add(bahan.getName());
                        }
                    }
                    // ini bisa jadi penerapan generics
                    if (bahanInInventory.containsAll(makanan.getBahan())) {
                        for (int i = 0; i < gamePanel.listSim.get(gamePanel.indexCurrentSim).inventory.size(); i++) {
                            if (gamePanel.listSim.get(gamePanel.indexCurrentSim).inventory
                                    .get(i) instanceof BahanMakanan) {
                                BahanMakanan bahanInv = (BahanMakanan) gamePanel.listSim
                                        .get(gamePanel.indexCurrentSim).inventory.get(i);
                                if (makanan.getBahan().contains(bahanInv.getName())) {
                                    if (bahanInv.getQuantity() > 1) {
                                        gamePanel.listSim.get(gamePanel.indexCurrentSim).inventory.get(i).decQuantity(1);
                                    } else {
                                        gamePanel.listSim.get(gamePanel.indexCurrentSim).inventory.remove(i);
                                    }
                                }
                            }
                        }

                        // efek
                        gamePanel.getCurrentSim().setMood(gamePanel.getCurrentSim().getMood() + 10);
                        if (gamePanel.getCurrentSim().getMood() > gamePanel.getCurrentSim().getMaxMood()) {
                            gamePanel.getCurrentSim().setMood(gamePanel.getCurrentSim().getMaxMood());
                        }

                        // simpan makanan yang udah jadi ke inventory
                        gamePanel.listSim.get(gamePanel.indexCurrentSim).canObtainItem(makanan);

                        durasiTimer = (int) (3 * makanan.getKekenyangan()/ 2);
                        int durasi = durasiTimer;

                        // nambah world time
                        for (int i = 0; i < gamePanel.listSim.size(); i++) {
                            gamePanel.listSim.get(i).getPekerjaan().worldTimeCounterForStartJobAfterChangeJob += durasi;
                            gamePanel.listSim.get(i).setEfekWaktuTidakTidurCounter(gamePanel.listSim.get(i).getEfekWaktuTidakTidurCounter() + durasi);
                            if (gamePanel.listSim.get(i).getIsUdahMakanDalamSatuHari()){
                                gamePanel.listSim.get(i).setEfekWaktuTidakBuangAirCounter(gamePanel.listSim.get(i).getEfekWaktuTidakBuangAirCounter() + durasi);
                            }
                        }
                        currentAksi = "Masak";
                        currentAksiCadangan = makanan.getName()+ " berhasil dibuat dan\ndimasukkan ke inventory!";
                        // mulai masak : draw dialog
                        gamePanel.gameState = gamePanel.timerState;
                        currentAksiDone = false;
                        gamePanel.ui.tempDurasi = (int) (3 * makanan.getKekenyangan()/ 2);
                        gamePanel.keyHandler.threadTemp = startTimerThread((int) (3 * makanan.getKekenyangan()/ 2));

                    } else {
                        charIndex = 0;
                        combinedText = "";
                        gamePanel.gameState = gamePanel.dialogState;
                        currentDialog = "Bahan tidak cukup untuk memasak\n" + makanan.getName()+ "!";
                    }
                }
            }
        }
        gamePanel.keyHandler.enterPressed = false;
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
        y += gamePanel.tileSize * 2 - 24;
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

        text = "Credits";
        y += gamePanel.tileSize;
        g2d.drawString(text, x, y);
        if (commandNumber == 5) {
            g2d.drawString(">", x - gamePanel.tileSize / 2, y);
            if (gamePanel.keyHandler.enterPressed) {
                commandNumber = 0;
                gamePanel.gameState = gamePanel.cutsceneState;
            }
        }

        text = "Back";
        y += gamePanel.tileSize * 2 - 24;
        g2d.drawString(text, x, y);
        if (commandNumber == 6) {
            g2d.drawString(">", x - gamePanel.tileSize / 2, y);
            if (gamePanel.keyHandler.enterPressed) {
                commandNumber = 0;
                gamePanel.gameState = gamePanel.playState;
            }
        }
    }

    public void drawSaveScreen() {
        // create frame
        int frameX = gamePanel.tileSize * 4;
        int frameY = gamePanel.tileSize * 3;
        int frameWidth = gamePanel.screenWidth - gamePanel.tileSize * 8;
        int frameHeight = gamePanel.screenHeight - gamePanel.tileSize * 6;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // title
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 35f));
        String text = "PILIH SAVE FILE";
        int x = getXforCenteredText(text);
        int y = gamePanel.tileSize * 4;
        g2d.setColor(Color.WHITE);
        g2d.drawString(text, x, y);

        // texts
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 32f));
        text = "SAVE FILE 1";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize * 2;
        g2d.drawString(text, x, y);
        if (commandNumber == 0) {
            g2d.drawString(">", x - gamePanel.tileSize / 2, y);
        }

        text = "SAVE FILE 2";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize * 2;
        g2d.drawString(text, x, y);
        if (commandNumber == 1) {
            g2d.drawString(">", x - gamePanel.tileSize / 2, y);
        }

        text = "SAVE FILE 3";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize * 2;
        g2d.drawString(text, x, y);
        if (commandNumber == 2) {
            g2d.drawString(">", x - gamePanel.tileSize / 2, y);
        }

        text = "Back";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize * 2;
        g2d.drawString(text, x, y);
        if (commandNumber == 3) {
            g2d.drawString(">", x - gamePanel.tileSize / 2, y);
        }

    }

    // HELP SCREEN
    public void drawHelpScreen() {
        // create frame
        int frameX = gamePanel.tileSize * 2;
        int frameY = gamePanel.tileSize * 2;
        int frameWidth = gamePanel.screenWidth - gamePanel.tileSize * 4;
        int frameHeight = gamePanel.screenHeight - gamePanel.tileSize * 4;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // title
        g2d.setFont(g2d.getFont().deriveFont(40f));
        String text = "HELP";
        int x = getXforCenteredText(text);
        int y = gamePanel.tileSize * 3;
        g2d.setColor(Color.WHITE);
        g2d.drawString(text, x, y);

        // texts
        g2d.setFont(g2d.getFont().deriveFont(30f));
        x = gamePanel.tileSize * 3;
        text = "T: Melihat Waktu";
        y += gamePanel.tileSize * 2;
        g2d.drawString(text, x, y);

        text = "I: Melihat Inventory";
        y += gamePanel.tileSize;
        g2d.drawString(text, x, y);

        text = "L: Melihat Lokasi";
        y += gamePanel.tileSize;
        g2d.drawString(text, x, y);

        text = "C: Melihat Sim Info";
        y += gamePanel.tileSize;
        g2d.drawString(text, x, y);

        text = "X: Melihat Mini Map";
        y += gamePanel.tileSize;
        g2d.drawString(text, x, y);

        text = "M: Melihat Map";
        y += gamePanel.tileSize;
        g2d.drawString(text, x, y);

        text = "Z: Mengambil Objek";
        y += gamePanel.tileSize;
        g2d.drawString(text, x, y);

        text = "ESC: Melihat Options";
        y += gamePanel.tileSize;
        g2d.drawString(text, x, y);

        x = gamePanel.tileSize * 9;
        text = "P: Pause";
        y = gamePanel.tileSize * 5;
        g2d.drawString(text, x, y);

        text = "K: Kerja";
        y += gamePanel.tileSize;
        g2d.drawString(text, x, y);

        text = "O: Olahraga";
        y += gamePanel.tileSize;
        g2d.drawString(text, x, y);

        text = "B: Beli Barang";
        y += gamePanel.tileSize;
        g2d.drawString(text, x, y);

        text = "G: Change Sim";
        y += gamePanel.tileSize;
        g2d.drawString(text, x, y);

        text = "U: Upgrade Rumah";
        y += gamePanel.tileSize;
        g2d.drawString(text, x, y);

        text = "ENTER: Interaksi";
        y += gamePanel.tileSize;
        g2d.drawString(text, x, y);
    }

    public void drawFullMapScreen(Graphics2D g2d) {
        // Background Color
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);

        // Draw Map
        int width = 500;
        int height = 500;
        int x = gamePanel.screenWidth / 2 - width / 2;
        int y = gamePanel.screenHeight / 2 - height / 2;
        g2d.drawImage(gamePanel.getMap().worldMap[gamePanel.getCurrentSim().getCurrentMap()], x, y, width, height, null);

        // Draw Player
        double scale = (double) (gamePanel.tileSize * gamePanel.maxWorldCol) / width;
        int playerX = (int) (x + gamePanel.getCurrentSim().getWorldX() / scale);
        int playerY = (int) (y + gamePanel.getCurrentSim().getWorldY() / scale);
        int playerSize = (int) (gamePanel.tileSize / scale) * 2;
        g2d.drawImage(gamePanel.getCurrentSim().down1, playerX, playerY, playerSize,
                playerSize, null);

        // draw benda
        int bendaSize = (int) (gamePanel.tileSize / scale);
        if (gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap() == 0) {
            for (int i = 0; i < gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()]
                    .size(); i++) {
                int bendaX = (int) (x
                        + gamePanel.listRumah[gamePanel.getCurrentSim().getCurrentMap()].get(i).getWorldX() / scale);
                int bendaY = (int) (y
                        + gamePanel.listRumah[gamePanel.getCurrentSim().getCurrentMap()].get(i).getWorldY() / scale);
                if (gamePanel.listRumah[gamePanel.getCurrentSim().getCurrentMap()].get(i) != null) {
                    g2d.drawImage(gamePanel.listRumah[gamePanel.getCurrentSim().getCurrentMap()].get(i).getImage(), bendaX,
                            bendaY, bendaSize, bendaSize, null);
                }
            }
        } else {

            for (int i = 0; i < gamePanel.listSim
                    .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                    .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).getIndexLocationRuangan()).getBendaRuangan()
                    .size(); i++) {
                int bendaX = (int) (x +
                        gamePanel.listSim
                                .get(gamePanel.listSim
                                        .get(gamePanel.indexCurrentSim).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                                .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).getIndexLocationRuangan()).getBendaRuangan()
                                .get(i).getWorldX() / scale);
                int bendaY = (int) (y +
                        gamePanel.listSim
                                .get(gamePanel.listSim
                                        .get(gamePanel.indexCurrentSim).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                                .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).getIndexLocationRuangan()).getBendaRuangan()
                                .get(i).getWorldY()/ scale);
                if (gamePanel.listSim
                        .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                        .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).getIndexLocationRuangan()).getBendaRuangan()
                        .get(i) != null) {
                    g2d.drawImage(
                            gamePanel.listSim
                                    .get(gamePanel.listSim
                                            .get(gamePanel.indexCurrentSim).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                                    .get(gamePanel.listSim
                                            .get(gamePanel.indexCurrentSim).getIndexLocationRuangan()).getBendaRuangan()
                                    .get(i).getImage(),
                            bendaX,
                            bendaY, bendaSize, bendaSize, null);
                }
            }
        }
    }

    public void drawMiniMap(Graphics2D g2d) {
        if (gamePanel.map.mapOn == true) {

            // Draw Map
            int width = 200;
            int height = 200;
            int x = gamePanel.screenWidth - width - 50;
            int y = 50;
            double scale = (double) (gamePanel.tileSize * gamePanel.maxWorldCol) / width;

            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
            g2d.drawImage(gamePanel.map.worldMap[gamePanel.getCurrentSim().getCurrentMap()],
                    x, y, width, height, null);

            // Draw Benda

            int bendaSize = (int) (gamePanel.tileSize / scale) * 2;
            if (gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap() == 0) {
                for (int i = 0; i < gamePanel.listRumah[gamePanel.listSim.get(gamePanel.indexCurrentSim).getCurrentMap()]
                        .size(); i++) {
                    int bendaX = (int) (x
                            + gamePanel.listRumah[gamePanel.getCurrentSim().getCurrentMap()].get(i).getWorldX()/ scale);
                    int bendaY = (int) (y
                            + gamePanel.listRumah[gamePanel.getCurrentSim().getCurrentMap()].get(i).getWorldY() / scale);
                    if (gamePanel.listRumah[gamePanel.getCurrentSim().getCurrentMap()].get(i) != null) {
                        g2d.drawImage(gamePanel.listRumah[gamePanel.getCurrentSim().getCurrentMap()].get(i).getImage(), bendaX,
                                bendaY, bendaSize, bendaSize, null);
                    }
                }
            } else {

                for (int i = 0; i < gamePanel.listSim
                        .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                        .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).getIndexLocationRuangan()).getBendaRuangan()
                        .size(); i++) {
                    int bendaX = (int) (x +
                            gamePanel.listSim
                                    .get(gamePanel.listSim
                                            .get(gamePanel.indexCurrentSim).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                                    .get(gamePanel.listSim
                                            .get(gamePanel.indexCurrentSim).getIndexLocationRuangan()).getBendaRuangan()
                                    .get(i).getWorldX()/ scale);
                    int bendaY = (int) (y +
                            gamePanel.listSim
                                    .get(gamePanel.listSim
                                            .get(gamePanel.indexCurrentSim).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                                    .get(gamePanel.listSim
                                            .get(gamePanel.indexCurrentSim).getIndexLocationRuangan()).getBendaRuangan()
                                    .get(i).getWorldY() / scale);
                    if (gamePanel.listSim
                            .get(gamePanel.listSim
                                    .get(gamePanel.indexCurrentSim).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                            .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).getIndexLocationRuangan()).getBendaRuangan()
                            .get(i) != null) {
                        g2d.drawImage(
                                gamePanel.listSim
                                        .get(gamePanel.listSim
                                                .get(gamePanel.indexCurrentSim).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                                        .get(gamePanel.listSim
                                                .get(gamePanel.indexCurrentSim).getIndexLocationRuangan()).getBendaRuangan()
                                        .get(i).getImage(),
                                bendaX,
                                bendaY, bendaSize, bendaSize, null);
                    }
                }

            }
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));

            // Draw Player

            int playerX = (int) (x + gamePanel.getCurrentSim().getWorldX() / scale);
            int playerY = (int) (y + gamePanel.getCurrentSim().getWorldY() / scale);
            int playerSize = (int) (gamePanel.tileSize / 6);
            g2d.drawImage(gamePanel.getCurrentSim().down1, playerX - 3, playerY - 3,
                    playerSize,
                    playerSize, null);

            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        }
    }

    public void drawGameOverScreen() {
        g2d.setColor(new Color(0, 0, 0, 150));
        g2d.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);

        int x;
        int y;
        String text;
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 70f));

        text = "Mati Akibat " + Sim.getKeteranganMati();
        // shadow
        g2d.setColor(Color.BLACK);
        x = getXforCenteredText(text);
        y = gamePanel.tileSize * 6;
        g2d.drawString(text, x + 4, y + 4);
        // text
        g2d.setColor(Color.WHITE);
        g2d.drawString(text, x, y);

        // retry
        if (gamePanel.isOneSim) {
            g2d.setFont(g2d.getFont().deriveFont(50f));
            text = "Change Sim";
            x = getXforCenteredText(text);
            y += gamePanel.tileSize * 4;
            g2d.setColor(Color.GRAY);
            g2d.drawString(text, x, y);

            // back to the the title screen
            text = "Quit";
            x = getXforCenteredText(text);
            y += 55;
            g2d.setColor(Color.WHITE);
            g2d.drawString(text, x, y);
            if (commandNumber == 0) {
                g2d.drawString(">", x - 40, y);
            }

        } else if (gamePanel.listSim.size() >= 1) {
            g2d.setFont(g2d.getFont().deriveFont(50f));
            text = "Change Sim";
            x = getXforCenteredText(text);
            y += gamePanel.tileSize * 4;
            g2d.drawString(text, x, y);
            if (commandNumber == 0) {
                g2d.drawString(">", x - 40, y);
            }

            // back to the the title screen
            text = "Quit";
            x = getXforCenteredText(text);
            y += 55;
            g2d.drawString(text, x, y);
            if (commandNumber == 1) {
                g2d.drawString(">", x - 40, y);
            }
        }

    }

    // TIMER SCREEN
    public void drawTimerScreen() {
        // draw window
        int x = getXforCenteredText("Sedang " + currentAksi + "...");
        x -= 4 * gamePanel.tileSize;
        int y = gamePanel.tileSize * 4;
        int width = gamePanel.screenWidth - 2 * x;
        int height = gamePanel.screenHeight - y - gamePanel.tileSize * 5;
        drawSubWindow(x, y, width, height);

        // draw judul text
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 35f));
        x = getXforCenteredText("Sedang " + currentAksi + "...");
        y += gamePanel.tileSize + 5;
        g2d.drawString("Sedang " + currentAksi + "...", x, y);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        String text = "(dalam detik)";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize - 10;
        g2d.drawString(text, x, y);

        // draw timer
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 50f));
        text = String.valueOf(durasiTimer);
        x = getXforCenteredText(text);
        y += gamePanel.tileSize * 3;
        g2d.drawString(text, x, y);

        // draw hint text
        x = gamePanel.tileSize * 2;
        y = gamePanel.tileSize * 13;
        width = gamePanel.tileSize * 6;
        height = gamePanel.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        g2d.drawString("[ESC] Skip", x + 24, y + 60);
    }

    // ----------------------------------- BATAS SUCI
    // ----------------------------------------
    public void drawInputDurasiTidurScreen(String judul) {
        // draw window
        int x = getXforCenteredText(judul);
        x -= 4 * gamePanel.tileSize;
        int y = gamePanel.tileSize * 4;
        int width = gamePanel.screenWidth - 2 * x;
        int height = gamePanel.screenHeight - y - gamePanel.tileSize * 5;
        drawSubWindow(x, y, width, height);

        // draw judul text
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 35f));
        x = getXforCenteredText(judul);
        y += gamePanel.tileSize + 5;
        g2d.drawString(judul, x, y);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        String text = "(dalam detik)";
        String textTemp = "(maks. 15 karakter)";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize - 10;
        g2d.drawString(text, x, y);

        // draw input text
        x = getXforCenteredText(textTemp);
        y += gamePanel.tileSize * 2;
        width = gamePanel.screenWidth - 2 * x;
        height = gamePanel.tileSize;
        g2d.fillRect(x, y, width, height);

        // draw text
        Graphics2D g2d2 = (Graphics2D) g2d.create();
        g2d2.setColor(Color.BLACK);
        g2d2.setFont(g2d2.getFont().deriveFont(Font.PLAIN, 30f));
        g2d2.drawString(gamePanel.ui.inputText, x + 10, y + gamePanel.tileSize - 14);
    }

    public void drawInputDurasiNontonScreen(String judul) {
        // draw window
        int x = getXforCenteredText(judul);
        x -= 4 * gamePanel.tileSize;
        int y = gamePanel.tileSize * 4;
        int width = gamePanel.screenWidth - 2 * x;
        int height = gamePanel.screenHeight - y - gamePanel.tileSize * 5;
        drawSubWindow(x, y, width, height);

        // draw judul text
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 35f));
        x = getXforCenteredText(judul);
        y += gamePanel.tileSize + 5;
        g2d.drawString(judul, x, y);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        String text = "(dalam detik)";
        String textTemp = "(maks. 15 karakter)";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize - 10;
        g2d.drawString(text, x, y);

        // draw input text
        x = getXforCenteredText(textTemp);
        y += gamePanel.tileSize * 2;
        width = gamePanel.screenWidth - 2 * x;
        height = gamePanel.tileSize;
        g2d.fillRect(x, y, width, height);

        // draw text
        Graphics2D g2d2 = (Graphics2D) g2d.create();
        g2d2.setColor(Color.BLACK);
        g2d2.setFont(g2d2.getFont().deriveFont(Font.PLAIN, 30f));
        g2d2.drawString(gamePanel.ui.inputText, x + 10, y + gamePanel.tileSize - 14);
    }

    public void drawInputDurasiMandiScreen(String judul) {
        // draw window
        int x = getXforCenteredText(judul);
        x -= 4 * gamePanel.tileSize;
        int y = gamePanel.tileSize * 4;
        int width = gamePanel.screenWidth - 2 * x;
        int height = gamePanel.screenHeight - y - gamePanel.tileSize * 5;
        drawSubWindow(x, y, width, height);

        // draw judul text
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 35f));
        x = getXforCenteredText(judul);
        y += gamePanel.tileSize + 5;
        g2d.drawString(judul, x, y);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        String text = "(dalam detik)";
        String textTemp = "(maks. 15 karakter)";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize - 10;
        g2d.drawString(text, x, y);

        // draw input text
        x = getXforCenteredText(textTemp);
        y += gamePanel.tileSize * 2;
        width = gamePanel.screenWidth - 2 * x;
        height = gamePanel.tileSize;
        g2d.fillRect(x, y, width, height);

        // draw text
        Graphics2D g2d2 = (Graphics2D) g2d.create();
        g2d2.setColor(Color.BLACK);
        g2d2.setFont(g2d2.getFont().deriveFont(Font.PLAIN, 30f));
        g2d2.drawString(gamePanel.ui.inputText, x + 10, y + gamePanel.tileSize - 14);
    }

    public void drawInputDurasiShalatScreen(String judul) {
        // draw window
        int x = getXforCenteredText(judul);
        x -= 4 * gamePanel.tileSize;
        int y = gamePanel.tileSize * 4;
        int width = gamePanel.screenWidth - 2 * x;
        int height = gamePanel.screenHeight - y - gamePanel.tileSize * 5;
        drawSubWindow(x, y, width, height);

        // draw judul text
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 35f));
        x = getXforCenteredText(judul);
        y += gamePanel.tileSize + 5;
        g2d.drawString(judul, x, y);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        String text = "(dalam detik)";
        String textTemp = "(maks. 15 karakter)";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize - 10;
        g2d.drawString(text, x, y);

        // draw input text
        x = getXforCenteredText(textTemp);
        y += gamePanel.tileSize * 2;
        width = gamePanel.screenWidth - 2 * x;
        height = gamePanel.tileSize;
        g2d.fillRect(x, y, width, height);

        // draw text
        Graphics2D g2d2 = (Graphics2D) g2d.create();
        g2d2.setColor(Color.BLACK);
        g2d2.setFont(g2d2.getFont().deriveFont(Font.PLAIN, 30f));
        g2d2.drawString(gamePanel.ui.inputText, x + 10, y + gamePanel.tileSize - 14);
    }

    public void drawInputDurasiBacaBukuScreen(String judul) {
        // draw window
        int x = getXforCenteredText(judul);
        x -= 4 * gamePanel.tileSize;
        int y = gamePanel.tileSize * 4;
        int width = gamePanel.screenWidth - 2 * x;
        int height = gamePanel.screenHeight - y - gamePanel.tileSize * 5;
        drawSubWindow(x, y, width, height);

        // draw judul text
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 35f));
        x = getXforCenteredText(judul);
        y += gamePanel.tileSize + 5;
        g2d.drawString(judul, x, y);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        String text = "(dalam detik)";
        String textTemp = "(maks. 15 karakter)";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize - 10;
        g2d.drawString(text, x, y);

        // draw input text
        x = getXforCenteredText(textTemp);
        y += gamePanel.tileSize * 2;
        width = gamePanel.screenWidth - 2 * x;
        height = gamePanel.tileSize;
        g2d.fillRect(x, y, width, height);

        // draw text
        Graphics2D g2d2 = (Graphics2D) g2d.create();
        g2d2.setColor(Color.BLACK);
        g2d2.setFont(g2d2.getFont().deriveFont(Font.PLAIN, 30f));
        g2d2.drawString(gamePanel.ui.inputText, x + 10, y + gamePanel.tileSize - 14);
    }

    public void drawInputDurasiRadioScreen(String judul) {
        // draw window
        int x = getXforCenteredText(judul);
        x -= 4 * gamePanel.tileSize;
        int y = gamePanel.tileSize * 4;
        int width = gamePanel.screenWidth - 2 * x;
        int height = gamePanel.screenHeight - y - gamePanel.tileSize * 5;
        drawSubWindow(x, y, width, height);

        // draw judul text
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 35f));
        x = getXforCenteredText(judul);
        y += gamePanel.tileSize + 5;
        g2d.drawString(judul, x, y);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        String text = "(dalam detik)";
        String textTemp = "(maks. 15 karakter)";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize - 10;
        g2d.drawString(text, x, y);

        // draw input text
        x = getXforCenteredText(textTemp);
        y += gamePanel.tileSize * 2;
        width = gamePanel.screenWidth - 2 * x;
        height = gamePanel.tileSize;
        g2d.fillRect(x, y, width, height);

        // draw text
        Graphics2D g2d2 = (Graphics2D) g2d.create();
        g2d2.setColor(Color.BLACK);
        g2d2.setFont(g2d2.getFont().deriveFont(Font.PLAIN, 30f));
        g2d2.drawString(gamePanel.ui.inputText, x + 10, y + gamePanel.tileSize - 14);
    }

    public void drawInputDurasiSiramTanamanScreen(String judul) {
        // draw window
        int x = getXforCenteredText(judul);
        x -= 4 * gamePanel.tileSize;
        int y = gamePanel.tileSize * 4;
        int width = gamePanel.screenWidth - 2 * x;
        int height = gamePanel.screenHeight - y - gamePanel.tileSize * 5;
        drawSubWindow(x, y, width, height);

        // draw judul text
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 35f));
        x = getXforCenteredText(judul);
        y += gamePanel.tileSize + 5;
        g2d.drawString(judul, x, y);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        String text = "(dalam detik)";
        String textTemp = "(maks. 15 karakter)";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize - 10;
        g2d.drawString(text, x, y);

        // draw input text
        x = getXforCenteredText(textTemp);
        y += gamePanel.tileSize * 2;
        width = gamePanel.screenWidth - 2 * x;
        height = gamePanel.tileSize;
        g2d.fillRect(x, y, width, height);

        // draw text
        Graphics2D g2d2 = (Graphics2D) g2d.create();
        g2d2.setColor(Color.BLACK);
        g2d2.setFont(g2d2.getFont().deriveFont(Font.PLAIN, 30f));
        g2d2.drawString(gamePanel.ui.inputText, x + 10, y + gamePanel.tileSize - 14);
    }

    public void drawInputDurasiMainGameScreen(String judul) {
        // draw window
        int x = getXforCenteredText(judul);
        x -= 4 * gamePanel.tileSize;
        int y = gamePanel.tileSize * 4;
        int width = gamePanel.screenWidth - 2 * x;
        int height = gamePanel.screenHeight - y - gamePanel.tileSize * 5;
        drawSubWindow(x, y, width, height);

        // draw judul text
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 35f));
        x = getXforCenteredText(judul);
        y += gamePanel.tileSize + 5;
        g2d.drawString(judul, x, y);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        String text = "(dalam detik)";
        String textTemp = "(maks. 15 karakter)";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize - 10;
        g2d.drawString(text, x, y);

        // draw input text
        x = getXforCenteredText(textTemp);
        y += gamePanel.tileSize * 2;
        width = gamePanel.screenWidth - 2 * x;
        height = gamePanel.tileSize;
        g2d.fillRect(x, y, width, height);

        // draw text
        Graphics2D g2d2 = (Graphics2D) g2d.create();
        g2d2.setColor(Color.BLACK);
        g2d2.setFont(g2d2.getFont().deriveFont(Font.PLAIN, 30f));
        g2d2.drawString(gamePanel.ui.inputText, x + 10, y + gamePanel.tileSize - 14);
    }

    public void drawGantiPekerjaanScreen(String judul) {
        // draw window
        int x = getXforCenteredText(judul);
        x -= 4 * gamePanel.tileSize;
        int y = gamePanel.tileSize * 4 - 15;
        int width = gamePanel.screenWidth - 2 * x;
        int height = gamePanel.screenHeight - y - gamePanel.tileSize * 5 + 15;
        drawSubWindow(x, y, width, height);

        // draw judul
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 40f));
        x = getXforCenteredText(judul);
        y += gamePanel.tileSize + 5;
        g2d.drawString(judul, x, y);

        // draw text
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        String text = "Pilih pekerjaan yang ingin diubah:";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize * 2 - 30;
        g2d.drawString(text, x, y);
        y += 15;

        // draw pekerjaan
        for (int i = 0; i < gamePanel.getCurrentSim().getPekerjaan().listPekerjaan.length; i++) {
            text = gamePanel.getCurrentSim().getPekerjaan().listPekerjaan[i];
            x = getXforCenteredText(text);
            y += gamePanel.tileSize - 10;
            g2d.drawString(text, x, y);
            if (commandNumber == i) {
                g2d.drawString(">", x - 40, y);
            }
        }

        // hint harga
        // draw hint text
        x = gamePanel.tileSize * 2;
        y = gamePanel.tileSize * 13;
        width = gamePanel.tileSize * 5;
        height = gamePanel.tileSize * 2;
        drawSubWindow(x, y, width, height);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        g2d.drawString("Harga: " + (int) gamePanel.getCurrentSim().getPekerjaan().gaji[commandNumber] / 2, x + 24, y + 60);

    }

    public void drawInputDurasiOlahragaScreen(String judul) {
        // draw window
        int x = getXforCenteredText(judul);
        x -= 4 * gamePanel.tileSize;
        int y = gamePanel.tileSize * 4;
        int width = gamePanel.screenWidth - 2 * x;
        int height = gamePanel.screenHeight - y - gamePanel.tileSize * 5;
        drawSubWindow(x, y, width, height);

        // draw judul text
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 35f));
        x = getXforCenteredText(judul);
        y += gamePanel.tileSize + 5;
        g2d.drawString(judul, x, y);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        String text = "(dalam detik)";
        String textTemp = "(maks. 15 karakter)";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize - 10;
        g2d.drawString(text, x, y);

        // draw input text
        x = getXforCenteredText(textTemp);
        y += gamePanel.tileSize * 2;
        width = gamePanel.screenWidth - 2 * x;
        height = gamePanel.tileSize;
        g2d.fillRect(x, y, width, height);

        // draw text
        Graphics2D g2d2 = (Graphics2D) g2d.create();
        g2d2.setColor(Color.BLACK);
        g2d2.setFont(g2d2.getFont().deriveFont(Font.PLAIN, 30f));
        g2d2.drawString(gamePanel.ui.inputText, x + 10, y + gamePanel.tileSize - 14);
    }

    public void drawKerjaScreen(String judul) {
        // draw window
        int x = getXforCenteredText(judul);
        x -= 4 * gamePanel.tileSize;
        int y = gamePanel.tileSize * 4;
        int width = gamePanel.screenWidth - 2 * x;
        int height = gamePanel.screenHeight - y - gamePanel.tileSize * 5;
        drawSubWindow(x, y, width, height);

        // draw judul
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 40f));
        x = getXforCenteredText(judul);
        y += gamePanel.tileSize + 5;
        g2d.drawString(judul, x, y);

        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 35f));
        x = getXforCenteredText("Pekerjaan: " + gamePanel.getCurrentSim().getPekerjaan().listPekerjaan[gamePanel
                .getCurrentSim().getPekerjaan().indexPekerjaan]) /* - 2 * gamePanel.tileSize - 35 */;
        y += gamePanel.tileSize + 35;
        g2d.drawString("Pekerjaan: "
                + gamePanel.getCurrentSim().getPekerjaan().listPekerjaan[gamePanel.getCurrentSim().getPekerjaan().indexPekerjaan],
                x, y);

        if (gamePanel.getCurrentSim().getPekerjaan().isCanStartPekerjaan
                && gamePanel.getCurrentSim().getPekerjaan().isCanChangePekerjaan) {
            // draw text
            g2d.setColor(Color.WHITE);
            g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
            x = getXforCenteredText("Mulai kerja");
            y += 2 * gamePanel.tileSize - 10;
            g2d.drawString("Mulai Kerja", x, y);
            if (commandNumber == 0) {
                g2d.drawString(">", x - 40, y);
            }

            x = getXforCenteredText("Ganti Pekerjaan");
            y += gamePanel.tileSize;
            g2d.drawString("Ganti Pekerjaan", x, y);
            if (commandNumber == 1) {
                g2d.drawString(">", x - 40, y);
            }
        } else if (gamePanel.getCurrentSim().getPekerjaan().isCanChangePekerjaan) {
            // draw text
            g2d.setColor(Color.GRAY);
            g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
            x = getXforCenteredText("Mulai kerja");
            y += 2 * gamePanel.tileSize - 10;
            g2d.drawString("Mulai Kerja", x, y);

            g2d.setColor(Color.WHITE);
            x = getXforCenteredText("Ganti Pekerjaan");
            y += gamePanel.tileSize;
            g2d.drawString("Ganti Pekerjaan", x, y);
            if (commandNumber == 0) {
                g2d.drawString(">", x - 40, y);
            }
        } else if (gamePanel.getCurrentSim().getPekerjaan().isCanStartPekerjaan) {
            // draw text
            g2d.setColor(Color.WHITE);
            g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
            x = getXforCenteredText("Mulai kerja");
            y += 2 * gamePanel.tileSize - 10;
            g2d.drawString("Mulai Kerja", x, y);
            if (commandNumber == 0) {
                g2d.drawString(">", x - 40, y);
            }

            g2d.setColor(Color.GRAY);
            x = getXforCenteredText("Ganti Pekerjaan");
            y += gamePanel.tileSize;
            g2d.drawString("Ganti Pekerjaan", x, y);
        } else {
            // draw text
            g2d.setColor(Color.GRAY);
            g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
            x = getXforCenteredText("Mulai kerja");
            y += 2 * gamePanel.tileSize - 10;
            g2d.drawString("Mulai Kerja", x, y);

            g2d.setColor(Color.GRAY);
            x = getXforCenteredText("Ganti Pekerjaan");
            y += gamePanel.tileSize;
            g2d.drawString("Ganti Pekerjaan", x, y);
        }

    }

    public void drawInputDurasiKerjaScreen(String judul) {
        // draw window
        int x = getXforCenteredText(judul);
        x -= 4 * gamePanel.tileSize;
        int y = gamePanel.tileSize * 4;
        int width = gamePanel.screenWidth - 2 * x;
        int height = gamePanel.screenHeight - y - gamePanel.tileSize * 5;
        drawSubWindow(x, y, width, height);

        // draw judul text
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 35f));
        x = getXforCenteredText(judul);
        y += gamePanel.tileSize + 5;
        g2d.drawString(judul, x, y);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        String text = "(dalam detik)";
        String textTemp = "(maks. 15 karakter)";
        x = getXforCenteredText(text);
        y += gamePanel.tileSize - 10;
        g2d.drawString(text, x, y);

        // draw input text
        x = getXforCenteredText(textTemp);
        y += gamePanel.tileSize * 2;
        width = gamePanel.screenWidth - 2 * x;
        height = gamePanel.tileSize;
        g2d.fillRect(x, y, width, height);

        // draw text
        Graphics2D g2d2 = (Graphics2D) g2d.create();
        g2d2.setColor(Color.BLACK);
        g2d2.setFont(g2d2.getFont().deriveFont(Font.PLAIN, 30f));
        g2d2.drawString(gamePanel.ui.inputText, x + 10, y + gamePanel.tileSize - 14);
    }

    // ------------------------------ BATAS SUCI ------------------------------

    public void drawMelihatWaktuScreen(){
        // draw window
        int x = getXforCenteredText("Waktu");
        x -= 5 * gamePanel.tileSize;
        int y = gamePanel.tileSize * 4;
        int width = gamePanel.screenWidth - 2 * x;
        int height = gamePanel.screenHeight - y - gamePanel.tileSize * 5;
        drawSubWindow(x, y, width, height);

        // draw judul text
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 35f));
        x = getXforCenteredText("Waktu");
        y += gamePanel.tileSize + 5;
        g2d.drawString("Waktu", x, y);

        // draw text
        // TODO MELIHAT WAKTU
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        x = getXforCenteredText("Waktu") - 3 * gamePanel.tileSize;
        y += gamePanel.tileSize + 20;
        g2d.drawString("World Time", x, y);
        y += 35;
        g2d.drawString("World Day", x, y);
        y += gamePanel.tileSize+15;
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 30f));
        g2d.drawString("Sisa Waktu", x, y);
        y += 35;
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        g2d.drawString("Upgrade Rumah", x, y);
        y += 35;
        g2d.drawString("Beli Barang", x, y);

        // value 
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        x = getXforCenteredText("Waktu") + 2 *gamePanel.tileSize -30;
        y = gamePanel.tileSize * 6 + 25;
        g2d.drawString(": " + gamePanel.worldTimeCounter%720, x, y);
        y += 35;
        g2d.drawString(": " + gamePanel.worldTimeCounter/720, x, y);
        y += gamePanel.tileSize + 15 + 35;
        g2d.drawString(": " + gamePanel.getCurrentSim().getRumah().getRemainingTimeUpgrade() + " detik", x, y);
        y += 35;
        g2d.drawString(": " + gamePanel.getCurrentSim().getRemainingTimeBuy() + " detik", x, y);


        // g2d.setFont(g2d.getFont().deriveFont(32f));
        // int textX = frameX + 20;
        // int textY = textYSIMINFO + lineHeight + 20;
        // g2d.drawString("Nama", textX, textY);
        // textY += lineHeight;
        // g2d.drawString("Pekerjaan", textX, textY);
        // textY += lineHeight;
        // g2d.drawString("Uang", textX, textY);
        // textY += lineHeight;
        // g2d.drawString("Kesehatan", textX, textY);
        // textY += lineHeight;
        // g2d.drawString("Kekenyangan", textX, textY);
        // textY += lineHeight;
        // g2d.drawString("Mood", textX, textY);

        // // SIM INFO VALUE
        // int tailX = frameX + frameWidth / 3;
        // textY = textYSIMINFO + lineHeight + 20;
        // String value;
        // g2d.drawString(" : " + gamePanel.listSim.get(gamePanel.indexCurrentSim).nama, tailX, textY);
        // textY += lineHeight;
        // g2d.drawString(" : "
        //         + gamePanel.getCurrentSim().pekerjaan.listPekerjaan[gamePanel.getCurrentSim().pekerjaan.indexPekerjaan],
        //         tailX, textY);
        // textY += lineHeight;
        // value = String.valueOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).uang);
        // g2d.drawString(" : " + value, tailX, textY);
        // textY += lineHeight;
        // value = String.valueOf(" : " + gamePanel.listSim.get(gamePanel.indexCurrentSim).kesehatan + "/"
        //         + gamePanel.listSim.get(gamePanel.indexCurrentSim).maxKesehatan);
        // g2d.drawString(value, tailX, textY);
        // textY += lineHeight;
        // value = String.valueOf(" : " + gamePanel.listSim.get(gamePanel.indexCurrentSim).kekenyangan + "/"
        //         + gamePanel.listSim.get(gamePanel.indexCurrentSim).maxKekenyangan);
        // g2d.drawString(value, tailX, textY);
        // textY += lineHeight;
        // value = String.valueOf(" : " + gamePanel.listSim.get(gamePanel.indexCurrentSim).mood + "/"
        //         + gamePanel.listSim.get(gamePanel.indexCurrentSim).maxMood);
        // g2d.drawString(value, tailX, textY);

        
    }


    // start timer thread
    public Thread startTimerThread(int duration) {
        TimerThread timer = new TimerThread(duration);
        Thread thread = new Thread(timer);
        thread.start();
        return thread;
    }

    // inner class for timer thread

    public class TimerThread implements Runnable {
        private int duration;

        public TimerThread(int duration) {
            this.duration = duration;
        }

        @Override
        public void run() {
            int remaining = duration;
            Random rand = new Random();
            if (currentAksi == "Setel Radio") {
                gamePanel.stopMusic();
                gamePanel.playMusic(rand.nextInt(6) + 5);
            } else if (currentAksi == "Boker") {
                gamePanel.stopMusic();
                gamePanel.playMusic(11);
            } else if (currentAksi == "Tidur") {
                gamePanel.stopMusic();
                gamePanel.playMusic(12);
            } else if (currentAksi == "Nonton") {
                gamePanel.stopMusic();
                gamePanel.playMusic(13);
            } else if (currentAksi == "Mandi") {
                gamePanel.stopMusic();
                gamePanel.playMusic(14);
            } else if (currentAksi == "Makan") {
                gamePanel.stopMusic();
                gamePanel.playMusic(15);
            } else if (currentAksi == "Shalat") {
                gamePanel.stopMusic();
                gamePanel.playMusic(16);
            } else if (currentAksi == "Baca Buku") {
                gamePanel.stopMusic();
                gamePanel.playMusic(17);
            } else if (currentAksi == "Siram Tanaman") {
                gamePanel.stopMusic();
                gamePanel.playMusic(18);
            } else if (currentAksi == "Main Game") {
                gamePanel.stopMusic();
                gamePanel.playMusic(19);
            } else if (currentAksi == "Kerja") {
                gamePanel.stopMusic();
                gamePanel.playMusic(20);
            } else if (currentAksi == "Masak") {
                gamePanel.stopMusic();
                gamePanel.playMusic(21);
            } else if (currentAksi == "Olahraga") {
                gamePanel.stopMusic();
                gamePanel.playMusic(22);
            }

            while (remaining > 0) {
                durasiTimer = remaining;
                remaining--;

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    gamePanel.stopMusic();
                    gamePanel.playMusic(1);
                    setelahAksi(duration);
                    // gamePanel.stopMusic();
                    // gamePanel.playMusic(1);
                    // gamePanel.gameState = gamePanel.playState;
                    // if (gamePanel.getCurrentSim().isMati == false) {
                    // charIndex = 0;
                    // combinedText = "";
                    // gamePanel.gameState = gamePanel.dialogState;
                    // if (currentAksi == "Makan") {
                    // currentDialog = "Selesai melakukan " + currentAksiCadangan;
                    // } else if (currentAksi == "Masak") {
                    // currentDialog = "Selesai melakukan " + currentAksi + "\n" +
                    // currentAksiCadangan;
                    // } else {
                    // currentDialog = "Selesai melakukan " + currentAksi;
                    // }
                    // } else {
                    // gamePanel.getCurrentSim().isCanBuy = true;
                    // gamePanel.getCurrentSim().rumah.isCanUpgrade = true;
                    // }
                    // charIndex = 0;
                    // combinedText = "";
                    // gamePanel.gameState = gamePanel.dialogState;
                    // if (currentAksi == "Makan") {
                    // currentDialog = "Selesai melakukan " + currentAksiCadangan;
                    // } else if (currentAksi == "Masak") {
                    // currentDialog = "Selesai melakukan " + currentAksi + "\n" +
                    // currentAksiCadangan;
                    // } else {
                    // currentDialog = "Selesai melakukan " + currentAksi;
                    // }

                    // for (int i = 0; i < gamePanel.listSim.size(); i++) {
                    // if (gamePanel.listSim.get(i).rumah.isCanUpgrade == false) {
                    // gamePanel.listSim.get(i).rumah.isLockUpgrade = false;
                    // gamePanel.listSim.get(i).rumah.remainingTimeUpgrade -= duration;
                    // gamePanel.listSim.get(i).rumah.setIsCanUpgradeToTrueAfter18Minutes();
                    // gamePanel.listSim.get(i).rumah.isLockUpgrade = true;
                    // }
                    // }
                    // for (int i = 0; i < gamePanel.listSim.size(); i++) {
                    // if (gamePanel.listSim.get(i).isCanBuy == false) {
                    // gamePanel.listSim.get(i).isLockBuy = false;
                    // gamePanel.listSim.get(i).remainingTimeBuy -= duration;
                    // gamePanel.listSim.get(i).setIsCanBuyToTrue();
                    // gamePanel.listSim.get(i).rumah.isLockUpgrade = true;
                    // }
                    // }

                    currentAksi = "";
                    durasiTimer = 0;
                    currentAksiDone = true;
                    return;
                }
            }
            gamePanel.stopMusic();
            gamePanel.playMusic(1);
            setelahAksi(duration);

            // gamePanel.gameState = gamePanel.playState;
            // gamePanel.stopMusic();
            // gamePanel.playMusic(1);
            // if (gamePanel.getCurrentSim().isMati == false) {
            // charIndex = 0;
            // combinedText = "";
            // gamePanel.gameState = gamePanel.dialogState;
            // if (currentAksi == "Makan") {
            // currentDialog = "Selesai melakukan " + currentAksiCadangan;
            // } else if (currentAksi == "Masak") {
            // currentDialog = "Selesai melakukan " + currentAksi + "\n" +
            // currentAksiCadangan;
            // } else {
            // currentDialog = "Selesai melakukan " + currentAksi;
            // }
            // } else {
            // gamePanel.getCurrentSim().isCanBuy = true;
            // gamePanel.getCurrentSim().rumah.isCanUpgrade = true;
            // }
            // charIndex = 0;
            // combinedText = "";
            // gamePanel.gameState = gamePanel.dialogState;
            // if (currentAksi == "Makan") {
            // currentDialog = "Selesai melakukan " + currentAksiCadangan;
            // } else if (currentAksi == "Masak") {
            // currentDialog = "Selesai melakukan " + currentAksi + "\n" +
            // currentAksiCadangan;
            // } else {
            // currentDialog = "Selesai melakukan " + currentAksi;
            // }
            // for (int i = 0; i < gamePanel.listSim.size(); i++) {
            // if (gamePanel.listSim.get(i).rumah.isCanUpgrade == false) {
            // gamePanel.listSim.get(i).rumah.isLockUpgrade = false;
            // gamePanel.listSim.get(i).rumah.remainingTimeUpgrade -= duration;
            // gamePanel.listSim.get(i).rumah.setIsCanUpgradeToTrueAfter18Minutes();
            // gamePanel.listSim.get(i).rumah.isLockUpgrade = true;
            // }
            // }
            // for (int i = 0; i < gamePanel.listSim.size(); i++) {
            // if (gamePanel.listSim.get(i).isCanBuy == false) {
            // gamePanel.listSim.get(i).isLockBuy = false;
            // gamePanel.listSim.get(i).remainingTimeBuy -= duration;
            // gamePanel.listSim.get(i).setIsCanBuyToTrue();
            // gamePanel.listSim.get(i).rumah.isLockUpgrade = true;
            // }
            // }

            durasiTimer = 0;
            currentAksi = "";
            currentAksiDone = true;
        }
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

    public void setelahAksi(int duration) {
        gamePanel.stopMusic();
        gamePanel.playMusic(1);
        charIndex = 0;
        combinedText = "";
        gamePanel.gameState = gamePanel.dialogState;
        if (currentAksi == "Makan") {
            currentDialog = "Selesai melakukan " + currentAksiCadangan;
        } else if (currentAksi == "Masak") {
            currentDialog = "Selesai melakukan " + currentAksi + "\n" + currentAksiCadangan;
        } else {
            currentDialog = "Selesai melakukan " + currentAksi;
        }
        if (gamePanel.getCurrentSim().getIsMati() == false) {
            // charIndex = 0;
            // combinedText = "";
            // gamePanel.gameState = gamePanel.dialogState;
            // if (currentAksi == "Makan") {
            // currentDialog = "Selesai melakukan " + currentAksiCadangan;
            // } else if (currentAksi == "Masak") {
            // currentDialog = "Selesai melakukan " + currentAksi + "\n" +
            // currentAksiCadangan;
            // } else {
            // currentDialog = "Selesai melakukan " + currentAksi;
            // }
            for (int i = 0; i < gamePanel.listSim.size(); i++) {
                if (gamePanel.listSim.get(i).getRumah().getIsCanUpgrade() == false) {
                    gamePanel.listSim.get(i).getRumah().setIsLockUpgrade(false);
                    gamePanel.listSim.get(i).getRumah().decRemainingTimeUpgrade(duration);
                    gamePanel.listSim.get(i).getRumah().setIsCanUpgradeToTrueAfter18Minutes();
                    gamePanel.listSim.get(i).getRumah().setIsLockUpgrade(true);
                }
            }
            for (int i = 0; i < gamePanel.listSim.size(); i++) {
                if (gamePanel.listSim.get(i).getIsCanBuy() == false) {
                    gamePanel.listSim.get(i).setIsLockBuy(false);
                    gamePanel.listSim.get(i).setRemainingTimeBuy(gamePanel.listSim.get(i).getRemainingTimeBuy() - duration);
                    gamePanel.listSim.get(i).setIsCanBuyToTrue();
                    gamePanel.listSim.get(i).getRumah().setIsLockUpgrade(true);
                }
            }

        } else {
            gamePanel.getCurrentSim().setIsCanBuy(true);
            gamePanel.getCurrentSim().getRumah().setIsCanUpgrade(true);
        }

        // if (gamePanel.getCurrentSim().isBarangSampai = true) {
        // charIndex = 0;
        // combinedText = "";
        // gamePanel.gameState = gamePanel.dialogState;
        // currentDialog = gamePanel.getCurrentSim().tempDialogBarang;
        // } else if (gamePanel.getCurrentSim().isUpgradeDone = true) {
        // charIndex = 0;
        // combinedText = "";
        // gamePanel.gameState = gamePanel.dialogState;
        // currentDialog = gamePanel.getCurrentSim().tempDialogUpgrade;
        // }
        // charIndex = 0;
        // combinedText = "";
        // gamePanel.gameState = gamePanel.dialogState;
        // if (currentAksi == "Makan") {
        // currentDialog = "Selesai melakukan " + currentAksiCadangan;
        // } else if (currentAksi == "Masak") {
        // currentDialog = "Selesai melakukan " + currentAksi + "\n" +
        // currentAksiCadangan;
        // } else {
        // currentDialog = "Selesai melakukan " + currentAksi;
        // }

        // for (int i = 0; i < gamePanel.listSim.size(); i++) {
        // if (gamePanel.listSim.get(i).rumah.isCanUpgrade == false) {
        // gamePanel.listSim.get(i).rumah.isLockUpgrade = false;
        // gamePanel.listSim.get(i).rumah.remainingTimeUpgrade -= duration;
        // gamePanel.listSim.get(i).rumah.setIsCanUpgradeToTrueAfter18Minutes();
        // gamePanel.listSim.get(i).rumah.isLockUpgrade = true;
        // }
        // }
        // for (int i = 0; i < gamePanel.listSim.size(); i++) {
        // if (gamePanel.listSim.get(i).isCanBuy == false) {
        // gamePanel.listSim.get(i).isLockBuy = false;
        // gamePanel.listSim.get(i).remainingTimeBuy -= duration;
        // gamePanel.listSim.get(i).setIsCanBuyToTrue();
        // gamePanel.listSim.get(i).rumah.isLockUpgrade = true;
        // }
        // }

        durasiTimer = 0;
        currentAksi = "";
        currentAksiDone = true;
    }
}
