import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // printFileContents("file.txt");

        LoadFrame openingLoad = new LoadFrame(); // creates a new MyFrame object
        delay(4000);
        openingLoad.dispose();

    }

    // -------------------------------------------------------------------------------

    public static void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void printFileContents(String fileName) {
        String path = "data/";
        String file = path + fileName;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

}
