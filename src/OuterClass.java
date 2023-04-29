class OuterClass {
    public Thread startTimerThread(int duration) {
        TimerThread timer = new TimerThread(duration);
        Thread thread = new Thread(timer);
        thread.start();
        return thread;
    }

    public class TimerThread implements Runnable {
        private int duration;

        public TimerThread(int duration) {
            this.duration = duration;
        }

        @Override
        public void run() {
            int remaining = duration;

            while (remaining > 0) {
                System.out.println("Waktu yang tersisa: " + remaining + " detik");
                remaining--;

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Timer dihentikan");
                    return;
                }
            }

            System.out.println("Waktu habis");
        }
    }
}