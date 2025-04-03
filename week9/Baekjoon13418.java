package week9;

/**
 * JAVA11 : 메모리 37,872 KB 시간 540ms
 * @author KIM MINGYU jun3021303@gmail.com
 */
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Baekjoon13418 {
    private static class Edge implements Comparable<Edge> {
        int node1, node2, isDownHill;

        Edge(int node1, int node2, int isUpHill) {
            this.node1 = node1;
            this.node2 = node2;
            this.isDownHill = isUpHill;
        }

        @Override
        public int compareTo(Edge e) {
            return Integer.compare(this.isDownHill, e.isDownHill);
        }
    }

    private static int N, M;
    private static int[] parentD, rankD, parentU, rankU;

    public static void main(String[] args) throws IOException {
        // 입력 및 초기화
        N = readInt();
        M = readInt();
        int edgesCount = M + 1;
        parentD = new int[N + 1];
        parentU = new int[N + 1];
        rankD = new int[N + 1];
        rankU = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parentU[i] = i;
            parentD[i] = i;
            rankU[i] = 0;
            rankD[i] = 0;
        }
        // 내리막 우선
        PriorityQueue<Edge> graphDownFirst = new PriorityQueue<>(edgesCount, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return Integer.compare(o2.isDownHill, o1.isDownHill);
            }
        });
        // 오르막 우선
        PriorityQueue<Edge> graphUpFirst = new PriorityQueue<>(edgesCount, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return Integer.compare(o1.isDownHill, o2.isDownHill);
            }
        });
        // 그래프에 요소 추가
        for (int i = 0; i < edgesCount; i++) {
            Edge e = new Edge(readInt(), readInt(), readInt());
            graphDownFirst.add(e);
            graphUpFirst.add(e);
        }
        // 최소 오르막 수 계산
        int minUpHillCount = 0;
        int edgeCount = 0;
        while (edgeCount < N) {
            Edge edge = graphDownFirst.poll();
            if (find(edge.node1, parentD) == find(edge.node2, parentD))
                continue;
            union(edge.node1, edge.node2, parentD, rankD);
            if (edge.isDownHill != 1)
                minUpHillCount++;
            edgeCount++;
        }
        // 최대 오르막 수 계산
        int maxUpHillCount = 0;
        edgeCount = 0;
        while (edgeCount < N) {
            Edge edge = graphUpFirst.poll();
            if (find(edge.node1, parentU) == find(edge.node2, parentU))
                continue;
            union(edge.node1, edge.node2, parentU, rankU);
            if (edge.isDownHill != 1)
                maxUpHillCount++;
            edgeCount++;
        }
        // 출력
        System.out.print(maxUpHillCount * maxUpHillCount - minUpHillCount * minUpHillCount);
    }

    private static int find(int x, int[] parent) {
        if (x == parent[x])
            return x;
        return parent[x] = find(parent[x], parent);
    }

    private static void union(int x, int y, int[] parent, int[] rank) {
        int rootX = find(x, parent);
        int rootY = find(y, parent);

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
