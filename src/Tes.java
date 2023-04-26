import java.util.*;

public class Tes {
    public static void main(String[] args) {
        int n = 2;
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] arrayOfLists = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            arrayOfLists[i] = new ArrayList<Integer>();
        }
        arrayOfLists[0].add(3);
        System.out.println(arrayOfLists[0].get(0));
    }
}