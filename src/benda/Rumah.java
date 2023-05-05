package benda;

import java.util.*;

import main.GamePanel;

public class Rumah extends Benda {
    private int colRumah;
    private int rowRumah;
    private int jumlahRuangan = 0;
    private boolean isCanUpgrade = true;
    private String posisiUpgrade = "";
    private int remainingTimeUpgrade = 0;
    private Ruangan ruanganUpgrade;
    private int indexLocationSaatUpgrade = 0;
    private int indexSimSaatUpgrade = 0;
    private boolean isLockUpgrade = true;

    private List<Ruangan> ruanganRumah = new ArrayList<>();

    public List<Ruangan> getRuanganRumah() {
        return ruanganRumah;
    }

    public void setRuanganRumah(List<Ruangan> ruanganRumah) {
        this.ruanganRumah = ruanganRumah;
    }

    public int getColRumah() {
        return colRumah;
    }

    public void setColRumah(int colRumah) {
        this.colRumah = colRumah;
    }

    public int getRowRumah() {
        return rowRumah;
    }

    public void setRowRumah(int rowRumah) {
        this.rowRumah = rowRumah;
    }

    public int getJumlahRuangan() {
        return jumlahRuangan;
    }

    public void setJumlahRuangan(int jumlahRuangan) {
        this.jumlahRuangan = jumlahRuangan;
    }

    public boolean getIsCanUpgrade() {
        return isCanUpgrade;
    }

    public void setIsCanUpgrade(boolean isCanUpgrade) {
        this.isCanUpgrade = isCanUpgrade;
    }

    public String getPosisiUpgrade() {
        return posisiUpgrade;
    }

    public void setPosisiUpgrade(String posisiUpgrade) {
        this.posisiUpgrade = posisiUpgrade;
    }

    public int getRemainingTimeUpgrade() {
        return remainingTimeUpgrade;
    }

    public void setRemainingTimeUpgrade(int remainingTimeUpgrade) {
        this.remainingTimeUpgrade = remainingTimeUpgrade;
    }

    public void decRemainingTimeUpgrade(int remainingTimeUpgrade) {
        this.remainingTimeUpgrade -= remainingTimeUpgrade;
    }

    public Ruangan getRuanganUpgrade() {
        return ruanganUpgrade;
    }

    public void setRuanganUpgrade(Ruangan ruanganUpgrade) {
        this.ruanganUpgrade = ruanganUpgrade;
    }

    public int getIndexLocationSaatUpgrade() {
        return indexLocationSaatUpgrade;
    }

    public void setIndexLocationSaatUpgrade(int indexLocationSaatUpgrade) {
        this.indexLocationSaatUpgrade = indexLocationSaatUpgrade;
    }

    public int getIndexSimSaatUpgrade() {
        return indexSimSaatUpgrade;
    }

    public void setIndexSimSaatUpgrade(int indexSimSaatUpgrade) {
        this.indexSimSaatUpgrade = indexSimSaatUpgrade;
    }

    public boolean getIsLockUpgrade() {
        return isLockUpgrade;
    }

    public void setIsLockUpgrade(boolean isLockUpgrade) {
        this.isLockUpgrade = isLockUpgrade;
    }

    public Rumah(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        setName("Rumah");
        setImage(setupImage("benda/rumah"));
        // random posisi rumah
        Random rand = new Random();
        colRumah = rand.nextInt(64) + 1;
        rowRumah = rand.nextInt(64) + 1;

        // saat rumah dibuat Ruangan utama otomatis dibuat
        Ruangan ruanganUtama = new Ruangan(gamePanel);
        jumlahRuangan = 1;
        ruanganUtama.setName("Ruangan Utama");
        ruanganRumah.add(ruanganUtama);
    }

