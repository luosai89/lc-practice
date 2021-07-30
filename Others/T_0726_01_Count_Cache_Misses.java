package Others;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * 7/29 (makeup 7/27)
 */
public class T_0726_01_Count_Cache_Misses {
    public int countCacheMissesSolution(int cacheSize, List<Integer> pageRequests) {
        Set<Integer> firstInFifoOutCacheSet = new HashSet<>();
        Queue<Integer> firstInFirstOutCacheQueue = new LinkedList<>();
        int cacheMiss = 0;
        for (Integer pageInteger : pageRequests) {
            if (!firstInFifoOutCacheSet.contains(pageInteger)) {
                cacheMiss++;
                firstInFirstOutCacheQueue.add(pageInteger);
                firstInFifoOutCacheSet.add(pageInteger);
                if (firstInFirstOutCacheQueue.size() > cacheSize) {
                    firstInFifoOutCacheSet.remove(firstInFirstOutCacheQueue.remove());
                }
            }
        }
        return cacheMiss;
    }

    public static int countCacheMisses(int cacheSize, List<Integer> pageRequests) {
        // Write your code here
        Deque<Integer> queue = new ArrayDeque<>();
        int res = 0;
        for (int i = 0; i < pageRequests.size(); i++) {
            int currReq = pageRequests.get(i);
            if (!queue.contains(currReq)) {
                res++;
                queue.addLast(currReq);
            }
            while (queue.size() > cacheSize) {
                queue.removeFirst();
            }
        }
        return res;
    }
}
