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
        int tot1 = target.length();
        int tot2 = tot1;

        int x = 0, y = s.length() - 1;
        while (tot1 > 0 || tot2 > 0) {
            char a = s.charAt(x);
            char b = s.charAt(y);

            if (tot1 > 0 && counter1[a-start] > 0) {
                tot1--;
                counter1[a-start]--;
                x++;
            } else if (tot1 > 0) x++;

            if (tot2 > 0 && counter2[b-start] > 0) {
                tot2--;
                counter2[b-start]--;
                y--;
            } else if (tot2 > 0) y--;
        }
        return (y+1)-(x-1)-1;
    }

}
