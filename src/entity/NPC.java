package entity;

import java.util.Random;

import main.*;

public class NPC extends Entity {
    public NPC(GamePanel gamePanel) {
        super(gamePanel);

        direction = "down";
        speed = 1;

        getImage();
    }

    public void getImage(){
        up1 = setupImage("npc/kakek_up_1");
        up2 = setupImage("npc/kakek_up_2");
        down1 = setupImage("npc/kakek_down_1");
        down2 = setupImage("npc/kakek_down_2");
        left1 = setupImage("npc/kakek_left_1");
        left2 = setupImage("npc/kakek_left_2");
        right1 = setupImage("npc/kakek_right_1");
        right2 = setupImage("npc/kakek_right_2");
    }

    public void setAction(){
        actionLockCounter++;

        if (actionLockCounter > 120){
            actionLockCounter = 0;
            Random random = new Random();
            int i = random.nextInt(100)+1;
    
            if (i <= 25){
                direction = "up";
            }
            if (i > 25 && i <= 50){
                direction = "down";
            }
            if (i > 50 && i <= 75){
                direction = "left";
            }
            if (i > 75 && i <= 100){
                direction = "right";
            }
        }
    }
}
