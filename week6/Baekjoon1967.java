package week6;

import java.io.*;
import java.util.*;
// 메모리 23940KB 실행시간 204ms
public class Baekjoon1967 {

    static int n;
    static List<List<Integer>> tree;
    static boolean[] visited;
    static int maxDist = 0;
    static int farthestNode = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        tree = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 1; i < n; i++) {
            String[] input = br.readLine().split(" ");
            int parent = Integer.parseInt(input[0]);
            int child = Integer.parseInt(input[1]);
            int weight = Integer.parseInt(input[2]);

            tree.get(parent).add(child);
            tree.get(parent).add(weight);

            tree.get(child).add(parent);
            tree.get(child).add(weight);
        }

        visited = new boolean[n + 1];
        dfs(1, 0);

        visited = new boolean[n + 1];
        maxDist = 0;
        dfs(farthestNode, 0);

        System.out.println(maxDist);
    }

    public static void dfs(int node, int dist) {
        visited[node] = true;

        if (dist > maxDist) {
            maxDist = dist;
            farthestNode = node;
        }

        List<Integer> adj = tree.get(node);
        for (int i = 0; i < adj.size(); i += 2) {
            int nextNode = adj.get(i);
            int weight = adj.get(i + 1);

            if (!visited[nextNode]) {
                dfs(nextNode, dist + weight);
            }
        }
    }
}
