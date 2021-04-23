package BJP;

import java.util.ArrayList;

/**
 * INCOMPLETE
 * https://practiceit.cs.washington.edu/problem/view/bjp5/chapter12/e24-printSquares
 */
public class BJP5_12_24_printSquares {
    public static void printSquares(int n) {
        ArrayList<Integer> s = new ArrayList<>();
        printSquares(s, 1, n);
    }

    public static void printSquares(ArrayList<Integer> s, int min, int n) {
        if (n != 0 && min > Math.sqrt(n)) {
            s.clear();
            return;
        }
        if (n == 0 && min <= Math.sqrt(n)) {
            s.add(min);
            printHelper(s);
        }
        if (n == 0) {
            s.clear();
            return;
        }
        for (int i = min; i <= Math.sqrt(n); i++) {
            int r = n - i * i;
            s.add(i);
            printSquares(s, i + 1, r);
        }
    }

    public static void printHelper(ArrayList<Integer> s) {
        if (s.size() < 1) return;
        System.out.print(s.get(0) + "^2");
        for (int i = 1; i < s.size(); i++) {
            System.out.print(" + " + s.get(i) + "^2");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        printSquares(4);
    }
}
