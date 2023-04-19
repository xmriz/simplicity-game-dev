package benda;

public abstract class IMakanan extends Benda {
    private int nilaiKekenyangan;
    //bahan nunggu bahanmakanan
    
    /* nunggu bahan makanan
    public List<BahanMakanan> getBahan(){
        return bahan;
    }
    */

    public int getNilaiKekenyangan(){
        return nilaiKekenyangan;
    }

    public void setNilaiKekenyangan(int nilaiKekenyangan){
        this.nilaiKekenyangan=nilaiKekenyangan;
    }

    //test branch
}
