package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

import javax.imageio.ImageIO;


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

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        try {
            maruMonica = Font.createFont(Font.TRUETYPE_FONT,
                    new FileInputStream(new File("data/fonts/x12y16pxMaruMonica.ttf")));
            purisaB = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("data/fonts/Purisa Bold.ttf")));
        } catch (Exception e) {
            // TODO: handle exception
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

        String text = "Masukkan nama karakter anda:";
        int x = getXforCenteredText(text);
        int y = gamePanel.tileSize * 4 + 10;
        g2d.drawString(text, x, y);

        // meminta input nama karakter 
        g2d.fillRect(x, y + gamePanel.tileSize, gamePanel.tileSize * 10, gamePanel.tileSize);
        Graphics2D g2d2 = (Graphics2D) g2d.create();;
        g2d2.setColor(Color.BLACK);
        g2d2.setFont(g2d2.getFont().deriveFont(Font.PLAIN, 36f));
        x = getXforCenteredText(input);
        g2d2.drawString(input, x + gamePanel.tileSize/2 + 4, y + gamePanel.tileSize*2 - 10);
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

    public void drawPauseScreen() {
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 80f));

        String message = "PAUSED";

        int x = getXforCenteredText(message);
        int y = gamePanel.screenHeight / 2;

        g2d.drawString(message, x, y);
    }

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
