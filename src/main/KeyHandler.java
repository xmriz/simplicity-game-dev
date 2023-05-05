package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import benda.Furnitur;
import entity.Sim;

public class KeyHandler implements KeyListener {

    private GamePanel gamePanel;
    private boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
    private Thread threadTemp;
    private boolean isFirst = true;

    private boolean checkWorldTime = false;
    private boolean checkCurrentLocation = false;

    public KeyHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public void setUpPressed(boolean upPressed) {
        this.upPressed = upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public void setDownPressed(boolean downPressed) {
        this.downPressed = downPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public void setLeftPressed(boolean leftPressed) {
        this.leftPressed = leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public void setRightPressed(boolean rightPressed) {
        this.rightPressed = rightPressed;
    }

    public boolean isEnterPressed() {
        return enterPressed;
    }

    public void setEnterPressed(boolean enterPressed) {
        this.enterPressed = enterPressed;
    }

    public Thread getThreadTemp() {
        return threadTemp;
    }

    public void setThreadTemp(Thread threadTemp) {
        this.threadTemp = threadTemp;
    }
    
    public boolean isCheckWorldTime() {
        return checkWorldTime;
    }

    public boolean isCheckCurrentLocation() {
        return checkCurrentLocation;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // TITLE STATE
        if (gamePanel.getGameState() == gamePanel.getTitleState()) {
            if (gamePanel.getUi().getTitleScreenState() == 0) {
                gamePanel.setIndexCurrentSim(0);
                if (keyCode == KeyEvent.VK_UP) {
                    gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()-1);
                    if (gamePanel.getUi().getCommandNumber() < 0) {
                        gamePanel.getUi().setCommandNumber(2);
                    }
                    cursorSound();
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()+1);
                    if (gamePanel.getUi().getCommandNumber() > 2) {
                        gamePanel.getUi().setCommandNumber(0);
                    }
                    cursorSound();
                } else if (keyCode == KeyEvent.VK_ENTER) {
                    if (gamePanel.getUi().getCommandNumber() == 0) {
                        gamePanel.getUi().setTitleScreenState(1); // go to input name screen
                        gamePanel.getUi().setInputFirstSimName("");
                    } else if (gamePanel.getUi().getCommandNumber() == 1) {
                        gamePanel.getUi().setTitleScreenState(2);
                        gamePanel.getUi().setCommandNumber(0);
                        // gamePanel.saveLoad.load();
                        // gamePanel.setGameState(gamePanel.getPlayState());
                        // gamePanel.stopMusic();
                        // gamePanel.playMusic(1);
                    } else if (gamePanel.getUi().getCommandNumber() == 2) {
                        System.exit(0);
                    }
                    cursorSound();
                }
            } else if (gamePanel.getUi().getTitleScreenState() == 1) {
                if (keyCode == KeyEvent.VK_UP) {
                    gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()-1);
                    if (gamePanel.getUi().getCommandNumber() < 0) {
                        gamePanel.getUi().setCommandNumber(1);
                    }
                    cursorSound();
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()+1);
                    if (gamePanel.getUi().getCommandNumber() > 1) {
                        gamePanel.getUi().setCommandNumber(0);
                    }
                    cursorSound();
                } else if (keyCode == KeyEvent.VK_BACK_SPACE && gamePanel.getUi().getInputFirstSimName().length() > 0) {
                    gamePanel.getUi().setInputFirstSimName(gamePanel.getUi().getInputFirstSimName().substring(0,
                    gamePanel.getUi().getInputFirstSimName().length() - 1));
                    cursorSound();
                } else if (keyCode == KeyEvent.VK_ENTER) {
                    if (gamePanel.getUi().getCommandNumber() == 0) { // start game
                        Sim sim1 = new Sim(gamePanel, gamePanel.getKeyHandler());
                        System.out.println(gamePanel.getListSim().size());
                        int tempSize = gamePanel.getListSim().size();
                        for (int i = 1; i < tempSize; i++) {
                            System.out.println(gamePanel.getListSim().size());
                            System.out.println(200);
                            gamePanel.getListSim().remove(gamePanel.getListSim().size() - 1);
                            System.out.println(2033);
                            gamePanel.getListRumah()[0].remove(gamePanel.getListSim().size() - 1);
                        }
                        gamePanel.getListSim().set(0, sim1);
                        gamePanel.getListRumah()[0].set(0, gamePanel.getListSim().get(0).getRumah());
                        gamePanel.getListRumah()[0].get(0).setWorldX(gamePanel.getListSim().get(0).getRumah().getColRumah()
                                * gamePanel.getTileSize());
                        gamePanel.getListRumah()[0].get(0).setWorldY(gamePanel.getListSim().get(0).getRumah().getRowRumah()
                                * gamePanel.getTileSize());

                        gamePanel.setIndexCurrentSim(0);
                        gamePanel.setWorldTimeCounter(0);
                        gamePanel.setIsOneSim(true);
                        gamePanel.setWorldTimeSatuHariCounter(0);

                        gamePanel.getListSim().get(0).setNama(gamePanel.getUi().getInputFirstSimName());
                        if (gamePanel.getListSim().get(0).getNama().length() == 0) {
                            gamePanel.getListSim().get(0).setNama("Sim");
                        } else {
                            // capitalize first letter
                            gamePanel.getListSim().get(0).setNama(UtilityTool
                                    .capitalizeFirstLetter(gamePanel.getListSim().get(0).getNama()));
                            // set currentlocation
                        }
                        gamePanel.getListSim().get(0).setCurrentLocation("Rumah "
                                + gamePanel.getListSim().get(0).getNama() + " ("
                                + UtilityTool.capitalizeFirstLetter(
                                        gamePanel.getListSim().get(0).getRumah().getRuanganRumah().get(0).getName())
                                + ")");
                        // set game state to play state
                        gamePanel.setGameState(gamePanel.getPlayState());
                        gamePanel.stopMusic();
                        gamePanel.playMusic(1);
                    } else if (gamePanel.getUi().getCommandNumber() == 1) { // back to Title Screen 0
                        gamePanel.getUi().setTitleScreenState(0);
                        gamePanel.getUi().setCommandNumber(0);
                        gamePanel.getUi().setInputFirstSimName("");
                    }
                    cursorSound();
                }

                // input name
                if (gamePanel.getUi().getInputFirstSimName().length() < 25) {
                    if (keyCode == KeyEvent.VK_A) {
                        gamePanel.getUi().addInputFirstSimName("A");
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_B) {
                        gamePanel.getUi().addInputFirstSimName("B");
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_C) {
                        gamePanel.getUi().addInputFirstSimName("C");
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_D) {
                        gamePanel.getUi().addInputFirstSimName("D");
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_E) {
                        gamePanel.getUi().addInputFirstSimName("E");
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_F) {
                        gamePanel.getUi().addInputFirstSimName("F");
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_G) {
                        gamePanel.getUi().addInputFirstSimName("G");
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_H) {
                        gamePanel.getUi().addInputFirstSimName("H");
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_I) {
                        gamePanel.getUi().addInputFirstSimName("I");
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_J) {
                        gamePanel.getUi().addInputFirstSimName("J");
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_K) {
                        gamePanel.getUi().addInputFirstSimName("K");
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_L) {
                        gamePanel.getUi().addInputFirstSimName("L");
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_M) {
                        gamePanel.getUi().addInputFirstSimName("M");
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_N) {
                        gamePanel.getUi().addInputFirstSimName("N");
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_O) {
                        gamePanel.getUi().addInputFirstSimName("O");
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_P) {
                        gamePanel.getUi().addInputFirstSimName("P");
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_Q) {
                        gamePanel.getUi().addInputFirstSimName("Q");
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_R) {
                        gamePanel.getUi().addInputFirstSimName("R");
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_S) {
                        gamePanel.getUi().addInputFirstSimName("S");
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_T) {
                        gamePanel.getUi().addInputFirstSimName("T");
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_U) {
                        gamePanel.getUi().addInputFirstSimName("U");
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_V) {
                        gamePanel.getUi().addInputFirstSimName("V");
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_W) {
                        gamePanel.getUi().addInputFirstSimName("W");
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_X) {
                        gamePanel.getUi().addInputFirstSimName("X");
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_Y) {
                        gamePanel.getUi().addInputFirstSimName("Y");
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_Z) {
                        gamePanel.getUi().addInputFirstSimName("Z");
                        cursorSound();
                    } else if (keyCode == KeyEvent.VK_SPACE) {
                        gamePanel.getUi().addInputFirstSimName(" ");
                        cursorSound();
                    }
                }
            } else if (gamePanel.getUi().getTitleScreenState() == 2) {
                if (keyCode == KeyEvent.VK_UP) {
                    gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()-1);
                    if (gamePanel.getUi().getCommandNumber() < 0) {
                        gamePanel.getUi().setCommandNumber(1);
                    }
                    cursorSound();
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()+1);
                    if (gamePanel.getUi().getCommandNumber() > 3) {
                        gamePanel.getUi().setCommandNumber(0);
                    }
                    cursorSound();
                } else if (keyCode == KeyEvent.VK_ENTER) {
                    if (gamePanel.getUi().getCommandNumber() == 0) {
                        gamePanel.saveLoad.load("save1");
                        gamePanel.setGameState(gamePanel.getPlayState());
                        gamePanel.stopMusic();
                        gamePanel.playMusic(1);
                    } else if (gamePanel.getUi().getCommandNumber() == 1) {
                        gamePanel.saveLoad.load("save2");
                        gamePanel.setGameState(gamePanel.getPlayState());
                        gamePanel.stopMusic();
                        gamePanel.playMusic(1);
                    } else if (gamePanel.getUi().getCommandNumber() == 2) {
                        gamePanel.saveLoad.load("save3");
                        gamePanel.setGameState(gamePanel.getPlayState());
                        gamePanel.stopMusic();
                        gamePanel.playMusic(1);
                    } else if (gamePanel.getUi().getCommandNumber() == 3) {
                        gamePanel.getUi().setTitleScreenState(0);
                    }
                    gamePanel.getUi().setCommandNumber(0);
                    keyCode = KeyEvent.VK_0;
                } else if (keyCode == KeyEvent.VK_ESCAPE) {
                    gamePanel.getUi().setTitleScreenState(0);
                    gamePanel.getUi().setCommandNumber(0);
                }
            }
        }

        // PLAY STATE
        if (gamePanel.getGameState() == gamePanel.getPlayState()) {
            playState(keyCode);
        }

        // PAUSE STATE
        else if (gamePanel.getGameState() == gamePanel.getPauseState()) {
            pauseState(keyCode);
        }

        // SIM INFO STATE
        else if (gamePanel.getGameState() == gamePanel.getSimInfoState()) {
            simInfoState(keyCode);
        }

        // DIALOG STATE
        else if (gamePanel.getGameState() == gamePanel.getDialogState()) {
            dialogState(keyCode);
        }

        // INVENTORY STATE
        else if (gamePanel.getGameState() == gamePanel.getInventoryState()) {
            inventoryState(keyCode);
        }

        // BELI STATE
        else if (gamePanel.getGameState() == gamePanel.getBeliState()) {
            beliState(keyCode);
        }

        // UPGRADE RUMAH STATE
        else if (gamePanel.getGameState() == gamePanel.getUpgradeRumahState()) {
            upgradeRumahState(keyCode);
        }

        // INPUT NAMA RUANGAN STATE
        else if (gamePanel.getGameState() == gamePanel.getInputNamaRuanganState()) {
            inputNamaRuanganState(keyCode);
        }

        // INPUT KOORDINAT BENDA STATE
        else if (gamePanel.getGameState() == gamePanel.getInputKoordinatBendaState()) {
            inputKoordinatBendaState(keyCode);
        }

        // ADD SIM STATE
        else if (gamePanel.getGameState() == gamePanel.getAddSimState()) {
            addSimState(keyCode);
        }

        // INPUT NAMA SIM STARE
        else if (gamePanel.getGameState() == gamePanel.getInputKoordinatRumahSimState()) {
            inputKoordinatRumahSimState(keyCode);
        }

        // MENU STATE
        else if (gamePanel.getGameState() == gamePanel.getMenuState()) {
            menuState(keyCode);
        }

        // HELP STATE
        else if (gamePanel.getGameState() == gamePanel.getHelpState()) {
            helpState(keyCode);
        }

        // CHANGE SIM STATE
        else if (gamePanel.getGameState() == gamePanel.getChangeSimState()) {
            changeSimState(keyCode);
        }
        // MAP STATE
        else if (gamePanel.getGameState() == gamePanel.getMapState()) {
            mapState(keyCode);
        }

        // RESEP STATE
        else if (gamePanel.getGameState() == gamePanel.getResepState()) {
            resepState(keyCode);
        }

        // INPUT DURASI TIDUR STATE
        else if (gamePanel.getGameState() == gamePanel.getInputDurasiTidurState()) {
            inputDurasiTidurState(keyCode);
        }

        // INPUT DURASI NONTON STATE
        else if (gamePanel.getGameState() == gamePanel.getInputDurasiNontonState()) {
            inputDurasiNontonState(keyCode);
        }

        // INPUT DURASI MANDI STATE
        else if (gamePanel.getGameState() == gamePanel.getInputDurasiMandiState()) {
            inputDurasiMandiState(keyCode);
        }

        // SHALAT STATE
        else if (gamePanel.getGameState() == gamePanel.getInputDurasiShalatState()) {
            inputDurasiShalatState(keyCode);
        }

        // BACA BUKU STATE
        else if (gamePanel.getGameState() == gamePanel.getInputDurasiBacaBukuState()) {
            inputDurasiBacaBukuState(keyCode);
        }

        // INPUT DURASI RADIO STATE
        else if (gamePanel.getGameState() == gamePanel.getInputDurasiRadioState()) {
            inputDurasiRadioState(keyCode);
        }

        // TIMER STATE
        else if (gamePanel.getGameState() == gamePanel.getTimerState()) {
            timerState(keyCode);
        }

        // GAME OVER STATE
        else if (gamePanel.getGameState() == gamePanel.getGameOverState()) {
            if (isFirst == true) {
                keyCode = KeyEvent.VK_0;
            }
            gameOverState(keyCode);
            isFirst = false;
        }

        // INPUT DURASI SIRAM TANAMAN STATE
        else if (gamePanel.getGameState() == gamePanel.getInputDurasiSiramTanamanState()) {
            inputDurasiSiramTanamanState(keyCode);
        }

        // INPUT DURASI MAIN GAME STATE
        else if (gamePanel.getGameState() == gamePanel.getInputDurasiMainGameState()) {
            inputDurasiMainGameState(keyCode);
        }

        // KERJA STATE
        else if (gamePanel.getGameState() == gamePanel.getKerjaState()) {
            kerjaState(keyCode);
        }

        // INPUT DURASI KERJA STATE
        else if (gamePanel.getGameState() == gamePanel.getInputDurasiKerjaState()) {
            inputDurasiKerjaState(keyCode);
        }

        // GANTI PEKERJAAN STATE
        else if (gamePanel.getGameState() == gamePanel.getGantiPekerjaanState()) {
            gantiPekerjaanState(keyCode);
        }

        // OLAHRAGA STATE
        else if (gamePanel.getGameState() == gamePanel.getInputDurasiOlahragaState()) {
            inputDurasiOlahragaState(keyCode);
        }
        // SAVE SCREEN
        else if (gamePanel.getGameState() == gamePanel.getSaveState()) {
            saveState(keyCode);
        }

        // MELIHAT WAKTU
        else if (gamePanel.getGameState() == gamePanel.getMelihatWaktuState()) {
            melihatWaktuState(keyCode);
        }

        // CUTSCENE
        else if (gamePanel.getGameState() == gamePanel.getCutsceneState()) {
            cutsceneState(keyCode);
        }
    }

    public void cutsceneState(int keyCode) {
        if (keyCode == KeyEvent.VK_ENTER) {
            gamePanel.setGameState(gamePanel.getMenuState());
            gamePanel.getCutsceneManager().setScenePhase(0);
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
            gamePanel.setGameState(gamePanel.getPauseState());
            checkWorldTime = false;
            checkCurrentLocation = false;
        } else if (keyCode == KeyEvent.VK_C) {
            gamePanel.setGameState(gamePanel.getSimInfoState());
            checkWorldTime = false;
            checkCurrentLocation = false;
        } else if (keyCode == KeyEvent.VK_I) {
            gamePanel.setGameState(gamePanel.getInventoryState());
            checkWorldTime = false;
            checkCurrentLocation = false;
        } else if (keyCode == KeyEvent.VK_B) {
            gamePanel.getUi().setCharIndex(0);
            gamePanel.getUi().setCombinedText("");
            gamePanel.getNpc()[0][4].speak();
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
            // System.out.println(gamePanel.getListSim().get(gamePanel.indexCurrentSim).indexRumahYangDimasuki);
            if (gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap() == 0) {
                gamePanel.getUi().setCharIndex(0);
                gamePanel.getUi().setCombinedText("");
                gamePanel.setGameState(gamePanel.getDialogState());
                gamePanel.getUi().setCurrentDialog("Anda sedang tidak berada di rumah.\nTidak dapat melakukan upgrade rumah!");
            } else if (gamePanel.getListSim()
                    .get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki() != gamePanel.getIndexCurrentSim()) {
                gamePanel.getUi().setCharIndex(0);
                gamePanel.getUi().setCombinedText("");
                gamePanel.setGameState(gamePanel.getDialogState());
                gamePanel.getUi().setCurrentDialog("Anda harus berada di rumah sendiri.\nTidak dapat melakukan upgrade rumah!");
            } else {
                gamePanel.setGameState(gamePanel.getUpgradeRumahState());
            }
            checkWorldTime = false;
            checkCurrentLocation = false;
        } else if (keyCode == KeyEvent.VK_N) {
            gamePanel.setGameState(gamePanel.getAddSimState());
            checkWorldTime = false;
            checkCurrentLocation = false;
        } else if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.setGameState(gamePanel.getMenuState());
            checkWorldTime = false;
            checkCurrentLocation = false;
        } else if (keyCode == KeyEvent.VK_G) {
            gamePanel.setGameState(gamePanel.getChangeSimState());
            checkWorldTime = false;
            checkCurrentLocation = false;
        } else if (keyCode == KeyEvent.VK_M) {
            if (gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap() == 0) {
                gamePanel.setGameState(gamePanel.getMapState());
            } else {
                gamePanel.getUi().setCharIndex(0);
                gamePanel.getUi().setCombinedText("");
                gamePanel.setGameState(gamePanel.getDialogState());
                gamePanel.getUi().setCurrentDialog("Tidak dapat membuka peta di rumah!");
            }
            checkWorldTime = false;
            checkCurrentLocation = false;
        } else if (keyCode == KeyEvent.VK_X) {
            if (gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap() == 0) {
                if (gamePanel.map.mapOn == false) {
                    gamePanel.map.mapOn = true;
                } else {
                    gamePanel.map.mapOn = false;
                }
            } else {
                gamePanel.getUi().setCharIndex(0);
                gamePanel.getUi().setCombinedText("");
                gamePanel.setGameState(gamePanel.getDialogState());
                gamePanel.getUi().setCurrentDialog("Tidak dapat membuka mini peta\ndi rumah!");
            }
        } else if (keyCode == KeyEvent.VK_Z) {
            if (gamePanel.getCurrentSim().getCurrentMap() == 1) {
                gamePanel.getCurrentSim().pickUpObject(gamePanel.getCurrentSim().getIndexBendaYangDisentuh());
            }
        } else if (keyCode == KeyEvent.VK_K) {
            gamePanel.setGameState(gamePanel.getKerjaState());
            checkWorldTime = false;
            checkCurrentLocation = false;
        } else if (keyCode == KeyEvent.VK_O) {
            gamePanel.setGameState(gamePanel.getInputDurasiOlahragaState());
            checkWorldTime = false;
            checkCurrentLocation = false;
        }
    }

    public void pauseState(int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.setGameState(gamePanel.getPlayState());
        }
    }

    public void simInfoState(int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.setGameState(gamePanel.getPlayState());
        }
    }

    public void dialogState(int keyCode) {
        if (keyCode == KeyEvent.VK_ENTER) {
            // gamePanel.stopMusic();
            // gamePanel.playMusic(1);
            gamePanel.getCurrentSim().update();
            if (gamePanel.getCurrentSim().getIsBarangSampai() == true) {
                gamePanel.getUi().setCharIndex(0);
                gamePanel.getUi().setCombinedText("");
                gamePanel.setGameState(gamePanel.getDialogState());
                gamePanel.getUi().setCurrentDialog(gamePanel.getCurrentSim().getTempDialogBarang());
                gamePanel.getCurrentSim().setIsBarangSampai(false);
                gamePanel.getCurrentSim().setTempDialogBarang("");
            } else if (gamePanel.getCurrentSim().getIsUpgradeDone() == true) {
                gamePanel.getUi().setCharIndex(0);
                gamePanel.getUi().setCombinedText("");
                gamePanel.setGameState(gamePanel.getDialogState());
                gamePanel.getUi().setCurrentDialog(gamePanel.getCurrentSim().getTempDialogUpgrade());
                gamePanel.getCurrentSim().setIsUpgradeDone(false);
                gamePanel.getCurrentSim().setTempDialogUpgrade("");
            } else if (gamePanel.getCurrentSim().getIsMati() == true) {
                gamePanel.setGameState(gamePanel.getGameOverState());
            } else {
                gamePanel.setGameState(gamePanel.getPlayState());
            }
            // change direction of player
            // if (gamePanel.getListSim().get(gamePanel.indexCurrentSim).direction == "up") {
            // gamePanel.getListSim().get(gamePanel.indexCurrentSim).direction = "down";
            // } else if (gamePanel.getListSim().get(gamePanel.indexCurrentSim).direction ==
            // "down") {
            // gamePanel.getListSim().get(gamePanel.indexCurrentSim).direction = "up";
            // } else if (gamePanel.getListSim().get(gamePanel.indexCurrentSim).direction ==
            // "left") {
            // gamePanel.getListSim().get(gamePanel.indexCurrentSim).direction = "right";
            // } else if (gamePanel.getListSim().get(gamePanel.indexCurrentSim).direction ==
            // "right") {
            // gamePanel.getListSim().get(gamePanel.indexCurrentSim).direction = "left";
            // }
        }
    }

    public void inventoryState(int keyCode) {
        int index;
        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.setGameState(gamePanel.getPlayState());
            gamePanel.getUi().setSimSlotRow(0);
            gamePanel.getUi().setSimSlotCol(0);
        } else if (keyCode == KeyEvent.VK_UP) {
            if (gamePanel.getUi().getSimSlotRow() > 0) {
                index = UI.getItemIndexOnSlot(gamePanel.getUi().getSimSlotRow() - 1, gamePanel.getUi().getSimSlotCol());
                if (index <= gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getInventory().size() - 1) {
                    gamePanel.getUi().setSimSlotRow(gamePanel.getUi().getSimSlotRow()-1);
                    cursorSound();
                }
            } else {
                index = UI.getItemIndexOnSlot(2, gamePanel.getUi().getSimSlotCol());
                if (index <= gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getInventory().size() - 1) {
                    gamePanel.getUi().setSimSlotRow(2);
                }
            }
        } else if (keyCode == KeyEvent.VK_DOWN) {
            if (gamePanel.getUi().getSimSlotRow() < 2) {
                index = UI.getItemIndexOnSlot(gamePanel.getUi().getSimSlotRow() + 1, gamePanel.getUi().getSimSlotCol());
                if (index <= gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getInventory().size() - 1) {
                    gamePanel.getUi().setSimSlotRow(gamePanel.getUi().getSimSlotRow()+1);
                    cursorSound();
                }
            } else {
                index = UI.getItemIndexOnSlot(0, gamePanel.getUi().getSimSlotCol());
                if (index <= gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getInventory().size() - 1) {
                    gamePanel.getUi().setSimSlotRow(0);
                }
            }
        } else if (keyCode == KeyEvent.VK_LEFT) {
            if (gamePanel.getUi().getSimSlotCol() > 0) {
                index = UI.getItemIndexOnSlot(gamePanel.getUi().getSimSlotRow(), gamePanel.getUi().getSimSlotCol() - 1);
                if (index <= gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getInventory().size() - 1) {
                    gamePanel.getUi().setSimSlotCol(gamePanel.getUi().getSimSlotCol()-1);
                    cursorSound();
                }
            } else {
                index = UI.getItemIndexOnSlot(gamePanel.getUi().getSimSlotRow(), 10);
                if (index <= gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getInventory().size() - 1) {
                    gamePanel.getUi().setSimSlotCol(10);
                }
            }
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            if (gamePanel.getUi().getSimSlotCol() < 10) {
                index = UI.getItemIndexOnSlot(gamePanel.getUi().getSimSlotRow(), gamePanel.getUi().getSimSlotCol() + 1);
                if (index <= gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getInventory().size() - 1) {
                    gamePanel.getUi().setSimSlotCol(gamePanel.getUi().getSimSlotCol()+1);
                    cursorSound();
                }
            } else {
                index = UI.getItemIndexOnSlot(gamePanel.getUi().getSimSlotRow(), 0);
                if (index <= gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getInventory().size() - 1) {
                    gamePanel.getUi().setSimSlotCol(0);
                }
            }
        } else if (keyCode == KeyEvent.VK_ENTER) {
            gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).selectItem();
            gamePanel.getUi().setSimSlotRow(0);
            gamePanel.getUi().setSimSlotCol(0);
            cursorSound();
        }
    }

    public void menuState(int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.setGameState(gamePanel.getPlayState());
            gamePanel.getUi().setCommandNumber(0);
        }
        if (gamePanel.getUi().getCommandNumber() == 0) {
            if (keyCode == KeyEvent.VK_DOWN) {
                gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()+1);
                cursorSound();
                inputDurasiTidurState(keyCode);
            } else if (keyCode == KeyEvent.VK_UP) {
                gamePanel.getUi().setCommandNumber(6);
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
        } else if (gamePanel.getUi().getCommandNumber() == 4) {
            if (keyCode == KeyEvent.VK_UP) {
                gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()-1);
                cursorSound();
            } else if (keyCode == KeyEvent.VK_DOWN) {
                gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()+1);
                cursorSound();
            } else if (keyCode == KeyEvent.VK_ENTER) {
                gamePanel.setGameState(gamePanel.getTitleState());
                gamePanel.getUi().setCommandNumber(0);
                gamePanel.getUi().setTitleScreenState(0);
                gamePanel.stopMusic();
                gamePanel.playMusic(0);
                cursorSound();
            }
        } else if (gamePanel.getUi().getCommandNumber() == 6) {
            if (keyCode == KeyEvent.VK_UP) {
                gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()-1);
                cursorSound();
            } else if (keyCode == KeyEvent.VK_DOWN) {
                gamePanel.getUi().setCommandNumber(0);
                cursorSound();
            } else if (keyCode == KeyEvent.VK_ENTER) {
                gamePanel.setGameState(gamePanel.getPlayState());
                gamePanel.getUi().setCommandNumber(0);
                cursorSound();
            }
        } else if (gamePanel.getUi().getCommandNumber() == 1) {
            if (keyCode == KeyEvent.VK_UP) {
                gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()-1);
                cursorSound();
            } else if (keyCode == KeyEvent.VK_DOWN) {
                gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()+1);
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
        } else if (gamePanel.getUi().getCommandNumber() == 2) {
            if (keyCode == KeyEvent.VK_UP) {
                gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()-1);
                cursorSound();
            } else if (keyCode == KeyEvent.VK_DOWN) {
                gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()+1);
                cursorSound();
            } else if (keyCode == KeyEvent.VK_ENTER) {
                gamePanel.setGameState(gamePanel.getHelpState());
                gamePanel.getUi().setCommandNumber(0);
                cursorSound();
            }
        } else if (gamePanel.getUi().getCommandNumber() == 3) {
            if (keyCode == KeyEvent.VK_UP) {
                gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()-1);
                cursorSound();
            } else if (keyCode == KeyEvent.VK_DOWN) {
                gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()+1);
                cursorSound();
            }
            if (keyCode == KeyEvent.VK_ENTER) {
                gamePanel.setGameState(gamePanel.getSaveState());
                gamePanel.getUi().setCommandNumber(0);
                cursorSound();
            }
        } else if (gamePanel.getUi().getCommandNumber() == 5) {
            if (keyCode == KeyEvent.VK_UP) {
                gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()-1);
                cursorSound();
            } else if (keyCode == KeyEvent.VK_DOWN) {
                gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()+1);
                cursorSound();
            } else if (keyCode == KeyEvent.VK_ENTER) {
                gamePanel.setGameState(gamePanel.getCutsceneState());
                gamePanel.getCutsceneManager().setSceneNum(gamePanel.getCutsceneManager().getEnding());
                gamePanel.getUi().setCommandNumber(0);
                cursorSound();
            }
        }
    }

    public void saveState(int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.setGameState(gamePanel.getMenuState());
            gamePanel.getUi().setCommandNumber(0);
        }
        if (gamePanel.getUi().getCommandNumber() == 0) {
            if (keyCode == KeyEvent.VK_DOWN) {
                gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()+1);
                cursorSound();
                inputDurasiTidurState(keyCode);
            } else if (keyCode == KeyEvent.VK_UP) {
                gamePanel.getUi().setCommandNumber(3);
                cursorSound();
            }
            if (keyCode == KeyEvent.VK_ENTER) {
                gamePanel.getUi().setCommandNumber(0);
                gamePanel.setGameState(gamePanel.getPlayState());
                gamePanel.saveLoad.save("save1");
            }
        } else if (gamePanel.getUi().getCommandNumber() == 1) {
            if (keyCode == KeyEvent.VK_UP) {
                gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()-1);
                cursorSound();
            } else if (keyCode == KeyEvent.VK_DOWN) {
                gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()+1);
                cursorSound();
            }
            if (keyCode == KeyEvent.VK_ENTER) {
                gamePanel.getUi().setCommandNumber(0);
                gamePanel.setGameState(gamePanel.getPlayState());
                gamePanel.saveLoad.save("save2");
            }
        } else if (gamePanel.getUi().getCommandNumber() == 2) {
            if (keyCode == KeyEvent.VK_UP) {
                gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()-1);
                cursorSound();
            } else if (keyCode == KeyEvent.VK_DOWN) {
                gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()+1);
                cursorSound();
            }
            if (keyCode == KeyEvent.VK_ENTER) {
                gamePanel.getUi().setCommandNumber(0);
                gamePanel.setGameState(gamePanel.getPlayState());
                gamePanel.saveLoad.save("save3");
            }
        } else if (gamePanel.getUi().getCommandNumber() == 3) {
            if (keyCode == KeyEvent.VK_UP) {
                gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()-1);
                cursorSound();
            } else if (keyCode == KeyEvent.VK_DOWN) {
                gamePanel.getUi().setCommandNumber(0);
                cursorSound();
            }
            if (keyCode == KeyEvent.VK_ENTER) {
                gamePanel.setGameState(gamePanel.getMenuState());
                gamePanel.getUi().setCommandNumber(0);
            }
        }
    }

    public void helpState(int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.setGameState(gamePanel.getMenuState());
        }
    }

    public void mapState(int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.setGameState(gamePanel.getPlayState());
        }
    }

    public void beliState(int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.setGameState(gamePanel.getPlayState());
            gamePanel.getUi().setSubState(0);
            gamePanel.getUi().setCommandNumber(0);
            cursorSound();
        }
        if (keyCode == KeyEvent.VK_ENTER) {
            enterPressed = true;
            cursorSound();
        }
        if (gamePanel.getUi().getSubState() == 0) {
            if (keyCode == KeyEvent.VK_UP) {
                gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()-1);
                if (gamePanel.getUi().getCommandNumber() < 0) {
                    gamePanel.getUi().setCommandNumber(1);
                }
                cursorSound();
            } else if (keyCode == KeyEvent.VK_DOWN) {
                gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()+1);
                if (gamePanel.getUi().getCommandNumber() > 1) {
                    gamePanel.getUi().setCommandNumber(0);
                }
                cursorSound();
            }
        }
        if (gamePanel.getUi().getSubState() == 1) {
            beliInventory(keyCode);
            if (keyCode == KeyEvent.VK_ESCAPE) {
                gamePanel.getUi().setSubState(0);
                cursorSound();
                gamePanel.getUi().setNpcSlotCol(0);
                gamePanel.getUi().setNpcSlotRow(0);
            }
        }
    }

    public void beliInventory(int keyCode) {
        int index = 0;
        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.setGameState(gamePanel.getPlayState());
            gamePanel.getUi().setSubState(0);
            gamePanel.getUi().setNpcSlotCol(0);
            gamePanel.getUi().setNpcSlotRow(0);
        } else if (keyCode == KeyEvent.VK_UP) {
            if (gamePanel.getUi().getNpcSlotRow() > 0) {
                index = UI.getItemIndexOnSlot(gamePanel.getUi().getNpcSlotRow() - 1, gamePanel.getUi().getNpcSlotCol());
                if (index <= gamePanel.getNpc()[0][4].getInventory().size() - 1) {
                    gamePanel.getUi().setNpcSlotRow(gamePanel.getUi().getNpcSlotRow()-1);
                    cursorSound();
                }
            } else {
                index = UI.getItemIndexOnSlot(2, gamePanel.getUi().getNpcSlotCol());
                if (index <= gamePanel.getNpc()[0][4].getInventory().size() - 1) {
                    gamePanel.getUi().setNpcSlotRow(2);
                }
            }
        } else if (keyCode == KeyEvent.VK_DOWN) {
            if (gamePanel.getUi().getNpcSlotRow() < 2) {
                index = UI.getItemIndexOnSlot(gamePanel.getUi().getNpcSlotRow() + 1, gamePanel.getUi().getNpcSlotCol());
                if (index <= gamePanel.getNpc()[0][4].getInventory().size() - 1) {
                    gamePanel.getUi().setNpcSlotRow(gamePanel.getUi().getNpcSlotRow()+1);
                    cursorSound();
                }
            } else {
                index = UI.getItemIndexOnSlot(0, gamePanel.getUi().getNpcSlotCol());
                if (index <= gamePanel.getNpc()[0][4].getInventory().size() - 1) {
                    gamePanel.getUi().setNpcSlotRow(0);
                }
            }
        } else if (keyCode == KeyEvent.VK_LEFT) {
            if (gamePanel.getUi().getNpcSlotCol() > 0) {
                index = UI.getItemIndexOnSlot(gamePanel.getUi().getNpcSlotRow(), gamePanel.getUi().getNpcSlotCol() - 1);
                if (index <= gamePanel.getNpc()[0][4].getInventory().size() - 1) {
                    gamePanel.getUi().setNpcSlotCol(gamePanel.getUi().getNpcSlotCol()-1);
                    cursorSound();
                }
            } else {
                index = UI.getItemIndexOnSlot(gamePanel.getUi().getNpcSlotRow(), 10);
                if (index <= gamePanel.getNpc()[0][4].getInventory().size() - 1) {
                    gamePanel.getUi().setNpcSlotCol(10);
                }
            }
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            if (gamePanel.getUi().getNpcSlotCol() < 10) {
                index = UI.getItemIndexOnSlot(gamePanel.getUi().getNpcSlotRow(), gamePanel.getUi().getNpcSlotCol() + 1);
                if (index <= gamePanel.getNpc()[0][4].getInventory().size() - 1) {
                    gamePanel.getUi().setNpcSlotCol(gamePanel.getUi().getNpcSlotCol()+1);
                    cursorSound();
                }
            } else {
                index = UI.getItemIndexOnSlot(gamePanel.getUi().getNpcSlotRow(), 0);
                if (index <= gamePanel.getNpc()[0][4].getInventory().size() - 1) {
                    gamePanel.getUi().setNpcSlotCol(0);
                }
            }
        }
    }

    public void upgradeRumahState(int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.setGameState(gamePanel.getPlayState());
            gamePanel.getUi().setCommandNumber(0);
        }

        if (keyCode == KeyEvent.VK_UP) {
            gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()-1);
            if (gamePanel.getUi().getCommandNumber() < 0) {
                gamePanel.getUi().setCommandNumber(3);
            }
            cursorSound();
        } else if (keyCode == KeyEvent.VK_DOWN) {
            gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()+1);
            if (gamePanel.getUi().getCommandNumber() > 3) {
                gamePanel.getUi().setCommandNumber(0);
            }
            cursorSound();
        } else if (keyCode == KeyEvent.VK_ENTER) {
            // gamePanel.setGameState(gamePanel.getPlayState());
            gamePanel.setGameState(gamePanel.getInputNamaRuanganState());
            cursorSound();
        }
        // draw input box
        // if (gamePanel.getUi().inputTextDone) {

        // }
    }

    public void inputKoordinatBendaState(int keyCode) {
        // DIALOG STATE
        // nyimpan tempBenda dari sim ke tempFurnitur
        Furnitur tempFurnitur = (Furnitur) gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getTempBenda();
        // input name
        if (gamePanel.getUi().getInputText().length() < 3) {
            if (keyCode == KeyEvent.VK_1) {
                gamePanel.getUi().addInputText("1");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_2) {
                gamePanel.getUi().addInputText("2");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_3) {
                gamePanel.getUi().addInputText("3");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_4) {
                gamePanel.getUi().addInputText("4");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_5) {
                gamePanel.getUi().addInputText("5");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_6) {
                gamePanel.getUi().addInputText("6");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_COMMA) {
                gamePanel.getUi().addInputText(",");
                cursorSound();
            }
        }

        if (keyCode == KeyEvent.VK_BACK_SPACE && gamePanel.getUi().getInputText().length() > 0) {
            gamePanel.getUi().setInputText(gamePanel.getUi().getInputText().substring(0, gamePanel.getUi().getInputText().length() - 1));
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ENTER) {

            // check panjang input
            if (gamePanel.getUi().getInputText().length() > 0) {
                gamePanel.setGameState(gamePanel.getPlayState());

                String input = gamePanel.getUi().getInputText();
                int commaCounter = input.length() - input.replace(",", "").length();
                // check apakah input valid
                if (input.charAt(0) == ',' || input.charAt(input.length() - 1) == ',' || commaCounter != 1) {
                    gamePanel.getUi().setCharIndex(0);
                    gamePanel.getUi().setCombinedText("");
                    gamePanel.setGameState(gamePanel.getDialogState());
                    gamePanel.getUi().setCurrentDialog("Koordinat tidak valid!");
                    gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).canObtainItem(tempFurnitur);
                    gamePanel.getUi().setCommandNumber(0);
                    gamePanel.getUi().setInputText("");
                } else {
                    int x = Integer.parseInt(input.substring(0, input.indexOf(",")));
                    int y = Integer.parseInt(input.substring(input.indexOf(",") + 1));
                    // check apakah koordinat range 1-6
                    if (x < 1 || x > 6 || y < 1 || y > 6) {
                        gamePanel.getUi().setCharIndex(0);
                        gamePanel.getUi().setCombinedText("");
                        gamePanel.setGameState(gamePanel.getDialogState());
                        gamePanel.getUi().setCurrentDialog("Koordinat harus berada pada range 1-6!");
                        gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).canObtainItem(tempFurnitur);
                        gamePanel.getUi().setCommandNumber(0);
                        gamePanel.getUi().setInputText("");
                    } else {
                        if (gamePanel.getListSim()
                                .get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki() != gamePanel.getIndexCurrentSim()) {
                            gamePanel.getUi().setCharIndex(0);
                            gamePanel.getUi().setCombinedText("");
                            gamePanel.setGameState(gamePanel.getDialogState());
                            gamePanel.getUi().setCurrentDialog("Tidak dapat meletakkan furnitur di rumah\nsim lain!");
                            gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).canObtainItem(tempFurnitur);
                            gamePanel.getUi().setCommandNumber(0);
                            gamePanel.getUi().setInputText("");
                        } else {
                            // check apakah dalam ruangan sudah terdapat furnitur yang sama
                            boolean isSame = false;
                            for (int i = 0; i < gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getRumah().getRuanganRumah().get(
                                    gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getBendaRuangan()
                                    .size(); i++) {
                                if (gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getRumah().getRuanganRumah()
                                        .get(gamePanel.getListSim()
                                                .get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getBendaRuangan()
                                        .get(i).getName().equals(tempFurnitur.getName())) {
                                    isSame = true;
                                }
                            }
                            if (isSame) {
                                gamePanel.getUi().setCharIndex(0);
                                gamePanel.getUi().setCombinedText("");
                                gamePanel.setGameState(gamePanel.getDialogState());
                                gamePanel.getUi().setCurrentDialog("Sudah terdapat " + tempFurnitur.getName() + " di ruangan ini!");
                                gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).canObtainItem(tempFurnitur);
                                gamePanel.getUi().setCommandNumber(0);
                                gamePanel.getUi().setInputText("");
                            } else {
                                // check nabrak dinding
                                int horizontalCollision = (x + 1) * gamePanel.getTileSize() + tempFurnitur.getSolidArea().width;
                                int verticalCollision = (y + 1) * gamePanel.getTileSize() + tempFurnitur.getSolidArea().height;
                                if (horizontalCollision >= 9 * gamePanel.getTileSize()
                                        || verticalCollision >= 9 * gamePanel.getTileSize()) {
                                    gamePanel.getUi().setCharIndex(0);
                                    gamePanel.getUi().setCombinedText("");
                                    gamePanel.setGameState(gamePanel.getDialogState());
                                    gamePanel.getUi().setCurrentDialog("Furnitur menabrak dinding!");
                                    gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).canObtainItem(tempFurnitur);
                                    gamePanel.getUi().setCommandNumber(0);
                                    gamePanel.getUi().setInputText("");
                                } else {
                                    // check intersect dengan furnitur lain
                                    tempFurnitur.getSolidArea().x = (x + 1) * gamePanel.getTileSize();
                                    tempFurnitur.getSolidArea().y = (y + 1) * gamePanel.getTileSize();
                                    boolean intersect = false;
                                    for (int i = 0; i < gamePanel.getListSim()
                                            .get(gamePanel.getIndexCurrentSim()).getRumah().getRuanganRumah()
                                            .get(gamePanel.getListSim()
                                                    .get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getBendaRuangan()
                                            .size(); i++) {
                                        if (tempFurnitur.getSolidArea().intersects(gamePanel.getListSim()
                                                .get(gamePanel.getIndexCurrentSim()).getRumah().getRuanganRumah()
                                                .get(gamePanel.getListSim().get(
                                                        gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getBendaRuangan()
                                                .get(i).getSolidArea())) {
                                            intersect = true;
                                            break;
                                        }
                                    }
                                    if (intersect) {
                                        gamePanel.getUi().setCharIndex(0);
                                        gamePanel.getUi().setCombinedText("");
                                        gamePanel.setGameState(gamePanel.getDialogState());
                                        gamePanel.getUi().setCurrentDialog("Furnitur tidak boleh bersebrangan!");
                                        gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).canObtainItem(tempFurnitur);
                                        gamePanel.getUi().setCommandNumber(0);
                                        gamePanel.getUi().setInputText("");
                                    } else {
                                        gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getRumah().getRuanganRumah()
                                                .get(gamePanel.getListSim().get(
                                                        gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getBendaRuangan()
                                                .add(tempFurnitur);
                                        gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getRumah().getRuanganRumah()
                                                .get(gamePanel.getListSim().get(
                                                        gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getBendaRuangan()
                                                .get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getRumah().getRuanganRumah()
                                                        .get(gamePanel.getListSim().get(
                                                                gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getBendaRuangan()
                                                        .size() - 1).setWorldX((x + 1) * gamePanel.getTileSize());
                                        gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getRumah().getRuanganRumah()
                                                .get(gamePanel.getListSim().get(
                                                        gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getBendaRuangan()
                                                .get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getRumah().getRuanganRumah()
                                                        .get(gamePanel.getListSim().get(
                                                                gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getBendaRuangan()
                                                        .size() - 1).setWorldY((y + 1) * gamePanel.getTileSize());
                                    }
                                }
                            }
                        }

                        // ------------------------------------BATAS------------------------------------
                        // gamePanel.getListSim().get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.getListSim().get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.add(tempFurnitur);
                        // gamePanel.getListSim().get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.getListSim().get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(gamePanel.getListSim().get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.getListSim().get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.size()
                        // - 1).worldX = (x+1) * gamePanel.tileSize;
                        // gamePanel.getListSim().get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.getListSim().get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(gamePanel.getListSim().get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.getListSim().get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.size()
                        // - 1).solidArea.x = (x+1) * gamePanel.tileSize;
                        // gamePanel.getListSim().get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.getListSim().get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(gamePanel.getListSim().get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.getListSim().get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.size()
                        // - 1).worldY = (y+1) * gamePanel.tileSize;
                        // gamePanel.getListSim().get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.getListSim().get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(gamePanel.getListSim().get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.getListSim().get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.size()
                        // - 1).solidArea.y = (y+1) * gamePanel.tileSize;
                        // int horizontalCollision =
                        // gamePanel.getListSim().get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.getListSim().get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(gamePanel.getListSim().get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.getListSim().get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.size()
                        // - 1).worldX +
                        // gamePanel.getListSim().get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.getListSim().get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(gamePanel.getListSim().get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.getListSim().get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.size()
                        // - 1).solidArea.width;
                        // int verticalCollision =
                        // gamePanel.getListSim().get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.getListSim().get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(gamePanel.getListSim().get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.getListSim().get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.size()
                        // - 1).worldY +
                        // gamePanel.getListSim().get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.getListSim().get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(gamePanel.getListSim().get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.getListSim().get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.size()
                        // - 1).solidArea.height;
                        // Benda bendaT =
                        // gamePanel.getListSim().get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.getListSim().get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(gamePanel.getListSim().get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.getListSim().get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.size()
                        // - 1);
                        // if (horizontalCollision >= 9*gamePanel.tileSize || verticalCollision >=
                        // 9*gamePanel.tileSize){
                        // // check nabrak dinding
                        // gamePanel.getListSim().get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.getListSim().get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.remove(gamePanel.getListSim().get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.getListSim().get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(gamePanel.getListSim().get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.getListSim().get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.size()
                        // - 1));
                        // gamePanel.getListSim().get(gamePanel.indexCurrentSim).canObtainItem(bendaT); //
                        // INI JANGAN DIHAPUS
                        // gamePanel.setGameState(gamePanel.getDialogState());
                        // gamePanel.getUi().setCurrentDialog"Tempat tidak cukup untuk
                        // meletakkan\nfurnitur!";
                        // } else {
                        // // check intersection dengan furnitur lain
                        // for (int i = 0; i <
                        // gamePanel.getListSim().get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.getListSim().get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.size()
                        // - 1; i++){
                        // Benda bendaR =
                        // gamePanel.getListSim().get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.getListSim().get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(i);
                        // // System.out.println(horizontalCollision);
                        // // System.out.println(verticalCollision);
                        // // System.out.println(bendaR.solidArea.x);

                        // if (bendaR instanceof Furnitur && bendaT instanceof Furnitur){
                        // if (bendaR.solidArea.intersects(bendaT.solidArea)){
                        // gamePanel.getListSim().get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.getListSim().get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.remove(gamePanel.getListSim().get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.getListSim().get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.get(gamePanel.getListSim().get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.getListSim().get(gamePanel.indexCurrentSim).indexLocationRuangan).bendaRuangan.size()
                        // - 1));
                        // gamePanel.getListSim().get(gamePanel.indexCurrentSim).canObtainItem(bendaT); //
                        // INI JANGAN DIHAPUS
                        // gamePanel.setGameState(gamePanel.getDialogState());
                        // gamePanel.getUi().setCurrentDialog"Tempat tidak cukup untuk meletakkan\nfurnitur
                        // atau Anda sudah memiliki\nfurnitur ini di ruangan!\nBerhasil memindahkan
                        // furnitur.";
                        // }
                        // }
                        // }
                        // }
                        // gamePanel.getListSim().get(gamePanel.indexCurrentSim).tempInt = -1;
                        // //
                        // gamePanel.getListSim().get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(0).bendaRuangan[1].worldX
                        // = 2 *gamePanel.tileSize;
                        // //
                        // gamePanel.getListSim().get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(0).bendaRuangan[1].worldY
                        // = 3 *gamePanel.tileSize;
                        // ------------------------------------BATAS------------------------------------
                    }
                }
                gamePanel.getUi().setInputText("");
                gamePanel.getUi().setInputTextDone(false);
            } else {
                gamePanel.getUi().setCharIndex(0);
                gamePanel.getUi().setCombinedText("");
                gamePanel.setGameState(gamePanel.getDialogState());
                gamePanel.getUi().setCurrentDialog("Nama tidak boleh kosong");
                gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).canObtainItem(tempFurnitur);
                gamePanel.getUi().setCommandNumber(0);
                gamePanel.getUi().setInputText("");
            }
            cursorSound();

        }

        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.getUi().setInputText("");
            gamePanel.getUi().setInputTextDone(false);
            gamePanel.getUi().setCommandNumber(0);
            gamePanel.setGameState(gamePanel.getInventoryState());
            gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).canObtainItem(tempFurnitur); // INI JANGAN DIHAPUS
        }
    }

    public void inputNamaRuanganState(int keyCode) {
        // input name
        if (gamePanel.getUi().getInputText().length() < 15) {
            if (keyCode == KeyEvent.VK_A) {
                gamePanel.getUi().addInputText("A");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_B) {
                gamePanel.getUi().addInputText("B");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_C) {
                gamePanel.getUi().addInputText("C");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_D) {
                gamePanel.getUi().addInputText("D");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_E) {
                gamePanel.getUi().addInputText("E");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_F) {
                gamePanel.getUi().addInputText("F");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_G) {
                gamePanel.getUi().addInputText("G");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_H) {
                gamePanel.getUi().addInputText("H");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_I) {
                gamePanel.getUi().addInputText("I");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_J) {
                gamePanel.getUi().addInputText("J");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_K) {
                gamePanel.getUi().addInputText("K");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_L) {
                gamePanel.getUi().addInputText("L");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_M) {
                gamePanel.getUi().addInputText("M");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_N) {
                gamePanel.getUi().addInputText("N");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_O) {
                gamePanel.getUi().addInputText("O");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_P) {
                gamePanel.getUi().addInputText("P");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_Q) {
                gamePanel.getUi().addInputText("Q");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_R) {
                gamePanel.getUi().addInputText("R");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_S) {
                gamePanel.getUi().addInputText("S");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_T) {
                gamePanel.getUi().addInputText("T");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_U) {
                gamePanel.getUi().addInputText("U");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_V) {
                gamePanel.getUi().addInputText("V");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_W) {
                gamePanel.getUi().addInputText("W");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_X) {
                gamePanel.getUi().addInputText("X");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_Y) {
                gamePanel.getUi().addInputText("Y");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_Z) {
                gamePanel.getUi().addInputText("Z");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_SPACE) {
                gamePanel.getUi().addInputText(" ");
                cursorSound();
            }
        }

        if (keyCode == KeyEvent.VK_BACK_SPACE && gamePanel.getUi().getInputText().length() > 0) {
            gamePanel.getUi().setInputText(gamePanel.getUi().getInputText().substring(0, gamePanel.getUi().getInputText().length() - 1));
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ENTER) {
            if (gamePanel.getUi().getInputText().length() > 0) {
                // nama tidak boleh sama
                boolean isRuanganNameExist = false;
                for (int i = 0; i < gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getRumah().getRuanganRumah().size(); i++) {
                    if (UtilityTool
                            .capitalizeFirstLetter(
                                    gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getRumah().getRuanganRumah().get(i).getName())
                            .equals(UtilityTool.capitalizeFirstLetter(gamePanel.getUi().getInputText()))) {
                        isRuanganNameExist = true;
                    }
                }
                if (isRuanganNameExist) {
                    gamePanel.getUi().setCharIndex(0);
                    gamePanel.getUi().setCombinedText("");
                    gamePanel.setGameState(gamePanel.getDialogState());
                    gamePanel.getUi().setCurrentDialog("Nama ruangan di rumah sudah ada!");
                    gamePanel.getUi().setInputText("");
                    gamePanel.getUi().setInputTextDone(false);
                    gamePanel.getUi().setCommandNumber(0);

                } else {
                    gamePanel.setGameState(gamePanel.getPlayState());
                    // System.out.println("TEST1");
                    String input = gamePanel.getUi().getInputText();
                    if (gamePanel.getUi().getCommandNumber() == 0) {
                        gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getRumah().upgradeRumah("up", input);
                        gamePanel.getUi().setCommandNumber(0);
                    } else if (gamePanel.getUi().getCommandNumber() == 1) {
                        // System.out.println("TEST2");
                        gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getRumah().upgradeRumah("down", input);
                        // System.out.println(gamePanel.getListSim().get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(0).down.name);
                        gamePanel.getUi().setCommandNumber(0);
                    } else if (gamePanel.getUi().getCommandNumber() == 2) {
                        gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getRumah().upgradeRumah("left", input);
                        gamePanel.getUi().setCommandNumber(0);
                    } else if (gamePanel.getUi().getCommandNumber() == 3) {
                        gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getRumah().upgradeRumah("right", input);
                        gamePanel.getUi().setCommandNumber(0);
                    }
                    gamePanel.getUi().setInputText("");
                    gamePanel.getUi().setInputTextDone(false);
                    // CEK RUANGAN APA SAJA DALAM RUMAH SIM
                    // for (Ruangan s :
                    // gamePanel.getListSim().get(gamePanel.indexCurrentSim).rumah.ruanganRumah){
                    // System.out.println(s.name);
                    // }
                }
            } else {
                gamePanel.getUi().setCharIndex(0);
                gamePanel.getUi().setCombinedText("");
                gamePanel.setGameState(gamePanel.getDialogState());
                gamePanel.getUi().setCurrentDialog("Nama tidak boleh kosong");
                gamePanel.getUi().setCommandNumber(0);
            }
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.getUi().setInputText("");
            gamePanel.getUi().setInputTextDone(false);
            gamePanel.getUi().setCommandNumber(0);
            gamePanel.setGameState(gamePanel.getUpgradeRumahState());
            cursorSound();
        }

    }

    public void inputKoordinatRumahSimState(int keyCode) {
        if (gamePanel.getUi().getInputText().length() < 5) {
            if (keyCode == KeyEvent.VK_1) {
                gamePanel.getUi().addInputText("1");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_2) {
                gamePanel.getUi().addInputText("2");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_3) {
                gamePanel.getUi().addInputText("3");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_4) {
                gamePanel.getUi().addInputText("4");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_5) {
                gamePanel.getUi().addInputText("5");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_6) {
                gamePanel.getUi().addInputText("6");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_7) {
                gamePanel.getUi().addInputText("7");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_8) {
                gamePanel.getUi().addInputText("8");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_9) {
                gamePanel.getUi().addInputText("9");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_0) {
                gamePanel.getUi().addInputText("0");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_COMMA) {
                gamePanel.getUi().addInputText(",");
                cursorSound();
            }
        }

        if (keyCode == KeyEvent.VK_BACK_SPACE && gamePanel.getUi().getInputText().length() > 0) {
            gamePanel.getUi().setInputText(gamePanel.getUi().getInputText().substring(0, gamePanel.getUi().getInputText().length() - 1));
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ENTER) {

            // check panjang input
            if (gamePanel.getUi().getInputText().length() > 0) {
                gamePanel.setGameState(gamePanel.getPlayState());

                String input = gamePanel.getUi().getInputText();
                int commaCounter = input.length() - input.replace(",", "").length();
                // check apakah input valid
                if (input.charAt(0) == ',' || input.charAt(input.length() - 1) == ',' || commaCounter != 1) {
                    gamePanel.getUi().setCharIndex(0);
                    gamePanel.getUi().setCombinedText("");
                    gamePanel.setGameState(gamePanel.getDialogState());
                    gamePanel.getUi().setCurrentDialog("Koordinat tidak valid!");
                    gamePanel.getListSim().remove(gamePanel.getListSim().size() - 1);
                    gamePanel.getUi().setCommandNumber(0);
                    gamePanel.getUi().setInputText("");
                } else {
                    int x = Integer.parseInt(input.substring(0, input.indexOf(",")));
                    int y = Integer.parseInt(input.substring(input.indexOf(",") + 1));
                    // check apakah koordinat range 1-6
                    if (x < 1 || x > 64 || y < 1 || y > 64) {
                        gamePanel.getUi().setCharIndex(0);
                        gamePanel.getUi().setCombinedText("");
                        gamePanel.setGameState(gamePanel.getDialogState());
                        gamePanel.getUi().setCurrentDialog("Koordinat harus berada pada range 1-64!");
                        gamePanel.getListSim().remove(gamePanel.getListSim().size() - 1);
                        gamePanel.getUi().setCommandNumber(0);
                        gamePanel.getUi().setInputText("");
                    } else {
                        // check apakah koordinat sudah ada rumah
                        boolean isExist = false;
                        for (int i = 0; i < gamePanel.getListRumah()[0].size(); i++) {
                            if (gamePanel.getListRumah()[0].get(i).getWorldX() == x * gamePanel.getTileSize()
                                    && gamePanel.getListRumah()[0].get(i).getWorldY() == y * gamePanel.getTileSize()) {
                                isExist = true;
                                break;
                            }
                        }
                        if (isExist) {
                            gamePanel.getUi().setCharIndex(0);
                            gamePanel.getUi().setCombinedText("");
                            gamePanel.setGameState(gamePanel.getDialogState());
                            gamePanel.getUi().setCurrentDialog("Koordinat sudah ditempati sim lain!");
                            gamePanel.getListSim().remove(gamePanel.getListSim().size() - 1);
                            gamePanel.getUi().setCommandNumber(0);
                            gamePanel.getUi().setInputText("");
                        } else {
                            gamePanel.getListSim().get(gamePanel.getListSim().size() - 1).getRumah().setColRumah(x);
                            gamePanel.getListSim().get(gamePanel.getListSim().size() - 1).getRumah().setWorldX(x * gamePanel.getTileSize());
                            gamePanel.getListSim().get(gamePanel.getListSim().size() - 1).getRumah().setRowRumah(y);
                            gamePanel.getListSim().get(gamePanel.getListSim().size() - 1).getRumah().setWorldY(y * gamePanel.getTileSize());
                            gamePanel.getListRumah()[0].add(gamePanel.getListSim().get(gamePanel.getListSim().size() - 1).getRumah());
                            // set sim to own rumah
                            gamePanel.getListSim().get(gamePanel.getListSim().size() - 1).setCurrentMap(1);
                            gamePanel.getListSim().get(
                                    gamePanel.getListSim().size() - 1).setIndexRumahYangDimasuki(gamePanel.getListRumah()[0].size()
                                            - 1);
                            gamePanel.getListSim().get(gamePanel.getListSim().size() - 1).setIndexLocationRuangan(0);
                            gamePanel.getListSim().get(gamePanel.getListSim().size() - 1).setCurrentLocation("Rumah "
                                    + gamePanel.getListSim().get(gamePanel.getListSim().size() - 1).getNama() + " ("
                                    + UtilityTool.capitalizeFirstLetter(
                                            gamePanel.getListSim().get(gamePanel.getListSim().size() - 1).getRumah().getRuanganRumah()
                                                    .get(0).getName())
                                    + ")");
                            gamePanel.getUi().setCharIndex(0);
                            gamePanel.getUi().setCombinedText("");
                            gamePanel.setGameState(gamePanel.getDialogState());
                            gamePanel.getUi().setCurrentDialog("Berhasil menambah sim.");
                            gamePanel.setIsOneSim(false);
                        }
                    }
                }
                gamePanel.getUi().setInputText("");
                gamePanel.getUi().setInputTextDone(false);
            } else {
                gamePanel.getUi().setCharIndex(0);
                gamePanel.getUi().setCombinedText("");
                gamePanel.setGameState(gamePanel.getDialogState());
                gamePanel.getUi().setCurrentDialog("Nama tidak boleh kosong");
                gamePanel.getListSim().remove(gamePanel.getListSim().size() - 1);
                gamePanel.getUi().setCommandNumber(0);
                gamePanel.getUi().setInputText("");
            }
            cursorSound();

        }

        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.getUi().setInputText("");
            gamePanel.getUi().setInputTextDone(false);
            gamePanel.getUi().setCommandNumber(0);
            gamePanel.setGameState(gamePanel.getAddSimState());
            gamePanel.getListSim().remove(gamePanel.getListSim().size() - 1); // INI JANGAN DIHAPUS
            cursorSound();
        }
    }

    public void addSimState(int keyCode) {
        if (gamePanel.getUi().getInputText().length() < 15) {
            if (keyCode == KeyEvent.VK_A) {
                gamePanel.getUi().addInputText("A");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_B) {
                gamePanel.getUi().addInputText("B");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_C) {
                gamePanel.getUi().addInputText("C");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_D) {
                gamePanel.getUi().addInputText("D");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_E) {
                gamePanel.getUi().addInputText("E");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_F) {
                gamePanel.getUi().addInputText("F");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_G) {
                gamePanel.getUi().addInputText("G");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_H) {
                gamePanel.getUi().addInputText("H");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_I) {
                gamePanel.getUi().addInputText("I");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_J) {
                gamePanel.getUi().addInputText("J");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_K) {
                gamePanel.getUi().addInputText("K");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_L) {
                gamePanel.getUi().addInputText("L");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_M) {
                gamePanel.getUi().addInputText("M");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_N) {
                gamePanel.getUi().addInputText("N");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_O) {
                gamePanel.getUi().addInputText("O");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_P) {
                gamePanel.getUi().addInputText("P");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_Q) {
                gamePanel.getUi().addInputText("Q");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_R) {
                gamePanel.getUi().addInputText("R");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_S) {
                gamePanel.getUi().addInputText("S");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_T) {
                gamePanel.getUi().addInputText("T");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_U) {
                gamePanel.getUi().addInputText("U");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_V) {
                gamePanel.getUi().addInputText("V");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_W) {
                gamePanel.getUi().addInputText("W");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_X) {
                gamePanel.getUi().addInputText("X");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_Y) {
                gamePanel.getUi().addInputText("Y");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_Z) {
                gamePanel.getUi().addInputText("Z");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_SPACE) {
                gamePanel.getUi().addInputText(" ");
                cursorSound();
            }
        }

        if (keyCode == KeyEvent.VK_BACK_SPACE && gamePanel.getUi().getInputText().length() > 0) {
            gamePanel.getUi().setInputText(gamePanel.getUi().getInputText().substring(0, gamePanel.getUi().getInputText().length() - 1));
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ENTER) {
            if (gamePanel.getUi().getInputText().length() > 0) {
                // name can't be same with other sim
                gamePanel.setGameState(gamePanel.getPlayState());
                String input = UtilityTool.capitalizeFirstLetter(gamePanel.getUi().getInputText());
                boolean isNameExist = false;
                for (int i = 0; i < gamePanel.getListSim().size(); i++) {
                    if (gamePanel.getListSim().get(i).getNama().equals(input)) {
                        isNameExist = true;
                    }
                }
                if (isNameExist) {
                    gamePanel.getUi().setCharIndex(0);
                    gamePanel.getUi().setCombinedText("");
                    gamePanel.setGameState(gamePanel.getDialogState());
                    gamePanel.getUi().setCurrentDialog("Nama sudah dimiliki sim lain!");
                    gamePanel.getUi().setCommandNumber(0);
                    gamePanel.getUi().setInputText("");
                    gamePanel.getUi().setInputTextDone(false);
                } else {
                    gamePanel.getListSim().add(new Sim(gamePanel, gamePanel.getKeyHandler()));
                    gamePanel.getListSim().get(gamePanel.getListSim().size() - 1).setNama(input);
                    gamePanel.setGameState(gamePanel.getInputKoordinatRumahSimState());
                    gamePanel.getUi().setInputText("");
                    gamePanel.getUi().setInputTextDone(false);
                }
            } else {
                gamePanel.getUi().setCharIndex(0);
                gamePanel.getUi().setCombinedText("");
                gamePanel.setGameState(gamePanel.getDialogState());
                gamePanel.getUi().setCurrentDialog("Nama tidak boleh kosong");
                gamePanel.getUi().setCommandNumber(0);
            }
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.getUi().setInputText("");
            gamePanel.getUi().setInputTextDone(false);
            gamePanel.getUi().setCommandNumber(0);
            gamePanel.setGameState(gamePanel.getPlayState());
            cursorSound();
        }
    }

    public void changeSimState(int keyCode) {
        int index = 0;
        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.setGameState(gamePanel.getPlayState());
            gamePanel.getUi().setListSimSlotCol(0);
            gamePanel.getUi().setListSimSlotRow(0);
            cursorSound();
        } else if (keyCode == KeyEvent.VK_UP) {
            if (gamePanel.getUi().getListSimSlotRow() > 0) {
                index = UI.getItemIndexOnSlot(gamePanel.getUi().getListSimSlotRow() - 1, gamePanel.getUi().getListSimSlotCol());
                if (index <= gamePanel.getListSim().size() - 1) {
                    gamePanel.getUi().setListSimSlotRow(gamePanel.getUi().getSimSlotRow()-1);
                    cursorSound();
                }
            } else {
                index = UI.getItemIndexOnSlot(2, gamePanel.getUi().getListSimSlotCol());
                if (index <= gamePanel.getListSim().size() - 1) {
                    gamePanel.getUi().setListSimSlotRow(2);
                }
            }
        } else if (keyCode == KeyEvent.VK_DOWN) {
            if (gamePanel.getUi().getListSimSlotRow() < 2) {
                index = UI.getItemIndexOnSlot(gamePanel.getUi().getListSimSlotRow() + 1, gamePanel.getUi().getListSimSlotCol());
                if (index <= gamePanel.getListSim().size() - 1) {
                    gamePanel.getUi().setListSimSlotRow(gamePanel.getUi().getSimSlotRow()+1);
                    cursorSound();
                }
            } else {
                index = UI.getItemIndexOnSlot(0, gamePanel.getUi().getListSimSlotCol());
                if (index <= gamePanel.getListSim().size() - 1) {
                    gamePanel.getUi().setListSimSlotRow(0);
                }
            }
        } else if (keyCode == KeyEvent.VK_LEFT) {
            if (gamePanel.getUi().getListSimSlotCol() > 0) {
                index = UI.getItemIndexOnSlot(gamePanel.getUi().getListSimSlotRow(), gamePanel.getUi().getListSimSlotCol() - 1);
                if (index <= gamePanel.getListSim().size() - 1) {
                    gamePanel.getUi().setListSimSlotCol(gamePanel.getUi().getSimSlotCol()-1);
                    cursorSound();
                }
            } else {
                index = UI.getItemIndexOnSlot(gamePanel.getUi().getListSimSlotRow(), 10);
                if (index <= gamePanel.getListSim().size() - 1) {
                    gamePanel.getUi().setListSimSlotCol(10);
                }
            }
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            if (gamePanel.getUi().getListSimSlotCol() < 10) {
                index = UI.getItemIndexOnSlot(gamePanel.getUi().getListSimSlotRow(), gamePanel.getUi().getListSimSlotCol() + 1);
                if (index <= gamePanel.getListSim().size() - 1) {
                    gamePanel.getUi().setListSimSlotCol(gamePanel.getUi().getSimSlotCol()+1);
                    cursorSound();
                }
            } else {
                index = UI.getItemIndexOnSlot(gamePanel.getUi().getListSimSlotRow(), 0);
                if (index <= gamePanel.getListSim().size() - 1) {
                    gamePanel.getUi().setListSimSlotCol(0);
                }
            }
        } else if (keyCode == KeyEvent.VK_ENTER) {
            int indexSim = UI.getItemIndexOnSlot(gamePanel.getUi().getListSimSlotRow(), gamePanel.getUi().getListSimSlotCol());
            gamePanel.setIndexCurrentSim(indexSim);
            gamePanel.getUi().setCharIndex(0);
            gamePanel.getUi().setCombinedText("");
            gamePanel.setGameState(gamePanel.getDialogState());
            gamePanel.getUi().setCurrentDialog("Sim telah diganti menjadi "
                    + gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getNama() + "!");
            cursorSound();
            gamePanel.getUi().setListSimSlotCol(0);
            gamePanel.getUi().setListSimSlotRow(0);
        }
    }

    public void resepState(int keyCode) {
        int index = 0;

        if (keyCode == KeyEvent.VK_ENTER) {
            enterPressed = true;
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.setGameState(gamePanel.getPlayState());
            cursorSound();
        } else if (keyCode == KeyEvent.VK_UP) {
            if (gamePanel.getUi().getKokiSlotRow() > 0) {
                index = UI.getItemIndexOnSlot(gamePanel.getUi().getKokiSlotRow() - 1, gamePanel.getUi().getKokiSlotCol());
                if (index <= gamePanel.getKokiTemp().getInventory().size() - 1) {
                    gamePanel.getUi().setKokiSlotRow(gamePanel.getUi().getKokiSlotRow()-1);
                    cursorSound();
                }
            } else {
                index = UI.getItemIndexOnSlot(2, gamePanel.getUi().getKokiSlotCol());
                if (index <= gamePanel.getKokiTemp().getInventory().size() - 1) {
                    gamePanel.getUi().setKokiSlotRow(2);
                }
            }
        } else if (keyCode == KeyEvent.VK_DOWN) {
            if (gamePanel.getUi().getKokiSlotRow() < 2) {
                index = UI.getItemIndexOnSlot(gamePanel.getUi().getKokiSlotRow() + 1, gamePanel.getUi().getKokiSlotCol());
                if (index <= gamePanel.getKokiTemp().getInventory().size() - 1) {
                    gamePanel.getUi().setKokiSlotRow(gamePanel.getUi().getKokiSlotRow()+1);
                    cursorSound();
                }
            } else {
                index = UI.getItemIndexOnSlot(0, gamePanel.getUi().getKokiSlotCol());
                if (index <= gamePanel.getKokiTemp().getInventory().size() - 1) {
                    gamePanel.getUi().setKokiSlotRow(0);
                }
            }
        } else if (keyCode == KeyEvent.VK_LEFT) {
            if (gamePanel.getUi().getKokiSlotCol() > 0) {
                index = UI.getItemIndexOnSlot(gamePanel.getUi().getKokiSlotRow(), gamePanel.getUi().getKokiSlotCol() - 1);
                if (index <= gamePanel.getKokiTemp().getInventory().size() - 1) {
                    gamePanel.getUi().setKokiSlotCol(gamePanel.getUi().getKokiSlotCol()-1);
                    cursorSound();
                }
            } else {
                index = UI.getItemIndexOnSlot(gamePanel.getUi().getKokiSlotRow(), 10);
                if (index <= gamePanel.getKokiTemp().getInventory().size() - 1) {
                    gamePanel.getUi().setKokiSlotCol(10);
                }
            }
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            if (gamePanel.getUi().getKokiSlotCol() < 10) {
                index = UI.getItemIndexOnSlot(gamePanel.getUi().getKokiSlotRow(), gamePanel.getUi().getKokiSlotCol() + 1);
                if (index <= gamePanel.getKokiTemp().getInventory().size() - 1) {
                    gamePanel.getUi().setKokiSlotCol(gamePanel.getUi().getKokiSlotCol()+1);
                    cursorSound();
                }
            } else {
                index = UI.getItemIndexOnSlot(gamePanel.getUi().getKokiSlotRow(), 0);
                if (index <= gamePanel.getKokiTemp().getInventory().size() - 1) {
                    gamePanel.getUi().setKokiSlotCol(0);
                }
            }
        }
    }

    public void timerState(int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.setGameState(gamePanel.getPlayState());
            cursorSound();
            gamePanel.getKeyHandler().threadTemp.interrupt();
            gamePanel.getUi().setDurasiTimer(0);
        }
    }

    public void gameOverState(int keyCode) {
        if (gamePanel.getIsOneSim()) {
            if (keyCode == KeyEvent.VK_ENTER) {
                gamePanel.getUi().setTitleScreenState(0);
                gamePanel.setGameState(gamePanel.getTitleState());
                cursorSound();
                gamePanel.getUi().setCommandNumber(0);
                isFirst = true;
            }
            if (keyCode == KeyEvent.VK_UP) {
                gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()-1);
                if (gamePanel.getUi().getCommandNumber() < 0) {
                    gamePanel.getUi().setCommandNumber(0);
                }
                cursorSound();
            } else if (keyCode == KeyEvent.VK_DOWN) {
                gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()+1);
                if (gamePanel.getUi().getCommandNumber() > 0) {
                    gamePanel.getUi().setCommandNumber(0);
                }
                cursorSound();
            }
        } else if (gamePanel.getListSim().size() >= 1) {
            if (keyCode == KeyEvent.VK_UP) {
                gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()-1);
                if (gamePanel.getUi().getCommandNumber() < 0) {
                    gamePanel.getUi().setCommandNumber(1);
                }
                cursorSound();
            } else if (keyCode == KeyEvent.VK_DOWN) {
                gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()+1);
                if (gamePanel.getUi().getCommandNumber() > 1) {
                    gamePanel.getUi().setCommandNumber(0);
                }
                cursorSound();
            } else if (keyCode == KeyEvent.VK_ENTER) {
                if (gamePanel.getUi().getCommandNumber() == 0) {
                    // gamePanel.setGameState(gamePanel.getPlayState());
                    if (gamePanel.getListSim().size() > 1) {
                        gamePanel.getListSim().remove(gamePanel.getIndexCurrentSim());
                        gamePanel.getListRumah()[0].remove(gamePanel.getIndexCurrentSim());
                        for (int i = 0; i < gamePanel.getListSim().size(); i++) {
                            if (gamePanel.getListSim().get(i).getIndexRumahYangDimasuki() != 999) {
                                if (gamePanel.getListSim().get(i).getIndexRumahYangDimasuki() > gamePanel.getIndexCurrentSim()) {
                                    gamePanel.getListSim().get(i).setIndexRumahYangDimasuki(gamePanel.getListSim().get(i).getIndexRumahYangDimasuki() - 1);
                                } else if (gamePanel.getListSim()
                                        .get(i).getIndexRumahYangDimasuki() == gamePanel.getIndexCurrentSim()) {
                                    gamePanel.getListSim().get(i).setIndexRumahYangDimasuki(i);
                                    gamePanel.getListSim().get(i).setDefaultValues();
                                }
                            }
                        }
                        gamePanel.setIndexCurrentSim(0);
                        for (int i = 0; i < gamePanel.getListSim().size(); i++) {
                            if (gamePanel.getListSim().get(i).getRumah().getIsCanUpgrade() == false) {
                                gamePanel.getListSim().get(i).getRumah().setIsLockUpgrade(false);
                                gamePanel.getListSim().get(i).getRumah().decRemainingTimeUpgrade(gamePanel.getUi().getTempDurasi());
                                gamePanel.getListSim().get(i).getRumah().setIsCanUpgradeToTrueAfter18Minutes();
                                gamePanel.getListSim().get(i).getRumah().setIsLockUpgrade(true);
                            }
                        }
                        for (int i = 0; i < gamePanel.getListSim().size(); i++) {
                            if (gamePanel.getListSim().get(i).getIsCanBuy() == false) {
                                gamePanel.getListSim().get(i).setIsLockBuy(false);
                                gamePanel.getListSim().get(i).setRemainingTimeBuy(gamePanel.getListSim().get(i).getRemainingTimeBuy() - gamePanel.getUi().getTempDurasi());
                                gamePanel.getListSim().get(i).setIsCanBuyToTrue();
                                gamePanel.getListSim().get(i).getRumah().setIsLockUpgrade(true);
                            }
                        }

                        // for (int i = gamePanel.indexCurrentSim; i < gamePanel.getListSim().size(); i++){
                        // if (gamePanel.getListSim().get(i).indexRumahYangDimasuki != 999){
                        // gamePanel.getListSim().get(i).indexRumahYangDimasuki--;
                        // }
                        // }
                    }
                    gamePanel.setGameState(gamePanel.getChangeSimState());
                } else {
                    gamePanel.getUi().setTitleScreenState(0);
                    gamePanel.setGameState(gamePanel.getTitleState());
                }
                cursorSound();
                gamePanel.getUi().setCommandNumber(0);
                isFirst = true;
            }
        }

    }

    // --------------------- TODO BATAS SUCI -------------------------
    public void inputDurasiTidurState(int keyCode) {
        if (gamePanel.getUi().getInputText().length() < 5) {
            if (keyCode == KeyEvent.VK_1) {
                gamePanel.getUi().addInputText("1");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_2) {
                gamePanel.getUi().addInputText("2");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_3) {
                gamePanel.getUi().addInputText("3");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_4) {
                gamePanel.getUi().addInputText("4");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_5) {
                gamePanel.getUi().addInputText("5");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_6) {
                gamePanel.getUi().addInputText("6");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_7) {
                gamePanel.getUi().addInputText("7");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_8) {
                gamePanel.getUi().addInputText("8");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_9) {
                gamePanel.getUi().addInputText("9");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_0) {
                gamePanel.getUi().addInputText("0");
                cursorSound();
            }
        }

        if (keyCode == KeyEvent.VK_BACK_SPACE && gamePanel.getUi().getInputText().length() > 0) {
            gamePanel.getUi().setInputText(gamePanel.getUi().getInputText().substring(0, gamePanel.getUi().getInputText().length() - 1));
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.getUi().setInputText("");
            gamePanel.getUi().setInputTextDone(false);
            gamePanel.getUi().setCommandNumber(0);
            gamePanel.setGameState(gamePanel.getPlayState());
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ENTER) {
            if (gamePanel.getUi().getInputText().length() > 0) {
                int durasi = Integer.parseInt(gamePanel.getUi().getInputText());
                gamePanel.setGameState(gamePanel.getPlayState());
                gamePanel.getUi().setTempDurasi(durasi);

                // timer state
                gamePanel.getUi().setDurasiTimer(durasi);
                gamePanel.getUi().setCurrentAksi("Tidur");
                gamePanel.gameState = gamePanel.timerState;
                gamePanel.getUi().setCurrentAksiDone(false);
                // efek
                gamePanel.getCurrentSim().setMood(gamePanel.getCurrentSim().getMood() + (durasi / 240) * 30);
                gamePanel.getCurrentSim().setKesehatan(gamePanel.getCurrentSim().getKesehatan() + (durasi / 240) * 20);
                if (gamePanel.getCurrentSim().getMood() > gamePanel.getCurrentSim().getMaxMood()) {
                    gamePanel.getCurrentSim().setMood(gamePanel.getCurrentSim().getMaxMood());
                }
                if (gamePanel.getCurrentSim().getKesehatan() > gamePanel.getCurrentSim().getMaxKesehatan()) {
                    gamePanel.getCurrentSim().setKesehatan(gamePanel.getCurrentSim().getMaxKesehatan());
                }
                if (gamePanel.getCurrentSim().getKekenyangan() > gamePanel.getCurrentSim().getMaxKekenyangan()) {
                    gamePanel.getCurrentSim().setKekenyangan(gamePanel.getCurrentSim().getMaxKekenyangan());
                }

                //nambah WorldTimeCounter
                gamePanel.incWorldTimeCounter(durasi);
                gamePanel.incWorldTimeSatuHariCounter(durasi);
                for (int i = 0; i < gamePanel.getListSim().size(); i++) {
                    gamePanel.getListSim().get(i).getPekerjaan().setWorldTimeCounterForStartJobAfterChangeJob(gamePanel.getListSim()
                        .get(i).getPekerjaan().getWorldTimeCounterForStartJobAfterChangeJob() + durasi);
                    gamePanel.getListSim().get(i).setEfekWaktuTidakTidurCounter(gamePanel.getListSim().get(i).getEfekWaktuTidakTidurCounter() + durasi);
                    if (gamePanel.getListSim().get(i).getIsUdahMakanDalamSatuHari()) {
                        gamePanel.getListSim().get(i).setEfekWaktuTidakBuangAirCounter(gamePanel.getListSim().get(i).getEfekWaktuTidakBuangAirCounter() + durasi);
                    }
                }
                // gamePanel.getUi().setelahAksi(durasi);

                // reset efek waktu tidak tidur
                gamePanel.getCurrentSim().setEfekWaktuTidakTidurCounter(0);

                threadTemp = gamePanel.getUi().startTimerThread(durasi);

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
                // for (int i = 0; i < gamePanel.getListSim().size(); i++) {
                // gamePanel.getListSim().get(i).pekerjaan.worldTimeCounterForStartJobAfterChangeJob
                // += durasi;
                // }
                // gamePanel.getUi().setelahAksi(durasi);

                // // reset efek waktu tidak tidur
                // gamePanel.getCurrentSim().efekWaktuTidakTidurCounter = 0;

                gamePanel.getUi().setInputText("");
                gamePanel.getUi().setCommandNumber(0);
            } else {
                gamePanel.getUi().setCharIndex(0);
                gamePanel.getUi().setCombinedText("");
                gamePanel.setGameState(gamePanel.getDialogState());
                gamePanel.getUi().setCurrentDialog("Input durasi tidak boleh kosong!");
                gamePanel.getUi().setCommandNumber(0);
                gamePanel.getUi().setInputText("");
            }
            cursorSound();
        }
    }

    public void inputDurasiNontonState(int keyCode) {
        if (gamePanel.getUi().getInputText().length() < 5) {
            if (keyCode == KeyEvent.VK_1) {
                gamePanel.getUi().addInputText("1");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_2) {
                gamePanel.getUi().addInputText("2");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_3) {
                gamePanel.getUi().addInputText("3");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_4) {
                gamePanel.getUi().addInputText("4");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_5) {
                gamePanel.getUi().addInputText("5");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_6) {
                gamePanel.getUi().addInputText("6");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_7) {
                gamePanel.getUi().addInputText("7");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_8) {
                gamePanel.getUi().addInputText("8");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_9) {
                gamePanel.getUi().addInputText("9");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_0) {
                gamePanel.getUi().addInputText("0");
                cursorSound();
            }
        }

        if (keyCode == KeyEvent.VK_BACK_SPACE && gamePanel.getUi().getInputText().length() > 0) {
            gamePanel.getUi().setInputText(gamePanel.getUi().getInputText().substring(0, gamePanel.getUi().getInputText().length() - 1));
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.getUi().setInputText("");
            gamePanel.getUi().setInputTextDone(false);
            gamePanel.getUi().setCommandNumber(0);
            gamePanel.setGameState(gamePanel.getPlayState());
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ENTER) {
            if (gamePanel.getUi().getInputText().length() > 0) {
                int durasi = Integer.parseInt(gamePanel.getUi().getInputText());
                gamePanel.setGameState(gamePanel.getPlayState());
                gamePanel.getUi().setTempDurasi(durasi);

                // timer state
                gamePanel.getUi().setDurasiTimer(durasi);
                gamePanel.getUi().setCurrentAksi("Nonton");
                gamePanel.gameState = gamePanel.timerState;
                gamePanel.getUi().setCurrentAksiDone(false);
                // efek
                gamePanel.getCurrentSim().setKekenyangan(gamePanel.getCurrentSim().getKekenyangan() - (durasi / 60) * 5);
                gamePanel.getCurrentSim().setMood(gamePanel.getCurrentSim().getMood() + (durasi / 60) * 15);
                gamePanel.getCurrentSim().setKesehatan(gamePanel.getCurrentSim().getKesehatan() - (durasi / 60) * 5);
                if (gamePanel.getCurrentSim().getKekenyangan() > 100) {
                    gamePanel.getCurrentSim().setKekenyangan(100);
                }
                if (gamePanel.getCurrentSim().getMood() > 100) {
                    gamePanel.getCurrentSim().setMood(100);;
                }
                if (gamePanel.getCurrentSim().getKesehatan() > 100) {
                    gamePanel.getCurrentSim().setKesehatan(100);
                }

                // nambah WorldTimeCounter
                gamePanel.worldTimeCounter += durasi;
                gamePanel.worldTimeSatuHariCounter += durasi;
                for (int i = 0; i < gamePanel.getListSim().size(); i++) {
                    gamePanel.getListSim().get(i).getPekerjaan().setWorldTimeCounterForStartJobAfterChangeJob(gamePanel.getListSim()
                        .get(i).getPekerjaan().getWorldTimeCounterForStartJobAfterChangeJob() + durasi);
                    gamePanel.getListSim().get(i).setEfekWaktuTidakTidurCounter(gamePanel.getListSim().get(i).getEfekWaktuTidakTidurCounter() + durasi);
                    if (gamePanel.getListSim().get(i).getIsUdahMakanDalamSatuHari()) {
                        gamePanel.getListSim().get(i).setEfekWaktuTidakBuangAirCounter(gamePanel.getListSim().get(i).getEfekWaktuTidakBuangAirCounter() + durasi);
                    }
                }
                threadTemp = gamePanel.getUi().startTimerThread(durasi);

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
                // for (int i = 0; i < gamePanel.getListSim().size(); i++) {
                // gamePanel.getListSim().get(i).pekerjaan.worldTimeCounterForStartJobAfterChangeJob
                // += durasi;
                // }
                // gamePanel.getUi().setelahAksi(durasi);

                gamePanel.getUi().setInputText("");
                gamePanel.getUi().setCommandNumber(0);
            } else {
                gamePanel.getUi().setCharIndex(0);
                gamePanel.getUi().setCombinedText("");
                gamePanel.setGameState(gamePanel.getDialogState());
                gamePanel.getUi().setCurrentDialog("Input durasi tidak boleh kosong!");
                gamePanel.getUi().setCommandNumber(0);
                gamePanel.getUi().setInputText("");
            }
            cursorSound();
        }
    }

    public void inputDurasiMandiState(int keyCode) {
        if (gamePanel.getUi().getInputText().length() < 5) {
            if (keyCode == KeyEvent.VK_1) {
                gamePanel.getUi().addInputText("1");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_2) {
                gamePanel.getUi().addInputText("2");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_3) {
                gamePanel.getUi().addInputText("3");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_4) {
                gamePanel.getUi().addInputText("4");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_5) {
                gamePanel.getUi().addInputText("5");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_6) {
                gamePanel.getUi().addInputText("6");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_7) {
                gamePanel.getUi().addInputText("7");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_8) {
                gamePanel.getUi().addInputText("8");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_9) {
                gamePanel.getUi().addInputText("9");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_0) {
                gamePanel.getUi().addInputText("0");
                cursorSound();
            }
        }

        if (keyCode == KeyEvent.VK_BACK_SPACE && gamePanel.getUi().getInputText().length() > 0) {
            gamePanel.getUi().setInputText(gamePanel.getUi().getInputText().substring(0, gamePanel.getUi().getInputText().length() - 1));
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.getUi().setInputText("");
            gamePanel.getUi().setInputTextDone(false);
            gamePanel.getUi().setCommandNumber(0);
            gamePanel.setGameState(gamePanel.getPlayState());
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ENTER) {
            if (gamePanel.getUi().getInputText().length() > 0) {
                int durasi = Integer.parseInt(gamePanel.getUi().getInputText());
                gamePanel.setGameState(gamePanel.getPlayState());
                gamePanel.getUi().setTempDurasi(durasi);

                // timer state
                gamePanel.getUi().setDurasiTimer(durasi);
                gamePanel.getUi().setCurrentAksi("Mandi");
                gamePanel.gameState = gamePanel.timerState;
                gamePanel.getUi().setCurrentAksiDone(false);
                // efek
                gamePanel.getCurrentSim().setKekenyangan(gamePanel.getCurrentSim().getKekenyangan() - (durasi / 30) * 5);
                gamePanel.getCurrentSim().setMood(gamePanel.getCurrentSim().getMood() + (durasi / 30) * 10);
                gamePanel.getCurrentSim().setKesehatan(gamePanel.getCurrentSim().getKesehatan() + (durasi / 30) * 10);
                if (gamePanel.getCurrentSim().getKekenyangan() > 100) {
                    gamePanel.getCurrentSim().setKekenyangan(100);
                }
                if (gamePanel.getCurrentSim().getMood() > 100) {
                    gamePanel.getCurrentSim().setMood(100);
                }
                if (gamePanel.getCurrentSim().getKesehatan() > 100) {
                    gamePanel.getCurrentSim().setKesehatan(100);
                }

                // nambah WorldTimeCounter
                gamePanel.worldTimeCounter += durasi;
                gamePanel.worldTimeSatuHariCounter += durasi;
                for (int i = 0; i < gamePanel.getListSim().size(); i++) {
                    gamePanel.getListSim().get(i).getPekerjaan().setWorldTimeCounterForStartJobAfterChangeJob(gamePanel.getListSim()
                        .get(i).getPekerjaan().getWorldTimeCounterForStartJobAfterChangeJob() + durasi);
                    gamePanel.getListSim().get(i).setEfekWaktuTidakTidurCounter(gamePanel.getListSim().get(i).getEfekWaktuTidakTidurCounter() + durasi);
                    if (gamePanel.getListSim().get(i).getIsUdahMakanDalamSatuHari()) {
                        gamePanel.getListSim().get(i).setEfekWaktuTidakBuangAirCounter(gamePanel.getListSim().get(i).getEfekWaktuTidakBuangAirCounter() + durasi);
                    }
                }
                threadTemp = gamePanel.getUi().startTimerThread(durasi);

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
                // for (int i = 0; i < gamePanel.getListSim().size(); i++) {
                // gamePanel.getListSim().get(i).pekerjaan.worldTimeCounterForStartJobAfterChangeJob
                // += durasi;
                // }
                // gamePanel.getUi().setelahAksi(durasi);

                gamePanel.getUi().setInputText("");
                gamePanel.getUi().setCommandNumber(0);
            } else {
                gamePanel.getUi().setCharIndex(0);
                gamePanel.getUi().setCombinedText("");
                gamePanel.setGameState(gamePanel.getDialogState());
                gamePanel.getUi().setCurrentDialog("Input durasi tidak boleh kosong!");
                gamePanel.getUi().setCommandNumber(0);
                gamePanel.getUi().setInputText("");
            }
            cursorSound();
        }
    }

    public void inputDurasiShalatState(int keyCode) {
        if (gamePanel.getUi().getInputText().length() < 5) {
            if (keyCode == KeyEvent.VK_1) {
                gamePanel.getUi().addInputText("1");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_2) {
                gamePanel.getUi().addInputText("2");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_3) {
                gamePanel.getUi().addInputText("3");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_4) {
                gamePanel.getUi().addInputText("4");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_5) {
                gamePanel.getUi().addInputText("5");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_6) {
                gamePanel.getUi().addInputText("6");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_7) {
                gamePanel.getUi().addInputText("7");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_8) {
                gamePanel.getUi().addInputText("8");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_9) {
                gamePanel.getUi().addInputText("9");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_0) {
                gamePanel.getUi().addInputText("0");
                cursorSound();
            }
        }

        if (keyCode == KeyEvent.VK_BACK_SPACE && gamePanel.getUi().getInputText().length() > 0) {
            gamePanel.getUi().setInputText(gamePanel.getUi().getInputText().substring(0, gamePanel.getUi().getInputText().length() - 1));
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.getUi().setInputText("");
            gamePanel.getUi().setInputTextDone(false);
            gamePanel.getUi().setCommandNumber(0);
            gamePanel.setGameState(gamePanel.getPlayState());
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ENTER) {
            if (gamePanel.getUi().getInputText().length() > 0) {
                int durasi = Integer.parseInt(gamePanel.getUi().getInputText());
                gamePanel.setGameState(gamePanel.getPlayState());
                gamePanel.getUi().setTempDurasi(durasi);

                // timer state
                gamePanel.getUi().setDurasiTimer(durasi);
                gamePanel.getUi().setCurrentAksi("Shalat");
                gamePanel.gameState = gamePanel.timerState;
                gamePanel.getUi().setCurrentAksiDone(false);
                // efek
                gamePanel.getCurrentSim().setMood(gamePanel.getCurrentSim().getMood() + (durasi / 5) * 5);
                gamePanel.getCurrentSim().setKekenyangan(gamePanel.getCurrentSim().getKekenyangan() - (durasi / 5) * 5);
                if (gamePanel.getCurrentSim().getMood() > 100) {
                    gamePanel.getCurrentSim().setMood(100);
                }
                if (gamePanel.getCurrentSim().getKekenyangan() > 100) {
                    gamePanel.getCurrentSim().setKekenyangan(100);
                }

                // nambah WorldTimeCounter
                gamePanel.worldTimeCounter += durasi;
                gamePanel.worldTimeSatuHariCounter += durasi;
                for (int i = 0; i < gamePanel.getListSim().size(); i++) {
                    gamePanel.getListSim().get(i).getPekerjaan().setWorldTimeCounterForStartJobAfterChangeJob(gamePanel.getListSim()
                        .get(i).getPekerjaan().getWorldTimeCounterForStartJobAfterChangeJob() + durasi);
                    gamePanel.getListSim().get(i).setEfekWaktuTidakTidurCounter(gamePanel.getListSim().get(i).getEfekWaktuTidakTidurCounter() + durasi);
                    if (gamePanel.getListSim().get(i).getIsUdahMakanDalamSatuHari()) {
                        gamePanel.getListSim().get(i).setEfekWaktuTidakBuangAirCounter(gamePanel.getListSim().get(i).getEfekWaktuTidakBuangAirCounter() + durasi);
                    }
                }
                threadTemp = gamePanel.getUi().startTimerThread(durasi);

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
                // for (int i = 0; i < gamePanel.getListSim().size(); i++) {
                // gamePanel.getListSim().get(i).pekerjaan.worldTimeCounterForStartJobAfterChangeJob
                // += durasi;
                // }
                // gamePanel.getUi().setelahAksi(durasi);

                gamePanel.getUi().setInputText("");
                gamePanel.getUi().setCommandNumber(0);
            } else {
                gamePanel.getUi().setCharIndex(0);
                gamePanel.getUi().setCombinedText("");
                gamePanel.setGameState(gamePanel.getDialogState());
                gamePanel.getUi().setCurrentDialog("Input durasi tidak boleh kosong!");
                gamePanel.getUi().setCommandNumber(0);
                gamePanel.getUi().setInputText("");
            }
            cursorSound();
        }
    }

    public void inputDurasiBacaBukuState(int keyCode) {
        if (gamePanel.getUi().getInputText().length() < 5) {
            if (keyCode == KeyEvent.VK_1) {
                gamePanel.getUi().addInputText("1");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_2) {
                gamePanel.getUi().addInputText("2");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_3) {
                gamePanel.getUi().addInputText("3");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_4) {
                gamePanel.getUi().addInputText("4");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_5) {
                gamePanel.getUi().addInputText("5");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_6) {
                gamePanel.getUi().addInputText("6");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_7) {
                gamePanel.getUi().addInputText("7");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_8) {
                gamePanel.getUi().addInputText("8");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_9) {
                gamePanel.getUi().addInputText("9");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_0) {
                gamePanel.getUi().addInputText("0");
                cursorSound();
            }
        }

        if (keyCode == KeyEvent.VK_BACK_SPACE && gamePanel.getUi().getInputText().length() > 0) {
            gamePanel.getUi().setInputText(gamePanel.getUi().getInputText().substring(0, gamePanel.getUi().getInputText().length() - 1));
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.getUi().setInputText("");
            gamePanel.getUi().setInputTextDone(false);
            gamePanel.getUi().setCommandNumber(0);
            gamePanel.setGameState(gamePanel.getPlayState());
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ENTER) {
            if (gamePanel.getUi().getInputText().length() > 0) {
                int durasi = Integer.parseInt(gamePanel.getUi().getInputText());
                gamePanel.setGameState(gamePanel.getPlayState());
                gamePanel.getUi().setTempDurasi(durasi);

                // timer state
                gamePanel.getUi().setDurasiTimer(durasi);
                gamePanel.getUi().setCurrentAksi("Baca Buku");
                gamePanel.gameState = gamePanel.timerState;
                gamePanel.getUi().setCurrentAksiDone(false);
                // efek
                gamePanel.getCurrentSim().setMood(gamePanel.getCurrentSim().getMood() + (durasi / 10) * 10);
                gamePanel.getCurrentSim().setKekenyangan(gamePanel.getCurrentSim().getKekenyangan() - (durasi / 10) * 3);
                if (gamePanel.getCurrentSim().getMood() > 100) {
                    gamePanel.getCurrentSim().setMood(100);
                }
                if (gamePanel.getCurrentSim().getKekenyangan() > 100) {
                    gamePanel.getCurrentSim().setKekenyangan(100);
                }

                // nambah WorldTimeCounter
                gamePanel.worldTimeCounter += durasi;
                gamePanel.worldTimeSatuHariCounter += durasi;
                for (int i = 0; i < gamePanel.getListSim().size(); i++) {
                    gamePanel.getListSim().get(i).getPekerjaan().setWorldTimeCounterForStartJobAfterChangeJob(gamePanel.getListSim()
                        .get(i).getPekerjaan().getWorldTimeCounterForStartJobAfterChangeJob() + durasi);
                    gamePanel.getListSim().get(i).setEfekWaktuTidakTidurCounter(gamePanel.getListSim().get(i).getEfekWaktuTidakTidurCounter() + durasi);
                    if (gamePanel.getListSim().get(i).getIsUdahMakanDalamSatuHari()) {
                        gamePanel.getListSim().get(i).setEfekWaktuTidakBuangAirCounter(gamePanel.getListSim().get(i).getEfekWaktuTidakBuangAirCounter() + durasi);
                    }
                }
                threadTemp = gamePanel.getUi().startTimerThread(durasi);

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
                // for (int i = 0; i < gamePanel.getListSim().size(); i++) {
                // gamePanel.getListSim().get(i).pekerjaan.worldTimeCounterForStartJobAfterChangeJob
                // += durasi;
                // }
                // gamePanel.getUi().setelahAksi(durasi);

                gamePanel.getUi().setInputText("");
                gamePanel.getUi().setCommandNumber(0);
            } else {
                gamePanel.getUi().setCharIndex(0);
                gamePanel.getUi().setCombinedText("");
                gamePanel.setGameState(gamePanel.getDialogState());
                gamePanel.getUi().setCurrentDialog("Input durasi tidak boleh kosong!");
                gamePanel.getUi().setCommandNumber(0);
                gamePanel.getUi().setInputText("");
            }
            cursorSound();
        }
    }

    public void inputDurasiRadioState(int keyCode) {
        if (gamePanel.getUi().getInputText().length() < 5) {
            if (keyCode == KeyEvent.VK_1) {
                gamePanel.getUi().addInputText("1");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_2) {
                gamePanel.getUi().addInputText("2");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_3) {
                gamePanel.getUi().addInputText("3");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_4) {
                gamePanel.getUi().addInputText("4");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_5) {
                gamePanel.getUi().addInputText("5");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_6) {
                gamePanel.getUi().addInputText("6");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_7) {
                gamePanel.getUi().addInputText("7");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_8) {
                gamePanel.getUi().addInputText("8");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_9) {
                gamePanel.getUi().addInputText("9");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_0) {
                gamePanel.getUi().addInputText("0");
                cursorSound();
            }
        }

        if (keyCode == KeyEvent.VK_BACK_SPACE && gamePanel.getUi().getInputText().length() > 0) {
            gamePanel.getUi().setInputText(gamePanel.getUi().getInputText().substring(0, gamePanel.getUi().getInputText().length() - 1));
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.getUi().setInputText("");
            gamePanel.getUi().setInputTextDone(false);
            gamePanel.getUi().setCommandNumber(0);
            gamePanel.setGameState(gamePanel.getPlayState());
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ENTER) {
            if (gamePanel.getUi().getInputText().length() > 0) {
                int durasi = Integer.parseInt(gamePanel.getUi().getInputText());
                gamePanel.setGameState(gamePanel.getPlayState());
                gamePanel.getUi().setTempDurasi(durasi);

                // timer state
                gamePanel.getUi().setDurasiTimer(durasi);
                gamePanel.getUi().setCurrentAksi("Setel Radio");
                gamePanel.gameState = gamePanel.timerState;
                gamePanel.getUi().setCurrentAksiDone(false);
                gamePanel.getCurrentSim().setMood(gamePanel.getCurrentSim().getMood() + (durasi / 10) * 10);
                gamePanel.getCurrentSim().setKekenyangan(gamePanel.getCurrentSim().getKekenyangan() - (durasi / 30) * 1);
                if (gamePanel.getCurrentSim().getMood() > 100) {
                    gamePanel.getCurrentSim().setMood(100);
                }
                if (gamePanel.getCurrentSim().getKekenyangan() > 100) {
                    gamePanel.getCurrentSim().setKekenyangan(100);
                }
                gamePanel.worldTimeCounter += durasi;
                gamePanel.worldTimeSatuHariCounter += durasi;
                for (int i = 0; i < gamePanel.getListSim().size(); i++) {
                    gamePanel.getListSim().get(i).getPekerjaan().setWorldTimeCounterForStartJobAfterChangeJob(gamePanel.getListSim()
                        .get(i).getPekerjaan().getWorldTimeCounterForStartJobAfterChangeJob() + durasi);
                    gamePanel.getListSim().get(i).setEfekWaktuTidakTidurCounter(gamePanel.getListSim().get(i).getEfekWaktuTidakTidurCounter() + durasi);
                    if (gamePanel.getListSim().get(i).getIsUdahMakanDalamSatuHari()) {
                        gamePanel.getListSim().get(i).setEfekWaktuTidakBuangAirCounter(gamePanel.getListSim().get(i).getEfekWaktuTidakBuangAirCounter() + durasi);
                    }
                }
                threadTemp = gamePanel.getUi().startTimerThread(durasi);

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
                // for (int i = 0; i < gamePanel.getListSim().size(); i++) {
                // gamePanel.getListSim().get(i).pekerjaan.worldTimeCounterForStartJobAfterChangeJob
                // += durasi;
                // }
                // gamePanel.getUi().setelahAksi(durasi);

                gamePanel.getUi().setInputText("");
                gamePanel.getUi().setCommandNumber(0);
            } else {
                gamePanel.getUi().setCharIndex(0);
                gamePanel.getUi().setCombinedText("");
                gamePanel.setGameState(gamePanel.getDialogState());
                gamePanel.getUi().setCurrentDialog("Input durasi tidak boleh kosong!");
                gamePanel.getUi().setCommandNumber(0);
                gamePanel.getUi().setInputText("");
            }
            cursorSound();
        }
    }

    public void inputDurasiSiramTanamanState(int keyCode) {
        if (gamePanel.getUi().getInputText().length() < 5) {
            if (keyCode == KeyEvent.VK_1) {
                gamePanel.getUi().addInputText("1");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_2) {
                gamePanel.getUi().addInputText("2");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_3) {
                gamePanel.getUi().addInputText("3");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_4) {
                gamePanel.getUi().addInputText("4");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_5) {
                gamePanel.getUi().addInputText("5");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_6) {
                gamePanel.getUi().addInputText("6");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_7) {
                gamePanel.getUi().addInputText("7");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_8) {
                gamePanel.getUi().addInputText("8");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_9) {
                gamePanel.getUi().addInputText("9");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_0) {
                gamePanel.getUi().addInputText("0");
                cursorSound();
            }
        }

        if (keyCode == KeyEvent.VK_BACK_SPACE && gamePanel.getUi().getInputText().length() > 0) {
            gamePanel.getUi().setInputText(gamePanel.getUi().getInputText().substring(0, gamePanel.getUi().getInputText().length() - 1));
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.getUi().setInputText("");
            gamePanel.getUi().setInputTextDone(false);
            gamePanel.getUi().setCommandNumber(0);
            gamePanel.setGameState(gamePanel.getPlayState());
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ENTER) {
            if (gamePanel.getUi().getInputText().length() > 0) {
                int durasi = Integer.parseInt(gamePanel.getUi().getInputText());
                gamePanel.setGameState(gamePanel.getPlayState());
                gamePanel.getUi().setTempDurasi(durasi);

                // timer state
                gamePanel.getUi().setDurasiTimer(durasi);
                gamePanel.getUi().setCurrentAksi("Siram Tanaman");
                gamePanel.gameState = gamePanel.timerState;
                gamePanel.getUi().setCurrentAksiDone(false);
                // efek
                gamePanel.getCurrentSim().setMood(gamePanel.getCurrentSim().getMood() + (durasi / 10) * 10);
                gamePanel.getCurrentSim().setKesehatan(gamePanel.getCurrentSim().getKesehatan() + (durasi / 10) * 5);
                gamePanel.getCurrentSim().setKekenyangan(gamePanel.getCurrentSim().getKekenyangan() - (durasi / 10) * 5);
                if (gamePanel.getCurrentSim().getMood() > gamePanel.getCurrentSim().getMaxMood()) {
                    gamePanel.getCurrentSim().setMood(gamePanel.getCurrentSim().getMaxMood());
                }
                if (gamePanel.getCurrentSim().getKesehatan() > gamePanel.getCurrentSim().getMaxKesehatan()) {
                    gamePanel.getCurrentSim().setKesehatan(gamePanel.getCurrentSim().getMaxKesehatan());
                }
                if (gamePanel.getCurrentSim().getKekenyangan() > gamePanel.getCurrentSim().getMaxKekenyangan()) {
                    gamePanel.getCurrentSim().setKekenyangan(gamePanel.getCurrentSim().getMaxKekenyangan());
                }

                // nambah WorldTimeCounter
                gamePanel.worldTimeCounter += durasi;
                gamePanel.worldTimeSatuHariCounter += durasi;
                for (int i = 0; i < gamePanel.getListSim().size(); i++) {
                    gamePanel.getListSim().get(i).getPekerjaan().setWorldTimeCounterForStartJobAfterChangeJob(gamePanel.getListSim()
                        .get(i).getPekerjaan().getWorldTimeCounterForStartJobAfterChangeJob() + durasi);
                    gamePanel.getListSim().get(i).setEfekWaktuTidakTidurCounter(gamePanel.getListSim().get(i).getEfekWaktuTidakTidurCounter() + durasi);
                    if (gamePanel.getListSim().get(i).getIsUdahMakanDalamSatuHari()) {
                        gamePanel.getListSim().get(i).setEfekWaktuTidakBuangAirCounter(gamePanel.getListSim().get(i).getEfekWaktuTidakBuangAirCounter() + durasi);
                    }
                }
                threadTemp = gamePanel.getUi().startTimerThread(durasi);

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
                // for (int i = 0; i < gamePanel.getListSim().size(); i++) {
                // gamePanel.getListSim().get(i).pekerjaan.worldTimeCounterForStartJobAfterChangeJob
                // += durasi;
                // }
                // gamePanel.getUi().setelahAksi(durasi);

                gamePanel.getUi().setInputText("");
                gamePanel.getUi().setCommandNumber(0);
            } else {
                gamePanel.getUi().setCharIndex(0);
                gamePanel.getUi().setCombinedText("");
                gamePanel.setGameState(gamePanel.getDialogState());
                gamePanel.getUi().setCurrentDialog("Input durasi tidak boleh kosong!");
                gamePanel.getUi().setCommandNumber(0);
                gamePanel.getUi().setInputText("");
            }
            cursorSound();
        }
    }

    public void inputDurasiMainGameState(int keyCode) {
        if (gamePanel.getUi().getInputText().length() < 5) {
            if (keyCode == KeyEvent.VK_1) {
                gamePanel.getUi().addInputText("1");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_2) {
                gamePanel.getUi().addInputText("2");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_3) {
                gamePanel.getUi().addInputText("3");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_4) {
                gamePanel.getUi().addInputText("4");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_5) {
                gamePanel.getUi().addInputText("5");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_6) {
                gamePanel.getUi().addInputText("6");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_7) {
                gamePanel.getUi().addInputText("7");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_8) {
                gamePanel.getUi().addInputText("8");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_9) {
                gamePanel.getUi().addInputText("9");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_0) {
                gamePanel.getUi().addInputText("0");
                cursorSound();
            }
        }

        if (keyCode == KeyEvent.VK_BACK_SPACE && gamePanel.getUi().getInputText().length() > 0) {
            gamePanel.getUi().setInputText(gamePanel.getUi().getInputText().substring(0, gamePanel.getUi().getInputText().length() - 1));
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.getUi().setInputText("");
            gamePanel.getUi().setInputTextDone(false);
            gamePanel.getUi().setCommandNumber(0);
            gamePanel.setGameState(gamePanel.getPlayState());
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ENTER) {
            if (gamePanel.getUi().getInputText().length() > 0) {
                int durasi = Integer.parseInt(gamePanel.getUi().getInputText());
                gamePanel.setGameState(gamePanel.getPlayState());
                gamePanel.getUi().setTempDurasi(durasi);

                // timer state
                gamePanel.getUi().setDurasiTimer(durasi);
                gamePanel.getUi().setCurrentAksi("Main Game");
                gamePanel.gameState = gamePanel.timerState;
                gamePanel.getUi().setCurrentAksiDone(false);
                // efek
                gamePanel.getCurrentSim().setMood(gamePanel.getCurrentSim().getMood() + (durasi / 30) * 30);
                gamePanel.getCurrentSim().setKesehatan(gamePanel.getCurrentSim().getKesehatan() - (durasi / 30) * 5);
                gamePanel.getCurrentSim().setKekenyangan(gamePanel.getCurrentSim().getKekenyangan() - (durasi / 30) * 10);
                if (gamePanel.getCurrentSim().getMood() > gamePanel.getCurrentSim().getMaxMood()) {
                    gamePanel.getCurrentSim().setMood(gamePanel.getCurrentSim().getMaxMood());
                }
                if (gamePanel.getCurrentSim().getKesehatan() > gamePanel.getCurrentSim().getMaxKesehatan()) {
                    gamePanel.getCurrentSim().setKesehatan(gamePanel.getCurrentSim().getMaxKesehatan());
                }
                if (gamePanel.getCurrentSim().getKekenyangan() > gamePanel.getCurrentSim().getMaxKekenyangan()) {
                    gamePanel.getCurrentSim().setKekenyangan(gamePanel.getCurrentSim().getMaxKekenyangan());
                }

                // nambah WorldTimeCounter
                gamePanel.worldTimeCounter += durasi;
                gamePanel.worldTimeSatuHariCounter += durasi;
                for (int i = 0; i < gamePanel.getListSim().size(); i++) {
                    gamePanel.getListSim().get(i).getPekerjaan().setWorldTimeCounterForStartJobAfterChangeJob(gamePanel.getListSim()
                        .get(i).getPekerjaan().getWorldTimeCounterForStartJobAfterChangeJob() + durasi);
                    gamePanel.getListSim().get(i).setEfekWaktuTidakTidurCounter(gamePanel.getListSim().get(i).getEfekWaktuTidakTidurCounter() + durasi);
                    if (gamePanel.getListSim().get(i).getIsUdahMakanDalamSatuHari()) {
                        gamePanel.getListSim().get(i).setEfekWaktuTidakBuangAirCounter(gamePanel.getListSim().get(i).getEfekWaktuTidakBuangAirCounter() + durasi);
                    }
                }
                threadTemp = gamePanel.getUi().startTimerThread(durasi);

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
                // for (int i = 0; i < gamePanel.getListSim().size(); i++) {
                // gamePanel.getListSim().get(i).pekerjaan.worldTimeCounterForStartJobAfterChangeJob
                // += durasi;
                // }
                // gamePanel.getUi().setelahAksi(durasi);

                gamePanel.getUi().setInputText("");
                gamePanel.getUi().setCommandNumber(0);
            } else {
                gamePanel.getUi().setCharIndex(0);
                gamePanel.getUi().setCombinedText("");
                gamePanel.setGameState(gamePanel.getDialogState());
                gamePanel.getUi().setCurrentDialog("Input durasi tidak boleh kosong!");
                gamePanel.getUi().setCommandNumber(0);
                gamePanel.getUi().setInputText("");
            }
            cursorSound();
        }
    }

    public void inputDurasiOlahragaState(int keyCode) {
        if (gamePanel.getUi().getInputText().length() < 5) {
            if (keyCode == KeyEvent.VK_1) {
                gamePanel.getUi().addInputText("1");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_2) {
                gamePanel.getUi().addInputText("2");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_3) {
                gamePanel.getUi().addInputText("3");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_4) {
                gamePanel.getUi().addInputText("4");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_5) {
                gamePanel.getUi().addInputText("5");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_6) {
                gamePanel.getUi().addInputText("6");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_7) {
                gamePanel.getUi().addInputText("7");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_8) {
                gamePanel.getUi().addInputText("8");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_9) {
                gamePanel.getUi().addInputText("9");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_0) {
                gamePanel.getUi().addInputText("0");
                cursorSound();
            }
        }

        if (keyCode == KeyEvent.VK_BACK_SPACE && gamePanel.getUi().getInputText().length() > 0) {
            gamePanel.getUi().setInputText(gamePanel.getUi().getInputText().substring(0, gamePanel.getUi().getInputText().length() - 1));
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.getUi().setInputText("");
            gamePanel.getUi().setInputTextDone(false);
            gamePanel.getUi().setCommandNumber(0);
            gamePanel.setGameState(gamePanel.getPlayState());
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ENTER) {
            if (gamePanel.getUi().getInputText().length() > 0) {
                int durasi = Integer.parseInt(gamePanel.getUi().getInputText());
                gamePanel.setGameState(gamePanel.getPlayState());
                gamePanel.getUi().setTempDurasi(durasi);

                // timer state
                gamePanel.getUi().setDurasiTimer(durasi);
                gamePanel.getUi().setCurrentAksi("Olahraga");
                gamePanel.gameState = gamePanel.timerState;
                gamePanel.getUi().setCurrentAksiDone(false);
                gamePanel.getCurrentSim().setMood(gamePanel.getCurrentSim().getMood() + (durasi / 20) * 10);
                gamePanel.getCurrentSim().setKesehatan(gamePanel.getCurrentSim().getKesehatan() + (durasi / 20) * 5);
                gamePanel.getCurrentSim().setKekenyangan(gamePanel.getCurrentSim().getKekenyangan() - (durasi / 20) * 5);
                if (gamePanel.getCurrentSim().getMood() > gamePanel.getCurrentSim().getMaxMood()) {
                    gamePanel.getCurrentSim().setMood(gamePanel.getCurrentSim().getMaxMood());
                }
                if (gamePanel.getCurrentSim().getKesehatan() > gamePanel.getCurrentSim().getMaxKesehatan()) {
                    gamePanel.getCurrentSim().setKesehatan(gamePanel.getCurrentSim().getMaxKesehatan());
                }
                if (gamePanel.getCurrentSim().getKekenyangan() > gamePanel.getCurrentSim().getMaxKekenyangan()) {
                    gamePanel.getCurrentSim().setKekenyangan(gamePanel.getCurrentSim().getMaxKekenyangan());
                }
                gamePanel.worldTimeCounter += durasi;
                gamePanel.worldTimeSatuHariCounter += durasi;
                for (int i = 0; i < gamePanel.getListSim().size(); i++) {
                    gamePanel.getListSim().get(i).getPekerjaan().setWorldTimeCounterForStartJobAfterChangeJob(gamePanel.getListSim()
                        .get(i).getPekerjaan().getWorldTimeCounterForStartJobAfterChangeJob() + durasi);
                    gamePanel.getListSim().get(i).setEfekWaktuTidakTidurCounter(gamePanel.getListSim().get(i).getEfekWaktuTidakTidurCounter() + durasi);
                    if (gamePanel.getListSim().get(i).getIsUdahMakanDalamSatuHari()) {
                        gamePanel.getListSim().get(i).setEfekWaktuTidakBuangAirCounter(gamePanel.getListSim().get(i).getEfekWaktuTidakBuangAirCounter() + durasi);
                    }
                }
                threadTemp = gamePanel.getUi().startTimerThread(durasi);

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
                // for (int i = 0; i < gamePanel.getListSim().size(); i++) {
                // gamePanel.getListSim().get(i).pekerjaan.worldTimeCounterForStartJobAfterChangeJob
                // += durasi;
                // }
                // gamePanel.getUi().setelahAksi(durasi);

                gamePanel.getUi().setInputText("");
                gamePanel.getUi().setCommandNumber(0);
            } else {
                gamePanel.getUi().setCharIndex(0);
                gamePanel.getUi().setCombinedText("");
                gamePanel.setGameState(gamePanel.getDialogState());
                gamePanel.getUi().setCurrentDialog("Input durasi tidak boleh kosong!");
                gamePanel.getUi().setCommandNumber(0);
                gamePanel.getUi().setInputText("");
            }
            cursorSound();
        }
    }

    public void inputDurasiKerjaState(int keyCode) {
        if (gamePanel.getUi().getInputText().length() < 5) {
            if (keyCode == KeyEvent.VK_1) {
                gamePanel.getUi().addInputText("1");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_2) {
                gamePanel.getUi().addInputText("2");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_3) {
                gamePanel.getUi().addInputText("3");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_4) {
                gamePanel.getUi().addInputText("4");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_5) {
                gamePanel.getUi().addInputText("5");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_6) {
                gamePanel.getUi().addInputText("6");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_7) {
                gamePanel.getUi().addInputText("7");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_8) {
                gamePanel.getUi().addInputText("8");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_9) {
                gamePanel.getUi().addInputText("9");
                cursorSound();
            } else if (keyCode == KeyEvent.VK_0) {
                gamePanel.getUi().addInputText("0");
                cursorSound();
            }
        }

        if (keyCode == KeyEvent.VK_BACK_SPACE && gamePanel.getUi().getInputText().length() > 0) {
            gamePanel.getUi().setInputText(gamePanel.getUi().getInputText().substring(0, gamePanel.getUi().getInputText().length() - 1));
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.getUi().setInputText("");
            gamePanel.getUi().setInputTextDone(false);
            gamePanel.getUi().setCommandNumber(0);
            gamePanel.gameState = gamePanel.kerjaState;
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ENTER) {
            if (gamePanel.getUi().getInputText().length() > 0) {
                int durasi = Integer.parseInt(gamePanel.getUi().getInputText());
                gamePanel.setGameState(gamePanel.getPlayState());
                gamePanel.getUi().setTempDurasi(durasi);

                // timer state
                gamePanel.getUi().setDurasiTimer(durasi);
                gamePanel.getUi().setCurrentAksi("Kerja");
                gamePanel.gameState = gamePanel.timerState;
                gamePanel.getUi().setCurrentAksiDone(false);

                // efek
                gamePanel.getCurrentSim().setMood(gamePanel.getCurrentSim().getMood() - (durasi / 30) * 10);
                gamePanel.getCurrentSim().setKekenyangan(gamePanel.getCurrentSim().getKekenyangan() - (durasi / 30) * 10);
                gamePanel.getCurrentSim().getPekerjaan().setTotalDurasiKerjaPerPekerjaan(gamePanel.getCurrentSim()
                    .getPekerjaan().getTotalDurasiKerjaPerPekerjaan() + durasi);
                gamePanel.getCurrentSim().getPekerjaan().setDurasiKerjaYangBelumDigaji(gamePanel.getCurrentSim()
                    .getPekerjaan().getDurasiKerjaYangBelumDigaji() + durasi);
                if (gamePanel.getCurrentSim().getPekerjaan().getDurasiKerjaYangBelumDigaji() >= 240) {
                    gamePanel.getCurrentSim().setUang(gamePanel.getCurrentSim().getUang() + gamePanel.getCurrentSim().getPekerjaan().getGaji()[gamePanel
                            .getCurrentSim().getPekerjaan().getIndexPekerjaan()]
                            * (gamePanel.getCurrentSim().getPekerjaan().getDurasiKerjaYangBelumDigaji() / 240));
                    gamePanel.getCurrentSim().getPekerjaan().setDurasiKerjaYangBelumDigaji(gamePanel.getCurrentSim()
                        .getPekerjaan().getDurasiKerjaYangBelumDigaji() - 240 * (durasi / 240));
                }
                if (gamePanel.getCurrentSim().getMood() > gamePanel.getCurrentSim().getMaxMood()) {
                    gamePanel.getCurrentSim().setMood(gamePanel.getCurrentSim().getMaxMood());
                }
                if (gamePanel.getCurrentSim().getKesehatan() > gamePanel.getCurrentSim().getMaxKesehatan()) {
                    gamePanel.getCurrentSim().setKesehatan(gamePanel.getCurrentSim().getMaxKesehatan());
                }
                if (gamePanel.getCurrentSim().getKekenyangan() > gamePanel.getCurrentSim().getMaxKekenyangan()) {
                    gamePanel.getCurrentSim().setKekenyangan(gamePanel.getCurrentSim().getMaxKekenyangan());
                }
                // nambah WorldTimeCounter
                gamePanel.worldTimeCounter += durasi;
                gamePanel.worldTimeSatuHariCounter += durasi;
                for (int i = 0; i < gamePanel.getListSim().size(); i++) {
                    gamePanel.getListSim().get(i).getPekerjaan().setWorldTimeCounterForStartJobAfterChangeJob(gamePanel.getListSim().get(i)
                        .getPekerjaan().getWorldTimeCounterForStartJobAfterChangeJob() + durasi);
                    gamePanel.getListSim().get(i).setEfekWaktuTidakTidurCounter(gamePanel.getListSim().get(i).getEfekWaktuTidakTidurCounter() + durasi);
                    if (gamePanel.getListSim().get(i).getIsUdahMakanDalamSatuHari()) {
                        gamePanel.getListSim().get(i).setEfekWaktuTidakBuangAirCounter(gamePanel.getListSim().get(i).getEfekWaktuTidakBuangAirCounter() + durasi);
                    }
                }

                threadTemp = gamePanel.getUi().startTimerThread(durasi);

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
                // for (int i = 0; i < gamePanel.getListSim().size(); i++) {
                // gamePanel.getListSim().get(i).pekerjaan.worldTimeCounterForStartJobAfterChangeJob
                // += durasi;
                // }
                // gamePanel.getUi().setelahAksi(durasi);

                gamePanel.getUi().setInputText("");
                gamePanel.getUi().setCommandNumber(0);
            } else {
                gamePanel.getUi().setCharIndex(0);
                gamePanel.getUi().setCombinedText("");
                gamePanel.setGameState(gamePanel.getDialogState());
                gamePanel.getUi().setCurrentDialog("Input durasi tidak boleh kosong!");
                gamePanel.getUi().setCommandNumber(0);
                gamePanel.getUi().setInputText("");
            }
            cursorSound();
        }
    }

    public void gantiPekerjaanState(int keyCode) {
        if (keyCode == KeyEvent.VK_ENTER) {
            gamePanel.setGameState(gamePanel.getPlayState());
            gamePanel.getUi().setCharIndex(0);
            gamePanel.getUi().setCombinedText("");
            gamePanel.setGameState(gamePanel.getDialogState());
            // cek pekerjaan sama seperti sebelumnya
            if (gamePanel.getCurrentSim().getPekerjaan().getIndexPekerjaan() == gamePanel.getUi().getCommandNumber()) {
                gamePanel.getUi().setCurrentDialog("Pekerjaan tidak berubah.");
                cursorSound();
                gamePanel.getUi().setCommandNumber(0);
                return;
            } else {
                if (gamePanel.getCurrentSim().getUang() >= (int) gamePanel
                        .getCurrentSim().getPekerjaan().getGaji()[gamePanel.getUi().getCommandNumber()] / 2) {
                    gamePanel.getCurrentSim().getPekerjaan().setIndexPekerjaan(gamePanel.getUi().getCommandNumber());
                    gamePanel.getCurrentSim().setUang(gamePanel.getCurrentSim().getUang() - (int) gamePanel
                            .getCurrentSim().getPekerjaan().getGaji()[gamePanel.getUi().getCommandNumber()] / 2);
                    gamePanel.getUi().setCurrentDialog("Pekerjaan berhasil diganti menjadi\n" + gamePanel
                            .getCurrentSim().getPekerjaan().getListPekerjaan()[gamePanel.getCurrentSim().getPekerjaan().getIndexPekerjaan()]
                            + ".");
                    cursorSound();
                    gamePanel.getUi().setCommandNumber(0);
                    gamePanel.getCurrentSim().getPekerjaan().setDurasiKerjaYangBelumDigaji(0);
                    gamePanel.getCurrentSim().getPekerjaan().setTotalDurasiKerjaPerPekerjaan(0);
                    gamePanel.getCurrentSim().getPekerjaan().setIsCanStartPekerjaan(false);
                } else {
                    gamePanel.getUi().setCharIndex(0);
                    gamePanel.getUi().setCombinedText("");
                    gamePanel.setGameState(gamePanel.getDialogState());
                    gamePanel.getUi().setCurrentDialog("Uang tidak cukup untuk melamar\npekerjaan!");
                }
            }

        } else if (keyCode == KeyEvent.VK_UP) {
            gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()-1);
            if (gamePanel.getUi().getCommandNumber() < 0) {
                gamePanel.getUi().setCommandNumber(4);
            }
            cursorSound();
        } else if (keyCode == KeyEvent.VK_DOWN) {
            gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()+1);
            if (gamePanel.getUi().getCommandNumber() > 4) {
                gamePanel.getUi().setCommandNumber(0);
            }
            cursorSound();
        }

        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.gameState = gamePanel.kerjaState;
            cursorSound();
        }
    }

    public void kerjaState(int keyCode) {
        if (gamePanel.getCurrentSim().getPekerjaan().getIsCanChangePekerjaan()
                && gamePanel.getCurrentSim().getPekerjaan().getIsCanStartPekerjaan()) {
            if (keyCode == KeyEvent.VK_UP) {
                gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()-1);
                if (gamePanel.getUi().getCommandNumber() < 0) {
                    gamePanel.getUi().setCommandNumber(1);
                }
                cursorSound();
            } else if (keyCode == KeyEvent.VK_DOWN) {
                gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()+1);
                if (gamePanel.getUi().getCommandNumber() > 1) {
                    gamePanel.getUi().setCommandNumber(0);
                }
                cursorSound();
            } else if (keyCode == KeyEvent.VK_ENTER) {
                if (gamePanel.getUi().getCommandNumber() == 0) {
                    gamePanel.gameState = gamePanel.inputDurasiKerjaState;
                } else {
                    gamePanel.gameState = gamePanel.gantiPekerjaanState;
                }
                cursorSound();
                gamePanel.getUi().setCommandNumber(0);
            }
        } else if (gamePanel.getCurrentSim().getPekerjaan().getIsCanChangePekerjaan()) {
            if (keyCode == KeyEvent.VK_UP) {
                gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()-1);
                if (gamePanel.getUi().getCommandNumber() < 0) {
                    gamePanel.getUi().setCommandNumber(0);
                }
                cursorSound();
            } else if (keyCode == KeyEvent.VK_DOWN) {
                gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()+1);
                if (gamePanel.getUi().getCommandNumber() > 0) {
                    gamePanel.getUi().setCommandNumber(0);
                }
                cursorSound();
            } else if (keyCode == KeyEvent.VK_ENTER) {
                gamePanel.gameState = gamePanel.gantiPekerjaanState;
                cursorSound();
                gamePanel.getUi().setCommandNumber(0);
            }
        } else if (gamePanel.getCurrentSim().getPekerjaan().getIsCanStartPekerjaan()) {
            if (keyCode == KeyEvent.VK_UP) {
                gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()-1);
                if (gamePanel.getUi().getCommandNumber() < 0) {
                    gamePanel.getUi().setCommandNumber(0);
                }
                cursorSound();
            } else if (keyCode == KeyEvent.VK_DOWN) {
                gamePanel.getUi().setCommandNumber(gamePanel.getUi().getCommandNumber()+1);
                if (gamePanel.getUi().getCommandNumber() > 0) {
                    gamePanel.getUi().setCommandNumber(0);
                }
                cursorSound();
            } else if (keyCode == KeyEvent.VK_ENTER) {
                gamePanel.gameState = gamePanel.inputDurasiKerjaState;
                cursorSound();
                gamePanel.getUi().setCommandNumber(0);
            }
        }

        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.getUi().setInputText("");
            gamePanel.getUi().setInputTextDone(false);
            gamePanel.getUi().setCommandNumber(0);
            gamePanel.setGameState(gamePanel.getPlayState());
            cursorSound();
        }
    }

    public void melihatWaktuState(int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            gamePanel.setGameState(gamePanel.getPlayState());
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
