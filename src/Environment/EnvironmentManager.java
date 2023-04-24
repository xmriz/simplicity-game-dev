package environment;

import java.awt.Graphics2D;

import main.GamePanel;

// environment filter : dark, light, rain, snow, fog, etc

public class EnvironmentManager {
    GamePanel gamePanel;
    Lighting lighting;

    public EnvironmentManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setup () {
        lighting = new Lighting(gamePanel);
    }

    public void update(){
        lighting.update();
    }

    public void draw (Graphics2D g2d) {
        lighting.draw(g2d);
    }
}
