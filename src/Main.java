import java.util.*;

/**
 * Main
 */
public class Main {
    public static void main(String[] args) {
        // bahan
        List<String> x = new ArrayList<>();
        x.add("Sapi");
        x.add("Sayur");
        
        // inventory
        List<String> y = new ArrayList<>();
        y.add("Sapi");
        y.add("Sayur");
        y.add("Udang");
        y.add("Telur");

        System.out.println(y.containsAll(x));
    }
    
}