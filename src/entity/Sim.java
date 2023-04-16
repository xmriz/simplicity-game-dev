package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.*;

public class Sim extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;

    public Sim(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        screenX = gamePanel.screenWidth/2 - (gamePanel.tileSize/2); // set the player's position on the screen (always center)
        screenY = gamePanel.screenHeight/2 - (gamePanel.tileSize/2);

        solidArea = new Rectangle(8,16,32,32); 

        setDefaultValues();
        getPlayerImage();
    }
 
    public void setDefaultValues() { // set the default values of the player
        worldX = 1*gamePanel.tileSize; // set the player's position in the world
        worldY = 1*gamePanel.tileSize;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        File imageUp1 = new File("assets/sim/p2_up_1.png");
        File imageUp2 = new File("assets/sim/p2_up_2.png");
        File imageDown1 = new File("assets/sim/p2_down_1.png");
        File imageDown2 = new File("assets/sim/p2_down_2.png");
        File imageLeft1 = new File("assets/sim/p2_left_1.png");
        File imageLeft2 = new File("assets/sim/p2_left_2.png");
        File imageRight1 = new File("assets/sim/p2_right_1.png");
        File imageRight2 = new File("assets/sim/p2_right_2.png");

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

            // if there is a collision, sim can't move
            if (!collisionOn){
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
                    default:
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

        g2d.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);

        // g2d.setColor(Color.red);
        // g2d.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);
    }
}
