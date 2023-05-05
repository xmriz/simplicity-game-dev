package entity;

import java.util.Random;

import main.*;

public class NPC_Kakek extends Entity {

    public NPC_Kakek(GamePanel gamePanel) {
        super(gamePanel);

        setDirection("down");
        setSpeed(1);
        getImage();
        setDialog();
    }

    public void getImage() {
        setUp1(setupImage("npc/kakek_up_1"));
        setUp2(setupImage("npc/kakek_up_2"));
        setDown1(setupImage("npc/kakek_down_1"));
        setDown2(setupImage("npc/kakek_down_2"));
        setLeft1(setupImage("npc/kakek_left_1"));
        setLeft2(setupImage("npc/kakek_left_2"));
        setRight1(setupImage("npc/kakek_right_1"));
        setRight2(setupImage("npc/kakek_right_2"));
    }

    public void setDialog() {
        getDialogs().add("Hai, Nak.");
        getDialogs().add("Apakah kamu pernah melihatku \nsebelumnya?");
        getDialogs().add("Berhati-hatilah!");
        getDialogs().add("...");
    }

    @Override
    public void setAction() {
        incActionLoctCounter(1);

        if (getActionLoctCounter() > 120) {
            setActionLoctCounter(0);
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                setDirection("up");
            } else if (i <= 50) {
                setDirection("down");
            } else if (i <= 75) {
                setDirection("left");
            } else {
                setDirection("right");
            }
        }
    }

    public void speak() {
        // NPC specific dialog
        super.speak();
    }
}
