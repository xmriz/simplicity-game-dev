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
    private GamePanel gamePanel;
    private Graphics2D g2d;

    private Font maruMonica;

    private String inputFirstSimName = "";

    private String inputNamaRuangan = "";

    private String inputText = "";

    private boolean inputTextDone = false;

    private int durasiTimer = 0;
    private int tempDurasi = 0;

    private String currentAksi = "";
    private String currentAksiCadangan = "";
    private boolean currentAksiDone = false;

    // TODO sil ubah jadi comment: public boolean messageOn = false;
    // public String message = "";
    // int messageCounter = 0;
    private ArrayList <String> message = new ArrayList<> ();
    private ArrayList <Integer> messageCounter = new ArrayList<> ();

    // TODO sil ubah jadi comment: private boolean gameFinished = false;
    private String currentDialog = "";
    private int commandNumber = 0;
    private int titleScreenState = 0; // 0 = first screen, 1 = second screen, 2 = third screen
    private int simSlotCol = 0, simSlotRow = 0; // default slot position
    private int npcSlotCol = 0, npcSlotRow = 0; // default slot position for npc
    private int listSimSlotCol = 0, listSimSlotRow = 0; // default slot position for list sim
    private int kokiSlotCol = 0, kokiSlotRow = 0; // default slot position for koki
    private int subState = 0;
    private int counter = 0;
    private int charIndex = 0;
    private String combinedText = "";
    private String currentDialog2 = "";

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        try {
            maruMonica = Font.createFont(Font.TRUETYPE_FONT,
                    new FileInputStream(new File("data/fonts/x12y16pxMaruMonica.ttf")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getInputFirstSimName(){
        return inputFirstSimName;
    }
    public void setInputFirstSimName(String inputFirstSimName){
        this.inputFirstSimName = inputFirstSimName;
    }
    public void addInputFirstSimName(String nextChar){
        inputFirstSimName += nextChar;
    }

    public String getInputNamaRuangan(){
        return inputNamaRuangan;
    }
    public void setInputNamaRuangan(String inputNamaRuangan){
        this.inputNamaRuangan = inputNamaRuangan;
    }
    public void addInputNamaRuangan(String nextChar){
        inputNamaRuangan += nextChar;
    }

    public String getInputText(){
        return inputText;
    }
    public void setInputText(String inputText){
        this.inputText = inputText;
    }
    public void addInputText(String nextChar){
        inputText += nextChar;
    }

    public boolean getInputTextDone(){
        return inputTextDone;
    }
    public void setInputTextDone(boolean inputTextDone){
        this.inputTextDone = inputTextDone;
    }

    public void setDurasiTimer(int durasiTimer){
        this.durasiTimer = durasiTimer;
    }

    public int getTempDurasi() {
        return tempDurasi;
    }
    public void setTempDurasi(int tempDurasi) {
        this.tempDurasi = tempDurasi;
    }

        public String getCurrentAksi() {
        return currentAksi;
    }

    public void setCurrentAksi(String currentAksi) {
        this.currentAksi = currentAksi;
    }

    public String getCurrentAksiCadangan() {
        return currentAksiCadangan;
    }

    public void setCurrentAksiCadangan(String currentAksiCadangan) {
        this.currentAksiCadangan = currentAksiCadangan;
    }

    public boolean isCurrentAksiDone() {
        return currentAksiDone;
    }

    public void setCurrentAksiDone(boolean currentAksiDone) {
        this.currentAksiDone = currentAksiDone;
    }

    public String getCurrentDialog() {
        return currentDialog;
    }

    public void setCurrentDialog(String currentDialog) {
        this.currentDialog = currentDialog;
    }

    public int getCommandNumber() {
        return commandNumber;
    }

    public void setCommandNumber(int commandNumber) {
        this.commandNumber = commandNumber;
    }

    public int getTitleScreenState() {
        return titleScreenState;
    }

    public void setTitleScreenState(int titleScreenState) {
        this.titleScreenState = titleScreenState;
    }

    public int getSimSlotCol() {
        return simSlotCol;
    }

    public void setSimSlotCol(int simSlotCol) {
        this.simSlotCol = simSlotCol;
    }

    public int getSimSlotRow() {
        return simSlotRow;
    }

    public void setSimSlotRow(int simSlotRow) {
        this.simSlotRow = simSlotRow;
    }

    public int getNpcSlotCol() {
        return npcSlotCol;
    }

    public void setNpcSlotCol(int npcSlotCol) {
        this.npcSlotCol = npcSlotCol;
    }

    public int getNpcSlotRow() {
        return npcSlotRow;
    }

    public void setNpcSlotRow(int npcSlotRow) {
        this.npcSlotRow = npcSlotRow;
    }

    public int getListSimSlotCol() {
        return listSimSlotCol;
    }

    public void setListSimSlotCol(int listSimSlotCol) {
        this.listSimSlotCol = listSimSlotCol;
    }

    public int getListSimSlotRow() {
        return listSimSlotRow;
    }

    public void setListSimSlotRow(int listSimSlotRow) {
        this.listSimSlotRow = listSimSlotRow;
    }

    public int getKokiSlotCol() {
        return kokiSlotCol;
    }

    public void setKokiSlotCol(int kokiSlotCol) {
        this.kokiSlotCol = kokiSlotCol;
    }

    public int getKokiSlotRow() {
        return kokiSlotRow;
    }

    public void setKokiSlotRow(int kokiSlotRow) {
        this.kokiSlotRow = kokiSlotRow;
    }

    public int getSubState() {
        return subState;
    }

    public void setSubState(int subState) {
        this.subState = subState;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getCharIndex() {
        return charIndex;
    }

    public void setCharIndex(int charIndex) {
        this.charIndex = charIndex;
    }

    public String getCombinedText() {
        return combinedText;
    }

    public void setCombinedText(String combinedText) {
        this.combinedText = combinedText;
    }

    public String getCurrentDialog2() {
        return currentDialog2;
    }

    public void setCurrentDialog2(String currentDialog2) {
        this.currentDialog2 = currentDialog2;
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

        if (gamePanel.getGameState() == gamePanel.getTitleState()) { // if game is in title
            drawTitleScreen();
        } else if (gamePanel.getGameState() == gamePanel.getPlayState()) { // if game is playing
            // later
            drawMiniMap(g2d);
            // drawMessage();
        } else if (gamePanel.getGameState() == gamePanel.getPauseState()) { // if game is paused
            drawPauseScreen();
        } else if (gamePanel.getGameState() == gamePanel.getDialogState()) { // if game is in dialog
            drawDialogScreen();
        } else if (gamePanel.getGameState() == gamePanel.getSimInfoState()) { // if game is in sim info
            drawSimInfoScreen();
        } else if (gamePanel.getGameState() == gamePanel.getInventoryState()) { // if game is in inventory
            drawInventoryScreen(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()), simSlotCol, simSlotRow);
        } else if (gamePanel.getGameState() == gamePanel.getBeliState()) { // if game is in beli
            drawBeliScreen();
        } else if (gamePanel.getGameState() == gamePanel.getUpgradeRumahState()) { // if game is in upgrade rumah
            drawUpgradeRumahScreen();
        } else if (gamePanel.getGameState() == gamePanel.getInputNamaRuanganState()) { // if game is in input nama ruangan
            drawInputNamaRuanganScreen("Input nama:");
        } else if (gamePanel.getGameState() == gamePanel.getInputKoordinatBendaState()) { // if game is in input koordinat
            drawInputKoordinatBendaScreen("Input koordinat:");
        } else if (gamePanel.getGameState() == gamePanel.getAddSimState()) {
            drawInputSimNameScreen("Input nama:");
        } else if (gamePanel.getGameState() == gamePanel.getInputKoordinatRumahSimState()) {
            drawInputKoordinatRumahSimScreen("Input koordinat:");
        } else if (gamePanel.getGameState() == gamePanel.getMenuState()) {
            drawMenuScreen();
        } else if (gamePanel.getGameState() == gamePanel.getHelpState()) {
            drawHelpScreen();
        } else if (gamePanel.getGameState() == gamePanel.getChangeSimState()) {
            drawChangeSimScreen();
        } else if (gamePanel.getGameState() == gamePanel.getMapState()) {
            drawFullMapScreen(g2d);
        } else if (gamePanel.getGameState() == gamePanel.getResepState()) {
            drawResepScreen();
        } else if (gamePanel.getGameState() == gamePanel.getInputDurasiTidurState()) {
            drawInputDurasiTidurScreen("Input Durasi Tidur:");
        } else if (gamePanel.getGameState() == gamePanel.getInputDurasiNontonState()) {
            drawInputDurasiNontonScreen("Input Durasi Nonton:");
        } else if (gamePanel.getGameState() == gamePanel.getInputDurasiMandiState()) {
            drawInputDurasiMandiScreen("Input Durasi Mandi:");
        } else if (gamePanel.getGameState() == gamePanel.getInputDurasiBacaBukuState()) {
            drawInputDurasiBacaBukuScreen("Input Durasi Baca: ");
        } else if (gamePanel.getGameState() == gamePanel.getInputDurasiShalatState()) {
            drawInputDurasiShalatScreen("Input Durasi Shalat: ");
        } else if (gamePanel.getGameState() == gamePanel.getInputDurasiRadioState()) {
            drawInputDurasiRadioScreen("Input Durasi Radio:");
        } else if (gamePanel.getGameState() == gamePanel.getTimerState()) {
            drawTimerScreen();
        } else if (gamePanel.getGameState() == gamePanel.getGameOverState()) {
            drawGameOverScreen();
        } else if (gamePanel.getGameState() == gamePanel.getInputDurasiSiramTanamanState()) {
            drawInputDurasiSiramTanamanScreen("Input Durasi Siram Tanaman:");
        } else if (gamePanel.getGameState() == gamePanel.getInputDurasiMainGameState()) {
            drawInputDurasiMainGameScreen("Input Durasi Main Game:");
        } else if (gamePanel.getGameState() == gamePanel.getKerjaState()) {
            drawKerjaScreen("Menu Kerja");
        } else if (gamePanel.getGameState() == gamePanel.getInputDurasiKerjaState()) {
            drawInputDurasiKerjaScreen("Input Durasi Kerja:");
        } else if (gamePanel.getGameState() == gamePanel.getGantiPekerjaanState()) {
            drawGantiPekerjaanScreen("Pilih Pekerjaan");
        } else if (gamePanel.getGameState() == gamePanel.getSaveState()) {
            drawSaveScreen();
        } else if (gamePanel.getGameState() == gamePanel.getInputDurasiOlahragaState()) {
            drawInputDurasiOlahragaScreen("Input Durasi Olahraga:");
        } else if (gamePanel.getGameState() == gamePanel.getMelihatWaktuState()){
            drawMelihatWaktuScreen();
        } else if (gamePanel.getGameState() == gamePanel.getCutsceneState()){
            // CUTSCENE
            gamePanel.getCutsceneManager().draw(g2d);
        }
    }

    // MESSAGE
    public void drawMessage(){
        int messageX = gamePanel.getTileSize();
        int messageY = gamePanel.getTileSize() * 10;
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
            g2d.fillRect(0, 0, gamePanel.getScreenWidth(), gamePanel.getScreenHeight());

            // TITLE NAME
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 96f));
            String title = "SIM-PLICITY";
            int x = getXforCenteredText(title);
            int y = gamePanel.getTileSize() * 4 + 10;

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
                x = gamePanel.getScreenWidth() / 2 - logo.getWidth() / 2;
                y += gamePanel.getTileSize();
                g2d.drawImage(logo, x, y, null);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // MENU
            g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 48f));

            String text = "NEW GAME";
            x = getXforCenteredText(text);
            y += logo.getHeight() + gamePanel.getTileSize() * 3 - 35;
            g2d.drawString(text, x, y);
            if (commandNumber == 0) {
                g2d.drawString(">", x - gamePanel.getTileSize(), y);
            }

            text = "LOAD GAME";
            x = getXforCenteredText(text);
            y += gamePanel.getTileSize();
            g2d.drawString(text, x, y);
            if (commandNumber == 1) {
                g2d.drawString(">", x - gamePanel.getTileSize(), y);
            }

            text = "EXIT";
            x = getXforCenteredText(text);
            y += gamePanel.getTileSize();
            g2d.drawString(text, x, y);
            if (commandNumber == 2) {
                g2d.drawString(">", x - gamePanel.getTileSize(), y);
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
        int y = gamePanel.getTileSize() * 4 + 10;
        g2d.drawString(text, x, y);
        text = "(maks. 25 karakter)";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize();
        g2d.drawString(text, x, y);

        // meminta input nama karakter
        g2d.fillRect(170, y + gamePanel.getTileSize(), gamePanel.getScreenWidth() - 2 * 170, gamePanel.getTileSize());
        Graphics2D g2d2 = (Graphics2D) g2d.create();
        g2d2.setColor(Color.BLACK);
        g2d2.setFont(g2d2.getFont().deriveFont(Font.PLAIN, 37f));
        g2d2.drawString(input, 183, y + gamePanel.getTileSize() * 2 - 10);
        text = "Oke";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize() * 3;
        g2d.drawString(text, x, y);
        if (commandNumber == 0) {
            g2d.drawString(">", x - gamePanel.getTileSize(), y);
        }

        text = "Back";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize() * 2;
        g2d.drawString(text, x, y);
        if (commandNumber == 1) {
            g2d.drawString(">", x - gamePanel.getTileSize(), y);
        }
    }

    public void loadScreen() {
        // first sim name input
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 42f));

        String text = "PILIH LOAD FILE";
        int x = getXforCenteredText(text);
        int y = gamePanel.getTileSize() * 4 + 10;
        g2d.drawString(text, x, y);

        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 40f));
        text = "LOAD FILE 1";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize() * 3;
        g2d.drawString(text, x, y);
        if (commandNumber == 0) {
            g2d.drawString(">", x - gamePanel.getTileSize(), y);
        }

        text = "LOAD FILE 2";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize() * 2;
        g2d.drawString(text, x, y);
        if (commandNumber == 1) {
            g2d.drawString(">", x - gamePanel.getTileSize(), y);
        }

        text = "LOAD FILE 3";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize() * 2;
        g2d.drawString(text, x, y);
        if (commandNumber == 2) {
            g2d.drawString(">", x - gamePanel.getTileSize(), y);
        }

        text = "Back";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize() * 2;
        g2d.drawString(text, x, y);
        if (commandNumber == 3) {
            g2d.drawString(">", x - gamePanel.getTileSize(), y);
        }
    }

    // PAUSE SCREEN
    public void drawPauseScreen() {
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 80f));

        String message = "PAUSED";

        int x = getXforCenteredText(message);
        int y = gamePanel.getScreenHeight() / 2;

        g2d.drawString(message, x, y);

        // hint 
        int x2 = gamePanel.getTileSize() * 2;
        int y2 = gamePanel.getTileSize() * 13;
        int width2 = gamePanel.getTileSize()* 6;
        int height2 = gamePanel.getTileSize() * 2;
        drawSubWindow(x2, y2, width2, height2);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        g2d.drawString("[ESC] Play", x2 + 24, y2 + 60);
    }

    // DIALOG SCREEN
    public void drawDialogScreen() {
        // WINDOW
        int x = gamePanel.getTileSize() * 2;
        int y = gamePanel.getTileSize() / 2;
        int width = gamePanel.getScreenWidth() - gamePanel.getTileSize() * 4;
        int height = gamePanel.getTileSize() * 4;
        drawSubWindow(x, y, width, height);

        // TEXT
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 32f));
        x += gamePanel.getTileSize();
        y += gamePanel.getTileSize();

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
        final int frameX = gamePanel.getTileSize() * 2;
        final int frameY = gamePanel.getTileSize();
        final int frameWidth = gamePanel.getTileSize() * 12;
        final int frameHeight = frameY + gamePanel.getTileSize() + lineHeight * 6;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // TEXT
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(40f));

        // SIM INFO
        final int textXSIMINFO = getXforCenteredText("SIM INFO");
        int textYSIMINFO = frameY + gamePanel.getTileSize();
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
        g2d.drawString(" : " + gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getNama(), tailX, textY);
        textY += lineHeight;
        g2d.drawString(" : "
                + gamePanel.getCurrentSim().getPekerjaan().getListPekerjaan()[gamePanel.getCurrentSim().getPekerjaan().getIndexPekerjaan()],
                tailX, textY);
        textY += lineHeight;
        value = String.valueOf(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getUang());
        g2d.drawString(" : " + value, tailX, textY);
        textY += lineHeight;
        value = String.valueOf(" : " + gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getKesehatan() + "/"
                + gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getMaxKesehatan());
        g2d.drawString(value, tailX, textY);
        textY += lineHeight;
        value = String.valueOf(" : " + gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getKekenyangan() + "/"
                + gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getMaxKekenyangan());
        g2d.drawString(value, tailX, textY);
        textY += lineHeight;
        value = String.valueOf(" : " + gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getMood() + "/"
                + gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getMaxMood());
        g2d.drawString(value, tailX, textY);

        // hint 
        int x2 = gamePanel.getTileSize() * 2;
        int y2 = gamePanel.getTileSize() * 13;
        int width2 = gamePanel.getTileSize() * 6;
        int height2 = gamePanel.getTileSize() * 2;
        drawSubWindow(x2, y2, width2, height2);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        g2d.drawString("[ESC] Back", x2 + 24, y2 + 60);
    }

    // INVENTORY SCREEN
    public void drawInventoryScreen(Entity entity, int slotCol, int slotRow) {
        // hint 
        // draw hint window
        if (entity instanceof Sim){
            int x = gamePanel.getTileSize() * 2;
            int y = gamePanel.getTileSize() * 13;
            int width = gamePanel.getTileSize() * 6;
            int height = gamePanel.getTileSize() * 2;
            drawSubWindow(x, y, width, height);
            g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
            g2d.drawString("[ESC] Back", x + 24, y + 60);
        }

        // create frame
        int frameX = gamePanel.getTileSize() * 2;
        int frameY = gamePanel.getTileSize();
        int frameWidth = gamePanel.getScreenWidth() - gamePanel.getTileSize() * 4;
        int frameHeight = gamePanel.getScreenHeight() - gamePanel.getTileSize() * 11;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // text
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(40f));

        // inventory title
        int textYINVENTORY = frameY + gamePanel.getTileSize();
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
        int slotSize = gamePanel.getTileSize() + 1;

        // draw entity items
        for (int i = 0; i < entity.getInventory().size(); i++) {
            // equip cursor
            if (entity.getInventory().get(i) == gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentLight()) {
                g2d.setColor(new Color(240, 190, 90));
                g2d.fillRoundRect(slotX, slotY, gamePanel.getTileSize(), gamePanel.getTileSize(), 10, 10);
            }

            g2d.drawImage(entity.getInventory().get(i).getImage(), slotX, slotY, gamePanel.getTileSize(), gamePanel.getTileSize(),
                    null);

            // display amount
            if (entity == gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()) && entity.getInventory().get(i).getQuantity() > 1) {
                g2d.setFont(g2d.getFont().deriveFont(32f));
                int amountX, amountY;

                String s = "" + entity.getInventory().get(i).getQuantity();
                amountX = getXforAlignToRightText(s, slotX + 44);
                amountY = slotY + gamePanel.getTileSize();

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
        int cursorWidth = gamePanel.getTileSize();
        int cursorHeight = gamePanel.getTileSize();
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
            iFrameHeight = gamePanel.getTileSize() * 6 - 50;
        } else {
            iFrameHeight = gamePanel.getTileSize() * 6 - 20;
        }
        drawSubWindow(iFrameX, iFrameY, iFrameWidth, iFrameHeight);

        // ITEM INFO TITLE
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(40f));
        final int textXITEMINFO = getXforCenteredText("ITEM INFO");
        int textYITEMINFO = iFrameY + gamePanel.getTileSize();
        g2d.drawString("ITEM INFO", textXITEMINFO, textYITEMINFO);

        // draw item info
        int lineHeight = 35;
        int textX = iFrameX + 20;
        int textY = textYITEMINFO + lineHeight + 20;
        g2d.setFont(g2d.getFont().deriveFont(32f));

        int itemIndex = getItemIndexOnSlot(slotRow, slotCol);
        if (itemIndex < entity.getInventory().size()) {
            if (entity.getInventory().get(itemIndex) != null) {
                if (entity.getInventory().get(itemIndex) instanceof BahanMakanan) {
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
                    BahanMakanan bahanMakanan = (BahanMakanan) entity.getInventory().get(itemIndex);
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
                } else if (entity.getInventory().get(itemIndex) instanceof Makanan) {
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
                    Makanan makanan = (Makanan) entity.getInventory().get(itemIndex);
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
                } else if (entity.getInventory().get(itemIndex) instanceof Furnitur) {
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
                    Furnitur furnitur = (Furnitur) entity.getInventory().get(itemIndex);
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
                } else if (entity.getInventory().get(itemIndex) instanceof Lampu) {
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
                    Lampu lampu = (Lampu) entity.getInventory().get(itemIndex);
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
        gamePanel.getKeyHandler().setEnterPressed(false);
    }

    public void drawBeliSelect() {
        drawDialogScreen();

        // DRAW WINDOW
        int x = gamePanel.getTileSize() * 11;
        int y = gamePanel.getTileSize() * 4;
        int width = gamePanel.getTileSize() * 3;
        int height = (int) (gamePanel.getTileSize() * 2.5);
        drawSubWindow(x, y, width, height);

        // DRAW TEXTS
        x += gamePanel.getTileSize();
        y += gamePanel.getTileSize();
        g2d.drawString("Buy", x, y);
        if (commandNumber == 0) {
            g2d.drawString(">", x - gamePanel.getTileSize() / 2, y);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                subState = 1;
            }
        }
        y += gamePanel.getTileSize();
        g2d.drawString("Leave", x, y);
        if (commandNumber == 1) {
            g2d.drawString(">", x - gamePanel.getTileSize() / 2, y);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                commandNumber = 0;
                gamePanel.setGameState(gamePanel.getPlayState());
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
        drawInventoryScreen(gamePanel.getNpc()[0][4], npcSlotCol, npcSlotRow);

        // draw hint window
        int x = gamePanel.getTileSize() * 2;
        int y = gamePanel.getTileSize() * 13;
        int width = gamePanel.getTileSize() * 6;
        int height = gamePanel.getTileSize() * 2;
        drawSubWindow(x, y, width, height);
        g2d.drawString("[ESC] Back", x + 24, y + 60);

        // draw sim uang window
        x = gamePanel.getTileSize() * 8;
        y = gamePanel.getTileSize() * 13;
        width = gamePanel.getTileSize() * 6;
        height = gamePanel.getTileSize() * 2;
        drawSubWindow(x, y, width, height);
        g2d.drawString("Sim Uang : " + gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getUang(), x + 24, y + 60);

        // buy an item
        int itemIndex = getItemIndexOnSlot(npcSlotRow, npcSlotCol);
        if (itemIndex < gamePanel.getNpc()[0][4].getInventory().size()) {
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                if (gamePanel.getCurrentSim().getIsCanBuy()) {
                    int harga = 0;
                    if (gamePanel.getNpc()[0][4].getInventory().get(itemIndex) instanceof BahanMakanan) {
                        BahanMakanan makanan = (BahanMakanan) gamePanel.getNpc()[0][4].getInventory().get(itemIndex);
                        harga = makanan.getHarga();
                    } else if (gamePanel.getNpc()[0][4].getInventory().get(itemIndex) instanceof Furnitur) {
                        Furnitur furnitur = (Furnitur) gamePanel.getNpc()[0][4].getInventory().get(itemIndex);
                        harga = furnitur.getHarga();
                    } else if (gamePanel.getNpc()[0][4].getInventory().get(itemIndex) instanceof Lampu) {
                        Lampu lampu = (Lampu) gamePanel.getNpc()[0][4].getInventory().get(itemIndex);
                        harga = lampu.getHarga();
                        if (gamePanel.getCurrentSim().searchItemInInventory(lampu.getName()) != 999) {
                            gamePanel.getUi().subState = 0;
                            gamePanel.getUi().charIndex = 0;
                            gamePanel.getUi().combinedText = "";
                            gamePanel.setGameState(gamePanel.getDialogState());
                            gamePanel.getUi().currentDialog = "Anda sudah memiliki lampu!";
                        }
                    }
                    if (gamePanel.getCurrentSim().getUang() < harga) {
                        gamePanel.getUi().subState = 0;
                        gamePanel.getUi().charIndex = 0;
                        gamePanel.getUi().combinedText = "";
                        gamePanel.setGameState(gamePanel.getDialogState());
                        gamePanel.getUi().currentDialog = "Uang tidak cukup!";
                    } else {
                        gamePanel.getCurrentSim().setIsCanBuy(false);
                        gamePanel.getCurrentSim().setIndexSimSaatBeli(gamePanel.getIndexCurrentSim());
                        gamePanel.getCurrentSim().setItemBuyTempIndex(itemIndex);
                        gamePanel.getCurrentSim().setUang(gamePanel.getCurrentSim().getUang() - harga);
                        Random rand = new Random();
                        gamePanel.getCurrentSim().setRemainingTimeBuy((rand.nextInt(5) + 1) * 30);
                        gamePanel.getUi().subState = 0;
                        gamePanel.getUi().charIndex = 0;
                        gamePanel.getUi().combinedText = "";
                        gamePanel.setGameState(gamePanel.getDialogState());
                        gamePanel.getUi().currentDialog = "Anda berhasil membeli "
                                + gamePanel.getNpc()[0][4].getInventory().get(itemIndex).getName() + ".\nSilahkan tunggu "
                                + gamePanel.getCurrentSim().getRemainingTimeBuy() + " detik.";
                        // gamePanel.getCurrentSim().setIsCanBuyToTrue();
                    }

                } else {
                    gamePanel.getUi().subState = 0;
                    gamePanel.getUi().charIndex = 0;
                    gamePanel.getUi().combinedText = "";
                    gamePanel.setGameState(gamePanel.getDialogState());
                    gamePanel.getUi().currentDialog = "Tidak dapat membeli barang.\nMasih dalam proses pembelian barang\nsebelumnya.";
                }

                // if (gamePanel.getNpc()[0][4].inventory.get(itemIndex) instanceof BahanMakanan) {
                // BahanMakanan makanan = (BahanMakanan)
                // gamePanel.getNpc()[0][4].inventory.get(itemIndex);
                // if (gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).uang >= makanan.harga) {
                // if (gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).canObtainItem(makanan))
                // {
                // gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).uang -= makanan.harga;
                // } else {
                // subState = 0;
                // charIndex = 0;
                // combinedText = "";
                // gamePanel.setGameState(gamePanel.getDialogState());
                // currentDialog = "Inventory penuh";
                // }
                // } else {
                // subState = 0;
                // charIndex = 0;
                // combinedText = "";
                // gamePanel.setGameState(gamePanel.getDialogState());
                // currentDialog = "Uang tidak cukup";
                // // drawDialogScreen(); // ini ga perlu karena sudah ada di atas
                // }
                // } else if (gamePanel.getNpc()[0][4].inventory.get(itemIndex) instanceof Furnitur)
                // {
                // Furnitur furnitur = (Furnitur) gamePanel.getNpc()[0][4].inventory.get(itemIndex);
                // if (gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).uang >= furnitur.harga)
                // {
                // if (gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).canObtainItem(furnitur))
                // {
                // gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).uang -= furnitur.harga;
                // } else {
                // subState = 0;
                // charIndex = 0;
                // combinedText = "";
                // gamePanel.setGameState(gamePanel.getDialogState());
                // currentDialog = "Inventory penuh";
                // }
                // } else {
                // subState = 0;
                // charIndex = 0;
                // combinedText = "";
                // gamePanel.setGameState(gamePanel.getDialogState());
                // currentDialog = "Uang tidak cukup";
                // // drawDialogScreen(); // ini ga perlu karena sudah ada di atas
                // }
                // } else if (gamePanel.getNpc()[0][4].inventory.get(itemIndex) instanceof Lampu) {
                // Lampu lampu = (Lampu) gamePanel.getNpc()[0][4].inventory.get(itemIndex);
                // if (gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).uang >= lampu.harga) {
                // if (gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).canObtainItem(lampu)) {
                // gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).uang -= lampu.harga;
                // } else {
                // if (gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).inventory
                // .size() >= gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).maxInventorySize)
                // {
                // subState = 0;
                // charIndex = 0;
                // combinedText = "";
                // gamePanel.setGameState(gamePanel.getDialogState());
                // currentDialog = "Inventory penuh";
                // } else {
                // subState = 0;
                // charIndex = 0;
                // combinedText = "";
                // gamePanel.setGameState(gamePanel.getDialogState());
                // currentDialog = "Anda sudah memiliki lampu!";
                // }
                // }
                // } else {
                // subState = 0;
                // charIndex = 0;
                // combinedText = "";
                // gamePanel.setGameState(gamePanel.getDialogState());
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
        int frameX = gamePanel.getTileSize() * 2;
        int frameY = gamePanel.getTileSize();
        int frameWidth = gamePanel.getScreenWidth() - gamePanel.getTileSize() * 4;
        int frameHeight = gamePanel.getScreenHeight() - gamePanel.getTileSize() * 11;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // text
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(40f));

        // inventory title
        int textYINVENTORY = frameY + gamePanel.getTileSize();
        final int textXINVENTORY = getXforCenteredText("LIST SIM");
        g2d.drawString("LIST SIM", textXINVENTORY, textYINVENTORY);

        // slot
        final int slotXstart = frameX + 19;
        final int slotYstart = textYINVENTORY + 19;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gamePanel.getTileSize() + 1;

        // draw entity items
        for (int i = 0; i < gamePanel.getListSim().size(); i++) {

            // kuningin sim yang dipilih
            if (gamePanel.getListSim().get(i) == gamePanel.getListSim().get(gamePanel.getIndexCurrentSim())) {
                g2d.setColor(new Color(240, 190, 90));
                g2d.fillRoundRect(slotX, slotY, gamePanel.getTileSize(), gamePanel.getTileSize(), 10, 10);
            }

            g2d.drawImage(gamePanel.getListSim().get(i).getDown1(), slotX, slotY, gamePanel.getTileSize(), gamePanel.getTileSize(), null);

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
        int cursorWidth = gamePanel.getTileSize();
        int cursorHeight = gamePanel.getTileSize();
        // draw cursor
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(3)); // ngubah ukuran stroke
        g2d.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

        // item info
        int iFrameX = frameX;
        int iFrameY = frameY + frameHeight + 10;
        int iFrameWidth = frameWidth;
        int iFrameHeight = gamePanel.getTileSize() * 6 - 20 - 35 - 30;
        drawSubWindow(iFrameX, iFrameY, iFrameWidth, iFrameHeight);

        // ITEM INFO TITLE
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(40f));
        final int textXITEMINFO = getXforCenteredText("SIM INFO");
        int textYITEMINFO = iFrameY + gamePanel.getTileSize();
        g2d.drawString("SIM INFO", textXITEMINFO, textYITEMINFO);

        // draw item info
        int lineHeight = 35;
        int textX = iFrameX + 20;
        int textY = textYITEMINFO + lineHeight + 20;
        g2d.setFont(g2d.getFont().deriveFont(32f));

        int itemIndex = getItemIndexOnSlot(listSimSlotRow, listSimSlotCol);
        if (itemIndex < gamePanel.getListSim().size()) {
            if (gamePanel.getListSim().get(itemIndex) != null) {

                // write item info
                g2d.drawString("Nama", textX, textY);
                textY += lineHeight;
                g2d.drawString("Pekerjaan", textX, textY);
                textY += lineHeight;
                g2d.drawString("Koordinat Rumah", textX, textY);
                // write item value
                int iTailX = iFrameX + iFrameWidth / 3 + gamePanel.getTileSize();
                textY = textYITEMINFO + lineHeight + 20;
                String iValue;
                Sim sim = gamePanel.getListSim().get(itemIndex);
                g2d.drawString(" : " + sim.getNama(), iTailX, textY);
                textY += lineHeight;
                g2d.drawString(" : " + sim.getPekerjaan().getListPekerjaan()[sim.getPekerjaan().getIndexPekerjaan()], iTailX, textY);
                textY += lineHeight;
                iValue = String.valueOf(" : " + sim.getRumah().getColRumah() + ", " + sim.getRumah().getRowRumah());
                g2d.drawString(iValue, iTailX, textY);

                textY = textYITEMINFO + lineHeight + 20;
            }
        }

        // hint 
        int x2 = gamePanel.getTileSize() * 2;
        int y2 = gamePanel.getTileSize() * 13;
        int width2 = gamePanel.getTileSize() * 6;
        int height2 = gamePanel.getTileSize() * 2;
        drawSubWindow(x2, y2, width2, height2);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        g2d.drawString("[ESC] Back", x2 + 24, y2 + 60);

        // batas
    }

    public void drawUpgradeRumahScreen() {
        // draw window
        int x = getXforCenteredText("UPGRADE RUMAH");
        x -= 3 * gamePanel.getTileSize();
        int y = gamePanel.getTileSize() * 4;
        int width = gamePanel.getScreenWidth() - 2 * x;
        int height = gamePanel.getScreenHeight() - y - gamePanel.getTileSize() * 4;
        drawSubWindow(x, y, width, height);

        // draw UPGRADE RUMAH text
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 35f));
        x = getXforCenteredText("UPGRADE RUMAH");
        y += gamePanel.getTileSize() + 5;
        g2d.drawString("UPGRADE RUMAH", x, y);

        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        String text = "UP";
        x = getXforCenteredText(text);
        y += 2 * gamePanel.getTileSize();
        g2d.drawString(text, x, y);
        if (commandNumber == 0) {
            g2d.drawString(">", x - gamePanel.getTileSize(), y);
        }

        text = "DOWN";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize();
        g2d.drawString(text, x, y);
        if (commandNumber == 1) {
            g2d.drawString(">", x - gamePanel.getTileSize(), y);
        }

        text = "LEFT";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize();
        g2d.drawString(text, x, y);
        if (commandNumber == 2) {
            g2d.drawString(">", x - gamePanel.getTileSize(), y);
        }

        text = "RIGHT";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize();
        g2d.drawString(text, x, y);
        if (commandNumber == 3) {
            g2d.drawString(">", x - gamePanel.getTileSize(), y);
        }

        // draw hint text
        x = gamePanel.getTileSize() * 2;
        y = gamePanel.getTileSize() * 13;
        width = gamePanel.getTileSize() * 6;
        height = gamePanel.getTileSize() * 2;
        drawSubWindow(x, y, width-24, height);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        g2d.drawString("Harga: 1500", x + 24, y + 60);

        // hint 
        int x2 = gamePanel.getTileSize() * 2;
        int y2 = gamePanel.getTileSize() * 13;
        int width2 = gamePanel.getTileSize() * 6;
        int height2 = gamePanel.getTileSize() * 2;
        drawSubWindow(x2 + width, y2, width2, height2);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        g2d.drawString("[ESC] Back", x2 + width + 24, y2 + 60);
    }

    public void drawInputKoordinatBendaScreen(String judul) {

        // draw window
        int x = getXforCenteredText(judul);
        x -= 3 * gamePanel.getTileSize();
        int y = gamePanel.getTileSize() * 4;
        int width = gamePanel.getScreenWidth() - 2 * x;
        int height = gamePanel.getScreenHeight() - y - gamePanel.getTileSize() * 5;
        drawSubWindow(x, y, width, height);

        // draw judul text
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 35f));
        x = getXforCenteredText(judul);
        y += gamePanel.getTileSize() + 5;
        g2d.drawString(judul, x, y);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        String text = "(format : x,y)";
        String textTemp = "(maks. 15 karakter)";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize() - 10;
        g2d.drawString(text, x, y);

        // draw input text
        // x -= 40;
        x = getXforCenteredText(textTemp);
        y += gamePanel.getTileSize() * 2;
        width = width - 2 * gamePanel.getTileSize() + 15;
        height = gamePanel.getTileSize();
        g2d.fillRect(x, y, width, height);

        Graphics2D g2d2 = (Graphics2D) g2d.create();
        g2d2.setColor(Color.BLACK);
        g2d2.setFont(g2d2.getFont().deriveFont(Font.PLAIN, 30f));
        g2d2.drawString(gamePanel.getUi().inputText, x + 10, y + gamePanel.getTileSize() - 14);

        int x2 = gamePanel.getTileSize() * 2;
        int y2 = gamePanel.getTileSize() * 13;
        int width2 = gamePanel.getTileSize() * 6;
        int height2 = gamePanel.getTileSize() * 2;
        drawSubWindow(x2, y2, width2, height2);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        g2d.drawString("[ESC] Back", x2 + 24, y2 + 60);
    }

    public void drawInputNamaRuanganScreen(String judul) {
        // draw window
        int x = getXforCenteredText(judul);
        x -= 3 * gamePanel.getTileSize();
        int y = gamePanel.getTileSize() * 4;
        int width = gamePanel.getScreenWidth() - 2 * x;
        int height = gamePanel.getScreenHeight() - y - gamePanel.getTileSize() * 5;
        drawSubWindow(x, y, width, height);

        // draw judul text
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 35f));
        x = getXforCenteredText(judul);
        y += gamePanel.getTileSize() + 5;
        g2d.drawString(judul, x, y);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        String text = "(maks. 15 karakter)";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize() - 10;
        g2d.drawString(text, x, y);

        // draw input text
        // x -= 40;
        y += gamePanel.getTileSize() * 2;
        width = width - 2 * gamePanel.getTileSize() + 15;
        height = gamePanel.getTileSize();
        g2d.fillRect(x, y, width, height);

        Graphics2D g2d2 = (Graphics2D) g2d.create();
        g2d2.setColor(Color.BLACK);
        g2d2.setFont(g2d2.getFont().deriveFont(Font.PLAIN, 30f));
        g2d2.drawString(gamePanel.getUi().inputText, x + 10, y + gamePanel.getTileSize() - 14);

        // hint 
        int x2 = gamePanel.getTileSize() * 2;
        int y2 = gamePanel.getTileSize() * 13;
        int width2 = gamePanel.getTileSize() * 6;
        int height2 = gamePanel.getTileSize() * 2;
        drawSubWindow(x2, y2, width2, height2);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        g2d.drawString("[ESC] Back", x2 + 24, y2 + 60);
    }

    public void drawInputSimNameScreen(String judul) {
        int x = getXforCenteredText(judul);
        x -= 3 * gamePanel.getTileSize();
        int y = gamePanel.getTileSize() * 4;
        int width = gamePanel.getScreenWidth() - 2 * x;
        int height = gamePanel.getScreenHeight() - y - gamePanel.getTileSize() * 5;
        drawSubWindow(x, y, width, height);

        // draw judul text
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 35f));
        x = getXforCenteredText(judul);
        y += gamePanel.getTileSize() + 5;
        g2d.drawString(judul, x, y);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        String text = "(maks. 15 karakter)";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize() - 10;
        g2d.drawString(text, x, y);

        // draw input text
        // x -= 40;
        y += gamePanel.getTileSize() * 2;
        width = width - 2 * gamePanel.getTileSize() + 15;
        height = gamePanel.getTileSize();
        g2d.fillRect(x, y, width, height);

        Graphics2D g2d2 = (Graphics2D) g2d.create();
        g2d2.setColor(Color.BLACK);
        g2d2.setFont(g2d2.getFont().deriveFont(Font.PLAIN, 30f));
        g2d2.drawString(gamePanel.getUi().inputText, x + 10, y + gamePanel.getTileSize() - 14);

        // hint 
        int x2 = gamePanel.getTileSize() * 2;
        int y2 = gamePanel.getTileSize() * 13;
        int width2 = gamePanel.getTileSize() * 6;
        int height2 = gamePanel.getTileSize() * 2;
        drawSubWindow(x2, y2, width2, height2);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        g2d.drawString("[ESC] Back", x2 + 24, y2 + 60);

    }

    public void drawInputKoordinatRumahSimScreen(String judul) {
        // draw window
        int x = getXforCenteredText(judul);
        x -= 3 * gamePanel.getTileSize();
        int y = gamePanel.getTileSize() * 4;
        int width = gamePanel.getScreenWidth() - 2 * x;
        int height = gamePanel.getScreenHeight() - y - gamePanel.getTileSize() * 5;
        drawSubWindow(x, y, width, height);

        // draw judul text
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 35f));
        x = getXforCenteredText(judul);
        y += gamePanel.getTileSize() + 5;
        g2d.drawString(judul, x, y);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        String text = "(format : x,y)";
        String textTemp = "(maks. 15 karakter)";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize() - 10;
        g2d.drawString(text, x, y);

        // draw input text
        // x -= 40;
        x = getXforCenteredText(textTemp);
        y += gamePanel.getTileSize() * 2;
        width = width - 2 * gamePanel.getTileSize() + 15;
        height = gamePanel.getTileSize();
        g2d.fillRect(x, y, width, height);

        Graphics2D g2d2 = (Graphics2D) g2d.create();
        g2d2.setColor(Color.BLACK);
        g2d2.setFont(g2d2.getFont().deriveFont(Font.PLAIN, 30f));
        g2d2.drawString(gamePanel.getUi().inputText, x + 10, y + gamePanel.getTileSize() - 14);
    }

    // RESEP SCREEN
    public void drawResepScreen() {
        drawInventoryScreen(gamePanel.getKokiTemp(), kokiSlotCol, kokiSlotRow);

        // draw hint window
        int x = gamePanel.getTileSize() * 2;
        int y = gamePanel.getTileSize() * 13;
        int width = gamePanel.getTileSize() * 6;
        int height = gamePanel.getTileSize() * 2;
        drawSubWindow(x, y, width, height);
        g2d.drawString("[ESC] Back", x + 24, y + 60);

        // cook makanan
        int itemIndex = getItemIndexOnSlot(kokiSlotRow, kokiSlotCol);
        if (itemIndex < gamePanel.getKokiTemp().getInventory().size()) {
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                if (gamePanel.getKokiTemp().getInventory().get(itemIndex) instanceof Makanan) {
                    Makanan makanan = (Makanan) gamePanel.getKokiTemp().getInventory().get(itemIndex);
                    // check inventory containsAll makanan.bahan
                    java.util.List<String> bahanInInventory = new java.util.ArrayList<>();
                    for (int i = 0; i < gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getInventory().size(); i++) {
                        if (gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getInventory().get(i) instanceof BahanMakanan) {
                            BahanMakanan bahan = (BahanMakanan) gamePanel.getListSim()
                                    .get(gamePanel.getIndexCurrentSim()).getInventory().get(i);
                            bahanInInventory.add(bahan.getName());
                        }
                    }
                    // ini bisa jadi penerapan generics
                    if (bahanInInventory.containsAll(makanan.getBahan())) {
                        for (int i = 0; i < gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getInventory().size(); i++) {
                            if (gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getInventory()
                                    .get(i) instanceof BahanMakanan) {
                                BahanMakanan bahanInv = (BahanMakanan) gamePanel.getListSim()
                                        .get(gamePanel.getIndexCurrentSim()).getInventory().get(i);
                                if (makanan.getBahan().contains(bahanInv.getName())) {
                                    if (bahanInv.getQuantity() > 1) {
                                        gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getInventory().get(i).decQuantity(1);
                                    } else {
                                        gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getInventory().remove(i);
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
                        gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).canObtainItem(makanan);

                        durasiTimer = (int) (3 * makanan.getKekenyangan()/ 2);
                        int durasi = durasiTimer;

                        // nambah world time
                        for (int i = 0; i < gamePanel.getListSim().size(); i++) {
                            gamePanel.getListSim().get(i).getPekerjaan().setWorldTimeCounterForStartJobAfterChangeJob(gamePanel.getListSim().get(i)
                                .getPekerjaan().getWorldTimeCounterForStartJobAfterChangeJob() + durasi);
                            gamePanel.getListSim().get(i).setEfekWaktuTidakTidurCounter(gamePanel.getListSim().get(i).getEfekWaktuTidakTidurCounter() + durasi);
                            if (gamePanel.getListSim().get(i).getIsUdahMakanDalamSatuHari()){
                                gamePanel.getListSim().get(i).setEfekWaktuTidakBuangAirCounter(gamePanel.getListSim().get(i).getEfekWaktuTidakBuangAirCounter() + durasi);
                            }
                        }
                        currentAksi = "Masak";
                        currentAksiCadangan = makanan.getName()+ " berhasil dibuat dan\ndimasukkan ke inventory!";
                        // mulai masak : draw dialog
                        gamePanel.setGameState(gamePanel.getTimerState());
                        currentAksiDone = false;
                        gamePanel.getUi().tempDurasi = (int) (3 * makanan.getKekenyangan()/ 2);
                        gamePanel.getKeyHandler().setThreadTemp(startTimerThread((int) (3 * makanan.getKekenyangan()/ 2)));

                    } else {
                        charIndex = 0;
                        combinedText = "";
                        gamePanel.setGameState(gamePanel.getDialogState());
                        currentDialog = "Bahan tidak cukup untuk memasak\n" + makanan.getName()+ "!";
                    }
                }
            }
        }
        gamePanel.getKeyHandler().setEnterPressed(false);
    }

    // Menu SCREEN
    public void drawMenuScreen() {
        // create frame
        int frameX = gamePanel.getTileSize() * 4;
        int frameY = gamePanel.getTileSize() * 2;
        int frameWidth = gamePanel.getScreenWidth() - gamePanel.getTileSize() * 8;
        int frameHeight = gamePanel.getScreenHeight() - gamePanel.getTileSize() * 6;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight-10);

        // title
        g2d.setFont(g2d.getFont().deriveFont(32f));
        String text = "OPTIONS";
        int x = getXforCenteredText(text);
        int y = gamePanel.getTileSize() * 3;
        g2d.setColor(Color.WHITE);
        g2d.drawString(text, x, y);

        // texts
        g2d.setFont(g2d.getFont().deriveFont(32f));
        x = gamePanel.getTileSize() * 5;
        text = "Music";
        y += gamePanel.getTileSize() * 2 - 24;
        g2d.drawString(text, x, y);
        if (commandNumber == 0) {
            int xRect = x + gamePanel.getTileSize() * 4;
            int yRect = y - 24;
            g2d.drawString(">", x - gamePanel.getTileSize() / 2, y);
            g2d.drawRect(xRect, yRect, 120, 24);
            int volumeWidth = 24 * gamePanel.music.volumeScale;
            g2d.fillRect(xRect, yRect, volumeWidth, 24);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                commandNumber = 0;
                gamePanel.setGameState(gamePanel.getPlayState());
            }
        }

        text = "Sound Effect";
        y += gamePanel.getTileSize();
        g2d.drawString(text, x, y);
        if (commandNumber == 1) {
            int xRect = x + gamePanel.getTileSize() * 4;
            int yRect = y - 24;
            g2d.drawString(">", x - gamePanel.getTileSize() / 2, y);
            g2d.drawRect(xRect, yRect, 120, 24);
            int volumeWidth = 24 * gamePanel.soundEffect.volumeScale;
            g2d.fillRect(xRect, yRect, volumeWidth, 24);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                commandNumber = 0;
                gamePanel.setGameState(gamePanel.getPlayState());
            }
        }

        text = "Help";
        y += gamePanel.getTileSize();
        g2d.drawString(text, x, y);
        if (commandNumber == 2) {
            g2d.drawString(">", x - gamePanel.getTileSize() / 2, y);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                commandNumber = 0;
                gamePanel.setGameState(gamePanel.getPlayState());
            }
        }

        text = "Save";
        y += gamePanel.getTileSize();
        g2d.drawString(text, x, y);
        if (commandNumber == 3) {
            g2d.drawString(">", x - gamePanel.getTileSize() / 2, y);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                commandNumber = 0;
                gamePanel.setGameState(gamePanel.getPlayState());
            }
        }

        text = "Main Menu";
        y += gamePanel.getTileSize();
        g2d.drawString(text, x, y);
        if (commandNumber == 4) {
            g2d.drawString(">", x - gamePanel.getTileSize() / 2, y);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                commandNumber = 0;
                gamePanel.setGameState(gamePanel.getPlayState());
            }
        }

        text = "Credits";
        y += gamePanel.getTileSize();
        g2d.drawString(text, x, y);
        if (commandNumber == 5) {
            g2d.drawString(">", x - gamePanel.getTileSize() / 2, y);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                commandNumber = 0;
                gamePanel.setGameState(gamePanel.getCutsceneState());
            }
        }

        text = "Back";
        y += gamePanel.getTileSize() * 2 - 24;
        g2d.drawString(text, x, y);
        if (commandNumber == 6) {
            g2d.drawString(">", x - gamePanel.getTileSize() / 2, y);
            if (gamePanel.getKeyHandler().isEnterPressed()) {
                commandNumber = 0;
                gamePanel.setGameState(gamePanel.getPlayState());
            }
        }

        // hint 
        int x2 = gamePanel.getTileSize() * 2;
        int y2 = gamePanel.getTileSize() * 13;
        int width2 = gamePanel.getTileSize() * 6;
        int height2 = gamePanel.getTileSize() * 2;
        drawSubWindow(x2, y2, width2, height2);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        g2d.drawString("[ESC] Back", x2 + 24, y2 + 60);
    }

    public void drawSaveScreen() {
        // create frame
        int frameX = gamePanel.getTileSize() * 4;
        int frameY = gamePanel.getTileSize() * 3;
        int frameWidth = gamePanel.getScreenWidth() - gamePanel.getTileSize() * 8;
        int frameHeight = gamePanel.getScreenHeight() - gamePanel.getTileSize() * 6;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // title
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 35f));
        String text = "PILIH SAVE FILE";
        int x = getXforCenteredText(text);
        int y = gamePanel.getTileSize() * 4;
        g2d.setColor(Color.WHITE);
        g2d.drawString(text, x, y);

        // texts
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 32f));
        text = "SAVE FILE 1";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize() * 2;
        g2d.drawString(text, x, y);
        if (commandNumber == 0) {
            g2d.drawString(">", x - gamePanel.getTileSize() / 2, y);
        }

        text = "SAVE FILE 2";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize() * 2;
        g2d.drawString(text, x, y);
        if (commandNumber == 1) {
            g2d.drawString(">", x - gamePanel.getTileSize() / 2, y);
        }

        text = "SAVE FILE 3";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize() * 2;
        g2d.drawString(text, x, y);
        if (commandNumber == 2) {
            g2d.drawString(">", x - gamePanel.getTileSize() / 2, y);
        }

        text = "Back";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize() * 2;
        g2d.drawString(text, x, y);
        if (commandNumber == 3) {
            g2d.drawString(">", x - gamePanel.getTileSize() / 2, y);
        }

    }

    // HELP SCREEN
    public void drawHelpScreen() {
        // create frame
        int frameX = gamePanel.getTileSize() * 2;
        int frameY = gamePanel.getTileSize() * 2;
        int frameWidth = gamePanel.getScreenWidth() - gamePanel.getTileSize() * 4;
        int frameHeight = gamePanel.getScreenHeight() - gamePanel.getTileSize() * 4;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // title
        g2d.setFont(g2d.getFont().deriveFont(40f));
        String text = "HELP";
        int x = getXforCenteredText(text);
        int y = gamePanel.getTileSize() * 3;
        g2d.setColor(Color.WHITE);
        g2d.drawString(text, x, y);

        // texts
        g2d.setFont(g2d.getFont().deriveFont(30f));
        x = gamePanel.getTileSize() * 3;
        text = "T: Melihat Waktu";
        y += gamePanel.getTileSize() * 2;
        g2d.drawString(text, x, y);

        text = "I: Melihat Inventory";
        y += gamePanel.getTileSize();
        g2d.drawString(text, x, y);

        text = "L: Melihat Lokasi";
        y += gamePanel.getTileSize();
        g2d.drawString(text, x, y);

        text = "C: Melihat Sim Info";
        y += gamePanel.getTileSize();
        g2d.drawString(text, x, y);

        text = "X: Melihat Mini Map";
        y += gamePanel.getTileSize();
        g2d.drawString(text, x, y);

        text = "M: Melihat Map";
        y += gamePanel.getTileSize();
        g2d.drawString(text, x, y);

        text = "Z: Mengambil Objek";
        y += gamePanel.getTileSize();
        g2d.drawString(text, x, y);

        text = "ESC: Melihat Options";
        y += gamePanel.getTileSize();
        g2d.drawString(text, x, y);

        x = gamePanel.getTileSize() * 9;
        text = "P: Pause";
        y = gamePanel.getTileSize() * 5;
        g2d.drawString(text, x, y);

        text = "K: Kerja";
        y += gamePanel.getTileSize();
        g2d.drawString(text, x, y);

        text = "O: Olahraga";
        y += gamePanel.getTileSize();
        g2d.drawString(text, x, y);

        text = "B: Beli Barang";
        y += gamePanel.getTileSize();
        g2d.drawString(text, x, y);

        text = "G: Change Sim";
        y += gamePanel.getTileSize();
        g2d.drawString(text, x, y);

        text = "U: Upgrade Rumah";
        y += gamePanel.getTileSize();
        g2d.drawString(text, x, y);

        text = "ENTER: Interaksi";
        y += gamePanel.getTileSize();
        g2d.drawString(text, x, y);
    }

    public void drawFullMapScreen(Graphics2D g2d) {
        // Background Color
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, gamePanel.getScreenWidth(), gamePanel.getScreenHeight());

        // Draw Map
        int width = 500;
        int height = 500;
        int x = gamePanel.getScreenWidth() / 2 - width / 2;
        int y = gamePanel.getScreenHeight() / 2 - height / 2;
        g2d.drawImage(gamePanel.getMap().worldMap[gamePanel.getCurrentSim().getCurrentMap()], x, y, width, height, null);

        // Draw Player
        double scale = (double) (gamePanel.getTileSize() * gamePanel.getMaxWorldCol()) / width;
        int playerX = (int) (x + gamePanel.getCurrentSim().getWorldX() / scale);
        int playerY = (int) (y + gamePanel.getCurrentSim().getWorldY() / scale);
        int playerSize = (int) (gamePanel.getTileSize() / scale) * 2;
        g2d.drawImage(gamePanel.getCurrentSim().getDown1(), playerX, playerY, playerSize,
                playerSize, null);

        // draw benda
        int bendaSize = (int) (gamePanel.getTileSize() / scale);
        if (gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap() == 0) {
            for (int i = 0; i < gamePanel.getListRumah()[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()]
                    .size(); i++) {
                int bendaX = (int) (x
                        + gamePanel.getListRumah()[gamePanel.getCurrentSim().getCurrentMap()].get(i).getWorldX() / scale);
                int bendaY = (int) (y
                        + gamePanel.getListRumah()[gamePanel.getCurrentSim().getCurrentMap()].get(i).getWorldY() / scale);
                if (gamePanel.getListRumah()[gamePanel.getCurrentSim().getCurrentMap()].get(i) != null) {
                    g2d.drawImage(gamePanel.getListRumah()[gamePanel.getCurrentSim().getCurrentMap()].get(i).getImage(), bendaX,
                            bendaY, bendaSize, bendaSize, null);
                }
            }
        } else {

            for (int i = 0; i < gamePanel.getListSim()
                    .get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                    .get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getBendaRuangan()
                    .size(); i++) {
                int bendaX = (int) (x +
                        gamePanel.getListSim()
                                .get(gamePanel.getListSim()
                                        .get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                                .get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getBendaRuangan()
                                .get(i).getWorldX() / scale);
                int bendaY = (int) (y +
                        gamePanel.getListSim()
                                .get(gamePanel.getListSim()
                                        .get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                                .get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getBendaRuangan()
                                .get(i).getWorldY()/ scale);
                if (gamePanel.getListSim()
                        .get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                        .get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getBendaRuangan()
                        .get(i) != null) {
                    g2d.drawImage(
                            gamePanel.getListSim()
                                    .get(gamePanel.getListSim()
                                            .get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                                    .get(gamePanel.getListSim()
                                            .get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getBendaRuangan()
                                    .get(i).getImage(),
                            bendaX,
                            bendaY, bendaSize, bendaSize, null);
                }
            }
        }

        // hint 
        int x2 = gamePanel.getTileSize() * 2;
        int y2 = gamePanel.getTileSize() * 13;
        g2d.setColor(Color.white);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        g2d.drawString("[ESC] Back", x2 + 24, y2 + 85);
    }

    public void drawMiniMap(Graphics2D g2d) {
        if (gamePanel.map.getMapOn() == true) {

            // Draw Map
            int width = 200;
            int height = 200;
            int x = gamePanel.getScreenWidth() - width - 50;
            int y = 50;
            double scale = (double) (gamePanel.getTileSize() * gamePanel.getMaxWorldCol()) / width;

            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
            g2d.drawImage(gamePanel.map.worldMap[gamePanel.getCurrentSim().getCurrentMap()],
                    x, y, width, height, null);

            // Draw Benda

            int bendaSize = (int) (gamePanel.getTileSize() / scale) * 2;
            if (gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap() == 0) {
                for (int i = 0; i < gamePanel.getListRumah()[gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()]
                        .size(); i++) {
                    int bendaX = (int) (x
                            + gamePanel.getListRumah()[gamePanel.getCurrentSim().getCurrentMap()].get(i).getWorldX()/ scale);
                    int bendaY = (int) (y
                            + gamePanel.getListRumah()[gamePanel.getCurrentSim().getCurrentMap()].get(i).getWorldY() / scale);
                    if (gamePanel.getListRumah()[gamePanel.getCurrentSim().getCurrentMap()].get(i) != null) {
                        g2d.drawImage(gamePanel.getListRumah()[gamePanel.getCurrentSim().getCurrentMap()].get(i).getImage(), bendaX,
                                bendaY, bendaSize, bendaSize, null);
                    }
                }
            } else {

                for (int i = 0; i < gamePanel.getListSim()
                        .get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                        .get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getBendaRuangan()
                        .size(); i++) {
                    int bendaX = (int) (x +
                            gamePanel.getListSim()
                                    .get(gamePanel.getListSim()
                                            .get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                                    .get(gamePanel.getListSim()
                                            .get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getBendaRuangan()
                                    .get(i).getWorldX()/ scale);
                    int bendaY = (int) (y +
                            gamePanel.getListSim()
                                    .get(gamePanel.getListSim()
                                            .get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                                    .get(gamePanel.getListSim()
                                            .get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getBendaRuangan()
                                    .get(i).getWorldY() / scale);
                    if (gamePanel.getListSim()
                            .get(gamePanel.getListSim()
                                    .get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                            .get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getBendaRuangan()
                            .get(i) != null) {
                        g2d.drawImage(
                                gamePanel.getListSim()
                                        .get(gamePanel.getListSim()
                                                .get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                                        .get(gamePanel.getListSim()
                                                .get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getBendaRuangan()
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
            int playerSize = (int) (gamePanel.getTileSize() / 6);
            g2d.drawImage(gamePanel.getCurrentSim().getDown1(), playerX - 3, playerY - 3,
                    playerSize,
                    playerSize, null);

            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        }
    }

    public void drawGameOverScreen() {
        System.out.println("masuk game over");
        g2d.setColor(new Color(0, 0, 0, 150));
        g2d.fillRect(0, 0, gamePanel.getScreenWidth(), gamePanel.getScreenHeight());

        int x;
        int y;
        String text;
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 70f));

        text = "Mati Akibat " + Sim.getKeteranganMati();
        // shadow
        g2d.setColor(Color.BLACK);
        x = getXforCenteredText(text);
        y = gamePanel.getTileSize() * 6;
        g2d.drawString(text, x + 4, y + 4);
        // text
        g2d.setColor(Color.WHITE);
        g2d.drawString(text, x, y);

        // retry
        if (gamePanel.getIsOneSim()) {
            g2d.setFont(g2d.getFont().deriveFont(50f));
            text = "Change Sim";
            x = getXforCenteredText(text);
            y += gamePanel.getTileSize() * 4;
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

        } else if (gamePanel.getListSim().size() >= 1) {
            g2d.setFont(g2d.getFont().deriveFont(50f));
            text = "Change Sim";
            x = getXforCenteredText(text);
            y += gamePanel.getTileSize() * 4;
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
        gamePanel.getKeyHandler().setCheckWorldTime(false);
        gamePanel.getKeyHandler().setCheckCurrentLocation(false);
        // draw window
        int x = getXforCenteredText("Sedang " + currentAksi + "...");
        x -= 4 * gamePanel.getTileSize();
        int y = gamePanel.getTileSize() * 4;
        int width = gamePanel.getScreenWidth() - 2 * x;
        int height = gamePanel.getScreenHeight() - y - gamePanel.getTileSize() * 5;
        drawSubWindow(x, y, width, height);

        // draw judul text
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 35f));
        x = getXforCenteredText("Sedang " + currentAksi + "...");
        y += gamePanel.getTileSize() + 5;
        g2d.drawString("Sedang " + currentAksi + "...", x, y);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        String text = "(dalam detik)";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize() - 10;
        g2d.drawString(text, x, y);

        // draw timer
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 50f));
        text = String.valueOf(durasiTimer);
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize() * 3;
        g2d.drawString(text, x, y);

        // draw hint text
        x = gamePanel.getTileSize() * 2;
        y = gamePanel.getTileSize() * 13;
        width = gamePanel.getTileSize() * 6;
        height = gamePanel.getTileSize() * 2;
        drawSubWindow(x, y, width, height);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        g2d.drawString("[ESC] Skip", x + 24, y + 60);
    }

    // ----------------------------------- BATAS SUCI
    // ----------------------------------------
    public void drawInputDurasiTidurScreen(String judul) {
        // hint 
        int x2 = gamePanel.getTileSize() * 2;
        int y2 = gamePanel.getTileSize() * 13;
        int width2 = gamePanel.getTileSize() * 6;
        int height2 = gamePanel.getTileSize() * 2;
        drawSubWindow(x2, y2, width2, height2);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        g2d.drawString("[ESC] Back", x2 + 24, y2 + 60);

        // draw window
        int x = getXforCenteredText(judul);
        x -= 2 * gamePanel.getTileSize();
        int y = gamePanel.getTileSize() * 4;
        int width = gamePanel.getScreenWidth() - 2 * x;
        int height = gamePanel.getScreenHeight() - y - gamePanel.getTileSize() * 5;
        drawSubWindow(x, y, width, height);

        // draw judul text
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 35f));
        x = getXforCenteredText(judul);
        y += gamePanel.getTileSize() + 5;
        g2d.drawString(judul, x, y);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        String text = "(dalam detik)";
        String textTemp = "(maks. 15 karakter)";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize() - 10;
        g2d.drawString(text, x, y);

        // draw input text
        x = getXforCenteredText(textTemp);
        y += gamePanel.getTileSize() * 2;
        width = gamePanel.getScreenWidth() - 2 * x;
        height = gamePanel.getTileSize();
        g2d.fillRect(x, y, width, height);

        // draw text
        Graphics2D g2d2 = (Graphics2D) g2d.create();
        g2d2.setColor(Color.BLACK);
        g2d2.setFont(g2d2.getFont().deriveFont(Font.PLAIN, 30f));
        g2d2.drawString(gamePanel.getUi().inputText, x + 10, y + gamePanel.getTileSize() - 14);
    }

    public void drawInputDurasiNontonScreen(String judul) {
        // hint 
        int x2 = gamePanel.getTileSize() * 2;
        int y2 = gamePanel.getTileSize() * 13;
        int width2 = gamePanel.getTileSize() * 6;
        int height2 = gamePanel.getTileSize() * 2;
        drawSubWindow(x2, y2, width2, height2);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        g2d.drawString("[ESC] Back", x2 + 24, y2 + 60);

        // draw window
        int x = getXforCenteredText(judul);
        x -= 2 * gamePanel.getTileSize();
        int y = gamePanel.getTileSize() * 4;
        int width = gamePanel.getScreenWidth() - 2 * x;
        int height = gamePanel.getScreenHeight() - y - gamePanel.getTileSize() * 5;
        drawSubWindow(x, y, width, height);

        // draw judul text
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 35f));
        x = getXforCenteredText(judul);
        y += gamePanel.getTileSize() + 5;
        g2d.drawString(judul, x, y);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        String text = "(dalam detik)";
        String textTemp = "(maks. 15 karakter)";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize() - 10;
        g2d.drawString(text, x, y);

        // draw input text
        x = getXforCenteredText(textTemp);
        y += gamePanel.getTileSize() * 2;
        width = gamePanel.getScreenWidth() - 2 * x;
        height = gamePanel.getTileSize();
        g2d.fillRect(x, y, width, height);

        // draw text
        Graphics2D g2d2 = (Graphics2D) g2d.create();
        g2d2.setColor(Color.BLACK);
        g2d2.setFont(g2d2.getFont().deriveFont(Font.PLAIN, 30f));
        g2d2.drawString(gamePanel.getUi().inputText, x + 10, y + gamePanel.getTileSize() - 14);
    }

    public void drawInputDurasiMandiScreen(String judul) {
        // hint 
        int x2 = gamePanel.getTileSize() * 2;
        int y2 = gamePanel.getTileSize() * 13;
        int width2 = gamePanel.getTileSize() * 6;
        int height2 = gamePanel.getTileSize() * 2;
        drawSubWindow(x2, y2, width2, height2);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        g2d.drawString("[ESC] Back", x2 + 24, y2 + 60);

        // draw window
        int x = getXforCenteredText(judul);
        x -= 2 * gamePanel.getTileSize();
        int y = gamePanel.getTileSize() * 4;
        int width = gamePanel.getScreenWidth() - 2 * x;
        int height = gamePanel.getScreenHeight() - y - gamePanel.getTileSize() * 5;
        drawSubWindow(x, y, width, height);

        // draw judul text
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 35f));
        x = getXforCenteredText(judul);
        y += gamePanel.getTileSize() + 5;
        g2d.drawString(judul, x, y);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        String text = "(dalam detik)";
        String textTemp = "(maks. 15 karakter)";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize() - 10;
        g2d.drawString(text, x, y);

        // draw input text
        x = getXforCenteredText(textTemp);
        y += gamePanel.getTileSize() * 2;
        width = gamePanel.getScreenWidth() - 2 * x;
        height = gamePanel.getTileSize();
        g2d.fillRect(x, y, width, height);

        // draw text
        Graphics2D g2d2 = (Graphics2D) g2d.create();
        g2d2.setColor(Color.BLACK);
        g2d2.setFont(g2d2.getFont().deriveFont(Font.PLAIN, 30f));
        g2d2.drawString(gamePanel.getUi().inputText, x + 10, y + gamePanel.getTileSize() - 14);
    }

    public void drawInputDurasiShalatScreen(String judul) {
        // hint 
        int x2 = gamePanel.getTileSize()* 2;
        int y2 = gamePanel.getTileSize()* 13;
        int width2 = gamePanel.getTileSize()* 6;
        int height2 = gamePanel.getTileSize()* 2;
        drawSubWindow(x2, y2, width2, height2);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        g2d.drawString("[ESC] Back", x2 + 24, y2 + 60);

        // draw window
        int x = getXforCenteredText(judul);
        x -= 2 * gamePanel.getTileSize();
        int y = gamePanel.getTileSize() * 4;
        int width = gamePanel.getScreenWidth() - 2 * x;
        int height = gamePanel.getScreenHeight() - y - gamePanel.getTileSize() * 5;
        drawSubWindow(x, y, width, height);

        // draw judul text
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 35f));
        x = getXforCenteredText(judul);
        y += gamePanel.getTileSize() + 5;
        g2d.drawString(judul, x, y);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        String text = "(dalam detik)";
        String textTemp = "(maks. 15 karakter)";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize() - 10;
        g2d.drawString(text, x, y);

        // draw input text
        x = getXforCenteredText(textTemp);
        y += gamePanel.getTileSize() * 2;
        width = gamePanel.getScreenWidth() - 2 * x;
        height = gamePanel.getTileSize();
        g2d.fillRect(x, y, width, height);

        // draw text
        Graphics2D g2d2 = (Graphics2D) g2d.create();
        g2d2.setColor(Color.BLACK);
        g2d2.setFont(g2d2.getFont().deriveFont(Font.PLAIN, 30f));
        g2d2.drawString(gamePanel.getUi().inputText, x + 10, y + gamePanel.getTileSize() - 14);
    }

    public void drawInputDurasiBacaBukuScreen(String judul) {
        // hint 
        int x2 = gamePanel.getTileSize() * 2;
        int y2 = gamePanel.getTileSize() * 13;
        int width2 = gamePanel.getTileSize() * 6;
        int height2 = gamePanel.getTileSize() * 2;
        drawSubWindow(x2, y2, width2, height2);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        g2d.drawString("[ESC] Back", x2 + 24, y2 + 60);

        // draw window
        int x = getXforCenteredText(judul);
        x -= 2 * gamePanel.getTileSize();
        int y = gamePanel.getTileSize() * 4;
        int width = gamePanel.getScreenWidth() - 2 * x;
        int height = gamePanel.getScreenHeight() - y - gamePanel.getTileSize() * 5;
        drawSubWindow(x, y, width, height);

        // draw judul text
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 35f));
        x = getXforCenteredText(judul);
        y += gamePanel.getTileSize() + 5;
        g2d.drawString(judul, x, y);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        String text = "(dalam detik)";
        String textTemp = "(maks. 15 karakter)";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize() - 10;
        g2d.drawString(text, x, y);

        // draw input text
        x = getXforCenteredText(textTemp);
        y += gamePanel.getTileSize() * 2;
        width = gamePanel.getScreenWidth() - 2 * x;
        height = gamePanel.getTileSize();
        g2d.fillRect(x, y, width, height);

        // draw text
        Graphics2D g2d2 = (Graphics2D) g2d.create();
        g2d2.setColor(Color.BLACK);
        g2d2.setFont(g2d2.getFont().deriveFont(Font.PLAIN, 30f));
        g2d2.drawString(gamePanel.getUi().inputText, x + 10, y + gamePanel.getTileSize() - 14);
    }

    public void drawInputDurasiRadioScreen(String judul) {
        // hint 
        int x2 = gamePanel.getTileSize() * 2;
        int y2 = gamePanel.getTileSize() * 13;
        int width2 = gamePanel.getTileSize() * 6;
        int height2 = gamePanel.getTileSize() * 2;
        drawSubWindow(x2, y2, width2, height2);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        g2d.drawString("[ESC] Back", x2 + 24, y2 + 60);

        // draw window
        int x = getXforCenteredText(judul);
        x -= 2 * gamePanel.getTileSize();
        int y = gamePanel.getTileSize() * 4;
        int width = gamePanel.getScreenWidth() - 2 * x;
        int height = gamePanel.getScreenHeight() - y - gamePanel.getTileSize() * 5;
        drawSubWindow(x, y, width, height);

        // draw judul text
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 35f));
        x = getXforCenteredText(judul);
        y += gamePanel.getTileSize() + 5;
        g2d.drawString(judul, x, y);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        String text = "(dalam detik)";
        String textTemp = "(maks. 15 karakter)";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize() - 10;
        g2d.drawString(text, x, y);

        // draw input text
        x = getXforCenteredText(textTemp);
        y += gamePanel.getTileSize() * 2;
        width = gamePanel.getScreenWidth() - 2 * x;
        height = gamePanel.getTileSize();
        g2d.fillRect(x, y, width, height);

        // draw text
        Graphics2D g2d2 = (Graphics2D) g2d.create();
        g2d2.setColor(Color.BLACK);
        g2d2.setFont(g2d2.getFont().deriveFont(Font.PLAIN, 30f));
        g2d2.drawString(gamePanel.getUi().inputText, x + 10, y + gamePanel.getTileSize() - 14);
    }

    public void drawInputDurasiSiramTanamanScreen(String judul) {
        // hint 
        int x2 = gamePanel.getTileSize() * 2;
        int y2 = gamePanel.getTileSize() * 13;
        int width2 = gamePanel.getTileSize() * 6;
        int height2 = gamePanel.getTileSize() * 2;
        drawSubWindow(x2, y2, width2, height2);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        g2d.drawString("[ESC] Back", x2 + 24, y2 + 60);

        // draw window
        int x = getXforCenteredText(judul);
        x -= 2 * gamePanel.getTileSize();
        int y = gamePanel.getTileSize() * 4;
        int width = gamePanel.getScreenWidth() - 2 * x;
        int height = gamePanel.getScreenHeight() - y - gamePanel.getTileSize() * 5;
        drawSubWindow(x, y, width, height);

        // draw judul text
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 35f));
        x = getXforCenteredText(judul);
        y += gamePanel.getTileSize() + 5;
        g2d.drawString(judul, x, y);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        String text = "(dalam detik)";
        String textTemp = "(maks. 15 karakter)";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize() - 10;
        g2d.drawString(text, x, y);

        // draw input text
        x = getXforCenteredText(textTemp);
        y += gamePanel.getTileSize() * 2;
        width = gamePanel.getScreenWidth() - 2 * x;
        height = gamePanel.getTileSize();
        g2d.fillRect(x, y, width, height);

        // draw text
        Graphics2D g2d2 = (Graphics2D) g2d.create();
        g2d2.setColor(Color.BLACK);
        g2d2.setFont(g2d2.getFont().deriveFont(Font.PLAIN, 30f));
        g2d2.drawString(gamePanel.getUi().inputText, x + 10, y + gamePanel.getTileSize() - 14);
    }

    public void drawInputDurasiMainGameScreen(String judul) {
        // hint 
        int x2 = gamePanel.getTileSize() * 2;
        int y2 = gamePanel.getTileSize() * 13;
        int width2 = gamePanel.getTileSize() * 6;
        int height2 = gamePanel.getTileSize() * 2;
        drawSubWindow(x2, y2, width2, height2);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        g2d.drawString("[ESC] Back", x2 + 24, y2 + 60);

        // draw window
        int x = getXforCenteredText(judul);
        x -= 2 * gamePanel.getTileSize();
        int y = gamePanel.getTileSize() * 4;
        int width = gamePanel.getScreenWidth() - 2 * x;
        int height = gamePanel.getScreenHeight() - y - gamePanel.getTileSize() * 5;
        drawSubWindow(x, y, width, height);

        // draw judul text
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 35f));
        x = getXforCenteredText(judul);
        y += gamePanel.getTileSize() + 5;
        g2d.drawString(judul, x, y);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        String text = "(dalam detik)";
        String textTemp = "(maks. 15 karakter)";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize() - 10;
        g2d.drawString(text, x, y);

        // draw input text
        x = getXforCenteredText(textTemp);
        y += gamePanel.getTileSize() * 2;
        width = gamePanel.getScreenWidth() - 2 * x;
        height = gamePanel.getTileSize();
        g2d.fillRect(x, y, width, height);

        // draw text
        Graphics2D g2d2 = (Graphics2D) g2d.create();
        g2d2.setColor(Color.BLACK);
        g2d2.setFont(g2d2.getFont().deriveFont(Font.PLAIN, 30f));
        g2d2.drawString(gamePanel.getUi().inputText, x + 10, y + gamePanel.getTileSize() - 14);
    }

    public void drawGantiPekerjaanScreen(String judul) {
        // draw window
        int x = getXforCenteredText(judul);
        x -= 4 * gamePanel.getTileSize();
        int y = gamePanel.getTileSize() * 4 - 15;
        int width = gamePanel.getScreenWidth() - 2 * x;
        int height = gamePanel.getScreenHeight() - y - gamePanel.getTileSize() * 5 + 15;
        drawSubWindow(x, y, width, height);

        // draw judul
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 40f));
        x = getXforCenteredText(judul);
        y += gamePanel.getTileSize() + 5;
        g2d.drawString(judul, x, y);

        // draw text
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        String text = "Pilih pekerjaan yang ingin diubah:";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize() * 2 - 30;
        g2d.drawString(text, x, y);
        y += 15;

        // draw pekerjaan
        for (int i = 0; i < gamePanel.getCurrentSim().getPekerjaan().getListPekerjaan().length; i++) {
            text = gamePanel.getCurrentSim().getPekerjaan().getListPekerjaan()[i];
            x = getXforCenteredText(text);
            y += gamePanel.getTileSize() - 10;
            g2d.drawString(text, x, y);
            if (commandNumber == i) {
                g2d.drawString(">", x - 40, y);
            }
        }

        // hint harga
        // draw hint text
        x = gamePanel.getTileSize() * 2;
        y = gamePanel.getTileSize() * 13;
        width = gamePanel.getTileSize() * 5;
        height = gamePanel.getTileSize() * 2;
        drawSubWindow(x, y, width, height);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        g2d.drawString("Harga: " + (int) gamePanel.getCurrentSim().getPekerjaan().getGaji()[commandNumber] / 2, x + 24, y + 60);

        // // draw hint text
        // x = gamePanel.tileSize * 2;
        // y = gamePanel.tileSize * 13;
        // width = gamePanel.tileSize * 6;
        // height = gamePanel.tileSize * 2;
        // drawSubWindow(x, y, width-24, height);
        // g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        // g2d.drawString("Harga: 1500", x + 24, y + 60);

        // hint 
        int x2 = gamePanel.getTileSize() * 2;
        int y2 = gamePanel.getTileSize() * 13;
        int width2 = gamePanel.getTileSize() * 6;
        int height2 = gamePanel.getTileSize() * 2;
        drawSubWindow(x2 + width +24, y2, width2, height2);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        g2d.drawString("[ESC] Back", x2 + width + 48, y2 + 60);

    }

    public void drawInputDurasiOlahragaScreen(String judul) {
        // hint 
        int x2 = gamePanel.getTileSize() * 2;
        int y2 = gamePanel.getTileSize() * 13;
        int width2 = gamePanel.getTileSize() * 6;
        int height2 = gamePanel.getTileSize() * 2;
        drawSubWindow(x2, y2, width2, height2);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        g2d.drawString("[ESC] Back", x2 + 24, y2 + 60);

        // draw window
        int x = getXforCenteredText(judul);
        x -= 2 * gamePanel.getTileSize();
        int y = gamePanel.getTileSize() * 4;
        int width = gamePanel.getScreenWidth() - 2 * x;
        int height = gamePanel.getScreenHeight() - y - gamePanel.getTileSize() * 5;
        drawSubWindow(x, y, width, height);

        // draw judul text
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 35f));
        x = getXforCenteredText(judul);
        y += gamePanel.getTileSize() + 5;
        g2d.drawString(judul, x, y);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        String text = "(dalam kelipatan 20 detik)";
        String textTemp = "(maks. 15 karakter)";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize() - 10;
        g2d.drawString(text, x, y);

        // draw input text
        x = getXforCenteredText(textTemp);
        y += gamePanel.getTileSize() * 2;
        width = gamePanel.getScreenWidth() - 2 * x;
        height = gamePanel.getTileSize();
        g2d.fillRect(x, y, width, height);

        // draw text
        Graphics2D g2d2 = (Graphics2D) g2d.create();
        g2d2.setColor(Color.BLACK);
        g2d2.setFont(g2d2.getFont().deriveFont(Font.PLAIN, 30f));
        g2d2.drawString(gamePanel.getUi().inputText, x + 10, y + gamePanel.getTileSize() - 14);
    }

    public void drawKerjaScreen(String judul) {
        // hint 
        int x2 = gamePanel.getTileSize() * 2;
        int y2 = gamePanel.getTileSize() * 13;
        int width2 = gamePanel.getTileSize() * 6;
        int height2 = gamePanel.getTileSize() * 2;
        drawSubWindow(x2, y2, width2, height2);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        g2d.drawString("[ESC] Back", x2 + 24, y2 + 60);

        // draw window
        int x = getXforCenteredText(judul);
        x -= 3 * gamePanel.getTileSize();
        int y = gamePanel.getTileSize() * 4;
        int width = gamePanel.getScreenWidth() - 2 * x;
        int height = gamePanel.getScreenHeight() - y - gamePanel.getTileSize() * 5;
        drawSubWindow(x, y, width, height);

        // draw judul
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 40f));
        x = getXforCenteredText(judul);
        y += gamePanel.getTileSize() + 5;
        g2d.drawString(judul, x, y);

        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 35f));
        x = getXforCenteredText("Pekerjaan: " + gamePanel.getCurrentSim().getPekerjaan().getListPekerjaan()[gamePanel
                .getCurrentSim().getPekerjaan().getIndexPekerjaan()]) /* - 2 * gamePanel.getTileSize() - 35 */;
        y += gamePanel.getTileSize() + 35;
        g2d.drawString("Pekerjaan: "
                + gamePanel.getCurrentSim().getPekerjaan().getListPekerjaan()[gamePanel.getCurrentSim().getPekerjaan().getIndexPekerjaan()],
                x, y);

        if (gamePanel.getCurrentSim().getPekerjaan().getIsCanStartPekerjaan()
                && gamePanel.getCurrentSim().getPekerjaan().getIsCanChangePekerjaan()) {
            // draw text
            g2d.setColor(Color.WHITE);
            g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
            x = getXforCenteredText("Mulai kerja");
            y += 2 * gamePanel.getTileSize() - 10;
            g2d.drawString("Mulai Kerja", x, y);
            if (commandNumber == 0) {
                g2d.drawString(">", x - 40, y);
            }

            x = getXforCenteredText("Ganti Pekerjaan");
            y += gamePanel.getTileSize();
            g2d.drawString("Ganti Pekerjaan", x, y);
            if (commandNumber == 1) {
                g2d.drawString(">", x - 40, y);
            }
        } else if (gamePanel.getCurrentSim().getPekerjaan().getIsCanChangePekerjaan()) {
            // draw text
            g2d.setColor(Color.GRAY);
            g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
            x = getXforCenteredText("Mulai kerja");
            y += 2 * gamePanel.getTileSize() - 10;
            g2d.drawString("Mulai Kerja", x, y);

            g2d.setColor(Color.WHITE);
            x = getXforCenteredText("Ganti Pekerjaan");
            y += gamePanel.getTileSize();
            g2d.drawString("Ganti Pekerjaan", x, y);
            if (commandNumber == 0) {
                g2d.drawString(">", x - 40, y);
            }
        } else if (gamePanel.getCurrentSim().getPekerjaan().getIsCanStartPekerjaan()) {
            // draw text
            g2d.setColor(Color.WHITE);
            g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
            x = getXforCenteredText("Mulai kerja");
            y += 2 * gamePanel.getTileSize() - 10;
            g2d.drawString("Mulai Kerja", x, y);
            if (commandNumber == 0) {
                g2d.drawString(">", x - 40, y);
            }

            g2d.setColor(Color.GRAY);
            x = getXforCenteredText("Ganti Pekerjaan");
            y += gamePanel.getTileSize();
            g2d.drawString("Ganti Pekerjaan", x, y);
        } else {
            // draw text
            g2d.setColor(Color.GRAY);
            g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
            x = getXforCenteredText("Mulai kerja");
            y += 2 * gamePanel.getTileSize() - 10;
            g2d.drawString("Mulai Kerja", x, y);

            g2d.setColor(Color.GRAY);
            x = getXforCenteredText("Ganti Pekerjaan");
            y += gamePanel.getTileSize();
            g2d.drawString("Ganti Pekerjaan", x, y);
        }

    }

    public void drawInputDurasiKerjaScreen(String judul) {
        // hint 
        int x2 = gamePanel.getTileSize() * 2;
        int y2 = gamePanel.getTileSize() * 13;
        int width2 = gamePanel.getTileSize() * 6;
        int height2 = gamePanel.getTileSize() * 2;
        drawSubWindow(x2, y2, width2, height2);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        g2d.drawString("[ESC] Back", x2 + 24, y2 + 60);

        // draw window
        int x = getXforCenteredText(judul);
        x -= 2 * gamePanel.getTileSize();
        int y = gamePanel.getTileSize() * 4;
        int width = gamePanel.getScreenWidth() - 2 * x;
        int height = gamePanel.getScreenHeight() - y - gamePanel.getTileSize() * 5;
        drawSubWindow(x, y, width, height);

        // draw judul text
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 35f));
        x = getXforCenteredText(judul);
        y += gamePanel.getTileSize() + 5;
        g2d.drawString(judul, x, y);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        String text = "(dalam kelipatan 120 detik)";
        String textTemp = "(maks. 15 karakter)";
        x = getXforCenteredText(text);
        y += gamePanel.getTileSize() - 10;
        g2d.drawString(text, x, y);

        // draw input text
        x = getXforCenteredText(textTemp);
        y += gamePanel.getTileSize() * 2;
        width = gamePanel.getScreenWidth() - 2 * x;
        height = gamePanel.getTileSize();
        g2d.fillRect(x, y, width, height);

        // draw text
        Graphics2D g2d2 = (Graphics2D) g2d.create();
        g2d2.setColor(Color.BLACK);
        g2d2.setFont(g2d2.getFont().deriveFont(Font.PLAIN, 30f));
        g2d2.drawString(gamePanel.getUi().inputText, x + 10, y + gamePanel.getTileSize() - 14);
    }

    // ------------------------------ BATAS SUCI ------------------------------

    public void drawMelihatWaktuScreen(){
        // draw window
        int x = getXforCenteredText("Waktu");
        x -= 5 * gamePanel.getTileSize();
        int y = gamePanel.getTileSize() * 4;
        int width = gamePanel.getScreenWidth() - 2 * x;
        int height = gamePanel.getScreenHeight() - y - gamePanel.getTileSize() * 5;
        drawSubWindow(x, y, width, height);

        // draw judul text
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 35f));
        x = getXforCenteredText("Waktu");
        y += gamePanel.getTileSize() + 5;
        g2d.drawString("Waktu", x, y);

        // draw text
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        x = getXforCenteredText("Waktu") - 3 * gamePanel.getTileSize();
        y += gamePanel.getTileSize() + 20;
        g2d.drawString("World Time", x, y);
        y += 35;
        g2d.drawString("World Day", x, y);
        y += gamePanel.getTileSize()+15;
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 30f));
        g2d.drawString("Sisa Waktu", x, y);
        y += 35;
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        g2d.drawString("Upgrade Rumah", x, y);
        y += 35;
        g2d.drawString("Beli Barang", x, y);

        // value 
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 30f));
        x = getXforCenteredText("Waktu") + 2 *gamePanel.getTileSize() -30;
        y = gamePanel.getTileSize() * 6 + 25;
        g2d.drawString(": " + gamePanel.getWorldTimeCounter()%720, x, y);
        y += 35;
        g2d.drawString(": " + gamePanel.getWorldTimeCounter()/720, x, y);
        y += gamePanel.getTileSize() + 15 + 35;
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
        // g2d.drawString(" : " + gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).nama, tailX, textY);
        // textY += lineHeight;
        // g2d.drawString(" : "
        //         + gamePanel.getCurrentSim().pekerjaan.listPekerjaan[gamePanel.getCurrentSim().pekerjaan.indexPekerjaan],
        //         tailX, textY);
        // textY += lineHeight;
        // value = String.valueOf(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).uang);
        // g2d.drawString(" : " + value, tailX, textY);
        // textY += lineHeight;
        // value = String.valueOf(" : " + gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).kesehatan + "/"
        //         + gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).maxKesehatan);
        // g2d.drawString(value, tailX, textY);
        // textY += lineHeight;
        // value = String.valueOf(" : " + gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).kekenyangan + "/"
        //         + gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).maxKekenyangan);
        // g2d.drawString(value, tailX, textY);
        // textY += lineHeight;
        // value = String.valueOf(" : " + gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).mood + "/"
        //         + gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).maxMood);
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
                    // gamePanel.setGameState(gamePanel.getPlayState());
                    // if (gamePanel.getCurrentSim().isMati == false) {
                    // charIndex = 0;
                    // combinedText = "";
                    // gamePanel.setGameState(gamePanel.getDialogState());
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
                    // gamePanel.setGameState(gamePanel.getDialogState());
                    // if (currentAksi == "Makan") {
                    // currentDialog = "Selesai melakukan " + currentAksiCadangan;
                    // } else if (currentAksi == "Masak") {
                    // currentDialog = "Selesai melakukan " + currentAksi + "\n" +
                    // currentAksiCadangan;
                    // } else {
                    // currentDialog = "Selesai melakukan " + currentAksi;
                    // }

                    // for (int i = 0; i < gamePanel.getListSim().size(); i++) {
                    // if (gamePanel.getListSim().get(i).rumah.isCanUpgrade == false) {
                    // gamePanel.getListSim().get(i).rumah.isLockUpgrade = false;
                    // gamePanel.getListSim().get(i).rumah.remainingTimeUpgrade -= duration;
                    // gamePanel.getListSim().get(i).rumah.setIsCanUpgradeToTrueAfter18Minutes();
                    // gamePanel.getListSim().get(i).rumah.isLockUpgrade = true;
                    // }
                    // }
                    // for (int i = 0; i < gamePanel.getListSim().size(); i++) {
                    // if (gamePanel.getListSim().get(i).isCanBuy == false) {
                    // gamePanel.getListSim().get(i).isLockBuy = false;
                    // gamePanel.getListSim().get(i).remainingTimeBuy -= duration;
                    // gamePanel.getListSim().get(i).setIsCanBuyToTrue();
                    // gamePanel.getListSim().get(i).rumah.isLockUpgrade = true;
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

            // gamePanel.setGameState(gamePanel.getPlayState());
            // gamePanel.stopMusic();
            // gamePanel.playMusic(1);
            // if (gamePanel.getCurrentSim().isMati == false) {
            // charIndex = 0;
            // combinedText = "";
            // gamePanel.setGameState(gamePanel.getDialogState());
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
            // gamePanel.setGameState(gamePanel.getDialogState());
            // if (currentAksi == "Makan") {
            // currentDialog = "Selesai melakukan " + currentAksiCadangan;
            // } else if (currentAksi == "Masak") {
            // currentDialog = "Selesai melakukan " + currentAksi + "\n" +
            // currentAksiCadangan;
            // } else {
            // currentDialog = "Selesai melakukan " + currentAksi;
            // }
            // for (int i = 0; i < gamePanel.getListSim().size(); i++) {
            // if (gamePanel.getListSim().get(i).rumah.isCanUpgrade == false) {
            // gamePanel.getListSim().get(i).rumah.isLockUpgrade = false;
            // gamePanel.getListSim().get(i).rumah.remainingTimeUpgrade -= duration;
            // gamePanel.getListSim().get(i).rumah.setIsCanUpgradeToTrueAfter18Minutes();
            // gamePanel.getListSim().get(i).rumah.isLockUpgrade = true;
            // }
            // }
            // for (int i = 0; i < gamePanel.getListSim().size(); i++) {
            // if (gamePanel.getListSim().get(i).isCanBuy == false) {
            // gamePanel.getListSim().get(i).isLockBuy = false;
            // gamePanel.getListSim().get(i).remainingTimeBuy -= duration;
            // gamePanel.getListSim().get(i).setIsCanBuyToTrue();
            // gamePanel.getListSim().get(i).rumah.isLockUpgrade = true;
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

        int x = gamePanel.getScreenWidth() / 2 - length / 2;

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
        gamePanel.setGameState(gamePanel.getDialogState());
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
            // gamePanel.setGameState(gamePanel.getDialogState());
            // if (currentAksi == "Makan") {
            // currentDialog = "Selesai melakukan " + currentAksiCadangan;
            // } else if (currentAksi == "Masak") {
            // currentDialog = "Selesai melakukan " + currentAksi + "\n" +
            // currentAksiCadangan;
            // } else {
            // currentDialog = "Selesai melakukan " + currentAksi;
            // }
            for (int i = 0; i < gamePanel.getListSim().size(); i++) {
                if (gamePanel.getListSim().get(i).getRumah().getIsCanUpgrade() == false) {
                    gamePanel.getListSim().get(i).getRumah().setIsLockUpgrade(false);
                    gamePanel.getListSim().get(i).getRumah().decRemainingTimeUpgrade(duration);
                    gamePanel.getListSim().get(i).getRumah().setIsCanUpgradeToTrueAfter18Minutes();
                    gamePanel.getListSim().get(i).getRumah().setIsLockUpgrade(true);
                }
            }
            for (int i = 0; i < gamePanel.getListSim().size(); i++) {
                if (gamePanel.getListSim().get(i).getIsCanBuy() == false) {
                    gamePanel.getListSim().get(i).setIsLockBuy(false);
                    gamePanel.getListSim().get(i).setRemainingTimeBuy(gamePanel.getListSim().get(i).getRemainingTimeBuy() - duration);
                    gamePanel.getListSim().get(i).setIsCanBuyToTrue();
                    gamePanel.getListSim().get(i).getRumah().setIsLockUpgrade(true);
                }
            }

        } else {
            gamePanel.getCurrentSim().setIsCanBuy(true);
            gamePanel.getCurrentSim().getRumah().setIsCanUpgrade(true);
        }

        // if (gamePanel.getCurrentSim().isBarangSampai = true) {
        // charIndex = 0;
        // combinedText = "";
        // gamePanel.setGameState(gamePanel.getDialogState());
        // currentDialog = gamePanel.getCurrentSim().tempDialogBarang;
        // } else if (gamePanel.getCurrentSim().isUpgradeDone = true) {
        // charIndex = 0;
        // combinedText = "";
        // gamePanel.setGameState(gamePanel.getDialogState());
        // currentDialog = gamePanel.getCurrentSim().tempDialogUpgrade;
        // }
        // charIndex = 0;
        // combinedText = "";
        // gamePanel.setGameState(gamePanel.getDialogState());
        // if (currentAksi == "Makan") {
        // currentDialog = "Selesai melakukan " + currentAksiCadangan;
        // } else if (currentAksi == "Masak") {
        // currentDialog = "Selesai melakukan " + currentAksi + "\n" +
        // currentAksiCadangan;
        // } else {
        // currentDialog = "Selesai melakukan " + currentAksi;
        // }

        // for (int i = 0; i < gamePanel.getListSim().size(); i++) {
        // if (gamePanel.getListSim().get(i).rumah.isCanUpgrade == false) {
        // gamePanel.getListSim().get(i).rumah.isLockUpgrade = false;
        // gamePanel.getListSim().get(i).rumah.remainingTimeUpgrade -= duration;
        // gamePanel.getListSim().get(i).rumah.setIsCanUpgradeToTrueAfter18Minutes();
        // gamePanel.getListSim().get(i).rumah.isLockUpgrade = true;
        // }
        // }
        // for (int i = 0; i < gamePanel.getListSim().size(); i++) {
        // if (gamePanel.getListSim().get(i).isCanBuy == false) {
        // gamePanel.getListSim().get(i).isLockBuy = false;
        // gamePanel.getListSim().get(i).remainingTimeBuy -= duration;
        // gamePanel.getListSim().get(i).setIsCanBuyToTrue();
        // gamePanel.getListSim().get(i).rumah.isLockUpgrade = true;
        // }
        // }

        durasiTimer = 0;
        currentAksi = "";
        currentAksiDone = true;
    }
}
