package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gamePanel;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;

    public KeyHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // PLAY STATE
        if (gamePanel.gameState == gamePanel.playState) {
            if (keyCode == KeyEvent.VK_UP) {
                upPressed = true;
            } else if (keyCode == KeyEvent.VK_DOWN) {
                downPressed = true;
            } else if (keyCode == KeyEvent.VK_LEFT) {
                leftPressed = true;
            } else if (keyCode == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            } else if (keyCode == KeyEvent.VK_P) {
                gamePanel.gameState = gamePanel.pauseState;
            } else if (keyCode == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }
        }

        // PAUSE STATE
        else if (gamePanel.gameState == gamePanel.pauseState) {
            if (keyCode == KeyEvent.VK_P) {
                gamePanel.gameState = gamePanel.playState;
            }
        }

        // DIALOG STATE
        else if (gamePanel.gameState == gamePanel.dialogState) {
            if (keyCode == KeyEvent.VK_ENTER) {
                gamePanel.gameState = gamePanel.playState;
                // change direction of player
                if (gamePanel.sim.direction == "up") {
                    gamePanel.sim.direction = "down";
                } else if (gamePanel.sim.direction == "down") {
                    gamePanel.sim.direction = "up";
                } else if (gamePanel.sim.direction == "left") {
                    gamePanel.sim.direction = "right";
                } else if (gamePanel.sim.direction == "right") {
                    gamePanel.sim.direction = "left";
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
    }

}
