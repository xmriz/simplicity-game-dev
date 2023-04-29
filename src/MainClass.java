if (gamePanel.listSim.size() >= 1){

    // gamePanel.listSim.remove(gamePanel.indexCurrentSim);
    // gamePanel.listRumah[0].remove(gamePanel.indexCurrentSim);
    // for (int i = 0; i < gamePanel.listSim.size(); i++){
    //     if (gamePanel.listSim.get(i).indexRumahYangDimasuki != 999){
    //         gamePanel.listSim.get(i).indexRumahYangDimasuki--;
    //     }
    // }
    // gamePanel.indexCurrentSim = 0;

    g2d.setFont(g2d.getFont().deriveFont(50f));
    text = "Change Sim";
    x = getXforCenteredText(text);
    y += gamePanel.tileSize*4;
    g2d.drawString(text, x, y);
    if (commandNumber == 0){
        g2d.drawString(">", x-40, y);
    }

    // back to the the title screen
    text = "Quit";
    x = getXforCenteredText(text);
    y += 55;
    g2d.drawString(text, x, y);
    if (commandNumber == 1){
        g2d.drawString(">", x-40, y);
    }
} else {
    g2d.setFont(g2d.getFont().deriveFont(50f));
    text = "Change Sim";
    x = getXforCenteredText(text);
    y += gamePanel.tileSize*4;
    g2d.setColor(Color.GRAY);
    g2d.drawString(text, x, y);

    // back to the the title screen
    text = "Quit";
    x = getXforCenteredText(text);
    y += 55;
    g2d.setColor(Color.WHITE);
    g2d.drawString(text, x, y);
    if (commandNumber == 0){
        g2d.drawString(">", x-40, y);
    }
}