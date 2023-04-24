package benda;

import java.util.*;

import main.GamePanel;

public class Rumah extends Benda{
    public int dimensiX;
    public int dimensiY;
    public List<Ruangan> ruanganRumah = new ArrayList<>();

    
    public Rumah(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        name = "Rumah";
        image = setupImage("benda/rumah");
        dimensiX = 10;
        dimensiY = 10;
        
        // saat rumah dibuat Ruangan utama otomatis dibuat
        ruanganRumah.add(new Ruangan(gamePanel));
    }
}
