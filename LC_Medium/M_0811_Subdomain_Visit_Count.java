package LC_Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 2021: 8/26 (Y)
 * https://leetcode.com/problems/subdomain-visit-count/
 * Given an array of count-paired domains cpdomains, return an array of the count-paired domains of each subdomain in
 * the input. You may return the answer in any order.
 */
public class M_0811_Subdomain_Visit_Count {
    public static List<String> subdomainVisits(String[] cpdomains) {
        List<String> result = new ArrayList<>();

        Map<String, Integer> domainCount = new HashMap<>();

        for (String s : cpdomains) {
            domainCount(domainCount, s);
        }

        return domainCount.entrySet().stream()
            .map(entry -> new StringBuilder().append(entry.getValue()).append(" ").append(entry.getKey()).toString())
            .collect(Collectors.toList());
    }

    // 我以前用while loop去持续check indexOf() 是非常费时的
    private static void domainCount(Map<String, Integer> domainCount, String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                String num = s.substring(0, i);
                count = Integer.parseInt(num);
            }
            if (c == '.' || c == ' ') {
                String domain  = s.substring(i + 1);
                domainCount.put(domain, domainCount.getOrDefault(domain, 0) + count);
            }
        }
    }

    public static void main(String[] args) {
        List<String> result = subdomainVisits(new String[]{"9001 discuss.leetcode.com"});
    }
}
