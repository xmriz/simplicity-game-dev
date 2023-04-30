package entity;

public class Pekerjaan {

    public String[] listPekerjaan = {"Badut Sulap", "Koki", "Polisi", "Programmer", "Dokter"};
    public int[] gaji = {15, 30, 35, 45, 50};

    public int indexPekerjaan;

    public int durasiKerjaYangBelumDigaji = 0;
    public int totalDurasiKerjaPerPekerjaan = 0;

    public boolean isCanStartPekerjaan = true; // dapat mulai pekerjaan setelah 1 hari setelah ganti pekerjaan
    public boolean isCanChangePekerjaan = false; // dapat ganti pekerjaan setelah 1 hari bekerja di pekerjaan sebelumnya

    public int worldTimeCounterForStartJobAfterChangeJob = 0;
}

