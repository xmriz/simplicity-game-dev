class OuterClass {
    public void startTimerThread(int duration) {
        TimerThread timer = new TimerThread(duration);
        Thread thread = new Thread(timer);
        thread.start();
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
                    e.printStackTrace();
                }
            }

            System.out.println("Waktu habis");
        }
    }
}