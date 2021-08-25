package LC_Medium;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 2021: 8/24 (R)
 *
 * https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/
 *
 * You are in a city that consists of n intersections numbered from 0 to n - 1 with bi-directional roads between some
 * intersections. The inputs are generated such that you can reach any intersection from any other intersection and that
 * there is at most one road between any two intersections.
 *
 * You are given an integer n and a 2D integer array roads where roads[i] = [ui, vi, timei] means that there is a road
 * between intersections ui and vi that takes timei minutes to travel. You want to know in how many ways you can travel
 * from intersection 0 to intersection n - 1 in the shortest amount of time.
 *
 * Return the number of ways you can arrive at your destination in the shortest amount of time. Since the answer may be
 * large, return it modulo 109 + 7.
 */

public class M_1976_Number_of_Ways_to_Arrive_at_Destination {

    // Method 1: Dijkstra: https://www.youtube.com/watch?v=pVfj6mxhdMw
    // Solution source: https://tinyurl.com/55newsh7
    public int countPaths(int n, int[][] roads) {
        final int mod = 1000000007;

        // reorganize roads to be searchable in O(1)
        int[][] connections = new int[n][n];
        for (int[] road : roads) {
            connections[road[0]][road[1]] = road[2];
            connections[road[1]][road[0]] = road[2];
        }

        // shortest time between start vertex AND all vertexes, mostly unknown, so set at infinity (int max val)
        int[] shortest = new int[n];
        Arrays.fill(shortest,1, n, Integer.MAX_VALUE);

        // pretty much the answer, but calculated for all vertexes (not just the destination n - 1)
        int[] waysDP = new int[n];
        waysDP[0] = 1;

        // each unvisited vertex is represented by this vertex and the distance to start vertex
        Queue<int[]> unvisited = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        unvisited.offer(new int[]{0, 0});

        // start form start vertex, examine neighbor
        // visit next - closest to last visited
        // if the calculated distance of a vertex < known shortest distance, update

        // WHILE vertices remain unvisited
        while(!unvisited.isEmpty()){
            // visit unvisited vertex (e[0]) that's closest (e[1], via priority queue) to the start vertex
            int[] e = unvisited.poll();
            int vertex = e[0], time = e[1];

            // if this vertex is worth visiting
            if (time <= shortest[vertex]) {
                // for each of its neighbor
                for (int neighbor = 0; neighbor < n; neighbor++) {
                    if (connections[vertex][neighbor] != 0) {
                        // 1) ... if it's faster to get to this neighbor via the current vertex
                        if (shortest[vertex] + connections[vertex][neighbor] < shortest[neighbor]) {
                            shortest[neighbor] = shortest[vertex] + connections[vertex][neighbor];
                            unvisited.offer(new int[]{neighbor, shortest[neighbor]});
                            // update ways to get to neighbor to be the same as ways to get to vertex
                            waysDP[neighbor] = waysDP[vertex];
                        // 2) ... if it matches the fastest other ways to get to this neighbor via the current vertex
                        } else if (shortest[vertex] + connections[vertex][neighbor] == shortest[neighbor]) {
                            // add ways to get to vertex to the current ways to get to neighbor
                            waysDP[neighbor] = (waysDP[neighbor] + waysDP[vertex]) % mod;
                        }
                        // 3) ... if it's slower to get to this neighbor via the current vertex, discard and move on
                    }
                }
            }
        }
        return waysDP[n - 1]; // we just need to know the last node
    }
}
