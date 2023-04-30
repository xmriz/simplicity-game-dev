package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import benda.Furnitur;
import entity.Sim;

public class KeyHandler implements KeyListener {

    GamePanel gamePanel;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;

    public Thread threadTemp;

    boolean checkWorldTime = false;
    boolean checkCurrentLocation = false;
    int moveCounter = 1;

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
                    cursorSound();
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    gamePanel.ui.commandNumber++;
                    if (gamePanel.ui.commandNumber > 2) {
                        gamePanel.ui.commandNumber = 0;
                    }
                    cursorSound();
                } else if (keyCode == KeyEvent.VK_ENTER) {
                    if (gamePanel.ui.commandNumber == 0) {
                        gamePanel.ui.titleScreenState = 1; // go to input name screen
                    } else if (gamePanel.ui.commandNumber == 1) {
                        // later
                    } else if (gamePanel.ui.commandNumber == 2) {
                        System.exit(0);
                    }
                    cursorSound();
                }
            } else if (gamePanel.ui.titleScreenState == 1) {
                if (keyCode == KeyEvent.VK_UP) {
                    gamePanel.ui.commandNumber--;
                    if (gamePanel.ui.commandNumber < 0) {
                        gamePanel.ui.commandNumber = 1;
                    }
                    cursorSound();
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    gamePanel.ui.commandNumber++;
                    if (gamePanel.ui.commandNumber > 1) {
                        gamePanel.ui.commandNumber = 0;
                    }
                    cursorSound();
                } else if (keyCode == KeyEvent.VK_BACK_SPACE && gamePanel.ui.inputFirstSimName.length() > 0) {
                    gamePanel.ui.inputFirstSimName = gamePanel.ui.inputFirstSimName.substring(0,
                            gamePanel.ui.inputFirstSimName.length() - 1);
                    cursorSound();
                } else if (keyCode == KeyEvent.VK_ENTER) {
                    if (gamePanel.ui.commandNumber == 0) { // start game
                        gamePanel.listSim.get(gamePanel.indexCurrentSim).nama = gamePanel.ui.inputFirstSimName;
                        if (gamePanel.listSim.get(gamePanel.indexCurrentSim).nama.length() == 0) {
                            gamePanel.listSim.get(gamePanel.indexCurrentSim).nama = "Sim";
                        } else {
                            // capitalize first letter
                            gamePanel.listSim.get(gamePanel.indexCurrentSim).nama = UtilityTool
                                    .capitalizeFirstLetter(gamePanel.listSim.get(gamePanel.indexCurrentSim).nama);
                            // set currentlocation
                        }
                        gamePanel.listSim.get(gamePanel.indexCurrentSim).currentLocation = "Rumah "
                                + gamePanel.listSim.get(gamePanel.indexCurrentSim).nama + " ("
                                + UtilityTool.capitalizeFirstLetter(
                                        gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(0).name)
                                + ")";
                        // set game state to play state
                        gamePanel.gameState = gamePanel.playState;
                        gamePanel.stopMusic();
                        gamePanel.playMusic(1);
                    } else if (gamePanel.ui.commandNumber == 1) { // back to Title Screen 0
                        gamePanel.ui.titleScreenState = 0;
                        gamePanel.ui.commandNumber = 0;
                        gamePanel.ui.inputFirstSimName = "";
                    }
                    cursorSound();
                }

                // input name
                if (gamePanel.ui.inputFirstSimName.length() < 25) {
                    if (keyCode == KeyEvent.VK_A) {
                        gamePanel.ui.inputFirstSimName += "A";
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_B) {
                        gamePanel.ui.inputFirstSimName += "B";
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_C) {
                        gamePanel.ui.inputFirstSimName += "C";
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_D) {
                        gamePanel.ui.inputFirstSimName += "D";
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_E) {
                        gamePanel.ui.inputFirstSimName += "E";
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_F) {
                        gamePanel.ui.inputFirstSimName += "F";
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_G) {
                        gamePanel.ui.inputFirstSimName += "G";
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_H) {
                        gamePanel.ui.inputFirstSimName += "H";
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_I) {
                        gamePanel.ui.inputFirstSimName += "I";
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_J) {
                        gamePanel.ui.inputFirstSimName += "J";
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_K) {
                        gamePanel.ui.inputFirstSimName += "K";
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_L) {
                        gamePanel.ui.inputFirstSimName += "L";
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_M) {
                        gamePanel.ui.inputFirstSimName += "M";
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_N) {
                        gamePanel.ui.inputFirstSimName += "N";
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_O) {
                        gamePanel.ui.inputFirstSimName += "O";
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_P) {
                        gamePanel.ui.inputFirstSimName += "P";
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_Q) {
                        gamePanel.ui.inputFirstSimName += "Q";
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_R) {
                        gamePanel.ui.inputFirstSimName += "R";
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_S) {
                        gamePanel.ui.inputFirstSimName += "S";
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_T) {
                        gamePanel.ui.inputFirstSimName += "T";
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_U) {
                        gamePanel.ui.inputFirstSimName += "U";
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_V) {
                        gamePanel.ui.inputFirstSimName += "V";
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_W) {
                        gamePanel.ui.inputFirstSimName += "W";
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_X) {
                        gamePanel.ui.inputFirstSimName += "X";
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_Y) {
                        gamePanel.ui.inputFirstSimName += "Y";
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_Z) {
                        gamePanel.ui.inputFirstSimName += "Z";
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_SPACE) {
                        gamePanel.ui.inputFirstSimName += " ";
                        cursorSound();
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

        // INVENTORY STATE
        else if (gamePanel.gameState == gamePanel.inventoryState) {
            inventoryState(keyCode);
        }

        // BELI STATE
        else if (gamePanel.gameState == gamePanel.beliState) {
            beliState(keyCode);
        }

        // UPGRADE RUMAH STATE
        else if (gamePanel.gameState == gamePanel.upgradeRumahState) {
            upgradeRumahState(keyCode);
        }

        // INPUT NAMA RUANGAN STATE
        else if (gamePanel.gameState == gamePanel.inputNamaRuanganState) {
            inputNamaRuanganState(keyCode);
        }

        // INPUT KOORDINAT BENDA STATE
        else if (gamePanel.gameState == gamePanel.inputKoordinatBendaState) {
            inputKoordinatBendaState(keyCode);
        }

        // ADD SIM STATE
        else if (gamePanel.gameState == gamePanel.addSimState) {
            addSimState(keyCode);
        }

        // INPUT NAMA SIM STARE
        else if (gamePanel.gameState == gamePanel.inputKoordinatRumahSimState) {
            inputKoordinatRumahSimState(keyCode);
        }

        // MENU STATE
        else if (gamePanel.gameState == gamePanel.menuState) {
            menuState(keyCode);
        }

        // HELP STATE
        else if (gamePanel.gameState == gamePanel.helpState) {
            helpState(keyCode);
        }

        // CHANGE SIM STATE
        else if (gamePanel.gameState == gamePanel.changeSimState) {
            changeSimState(keyCode);
        }
        // MAP STATE
        else if (gamePanel.gameState == gamePanel.mapState) {
            mapState(keyCode);
        }

        // RESEP STATE
        else if (gamePanel.gameState == gamePanel.resepState) {
            resepState(keyCode);
        }

        // INPUT DURASI TIDUR STATE
        else if (gamePanel.gameState == gamePanel.inputDurasiTidurState) {
            inputDurasiTidurState(keyCode);
        }

        // INPUT DURASI NONTON STATE
        else if (gamePanel.gameState == gamePanel.inputDurasiNontonState) {
            inputDurasiNontonState(keyCode);
        }

        // INPUT DURASI MANDI STATE
        else if (gamePanel.gameState == gamePanel.inputDurasiMandiState) {
            inputDurasiMandiState(keyCode);
        }

        // SALAT STATE
        else if (gamePanel.gameState == gamePanel.inputDurasiSalatState){
            inputDurasiSalatState(keyCode);
        }

        // BACA BUKU STATE
        else if (gamePanel.gameState == gamePanel.inputDurasiBacaBukuState){
            inputDurasiBacaBukuState(keyCode);
        }

        // INPUT DURASI RADIO STATE
        else if (gamePanel.gameState == gamePanel.inputDurasiRadioState) {
            inputDurasiRadioState(keyCode);
        }

        // TIMER STATE
        else if (gamePanel.gameState == gamePanel.timerState) {
            timerState(keyCode);
        }

        // GAME OVER STATE
        else if (gamePanel.gameState == gamePanel.gameOverState) {
            gameOverState(keyCode);
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
        } else if (keyCode == KeyEvent.VK_I) {
            gamePanel.gameState = gamePanel.inventoryState;
        } else if (keyCode == KeyEvent.VK_B) {
            gamePanel.ui.charIndex = 0;
            gamePanel.ui.combinedText = "";
            gamePanel.npc[0][4].speak();
        } else if (keyCode == KeyEvent.VK_ENTER) {
            enterPressed = true;
        } else if (keyCode == KeyEvent.VK_T) {
            if (checkWorldTime == false) {
                checkWorldTime = true;
            } else {
                checkWorldTime = false;
            }
        } else if (keyCode == KeyEvent.VK_L) {
            if (checkCurrentLocation == false) {
                checkCurrentLocation = true;
            } else {
                checkCurrentLocation = false;
            }
        } else if (keyCode == KeyEvent.VK_U) {
            // System.out.println(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexRumahYangDimasuki);
            if (gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap == 0) {
                gamePanel.ui.charIndex = 0;
                gamePanel.ui.combinedText = "";
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.ui.currentDialog = "Anda sedang tidak berada di rumah.\nTidak dapat melakukan upgrade rumah!";
            } else if (gamePanel.listSim
                    .get(gamePanel.indexCurrentSim).indexRumahYangDimasuki != gamePanel.indexCurrentSim) {
                gamePanel.ui.charIndex = 0;
                gamePanel.ui.combinedText = "";
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.ui.currentDialog = "Anda harus berada di rumah sendiri.\nTidak dapat melakukan upgrade rumah!";
            } else {
                gamePanel.gameState = gamePanel.upgradeRumahState;
            }

        } else if (keyCode == KeyEvent.VK_N) {
            gamePanel.gameState = gamePanel.addSimState;
        } else if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.gameState = gamePanel.menuState;
        } else if (keyCode == KeyEvent.VK_G) {
            gamePanel.gameState = gamePanel.changeSimState;
        } else if (keyCode == KeyEvent.VK_M) {
            if (gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap == 0) {
                gamePanel.gameState = gamePanel.mapState;
            } else {
                gamePanel.ui.charIndex = 0;
                gamePanel.ui.combinedText = "";
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.ui.currentDialog = "Tidak dapat membuka peta di rumah!";
            }
        } else if (keyCode == KeyEvent.VK_X) {
            if (gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap == 0) {
                if (gamePanel.map.mapOn == false) {
                    gamePanel.map.mapOn = true;
                } else {
                    gamePanel.map.mapOn = false;
                }
            } else {
                gamePanel.ui.charIndex = 0;
                gamePanel.ui.combinedText = "";
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.ui.currentDialog = "Tidak dapat membuka mini peta\ndi rumah!";
            }
        } else if (keyCode == KeyEvent.VK_Z) {
            if (gamePanel.getCurrentSim().currentMap == 1) {
                gamePanel.getCurrentSim().pickUpObject(gamePanel.getCurrentSim().indexBendaYangDisentuh);
            }
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
            // if (gamePanel.listSim.get(gamePanel.indexCurrentSim).direction == "up") {
            // gamePanel.listSim.get(gamePanel.indexCurrentSim).direction = "down";
            // } else if (gamePanel.listSim.get(gamePanel.indexCurrentSim).direction ==
            // "down") {
            // gamePanel.listSim.get(gamePanel.indexCurrentSim).direction = "up";
            // } else if (gamePanel.listSim.get(gamePanel.indexCurrentSim).direction ==
            // "left") {
            // gamePanel.listSim.get(gamePanel.indexCurrentSim).direction = "right";
            // } else if (gamePanel.listSim.get(gamePanel.indexCurrentSim).direction ==
            // "right") {
            // gamePanel.listSim.get(gamePanel.indexCurrentSim).direction = "left";
            // }
        }
    }

    public void inventoryState(int keyCode) {
        int index;
        if (keyCode == KeyEvent.VK_I) {
            gamePanel.gameState = gamePanel.playState;
            gamePanel.ui.simSlotRow = 0;
            gamePanel.ui.simSlotCol = 0;
        } else if (keyCode == KeyEvent.VK_UP) {
            if (gamePanel.ui.simSlotRow > 0) {
                index = UI.getItemIndexOnSlot(gamePanel.ui.simSlotRow - 1, gamePanel.ui.simSlotCol);
                if (index <= gamePanel.listSim.get(gamePanel.indexCurrentSim).inventory.size() - 1) {
                    gamePanel.ui.simSlotRow--;
                    cursorSound();
                }
            } else {
                index = UI.getItemIndexOnSlot(2, gamePanel.ui.simSlotCol);
                if (index <= gamePanel.listSim.get(gamePanel.indexCurrentSim).inventory.size() - 1) {
                    gamePanel.ui.simSlotRow = 2;
                }
            }
        } else if (keyCode == KeyEvent.VK_DOWN) {
            if (gamePanel.ui.simSlotRow < 2) {
                index = UI.getItemIndexOnSlot(gamePanel.ui.simSlotRow + 1, gamePanel.ui.simSlotCol);
                if (index <= gamePanel.listSim.get(gamePanel.indexCurrentSim).inventory.size() - 1) {
                    gamePanel.ui.simSlotRow++;
                    cursorSound();
                }
            } else {
                index = UI.getItemIndexOnSlot(0, gamePanel.ui.simSlotCol);
                if (index <= gamePanel.listSim.get(gamePanel.indexCurrentSim).inventory.size() - 1) {
                    gamePanel.ui.simSlotRow = 0;
                }
            }
        } else if (keyCode == KeyEvent.VK_LEFT) {
            if (gamePanel.ui.simSlotCol > 0) {
                index = UI.getItemIndexOnSlot(gamePanel.ui.simSlotRow, gamePanel.ui.simSlotCol - 1);
                if (index <= gamePanel.listSim.get(gamePanel.indexCurrentSim).inventory.size() - 1) {
                    gamePanel.ui.simSlotCol--;
                    cursorSound();
                }
            } else {
                index = UI.getItemIndexOnSlot(gamePanel.ui.simSlotRow, 10);
                if (index <= gamePanel.listSim.get(gamePanel.indexCurrentSim).inventory.size() - 1) {
                    gamePanel.ui.simSlotCol = 10;
                }
            }
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            if (gamePanel.ui.simSlotCol < 10) {
                index = UI.getItemIndexOnSlot(gamePanel.ui.simSlotRow, gamePanel.ui.simSlotCol + 1);
                if (index <= gamePanel.listSim.get(gamePanel.indexCurrentSim).inventory.size() - 1) {
                    gamePanel.ui.simSlotCol++;
                    cursorSound();
                }
            } else {
                index = UI.getItemIndexOnSlot(gamePanel.ui.simSlotRow, 0);
                if (index <= gamePanel.listSim.get(gamePanel.indexCurrentSim).inventory.size() - 1) {
                    gamePanel.ui.simSlotCol = 0;
                }
            }
        } else if (keyCode == KeyEvent.VK_ENTER) {
            gamePanel.listSim.get(gamePanel.indexCurrentSim).selectItem();
            gamePanel.ui.simSlotRow = 0;
            gamePanel.ui.simSlotCol = 0;
            cursorSound();
        }
    }

    public void menuState(int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.gameState = gamePanel.playState;
            gamePanel.ui.commandNumber = 0;
        }
        if (gamePanel.ui.commandNumber == 0) {
            if (keyCode == KeyEvent.VK_DOWN) {
                gamePanel.ui.commandNumber++;
                cursorSound();
            } else if (keyCode == KeyEvent.VK_UP) {
                gamePanel.ui.commandNumber = 5;
                cursorSound();
            } else if (keyCode == KeyEvent.VK_LEFT) {
                if (gamePanel.music.volumeScale > 0) {
                    gamePanel.music.volumeScale--;
                    gamePanel.music.checkVolume();
                    cursorSound();
                }
            } else if (keyCode == KeyEvent.VK_RIGHT) {
                if (gamePanel.music.volumeScale < 5) {
                    gamePanel.music.volumeScale++;
                    gamePanel.music.checkVolume();
                    cursorSound();
                }
            }
        } else if (gamePanel.ui.commandNumber == 4) {
            if (keyCode == KeyEvent.VK_UP) {
                gamePanel.ui.commandNumber--;
                cursorSound();
            } else if (keyCode == KeyEvent.VK_DOWN) {
                gamePanel.ui.commandNumber++;
                cursorSound();
            } else if (keyCode == KeyEvent.VK_ENTER) {
                gamePanel.gameState = gamePanel.titleState;
                gamePanel.ui.commandNumber = 0;
                gamePanel.ui.titleScreenState = 0;
                gamePanel.stopMusic();
                gamePanel.playMusic(0);
                cursorSound();
            }
        } else if (gamePanel.ui.commandNumber == 5) {
            if (keyCode == KeyEvent.VK_UP) {
                gamePanel.ui.commandNumber--;
                cursorSound();
            } else if (keyCode == KeyEvent.VK_DOWN) {
                gamePanel.ui.commandNumber = 0;
                cursorSound();
            } else if (keyCode == KeyEvent.VK_ENTER) {
                gamePanel.gameState = gamePanel.playState;
                gamePanel.ui.commandNumber = 0;
                cursorSound();
            }
        } else if (gamePanel.ui.commandNumber == 1) {
            if (keyCode == KeyEvent.VK_UP) {
                gamePanel.ui.commandNumber--;
                cursorSound();
            } else if (keyCode == KeyEvent.VK_DOWN) {
                gamePanel.ui.commandNumber++;
                cursorSound();
            } else if (keyCode == KeyEvent.VK_LEFT) {
                if (gamePanel.soundEffect.volumeScale > 0) {
                    gamePanel.soundEffect.volumeScale--;
                    cursorSound();
                }
            } else if (keyCode == KeyEvent.VK_RIGHT) {
                if (gamePanel.soundEffect.volumeScale < 5) {
                    gamePanel.soundEffect.volumeScale++;
                    cursorSound();
                }
            }
        } else if (gamePanel.ui.commandNumber == 2) {
            if (keyCode == KeyEvent.VK_UP) {
                gamePanel.ui.commandNumber--;
                cursorSound();
            } else if (keyCode == KeyEvent.VK_DOWN) {
                gamePanel.ui.commandNumber++;
                cursorSound();
            } else if (keyCode == KeyEvent.VK_ENTER) {
                gamePanel.gameState = gamePanel.helpState;
                gamePanel.ui.commandNumber = 0;
                cursorSound();
            }
        } else if (gamePanel.ui.commandNumber == 3) {
            if (keyCode == KeyEvent.VK_UP) {
                gamePanel.ui.commandNumber--;
                cursorSound();
            } else if (keyCode == KeyEvent.VK_DOWN) {
                gamePanel.ui.commandNumber++;
                cursorSound();
            }
        }
    }

    public void helpState(int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.gameState = gamePanel.menuState;
        }
    }

    public void mapState(int keyCode) {
        if (keyCode == KeyEvent.VK_M) {
            gamePanel.gameState = gamePanel.playState;
        }
    }

    public void beliState(int keyCode) {
        if (keyCode == KeyEvent.VK_ENTER) {
            enterPressed = true;
            cursorSound();
        }
        if (gamePanel.ui.subState == 0) {
            if (keyCode == KeyEvent.VK_UP) {
                gamePanel.ui.commandNumber--;
                if (gamePanel.ui.commandNumber < 0) {
                    gamePanel.ui.commandNumber = 1;
                }
                cursorSound();
            } else if (keyCode == KeyEvent.VK_DOWN) {
                gamePanel.ui.commandNumber++;
                if (gamePanel.ui.commandNumber > 1) {
                    gamePanel.ui.commandNumber = 0;
                }
                cursorSound();
            }
        }
        if (gamePanel.ui.subState == 1) {
            beliInventory(keyCode);
            if (keyCode == KeyEvent.VK_ESCAPE) {
                gamePanel.ui.subState = 0;
                cursorSound();
            }
        }
    }

    public void beliInventory(int keyCode) {
        int index = 0;
        if (keyCode == KeyEvent.VK_B) {
            gamePanel.gameState = gamePanel.playState;
            gamePanel.ui.subState = 0;
        } else if (keyCode == KeyEvent.VK_UP) {
            if (gamePanel.ui.npcSlotRow > 0) {
                index = UI.getItemIndexOnSlot(gamePanel.ui.npcSlotRow - 1, gamePanel.ui.npcSlotCol);
                if (index <= gamePanel.npc[0][4].inventory.size() - 1) {
                    gamePanel.ui.npcSlotRow--;
                    cursorSound();
                }
            } else {
                index = UI.getItemIndexOnSlot(2, gamePanel.ui.npcSlotCol);
                if (index <= gamePanel.npc[0][4].inventory.size() - 1) {
                    gamePanel.ui.npcSlotRow = 2;
                }
            }
        } else if (keyCode == KeyEvent.VK_DOWN) {
            if (gamePanel.ui.npcSlotRow < 2) {
                index = UI.getItemIndexOnSlot(gamePanel.ui.npcSlotRow + 1, gamePanel.ui.npcSlotCol);
                if (index <= gamePanel.npc[0][4].inventory.size() - 1) {
                    gamePanel.ui.npcSlotRow++;
                    cursorSound();
                }
            } else {
                index = UI.getItemIndexOnSlot(0, gamePanel.ui.npcSlotCol);
                if (index <= gamePanel.npc[0][4].inventory.size() - 1) {
                    gamePanel.ui.npcSlotRow = 0;
                }
            }
        } else if (keyCode == KeyEvent.VK_LEFT) {
            if (gamePanel.ui.npcSlotCol > 0) {
                index = UI.getItemIndexOnSlot(gamePanel.ui.npcSlotRow, gamePanel.ui.npcSlotCol - 1);
                if (index <= gamePanel.npc[0][4].inventory.size() - 1) {
                    gamePanel.ui.npcSlotCol--;
                    cursorSound();
                }
            } else {
                index = UI.getItemIndexOnSlot(gamePanel.ui.npcSlotRow, 10);
                if (index <= gamePanel.npc[0][4].inventory.size() - 1) {
                    gamePanel.ui.npcSlotCol = 10;
                }
            }
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            if (gamePanel.ui.npcSlotCol < 10) {
                index = UI.getItemIndexOnSlot(gamePanel.ui.npcSlotRow, gamePanel.ui.npcSlotCol + 1);
                if (index <= gamePanel.npc[0][4].inventory.size() - 1) {
                    gamePanel.ui.npcSlotCol++;
                    cursorSound();
                }
            } else {
                index = UI.getItemIndexOnSlot(gamePanel.ui.npcSlotRow, 0);
                if (index <= gamePanel.npc[0][4].inventory.size() - 1) {
                    gamePanel.ui.npcSlotCol = 0;
                }
            }
        }
    }

    public void upgradeRumahState(int keyCode) {
        if (keyCode == KeyEvent.VK_U) {
            gamePanel.gameState = gamePanel.playState;
            gamePanel.ui.commandNumber = 0;
        }

        if (keyCode == KeyEvent.VK_UP) {
            gamePanel.ui.commandNumber--;
            if (gamePanel.ui.commandNumber < 0) {
                gamePanel.ui.commandNumber = 3;
            }
            cursorSound();
        } else if (keyCode == KeyEvent.VK_DOWN) {
            gamePanel.ui.commandNumber++;
            if (gamePanel.ui.commandNumber > 3) {
                gamePanel.ui.commandNumber = 0;
            }
            cursorSound();
        } else if (keyCode == KeyEvent.VK_ENTER) {
            // gamePanel.gameState = gamePanel.playState;
            gamePanel.gameState = gamePanel.inputNamaRuanganState;
            cursorSound();
        }
        // draw input box
        // if (gamePanel.ui.inputTextDone) {

        // }
    }

    public void inputKoordinatBendaState(int keyCode) {
        // DIALOG STATE
        // nyimpan tempBenda dari sim ke tempFurnitur
        Furnitur tempFurnitur = (Furnitur) gamePanel.listSim.get(gamePanel.indexCurrentSim).tempBenda;
        // input name
        if (gamePanel.ui.inputText.length() < 3) {
            if (keyCode == KeyEvent.VK_1) {
                gamePanel.ui.inputText += "1";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_2) {
                gamePanel.ui.inputText += "2";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_3) {
                gamePanel.ui.inputText += "3";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_4) {
                gamePanel.ui.inputText += "4";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_5) {
                gamePanel.ui.inputText += "5";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_6) {
                gamePanel.ui.inputText += "6";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_COMMA) {
                gamePanel.ui.inputText += ",";
                cursorSound();
            }
        }

        if (keyCode == KeyEvent.VK_BACK_SPACE && gamePanel.ui.inputText.length() > 0) {
            gamePanel.ui.inputText = gamePanel.ui.inputText.substring(0, gamePanel.ui.inputText.length() - 1);
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ENTER) {

            // check panjang input
            if (gamePanel.ui.inputText.length() > 0) {
                gamePanel.gameState = gamePanel.playState;

                String input = gamePanel.ui.inputText;
                int commaCounter = input.length() - input.replace(",", "").length();
                // check apakah input valid
                if (input.charAt(0) == ',' || input.charAt(input.length() - 1) == ',' || commaCounter != 1) {
                    gamePanel.ui.charIndex = 0;
                    gamePanel.ui.combinedText = "";
                    gamePanel.gameState = gamePanel.dialogState;
                    gamePanel.ui.currentDialog = "Koordinat tidak valid!";
                    gamePanel.listSim.get(gamePanel.indexCurrentSim).canObtainItem(tempFurnitur);
                    gamePanel.ui.commandNumber = 0;
                    gamePanel.ui.inputText = "";
                } else {
                    int x = Integer.parseInt(input.substring(0, input.indexOf(",")));
                    int y = Integer.parseInt(input.substring(input.indexOf(",") + 1));
                    // check apakah koordinat range 1-6
                    if (x < 1 || x > 6 || y < 1 || y > 6) {
                        gamePanel.ui.charIndex = 0;
                        gamePanel.ui.combinedText = "";
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.currentDialog = "Koordinat harus berada pada range 1-6!";
                        gamePanel.listSim.get(gamePanel.indexCurrentSim).canObtainItem(tempFurnitur);
                        gamePanel.ui.commandNumber = 0;
                        gamePanel.ui.inputText = "";
                    } else {
                        if (gamePanel.listSim
                                .get(gamePanel.indexCurrentSim).indexRumahYangDimasuki != gamePanel.indexCurrentSim) {
                            gamePanel.ui.charIndex = 0;
                            gamePanel.ui.combinedText = "";
                            gamePanel.gameState = gamePanel.dialogState;
                            gamePanel.ui.currentDialog = "Tidak dapat meletakkan furnitur di rumah\nsim lain!";
                            gamePanel.listSim.get(gamePanel.indexCurrentSim).canObtainItem(tempFurnitur);
                            gamePanel.ui.commandNumber = 0;
                            gamePanel.ui.inputText = "";
                        } else {
                            // check apakah dalam ruangan sudah terdapat furnitur yang sama
                            boolean isSame = false;
                            for (int i = 0; i < gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(
                                    gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan
                                    .size(); i++) {
                                if (gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah
                                        .get(gamePanel.listSim
                                                .get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan
                                        .get(i).name.equals(tempFurnitur.name)) {
                                    isSame = true;
                                }
                            }
                            if (isSame) {
                                gamePanel.ui.charIndex = 0;
                                gamePanel.ui.combinedText = "";
                                gamePanel.gameState = gamePanel.dialogState;
                                gamePanel.ui.currentDialog = "Sudah terdapat " + tempFurnitur.name + " di ruangan ini!";
                                gamePanel.listSim.get(gamePanel.indexCurrentSim).canObtainItem(tempFurnitur);
                                gamePanel.ui.commandNumber = 0;
                                gamePanel.ui.inputText = "";
                            } else {
                                // check nabrak dinding
                                int horizontalCollision = (x + 1) * gamePanel.tileSize + tempFurnitur.solidArea.width;
                                int verticalCollision = (y + 1) * gamePanel.tileSize + tempFurnitur.solidArea.height;
                                if (horizontalCollision >= 9 * gamePanel.tileSize
                                        || verticalCollision >= 9 * gamePanel.tileSize) {
                                    gamePanel.ui.charIndex = 0;
                                    gamePanel.ui.combinedText = "";
                                    gamePanel.gameState = gamePanel.dialogState;
                                    gamePanel.ui.currentDialog = "Furnitur menabrak dinding!";
                                    gamePanel.listSim.get(gamePanel.indexCurrentSim).canObtainItem(tempFurnitur);
                                    gamePanel.ui.commandNumber = 0;
                                    gamePanel.ui.inputText = "";
                                } else {
                                    // check intersect dengan furnitur lain
                                    tempFurnitur.solidArea.x = (x + 1) * gamePanel.tileSize;
                                    tempFurnitur.solidArea.y = (y + 1) * gamePanel.tileSize;
                                    boolean intersect = false;
                                    for (int i = 0; i < gamePanel.listSim
                                            .get(gamePanel.indexCurrentSim).rumah.ruanganRumah
                                            .get(gamePanel.listSim
                                                    .get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan
                                            .size(); i++) {
                                        if (tempFurnitur.solidArea.intersects(gamePanel.listSim
                                                .get(gamePanel.indexCurrentSim).rumah.ruanganRumah
                                                .get(gamePanel.listSim.get(
                                                        gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan
                                                .get(i).solidArea)) {
                                            intersect = true;
                                            break;
                                        }
                                    }
                                    if (intersect) {
                                        gamePanel.ui.charIndex = 0;
                                        gamePanel.ui.combinedText = "";
                                        gamePanel.gameState = gamePanel.dialogState;
                                        gamePanel.ui.currentDialog = "Furnitur tidak boleh bersebrangan!";
                                        gamePanel.listSim.get(gamePanel.indexCurrentSim).canObtainItem(tempFurnitur);
                                        gamePanel.ui.commandNumber = 0;
                                        gamePanel.ui.inputText = "";
                                    } else {
                                        gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah
                                                .get(gamePanel.listSim.get(
                                                        gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan
                                                .add(tempFurnitur);
                                        gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah
                                                .get(gamePanel.listSim.get(
                                                        gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan
                                                .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah
                                                        .get(gamePanel.listSim.get(
                                                                gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan
                                                        .size() - 1).worldX = (x + 1) * gamePanel.tileSize;
                                        gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah
                                                .get(gamePanel.listSim.get(
                                                        gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan
                                                .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah
                                                        .get(gamePanel.listSim.get(
                                                                gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan
                                                        .size() - 1).worldY = (y + 1) * gamePanel.tileSize;
                                    }
                                }
                            }
                        }

                        // ------------------------------------BATAS------------------------------------
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.add(tempFurnitur);
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.size()
                        // - 1).worldX = (x+1) * gamePanel.tileSize;
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.size()
                        // - 1).solidArea.x = (x+1) * gamePanel.tileSize;
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.size()
                        // - 1).worldY = (y+1) * gamePanel.tileSize;
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.size()
                        // - 1).solidArea.y = (y+1) * gamePanel.tileSize;
                        // int horizontalCollision =
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.size()
                        // - 1).worldX +
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.size()
                        // - 1).solidArea.width;
                        // int verticalCollision =
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.size()
                        // - 1).worldY +
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.size()
                        // - 1).solidArea.height;
                        // Benda bendaT =
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.size()
                        // - 1);
                        // if (horizontalCollision >= 9*gamePanel.tileSize || verticalCollision >=
                        // 9*gamePanel.tileSize){
                        // // check nabrak dinding
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.remove(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.size()
                        // - 1));
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).canObtainItem(bendaT); //
                        // INI JANGAN DIHAPUS
                        // gamePanel.gameState = gamePanel.dialogState;
                        // gamePanel.ui.currentDialog = "Tempat tidak cukup untuk
                        // meletakkan\nfurnitur!";
                        // } else {
                        // // check intersection dengan furnitur lain
                        // for (int i = 0; i <
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.size()
                        // - 1; i++){
                        // Benda bendaR =
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(i);
                        // // System.out.println(horizontalCollision);
                        // // System.out.println(verticalCollision);
                        // // System.out.println(bendaR.solidArea.x);

                        // if (bendaR instanceof Furnitur && bendaT instanceof Furnitur){
                        // if (bendaR.solidArea.intersects(bendaT.solidArea)){
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.remove(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.size()
                        // - 1));
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).canObtainItem(bendaT); //
                        // INI JANGAN DIHAPUS
                        // gamePanel.gameState = gamePanel.dialogState;
                        // gamePanel.ui.currentDialog = "Tempat tidak cukup untuk meletakkan\nfurnitur
                        // atau Anda sudah memiliki\nfurnitur ini di ruangan!\nBerhasil memindahkan
                        // furnitur.";
                        // }
                        // }
                        // }
                        // }
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).tempInt = -1;
                        // //
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(0).bendaRuangan[1].worldX
                        // = 2 *gamePanel.tileSize;
                        // //
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(0).bendaRuangan[1].worldY
                        // = 3 *gamePanel.tileSize;
                        // ------------------------------------BATAS------------------------------------
                    }
                }
                gamePanel.ui.inputText = "";
                gamePanel.ui.inputTextDone = false;
            } else {
                gamePanel.ui.charIndex = 0;
                gamePanel.ui.combinedText = "";
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.ui.currentDialog = "Nama tidak boleh kosong";
                gamePanel.listSim.get(gamePanel.indexCurrentSim).canObtainItem(tempFurnitur);
                gamePanel.ui.commandNumber = 0;
                gamePanel.ui.inputText = "";
            }
            cursorSound();

        }

        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.ui.inputText = "";
            gamePanel.ui.inputTextDone = false;
            gamePanel.ui.commandNumber = 0;
            gamePanel.gameState = gamePanel.inventoryState;
            gamePanel.listSim.get(gamePanel.indexCurrentSim).canObtainItem(tempFurnitur); // INI JANGAN DIHAPUS
        }
    }

    public void inputNamaRuanganState(int keyCode) {
        // input name
        if (gamePanel.ui.inputText.length() < 15) {
            if (keyCode == KeyEvent.VK_A) {
                gamePanel.ui.inputText += "A";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_B) {
                gamePanel.ui.inputText += "B";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_C) {
                gamePanel.ui.inputText += "C";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_D) {
                gamePanel.ui.inputText += "D";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_E) {
                gamePanel.ui.inputText += "E";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_F) {
                gamePanel.ui.inputText += "F";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_G) {
                gamePanel.ui.inputText += "G";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_H) {
                gamePanel.ui.inputText += "H";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_I) {
                gamePanel.ui.inputText += "I";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_J) {
                gamePanel.ui.inputText += "J";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_K) {
                gamePanel.ui.inputText += "K";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_L) {
                gamePanel.ui.inputText += "L";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_M) {
                gamePanel.ui.inputText += "M";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_N) {
                gamePanel.ui.inputText += "N";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_O) {
                gamePanel.ui.inputText += "O";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_P) {
                gamePanel.ui.inputText += "P";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_Q) {
                gamePanel.ui.inputText += "Q";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_R) {
                gamePanel.ui.inputText += "R";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_S) {
                gamePanel.ui.inputText += "S";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_T) {
                gamePanel.ui.inputText += "T";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_U) {
                gamePanel.ui.inputText += "U";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_V) {
                gamePanel.ui.inputText += "V";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_W) {
                gamePanel.ui.inputText += "W";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_X) {
                gamePanel.ui.inputText += "X";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_Y) {
                gamePanel.ui.inputText += "Y";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_Z) {
                gamePanel.ui.inputText += "Z";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_SPACE) {
                gamePanel.ui.inputText += " ";
                cursorSound();
            }
        }

        if (keyCode == KeyEvent.VK_BACK_SPACE && gamePanel.ui.inputText.length() > 0) {
            gamePanel.ui.inputText = gamePanel.ui.inputText.substring(0, gamePanel.ui.inputText.length() - 1);
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ENTER) {
            if (gamePanel.ui.inputText.length() > 0) {
                // nama tidak boleh sama
                boolean isRuanganNameExist = false;
                for (int i = 0; i < gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.size(); i++) {
                    if (UtilityTool
                            .capitalizeFirstLetter(
                                    gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(i).name)
                            .equals(UtilityTool.capitalizeFirstLetter(gamePanel.ui.inputText))) {
                        isRuanganNameExist = true;
                    }
                }
                if (isRuanganNameExist) {
                    gamePanel.ui.charIndex = 0;
                    gamePanel.ui.combinedText = "";
                    gamePanel.gameState = gamePanel.dialogState;
                    gamePanel.ui.currentDialog = "Nama ruangan di rumah sudah ada!";
                    gamePanel.ui.inputText = "";
                    gamePanel.ui.inputTextDone = false;
                    gamePanel.ui.commandNumber = 0;

                } else {
                    gamePanel.gameState = gamePanel.playState;
                    // System.out.println("TEST1");
                    String input = gamePanel.ui.inputText;
                    if (gamePanel.ui.commandNumber == 0) {
                        gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.upgradeRumah("up", input);
                        gamePanel.ui.commandNumber = 0;
                    } else if (gamePanel.ui.commandNumber == 1) {
                        // System.out.println("TEST2");
                        gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.upgradeRumah("down", input);
                        // System.out.println(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(0).down.name);
                        gamePanel.ui.commandNumber = 0;
                    } else if (gamePanel.ui.commandNumber == 2) {
                        gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.upgradeRumah("left", input);
                        gamePanel.ui.commandNumber = 0;
                    } else if (gamePanel.ui.commandNumber == 3) {
                        gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.upgradeRumah("right", input);
                        gamePanel.ui.commandNumber = 0;
                    }
                    gamePanel.ui.inputText = "";
                    gamePanel.ui.inputTextDone = false;
                    // CEK RUANGAN APA SAJA DALAM RUMAH SIM
                    // for (Ruangan s :
                    // gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah){
                    // System.out.println(s.name);
                    // }
                }
            } else {
                gamePanel.ui.charIndex = 0;
                gamePanel.ui.combinedText = "";
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.ui.currentDialog = "Nama tidak boleh kosong";
                gamePanel.ui.commandNumber = 0;
            }
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.ui.inputText = "";
            gamePanel.ui.inputTextDone = false;
            gamePanel.ui.commandNumber = 0;
            gamePanel.gameState = gamePanel.upgradeRumahState;
            cursorSound();
        }

    }

    public void inputKoordinatRumahSimState(int keyCode) {
        if (gamePanel.ui.inputText.length() < 5) {
            if (keyCode == KeyEvent.VK_1) {
                gamePanel.ui.inputText += "1";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_2) {
                gamePanel.ui.inputText += "2";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_3) {
                gamePanel.ui.inputText += "3";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_4) {
                gamePanel.ui.inputText += "4";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_5) {
                gamePanel.ui.inputText += "5";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_6) {
                gamePanel.ui.inputText += "6";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_7) {
                gamePanel.ui.inputText += "7";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_8) {
                gamePanel.ui.inputText += "8";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_9) {
                gamePanel.ui.inputText += "9";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_0) {
                gamePanel.ui.inputText += "0";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_COMMA) {
                gamePanel.ui.inputText += ",";
                cursorSound();
            }
        }

        if (keyCode == KeyEvent.VK_BACK_SPACE && gamePanel.ui.inputText.length() > 0) {
            gamePanel.ui.inputText = gamePanel.ui.inputText.substring(0, gamePanel.ui.inputText.length() - 1);
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ENTER) {

            // check panjang input
            if (gamePanel.ui.inputText.length() > 0) {
                gamePanel.gameState = gamePanel.playState;

                String input = gamePanel.ui.inputText;
                int commaCounter = input.length() - input.replace(",", "").length();
                // check apakah input valid
                if (input.charAt(0) == ',' || input.charAt(input.length() - 1) == ',' || commaCounter != 1) {
                    gamePanel.ui.charIndex = 0;
                    gamePanel.ui.combinedText = "";
                    gamePanel.gameState = gamePanel.dialogState;
                    gamePanel.ui.currentDialog = "Koordinat tidak valid!";
                    gamePanel.listSim.remove(gamePanel.listSim.size() - 1);
                    gamePanel.ui.commandNumber = 0;
                    gamePanel.ui.inputText = "";
                } else {
                    int x = Integer.parseInt(input.substring(0, input.indexOf(",")));
                    int y = Integer.parseInt(input.substring(input.indexOf(",") + 1));
                    // check apakah koordinat range 1-6
                    if (x < 1 || x > 64 || y < 1 || y > 64) {
                        gamePanel.ui.charIndex = 0;
                        gamePanel.ui.combinedText = "";
                        gamePanel.gameState = gamePanel.dialogState;
                        gamePanel.ui.currentDialog = "Koordinat harus berada pada range 1-64!";
                        gamePanel.listSim.remove(gamePanel.listSim.size() - 1);
                        gamePanel.ui.commandNumber = 0;
                        gamePanel.ui.inputText = "";
                    } else {
                        // check apakah koordinat sudah ada rumah
                        boolean isExist = false;
                        for (int i = 0; i < gamePanel.listRumah[0].size(); i++) {
                            if (gamePanel.listRumah[0].get(i).worldX == x * gamePanel.tileSize
                                    && gamePanel.listRumah[0].get(i).worldY == y * gamePanel.tileSize) {
                                isExist = true;
                                break;
                            }
                        }
                        if (isExist) {
                            gamePanel.ui.charIndex = 0;
                            gamePanel.ui.combinedText = "";
                            gamePanel.gameState = gamePanel.dialogState;
                            gamePanel.ui.currentDialog = "Koordinat sudah ditempati sim lain!";
                            gamePanel.listSim.remove(gamePanel.listSim.size() - 1);
                            gamePanel.ui.commandNumber = 0;
                            gamePanel.ui.inputText = "";
                        } else {
                            gamePanel.listSim.get(gamePanel.listSim.size() - 1).rumah.colRumah = x;
                            gamePanel.listSim.get(gamePanel.listSim.size() - 1).rumah.worldX = x * gamePanel.tileSize;
                            gamePanel.listSim.get(gamePanel.listSim.size() - 1).rumah.rowRumah = y;
                            gamePanel.listSim.get(gamePanel.listSim.size() - 1).rumah.worldY = y * gamePanel.tileSize;
                            gamePanel.listRumah[0].add(gamePanel.listSim.get(gamePanel.listSim.size() - 1).rumah);
                            // set sim to own rumah
                            gamePanel.listSim.get(gamePanel.listSim.size() - 1).currentMap = 1;
                            gamePanel.listSim.get(
                                    gamePanel.listSim.size() - 1).indexRumahYangDimasuki = gamePanel.listRumah[0].size()
                                            - 1;
                            gamePanel.listSim.get(gamePanel.listSim.size() - 1).indexLocationRuangan = 0;
                            gamePanel.listSim.get(gamePanel.listSim.size() - 1).currentLocation = "Rumah "
                                    + gamePanel.listSim.get(gamePanel.listSim.size() - 1).nama + " ("
                                    + UtilityTool.capitalizeFirstLetter(
                                            gamePanel.listSim.get(gamePanel.listSim.size() - 1).rumah.ruanganRumah
                                                    .get(0).name)
                                    + ")";
                            gamePanel.ui.charIndex = 0;
                            gamePanel.ui.combinedText = "";
                            gamePanel.gameState = gamePanel.dialogState;
                            gamePanel.ui.currentDialog = "Berhasil menambah sim.";
                            gamePanel.isOneSim = false;
                        }
                    }
                }
                gamePanel.ui.inputText = "";
                gamePanel.ui.inputTextDone = false;
            } else {
                gamePanel.ui.charIndex = 0;
                gamePanel.ui.combinedText = "";
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.ui.currentDialog = "Nama tidak boleh kosong";
                gamePanel.listSim.remove(gamePanel.listSim.size() - 1);
                gamePanel.ui.commandNumber = 0;
                gamePanel.ui.inputText = "";
            }
            cursorSound();

        }

        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.ui.inputText = "";
            gamePanel.ui.inputTextDone = false;
            gamePanel.ui.commandNumber = 0;
            gamePanel.gameState = gamePanel.addSimState;
            gamePanel.listSim.remove(gamePanel.listSim.size() - 1); // INI JANGAN DIHAPUS
            cursorSound();
        }
    }

    public void addSimState(int keyCode) {
        if (gamePanel.ui.inputText.length() < 15) {
            if (keyCode == KeyEvent.VK_A) {
                gamePanel.ui.inputText += "A";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_B) {
                gamePanel.ui.inputText += "B";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_C) {
                gamePanel.ui.inputText += "C";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_D) {
                gamePanel.ui.inputText += "D";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_E) {
                gamePanel.ui.inputText += "E";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_F) {
                gamePanel.ui.inputText += "F";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_G) {
                gamePanel.ui.inputText += "G";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_H) {
                gamePanel.ui.inputText += "H";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_I) {
                gamePanel.ui.inputText += "I";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_J) {
                gamePanel.ui.inputText += "J";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_K) {
                gamePanel.ui.inputText += "K";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_L) {
                gamePanel.ui.inputText += "L";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_M) {
                gamePanel.ui.inputText += "M";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_N) {
                gamePanel.ui.inputText += "N";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_O) {
                gamePanel.ui.inputText += "O";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_P) {
                gamePanel.ui.inputText += "P";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_Q) {
                gamePanel.ui.inputText += "Q";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_R) {
                gamePanel.ui.inputText += "R";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_S) {
                gamePanel.ui.inputText += "S";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_T) {
                gamePanel.ui.inputText += "T";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_U) {
                gamePanel.ui.inputText += "U";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_V) {
                gamePanel.ui.inputText += "V";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_W) {
                gamePanel.ui.inputText += "W";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_X) {
                gamePanel.ui.inputText += "X";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_Y) {
                gamePanel.ui.inputText += "Y";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_Z) {
                gamePanel.ui.inputText += "Z";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_SPACE) {
                gamePanel.ui.inputText += " ";
                cursorSound();
            }
        }

        if (keyCode == KeyEvent.VK_BACK_SPACE && gamePanel.ui.inputText.length() > 0) {
            gamePanel.ui.inputText = gamePanel.ui.inputText.substring(0, gamePanel.ui.inputText.length() - 1);
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ENTER) {
            if (gamePanel.ui.inputText.length() > 0) {
                // name can't be same with other sim
                gamePanel.gameState = gamePanel.playState;
                String input = UtilityTool.capitalizeFirstLetter(gamePanel.ui.inputText);
                boolean isNameExist = false;
                for (int i = 0; i < gamePanel.listSim.size(); i++) {
                    if (gamePanel.listSim.get(i).nama.equals(input)) {
                        isNameExist = true;
                    }
                }
                if (isNameExist) {
                    gamePanel.ui.charIndex = 0;
                    gamePanel.ui.combinedText = "";
                    gamePanel.gameState = gamePanel.dialogState;
                    gamePanel.ui.currentDialog = "Nama sudah dimiliki sim lain!";
                    gamePanel.ui.commandNumber = 0;
                    gamePanel.ui.inputText = "";
                    gamePanel.ui.inputTextDone = false;
                } else {
                    gamePanel.listSim.add(new Sim(gamePanel, gamePanel.keyHandler));
                    gamePanel.listSim.get(gamePanel.listSim.size() - 1).nama = input;
                    gamePanel.gameState = gamePanel.inputKoordinatRumahSimState;
                    gamePanel.ui.inputText = "";
                    gamePanel.ui.inputTextDone = false;
                }
            } else {
                gamePanel.ui.charIndex = 0;
                gamePanel.ui.combinedText = "";
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.ui.currentDialog = "Nama tidak boleh kosong";
                gamePanel.ui.commandNumber = 0;
            }
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.ui.inputText = "";
            gamePanel.ui.inputTextDone = false;
            gamePanel.ui.commandNumber = 0;
            gamePanel.gameState = gamePanel.playState;
            cursorSound();
        }
    }

    public void changeSimState(int keyCode) {
        int index = 0;
        if (keyCode == KeyEvent.VK_G) {
            gamePanel.gameState = gamePanel.playState;
            gamePanel.ui.listSimSlotCol = 0;
            gamePanel.ui.listSimSlotRow = 0;
            cursorSound();
        } else if (keyCode == KeyEvent.VK_UP) {
            if (gamePanel.ui.listSimSlotRow > 0) {
                index = UI.getItemIndexOnSlot(gamePanel.ui.listSimSlotRow - 1, gamePanel.ui.listSimSlotCol);
                if (index <= gamePanel.listSim.size() - 1) {
                    gamePanel.ui.listSimSlotRow--;
                    cursorSound();
                }
            } else {
                index = UI.getItemIndexOnSlot(2, gamePanel.ui.listSimSlotCol);
                if (index <= gamePanel.listSim.size() - 1) {
                    gamePanel.ui.listSimSlotRow = 2;
                }
            }
        } else if (keyCode == KeyEvent.VK_DOWN) {
            if (gamePanel.ui.listSimSlotRow < 2) {
                index = UI.getItemIndexOnSlot(gamePanel.ui.listSimSlotRow + 1, gamePanel.ui.listSimSlotCol);
                if (index <= gamePanel.listSim.size() - 1) {
                    gamePanel.ui.listSimSlotRow++;
                    cursorSound();
                }
            } else {
                index = UI.getItemIndexOnSlot(0, gamePanel.ui.listSimSlotCol);
                if (index <= gamePanel.listSim.size() - 1) {
                    gamePanel.ui.listSimSlotRow = 0;
                }
            }
        } else if (keyCode == KeyEvent.VK_LEFT) {
            if (gamePanel.ui.listSimSlotCol > 0) {
                index = UI.getItemIndexOnSlot(gamePanel.ui.listSimSlotRow, gamePanel.ui.listSimSlotCol - 1);
                if (index <= gamePanel.listSim.size() - 1) {
                    gamePanel.ui.listSimSlotCol--;
                    cursorSound();
                }
            } else {
                index = UI.getItemIndexOnSlot(gamePanel.ui.listSimSlotRow, 10);
                if (index <= gamePanel.listSim.size() - 1) {
                    gamePanel.ui.listSimSlotCol = 10;
                }
            }
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            if (gamePanel.ui.listSimSlotCol < 10) {
                index = UI.getItemIndexOnSlot(gamePanel.ui.listSimSlotRow, gamePanel.ui.listSimSlotCol + 1);
                if (index <= gamePanel.listSim.size() - 1) {
                    gamePanel.ui.listSimSlotCol++;
                    cursorSound();
                }
            } else {
                index = UI.getItemIndexOnSlot(gamePanel.ui.listSimSlotRow, 0);
                if (index <= gamePanel.listSim.size() - 1) {
                    gamePanel.ui.listSimSlotCol = 0;
                }
            }
        } else if (keyCode == KeyEvent.VK_ENTER) {
            int indexSim = UI.getItemIndexOnSlot(gamePanel.ui.listSimSlotRow, gamePanel.ui.listSimSlotCol);
            gamePanel.indexCurrentSim = indexSim;
            gamePanel.ui.charIndex = 0;
            gamePanel.ui.combinedText = "";
            gamePanel.gameState = gamePanel.dialogState;
            gamePanel.ui.currentDialog = "Sim telah diganti menjadi "
                    + gamePanel.listSim.get(gamePanel.indexCurrentSim).nama + "!";
            cursorSound();
            gamePanel.ui.listSimSlotCol = 0;
            gamePanel.ui.listSimSlotRow = 0;
        }
    }

    public void resepState(int keyCode) {
        int index = 0;

        if (keyCode == KeyEvent.VK_ENTER) {
            enterPressed = true;
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.gameState = gamePanel.playState;
            cursorSound();
        } else if (keyCode == KeyEvent.VK_UP) {
            if (gamePanel.ui.kokiSlotRow > 0) {
                index = UI.getItemIndexOnSlot(gamePanel.ui.kokiSlotRow - 1, gamePanel.ui.kokiSlotCol);
                if (index <= gamePanel.kokiTemp.inventory.size() - 1) {
                    gamePanel.ui.kokiSlotRow--;
                    cursorSound();
                }
            } else {
                index = UI.getItemIndexOnSlot(2, gamePanel.ui.kokiSlotCol);
                if (index <= gamePanel.kokiTemp.inventory.size() - 1) {
                    gamePanel.ui.kokiSlotRow = 2;
                }
            }
        } else if (keyCode == KeyEvent.VK_DOWN) {
            if (gamePanel.ui.kokiSlotRow < 2) {
                index = UI.getItemIndexOnSlot(gamePanel.ui.kokiSlotRow + 1, gamePanel.ui.kokiSlotCol);
                if (index <= gamePanel.kokiTemp.inventory.size() - 1) {
                    gamePanel.ui.kokiSlotRow++;
                    cursorSound();
                }
            } else {
                index = UI.getItemIndexOnSlot(0, gamePanel.ui.kokiSlotCol);
                if (index <= gamePanel.kokiTemp.inventory.size() - 1) {
                    gamePanel.ui.kokiSlotRow = 0;
                }
            }
        } else if (keyCode == KeyEvent.VK_LEFT) {
            if (gamePanel.ui.kokiSlotCol > 0) {
                index = UI.getItemIndexOnSlot(gamePanel.ui.kokiSlotRow, gamePanel.ui.kokiSlotCol - 1);
                if (index <= gamePanel.kokiTemp.inventory.size() - 1) {
                    gamePanel.ui.kokiSlotCol--;
                    cursorSound();
                }
            } else {
                index = UI.getItemIndexOnSlot(gamePanel.ui.kokiSlotRow, 10);
                if (index <= gamePanel.kokiTemp.inventory.size() - 1) {
                    gamePanel.ui.kokiSlotCol = 10;
                }
            }
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            if (gamePanel.ui.kokiSlotCol < 10) {
                index = UI.getItemIndexOnSlot(gamePanel.ui.kokiSlotRow, gamePanel.ui.kokiSlotCol + 1);
                if (index <= gamePanel.kokiTemp.inventory.size() - 1) {
                    gamePanel.ui.kokiSlotCol++;
                    cursorSound();
                }
            } else {
                index = UI.getItemIndexOnSlot(gamePanel.ui.kokiSlotRow, 0);
                if (index <= gamePanel.kokiTemp.inventory.size() - 1) {
                    gamePanel.ui.kokiSlotCol = 0;
                }
            }
        }
    }

    public void timerState(int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.gameState = gamePanel.playState;
            cursorSound();
            gamePanel.keyHandler.threadTemp.interrupt();
            gamePanel.ui.durasiTimer = 0;
        } 
    }
    
    public void gameOverState(int keyCode){
        if (gamePanel.isOneSim){
            if (keyCode == KeyEvent.VK_ENTER){
                gamePanel.ui.titleScreenState = 0;
                gamePanel.gameState = gamePanel.titleState;
                cursorSound();
                gamePanel.ui.commandNumber = 0;
            }
            if (keyCode == KeyEvent.VK_UP){
                gamePanel.ui.commandNumber--;
                if (gamePanel.ui.commandNumber < 0){
                    gamePanel.ui.commandNumber = 0;
                }
                cursorSound();
            } else if (keyCode == KeyEvent.VK_DOWN){
                gamePanel.ui.commandNumber++;
                if (gamePanel.ui.commandNumber > 0){
                    gamePanel.ui.commandNumber = 0;
                }
                cursorSound();
            }
        } else if (gamePanel.listSim.size() >= 1) {
            if (keyCode == KeyEvent.VK_UP){
                gamePanel.ui.commandNumber--;
                if (gamePanel.ui.commandNumber < 0){
                    gamePanel.ui.commandNumber = 1;
                }
                cursorSound();
            } else if (keyCode == KeyEvent.VK_DOWN){
                gamePanel.ui.commandNumber++;
                if (gamePanel.ui.commandNumber > 1){
                    gamePanel.ui.commandNumber = 0;
                }
                cursorSound();
            } else if (keyCode == KeyEvent.VK_ENTER){
                if (gamePanel.ui.commandNumber == 0){
                    gamePanel.gameState = gamePanel.playState;
                    gamePanel.gameState = gamePanel.changeSimState;
                } else {
                    gamePanel.gameState = gamePanel.titleState;
                }
                cursorSound();
                gamePanel.ui.commandNumber = 0;
            }
        }

        
    }

    // --------------------- TODO BATAS SUCI -------------------------
    public void inputDurasiTidurState(int keyCode){
        if (gamePanel.ui.inputText.length() < 5) {
            if (keyCode == KeyEvent.VK_1) {
                gamePanel.ui.inputText += "1";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_2) {
                gamePanel.ui.inputText += "2";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_3) {
                gamePanel.ui.inputText += "3";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_4) {
                gamePanel.ui.inputText += "4";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_5) {
                gamePanel.ui.inputText += "5";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_6) {
                gamePanel.ui.inputText += "6";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_7) {
                gamePanel.ui.inputText += "7";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_8) {
                gamePanel.ui.inputText += "8";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_9) {
                gamePanel.ui.inputText += "9";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_0) {
                gamePanel.ui.inputText += "0";
                cursorSound();
            }
        }

        if (keyCode == KeyEvent.VK_BACK_SPACE && gamePanel.ui.inputText.length() > 0) {
            gamePanel.ui.inputText = gamePanel.ui.inputText.substring(0, gamePanel.ui.inputText.length() - 1);
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.ui.inputText = "";
            gamePanel.ui.inputTextDone = false;
            gamePanel.ui.commandNumber = 0;
            gamePanel.gameState = gamePanel.playState;
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ENTER) {
            if (gamePanel.ui.inputText.length() > 0) {
                int durasi = Integer.parseInt(gamePanel.ui.inputText);
                gamePanel.gameState = gamePanel.playState;

                // timer state
                gamePanel.ui.durasiTimer = durasi;
                gamePanel.ui.currentAksi = "tidur";
                gamePanel.gameState = gamePanel.timerState;
                threadTemp = gamePanel.ui.startTimerThread(durasi);

                // efek
                gamePanel.getCurrentSim().mood += (durasi/240)*30;
                gamePanel.getCurrentSim().kesehatan += (durasi/240)*20;
                if (gamePanel.getCurrentSim().mood > gamePanel.getCurrentSim().maxMood) {
                    gamePanel.getCurrentSim().mood = gamePanel.getCurrentSim().maxMood;
                }
                if (gamePanel.getCurrentSim().kesehatan > gamePanel.getCurrentSim().maxKesehatan) {
                    gamePanel.getCurrentSim().kesehatan = gamePanel.getCurrentSim().maxKesehatan;
                }
                if (gamePanel.getCurrentSim().kekenyangan > gamePanel.getCurrentSim().maxKekenyangan) {
                    gamePanel.getCurrentSim().kekenyangan = gamePanel.getCurrentSim().maxKekenyangan;
                }

                // nambah WorldTimeCounter
                gamePanel.worldTimeCounter += durasi;

                gamePanel.ui.inputText = "";
                gamePanel.ui.commandNumber = 0;
            } else {
                gamePanel.ui.charIndex = 0;
                gamePanel.ui.combinedText = "";
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.ui.currentDialog = "Input durasi tidak boleh kosong!";
                gamePanel.ui.commandNumber = 0;
                gamePanel.ui.inputText = "";
            }
            cursorSound();
        }
    }

    public void inputDurasiNontonState(int keyCode){
        if (gamePanel.ui.inputText.length() < 5) {
            if (keyCode == KeyEvent.VK_1) {
                gamePanel.ui.inputText += "1";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_2) {
                gamePanel.ui.inputText += "2";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_3) {
                gamePanel.ui.inputText += "3";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_4) {
                gamePanel.ui.inputText += "4";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_5) {
                gamePanel.ui.inputText += "5";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_6) {
                gamePanel.ui.inputText += "6";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_7) {
                gamePanel.ui.inputText += "7";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_8) {
                gamePanel.ui.inputText += "8";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_9) {
                gamePanel.ui.inputText += "9";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_0) {
                gamePanel.ui.inputText += "0";
                cursorSound();
            }
        }

        if (keyCode == KeyEvent.VK_BACK_SPACE && gamePanel.ui.inputText.length() > 0) {
            gamePanel.ui.inputText = gamePanel.ui.inputText.substring(0, gamePanel.ui.inputText.length() - 1);
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.ui.inputText = "";
            gamePanel.ui.inputTextDone = false;
            gamePanel.ui.commandNumber = 0;
            gamePanel.gameState = gamePanel.playState;
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ENTER) {
            if (gamePanel.ui.inputText.length() > 0) {
                int durasi = Integer.parseInt(gamePanel.ui.inputText);
                gamePanel.gameState = gamePanel.playState;

                // timer state
                gamePanel.ui.durasiTimer = durasi;
                gamePanel.ui.currentAksi = "Nonton";
                gamePanel.gameState = gamePanel.timerState;
                threadTemp = gamePanel.ui.startTimerThread(durasi);

                // efek
                gamePanel.getCurrentSim().kekenyangan -= (durasi/60)*5;
                gamePanel.getCurrentSim().mood += (durasi/60)*15;
                gamePanel.getCurrentSim().kesehatan -= (durasi/60)*5;
                if (gamePanel.getCurrentSim().kekenyangan > 100) {
                    gamePanel.getCurrentSim().kekenyangan = 100;
                }
                if (gamePanel.getCurrentSim().mood > 100) {
                    gamePanel.getCurrentSim().mood = 100;
                }
                if (gamePanel.getCurrentSim().kesehatan > 100) {
                    gamePanel.getCurrentSim().kesehatan = 100;
                }

                // nambah WorldTimeCounter
                gamePanel.worldTimeCounter += durasi;

                gamePanel.ui.inputText = "";
                gamePanel.ui.commandNumber = 0;
            } else {
                gamePanel.ui.charIndex = 0;
                gamePanel.ui.combinedText = "";
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.ui.currentDialog = "Input durasi tidak boleh kosong!";
                gamePanel.ui.commandNumber = 0;
                gamePanel.ui.inputText = "";
            }
            cursorSound();
        }
    }

    public void inputDurasiMandiState(int keyCode){
        if (gamePanel.ui.inputText.length() < 5) {
            if (keyCode == KeyEvent.VK_1) {
                gamePanel.ui.inputText += "1";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_2) {
                gamePanel.ui.inputText += "2";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_3) {
                gamePanel.ui.inputText += "3";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_4) {
                gamePanel.ui.inputText += "4";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_5) {
                gamePanel.ui.inputText += "5";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_6) {
                gamePanel.ui.inputText += "6";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_7) {
                gamePanel.ui.inputText += "7";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_8) {
                gamePanel.ui.inputText += "8";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_9) {
                gamePanel.ui.inputText += "9";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_0) {
                gamePanel.ui.inputText += "0";
                cursorSound();
            }
        }

        if (keyCode == KeyEvent.VK_BACK_SPACE && gamePanel.ui.inputText.length() > 0) {
            gamePanel.ui.inputText = gamePanel.ui.inputText.substring(0, gamePanel.ui.inputText.length() - 1);
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.ui.inputText = "";
            gamePanel.ui.inputTextDone = false;
            gamePanel.ui.commandNumber = 0;
            gamePanel.gameState = gamePanel.playState;
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ENTER) {
            if (gamePanel.ui.inputText.length() > 0) {
                int durasi = Integer.parseInt(gamePanel.ui.inputText);
                gamePanel.gameState = gamePanel.playState;

                // timer state
                gamePanel.ui.durasiTimer = durasi;
                gamePanel.ui.currentAksi = "Mandi";
                gamePanel.gameState = gamePanel.timerState;
                threadTemp = gamePanel.ui.startTimerThread(durasi);

                // efek
                gamePanel.getCurrentSim().kekenyangan -= (durasi/30)*5;
                gamePanel.getCurrentSim().mood += (durasi/30)*10;
                gamePanel.getCurrentSim().kesehatan += (durasi/30)*10;
                if (gamePanel.getCurrentSim().kekenyangan > 100) {
                    gamePanel.getCurrentSim().kekenyangan = 100;
                }
                if (gamePanel.getCurrentSim().mood > 100) {
                    gamePanel.getCurrentSim().mood = 100;
                }
                if (gamePanel.getCurrentSim().kesehatan > 100) {
                    gamePanel.getCurrentSim().kesehatan = 100;
                }

                // nambah WorldTimeCounter
                gamePanel.worldTimeCounter += durasi;

                gamePanel.ui.inputText = "";
                gamePanel.ui.commandNumber = 0;
            } else {
                gamePanel.ui.charIndex = 0;
                gamePanel.ui.combinedText = "";
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.ui.currentDialog = "Input durasi tidak boleh kosong!";
                gamePanel.ui.commandNumber = 0;
                gamePanel.ui.inputText = "";
            }
            cursorSound();
        }
    }

    public void inputDurasiSalatState(int keyCode){
        if (gamePanel.ui.inputText.length() < 5) {
            if (keyCode == KeyEvent.VK_1) {
                gamePanel.ui.inputText += "1";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_2) {
                gamePanel.ui.inputText += "2";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_3) {
                gamePanel.ui.inputText += "3";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_4) {
                gamePanel.ui.inputText += "4";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_5) {
                gamePanel.ui.inputText += "5";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_6) {
                gamePanel.ui.inputText += "6";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_7) {
                gamePanel.ui.inputText += "7";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_8) {
                gamePanel.ui.inputText += "8";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_9) {
                gamePanel.ui.inputText += "9";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_0) {
                gamePanel.ui.inputText += "0";
                cursorSound();
            }
        }

        if (keyCode == KeyEvent.VK_BACK_SPACE && gamePanel.ui.inputText.length() > 0) {
            gamePanel.ui.inputText = gamePanel.ui.inputText.substring(0, gamePanel.ui.inputText.length() - 1);
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.ui.inputText = "";
            gamePanel.ui.inputTextDone = false;
            gamePanel.ui.commandNumber = 0;
            gamePanel.gameState = gamePanel.playState;
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ENTER) {
            if (gamePanel.ui.inputText.length() > 0) {
                int durasi = Integer.parseInt(gamePanel.ui.inputText);
                gamePanel.gameState = gamePanel.playState;

                // timer state
                gamePanel.ui.durasiTimer = durasi;
                gamePanel.ui.currentAksi = "Salat";
                gamePanel.gameState = gamePanel.timerState;
                threadTemp = gamePanel.ui.startTimerThread(durasi);

                // efek
                gamePanel.getCurrentSim().mood += (durasi/5)*5;
                gamePanel.getCurrentSim().kekenyangan -= (durasi/5)*5;
                if (gamePanel.getCurrentSim().mood > 100) {
                    gamePanel.getCurrentSim().mood = 100;
                }
                if (gamePanel.getCurrentSim().kekenyangan > 100) {
                    gamePanel.getCurrentSim().kekenyangan = 100;
                }

                // nambah WorldTimeCounter
                gamePanel.worldTimeCounter += durasi;

                gamePanel.ui.inputText = "";
                gamePanel.ui.commandNumber = 0;
            } else {
                gamePanel.ui.charIndex = 0;
                gamePanel.ui.combinedText = "";
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.ui.currentDialog = "Input durasi tidak boleh kosong!";
                gamePanel.ui.commandNumber = 0;
                gamePanel.ui.inputText = "";
            }
            cursorSound();
        }
    }

    public void inputDurasiBacaBukuState(int keyCode){
        if (gamePanel.ui.inputText.length() < 5) {
            if (keyCode == KeyEvent.VK_1) {
                gamePanel.ui.inputText += "1";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_2) {
                gamePanel.ui.inputText += "2";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_3) {
                gamePanel.ui.inputText += "3";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_4) {
                gamePanel.ui.inputText += "4";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_5) {
                gamePanel.ui.inputText += "5";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_6) {
                gamePanel.ui.inputText += "6";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_7) {
                gamePanel.ui.inputText += "7";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_8) {
                gamePanel.ui.inputText += "8";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_9) {
                gamePanel.ui.inputText += "9";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_0) {
                gamePanel.ui.inputText += "0";
                cursorSound();
            }
        }

        if (keyCode == KeyEvent.VK_BACK_SPACE && gamePanel.ui.inputText.length() > 0) {
            gamePanel.ui.inputText = gamePanel.ui.inputText.substring(0, gamePanel.ui.inputText.length() - 1);
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.ui.inputText = "";
            gamePanel.ui.inputTextDone = false;
            gamePanel.ui.commandNumber = 0;
            gamePanel.gameState = gamePanel.playState;
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ENTER) {
            if (gamePanel.ui.inputText.length() > 0) {
                int durasi = Integer.parseInt(gamePanel.ui.inputText);
                gamePanel.gameState = gamePanel.playState;

                // timer state
                gamePanel.ui.durasiTimer = durasi;
                gamePanel.ui.currentAksi = "Baca Buku";
                gamePanel.gameState = gamePanel.timerState;
                threadTemp = gamePanel.ui.startTimerThread(durasi);

                // efek
                gamePanel.getCurrentSim().mood += (durasi/10)*10;
                gamePanel.getCurrentSim().kekenyangan -= (durasi/10)*3;
                if (gamePanel.getCurrentSim().mood > 100) {
                    gamePanel.getCurrentSim().mood = 100;
                }
                if (gamePanel.getCurrentSim().kekenyangan > 100) {
                    gamePanel.getCurrentSim().kekenyangan = 100;
                }

                // nambah WorldTimeCounter
                gamePanel.worldTimeCounter += durasi;

                gamePanel.ui.inputText = "";
                gamePanel.ui.commandNumber = 0;
            } else {
                gamePanel.ui.charIndex = 0;
                gamePanel.ui.combinedText = "";
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.ui.currentDialog = "Input durasi tidak boleh kosong!";
                gamePanel.ui.commandNumber = 0;
                gamePanel.ui.inputText = "";
            }
            cursorSound();
        }
    }
    
    public void inputDurasiRadioState(int keyCode) {
        if (gamePanel.ui.inputText.length() < 5) {
            if (keyCode == KeyEvent.VK_1) {
                gamePanel.ui.inputText += "1";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_2) {
                gamePanel.ui.inputText += "2";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_3) {
                gamePanel.ui.inputText += "3";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_4) {
                gamePanel.ui.inputText += "4";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_5) {
                gamePanel.ui.inputText += "5";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_6) {
                gamePanel.ui.inputText += "6";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_7) {
                gamePanel.ui.inputText += "7";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_8) {
                gamePanel.ui.inputText += "8";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_9) {
                gamePanel.ui.inputText += "9";
                cursorSound();
            } else if (keyCode == KeyEvent.VK_0) {
                gamePanel.ui.inputText += "0";
                cursorSound();
            }
        }

        if (keyCode == KeyEvent.VK_BACK_SPACE && gamePanel.ui.inputText.length() > 0) {
            gamePanel.ui.inputText = gamePanel.ui.inputText.substring(0, gamePanel.ui.inputText.length() - 1);
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.ui.inputText = "";
            gamePanel.ui.inputTextDone = false;
            gamePanel.ui.commandNumber = 0;
            gamePanel.gameState = gamePanel.playState;
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ENTER) {
            if (gamePanel.ui.inputText.length() > 0) {
                int durasi = Integer.parseInt(gamePanel.ui.inputText);
                gamePanel.gameState = gamePanel.playState;

                // timer state
                gamePanel.ui.durasiTimer = durasi;
                gamePanel.ui.currentAksi = "Menyetel Radio";
                gamePanel.gameState = gamePanel.timerState;
                threadTemp = gamePanel.ui.startTimerThread(durasi);

                // efek
                gamePanel.getCurrentSim().mood += (durasi / 240) * 40;
                gamePanel.getCurrentSim().kesehatan += (durasi / 240) * 15;
                if (gamePanel.getCurrentSim().mood > 100) {
                    gamePanel.getCurrentSim().mood = 100;
                }
                if (gamePanel.getCurrentSim().kesehatan > 100) {
                    gamePanel.getCurrentSim().kesehatan = 100;
                }

                // nambah WorldTimeCounter
                gamePanel.worldTimeCounter += durasi;

                gamePanel.ui.inputText = "";
                gamePanel.ui.commandNumber = 0;
            } else {
                gamePanel.ui.charIndex = 0;
                gamePanel.ui.combinedText = "";
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.ui.currentDialog = "Input durasi tidak boleh kosong!";
                gamePanel.ui.commandNumber = 0;
                gamePanel.ui.inputText = "";
            }
            cursorSound();
        }
    }

    // --------------------- BATAS SUCI -------------------------

    public void cursorSound() {
        gamePanel.playSoundEffect(3);
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

// ------------------------------------------NYIMPAN------------------------------------------
// package main;

// import java.awt.event.KeyEvent;
// import java.awt.event.KeyListener;

// import benda.Benda;
// import benda.Furnitur;

// public class KeyHandler implements KeyListener {

// GamePanel gamePanel;
// public boolean upPressed, downPressed, leftPressed, rightPressed,
// enterPressed;

// boolean checkWorldTime = false;
// boolean checkCurrentLocation = false;
// int moveCounter = 1;

// public KeyHandler(GamePanel gamePanel) {
// this.gamePanel = gamePanel;
// }

// @Override
// public void keyTyped(KeyEvent e) {
// }

// @Override
// public void keyPressed(KeyEvent e) {
// int keyCode = e.getKeyCode();

// // TITLE STATE
// if (gamePanel.gameState == gamePanel.titleState) {
// if (gamePanel.ui.titleScreenState == 0) {
// if (keyCode == KeyEvent.VK_UP) {
// gamePanel.ui.commandNumber--;
// if (gamePanel.ui.commandNumber < 0) {
// gamePanel.ui.commandNumber = 2;
// }
// } else if (keyCode == KeyEvent.VK_DOWN) {
// gamePanel.ui.commandNumber++;
// if (gamePanel.ui.commandNumber > 2) {
// gamePanel.ui.commandNumber = 0;
// }
// } else if (keyCode == KeyEvent.VK_ENTER) {
// if (gamePanel.ui.commandNumber == 0) {
// gamePanel.ui.titleScreenState = 1; // go to input name screen
// } else if (gamePanel.ui.commandNumber == 1) {
// // later
// } else if (gamePanel.ui.commandNumber == 2) {
// System.exit(0);
// }
// }
// } else if (gamePanel.ui.titleScreenState == 1) {
// if (keyCode == KeyEvent.VK_UP) {
// gamePanel.ui.commandNumber--;
// if (gamePanel.ui.commandNumber < 0) {
// gamePanel.ui.commandNumber = 1;
// }
// } else if (keyCode == KeyEvent.VK_DOWN) {
// gamePanel.ui.commandNumber++;
// if (gamePanel.ui.commandNumber > 1) {
// gamePanel.ui.commandNumber = 0;
// }
// } else if (keyCode == KeyEvent.VK_BACK_SPACE &&
// gamePanel.ui.inputFirstSimName.length() > 0) {
// gamePanel.ui.inputFirstSimName = gamePanel.ui.inputFirstSimName.substring(0,
// gamePanel.ui.inputFirstSimName.length() - 1);
// } else if (keyCode == KeyEvent.VK_ENTER) {
// if (gamePanel.ui.commandNumber == 0) { // start game
// gamePanel.listSim.get(gamePanel.indexCurrentSim).nama =
// gamePanel.ui.inputFirstSimName;
// if (gamePanel.listSim.get(gamePanel.indexCurrentSim).nama.length() == 0) {
// gamePanel.listSim.get(gamePanel.indexCurrentSim).nama = "Sim";
// } else {
// // capitalize first letter
// String[] pecah =
// gamePanel.listSim.get(gamePanel.indexCurrentSim).nama.split(" ");
// StringBuilder sb = new StringBuilder();
// for (int i = 0; i < pecah.length; i++) {
// sb.append(pecah[i].substring(0, 1).toUpperCase());
// sb.append(pecah[i].substring(1).toLowerCase());
// sb.append(" ");
// }
// gamePanel.listSim.get(gamePanel.indexCurrentSim).nama = sb.toString().trim();
// }
// // set game state to play state
// gamePanel.gameState = gamePanel.playState;
// } else if (gamePanel.ui.commandNumber == 1) { // back to Title Screen 0
// gamePanel.ui.titleScreenState = 0;
// gamePanel.ui.commandNumber = 0;
// gamePanel.ui.inputFirstSimName = "";
// }
// }

// // input name
// if (gamePanel.ui.inputFirstSimName.length() < 25) {
// if (keyCode == KeyEvent.VK_A) {
// gamePanel.ui.inputFirstSimName += "A";
// } else if (keyCode == KeyEvent.VK_B) {
// gamePanel.ui.inputFirstSimName += "B";
// } else if (keyCode == KeyEvent.VK_C) {
// gamePanel.ui.inputFirstSimName += "C";
// } else if (keyCode == KeyEvent.VK_D) {
// gamePanel.ui.inputFirstSimName += "D";
// } else if (keyCode == KeyEvent.VK_E) {
// gamePanel.ui.inputFirstSimName += "E";
// } else if (keyCode == KeyEvent.VK_F) {
// gamePanel.ui.inputFirstSimName += "F";
// } else if (keyCode == KeyEvent.VK_G) {
// gamePanel.ui.inputFirstSimName += "G";
// } else if (keyCode == KeyEvent.VK_H) {
// gamePanel.ui.inputFirstSimName += "H";
// } else if (keyCode == KeyEvent.VK_I) {
// gamePanel.ui.inputFirstSimName += "I";
// } else if (keyCode == KeyEvent.VK_J) {
// gamePanel.ui.inputFirstSimName += "J";
// } else if (keyCode == KeyEvent.VK_K) {
// gamePanel.ui.inputFirstSimName += "K";
// } else if (keyCode == KeyEvent.VK_L) {
// gamePanel.ui.inputFirstSimName += "L";
// } else if (keyCode == KeyEvent.VK_M) {
// gamePanel.ui.inputFirstSimName += "M";
// } else if (keyCode == KeyEvent.VK_N) {
// gamePanel.ui.inputFirstSimName += "N";
// } else if (keyCode == KeyEvent.VK_O) {
// gamePanel.ui.inputFirstSimName += "O";
// } else if (keyCode == KeyEvent.VK_P) {
// gamePanel.ui.inputFirstSimName += "P";
// } else if (keyCode == KeyEvent.VK_Q) {
// gamePanel.ui.inputFirstSimName += "Q";
// } else if (keyCode == KeyEvent.VK_R) {
// gamePanel.ui.inputFirstSimName += "R";
// } else if (keyCode == KeyEvent.VK_S) {
// gamePanel.ui.inputFirstSimName += "S";
// } else if (keyCode == KeyEvent.VK_T) {
// gamePanel.ui.inputFirstSimName += "T";
// } else if (keyCode == KeyEvent.VK_U) {
// gamePanel.ui.inputFirstSimName += "U";
// } else if (keyCode == KeyEvent.VK_V) {
// gamePanel.ui.inputFirstSimName += "V";
// } else if (keyCode == KeyEvent.VK_W) {
// gamePanel.ui.inputFirstSimName += "W";
// } else if (keyCode == KeyEvent.VK_X) {
// gamePanel.ui.inputFirstSimName += "X";
// } else if (keyCode == KeyEvent.VK_Y) {
// gamePanel.ui.inputFirstSimName += "Y";
// } else if (keyCode == KeyEvent.VK_Z) {
// gamePanel.ui.inputFirstSimName += "Z";
// } else if (keyCode == KeyEvent.VK_SPACE) {
// gamePanel.ui.inputFirstSimName += " ";
// }
// }
// }
// }

// // PLAY STATE
// if (gamePanel.gameState == gamePanel.playState) {
// playState(keyCode);
// }

// // PAUSE STATE
// else if (gamePanel.gameState == gamePanel.pauseState) {
// pauseState(keyCode);
// }

// // SIM INFO STATE
// else if (gamePanel.gameState == gamePanel.currentSimInfoState) {
// simInfoState(keyCode);
// }

// // DIALOG STATE
// else if (gamePanel.gameState == gamePanel.dialogState) {
// dialogState(keyCode);
// }

// // INVENTORY STATE
// else if (gamePanel.gameState == gamePanel.inventoryState) {
// inventoryState(keyCode);
// }

// // BELI STATE
// else if (gamePanel.gameState == gamePanel.beliState) {
// beliState(keyCode);
// }

// // UPGRADE RUMAH STATE
// else if (gamePanel.gameState == gamePanel.upgradeRumahState) {
// upgradeRumahState(keyCode);
// }

// // INPUT NAMA RUANGAN STATE
// else if (gamePanel.gameState == gamePanel.inputNamaRuanganState) {
// inputNamaRuanganState(keyCode);
// }

// // INPUT KOORDINAT BENDA STATE
// else if (gamePanel.gameState == gamePanel.inputKoordinatBendaState) {
// inputKoordinatBendaState(keyCode);
// }

// }

// public void playState(int keyCode) {
// if (keyCode == KeyEvent.VK_UP) {
// upPressed = true;
// } else if (keyCode == KeyEvent.VK_DOWN) {
// downPressed = true;
// } else if (keyCode == KeyEvent.VK_LEFT) {
// leftPressed = true;
// } else if (keyCode == KeyEvent.VK_RIGHT) {
// rightPressed = true;
// } else if (keyCode == KeyEvent.VK_P) {
// gamePanel.gameState = gamePanel.pauseState;
// } else if (keyCode == KeyEvent.VK_C) {
// gamePanel.gameState = gamePanel.currentSimInfoState;
// } else if (keyCode == KeyEvent.VK_I) {
// gamePanel.gameState = gamePanel.inventoryState;
// } else if (keyCode == KeyEvent.VK_B) {
// gamePanel.npc[0][4].speak();
// } else if (keyCode == KeyEvent.VK_ENTER) {
// enterPressed = true;
// } else if (keyCode == KeyEvent.VK_T) {
// if (checkWorldTime == false) {
// checkWorldTime = true;
// } else {
// checkWorldTime = false;
// }
// } else if (keyCode == KeyEvent.VK_L) {
// if (checkCurrentLocation == false) {
// checkCurrentLocation = true;
// } else {
// checkCurrentLocation = false;
// }
// } else if (keyCode == KeyEvent.VK_U) {
// if (gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap == 0) {
// gamePanel.gameState = gamePanel.dialogState;
// gamePanel.ui.currentDialog = "Anda sedang tidak berada di rumah.\nTidak dapat
// melakukan upgrade rumah!";
// } else {
// gamePanel.gameState = gamePanel.upgradeRumahState;
// }

// }
// }

// public void pauseState(int keyCode) {
// if (keyCode == KeyEvent.VK_P) {
// gamePanel.gameState = gamePanel.playState;
// }
// }

// public void simInfoState(int keyCode) {
// if (keyCode == KeyEvent.VK_C) {
// gamePanel.gameState = gamePanel.playState;
// }
// }

// public void dialogState(int keyCode) {
// if (keyCode == KeyEvent.VK_ENTER) {
// gamePanel.gameState = gamePanel.playState;
// // change direction of player
// // if (gamePanel.listSim.get(gamePanel.indexCurrentSim).direction == "up") {
// // gamePanel.listSim.get(gamePanel.indexCurrentSim).direction = "down";
// // } else if (gamePanel.listSim.get(gamePanel.indexCurrentSim).direction ==
// "down") {
// // gamePanel.listSim.get(gamePanel.indexCurrentSim).direction = "up";
// // } else if (gamePanel.listSim.get(gamePanel.indexCurrentSim).direction ==
// "left") {
// // gamePanel.listSim.get(gamePanel.indexCurrentSim).direction = "right";
// // } else if (gamePanel.listSim.get(gamePanel.indexCurrentSim).direction ==
// "right") {
// // gamePanel.listSim.get(gamePanel.indexCurrentSim).direction = "left";
// // }
// }
// }

// public void inventoryState(int keyCode) {
// int index;
// if (keyCode == KeyEvent.VK_I) {
// gamePanel.gameState = gamePanel.playState;
// gamePanel.ui.simSlotRow = 0;
// gamePanel.ui.simSlotCol = 0;
// } else if (keyCode == KeyEvent.VK_UP) {
// if (gamePanel.ui.simSlotRow > 0) {
// index = UI.getItemIndexOnSlot(gamePanel.ui.simSlotRow - 1,
// gamePanel.ui.simSlotCol);
// if (index <=
// gamePanel.listSim.size() - 1) {
// gamePanel.ui.simSlotRow--;
// }
// } else {
// index = UI.getItemIndexOnSlot(2, gamePanel.ui.simSlotCol);
// if (index <=
// gamePanel.listSim.size() - 1) {
// gamePanel.ui.simSlotRow = 2;
// }
// }
// } else if (keyCode == KeyEvent.VK_DOWN) {
// if (gamePanel.ui.simSlotRow < 2) {
// index = UI.getItemIndexOnSlot(gamePanel.ui.simSlotRow + 1,
// gamePanel.ui.simSlotCol);
// if (index <=
// gamePanel.listSim.size() - 1) {
// gamePanel.ui.simSlotRow++;
// }
// } else {
// index = UI.getItemIndexOnSlot(0, gamePanel.ui.simSlotCol);
// if (index <=
// gamePanel.listSim.size() - 1) {
// gamePanel.ui.simSlotRow = 0;
// }
// }
// } else if (keyCode == KeyEvent.VK_LEFT) {
// if (gamePanel.ui.simSlotCol > 0) {
// index = UI.getItemIndexOnSlot(gamePanel.ui.simSlotRow,
// gamePanel.ui.simSlotCol - 1);
// if (index <=
// gamePanel.listSim.size() - 1) {
// gamePanel.ui.simSlotCol--;
// }
// } else {
// index = UI.getItemIndexOnSlot(gamePanel.ui.simSlotRow, 10);
// if (index <=
// gamePanel.listSim.size() - 1) {
// gamePanel.ui.simSlotCol = 10;
// }
// }
// } else if (keyCode == KeyEvent.VK_RIGHT) {
// if (gamePanel.ui.simSlotCol < 10) {
// index = UI.getItemIndexOnSlot(gamePanel.ui.simSlotRow,
// gamePanel.ui.simSlotCol + 1);
// if (index <=
// gamePanel.listSim.size() - 1) {
// gamePanel.ui.simSlotCol++;
// }
// } else {
// index = UI.getItemIndexOnSlot(gamePanel.ui.simSlotRow, 0);
// if (index <=
// gamePanel.listSim.size() - 1) {
// gamePanel.ui.simSlotCol = 0;
// }
// }
// } else if (keyCode == KeyEvent.VK_ENTER) {
// gamePanel.listSim.get(gamePanel.indexCurrentSim).selectItem();
// gamePanel.ui.simSlotRow = 0;
// gamePanel.ui.simSlotCol = 0;
// }
// }

// public void beliState(int keyCode) {
// if (keyCode == KeyEvent.VK_ENTER) {
// enterPressed = true;
// }
// if (gamePanel.ui.subState == 0) {
// if (keyCode == KeyEvent.VK_UP) {
// gamePanel.ui.commandNumber--;
// if (gamePanel.ui.commandNumber < 0) {
// gamePanel.ui.commandNumber = 1;
// }
// } else if (keyCode == KeyEvent.VK_DOWN) {
// gamePanel.ui.commandNumber++;
// if (gamePanel.ui.commandNumber > 1) {
// gamePanel.ui.commandNumber = 0;
// }
// }
// }
// if (gamePanel.ui.subState == 1) {
// beliInventory(keyCode);
// if (keyCode == KeyEvent.VK_ESCAPE) {
// gamePanel.ui.subState = 0;
// }
// }
// }

// public void beliInventory(int keyCode) {
// int index = 0;
// if (keyCode == KeyEvent.VK_B) {
// gamePanel.gameState = gamePanel.playState;
// gamePanel.ui.subState = 0;
// } else if (keyCode == KeyEvent.VK_UP) {
// if (gamePanel.ui.npcSlotRow > 0) {
// index = UI.getItemIndexOnSlot(gamePanel.ui.npcSlotRow - 1,
// gamePanel.ui.npcSlotCol);
// if (index <= gamePanel.npc[0][4].inventory.size() - 1) {
// gamePanel.ui.npcSlotRow--;
// }
// } else {
// index = UI.getItemIndexOnSlot(2, gamePanel.ui.npcSlotCol);
// if (index <= gamePanel.npc[0][4].inventory.size() - 1) {
// gamePanel.ui.npcSlotRow = 2;
// }
// }
// } else if (keyCode == KeyEvent.VK_DOWN) {
// if (gamePanel.ui.npcSlotRow < 2) {
// index = UI.getItemIndexOnSlot(gamePanel.ui.npcSlotRow + 1,
// gamePanel.ui.npcSlotCol);
// if (index <= gamePanel.npc[0][4].inventory.size() - 1) {
// gamePanel.ui.npcSlotRow++;
// }
// } else {
// index = UI.getItemIndexOnSlot(0, gamePanel.ui.npcSlotCol);
// if (index <= gamePanel.npc[0][4].inventory.size() - 1) {
// gamePanel.ui.npcSlotRow = 0;
// }
// }
// } else if (keyCode == KeyEvent.VK_LEFT) {
// if (gamePanel.ui.npcSlotCol > 0) {
// index = UI.getItemIndexOnSlot(gamePanel.ui.npcSlotRow,
// gamePanel.ui.npcSlotCol - 1);
// if (index <= gamePanel.npc[0][4].inventory.size() - 1) {
// gamePanel.ui.npcSlotCol--;
// }
// } else {
// index = UI.getItemIndexOnSlot(gamePanel.ui.npcSlotRow, 10);
// if (index <= gamePanel.npc[0][4].inventory.size() - 1) {
// gamePanel.ui.npcSlotCol = 10;
// }
// }
// } else if (keyCode == KeyEvent.VK_RIGHT) {
// if (gamePanel.ui.npcSlotCol < 10) {
// index = UI.getItemIndexOnSlot(gamePanel.ui.npcSlotRow,
// gamePanel.ui.npcSlotCol + 1);
// if (index <= gamePanel.npc[0][4].inventory.size() - 1) {
// gamePanel.ui.npcSlotCol++;
// }
// } else {
// index = UI.getItemIndexOnSlot(gamePanel.ui.npcSlotRow, 0);
// if (index <= gamePanel.npc[0][4].inventory.size() - 1) {
// gamePanel.ui.npcSlotCol = 0;
// }
// }
// }
// }

// public void upgradeRumahState(int keyCode) {
// if (keyCode == KeyEvent.VK_U) {
// gamePanel.gameState = gamePanel.playState;
// gamePanel.ui.commandNumber = 0;
// }

// if (keyCode == KeyEvent.VK_UP) {
// gamePanel.ui.commandNumber--;
// if (gamePanel.ui.commandNumber < 0) {
// gamePanel.ui.commandNumber = 3;
// }
// } else if (keyCode == KeyEvent.VK_DOWN) {
// gamePanel.ui.commandNumber++;
// if (gamePanel.ui.commandNumber > 3) {
// gamePanel.ui.commandNumber = 0;
// }
// } else if (keyCode == KeyEvent.VK_ENTER) {
// // gamePanel.gameState = gamePanel.playState;
// gamePanel.gameState = gamePanel.inputNamaRuanganState;
// }
// // draw input box
// // if (gamePanel.ui.inputTextDone) {

// // }
// }

// public void inputKoordinatBendaState(int keyCode) {
// Furnitur tempFurnitur = (Furnitur)
// gamePanel.listSim.get(gamePanel.indexCurrentSim).tempBenda;
// // input name
// if (gamePanel.ui.inputText.length() < 15) {
// if (keyCode == KeyEvent.VK_1) {
// gamePanel.ui.inputText += "1";
// } else if (keyCode == KeyEvent.VK_2) {
// gamePanel.ui.inputText += "2";
// } else if (keyCode == KeyEvent.VK_3) {
// gamePanel.ui.inputText += "3";
// } else if (keyCode == KeyEvent.VK_4) {
// gamePanel.ui.inputText += "4";
// } else if (keyCode == KeyEvent.VK_5) {
// gamePanel.ui.inputText += "5";
// } else if (keyCode == KeyEvent.VK_6) {
// gamePanel.ui.inputText += "6";
// } else if (keyCode == KeyEvent.VK_COMMA) {
// gamePanel.ui.inputText += ",";
// }
// }

// if (keyCode == KeyEvent.VK_BACK_SPACE && gamePanel.ui.inputText.length() > 0)
// {
// gamePanel.ui.inputText = gamePanel.ui.inputText.substring(0,
// gamePanel.ui.inputText.length() - 1);
// }

// if (keyCode == KeyEvent.VK_ENTER) {

// if (gamePanel.ui.inputText.length() > 0) {
// gamePanel.gameState = gamePanel.playState;

// String input = gamePanel.ui.inputText;
// int commaCounter = input.length() - input.replace(",", "").length();
// if (input.charAt(0) == ',' || input.charAt(input.length() - 1) == ',' ||
// commaCounter != 1) {
// gamePanel.gameState = gamePanel.dialogState;
// gamePanel.ui.currentDialog = "Koordinat tidak valid!";
// } else {
// int x = Integer.parseInt(input.substring(0, input.indexOf(",")));
// int y = Integer.parseInt(input.substring(input.indexOf(",") + 1));
// if (x < 1 || x > 6 || y < 1 || y > 6) {
// gamePanel.gameState = gamePanel.dialogState;
// gamePanel.ui.currentDialog = "Koordinat tidak valid!";
// } else {
// gamePanel.listSim.get(gamePanel.indexCurrentSim).tempBenda = null;
// gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.add(tempFurnitur);
// gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.size()
// - 1).worldX = (x+1) * gamePanel.tileSize;
// gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.size()
// - 1).solidArea.x = (x+1) * gamePanel.tileSize;
// gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.size()
// - 1).worldY = (y+1) * gamePanel.tileSize;
// gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.size()
// - 1).solidArea.y = (y+1) * gamePanel.tileSize;
// int horizontalCollision =
// gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.size()
// - 1).worldX +
// gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.size()
// - 1).solidArea.width;
// int verticalCollision =
// gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.size()
// - 1).worldY +
// gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.size()
// - 1).solidArea.height;
// Benda bendaT =
// gamePanel.listSim.get(gamePanel.indexCurrentSim)..ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.size()
// - 1);
// if (horizontalCollision >= 9*gamePanel.tileSize || verticalCollision >=
// 9*gamePanel.tileSize){
// // check nabrak dinding
// gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.remove(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.size()
// - 1));
// gamePanel.listSim.get(gamePanel.indexCurrentSim).canObtainItem(bendaT); //
// INI JANGAN DIHAPUS
// gamePanel.gameState = gamePanel.dialogState;
// gamePanel.ui.currentDialog = "Tempat tidak cukup untuk
// meletakkan\nfurnitur!";
// } else {
// // check intersection dengan furnitur lain
// for (int i = 0; i <
// gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.size()
// - 1; i++){
// Benda bendaR =
// gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(i);
// // System.out.println(horizontalCollision);
// // System.out.println(verticalCollision);
// // System.out.println(bendaR.solidArea.x);

// if (bendaR instanceof Furnitur && bendaT instanceof Furnitur){
// if (bendaR.solidArea.intersects(bendaT.solidArea)){
// gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.remove(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.size()
// - 1));
// gamePanel.listSim.get(gamePanel.indexCurrentSim).canObtainItem(bendaT); //
// INI JANGAN DIHAPUS
// gamePanel.gameState = gamePanel.dialogState;
// gamePanel.ui.currentDialog = "Tempat tidak cukup untuk meletakkan\nfurnitur
// atau Anda sudah memiliki\nfurnitur ini di ruangan!\nBerhasil memindahkan
// furnitur.";
// }
// }
// }
// }
// gamePanel.listSim.get(gamePanel.indexCurrentSim).tempInt = -1;
// //
// gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(0).bendaRuangan[1].worldX
// = 2 *gamePanel.tileSize;
// //
// gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(0).bendaRuangan[1].worldY
// = 3 *gamePanel.tileSize;
// }
// }
// gamePanel.ui.inputText = "";
// gamePanel.ui.inputTextDone = false;
// } else {
// gamePanel.gameState = gamePanel.dialogState;
// gamePanel.ui.currentDialog = "Nama tidak boleh kosong";
// gamePanel.listSim.get(gamePanel.indexCurrentSim).canObtainItem(tempFurnitur);
// gamePanel.ui.commandNumber = 0;
// }
// }

// if (keyCode == KeyEvent.VK_ESCAPE) {
// gamePanel.ui.inputText = "";
// gamePanel.ui.inputTextDone = false;
// gamePanel.ui.commandNumber = 0;
// gamePanel.gameState = gamePanel.inventoryState;
// gamePanel.listSim.get(gamePanel.indexCurrentSim).canObtainItem(tempFurnitur);
// // INI JANGAN DIHAPUS
// }
// }

// public void inputNamaRuanganState(int keyCode) {
// // input name
// if (gamePanel.ui.inputText.length() < 15) {
// if (keyCode == KeyEvent.VK_A) {
// gamePanel.ui.inputText += "A";
// } else if (keyCode == KeyEvent.VK_B) {
// gamePanel.ui.inputText += "B";
// } else if (keyCode == KeyEvent.VK_C) {
// gamePanel.ui.inputText += "C";
// } else if (keyCode == KeyEvent.VK_D) {
// gamePanel.ui.inputText += "D";
// } else if (keyCode == KeyEvent.VK_E) {
// gamePanel.ui.inputText += "E";
// } else if (keyCode == KeyEvent.VK_F) {
// gamePanel.ui.inputText += "F";
// } else if (keyCode == KeyEvent.VK_G) {
// gamePanel.ui.inputText += "G";
// } else if (keyCode == KeyEvent.VK_H) {
// gamePanel.ui.inputText += "H";
// } else if (keyCode == KeyEvent.VK_I) {
// gamePanel.ui.inputText += "I";
// } else if (keyCode == KeyEvent.VK_J) {
// gamePanel.ui.inputText += "J";
// } else if (keyCode == KeyEvent.VK_K) {
// gamePanel.ui.inputText += "K";
// } else if (keyCode == KeyEvent.VK_L) {
// gamePanel.ui.inputText += "L";
// } else if (keyCode == KeyEvent.VK_M) {
// gamePanel.ui.inputText += "M";
// } else if (keyCode == KeyEvent.VK_N) {
// gamePanel.ui.inputText += "N";
// } else if (keyCode == KeyEvent.VK_O) {
// gamePanel.ui.inputText += "O";
// } else if (keyCode == KeyEvent.VK_P) {
// gamePanel.ui.inputText += "P";
// } else if (keyCode == KeyEvent.VK_Q) {
// gamePanel.ui.inputText += "Q";
// } else if (keyCode == KeyEvent.VK_R) {
// gamePanel.ui.inputText += "R";
// } else if (keyCode == KeyEvent.VK_S) {
// gamePanel.ui.inputText += "S";
// } else if (keyCode == KeyEvent.VK_T) {
// gamePanel.ui.inputText += "T";
// } else if (keyCode == KeyEvent.VK_U) {
// gamePanel.ui.inputText += "U";
// } else if (keyCode == KeyEvent.VK_V) {
// gamePanel.ui.inputText += "V";
// } else if (keyCode == KeyEvent.VK_W) {
// gamePanel.ui.inputText += "W";
// } else if (keyCode == KeyEvent.VK_X) {
// gamePanel.ui.inputText += "X";
// } else if (keyCode == KeyEvent.VK_Y) {
// gamePanel.ui.inputText += "Y";
// } else if (keyCode == KeyEvent.VK_Z) {
// gamePanel.ui.inputText += "Z";
// } else if (keyCode == KeyEvent.VK_SPACE) {
// gamePanel.ui.inputText += " ";
// }
// }

// if (keyCode == KeyEvent.VK_BACK_SPACE && gamePanel.ui.inputText.length() > 0)
// {
// gamePanel.ui.inputText = gamePanel.ui.inputText.substring(0,
// gamePanel.ui.inputText.length() - 1);
// }

// if (keyCode == KeyEvent.VK_ENTER) {
// if (gamePanel.ui.inputText.length() > 0) {
// gamePanel.gameState = gamePanel.playState;
// // System.out.println("TEST1");
// String input = gamePanel.ui.inputText;
// if (gamePanel.ui.commandNumber == 0) {
// gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.upgradeRumah("up",
// input);
// gamePanel.ui.commandNumber = 0;
// } else if (gamePanel.ui.commandNumber == 1) {
// // System.out.println("TEST2");
// gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.upgradeRumah("down",
// input);
// //
// System.out.println(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(0).down.name);
// gamePanel.ui.commandNumber = 0;
// } else if (gamePanel.ui.commandNumber == 2) {
// gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.upgradeRumah("left",
// input);
// gamePanel.ui.commandNumber = 0;
// } else if (gamePanel.ui.commandNumber == 3) {
// gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.upgradeRumah("right",
// input);
// gamePanel.ui.commandNumber = 0;
// }
// gamePanel.ui.inputText = "";
// gamePanel.ui.inputTextDone = false;
// // CEK RUANGAN APA SAJA DALAM RUMAH SIM
// // for (Ruangan s :
// gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah){
// // System.out.println(s.name);
// // }
// } else {
// gamePanel.gameState = gamePanel.dialogState;
// gamePanel.ui.currentDialog = "Nama tidak boleh kosong";
// gamePanel.ui.commandNumber = 0;
// }
// }

// if (keyCode == KeyEvent.VK_ESCAPE) {
// gamePanel.ui.inputText = "";
// gamePanel.ui.inputTextDone = false;
// gamePanel.ui.commandNumber = 0;
// gamePanel.gameState = gamePanel.upgradeRumahState;
// }

// }

// @Override
// public void keyReleased(KeyEvent e) {
// int keyCode = e.getKeyCode();

// if (keyCode == KeyEvent.VK_UP) {
// upPressed = false;
// }
// if (keyCode == KeyEvent.VK_DOWN) {
// downPressed = false;
// }
// if (keyCode == KeyEvent.VK_LEFT) {
// leftPressed = false;
// }
// if (keyCode == KeyEvent.VK_RIGHT) {
// rightPressed = false;
// }
// }
// }
