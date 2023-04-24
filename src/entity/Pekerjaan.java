package entity;

public class Pekerjaan {
    private String namaPekerjaan;
    private int gaji;

    public Pekerjaan(){
        namaPekerjaan="Pengangguran";
        gaji=0;
    }

    public int getGaji(){
        return gaji;
    }

    public String getNamaPekerjaan(){
        return namaPekerjaan;
    }

    public void setGaji(int gaji){
        this.gaji=gaji;
    }

    public void setNamaPekerjaan(String namaPekerjaan){
        this.namaPekerjaan=namaPekerjaan;
    }

    public void kerja (String namaPekerjaan,int lama) throws Exception{
        int tempgaji=0;
        int repeat = lama/4;
        switch (namaPekerjaan){
            case "Badut Sulap":
            tempgaji=15;
            break;

            case "Koki":
            tempgaji=30;
            break;

            case "Polisi":
            tempgaji=35;
            break;

            case "Programmer":
            tempgaji=45;
            break;

            case "Dokter":
            tempgaji=50;
            break;
        }
        try{
            for(int i=0;i<repeat;i++){
                this.gaji+=tempgaji;
            }
            Thread.sleep(lama*60000);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}

