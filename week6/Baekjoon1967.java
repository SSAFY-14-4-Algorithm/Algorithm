package test;

import java.io.*;
import java.util.*;

/**
 * 
 * 메모리: 21848
 * 시간: 200	
 *
 */

public class Baekjoon1967 {
    static int n;
    static boolean[] visited;
    static Queue<int[]> queue;
    static List<List<int[]>> graph;
    static int maxDistance = 0;
    static int endNode = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i <= n ; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            graph.get(a).add(new int[] {b, l});
            graph.get(b).add(new int[] {a, l});
        }

        visited = new boolean[n + 1];
        dfs(1, 0);

        visited = new boolean[n + 1];
        maxDistance = 0;
        dfs(endNode, 0);

        System.out.println(maxDistance);
    }

    public static void dfs(int node, int distance) {
        visited[node] = true;

        if (distance > maxDistance) {
            maxDistance = distance;
            endNode = node;
        }

        for (int[] next : graph.get(node)) {
            int nextNode = next[0];
            int nextLength = next[1];
            if (!visited[nextNode]) {
                dfs(nextNode, distance + nextLength);
            }
        }
    }
}