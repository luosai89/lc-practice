package LC_Medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 2021: 9/9
 *
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array
 * prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take
 * course ai. For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 *
 * Topics: BFS, DFS, Graph, Topological Sort
 */
public class M_0207_Course_Schedule {

    // todo Method 1: BFS + Topological Sort + O(V * E)

    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        // Method: BFS + Topological Sort
        // Topological sort:
        // 2) linear ordering of vertices -> example: edge uv, linear order of u -> v
        // 1) directed acyclic graph (DAG)

        // 思路: 建立int[] indegrees，记录每一门课需要的先修课的数量，从完成的课开始，去寻找接下来可以完成的课
        // 如果最后indegrees values都为0，那么所有课程都完成了

        int[] indegree = new int[numCourses];
        for(int[] pair : prerequisites) {
            indegree[pair[0]]++;
        }

        Queue<Integer> completed = new LinkedList<>();
        for(int i = 0; i < indegree.length; i++) {
            if(indegree[i] == 0) {
                completed.offer(i);
            }
        }

        while(!completed.isEmpty()) {
            int current = completed.poll();
            for(int[] pair : prerequisites) {
                if(indegree[pair[0]] == 0) continue;
                if(pair[1] == current) {
                    if(--indegree[pair[0]] == 0) {
                        completed.offer(pair[0]);
                    }
                }
            }
        }

        for(int n : indegree) {
            if(n > 0) return false;
        }
        return true;
    }

    // todo Method 1 时间优化: BFS + Topological Sort + O(V * E)

    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] postReqs = new ArrayList[numCourses];
        for(int i = 0; i < postReqs.length; i++) {
            postReqs[i] = new ArrayList<>();
        }
        // 用一个ArrayList Array 来存储

        int[] indegree = new int[numCourses];
        for(int[] pair : prerequisites) {
            postReqs[pair[1]].add(pair[0]);
            indegree[pair[0]]++;
        }

        Queue<Integer> completed = new LinkedList<>(); // bfs
        int count = 0;
        for(int i = 0; i < indegree.length; i++) {
            if(indegree[i] == 0) {
                completed.offer(i);
                count++;
            }
        }

        while(!completed.isEmpty()) {
            int n = completed.poll();
            for(int j : postReqs[n]) {
                if(--indegree[j] == 0) {
                    completed.offer(j);
                    count++;
                }
            }
        }

        return count == numCourses;
    }
}
