package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javafx.scene.input.KeyCode;

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

        // TITLE STATE
        if (gamePanel.gameState == gamePanel.titleState) {
            if (gamePanel.ui.titleScreenState == 0) {
                if (keyCode == KeyEvent.VK_UP) {
                    gamePanel.ui.commandNumber--;
                    if (gamePanel.ui.commandNumber < 0) {
                        gamePanel.ui.commandNumber = 2;
                    }
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    gamePanel.ui.commandNumber++;
                    if (gamePanel.ui.commandNumber > 2) {
                        gamePanel.ui.commandNumber = 0;
                    }
                } else if (keyCode == KeyEvent.VK_ENTER) {
                    if (gamePanel.ui.commandNumber == 0) {
                        gamePanel.ui.titleScreenState = 1; // go to input name screen
                    } else if (gamePanel.ui.commandNumber == 1) {
                        // later
                    } else if (gamePanel.ui.commandNumber == 2) {
                        System.exit(0);
                    }
                }
            } else if (gamePanel.ui.titleScreenState == 1) {
                if (keyCode == KeyEvent.VK_UP) {
                    gamePanel.ui.commandNumber--;
                    if (gamePanel.ui.commandNumber < 0) {
                        gamePanel.ui.commandNumber = 1;
                    }
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    gamePanel.ui.commandNumber++;
                    if (gamePanel.ui.commandNumber > 1) {
                        gamePanel.ui.commandNumber = 0;
                    }
                } else if (keyCode == KeyEvent.VK_BACK_SPACE && gamePanel.ui.inputFirstSimName.length() > 0){
                    gamePanel.ui.inputFirstSimName = gamePanel.ui.inputFirstSimName.substring(0, gamePanel.ui.inputFirstSimName.length() - 1);
                } else if (keyCode == KeyEvent.VK_A && gamePanel.ui.inputFirstSimName.length() < 25) {
                    gamePanel.ui.inputFirstSimName += "A";
                } else if (keyCode == KeyEvent.VK_B && gamePanel.ui.inputFirstSimName.length() < 25) {
                    gamePanel.ui.inputFirstSimName += "B";
                } else if (keyCode == KeyEvent.VK_C && gamePanel.ui.inputFirstSimName.length() < 25) {
                    gamePanel.ui.inputFirstSimName += "C";
                } else if (keyCode == KeyEvent.VK_D && gamePanel.ui.inputFirstSimName.length() < 25) {
                    gamePanel.ui.inputFirstSimName += "D";
                } else if (keyCode == KeyEvent.VK_E && gamePanel.ui.inputFirstSimName.length() < 25) {
                    gamePanel.ui.inputFirstSimName += "E";
                } else if (keyCode == KeyEvent.VK_F && gamePanel.ui.inputFirstSimName.length() < 25) {
                    gamePanel.ui.inputFirstSimName += "F";
                } else if (keyCode == KeyEvent.VK_G && gamePanel.ui.inputFirstSimName.length() < 25) {
                    gamePanel.ui.inputFirstSimName += "G";
                } else if (keyCode == KeyEvent.VK_H && gamePanel.ui.inputFirstSimName.length() < 25) {
                    gamePanel.ui.inputFirstSimName += "H";
                } else if (keyCode == KeyEvent.VK_I && gamePanel.ui.inputFirstSimName.length() < 25) {
                    gamePanel.ui.inputFirstSimName += "I";
                } else if (keyCode == KeyEvent.VK_J && gamePanel.ui.inputFirstSimName.length() < 25) {
                    gamePanel.ui.inputFirstSimName += "J";
                } else if (keyCode == KeyEvent.VK_K && gamePanel.ui.inputFirstSimName.length() < 25) {
                    gamePanel.ui.inputFirstSimName += "K";
                } else if (keyCode == KeyEvent.VK_L && gamePanel.ui.inputFirstSimName.length() < 25) {
                    gamePanel.ui.inputFirstSimName += "L";
                } else if (keyCode == KeyEvent.VK_M && gamePanel.ui.inputFirstSimName.length() < 25) {
                    gamePanel.ui.inputFirstSimName += "M";
                } else if (keyCode == KeyEvent.VK_N && gamePanel.ui.inputFirstSimName.length() < 25) {
                    gamePanel.ui.inputFirstSimName += "N";
                } else if (keyCode == KeyEvent.VK_O && gamePanel.ui.inputFirstSimName.length() < 25) {
                    gamePanel.ui.inputFirstSimName += "O";
                } else if (keyCode == KeyEvent.VK_P && gamePanel.ui.inputFirstSimName.length() < 25) {
                    gamePanel.ui.inputFirstSimName += "P";
                } else if (keyCode == KeyEvent.VK_Q && gamePanel.ui.inputFirstSimName.length() < 25) {
                    gamePanel.ui.inputFirstSimName += "Q";
                } else if (keyCode == KeyEvent.VK_R && gamePanel.ui.inputFirstSimName.length() < 25) {
                    gamePanel.ui.inputFirstSimName += "R";
                } else if (keyCode == KeyEvent.VK_S && gamePanel.ui.inputFirstSimName.length() < 25) {
                    gamePanel.ui.inputFirstSimName += "S";
                } else if (keyCode == KeyEvent.VK_T && gamePanel.ui.inputFirstSimName.length() < 25) {
                    gamePanel.ui.inputFirstSimName += "T";
                } else if (keyCode == KeyEvent.VK_U && gamePanel.ui.inputFirstSimName.length() < 25) {
                    gamePanel.ui.inputFirstSimName += "U";
                } else if (keyCode == KeyEvent.VK_V && gamePanel.ui.inputFirstSimName.length() < 25) {
                    gamePanel.ui.inputFirstSimName += "V";
                } else if (keyCode == KeyEvent.VK_W && gamePanel.ui.inputFirstSimName.length() < 25) {
                    gamePanel.ui.inputFirstSimName += "W";
                } else if (keyCode == KeyEvent.VK_X && gamePanel.ui.inputFirstSimName.length() < 25) {
                    gamePanel.ui.inputFirstSimName += "X";
                } else if (keyCode == KeyEvent.VK_Y && gamePanel.ui.inputFirstSimName.length() < 25) {
                    gamePanel.ui.inputFirstSimName += "Y";
                } else if (keyCode == KeyEvent.VK_Z && gamePanel.ui.inputFirstSimName.length() < 25) {
                    gamePanel.ui.inputFirstSimName += "Z";
                } else if (keyCode == KeyEvent.VK_SPACE && gamePanel.ui.inputFirstSimName.length() < 25) {
                    gamePanel.ui.inputFirstSimName += " ";
                } else if (keyCode == KeyEvent.VK_ENTER) {
                    if (gamePanel.ui.commandNumber == 0) { // start game
                        gamePanel.sim.nama = gamePanel.ui.inputFirstSimName;
                        if (gamePanel.sim.nama.length() == 0) {
                            gamePanel.sim.nama = "Sim";
                        } else {
                            // capitalize first letter
                            String[] pecah = gamePanel.sim.nama.split(" ");
                            StringBuilder sb = new StringBuilder();
                            for (int i = 0; i < pecah.length; i++) {
                                sb.append(pecah[i].substring(0, 1).toUpperCase());
                                sb.append(pecah[i].substring(1).toLowerCase());
                                sb.append(" ");
                            }
                            gamePanel.sim.nama = sb.toString().trim();
                        }
                        // set game state to play state
                        gamePanel.gameState = gamePanel.playState;
                    } else if (gamePanel.ui.commandNumber == 1) { // back to Title Screen 0
                        gamePanel.ui.titleScreenState = 0;
                        gamePanel.ui.commandNumber = 0;
                        gamePanel.ui.inputFirstSimName = "";
                    } 
                }
            }
        }

        // PLAY STATE
        if (gamePanel.gameState == gamePanel.playState) {
            playState(keyCode);
        }

        // PAUSE STATE
        else if (gamePanel.gameState == gamePanel.pauseState) {
            pauseState(keyCode);
        }

        // SIM INFO STATE
        else if (gamePanel.gameState == gamePanel.simInfoState) {
            simInfoState(keyCode);
        }

        // DIALOG STATE
        else if (gamePanel.gameState == gamePanel.dialogState) {
            dialogState(keyCode);
        }
    }

    public void playState(int keyCode) {
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
        } else if (keyCode == KeyEvent.VK_C) {
            gamePanel.gameState = gamePanel.simInfoState;
        } else if (keyCode == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
    }

    public void pauseState(int keyCode) {
        if (keyCode == KeyEvent.VK_P) {
            gamePanel.gameState = gamePanel.playState;
        }
    }

    public void simInfoState(int keyCode) {
        if (keyCode == KeyEvent.VK_C) {
            gamePanel.gameState = gamePanel.playState;
        }
    }

    public void dialogState(int keyCode) {
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
