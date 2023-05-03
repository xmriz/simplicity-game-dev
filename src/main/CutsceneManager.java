package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

public class CutsceneManager {
    GamePanel gamePanel;
    Graphics2D g2d;
    public int sceneNum;
    public int scenePhase;
    int counter = 0;
    float alpha = 0.0f;
    int y;
    String credit;

    // scene number
    public final int ending = 0;

    public CutsceneManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;

        credit = "Created by:\n" +
                "Kelompok 08 K1 Tugas Besar OOP-IF2212\n" +
                "\n\n\n\n\n\n\n\n\n" +
                "Kontributor:\n" +
                "Silvester Kresna (18221049)\n" +
                "Ahmad Rizki (18221071)\n" +
                "Nadine Aliya (18221081)\n" + 
                "Iskandar Muda R. Parlambang (18221109)\n" +
                "Luthfi Hanif (18221071)\n"
                + "\n\n\n"
                + "PJ Source Code:\n"
                + "Ahmad Rizki (18221071)\n" +
                "Iskandar Muda R. Parlambang (18221109)\n" + 
                "\n\n\n" +
                "PJ Buklet:\n" +
                "Silvester Kresna (18221049)\n" +
                "Nadine Aliya (18221081)\n" + 
                "\n\n\n" +
                "PJ Class Diagram:\n" +
                "Luthfi Hanif (18221071)\n" 
                + "\n\n\n"
                + "Music:\n" +
                "Ahmad Rizki (18221071)\n" +
                "Nadine Aliya (18221081)\n" + 
                "Iskandar Muda R. Parlambang (18221109)\n" +
                "Luthfi Hanif (18221071)\n" +
                "\n\n\n" +
                "Art:\n" 
                + "Silvester Kresna (18221049)\n" +
                "Ahmad Rizki (18221071)\n" +
                "\n\n\n" +
                "Program:\n" + 
                "Silvester Kresna (18221049)\n" +
                "Ahmad Rizki (18221071)\n" +
                "Nadine Aliya (18221081)\n" +
                "Iskandar Muda R. Parlambang (18221109)\n" +
                "Luthfi Hanif (18221071)\n"
                + "\n\n\n" + 
                "Tester:\n" +
                "Abraham Megantoro (18221123)\n" +
                "Kevin Sebastian (1822143)\n" +
                "Ahmad Rizki (18221071)" +
                "Iskandar Muda R. Parlambang (18221109)\n"
                + "\n\n\n" + 
                "Asisten:\n" +
                "Afif Fahreza (18219058)\n" +
                "\n\n\n" +
                "Asisten Demo:\n" +
                "Marcelino Feihan (18219006)\n" +
                "\n\n\n" +
                "Dosen Pengampu:\n" +
                "Riza Satria Perdana, S.Kom., M.T.\n" +
                "\n\n\n" + 
                "Terima Kasih Kepada:\n" +
                "ALLAH SWT\n" +
                "Orang Tua\n" +
                "Keluarga\n" +
                "Kakak-kakak asisten\n" +
                "Teman-teman kelas ganjil STI 2021\n" +
                "Dan semua yang berperan dalam pembuatan game ini\n" +
                "\n\n\n" + 
                "Referensi:\n" +
                "RyiSnow Channel Youtube\n" +
                "Brocode Channel Youtuber\n"+
                "W3Schools\n" +
                "Stackoverflow\n" +
                "Geeksforgeeks\n" +
                "Java Documentation\n" +
                "ChatGPT\n" +
                "\n\n\n\n" + 
                "Game ini dibuat untuk memenuhi tugas akhir\nmata kuliah Pemrograman Berorientasi Objek\n" +
                "STI 2021\n" +
                "Institut Teknologi Bandung\n" +
                "\n\n\n\n" + 
                "Terima kasih telah bermain...\n";
    }

    public void draw (Graphics2D g2d){
        this.g2d = g2d;

        switch (sceneNum){
            case ending : scene_ending(); break;
        }
    }

    public void scene_ending(){
        if (scenePhase == 0){ // fade in black background
            alpha += 0.005f;
            if (alpha >= 1.0f){
                alpha = 1.0f;
            }
            drawBlackBackground(alpha);
            if (alpha == 1.0f){
                alpha = 0.0f;
                scenePhase++;
            }
        }

        if (scenePhase == 1){ // phase nampilin tulisan simplicity dan logo

            drawBlackBackground(1.0f);

            // TITLE NAME
            g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 96f));
            String title = "SIM-PLICITY";
            int x = gamePanel.ui.getXforCenteredText(title);
            int y = gamePanel.tileSize * 7 - 20;

            // shadow
            g2d.setColor(Color.GRAY);
            g2d.drawString(title, x + 5, y + 5);
            // main color
            g2d.setColor(Color.WHITE);
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


            if (counterReached(480)){
                scenePhase++;
            }
        } 

        if (scenePhase == 2){ // credit scene
            drawBlackBackground(1f);
            y = gamePanel.screenHeight/2;
            drawString(1f, 38f, y, credit, 40);

            if (counterReached(480)){
                scenePhase++;
            }
        }

        if (scenePhase == 3){ // scrolling up the credit
            drawBlackBackground(1f);
            y--;
            drawString(1f, 38f, y, credit, 40);
        }

        if (scenePhase == 4){ // fade out black background
            alpha += 0.005f;
            if (alpha >= 1.0f){
                alpha = 1.0f;
            }
            drawBlackBackground(alpha);
            if (alpha == 1.0f){
                alpha = 0.0f;
                gamePanel.gameState = gamePanel.menuState;
            }
        }
    }

    public boolean counterReached(int target){
        boolean counterReached = false;
        counter++;
        if (counter > target){
            counterReached = true;
            counter = 0;
        }
        return counterReached;
    }

    public void drawBlackBackground (float alpha){
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    }

    public void drawString (float alpha, float fontSize, int y, String text, int lineHeight){
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(fontSize));
        
        for (String line : text.split("\n")){
            int x = gamePanel.ui.getXforCenteredText(line);
            g2d.drawString(line, x, y);
            y += lineHeight;
        }
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    }
}
