package week6;

/**
 * <h1>BAEKJOON 1967번 트리의 지름 GOLD IV</h1>
 * <p>
 * JAVA 8 : 메모리 15,080KB, 시간 108ms <br>
 * JAVA11 : 메모리 17,332KB, 시간 140ms
 * </p>
 * 
 * @author KIM MINGYU jun3021303@gmail.com
 * @since 2025-03-12
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Baekjoon1967 {
    private static class Node {
        int id;
        List<Node> neighbors;
        List<Integer> weights;

        Node(int id) {
            this.id = id;
            this.neighbors = new ArrayList<>();
            this.weights = new ArrayList<>();
        }
    }

    private static Node[] nodes;
    private static boolean[] visited;
    private static int maxDistance = 0;
    private static int farthestNode = 0;
    private static int n;

    public static void main(String[] args) throws IOException {
        n = readInt();
        nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i);
        }
        for (int i = 0; i < n - 1; i++) {
            int parent = readInt() - 1;
            int child = readInt() - 1;
            int weight = readInt();
            nodes[parent].neighbors.add(nodes[child]);
            nodes[parent].weights.add(weight);
            nodes[child].neighbors.add(nodes[parent]);
            nodes[child].weights.add(weight);
        }

        // 임의의 시작점에서 DFS -> 가장 먼 노드 탐색
        visited = new boolean[n];
        dfs(nodes[0], 0);

        // 첫 번째 DFS에서 찾은 farthestNode를 시작점으로 해서 두 번째 DFS 수행
        visited = new boolean[n];
        maxDistance = 0;
        dfs(nodes[farthestNode], 0);

        System.out.print(maxDistance);
    }

    private static void dfs(Node current, int sum) {
        if (sum > maxDistance) {
            maxDistance = sum;
            farthestNode = current.id;
        }
        visited[current.id] = true;
        for (int i = 0; i < current.neighbors.size(); i++) {
            Node next = current.neighbors.get(i);
            if (!visited[next.id]) {
                dfs(next, sum + current.weights.get(i));
            }
        }
    }

    private static int readInt() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = n * 10 + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }
}
