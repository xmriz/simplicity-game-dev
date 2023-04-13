package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.*;

public class Sim extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Sim(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() { // set the default values of the player
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        File imageUp1 = new File("assets/sim/boy_up_1.png");
        File imageUp2 = new File("assets/sim/boy_up_2.png");
        File imageDown1 = new File("assets/sim/boy_down_1.png");
        File imageDown2 = new File("assets/sim/boy_down_2.png");
        File imageLeft1 = new File("assets/sim/boy_left_1.png");
        File imageLeft2 = new File("assets/sim/boy_left_2.png");
        File imageRight1 = new File("assets/sim/boy_right_1.png");
        File imageRight2 = new File("assets/sim/boy_right_2.png");

        try {
            up1 = ImageIO.read(new FileInputStream(imageUp1));
            up2 = ImageIO.read(new FileInputStream(imageUp2));
            down1 = ImageIO.read(new FileInputStream(imageDown1));
            down2 = ImageIO.read(new FileInputStream(imageDown2));
            left1 = ImageIO.read(new FileInputStream(imageLeft1));
            left2 = ImageIO.read(new FileInputStream(imageLeft2));
            right1 = ImageIO.read(new FileInputStream(imageRight1));
            right2 = ImageIO.read(new FileInputStream(imageRight2));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() { // update the position of the player
        if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {
            if (keyHandler.upPressed) {
                y -= speed;
                direction = "up";
            } else if (keyHandler.downPressed) {
                y += speed;
                direction = "down";
            } else if (keyHandler.leftPressed) {
                x -= speed;
                direction = "left";
            } else if (keyHandler.rightPressed) {
                x += speed;
                direction = "right";
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

    public void draw(Graphics2D g2d) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNumber == 1) {
                    image = up1;
                }
                if (spriteNumber == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNumber == 1) {
                    image = down1;
                }
                if (spriteNumber == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNumber == 1) {
                    image = left1;
                }
                if (spriteNumber == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNumber == 1) {
                    image = right1;
                }
                if (spriteNumber == 2) {
                    image = right2;
                }
                break;
        }

        g2d.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);

        // g2d.setColor(Color.red);
        // g2d.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);
    }
}
