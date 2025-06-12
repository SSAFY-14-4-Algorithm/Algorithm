package week16;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Baekjoon1916 {
    private static ArrayList<Node>[] node_List;
    private static int N, M, findFrom, findTo;
    private static int INF = Integer.MAX_VALUE;
    private static int[] minCost;

    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Arrays.fill(minCost, INF);
        minCost[findFrom] = 0;
        pq.add(new Node(findFrom, 0));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int nodeVertex = node.vertex;
            int nodeCost = node.Cost;
            if (minCost[nodeVertex] < nodeCost)
                continue;
            for (Node nextNode : node_List[nodeVertex]) {
                int vertex_update = nextNode.vertex;
                int minCost_update = nextNode.Cost + minCost[nodeVertex];
                if (minCost[vertex_update] > minCost_update) {
                    minCost[vertex_update] = minCost_update;
                    pq.add(new Node(vertex_update, minCost_update));
                }
            }
        }
    }

    private static class Node implements Comparable<Node> {
        int vertex;
        int Cost;

        public Node(int vertex, int Cost) {
            this.vertex = vertex;
            this.Cost = Cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.Cost - o.Cost;
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        // !변수 설정
        N = readInt();
        M = readInt();
        minCost = new int[N + 1];
        node_List = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            node_List[i] = new ArrayList<>();
        }
        // !입력
        for (int i = 0; i < M; i++) {
            int from = readInt();
            int to = readInt();
            int cost = readInt();
            node_List[from].add(new Node(to, cost));
        }
        findFrom = readInt();
        findTo = readInt();
        // !계산
        dijkstra();
        // !출력
        System.out.println(minCost[findTo]);
    }

    private static int readInt() throws IOException {
        int c = System.in.read(), n = 0;
        while (c <= ' ')
            c = System.in.read();
        while ('0' <= c && c <= '9') {
            n = (n * 10) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}