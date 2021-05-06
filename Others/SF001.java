package Others;

public class SF001 {
    public static int findGap(String s, String target) {
        int[] counter1 = new int[26];
        int[] counter2 = new int[26];
        char start = 'a';
        for (char c : target.toCharArray()) {
            counter1[c - start]++;
            counter2[c - start]++;
        }

        int x = 0;
        int y = s.length() - 1;

        int tot = target.length();
        while (tot > 0) {
            char a = s.charAt(x);
            if (counter1[a - start] > 0) {
                tot--;
                counter1[a - start]--;
            }
            x++;
        }

        tot = target.length();
        while (tot > 0) {
            char a = s.charAt(y);
            if (counter2[a - start] > 0) {
                tot--;
                counter2[a - start]--;
            }
            y--;
        }

        return (y+1)-(x-1)-1;
    }

    public static void main(String[] args) {
        String test = "programmerxxxprozmerqgram";
        System.out.println(findGap(test, "programmer"));
    }

}
