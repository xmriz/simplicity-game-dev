package entity;

public class Pekerjaan {

    private String[] listPekerjaan = { "Badut Sulap", "Koki", "Polisi", "Programmer", "Dokter" };
    private int[] gaji = { 15, 30, 35, 45, 50 };
    private int indexPekerjaan;
    private int durasiKerjaYangBelumDigaji = 0;
    private int totalDurasiKerjaPerPekerjaan = 0;
    private boolean isCanStartPekerjaan = true; // dapat mulai pekerjaan setelah 1 hari setelah ganti pekerjaan
    private boolean isCanChangePekerjaan = false; // dapat ganti pekerjaan setelah 1 hari bekerja di pekerjaan
                                                  // sebelumnya
    private int worldTimeCounterForStartJobAfterChangeJob = 0;

    public String[] getListPekerjaan() {
        return listPekerjaan;
    }

    public void setListPekerjaan(String[] listPekerjaan) {
        this.listPekerjaan = listPekerjaan;
    }

    public int[] getGaji() {
        return gaji;
    }

    public void setGaji(int[] gaji) {
        this.gaji = gaji;
    }

    public int getIndexPekerjaan() {
        return this.indexPekerjaan;
    }

    public void setIndexPekerjaan(int indexPekerjaan) {
        this.indexPekerjaan = indexPekerjaan;
    }

    public int getDurasiKerjaYangBelumDigaji() {
        return durasiKerjaYangBelumDigaji;
    }

    public void setDurasiKerjaYangBelumDigaji(int durasiKerjaYangBelumDigaji) {
        this.durasiKerjaYangBelumDigaji = durasiKerjaYangBelumDigaji;
    }

    public int getTotalDurasiKerjaPerPekerjaan() {
        return totalDurasiKerjaPerPekerjaan;
    }

    public void setTotalDurasiKerjaPerPekerjaan(int totalDurasiKerjaPerPekerjaan) {
        this.totalDurasiKerjaPerPekerjaan = totalDurasiKerjaPerPekerjaan;
    }

    public boolean getIsCanStartPekerjaan() {
        return isCanStartPekerjaan;
    }

    public void setIsCanStartPekerjaan(boolean isCanStartPekerjaan) {
        this.isCanStartPekerjaan = isCanStartPekerjaan;
    }

    public boolean getIsCanChangePekerjaan() {
        return isCanChangePekerjaan;
    }

    public void setIsCanChangePekerjaan(boolean isCanChangePekerjaan) {
        this.isCanChangePekerjaan = isCanChangePekerjaan;
    }

    public int getWorldTimeCounterForStartJobAfterChangeJob() {
        return worldTimeCounterForStartJobAfterChangeJob;
    }

    public void setWorldTimeCounterForStartJobAfterChangeJob(int worldTimeCounterForStartJobAfterChangeJob) {
        this.worldTimeCounterForStartJobAfterChangeJob = worldTimeCounterForStartJobAfterChangeJob;
    }
}
