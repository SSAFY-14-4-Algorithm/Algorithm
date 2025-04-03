package week9;

/**
 * JAVA11 : 메모리 45,752 KB 시간 628ms
 * @author KIM MINGYU jun3021303@gmail.com
 */
import java.io.IOException;
import java.util.PriorityQueue;

public class Baekjoon1647 {
    private static class Edge implements Comparable<Edge> {
        int node1, node2, weight;

        Edge(int node1, int node2, int weight) {
            this.node1 = node1;
            this.node2 = node2;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e) {
            return Integer.compare(this.weight, e.weight);
        }
    }

    private static int N, M;
    private static int[] parent, rank;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        parent = new int[N + 1];
        rank = new int[N + 1];
        PriorityQueue<Edge> graph = new PriorityQueue<>(M);
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
        while (M-- > 0)
            graph.add(new Edge(readInt(), readInt(), readInt()));
        int totalWeight = 0;
        int maxWeight = 0;
        int count = 0;
        while (count < N - 1) {
            Edge edge = graph.poll();
            if (find(edge.node1) == find(edge.node2))
                continue;
            totalWeight += edge.weight;
            if (maxWeight < edge.weight)
                maxWeight = edge.weight;
            union(edge.node1, edge.node2);
            count++;
        }
        System.out.print(totalWeight - maxWeight);
    }

    private static int find(int x) {
        if (x == parent[x])
            return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        // 같은 집합일 경우
        if (rootX == rootY)
            return;

        // rank가 큰 트리에 작은 트리를 붙임
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else { // rank가 같다면 임의로 연결
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }

    private static int readInt() throws IOException {
        int c, n = 0;
        while ((c = System.in.read()) > 47)
            n = (n * 10) + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }
}
