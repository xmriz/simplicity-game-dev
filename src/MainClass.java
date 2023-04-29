if (index != 999){ // 999 means there is no collision with benda
    if (!(gamePanel.benda[currentMap][index] instanceof Furnitur ||
    gamePanel.benda[currentMap][index] instanceof Rumah)){
    if (canObtainItem(gamePanel.benda[currentMap][index])){
    // text = "kamu mendapatkan " + gamePanel.benda[currentMap][index].nama;
    } else {
    // text = "Inventory penuh";
    }
    // gamePanel.ui.addMessage(text);
    gamePanel.benda[currentMap][index] = null;
    }
    }