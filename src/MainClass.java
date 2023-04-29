import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan durasi timer (dalam detik): ");
        int duration = scanner.nextInt();
        scanner.close();

        OuterClass outer = new OuterClass();
        outer.startTimerThread(duration);

        // melakukan pekerjaan lain di sini
    }
}