package main;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;

public class UI {
    GamePanel gamePanel;
    Graphics2D g2d;

    Font maruMonica, purisaB;

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialog = "";


    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        try {
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("data/fonts/x12y16pxMaruMonica.ttf")));
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

        if (gamePanel.gameState == gamePanel.playState) { // if game is playing
            // later
        } else if (gamePanel.gameState == gamePanel.pauseState) { // if game is paused
            drawPauseScreen();
        } else if (gamePanel.gameState == gamePanel.dialogState) { // if game is in dialog
            drawDialogScreen();
        }
    }

    public void drawPauseScreen() {
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN ,80f));

        String message = "PAUSED";

        int x = getXforCenteredText(message);
        int y = gamePanel.screenHeight / 2;

        g2d.drawString(message, x, y);
    }

    public void drawDialogScreen(){
        // WINDOW
        int x = gamePanel.tileSize * 2;
        int y = gamePanel.tileSize / 2;
        int width = gamePanel.screenWidth - gamePanel.tileSize * 4;
        int height = gamePanel.tileSize * 4;
        drawSubWindow(x, y, width, height);

        // TEXT
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN ,32f));
        x += gamePanel.tileSize;
        y += gamePanel.tileSize;
        for (String line : currentDialog.split("\n")){
            g2d.drawString(line, x, y);
            y += g2d.getFontMetrics().getHeight();
        }
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        // grey window
        g2d.setColor(new Color(0,0,0,210));
        g2d.fillRoundRect(x, y, width, height, 35, 35);

        // white border
        g2d.setColor(new Color(255,255,255));
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }

    public int getXforCenteredText(String message) {
        int length = (int) g2d.getFontMetrics().getStringBounds(message, g2d).getWidth();

        int x = gamePanel.screenWidth / 2 - length / 2;

        return x;
    }
}
