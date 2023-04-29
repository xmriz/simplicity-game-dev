import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan durasi timer (dalam detik): ");
        int duration = scanner.nextInt();

        OuterClass outer = new OuterClass();
        Thread thread = outer.startTimerThread(duration);

        // melakukan pekerjaan lain di sini

        System.out.print("Apakah ingin menghentikan timer? (y/n): ");
        String answer = scanner.next();
        if (answer.equals("y")) {
            thread.interrupt();
        }
    }
}