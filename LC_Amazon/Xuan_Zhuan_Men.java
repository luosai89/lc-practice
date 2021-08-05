package LC_Amazon;

import java.util.LinkedList;
import java.util.Queue;

/**
 * OA prep
 * 8/5 (makeup 8/4)
 */
public class Xuan_Zhuan_Men {
    public static int[] getTimes(int[] time, int[] direction) {
        // for each person passing (total n), form the exit and entrance queues with their 0-based number (arrival time)
        // time O(n), space O(n)
        Queue<Integer> enters = new LinkedList<>();
        Queue<Integer> exits = new LinkedList<>();
        int numberOfPeople = time.length;
        for(int personIdx = 0; personIdx < numberOfPeople; personIdx++) {
            if (direction[personIdx] == 1) exits.offer(personIdx);
            else enters.offer(personIdx);
        }

        int currentTime = 0;
        Queue<Integer> currentQueue = exits;
        int[] passingTimeByPersonIdx = new int[numberOfPeople];

        // while both directions have remaining arrivals
        while(enters.size() > 0 && exits.size() > 0) {

            // get the next number by peeking the queues, and find their arrival times
            int nextEntranceTime = time[enters.peek()];
            int nextExitTime = time[exits.peek()];

            // at current time, examine which queue should move next (tracked by q)
            // int currentTime = lastTime + 1;
            if (currentTime < nextEntranceTime && currentTime < nextExitTime) {
                // 3) The turnstile was not used, take whoever is next but prioritize exits
                currentQueue = (nextEntranceTime < nextExitTime) ? enters : exits;
                // remove the passing person, record passing time by personIdx, and advance current time
                // ... passing time now coincides with arrival time
                int personIdx = currentQueue.poll();
                passingTimeByPersonIdx[personIdx] = time[personIdx];
                currentTime = time[personIdx] + 1;

            } else {
                // 1) & 2) Xuan_Zhuan_Men was used last second - determine the right queue first
                if (!(currentTime >= nextEntranceTime && currentTime >= nextExitTime)) {
                    // If only one direction has people waiting at current time, move the queue from that direction
                    currentQueue = currentTime >= nextEntranceTime ? enters : exits;
                } // otherwise, we are already prioritizing the last queue moved

                // remove the passing person, record passing time by personIdx, and advance current time
                // ... passing time is current time >= arrival time
                int personIdx = currentQueue.poll();
                passingTimeByPersonIdx[personIdx] = currentTime;
                currentTime++;
            }
        }

        // if one queue is empty but the other queue expect more arrivals
        Queue<Integer> q = enters.size() > 0 ? enters : exits;
        while(q.size() > 0) {
            int personIdx = q.poll();
            // No one arriving now, advance time to the next arrival
            if (currentTime < time[personIdx]) currentTime = time[personIdx];
            passingTimeByPersonIdx[personIdx] = currentTime;
            currentTime++;
        }

        return passingTimeByPersonIdx;
    }
}
