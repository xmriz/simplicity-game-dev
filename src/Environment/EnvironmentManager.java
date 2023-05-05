package environment;

import java.awt.Graphics2D;

import main.GamePanel;

// environment filter : dark, light, rain, snow, fog, etc

public class EnvironmentManager {
    private GamePanel gamePanel;
    private Lighting lighting;

    public GamePanel getGamePanel(){
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel){
        this.gamePanel=gamePanel;
    }

    public Lighting getLighting(){
        return lighting;
    }

    public void setLighting(Lighting lighting){
        this.lighting=lighting;
    }

    public EnvironmentManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setup() {
        lighting = new Lighting(gamePanel);
    }

    public void update() {
        lighting.update();
    }

    public void draw(Graphics2D g2d) {
        lighting.draw(g2d);
    }
}
