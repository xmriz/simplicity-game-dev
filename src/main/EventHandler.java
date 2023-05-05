package main;

public class EventHandler {
    private GamePanel gamePanel;
    private EventRect eventRect[][][];

    private int previousEventX, previousEventY;
    private boolean canTouchEvent = true;

    // public static int
    // gamePanel.listSim.get(gamePanel.indexCurrentSim).indexRumahYangDimasuki =
    // 999;;

    public EventHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        eventRect = new EventRect[gamePanel.getMaxMap()][gamePanel.getMaxWorldCol()][gamePanel.getMaxWorldRow()];

        int map = 0;
        int col = 0, row = 0;

        while (map < gamePanel.getMaxMap() && col < gamePanel.getMaxWorldCol() && row < gamePanel.getMaxWorldRow()) {
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].setEventRectDefaultX(eventRect[map][col][row].x);
            eventRect[map][col][row].setEventRectDefaultY(eventRect[map][col][row].y);

            col++;
            if (col == gamePanel.getMaxWorldCol()) {
                col = 0;
                row++;

                if (row == gamePanel.getMaxWorldRow()) {
                    row = 0;
                    map++;
                }
            }
        }
    }

    public void checkEvent() {
        // check if the sim is more than 1 tile away from the event
        int xDistance = Math.abs(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getWorldX() - previousEventX);
        int yDistance = Math.abs(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getWorldY() - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gamePanel.getTileSize()) {
            canTouchEvent = true;
        }

        int indexRumahWorldTemp = gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexBendaYangDisentuh();
        // gamePanel.listSim.get(gamePanel.indexCurrentSim).indexRumahYangDimasuki =
        // indexRumahWorldTemp;

        // checking for some event
        if (canTouchEvent) {

            // ----- BATAS ----- //

            // for (int i = 0; i < gamePanel.listSim.size(); i++){
            // if (gamePanel.listSim.get(gamePanel.indexCurrentSim).currentMap == 0){ // DI
            // WORLD
            // if (hit(0,gamePanel.listSim.get(i).rumah.colRumah,
            // gamePanel.listSim.get(i).rumah.rowRumah, "any",999)){
            // teleport(1,5,8,0);
            // gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan = 0;
            // gamePanel.listSim.get(gamePanel.indexCurrentSim).currentLocation = "Rumah " +
            // gamePanel.listSim.get(i).nama + " (" +
            // gamePanel.listSim.get(i).rumah.ruanganRumah.get(gamePanel.listSim.get(i).indexLocationRuangan).name
            // + ")";
            // }
            // } else if
            // (gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan == 0){
            // // DI RUANGAN UTAMA
            // if (hit(1,5,8, "any", 0)){
            // teleport(0,gamePanel.listSim.get(i).rumah.colRumah,
            // gamePanel.listSim.get(i).rumah.rowRumah, 999);
            // gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan = 999;
            // gamePanel.listSim.get(gamePanel.indexCurrentSim).currentLocation = "World";
            // } else if
            // (hit(1,4,1,"any",gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan)){
            // // atas
            // if
            // (gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).up
            // != null){
            // teleport(1,5, 8,
            // gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).up));
            // gamePanel.listSim.get(gamePanel.indexCurrentSim).currentLocation = "Rumah " +
            // gamePanel.listSim.get(gamePanel.indexCurrentSim).nama + " (" +
            // UtilityTool.capitalizeFirstLetter(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).name)
            // + ")";
            // // gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan =
            // gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).up);
            // } else {
            // gamePanel.gameState = gamePanel.dialogState;
            // gamePanel.ui.setCurrentDialog"Tidak ada ruangan di atas!";
            // canTouchEvent = false;
            // }
            // // UNTUK RUANGAN UTAMA BAWAH UNTUK TELEPORT KE WORLD
            // // } else if
            // (hit(1,5,8,"any",gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan)){
            // // if
            // (gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).down
            // != null){
            // // teleport(1,4, 1,
            // gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).down));
            // // gamePanel.listSim.get(gamePanel.indexCurrentSim).currentLocation = "Rumah
            // " + gamePanel.listSim.get(gamePanel.indexCurrentSim).nama + " (" +
            // UtilityTool.capitalizeFirstLetter(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).name);
            // // // gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan =
            // gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).down);
            // // } else {
            // // gamePanel.gameState = gamePanel.dialogState;
            // // gamePanel.ui.setCurrentDialog"Tidak ada ruangan di bawah!";
            // // canTouchEvent = false;
            // // }
            // } else if
            // (hit(1,1,5,"any",gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan))
            // { // kiri
            // if
            // (gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).left
            // != null){
            // teleport(1,8, 4,
            // gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).left));
            // gamePanel.listSim.get(gamePanel.indexCurrentSim).currentLocation = "Rumah " +
            // gamePanel.listSim.get(gamePanel.indexCurrentSim).nama + " (" +
            // UtilityTool.capitalizeFirstLetter(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).name)
            // + ")";
            // // gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan =
            // gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).left);
            // } else {
            // gamePanel.gameState = gamePanel.dialogState;
            // gamePanel.ui.setCurrentDialog"Tidak ada ruangan di kiri!";
            // canTouchEvent = false;
            // }
            // } else if
            // (hit(1,8,4,"any",gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan))
            // { // kanan
            // if
            // (gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).right
            // != null){
            // teleport(1,1, 5,
            // gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).right));
            // gamePanel.listSim.get(gamePanel.indexCurrentSim).currentLocation = "Rumah " +
            // gamePanel.listSim.get(gamePanel.indexCurrentSim).nama + " (" +
            // UtilityTool.capitalizeFirstLetter(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).name)
            // + ")";
            // // gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan =
            // gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).right);
            // } else {
            // gamePanel.gameState = gamePanel.dialogState;
            // gamePanel.ui.setCurrentDialog"Tidak ada ruangan di kanan!";
            // canTouchEvent = false;
            // }
            // }
            // }
            // }

            // --- BATAS --- //
            if (gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap() == 0) {

                if (indexRumahWorldTemp != 999) {
                    if (hit(0, gamePanel.getListSim().get(indexRumahWorldTemp).getRumah().getColRumah(),
                            gamePanel.getListSim().get(indexRumahWorldTemp).getRumah().getRowRumah(), "any")) { // pindah dari world ke
                                                                                                 // ruang utama
                        teleport(1, 5, 8, 0);
                        gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).setCurrentLocation("Rumah "
                                + gamePanel.getListSim().get(indexRumahWorldTemp).getNama() + " ("
                                + UtilityTool.capitalizeFirstLetter(
                                        gamePanel.getListSim().get(indexRumahWorldTemp).getRumah().getRuanganRumah()
                                                .get(gamePanel.getListSim()
                                                        .get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getName())
                                + ")");
                        gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).setIndexRumahYangDimasuki(indexRumahWorldTemp);

                        // nambah worldtimecounter
                        int x1 = gamePanel.getListSim().get(indexRumahWorldTemp).getRumah().getColRumah();
                        int y2 = gamePanel.getListSim().get(indexRumahWorldTemp).getRumah().getRowRumah();
                        int x2 = gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getRumah().getColRumah();
                        int y1 = gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getRumah().getRowRumah();
                        int durasi = UtilityTool.pythagoras(x1, y1, x2, y2);
                        gamePanel.incWorldTimeCounter(durasi);
                        gamePanel.incWorldTimeSatuHariCounter(durasi);

                        // efek waktu
                        for (int i = 0; i < gamePanel.getListSim().size(); i++) {
                                gamePanel.getListSim().get(i).getPekerjaan().setWorldTimeCounterForStartJobAfterChangeJob(gamePanel.getListSim()
                                        .get(i).getPekerjaan().getWorldTimeCounterForStartJobAfterChangeJob() + durasi);
                                gamePanel.getListSim().get(i).setEfekWaktuTidakTidurCounter(gamePanel.getListSim().get(i).getEfekWaktuTidakTidurCounter() + durasi);
                                if (gamePanel.getListSim().get(i).getIsUdahMakanDalamSatuHari()){
                                        gamePanel.getListSim().get(i).setEfekWaktuTidakBuangAirCounter(gamePanel.getListSim().get(i).getEfekWaktuTidakBuangAirCounter() + durasi);
                                }
                        }

                        // efek
                        gamePanel.getCurrentSim().setMood(gamePanel.getCurrentSim().getMood() + (durasi/30)*10);
                        gamePanel.getCurrentSim().setKekenyangan(gamePanel.getCurrentSim().getKekenyangan() - (durasi/30)*10);
                        if (gamePanel.getCurrentSim().getMood() > gamePanel.getCurrentSim().getMaxMood()) {
                                gamePanel.getCurrentSim().setMood(gamePanel.getCurrentSim().getMaxMood()); 
                        }
                        if (gamePanel.getCurrentSim().getKesehatan() > gamePanel.getCurrentSim().getMaxKesehatan()) {
                                gamePanel.getCurrentSim().setKesehatan(gamePanel.getCurrentSim().getMaxKesehatan());
                        }
                        if (gamePanel.getCurrentSim().getKekenyangan() > gamePanel.getCurrentSim().getMaxKekenyangan()) {
                                gamePanel.getCurrentSim().setKekenyangan(gamePanel.getCurrentSim().getMaxKekenyangan());
                        }

                        // matiiin mini map
                        gamePanel.map.setMapOn(false);
                    }
                }
                // if (hit(0,gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.colRumah,
                // gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.rowRumah, "any",999)){
                // teleport(1,5,8,0);
                // gamePanel.listSim.get(gamePanel.indexCurrentSim).currentLocation = "Rumah " +
                // gamePanel.listSim.get(gamePanel.indexCurrentSim).nama + " (" +
                // UtilityTool.capitalizeFirstLetter(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).name)
                // + ")";
                // }
            } else if (gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan() == 0) {
                if (hit(1, 5, 8, "any")) { // pindah dari ruang utama ke world
                    teleport(0,
                            gamePanel.getListSim().get(gamePanel.getListSim()
                                    .get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getColRumah(),
                            gamePanel.getListSim().get(gamePanel.getListSim()
                                    .get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRowRumah(),
                            999);
                    gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).setCurrentLocation("World");
                    gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).setIndexRumahYangDimasuki(999);
                } else if (hit(1, 4, 1, "any")) { // atas
                    if (gamePanel.getListSim().get(
                            gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                            .get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getUp() != null) {
                        teleport(1, 5, 8, gamePanel.getListSim()
                                .get(gamePanel.getListSim()
                                        .get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                                .indexOf(gamePanel.getListSim()
                                        .get(gamePanel.getListSim().get(
                                                gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                                        .get(
                                                gamePanel.getListSim()
                                                        .get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getUp()));
                        gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).setCurrentLocation("Rumah "
                                + gamePanel.getListSim().get(
                                        gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getNama()
                                + " ("
                                + UtilityTool.capitalizeFirstLetter(
                                        gamePanel.getListSim().get(gamePanel.getListSim().get(
                                                gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                                                .get(gamePanel.getListSim()
                                                        .get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getName())
                                + ")");
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan =
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).up);
                    } else {
                        gamePanel.getUi().setCharIndex(0);
                        gamePanel.getUi().setCombinedText("");
                        gamePanel.setGameState(gamePanel.getDialogState());
                        gamePanel.getUi().setCurrentDialog("Tidak ada ruangan di atas!");
                        canTouchEvent = false;
                    }
                    // UNTUK RUANGAN UTAMA BAWAH UNTUK TELEPORT KE WORLD
                    // } else if
                    // (hit(1,5,8,"any",gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan)){
                    // if
                    // (gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).down
                    // != null){
                    // teleport(1,4, 1,
                    // gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).down));
                    // gamePanel.listSim.get(gamePanel.indexCurrentSim).currentLocation = "Rumah " +
                    // gamePanel.listSim.get(gamePanel.indexCurrentSim).nama + " (" +
                    // UtilityTool.capitalizeFirstLetter(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).name);
                    // // gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan =
                    // gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).down);
                    // } else {
                    // gamePanel.gameState = gamePanel.dialogState;
                    // gamePanel.ui.setCurrentDialog"Tidak ada ruangan di bawah!";
                    // canTouchEvent = false;
                    // }
                } else if (hit(1, 1, 5, "any")) { // kiri
                    if (gamePanel.getListSim().get(
                            gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                            .get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getLeft() != null) {
                        teleport(1, 8, 4, gamePanel.getListSim()
                                .get(gamePanel.getListSim()
                                        .get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                                .indexOf(gamePanel.getListSim()
                                        .get(gamePanel.getListSim().get(
                                                gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                                        .get(
                                                gamePanel.getListSim()
                                                        .get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getLeft()));
                        gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).setCurrentLocation("Rumah "
                                + gamePanel.getListSim().get(
                                        gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getNama()
                                + " ("
                                + UtilityTool.capitalizeFirstLetter(
                                        gamePanel.getListSim().get(gamePanel.getListSim().get(
                                                gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                                                .get(gamePanel.getListSim()
                                                        .get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getName())
                                + ")");
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan =
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).left);
                    } else {
                        gamePanel.getUi().setCharIndex(0);
                        gamePanel.getUi().setCombinedText("");
                        gamePanel.setGameState(gamePanel.getDialogState());
                        gamePanel.getUi().setCurrentDialog("Tidak ada ruangan di kiri!");
                        canTouchEvent = false;
                    }
                } else if (hit(1, 8, 4, "any")) { // kanan
                    if (gamePanel.getListSim().get(
                            gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                            .get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getRight() != null) {
                        teleport(1, 1, 5, gamePanel.getListSim()
                                .get(gamePanel.getListSim()
                                        .get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                                .indexOf(gamePanel.getListSim()
                                        .get(gamePanel.getListSim().get(
                                                gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                                        .get(
                                                gamePanel.getListSim()
                                                        .get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getRight()));
                        gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).setCurrentLocation("Rumah "
                                + gamePanel.getListSim().get(
                                        gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getNama()
                                + " ("
                                + UtilityTool.capitalizeFirstLetter(
                                        gamePanel.getListSim().get(gamePanel.getListSim().get(
                                                gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                                                .get(gamePanel.getListSim()
                                                        .get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getName())
                                + ")");
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan =
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).right);
                    } else {
                        gamePanel.getUi().setCharIndex(0);
                        gamePanel.getUi().setCombinedText("");
                        gamePanel.setGameState(gamePanel.getDialogState());
                        gamePanel.getUi().setCurrentDialog("Tidak ada ruangan di kanan!");
                        canTouchEvent = false;
                    }
                }
            } else { // SELAIN DI RUANGAN UTAMA DAN WORLD
                if (hit(1, 4, 1, "any")) { // atas
                    if (gamePanel.getListSim().get(
                            gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                            .get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getUp() != null) {
                        teleport(1, 5, 8, gamePanel.getListSim()
                                .get(gamePanel.getListSim()
                                        .get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                                .indexOf(gamePanel.getListSim()
                                        .get(gamePanel.getListSim().get(
                                                gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                                        .get(
                                                gamePanel.getListSim()
                                                        .get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getUp()));
                        gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).setCurrentLocation("Rumah "
                                + gamePanel.getListSim().get(
                                        gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getNama()
                                + " ("
                                + UtilityTool.capitalizeFirstLetter(
                                        gamePanel.getListSim().get(gamePanel.getListSim().get(
                                                gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                                                .get(gamePanel.getListSim()
                                                        .get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getName())
                                + ")");
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan =
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).up);
                    } else {
                        gamePanel.getUi().setCharIndex(0);
                        gamePanel.getUi().setCombinedText("");
                        gamePanel.setGameState(gamePanel.getDialogState());
                        gamePanel.getUi().setCurrentDialog("Tidak ada ruangan di atas!");
                        canTouchEvent = false;
                    }
                } else if (hit(1, 5, 8, "any")) { // bawah
                    if (gamePanel.getListSim().get(
                            gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                            .get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getDown() != null) {
                        teleport(1, 4, 1, gamePanel.getListSim()
                                .get(gamePanel.getListSim()
                                        .get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                                .indexOf(gamePanel.getListSim()
                                        .get(gamePanel.getListSim().get(
                                                gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                                        .get(
                                                gamePanel.getListSim()
                                                        .get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getDown()));
                        gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).setCurrentLocation("Rumah "
                                + gamePanel.getListSim().get(
                                        gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getNama()
                                + " ("
                                + UtilityTool.capitalizeFirstLetter(
                                        gamePanel.getListSim().get(gamePanel.getListSim().get(
                                                gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                                                .get(gamePanel.getListSim()
                                                        .get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getName())
                                + ")");
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan =
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).down);
                    } else {
                        gamePanel.getUi().setCharIndex(0);
                        gamePanel.getUi().setCombinedText("");
                        gamePanel.setGameState(gamePanel.getDialogState());
                        gamePanel.getUi().setCurrentDialog("Tidak ada ruangan di bawah!");
                        canTouchEvent = false;
                    }
                } else if (hit(1, 1, 5, "any")) { // kiri
                    if (gamePanel.getListSim().get(
                            gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                            .get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getLeft() != null) {
                        teleport(1, 8, 4, gamePanel.getListSim()
                                .get(gamePanel.getListSim()
                                        .get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                                .indexOf(gamePanel.getListSim()
                                        .get(gamePanel.getListSim().get(
                                                gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                                        .get(
                                                gamePanel.getListSim()
                                                        .get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getLeft()));
                        gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).setCurrentLocation("Rumah "
                                + gamePanel.getListSim().get(
                                        gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getNama()
                                + " ("
                                + UtilityTool.capitalizeFirstLetter(
                                        gamePanel.getListSim().get(gamePanel.getListSim().get(
                                                gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                                                .get(gamePanel.getListSim()
                                                        .get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getName())
                                + ")");
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan =
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).left);
                    } else {
                        gamePanel.getUi().setCharIndex(0);
                        gamePanel.getUi().setCombinedText("");
                        gamePanel.setGameState(gamePanel.getDialogState());
                        gamePanel.getUi().setCurrentDialog("Tidak ada ruangan di kiri!");
                        canTouchEvent = false;
                    }
                } else if (hit(1, 8, 4, "any")) { // kanan
                    if (gamePanel.getListSim().get(
                            gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                            .get(gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getRight()!= null) {
                        teleport(1, 1, 5, gamePanel.getListSim()
                                .get(gamePanel.getListSim()
                                        .get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                                .indexOf(gamePanel.getListSim()
                                        .get(gamePanel.getListSim().get(
                                                gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                                        .get(
                                                gamePanel.getListSim()
                                                        .get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getRight()));
                        gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).setCurrentLocation("Rumah "
                                + gamePanel.getListSim().get(
                                        gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getNama()
                                + " ("
                                + UtilityTool.capitalizeFirstLetter(
                                        gamePanel.getListSim().get(gamePanel.getListSim().get(
                                                gamePanel.getIndexCurrentSim()).getIndexRumahYangDimasuki()).getRumah().getRuanganRumah()
                                                .get(gamePanel.getListSim()
                                                        .get(gamePanel.getIndexCurrentSim()).getIndexLocationRuangan()).getName())
                                + ")");
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan =
                        // gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.indexOf(gamePanel.listSim.get(gamePanel.indexCurrentSim).rumah.ruanganRumah.get(gamePanel.listSim.get(gamePanel.indexCurrentSim).indexLocationRuangan).right);
                    } else {
                        gamePanel.getUi().setCharIndex(0);
                        gamePanel.getUi().setCombinedText("");
                        gamePanel.setGameState(gamePanel.getDialogState());
                        gamePanel.getUi().setCurrentDialog("Tidak ada ruangan di kanan!");
                        canTouchEvent = false;
                    }
                }
            }
        }
    }

    public boolean hit(int map, int col, int row, String reqDirection) {
        boolean hit = false;
        if (map == 0) {
            if (map == gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()) {
                gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getSolidArea().x = gamePanel.getListSim()
                        .get(gamePanel.getIndexCurrentSim()).getWorldX()
                        + gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getSolidArea().x;
                gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getSolidArea().y = gamePanel.getListSim()
                        .get(gamePanel.getIndexCurrentSim()).getWorldY()
                        + gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getSolidArea().y;

                eventRect[map][col][row].x = col * gamePanel.getTileSize() + eventRect[map][col][row].x;
                eventRect[map][col][row].y = row * gamePanel.getTileSize() + eventRect[map][col][row].y;

                if (gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getSolidArea().intersects(eventRect[map][col][row])
                        && !eventRect[map][col][row].getEventDone()) {
                    if (gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getDirection().contentEquals(reqDirection)
                            || reqDirection.contentEquals("any")) {
                        hit = true;

                        previousEventX = gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getWorldX();
                        previousEventY = gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getWorldY();
                    }
                }

                gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getSolidArea().x = gamePanel.getListSim()
                        .get(gamePanel.getIndexCurrentSim()).getSolidAreaDefaultX();
                gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getSolidArea().y = gamePanel.getListSim()
                        .get(gamePanel.getIndexCurrentSim()).getSolidAreaDefaultY();
                eventRect[map][col][row].x = eventRect[map][col][row].getEventRectDefaultX();
                eventRect[map][col][row].y = eventRect[map][col][row].getEventRectDefaultY();
            }
        } else {
            if (map == gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getCurrentMap()) {
                gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getSolidArea().x = gamePanel.getListSim()
                        .get(gamePanel.getIndexCurrentSim()).getWorldX()
                        + gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getSolidArea().x;
                gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getSolidArea().y = gamePanel.getListSim()
                        .get(gamePanel.getIndexCurrentSim()).getWorldY()
                        + gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getSolidArea().y;

                eventRect[map][col][row].x = col * gamePanel.getTileSize() + eventRect[map][col][row].x;
                eventRect[map][col][row].y = row * gamePanel.getTileSize() + eventRect[map][col][row].y;

                if (gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getSolidArea().intersects(eventRect[map][col][row])
                        && !eventRect[map][col][row].getEventDone()) {
                    if (gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getDirection().contentEquals(reqDirection)
                            || reqDirection.contentEquals("any")) {
                        hit = true;

                        previousEventX = gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getWorldX();
                        previousEventY = gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getWorldY();
                    }
                }

                gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getSolidArea().x = gamePanel.getListSim()
                        .get(gamePanel.getIndexCurrentSim()).getSolidAreaDefaultX();
                gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getSolidArea().y = gamePanel.getListSim()
                        .get(gamePanel.getIndexCurrentSim()).getSolidAreaDefaultY();
                eventRect[map][col][row].x = eventRect[map][col][row].getEventRectDefaultX();
                eventRect[map][col][row].y = eventRect[map][col][row].getEventRectDefaultY();
            }
        }

        return hit;
    }

    public void teleport(int map, int col, int row, int indexRuangan) {
        // if (map == 0)
        gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).setCurrentMap(map); // pindahin sim ke map 1
        gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).setWorldX(col * gamePanel.getTileSize()); // pindahin sim ke kolom 1
        gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).setWorldY(row * gamePanel.getTileSize());
        previousEventX = gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getWorldX();
        previousEventY = gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).getWorldY();
        canTouchEvent = false;
        gamePanel.getListSim().get(gamePanel.getIndexCurrentSim()).setIndexLocationRuangan(indexRuangan);
        gamePanel.playSoundEffect(2);
    }
}