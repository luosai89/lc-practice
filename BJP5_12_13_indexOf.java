/**
 * https://practiceit.cs.washington.edu/problem/view/bjp5/chapter12/e13-indexOf
 * Had trouble determine the base case for when String b is not found, or WHEN NOT TO RETURN
 */
public class BJP5_12_13_indexOf {
    public static int indexOf(String a, String b) {
        if (a.length() == b.length()) {
            if (a.equals(b)) {
                return 0;
            }
        } else if (a.length() >= b.length()) {
            int length = b.length();
            if (a.substring(0, length).equals(b)) {
                return 0;
            } else {
                int result = 1 + indexOf(a.substring(1), b);
                if (result != 0) {
                    return result;
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        String a = "abc";
        String b = "d";
        System.out.println(indexOf(a, b));
    }
}