    public void upgradeRumah(String posisi, String nama) {
        Ruangan ruangan = new Ruangan(gamePanel);
        ruangan.setName(nama);
        if (posisi == "up") {
            if (ruanganRumah.get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan())
                    .getUp() != null) {
                gamePanel.getUi().setCharIndex(0);
                gamePanel.getUi().setCombinedText("");
                gamePanel.setGameState(gamePanel.getDialogState());
                gamePanel.getUi().setCurrentDialog("Ruangan di atas sudah ada!");
            } else {
                if (gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getUang() < 1500) {
                    gamePanel.getUi().setCharIndex(0);
                    gamePanel.getUi().setCombinedText("");
                    gamePanel.setGameState(gamePanel.getDialogState());
                    gamePanel.getUi().setCurrentDialog("Uang tidak cukup untuk melakukan\nupgrade rumah!");
                    return;
                } else {
                    if (isCanUpgrade) {
                        posisiUpgrade = posisi;
                        isCanUpgrade = false;
                        ruanganUpgrade = ruangan;
                        indexLocationSaatUpgrade = gamePanel.getListSim()
                                .get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan();
                        indexSimSaatUpgrade = gamePanel.getIndexCurrentSim();
                        remainingTimeUpgrade = 18 * 60;
                    } else {
                        gamePanel.getUi().setCharIndex(0);
                        gamePanel.getUi().setCombinedText("");
                        gamePanel.setGameState(gamePanel.getDialogState());
                        gamePanel.getUi().setCurrentDialog("Terdapat ruangan yang sedang\ndibangun!");
                    }
                }
            }
        } else if (posisi == "down") {
            if (gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan() == 0) {
                gamePanel.getUi().setCharIndex(0);
                gamePanel.getUi().setCombinedText("");
                gamePanel.setGameState(gamePanel.getDialogState());
                gamePanel.getUi().setCurrentDialog("Tidak bisa membuat ruangan di bawah\nruangan utama!");
                return;
            } else {
                if (ruanganRumah
                        .get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan())
                        .getDown() != null) {
                    gamePanel.getUi().setCharIndex(0);
                    gamePanel.getUi().setCombinedText("");
                    gamePanel.setGameState(gamePanel.getDialogState());
                    gamePanel.getUi().setCurrentDialog("Ruangan di bawah sudah ada!");
                } else {
                    if (gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getUang() < 1500) {
                        gamePanel.getUi().setCharIndex(0);
                        gamePanel.getUi().setCombinedText("");
                        gamePanel.setGameState(gamePanel.getDialogState());
                        gamePanel.getUi().setCurrentDialog("Uang tidak cukup untuk melakukan\nupgrade rumah!");
                        return;
                    } else {
                        if (isCanUpgrade) {
                            posisiUpgrade = posisi;
                            isCanUpgrade = false;
                            indexLocationSaatUpgrade = gamePanel.getListSim()
                                    .get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan();
                            indexSimSaatUpgrade = gamePanel.getIndexCurrentSim();
                            remainingTimeUpgrade = 18 * 60;
                            ruanganUpgrade = ruangan;
                        } else {
                            gamePanel.getUi().setCharIndex(0);
                            gamePanel.getUi().setCombinedText("");
                            gamePanel.setGameState(gamePanel.getDialogState());
                            gamePanel.getUi().setCurrentDialog("Terdapat ruangan yang sedang\ndibangun!");
                        }
                    }
                }
            }

        } else if (posisi == "left") {
            if (ruanganRumah.get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan())
                    .getLeft() != null) {
                gamePanel.getUi().setCharIndex(0);
                gamePanel.getUi().setCombinedText("");
                gamePanel.setGameState(gamePanel.getDialogState());
                gamePanel.getUi().setCurrentDialog("Ruangan di kiri sudah ada!");
            } else {
                if (gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getUang() < 1500) {
                    gamePanel.getUi().setCharIndex(0);
                    gamePanel.getUi().setCombinedText("");
                    gamePanel.setGameState(gamePanel.getDialogState());
                    gamePanel.getUi().setCurrentDialog("Uang tidak cukup untuk melakukan\nupgrade rumah!");
                    return;
                } else {
                    if (isCanUpgrade) {
                        posisiUpgrade = posisi;
                        isCanUpgrade = false;
                        indexLocationSaatUpgrade = gamePanel.getListSim()
                                .get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan();
                        indexSimSaatUpgrade = gamePanel.getIndexCurrentSim();
                        remainingTimeUpgrade = 18 * 60;
                        ruanganUpgrade = ruangan;
                    } else {
                        gamePanel.getUi().setCharIndex(0);
                        gamePanel.getUi().setCombinedText("");
                        gamePanel.setGameState(gamePanel.getDialogState());
                        gamePanel.getUi().setCurrentDialog("Terdapat ruangan yang sedang\ndibangun!");
                    }
                }
            }
        } else if (posisi == "right") {
            if (ruanganRumah.get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan())
                    .getRight() != null) {
                gamePanel.getUi().setCharIndex(0);
                gamePanel.getUi().setCombinedText("");
                gamePanel.setGameState(gamePanel.getDialogState());
                gamePanel.getUi().setCurrentDialog("Ruangan di kanan sudah ada!");
            } else {
                if (gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getUang() < 1500) {
                    gamePanel.getUi().setCharIndex(0);
                    gamePanel.getUi().setCombinedText("");
                    gamePanel.setGameState(gamePanel.getDialogState());
                    gamePanel.getUi().setCurrentDialog("Uang tidak cukup untuk melakukan\nupgrade rumah!");
                    return;
                } else {
                    if (isCanUpgrade) {
                        posisiUpgrade = posisi;
                        isCanUpgrade = false;
                        indexLocationSaatUpgrade = gamePanel.getListSim()
                                .get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan();
                        indexSimSaatUpgrade = gamePanel.getIndexCurrentSim();
                        remainingTimeUpgrade = 18 * 60;
                        ruanganUpgrade = ruangan;
                    } else {
                        gamePanel.getUi().setCharIndex(0);
                        gamePanel.getUi().setCombinedText("");
                        gamePanel.setGameState(gamePanel.getDialogState());
                        gamePanel.getUi().setCurrentDialog("Terdapat ruangan yang sedang\ndibangun!");
                    }
                }
            }
        }
    }

    public void setIsCanUpgradeToTrueAfter18Minutes() {
        if (isLockUpgrade == false) {
            if (remainingTimeUpgrade < 0) {
                remainingTimeUpgrade = 0;
            }
            if (remainingTimeUpgrade == 0) {
                isCanUpgrade = true; // atur isCanUpgrade menjadi true setelah 18 menit
                ruanganUpgrade.setIndex(jumlahRuangan);
                if (posisiUpgrade.equals("up")) {
                    ruanganRumah
                            .get(gamePanel.getListSim()
                                    .get(indexSimSaatUpgrade).getRumah().indexLocationSaatUpgrade)
                            .setUp(ruanganUpgrade);
                    ruanganUpgrade.setDown(ruanganRumah
                            .get(gamePanel.getListSim().get(indexSimSaatUpgrade).getRumah().indexLocationSaatUpgrade));
                    ruanganRumah.add(ruanganUpgrade);
                    gamePanel.getListSim().get(indexSimSaatUpgrade)
                            .setUang(gamePanel.getListSim().get(indexSimSaatUpgrade).getUang() - 1500);
                } else if (posisiUpgrade.equals("down")) {
                    ruanganRumah
                            .get(gamePanel.getListSim()
                                    .get(indexSimSaatUpgrade).getRumah().indexLocationSaatUpgrade)
                            .setDown(ruanganUpgrade);
                    ruanganUpgrade.setUp(ruanganRumah
                            .get(gamePanel.getListSim().get(indexSimSaatUpgrade).getRumah().indexLocationSaatUpgrade));
                    ruanganRumah.add(ruanganUpgrade);
                    gamePanel.getListSim().get(indexSimSaatUpgrade)
                            .setUang(gamePanel.getListSim().get(indexSimSaatUpgrade).getUang() - 1500);
                } else if (posisiUpgrade.equals("left")) {
                    ruanganRumah
                            .get(gamePanel.getListSim()
                                    .get(indexSimSaatUpgrade).getRumah().indexLocationSaatUpgrade)
                            .setLeft(ruanganUpgrade);
                    ruanganUpgrade.setRight(ruanganRumah
                            .get(gamePanel.getListSim().get(indexSimSaatUpgrade).getRumah().indexLocationSaatUpgrade));
                    ruanganRumah.add(ruanganUpgrade);
                    gamePanel.getListSim().get(indexSimSaatUpgrade)
                            .setUang(gamePanel.getListSim().get(indexSimSaatUpgrade).getUang() - 1500);
                } else if (posisiUpgrade.equals("right")) {
                    ruanganRumah
                            .get(gamePanel.getListSim()
                                    .get(indexSimSaatUpgrade).getRumah().indexLocationSaatUpgrade)
                            .setRight(ruanganUpgrade);
                    ruanganUpgrade.setLeft(ruanganRumah
                            .get(gamePanel.getListSim().get(indexSimSaatUpgrade).getRumah().indexLocationSaatUpgrade));
                    ruanganRumah.add(ruanganUpgrade);
                    gamePanel.getListSim().get(indexSimSaatUpgrade)
                            .setUang(gamePanel.getListSim().get(indexSimSaatUpgrade).getUang() - 1500);
                }
                jumlahRuangan += 1;
                gamePanel.getListSim().get(indexSimSaatUpgrade).setTempDialogUpgrade("Pembangunan rumah "
                        + gamePanel.getListSim().get(indexSimSaatUpgrade).getNama() + " telah selesai.");
                gamePanel.getListSim().get(indexSimSaatUpgrade).setIsUpgradeDone(true);
            }
        }
    }
}
