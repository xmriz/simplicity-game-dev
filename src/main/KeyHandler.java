package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import benda.Furnitur;
import entity.Sim;

public class KeyHandler implements KeyListener {

    GamePanel gamePanel;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;

    public Thread threadTemp;
    boolean isFirst = true;

    boolean checkWorldTime = false;
    boolean checkCurrentLocation = false;

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
                gamePanel.indexCurrentSim = 0;
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
                        gamePanel.ui.inputFirstSimName = "";
                    } else if (gamePanel.ui.commandNumber == 1) {
                        gamePanel.ui.titleScreenState = 2;
                        gamePanel.ui.commandNumber = 0;
                        // gamePanel.saveLoad.load();
                        // gamePanel.gameState = gamePanel.playState;
                        // gamePanel.stopMusic();
                        // gamePanel.playMusic(1);
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
                        Sim sim1 = new Sim(gamePanel, gamePanel.keyHandler);
                        System.out.println(gamePanel.listSim.size());
                        int tempSize = gamePanel.listSim.size();
                        for (int i = 1; i < tempSize; i++) {
                            System.out.println(gamePanel.listSim.size());
                            System.out.println(200);
                            gamePanel.listSim.remove(gamePanel.listSim.size() - 1);
                            System.out.println(2033);
                            gamePanel.listRumah[0].remove(gamePanel.listSim.size() - 1);
                        }
                        gamePanel.listSim.set(0, sim1);
                        gamePanel.listRumah[0].set(0, gamePanel.listSim.get(0).rumah);
                        gamePanel.listRumah[0].get(0).setWorldX(gamePanel.listSim.get(0).rumah.getColRumah()
                                * gamePanel.tileSize);
                        gamePanel.listRumah[0].get(0).setWorldY(gamePanel.listSim.get(0).rumah.getRowRumah()
                                * gamePanel.tileSize);

                        gamePanel.indexCurrentSim = 0;
                        gamePanel.worldTimeCounter = 0;
                        gamePanel.isOneSim = true;
                        gamePanel.worldTimeSatuHariCounter = 0;

                        gamePanel.listSim.get(0).nama = gamePanel.ui.inputFirstSimName;
                        if (gamePanel.listSim.get(0).nama.length() == 0) {
                            gamePanel.listSim.get(0).nama = "Sim";
                        } else {
                            // capitalize first letter
                            gamePanel.listSim.get(0).nama = UtilityTool
                                    .capitalizeFirstLetter(gamePanel.listSim.get(0).nama);
                            // set currentlocation
                        }
                        gamePanel.listSim.get(0).currentLocation = "Rumah "
                                + gamePanel.listSim.get(0).nama + " ("
                                + UtilityTool.capitalizeFirstLetter(
                                        gamePanel.listSim.get(0).rumah.getRuanganRumah().get(0).getName())
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
            } else if (gamePanel.ui.titleScreenState == 2) {
                if (keyCode == KeyEvent.VK_UP) {
                    gamePanel.ui.commandNumber--;
                    if (gamePanel.ui.commandNumber < 0) {
                        gamePanel.ui.commandNumber = 1;
                    }
                    cursorSound();
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    gamePanel.ui.commandNumber++;
                    if (gamePanel.ui.commandNumber > 3) {
                        gamePanel.ui.commandNumber = 0;
                    }
                    cursorSound();
                } else if (keyCode == KeyEvent.VK_ENTER) {
                    if (gamePanel.ui.commandNumber == 0) {
                        gamePanel.saveLoad.load("save1");
                        gamePanel.gameState = gamePanel.playState;
                        gamePanel.stopMusic();
                        gamePanel.playMusic(1);
                    } else if (gamePanel.ui.commandNumber == 1) {
                        gamePanel.saveLoad.load("save2");
                        gamePanel.gameState = gamePanel.playState;
                        gamePanel.stopMusic();
                        gamePanel.playMusic(1);
                    } else if (gamePanel.ui.commandNumber == 2) {
                        gamePanel.saveLoad.load("save3");
                        gamePanel.gameState = gamePanel.playState;
                        gamePanel.stopMusic();
                        gamePanel.playMusic(1);
                    } else if (gamePanel.ui.commandNumber == 3) {
                        gamePanel.ui.titleScreenState = 0;
                    }
                    gamePanel.ui.commandNumber = 0;
                    keyCode = KeyEvent.VK_0;
                } else if (keyCode == KeyEvent.VK_ESCAPE) {
                    gamePanel.ui.titleScreenState = 0;
                    gamePanel.ui.commandNumber = 0;
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

        // SHALAT STATE
        else if (gamePanel.gameState == gamePanel.inputDurasiShalatState) {
            inputDurasiShalatState(keyCode);
        }

        // BACA BUKU STATE
        else if (gamePanel.gameState == gamePanel.inputDurasiBacaBukuState) {
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
            if (isFirst == true) {
                keyCode = KeyEvent.VK_0;
            }
            gameOverState(keyCode);
            isFirst = false;
        }

        // INPUT DURASI SIRAM TANAMAN STATE
        else if (gamePanel.gameState == gamePanel.inputDurasiSiramTanamanState) {
            inputDurasiSiramTanamanState(keyCode);
        }

        // INPUT DURASI MAIN GAME STATE
        else if (gamePanel.gameState == gamePanel.inputDurasiMainGameState) {
            inputDurasiMainGameState(keyCode);
        }

        // KERJA STATE
        else if (gamePanel.gameState == gamePanel.kerjaState) {
            kerjaState(keyCode);
        }

        // INPUT DURASI KERJA STATE
        else if (gamePanel.gameState == gamePanel.inputDurasiKerjaState) {
            inputDurasiKerjaState(keyCode);
        }

        // GANTI PEKERJAAN STATE
        else if (gamePanel.gameState == gamePanel.gantiPekerjaanState) {
            gantiPekerjaanState(keyCode);
        }

        // OLAHRAGA STATE
        else if (gamePanel.gameState == gamePanel.inputDurasiOlahragaState) {
            inputDurasiOlahragaState(keyCode);
        }
        // SAVE SCREEN
        else if (gamePanel.gameState == gamePanel.saveState) {
            saveState(keyCode);
        }

        // MELIHAT WAKTU
        else if (gamePanel.gameState == gamePanel.melihatWaktuState) {
            melihatWaktuState(keyCode);
        }

        // CUTSCENE
        else if (gamePanel.gameState == gamePanel.cutsceneState) {
            cutsceneState(keyCode);
        }
    }

    public void cutsceneState(int keyCode) {
        if (keyCode == KeyEvent.VK_ENTER) {
            gamePanel.gameState = gamePanel.menuState;
            gamePanel.cutsceneManager.scenePhase=0;
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
            checkWorldTime = false;
            checkCurrentLocation = false;
        } else if (keyCode == KeyEvent.VK_C) {
            gamePanel.gameState = gamePanel.simInfoState;
            checkWorldTime = false;
            checkCurrentLocation = false;
        } else if (keyCode == KeyEvent.VK_I) {
            gamePanel.gameState = gamePanel.inventoryState;
            checkWorldTime = false;
            checkCurrentLocation = false;
        } else if (keyCode == KeyEvent.VK_B) {
            gamePanel.ui.charIndex = 0;
            gamePanel.ui.combinedText = "";
            gamePanel.npc[0][4].speak();
            checkWorldTime = false;
            checkCurrentLocation = false;
        } else if (keyCode == KeyEvent.VK_ENTER) {
            enterPressed = true;
        } else if (keyCode == KeyEvent.VK_T) {
            if (checkWorldTime == false) {
                checkWorldTime = true;
                checkCurrentLocation = false;
            } else {
                checkWorldTime = false;
            }
        } else if (keyCode == KeyEvent.VK_L) {
            if (checkCurrentLocation == false) {
                checkCurrentLocation = true;
                checkWorldTime = false;
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
            checkWorldTime = false;
            checkCurrentLocation = false;
        } else if (keyCode == KeyEvent.VK_N) {
            gamePanel.gameState = gamePanel.addSimState;
            checkWorldTime = false;
            checkCurrentLocation = false;
        } else if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.gameState = gamePanel.menuState;
            checkWorldTime = false;
            checkCurrentLocation = false;
        } else if (keyCode == KeyEvent.VK_G) {
            gamePanel.gameState = gamePanel.changeSimState;
            checkWorldTime = false;
            checkCurrentLocation = false;
        } else if (keyCode == KeyEvent.VK_M) {
            if (gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap == 0) {
                gamePanel.gameState = gamePanel.mapState;
            } else {
                gamePanel.ui.charIndex = 0;
                gamePanel.ui.combinedText = "";
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.ui.currentDialog = "Tidak dapat membuka peta di rumah!";
            }
            checkWorldTime = false;
            checkCurrentLocation = false;
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
        } else if (keyCode == KeyEvent.VK_K) {
            gamePanel.gameState = gamePanel.kerjaState;
            checkWorldTime = false;
            checkCurrentLocation = false;
        } else if (keyCode == KeyEvent.VK_O) {
            gamePanel.gameState = gamePanel.inputDurasiOlahragaState;
            checkWorldTime = false;
            checkCurrentLocation = false;
        }
    }

    public void pauseState(int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.gameState = gamePanel.playState;
        }
    }

    public void simInfoState(int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.gameState = gamePanel.playState;
        }
    }

    public void dialogState(int keyCode) {
        if (keyCode == KeyEvent.VK_ENTER) {
            // gamePanel.stopMusic();
            // gamePanel.playMusic(1);
            gamePanel.getCurrentSim().update();
            if (gamePanel.getCurrentSim().isBarangSampai == true) {
                gamePanel.ui.charIndex = 0;
                gamePanel.ui.combinedText = "";
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.ui.currentDialog = gamePanel.getCurrentSim().tempDialogBarang;
                gamePanel.getCurrentSim().isBarangSampai = false;
                gamePanel.getCurrentSim().tempDialogBarang = "";
            } else if (gamePanel.getCurrentSim().isUpgradeDone == true) {
                gamePanel.ui.charIndex = 0;
                gamePanel.ui.combinedText = "";
                gamePanel.gameState = gamePanel.dialogState;
                gamePanel.ui.currentDialog = gamePanel.getCurrentSim().tempDialogUpgrade;
                gamePanel.getCurrentSim().isUpgradeDone = false;
                gamePanel.getCurrentSim().tempDialogUpgrade = "";
            } else if (gamePanel.getCurrentSim().isMati == true) {
                gamePanel.gameState = gamePanel.gameOverState;
            } else {
                gamePanel.gameState = gamePanel.playState;
            }
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
        if (keyCode == KeyEvent.VK_ESCAPE) {
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
                inputDurasiTidurState(keyCode);
            } else if (keyCode == KeyEvent.VK_UP) {
                gamePanel.ui.commandNumber = 6;
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
        } else if (gamePanel.ui.commandNumber == 6) {
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
            if (keyCode == KeyEvent.VK_ENTER) {
                gamePanel.gameState = gamePanel.saveState;
                gamePanel.ui.commandNumber = 0;
                cursorSound();
            }
        } else if (gamePanel.ui.commandNumber == 5) {
            if (keyCode == KeyEvent.VK_UP) {
                gamePanel.ui.commandNumber--;
                cursorSound();
            } else if (keyCode == KeyEvent.VK_DOWN) {
                gamePanel.ui.commandNumber++;
                cursorSound();
            } else if (keyCode == KeyEvent.VK_ENTER) {
                gamePanel.gameState = gamePanel.cutsceneState;
                gamePanel.cutsceneManager.sceneNum = gamePanel.cutsceneManager.ending;
                gamePanel.ui.commandNumber = 0;
                cursorSound();
            }
        }
    }

    public void saveState(int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.gameState = gamePanel.menuState;
            gamePanel.ui.commandNumber = 0;
        }
        if (gamePanel.ui.commandNumber == 0) {
            if (keyCode == KeyEvent.VK_DOWN) {
                gamePanel.ui.commandNumber++;
                cursorSound();
                inputDurasiTidurState(keyCode);
            } else if (keyCode == KeyEvent.VK_UP) {
                gamePanel.ui.commandNumber = 3;
                cursorSound();
            }
            if (keyCode == KeyEvent.VK_ENTER) {
                gamePanel.ui.commandNumber = 0;
                gamePanel.gameState = gamePanel.playState;
                gamePanel.saveLoad.save("save1");
            }
        } else if (gamePanel.ui.commandNumber == 1) {
            if (keyCode == KeyEvent.VK_UP) {
                gamePanel.ui.commandNumber--;
                cursorSound();
            } else if (keyCode == KeyEvent.VK_DOWN) {
                gamePanel.ui.commandNumber++;
                cursorSound();
            }
            if (keyCode == KeyEvent.VK_ENTER) {
                gamePanel.ui.commandNumber = 0;
                gamePanel.gameState = gamePanel.playState;
                gamePanel.saveLoad.save("save2");
            }
        } else if (gamePanel.ui.commandNumber == 2) {
            if (keyCode == KeyEvent.VK_UP) {
                gamePanel.ui.commandNumber--;
                cursorSound();
            } else if (keyCode == KeyEvent.VK_DOWN) {
                gamePanel.ui.commandNumber++;
                cursorSound();
            }
            if (keyCode == KeyEvent.VK_ENTER) {
                gamePanel.ui.commandNumber = 0;
                gamePanel.gameState = gamePanel.playState;
                gamePanel.saveLoad.save("save3");
            }
        } else if (gamePanel.ui.commandNumber == 3) {
            if (keyCode == KeyEvent.VK_UP) {
                gamePanel.ui.commandNumber--;
                cursorSound();
            } else if (keyCode == KeyEvent.VK_DOWN) {
                gamePanel.ui.commandNumber = 0;
                cursorSound();
            }
            if (keyCode == KeyEvent.VK_ENTER) {
                gamePanel.gameState = gamePanel.menuState;
                gamePanel.ui.commandNumber = 0;
            }
        }
    }

    public void helpState(int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.gameState = gamePanel.menuState;
        }
    }

    public void mapState(int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.gameState = gamePanel.playState;
        }
    }

    public void beliState(int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.gameState = gamePanel.playState;
            gamePanel.ui.subState = 0;
            gamePanel.ui.commandNumber = 0;
            cursorSound();
        }
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
                gamePanel.ui.npcSlotCol = 0;
                gamePanel.ui.npcSlotRow = 0;
            }
        }
    }

    public void beliInventory(int keyCode) {
        int index = 0;
        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.gameState = gamePanel.playState;
            gamePanel.ui.subState = 0;
            gamePanel.ui.npcSlotCol = 0;
            gamePanel.ui.npcSlotRow = 0;
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
        if (keyCode == KeyEvent.VK_ESCAPE) {
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
                            for (int i = 0; i < gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.getRuanganRumah().get(
                                    gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).getBendaRuangan()
                                    .size(); i++) {
                                if (gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.getRuanganRumah()
                                        .get(gamePanel.listSim
                                                .get(gamePanel.indexCurrentSim).indexLocationRuangan).getBendaRuangan()
                                        .get(i).getName().equals(tempFurnitur.getName())) {
                                    isSame = true;
                                }
                            }
                            if (isSame) {
                                gamePanel.ui.charIndex = 0;
                                gamePanel.ui.combinedText = "";
                                gamePanel.gameState = gamePanel.dialogState;
                                gamePanel.ui.currentDialog = "Sudah terdapat " + tempFurnitur.getName() + " di ruangan ini!";
                                gamePanel.listSim.get(gamePanel.indexCurrentSim).canObtainItem(tempFurnitur);
                                gamePanel.ui.commandNumber = 0;
                                gamePanel.ui.inputText = "";
                            } else {
                                // check nabrak dinding
                                int horizontalCollision = (x + 1) * gamePanel.tileSize + tempFurnitur.getSolidArea().width;
                                int verticalCollision = (y + 1) * gamePanel.tileSize + tempFurnitur.getSolidArea().height;
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
                                    tempFurnitur.getSolidArea().x = (x + 1) * gamePanel.tileSize;
                                    tempFurnitur.getSolidArea().y = (y + 1) * gamePanel.tileSize;
                                    boolean intersect = false;
                                    for (int i = 0; i < gamePanel.listSim
                                            .get(gamePanel.indexCurrentSim).rumah.getRuanganRumah()
                                            .get(gamePanel.listSim
                                                    .get(gamePanel.indexCurrentSim).indexLocationRuangan).getBendaRuangan()
                                            .size(); i++) {
                                        if (tempFurnitur.getSolidArea().intersects(gamePanel.listSim
                                                .get(gamePanel.indexCurrentSim).rumah.getRuanganRumah()
                                                .get(gamePanel.listSim.get(
                                                        gamePanel.indexCurrentSim).indexLocationRuangan).getBendaRuangan()
                                                .get(i).getSolidArea())) {
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
                                        gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.getRuanganRumah()
                                                .get(gamePanel.listSim.get(
                                                        gamePanel.indexCurrentSim).indexLocationRuangan).getBendaRuangan()
                                                .add(tempFurnitur);
                                        gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.getRuanganRumah()
                                                .get(gamePanel.listSim.get(
                                                        gamePanel.indexCurrentSim).indexLocationRuangan).getBendaRuangan()
                                                .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.getRuanganRumah()
                                                        .get(gamePanel.listSim.get(
                                                                gamePanel.indexCurrentSim).indexLocationRuangan).getBendaRuangan()
                                                        .size() - 1).setWorldX((x + 1) * gamePanel.tileSize);
                                        gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.getRuanganRumah()
                                                .get(gamePanel.listSim.get(
                                                        gamePanel.indexCurrentSim).indexLocationRuangan).getBendaRuangan()
                                                .get(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.getRuanganRumah()
                                                        .get(gamePanel.listSim.get(
                                                                gamePanel.indexCurrentSim).indexLocationRuangan).getBendaRuangan()
                                                        .size() - 1).setWorldY((y + 1) * gamePanel.tileSize);
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
                for (int i = 0; i < gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.getRuanganRumah().size(); i++) {
                    if (UtilityTool
                            .capitalizeFirstLetter(
                                    gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.getRuanganRumah().get(i).getName())
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
                            if (gamePanel.listRumah[0].get(i).getWorldX() == x * gamePanel.tileSize
                                    && gamePanel.listRumah[0].get(i).getWorldY() == y * gamePanel.tileSize) {
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
                            gamePanel.listSim.get(gamePanel.listSim.size() - 1).rumah.setColRumah(x);
                            gamePanel.listSim.get(gamePanel.listSim.size() - 1).rumah.setWorldX(x * gamePanel.tileSize);
                            gamePanel.listSim.get(gamePanel.listSim.size() - 1).rumah.setRowRumah(y);
                            gamePanel.listSim.get(gamePanel.listSim.size() - 1).rumah.setWorldY(y * gamePanel.tileSize);
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
                                            gamePanel.listSim.get(gamePanel.listSim.size() - 1).rumah.getRuanganRumah()
                                                    .get(0).getName())
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
        if (keyCode == KeyEvent.VK_ESCAPE) {
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

    public void gameOverState(int keyCode) {
        if (gamePanel.isOneSim) {
            if (keyCode == KeyEvent.VK_ENTER) {
                gamePanel.ui.titleScreenState = 0;
                gamePanel.gameState = gamePanel.titleState;
                cursorSound();
                gamePanel.ui.commandNumber = 0;
                isFirst = true;
            }
            if (keyCode == KeyEvent.VK_UP) {
                gamePanel.ui.commandNumber--;
                if (gamePanel.ui.commandNumber < 0) {
                    gamePanel.ui.commandNumber = 0;
                }
                cursorSound();
            } else if (keyCode == KeyEvent.VK_DOWN) {
                gamePanel.ui.commandNumber++;
                if (gamePanel.ui.commandNumber > 0) {
                    gamePanel.ui.commandNumber = 0;
                }
                cursorSound();
            }
        } else if (gamePanel.listSim.size() >= 1) {
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
            } else if (keyCode == KeyEvent.VK_ENTER) {
                if (gamePanel.ui.commandNumber == 0) {
                    // gamePanel.gameState = gamePanel.playState;
                    if (gamePanel.listSim.size() > 1) {
                        gamePanel.listSim.remove(gamePanel.indexCurrentSim);
                        gamePanel.listRumah[0].remove(gamePanel.indexCurrentSim);
                        for (int i = 0; i < gamePanel.listSim.size(); i++) {
                            if (gamePanel.listSim.get(i).indexRumahYangDimasuki != 999) {
                                if (gamePanel.listSim.get(i).indexRumahYangDimasuki > gamePanel.indexCurrentSim) {
                                    gamePanel.listSim.get(i).indexRumahYangDimasuki--;
                                } else if (gamePanel.listSim
                                        .get(i).indexRumahYangDimasuki == gamePanel.indexCurrentSim) {
                                    gamePanel.listSim.get(i).indexRumahYangDimasuki = i;
                                    gamePanel.listSim.get(i).setDefaultValues();
                                }
                            }
                        }
                        gamePanel.indexCurrentSim = 0;
                        for (int i = 0; i < gamePanel.listSim.size(); i++) {
                            if (gamePanel.listSim.get(i).rumah.getIsCanUpgrade() == false) {
                                gamePanel.listSim.get(i).rumah.setIsLockUpgrade(false);
                                gamePanel.listSim.get(i).rumah.decRemainingTimeUpgrade(gamePanel.ui.tempDurasi);
                                gamePanel.listSim.get(i).rumah.setIsCanUpgradeToTrueAfter18Minutes();
                                gamePanel.listSim.get(i).rumah.setIsLockUpgrade(true);
                            }
                        }
                        for (int i = 0; i < gamePanel.listSim.size(); i++) {
                            if (gamePanel.listSim.get(i).isCanBuy == false) {
                                gamePanel.listSim.get(i).isLockBuy = false;
                                gamePanel.listSim.get(i).remainingTimeBuy -= gamePanel.ui.tempDurasi;
                                gamePanel.listSim.get(i).setIsCanBuyToTrue();
                                gamePanel.listSim.get(i).rumah.setIsLockUpgrade(true);
                            }
                        }

                        // for (int i = gamePanel.indexCurrentSim; i < gamePanel.listSim.size(); i++){
                        // if (gamePanel.listSim.get(i).indexRumahYangDimasuki != 999){
                        // gamePanel.listSim.get(i).indexRumahYangDimasuki--;
                        // }
                        // }
                    }
                    gamePanel.gameState = gamePanel.changeSimState;
                } else {
                    gamePanel.ui.titleScreenState = 0;
                    gamePanel.gameState = gamePanel.titleState;
                }
                cursorSound();
                gamePanel.ui.commandNumber = 0;
                isFirst = true;
            }
        }

    }

    // --------------------- TODO BATAS SUCI -------------------------
    public void inputDurasiTidurState(int keyCode) {
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
                gamePanel.ui.tempDurasi = durasi;

                // timer state
                gamePanel.ui.durasiTimer = durasi;
                gamePanel.ui.currentAksi = "Tidur";
                gamePanel.gameState = gamePanel.timerState;
                gamePanel.ui.currentAksiDone = false;
                // efek
                gamePanel.getCurrentSim().mood += (durasi / 240) * 30;
                gamePanel.getCurrentSim().kesehatan += (durasi / 240) * 20;
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
                gamePanel.worldTimeSatuHariCounter += durasi;
                for (int i = 0; i < gamePanel.listSim.size(); i++) {
                    gamePanel.listSim.get(i).pekerjaan.worldTimeCounterForStartJobAfterChangeJob += durasi;
                    gamePanel.listSim.get(i).efekWaktuTidakTidurCounter += durasi;
                    if (gamePanel.listSim.get(i).isUdahMakanDalamSatuHari) {
                        gamePanel.listSim.get(i).efekWaktuTidakBuangAirCounter += durasi;
                    }
                }
                // gamePanel.ui.setelahAksi(durasi);

                // reset efek waktu tidak tidur
                gamePanel.getCurrentSim().efekWaktuTidakTidurCounter = 0;

                threadTemp = gamePanel.ui.startTimerThread(durasi);

                // // efek
                // gamePanel.getCurrentSim().mood += (durasi / 240) * 30;
                // gamePanel.getCurrentSim().kesehatan += (durasi / 240) * 20;
                // if (gamePanel.getCurrentSim().mood > gamePanel.getCurrentSim().maxMood) {
                // gamePanel.getCurrentSim().mood = gamePanel.getCurrentSim().maxMood;
                // }
                // if (gamePanel.getCurrentSim().kesehatan >
                // gamePanel.getCurrentSim().maxKesehatan) {
                // gamePanel.getCurrentSim().kesehatan = gamePanel.getCurrentSim().maxKesehatan;
                // }
                // if (gamePanel.getCurrentSim().kekenyangan >
                // gamePanel.getCurrentSim().maxKekenyangan) {
                // gamePanel.getCurrentSim().kekenyangan =
                // gamePanel.getCurrentSim().maxKekenyangan;
                // }

                // // nambah WorldTimeCounter
                // gamePanel.worldTimeCounter += durasi;
                // gamePanel.worldTimeSatuHariCounter += durasi;
                // for (int i = 0; i < gamePanel.listSim.size(); i++) {
                // gamePanel.listSim.get(i).pekerjaan.worldTimeCounterForStartJobAfterChangeJob
                // += durasi;
                // }
                // gamePanel.ui.setelahAksi(durasi);

                // // reset efek waktu tidak tidur
                // gamePanel.getCurrentSim().efekWaktuTidakTidurCounter = 0;

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

    public void inputDurasiNontonState(int keyCode) {
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
                gamePanel.ui.tempDurasi = durasi;

                // timer state
                gamePanel.ui.durasiTimer = durasi;
                gamePanel.ui.currentAksi = "Nonton";
                gamePanel.gameState = gamePanel.timerState;
                gamePanel.ui.currentAksiDone = false;
                // efek
                gamePanel.getCurrentSim().kekenyangan -= (durasi / 60) * 5;
                gamePanel.getCurrentSim().mood += (durasi / 60) * 15;
                gamePanel.getCurrentSim().kesehatan -= (durasi / 60) * 5;
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
                gamePanel.worldTimeSatuHariCounter += durasi;
                for (int i = 0; i < gamePanel.listSim.size(); i++) {
                    gamePanel.listSim.get(i).pekerjaan.worldTimeCounterForStartJobAfterChangeJob += durasi;
                    gamePanel.listSim.get(i).efekWaktuTidakTidurCounter += durasi;
                    if (gamePanel.listSim.get(i).isUdahMakanDalamSatuHari) {
                        gamePanel.listSim.get(i).efekWaktuTidakBuangAirCounter += durasi;
                    }
                }
                threadTemp = gamePanel.ui.startTimerThread(durasi);

                // // efek
                // gamePanel.getCurrentSim().kekenyangan -= (durasi / 60) * 5;
                // gamePanel.getCurrentSim().mood += (durasi / 60) * 15;
                // gamePanel.getCurrentSim().kesehatan -= (durasi / 60) * 5;
                // if (gamePanel.getCurrentSim().kekenyangan > 100) {
                // gamePanel.getCurrentSim().kekenyangan = 100;
                // }
                // if (gamePanel.getCurrentSim().mood > 100) {
                // gamePanel.getCurrentSim().mood = 100;
                // }
                // if (gamePanel.getCurrentSim().kesehatan > 100) {
                // gamePanel.getCurrentSim().kesehatan = 100;
                // }

                // // nambah WorldTimeCounter
                // gamePanel.worldTimeCounter += durasi;
                // gamePanel.worldTimeSatuHariCounter += durasi;
                // for (int i = 0; i < gamePanel.listSim.size(); i++) {
                // gamePanel.listSim.get(i).pekerjaan.worldTimeCounterForStartJobAfterChangeJob
                // += durasi;
                // }
                // gamePanel.ui.setelahAksi(durasi);

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

    public void inputDurasiMandiState(int keyCode) {
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
                gamePanel.ui.tempDurasi = durasi;

                // timer state
                gamePanel.ui.durasiTimer = durasi;
                gamePanel.ui.currentAksi = "Mandi";
                gamePanel.gameState = gamePanel.timerState;
                gamePanel.ui.currentAksiDone = false;
                // efek
                gamePanel.getCurrentSim().kekenyangan -= (durasi / 30) * 5;
                gamePanel.getCurrentSim().mood += (durasi / 30) * 10;
                gamePanel.getCurrentSim().kesehatan += (durasi / 30) * 10;
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
                gamePanel.worldTimeSatuHariCounter += durasi;
                for (int i = 0; i < gamePanel.listSim.size(); i++) {
                    gamePanel.listSim.get(i).pekerjaan.worldTimeCounterForStartJobAfterChangeJob += durasi;
                    gamePanel.listSim.get(i).efekWaktuTidakTidurCounter += durasi;
                    if (gamePanel.listSim.get(i).isUdahMakanDalamSatuHari) {
                        gamePanel.listSim.get(i).efekWaktuTidakBuangAirCounter += durasi;
                    }
                }
                threadTemp = gamePanel.ui.startTimerThread(durasi);

                // // efek
                // gamePanel.getCurrentSim().kekenyangan -= (durasi / 30) * 5;
                // gamePanel.getCurrentSim().mood += (durasi / 30) * 10;
                // gamePanel.getCurrentSim().kesehatan += (durasi / 30) * 10;
                // if (gamePanel.getCurrentSim().kekenyangan > 100) {
                // gamePanel.getCurrentSim().kekenyangan = 100;
                // }
                // if (gamePanel.getCurrentSim().mood > 100) {
                // gamePanel.getCurrentSim().mood = 100;
                // }
                // if (gamePanel.getCurrentSim().kesehatan > 100) {
                // gamePanel.getCurrentSim().kesehatan = 100;
                // }

                // // nambah WorldTimeCounter
                // gamePanel.worldTimeCounter += durasi;
                // gamePanel.worldTimeSatuHariCounter += durasi;
                // for (int i = 0; i < gamePanel.listSim.size(); i++) {
                // gamePanel.listSim.get(i).pekerjaan.worldTimeCounterForStartJobAfterChangeJob
                // += durasi;
                // }
                // gamePanel.ui.setelahAksi(durasi);

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

    public void inputDurasiShalatState(int keyCode) {
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
                gamePanel.ui.tempDurasi = durasi;

                // timer state
                gamePanel.ui.durasiTimer = durasi;
                gamePanel.ui.currentAksi = "Shalat";
                gamePanel.gameState = gamePanel.timerState;
                gamePanel.ui.currentAksiDone = false;
                // efek
                gamePanel.getCurrentSim().mood += (durasi / 5) * 5;
                gamePanel.getCurrentSim().kekenyangan -= (durasi / 5) * 5;
                if (gamePanel.getCurrentSim().mood > 100) {
                    gamePanel.getCurrentSim().mood = 100;
                }
                if (gamePanel.getCurrentSim().kekenyangan > 100) {
                    gamePanel.getCurrentSim().kekenyangan = 100;
                }

                // nambah WorldTimeCounter
                gamePanel.worldTimeCounter += durasi;
                gamePanel.worldTimeSatuHariCounter += durasi;
                for (int i = 0; i < gamePanel.listSim.size(); i++) {
                    gamePanel.listSim.get(i).pekerjaan.worldTimeCounterForStartJobAfterChangeJob += durasi;
                    gamePanel.listSim.get(i).efekWaktuTidakTidurCounter += durasi;
                    if (gamePanel.listSim.get(i).isUdahMakanDalamSatuHari) {
                        gamePanel.listSim.get(i).efekWaktuTidakBuangAirCounter += durasi;
                    }
                }
                threadTemp = gamePanel.ui.startTimerThread(durasi);

                // // efek
                // gamePanel.getCurrentSim().mood += (durasi / 5) * 5;
                // gamePanel.getCurrentSim().kekenyangan -= (durasi / 5) * 5;
                // if (gamePanel.getCurrentSim().mood > 100) {
                // gamePanel.getCurrentSim().mood = 100;
                // }
                // if (gamePanel.getCurrentSim().kekenyangan > 100) {
                // gamePanel.getCurrentSim().kekenyangan = 100;
                // }

                // // nambah WorldTimeCounter
                // gamePanel.worldTimeCounter += durasi;
                // gamePanel.worldTimeSatuHariCounter += durasi;
                // for (int i = 0; i < gamePanel.listSim.size(); i++) {
                // gamePanel.listSim.get(i).pekerjaan.worldTimeCounterForStartJobAfterChangeJob
                // += durasi;
                // }
                // gamePanel.ui.setelahAksi(durasi);

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

    public void inputDurasiBacaBukuState(int keyCode) {
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
                gamePanel.ui.tempDurasi = durasi;

                // timer state
                gamePanel.ui.durasiTimer = durasi;
                gamePanel.ui.currentAksi = "Baca Buku";
                gamePanel.gameState = gamePanel.timerState;
                gamePanel.ui.currentAksiDone = false;
                // efek
                gamePanel.getCurrentSim().mood += (durasi / 10) * 10;
                gamePanel.getCurrentSim().kekenyangan -= (durasi / 10) * 3;
                if (gamePanel.getCurrentSim().mood > 100) {
                    gamePanel.getCurrentSim().mood = 100;
                }
                if (gamePanel.getCurrentSim().kekenyangan > 100) {
                    gamePanel.getCurrentSim().kekenyangan = 100;
                }

                // nambah WorldTimeCounter
                gamePanel.worldTimeCounter += durasi;
                gamePanel.worldTimeSatuHariCounter += durasi;
                for (int i = 0; i < gamePanel.listSim.size(); i++) {
                    gamePanel.listSim.get(i).pekerjaan.worldTimeCounterForStartJobAfterChangeJob += durasi;
                    gamePanel.listSim.get(i).efekWaktuTidakTidurCounter += durasi;
                    if (gamePanel.listSim.get(i).isUdahMakanDalamSatuHari) {
                        gamePanel.listSim.get(i).efekWaktuTidakBuangAirCounter += durasi;
                    }
                }
                threadTemp = gamePanel.ui.startTimerThread(durasi);

                // // efek
                // gamePanel.getCurrentSim().mood += (durasi / 10) * 10;
                // gamePanel.getCurrentSim().kekenyangan -= (durasi / 10) * 3;
                // if (gamePanel.getCurrentSim().mood > 100) {
                // gamePanel.getCurrentSim().mood = 100;
                // }
                // if (gamePanel.getCurrentSim().kekenyangan > 100) {
                // gamePanel.getCurrentSim().kekenyangan = 100;
                // }

                // // nambah WorldTimeCounter
                // gamePanel.worldTimeCounter += durasi;
                // gamePanel.worldTimeSatuHariCounter += durasi;
                // for (int i = 0; i < gamePanel.listSim.size(); i++) {
                // gamePanel.listSim.get(i).pekerjaan.worldTimeCounterForStartJobAfterChangeJob
                // += durasi;
                // }
                // gamePanel.ui.setelahAksi(durasi);

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
                gamePanel.ui.tempDurasi = durasi;

                // timer state
                gamePanel.ui.durasiTimer = durasi;
                gamePanel.ui.currentAksi = "Setel Radio";
                gamePanel.gameState = gamePanel.timerState;
                gamePanel.ui.currentAksiDone = false;
                gamePanel.getCurrentSim().mood += (durasi / 10) * 10;
                gamePanel.getCurrentSim().kekenyangan -= (durasi / 30) * 1;
                if (gamePanel.getCurrentSim().mood > 100) {
                    gamePanel.getCurrentSim().mood = 100;
                }
                if (gamePanel.getCurrentSim().kekenyangan > 100) {
                    gamePanel.getCurrentSim().kekenyangan = 100;
                }
                gamePanel.worldTimeCounter += durasi;
                gamePanel.worldTimeSatuHariCounter += durasi;
                for (int i = 0; i < gamePanel.listSim.size(); i++) {
                    gamePanel.listSim.get(i).pekerjaan.worldTimeCounterForStartJobAfterChangeJob += durasi;
                    gamePanel.listSim.get(i).efekWaktuTidakTidurCounter += durasi;
                    if (gamePanel.listSim.get(i).isUdahMakanDalamSatuHari) {
                        gamePanel.listSim.get(i).efekWaktuTidakBuangAirCounter += durasi;
                    }
                }
                threadTemp = gamePanel.ui.startTimerThread(durasi);

                // efek
                // gamePanel.getCurrentSim().mood += (durasi / 10) * 10;
                // gamePanel.getCurrentSim().kekenyangan -= (durasi / 30) * 1;
                // if (gamePanel.getCurrentSim().mood > 100) {
                // gamePanel.getCurrentSim().mood = 100;
                // }
                // if (gamePanel.getCurrentSim().kekenyangan > 100) {
                // gamePanel.getCurrentSim().kekenyangan = 100;
                // }

                // nambah WorldTimeCounter
                // gamePanel.worldTimeCounter += durasi;
                // gamePanel.worldTimeSatuHariCounter += durasi;
                // for (int i = 0; i < gamePanel.listSim.size(); i++) {
                // gamePanel.listSim.get(i).pekerjaan.worldTimeCounterForStartJobAfterChangeJob
                // += durasi;
                // }
                // gamePanel.ui.setelahAksi(durasi);

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

    public void inputDurasiSiramTanamanState(int keyCode) {
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
                gamePanel.ui.tempDurasi = durasi;

                // timer state
                gamePanel.ui.durasiTimer = durasi;
                gamePanel.ui.currentAksi = "Siram Tanaman";
                gamePanel.gameState = gamePanel.timerState;
                gamePanel.ui.currentAksiDone = false;
                // efek
                gamePanel.getCurrentSim().mood += (durasi / 10) * 10;
                gamePanel.getCurrentSim().kesehatan += (durasi / 10) * 5;
                gamePanel.getCurrentSim().kekenyangan -= (durasi / 10) * 5;
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
                gamePanel.worldTimeSatuHariCounter += durasi;
                for (int i = 0; i < gamePanel.listSim.size(); i++) {
                    gamePanel.listSim.get(i).pekerjaan.worldTimeCounterForStartJobAfterChangeJob += durasi;
                    gamePanel.listSim.get(i).efekWaktuTidakTidurCounter += durasi;
                    if (gamePanel.listSim.get(i).isUdahMakanDalamSatuHari) {
                        gamePanel.listSim.get(i).efekWaktuTidakBuangAirCounter += durasi;
                    }
                }
                threadTemp = gamePanel.ui.startTimerThread(durasi);

                // // efek
                // gamePanel.getCurrentSim().mood += (durasi / 10) * 10;
                // gamePanel.getCurrentSim().kesehatan += (durasi / 10) * 5;
                // gamePanel.getCurrentSim().kekenyangan -= (durasi / 10) * 5;
                // if (gamePanel.getCurrentSim().mood > gamePanel.getCurrentSim().maxMood) {
                // gamePanel.getCurrentSim().mood = gamePanel.getCurrentSim().maxMood;
                // }
                // if (gamePanel.getCurrentSim().kesehatan >
                // gamePanel.getCurrentSim().maxKesehatan) {
                // gamePanel.getCurrentSim().kesehatan = gamePanel.getCurrentSim().maxKesehatan;
                // }
                // if (gamePanel.getCurrentSim().kekenyangan >
                // gamePanel.getCurrentSim().maxKekenyangan) {
                // gamePanel.getCurrentSim().kekenyangan =
                // gamePanel.getCurrentSim().maxKekenyangan;
                // }

                // // nambah WorldTimeCounter
                // gamePanel.worldTimeCounter += durasi;
                // gamePanel.worldTimeSatuHariCounter += durasi;
                // for (int i = 0; i < gamePanel.listSim.size(); i++) {
                // gamePanel.listSim.get(i).pekerjaan.worldTimeCounterForStartJobAfterChangeJob
                // += durasi;
                // }
                // gamePanel.ui.setelahAksi(durasi);

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

    public void inputDurasiMainGameState(int keyCode) {
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
                gamePanel.ui.tempDurasi = durasi;

                // timer state
                gamePanel.ui.durasiTimer = durasi;
                gamePanel.ui.currentAksi = "Main Game";
                gamePanel.gameState = gamePanel.timerState;
                gamePanel.ui.currentAksiDone = false;
                // efek
                gamePanel.getCurrentSim().mood += (durasi / 30) * 30;
                gamePanel.getCurrentSim().kesehatan -= (durasi / 30) * 5;
                gamePanel.getCurrentSim().kekenyangan -= (durasi / 30) * 10;
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
                gamePanel.worldTimeSatuHariCounter += durasi;
                for (int i = 0; i < gamePanel.listSim.size(); i++) {
                    gamePanel.listSim.get(i).pekerjaan.worldTimeCounterForStartJobAfterChangeJob += durasi;
                    gamePanel.listSim.get(i).efekWaktuTidakTidurCounter += durasi;
                    if (gamePanel.listSim.get(i).isUdahMakanDalamSatuHari) {
                        gamePanel.listSim.get(i).efekWaktuTidakBuangAirCounter += durasi;
                    }
                }
                threadTemp = gamePanel.ui.startTimerThread(durasi);

                // // efek
                // gamePanel.getCurrentSim().mood += (durasi / 30) * 30;
                // gamePanel.getCurrentSim().kesehatan -= (durasi / 30) * 5;
                // gamePanel.getCurrentSim().kekenyangan -= (durasi / 30) * 10;
                // if (gamePanel.getCurrentSim().mood > gamePanel.getCurrentSim().maxMood) {
                // gamePanel.getCurrentSim().mood = gamePanel.getCurrentSim().maxMood;
                // }
                // if (gamePanel.getCurrentSim().kesehatan >
                // gamePanel.getCurrentSim().maxKesehatan) {
                // gamePanel.getCurrentSim().kesehatan = gamePanel.getCurrentSim().maxKesehatan;
                // }
                // if (gamePanel.getCurrentSim().kekenyangan >
                // gamePanel.getCurrentSim().maxKekenyangan) {
                // gamePanel.getCurrentSim().kekenyangan =
                // gamePanel.getCurrentSim().maxKekenyangan;
                // }

                // // nambah WorldTimeCounter
                // gamePanel.worldTimeCounter += durasi;
                // gamePanel.worldTimeSatuHariCounter += durasi;
                // for (int i = 0; i < gamePanel.listSim.size(); i++) {
                // gamePanel.listSim.get(i).pekerjaan.worldTimeCounterForStartJobAfterChangeJob
                // += durasi;
                // }
                // gamePanel.ui.setelahAksi(durasi);

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

    public void inputDurasiOlahragaState(int keyCode) {
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
                gamePanel.ui.tempDurasi = durasi;

                // timer state
                gamePanel.ui.durasiTimer = durasi;
                gamePanel.ui.currentAksi = "Olahraga";
                gamePanel.gameState = gamePanel.timerState;
                gamePanel.ui.currentAksiDone = false;
                gamePanel.getCurrentSim().mood += (durasi / 20) * 10;
                gamePanel.getCurrentSim().kesehatan += (durasi / 20) * 5;
                gamePanel.getCurrentSim().kekenyangan -= (durasi / 20) * 5;
                if (gamePanel.getCurrentSim().mood > gamePanel.getCurrentSim().maxMood) {
                    gamePanel.getCurrentSim().mood = gamePanel.getCurrentSim().maxMood;
                }
                if (gamePanel.getCurrentSim().kesehatan > gamePanel.getCurrentSim().maxKesehatan) {
                    gamePanel.getCurrentSim().kesehatan = gamePanel.getCurrentSim().maxKesehatan;
                }
                if (gamePanel.getCurrentSim().kekenyangan > gamePanel.getCurrentSim().maxKekenyangan) {
                    gamePanel.getCurrentSim().kekenyangan = gamePanel.getCurrentSim().maxKekenyangan;
                }
                gamePanel.worldTimeCounter += durasi;
                gamePanel.worldTimeSatuHariCounter += durasi;
                for (int i = 0; i < gamePanel.listSim.size(); i++) {
                    gamePanel.listSim.get(i).pekerjaan.worldTimeCounterForStartJobAfterChangeJob += durasi;
                    gamePanel.listSim.get(i).efekWaktuTidakTidurCounter += durasi;
                    if (gamePanel.listSim.get(i).isUdahMakanDalamSatuHari) {
                        gamePanel.listSim.get(i).efekWaktuTidakBuangAirCounter += durasi;
                    }
                }
                threadTemp = gamePanel.ui.startTimerThread(durasi);

                // efek
                // gamePanel.getCurrentSim().mood += (durasi / 20) * 10;
                // gamePanel.getCurrentSim().kesehatan += (durasi / 20) * 5;
                // gamePanel.getCurrentSim().kekenyangan -= (durasi / 20) * 5;
                // if (gamePanel.getCurrentSim().mood > gamePanel.getCurrentSim().maxMood) {
                // gamePanel.getCurrentSim().mood = gamePanel.getCurrentSim().maxMood;
                // }
                // if (gamePanel.getCurrentSim().kesehatan >
                // gamePanel.getCurrentSim().maxKesehatan) {
                // gamePanel.getCurrentSim().kesehatan = gamePanel.getCurrentSim().maxKesehatan;
                // }
                // if (gamePanel.getCurrentSim().kekenyangan >
                // gamePanel.getCurrentSim().maxKekenyangan) {
                // gamePanel.getCurrentSim().kekenyangan =
                // gamePanel.getCurrentSim().maxKekenyangan;
                // }

                // nambah WorldTimeCounter
                // gamePanel.worldTimeCounter += durasi;
                // gamePanel.worldTimeSatuHariCounter += durasi;
                // for (int i = 0; i < gamePanel.listSim.size(); i++) {
                // gamePanel.listSim.get(i).pekerjaan.worldTimeCounterForStartJobAfterChangeJob
                // += durasi;
                // }
                // gamePanel.ui.setelahAksi(durasi);

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

    public void inputDurasiKerjaState(int keyCode) {
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
            gamePanel.gameState = gamePanel.kerjaState;
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ENTER) {
            if (gamePanel.ui.inputText.length() > 0) {
                int durasi = Integer.parseInt(gamePanel.ui.inputText);
                gamePanel.gameState = gamePanel.playState;
                gamePanel.ui.tempDurasi = durasi;

                // timer state
                gamePanel.ui.durasiTimer = durasi;
                gamePanel.ui.currentAksi = "Kerja";
                gamePanel.gameState = gamePanel.timerState;
                gamePanel.ui.currentAksiDone = false;

                // efek
                gamePanel.getCurrentSim().mood -= (durasi / 30) * 10;
                gamePanel.getCurrentSim().kekenyangan -= (durasi / 30) * 10;
                gamePanel.getCurrentSim().pekerjaan.totalDurasiKerjaPerPekerjaan += durasi;
                gamePanel.getCurrentSim().pekerjaan.durasiKerjaYangBelumDigaji += durasi;
                if (gamePanel.getCurrentSim().pekerjaan.durasiKerjaYangBelumDigaji >= 240) {
                    gamePanel.getCurrentSim().uang += gamePanel.getCurrentSim().pekerjaan.gaji[gamePanel
                            .getCurrentSim().pekerjaan.indexPekerjaan]
                            * (gamePanel.getCurrentSim().pekerjaan.durasiKerjaYangBelumDigaji / 240);
                    gamePanel.getCurrentSim().pekerjaan.durasiKerjaYangBelumDigaji -= 240 * (durasi / 240);
                }
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
                gamePanel.worldTimeSatuHariCounter += durasi;
                for (int i = 0; i < gamePanel.listSim.size(); i++) {
                    gamePanel.listSim.get(i).pekerjaan.worldTimeCounterForStartJobAfterChangeJob += durasi;
                    gamePanel.listSim.get(i).efekWaktuTidakTidurCounter += durasi;
                    if (gamePanel.listSim.get(i).isUdahMakanDalamSatuHari) {
                        gamePanel.listSim.get(i).efekWaktuTidakBuangAirCounter += durasi;
                    }
                }

                threadTemp = gamePanel.ui.startTimerThread(durasi);

                // // efek
                // // gamePanel.getCurrentSim().mood -= (durasi/30)*10;
                // // gamePanel.getCurrentSim().kekenyangan -= (durasi/30)*10;
                // gamePanel.getCurrentSim().pekerjaan.totalDurasiKerjaPerPekerjaan += durasi;
                // gamePanel.getCurrentSim().pekerjaan.durasiKerjaYangBelumDigaji += durasi;
                // if (gamePanel.getCurrentSim().pekerjaan.durasiKerjaYangBelumDigaji >= 240) {
                // gamePanel.getCurrentSim().uang +=
                // gamePanel.getCurrentSim().pekerjaan.gaji[gamePanel
                // .getCurrentSim().pekerjaan.indexPekerjaan]
                // * (gamePanel.getCurrentSim().pekerjaan.durasiKerjaYangBelumDigaji / 240);
                // gamePanel.getCurrentSim().pekerjaan.durasiKerjaYangBelumDigaji -= 240 *
                // (durasi / 240);
                // }
                // if (gamePanel.getCurrentSim().mood > gamePanel.getCurrentSim().maxMood) {
                // gamePanel.getCurrentSim().mood = gamePanel.getCurrentSim().maxMood;
                // }
                // if (gamePanel.getCurrentSim().kesehatan >
                // gamePanel.getCurrentSim().maxKesehatan) {
                // gamePanel.getCurrentSim().kesehatan = gamePanel.getCurrentSim().maxKesehatan;
                // }
                // if (gamePanel.getCurrentSim().kekenyangan >
                // gamePanel.getCurrentSim().maxKekenyangan) {
                // gamePanel.getCurrentSim().kekenyangan =
                // gamePanel.getCurrentSim().maxKekenyangan;
                // }

                // // nambah WorldTimeCounter
                // gamePanel.worldTimeCounter += durasi;
                // gamePanel.worldTimeSatuHariCounter += durasi;
                // for (int i = 0; i < gamePanel.listSim.size(); i++) {
                // gamePanel.listSim.get(i).pekerjaan.worldTimeCounterForStartJobAfterChangeJob
                // += durasi;
                // }
                // gamePanel.ui.setelahAksi(durasi);

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

    public void gantiPekerjaanState(int keyCode) {
        if (keyCode == KeyEvent.VK_ENTER) {
            gamePanel.gameState = gamePanel.playState;
            gamePanel.ui.charIndex = 0;
            gamePanel.ui.combinedText = "";
            gamePanel.gameState = gamePanel.dialogState;
            // cek pekerjaan sama seperti sebelumnya
            if (gamePanel.getCurrentSim().pekerjaan.indexPekerjaan == gamePanel.ui.commandNumber) {
                gamePanel.ui.currentDialog = "Pekerjaan tidak berubah.";
                cursorSound();
                gamePanel.ui.commandNumber = 0;
                return;
            } else {
                if (gamePanel.getCurrentSim().uang >= (int) gamePanel
                        .getCurrentSim().pekerjaan.gaji[gamePanel.ui.commandNumber] / 2) {
                    gamePanel.getCurrentSim().pekerjaan.indexPekerjaan = gamePanel.ui.commandNumber;
                    gamePanel.getCurrentSim().uang -= (int) gamePanel
                            .getCurrentSim().pekerjaan.gaji[gamePanel.ui.commandNumber] / 2;
                    gamePanel.ui.currentDialog = "Pekerjaan berhasil diganti menjadi\n" + gamePanel
                            .getCurrentSim().pekerjaan.listPekerjaan[gamePanel.getCurrentSim().pekerjaan.indexPekerjaan]
                            + ".";
                    cursorSound();
                    gamePanel.ui.commandNumber = 0;
                    gamePanel.getCurrentSim().pekerjaan.durasiKerjaYangBelumDigaji = 0;
                    gamePanel.getCurrentSim().pekerjaan.totalDurasiKerjaPerPekerjaan = 0;
                    gamePanel.getCurrentSim().pekerjaan.isCanStartPekerjaan = false;
                } else {
                    gamePanel.ui.charIndex = 0;
                    gamePanel.ui.combinedText = "";
                    gamePanel.gameState = gamePanel.dialogState;
                    gamePanel.ui.currentDialog = "Uang tidak cukup untuk melamar\npekerjaan!";
                }
            }

        } else if (keyCode == KeyEvent.VK_UP) {
            gamePanel.ui.commandNumber--;
            if (gamePanel.ui.commandNumber < 0) {
                gamePanel.ui.commandNumber = 4;
            }
            cursorSound();
        } else if (keyCode == KeyEvent.VK_DOWN) {
            gamePanel.ui.commandNumber++;
            if (gamePanel.ui.commandNumber > 4) {
                gamePanel.ui.commandNumber = 0;
            }
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.gameState = gamePanel.kerjaState;
            cursorSound();
        }
    }

    public void kerjaState(int keyCode) {
        if (gamePanel.getCurrentSim().pekerjaan.isCanChangePekerjaan
                && gamePanel.getCurrentSim().pekerjaan.isCanStartPekerjaan) {
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
            } else if (keyCode == KeyEvent.VK_ENTER) {
                if (gamePanel.ui.commandNumber == 0) {
                    gamePanel.gameState = gamePanel.inputDurasiKerjaState;
                } else {
                    gamePanel.gameState = gamePanel.gantiPekerjaanState;
                }
                cursorSound();
                gamePanel.ui.commandNumber = 0;
            }
        } else if (gamePanel.getCurrentSim().pekerjaan.isCanChangePekerjaan) {
            if (keyCode == KeyEvent.VK_UP) {
                gamePanel.ui.commandNumber--;
                if (gamePanel.ui.commandNumber < 0) {
                    gamePanel.ui.commandNumber = 0;
                }
                cursorSound();
            } else if (keyCode == KeyEvent.VK_DOWN) {
                gamePanel.ui.commandNumber++;
                if (gamePanel.ui.commandNumber > 0) {
                    gamePanel.ui.commandNumber = 0;
                }
                cursorSound();
            } else if (keyCode == KeyEvent.VK_ENTER) {
                gamePanel.gameState = gamePanel.gantiPekerjaanState;
                cursorSound();
                gamePanel.ui.commandNumber = 0;
            }
        } else if (gamePanel.getCurrentSim().pekerjaan.isCanStartPekerjaan) {
            if (keyCode == KeyEvent.VK_UP) {
                gamePanel.ui.commandNumber--;
                if (gamePanel.ui.commandNumber < 0) {
                    gamePanel.ui.commandNumber = 0;
                }
                cursorSound();
            } else if (keyCode == KeyEvent.VK_DOWN) {
                gamePanel.ui.commandNumber++;
                if (gamePanel.ui.commandNumber > 0) {
                    gamePanel.ui.commandNumber = 0;
                }
                cursorSound();
            } else if (keyCode == KeyEvent.VK_ENTER) {
                gamePanel.gameState = gamePanel.inputDurasiKerjaState;
                cursorSound();
                gamePanel.ui.commandNumber = 0;
            }
        }

        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.ui.inputText = "";
            gamePanel.ui.inputTextDone = false;
            gamePanel.ui.commandNumber = 0;
            gamePanel.gameState = gamePanel.playState;
            cursorSound();
        }
    }

    public void melihatWaktuState(int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.gameState = gamePanel.playState;
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
