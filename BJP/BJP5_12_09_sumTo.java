package BJP;

/**
 * https://practiceit.cs.washington.edu/problem/view/bjp5/chapter12/e9-sumTo
 * Select appropriate variable type
 */
public class BJP5_12_09_sumTo {
    public static int sumTo(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        if (n < 2) {
            return n;
        }
        System.out.println("1 / n = " + (1 / n));
        return sumTo(n - 1) + 1 / n;
    }

    public static void main(String[] args) {
        System.out.println(sumTo(2));
    }
}
