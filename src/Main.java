public class Main {
    private static boolean isCanUpgrade = false;

    public static void setIsCanUpgradeToTrueAfter5Detik() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000); // tunggu 5 menit
                    isCanUpgrade = true; // atur isCanUpgrade menjadi true setelah 5 detik
                    System.out.println("isCanUpgrade sudah true");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start(); // mulai thread
    }

    public static void main(String[] args) {
        setIsCanUpgradeToTrueAfter5Detik(); // memulai thread untuk mengatur isCanUpgrade menjadi true setelah 5 detik
        System.out.println("isCanUpgrade: " + isCanUpgrade); // isCanUpgrade masih false
        
        try {
            Thread.sleep(6000); // tunggu 1 detik
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("isCanUpgrade: " + isCanUpgrade); // isCanUpgrade sudah true
        setIsCanUpgradeToTrueAfter5Detik(); // memulai thread untuk mengatur isCanUpgrade menjadi true setelah 5 detik
    }
}